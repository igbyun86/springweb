package com.springweb.framework.excel;

import java.io.OutputStream;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.springweb.framework.util.StringUtil;

public class CustomExcelDataHandler implements ResultHandler<Map<String, Object>> {
	protected final Logger log = LogManager.getLogger();

	private SXSSFWorkbook workbook;
	private SXSSFSheet sheet;
	private String[] headerColumnNames;
	private boolean isFirst = true;
	private int rowIdx = 0;

	private CellStyle headerCellStyle;
	private CellStyle defaultCellStyle;

	public CustomExcelDataHandler(Map<String, Object> headerDataMap) {
		String header = StringUtil.toString(headerDataMap.get("header"));
		this.headerColumnNames = header.split("[|]");
		String sheetName = StringUtil.toString(headerDataMap.get("sheetName"));

		this.workbook = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
		this.workbook.setCompressTempFiles(true);
		this.sheet = workbook.createSheet(sheetName);

		this.setCellStype();
	}

	private void setCellStype() {

		Font headerFont;
		Font bodyFont;
		// 헤더 기본 폰트 설정
		headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(Font.COLOR_NORMAL);

		// 그리드 기본 폰트 설정
		bodyFont = workbook.createFont();
		bodyFont.setFontName("맑은 고딕");
		bodyFont.setFontHeightInPoints((short) 10);

		 // 헤더 셀 스타일 설정
		headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);				// align 정렬
		headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);			// vertical 정렬
		headerCellStyle.setBorderTop(BorderStyle.THIN);					// 테두리 설정
		headerCellStyle.setBorderLeft(BorderStyle.THIN);
		headerCellStyle.setBorderRight(BorderStyle.THIN);
		headerCellStyle.setBorderBottom(BorderStyle.THIN);

		// 기본 셀 스타일
		defaultCellStyle = workbook.createCellStyle();
		defaultCellStyle.setFont(bodyFont);
	}


	private void setText(SXSSFCell cell, String text) {
		cell.setCellType(CellType.STRING);
		cell.setCellValue(text);
	}

	private SXSSFCell getCell(SXSSFSheet sheet, int row, int col) {
		SXSSFRow sheetRow = sheet.getRow(row);
		if (sheetRow == null) {
			sheetRow = sheet.createRow(row);
		}

		SXSSFCell cell = sheetRow.getCell(col);
		if (cell == null) {
			cell = sheetRow.createCell(col);
		}

		return cell;
	}

	@Override
	public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {

		SXSSFCell cell;

		try {
			// col index초기화
			int colIdx = 0;

			// header render
			if (isFirst) {
				for (int i = 0; i < headerColumnNames.length; i++) {
					String headerColumnName = headerColumnNames[i];

					// write header
					cell = getCell(sheet, rowIdx, colIdx);
					setText(cell, headerColumnName);
					// style
					cell.setCellStyle(headerCellStyle);

					// 컬럼 자동 Width
					sheet.setColumnWidth(colIdx, (sheet.getColumnWidth(colIdx)) + 1000);
					colIdx ++;
				}

				isFirst = false;
				rowIdx++;
			}

			// 새로운 Row 생성
			SXSSFRow row = (SXSSFRow)sheet.createRow(rowIdx);

			// DB에서 조회되는 ROW
			Map<String, Object> rowMap = resultContext.getResultObject();

			// content render
			int offset = 0;
			for (int i = 0; i < headerColumnNames.length; i++) {
				String headerColumnName = headerColumnNames[i];
				String colVal = StringUtil.toString(rowMap.get(headerColumnName));
				cell = (SXSSFCell)row.createCell(offset);
				cell.setCellStyle(defaultCellStyle);
				setText(cell, colVal);

				offset++;
			}
			rowIdx++;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(OutputStream stream) {
		try {
			workbook.write(stream);
			workbook.dispose();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

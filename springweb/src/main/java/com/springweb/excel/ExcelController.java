package com.springweb.excel;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springweb.excel.service.ExcelService;
import com.springweb.framework.excel.CustomExcelDataHandler;
import com.springweb.framework.util.JsonUtil;
import com.springweb.framework.util.StringUtil;

@Controller
@RequestMapping("/excel")
public class ExcelController {

	protected final Logger log = LogManager.getLogger();

	@Resource(name="excelService")
	private ExcelService excelService;

	/**
	 * 엑셀 다운로드 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "", "/" })
	public String initialPage(Model model) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		return "/excel/excelDown";
	}



	@RequestMapping(value="/exceldown")
	public void downloadExcel(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String jsonString = request.getParameter("__json__data");
		Map<String, Object> paramMap = JsonUtil.getMapFromJsonStr(jsonString);
		Map<String, Object> headerMap = (Map<String, Object>) paramMap.get("headData");

		try {

			String fileName = StringUtil.toString(headerMap.get("fileName"));
			fileName=URLEncoder.encode(fileName, "UTF8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
			response.setContentType("application/vnd.ms-excel");

			// 조회 및 Excel 생성
			CustomExcelDataHandler dataHandler = new CustomExcelDataHandler(headerMap);
			excelService.downloadExcel(paramMap, dataHandler);
			dataHandler.write(response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

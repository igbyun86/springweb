package com.springweb.excel.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springweb.excel.service.ExcelService;
import com.springweb.excel.service.mapper.ExcelMapper;
import com.springweb.framework.excel.CustomExcelDataHandler;

@Service(value="excelService")
public class ExcelServiceImpl implements ExcelService {

	@Resource(name="com.springweb.excel.service.mapper.excelMapper")
	private ExcelMapper excelMapper;


	@Override
	public void downloadExcel(Map<String, Object> paramMap, CustomExcelDataHandler dataHandler) throws Exception {
		excelMapper.downloadExcel(paramMap, dataHandler);

	}

}

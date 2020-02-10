package com.springweb.excel.service;

import java.util.Map;

import com.springweb.framework.excel.CustomExcelDataHandler;

public interface ExcelService {

	public void downloadExcel(Map<String,Object> paramMap, CustomExcelDataHandler dataHandler) throws Exception;
}

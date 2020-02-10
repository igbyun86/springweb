package com.springweb.excel.service.mapper;

import java.util.Map;

import com.springweb.framework.dataaccess.mapper.Mapper;
import com.springweb.framework.excel.CustomExcelDataHandler;

@Mapper(value="com.springweb.excel.service.mapper.excelMapper")
public interface ExcelMapper {


	public void downloadExcel(Map<String,Object> paramMap, CustomExcelDataHandler dataHandler) throws Exception;
}

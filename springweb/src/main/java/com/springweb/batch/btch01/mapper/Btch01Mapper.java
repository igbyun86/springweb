package com.springweb.batch.btch01.mapper;

import java.util.List;
import java.util.Map;

import com.springweb.framework.dataaccess.mapper.Mapper;

@Mapper
public interface Btch01Mapper {

	/**
	 * temp 테이블 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selTempData(Map<String, Object> map) throws Exception;

	/**
	 * temp 테이블2 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insTempData2(Map<String, Object> map) throws Exception;
}

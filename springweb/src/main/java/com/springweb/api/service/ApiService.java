package com.springweb.api.service;

import java.util.List;
import java.util.Map;

import com.springweb.api.model.EmpVO;

public interface ApiService {

	/**
	 * Api data 조회
	 * @return
	 */
	public List<EmpVO> selApiData(Map<String, Object> ParamMap) throws Exception;

	/**
	 * Api data 등록
	 */
	public void insApiData(Map<String, Object> ParamMap) throws Exception;

	/**
	 * Api data 수정
	 */
	public void updApiData(Map<String, Object> ParamMap) throws Exception;

	/**
	 * Api data 삭제
	 */
	public void delApiData(Map<String, Object> ParamMap) throws Exception;

}

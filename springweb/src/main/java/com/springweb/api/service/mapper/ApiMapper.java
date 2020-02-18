package com.springweb.api.service.mapper;

import java.util.List;
import java.util.Map;

import com.springweb.api.model.EmpVO;
import com.springweb.framework.dataaccess.mapper.Mapper;

@Mapper(value="com.springweb.api.service.mapper.apiMapper")
public interface ApiMapper {

	/**
	 * 사원 정보 조회
	 * @return
	 */
	public List<EmpVO> selApiData(Map<String, Object> ParamMap) throws Exception;

	/**
	 * 사원 정보 등록
	 */
	public void insApiData(EmpVO empVO)  throws Exception;

	/**
	 * 사원 정보 수정
	 */
	public void updApiData(EmpVO empVO)  throws Exception;

	/**
	 * 사원 정보 삭제
	 */
	public void delApiData(String empno) throws Exception;

}

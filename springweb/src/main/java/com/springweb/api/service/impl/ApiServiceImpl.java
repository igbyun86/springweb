package com.springweb.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springweb.api.model.EmpVO;
import com.springweb.api.service.ApiService;
import com.springweb.api.service.mapper.ApiMapper;

@Service(value="apiService")
public class ApiServiceImpl implements ApiService {

	@Resource(name="com.springweb.api.service.mapper.apiMapper")
	ApiMapper apiMapper;


	@Override
	public List<EmpVO> selApiData(Map<String, Object> ParamMap) throws Exception {
		return apiMapper.selApiData(ParamMap);
	}

	@Override
	public void insApiData(EmpVO empVO) throws Exception {
		apiMapper.insApiData(empVO);
	}

	@Override
	public void updApiData(EmpVO empVO) throws Exception {
		apiMapper.updApiData(empVO);
	}

	@Override
	public void delApiData(String empno) throws Exception {
		apiMapper.delApiData(empno);
	}

}

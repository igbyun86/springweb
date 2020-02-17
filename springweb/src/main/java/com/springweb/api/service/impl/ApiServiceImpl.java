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
	public void insApiData(Map<String, Object> ParamMap) throws Exception {
		apiMapper.insApiData(ParamMap);
	}

	@Override
	public void updApiData(Map<String, Object> ParamMap) throws Exception {
		apiMapper.updApiData(ParamMap);
	}

	@Override
	public void delApiData(Map<String, Object> ParamMap) throws Exception {
		apiMapper.delApiData(ParamMap);
	}

}

package com.springweb.bctr.service;

import java.util.Map;

/**
 * spring batch / quartz + spring batch 실행 Service
 * @author big
 *
 */
public interface BatchRunService {

	/**
	 * spring batch 실행
	 * @param paramMap
	 * @throws Exception
	 */
	public void batchRun(Map<String,Object> paramMap) throws Exception;

	/**
	 * quartz + spring batch 실행
	 * @param paramMap
	 * @throws Exception
	 */
	public void quartzRun(Map<String,Object> paramMap) throws Exception;
}

package com.springweb.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.springweb.framework.batch.SpringBatchRunner;
import com.springweb.framework.util.StringUtil;

public class ExcuteJob implements Job {

	protected static final Logger log = LogManager.getLogger();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap map = context.getJobDetail().getJobDataMap();

		log.debug("스케줄 실행!!");

		// spring batch job
		SpringBatchRunner springBatchRunner = (SpringBatchRunner) map.get("batchObject");

		// 배치 실행 job ID
		String batchJobId = StringUtil.toString(map.get("batchJobId"));

		// 추가 고유 파라미터
		// 파라미터가 동일할 경우 batch 실행이 안됨.
		String param = springBatchRunner.createUniqueJobParameters();
		try {

			springBatchRunner.start(batchJobId, param);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

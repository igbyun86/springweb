package com.springweb.bctr.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.springweb.bctr.service.BatchRunService;
import com.springweb.framework.batch.SpringBatchRunner;

@Service(value="batchRunService")
public class BatchRunServiceImpl implements BatchRunService {

	protected final Logger log = LogManager.getLogger();

	@Resource(name="springBatchRunner")
	private SpringBatchRunner springBatchRunner;

	@Override
	public void batchRun(Map<String, Object> paramMap) throws Exception {

		String param = springBatchRunner.createUniqueJobParameters();
		/*
		JobParameters jobParameters = new JobParametersBuilder()
					.addString("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"))
					.toJobParameters();
*/
		try {
			springBatchRunner.start("btch01Job", param);
		} catch (Exception e) {
			// TODO: handle exception
		}


	}

}

package com.springweb.bctr.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.springweb.bctr.service.BatchRunService;
import com.springweb.framework.batch.SpringBatchRunner;
import com.springweb.framework.util.StringUtil;
import com.springweb.quartz.QuartzScheduler;

/**
 * spring batch / quartz + spring batch 실행 Service 구현
 * @author big
 *
 */
@Service(value="batchRunService")
public class BatchRunServiceImpl implements BatchRunService {

	protected final Logger log = LogManager.getLogger();

	@Resource(name="springBatchRunner")
	private SpringBatchRunner springBatchRunner;

	@Resource(name="quartzScheduler")
	private QuartzScheduler quartzScheduler;


	/**
	 * spring batch 실행
	 */
	@Override
	public void batchRun(Map<String, Object> paramMap) throws Exception {
		String batchJobId = StringUtil.toString(paramMap.get("batchJobId"));

		String param = springBatchRunner.createUniqueJobParameters();
		/*
		JobParameters jobParameters = new JobParametersBuilder()
					.addString("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"))
					.toJobParameters();
*/
		try {
			springBatchRunner.start(batchJobId, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * quartz + spring batch 실행
	 */
	@Override
	public void quartzRun(Map<String, Object> paramMap) throws Exception {
		try {

			// quartz 실행
			quartzScheduler.start(paramMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

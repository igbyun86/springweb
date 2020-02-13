package com.springweb.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.batch.core.JobParametersBuilder;

import com.springweb.framework.batch.SpringBatchRunner;
import com.springweb.framework.util.QuartzUtil;
import com.springweb.framework.util.StringUtil;

public class QuartzScheduler {
	protected static final Logger log = LogManager.getLogger();

	@Resource(name="springBatchRunner")
	private SpringBatchRunner springBatchRunner;

	/**
	 * Quartz 실행
	 * @param paramMap
	 * @throws SchedulerException
	 */
	public void start(Map<String, Object> paramMap) throws SchedulerException {
		log.debug(paramMap);

		// Scheduler 객체 생성
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		// 파라미터
		JobParametersBuilder builder = new JobParametersBuilder();	// Spring batch 파라미터
		JobDataMap dataMap = new JobDataMap();						// quartz 파라미터

		// batch 파라미터 셋팅
		dataMap.put("batchJobId", paramMap.get("batchJobId"));
		Map<String, Object> batchParam = (Map<String, Object>) paramMap.get("batchParam");

		if (batchParam != null) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				dataMap.put(key, value);
				builder.addString(key, String.valueOf(value));
			}
		}

		dataMap.put("batchParam", builder);

		// batch 실행 instance
		dataMap.put("batchObject", springBatchRunner);

		try {
			// 고유키 생성
			JobKey jobKey = new JobKey(StringUtil.toString(paramMap.get("jobKey")));

			// 공통 Quartz 클래스에서 필요한 변수들을 생성, 설정한다.
			JobDetail jobDetail = JobBuilder
										.newJob(ExcuteJob.class)
										.withIdentity(jobKey)
										.setJobData(dataMap).build();

			// Trigger
			Trigger trigger = setTrigger(paramMap);

			log.debug("스케줄 등록!!!");

			// 기존에 있다면 삭제 후 등록
			this.deleteJob(StringUtil.toString(paramMap.get("jobKey")));

			scheduler.start();

			// scheduler 등록
			scheduler.scheduleJob(jobDetail, trigger);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Trigger 설정
	 * @param paramMap
	 * @return
	 * @throws ParseException
	 */
	public Trigger setTrigger(Map<String, Object> paramMap) throws ParseException {
		Trigger trigger = null;
		Date startDate = null;

		String startDt = StringUtil.toString(paramMap.get("startDt"));
		String endDt = StringUtil.toString(paramMap.get("endDt"));

		// endDt가 없을 경우
		if (endDt == null) {
			endDt = "2099-12-31:1200";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HHmm");
		startDate = dateFormat.parse(startDt);

		Date endDate = null;
		endDate = dateFormat.parse(endDt);

		String croneExp = QuartzUtil.generateCronExpression(startDate);

		// 입력한 크론 표현식 사용
		if("CUSTOM".equals(paramMap.get("croneType"))) {
			croneExp = StringUtil.toString(paramMap.get("croneExp"));
		}

		log.debug("===== croneExp : " + croneExp);

		trigger = TriggerBuilder.newTrigger().startAt(startDate).endAt(endDate)
						.withSchedule(CronScheduleBuilder.cronSchedule(croneExp).withMisfireHandlingInstructionDoNothing())
						.build();

		return trigger;
	}


	/**
	 * Job list조회
	 * @return
	 * @throws SchedulerException
	 */
	public List<Map<String, Object>> getJobList() throws SchedulerException {

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				String jobName = jobKey.getName();
				String jobGroup = jobKey.getGroup();

				List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				Date nextFireTime = triggers.get(0).getNextFireTime();

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("jobKey", jobKey);
				map.put("jobName", jobName);
				map.put("jobGroup", jobGroup);
				map.put("nextFireTime", nextFireTime);
				list.add(map);

			}
		}

		return list;
	}


	/**
	 * job 삭제
	 * @param jobName
	 * @throws SchedulerException
	 */
	public void deleteJob(String jobName) throws SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		JobKey jobKey = new JobKey(jobName);
		if(scheduler.getJobDetail(jobKey) != null) {
			scheduler.deleteJob(jobKey);
		}
	}
}

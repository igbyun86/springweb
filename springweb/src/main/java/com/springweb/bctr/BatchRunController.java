package com.springweb.bctr;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springweb.bctr.service.BatchRunService;

/**
 * spring batch / quartz + spring batch 실행 컨트롤러
 * @author big
 *
 */
@Controller
@RequestMapping("/batch")
public class BatchRunController {

	protected final Logger log = LogManager.getLogger();

	@Resource(name="batchRunService")
	private BatchRunService batchRunService;

	/**
	 * 엑셀 다운로드 페이지
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "", "/" })
	public String initialPage(Model model) throws Exception {

		return "/batch/batchRun";
	}

	@RequestMapping(method = RequestMethod.POST, value="/batchrun")
	public void batchRun(Model model, @RequestBody Map<String, Object> paramMap) throws Exception {
		batchRunService.batchRun(paramMap);
	}


	@RequestMapping(method = RequestMethod.POST, value="/quartzrun")
	public void quartzRun(Model model, @RequestBody Map<String, Object> paramMap) throws Exception {
		batchRunService.quartzRun(paramMap);
	}


}

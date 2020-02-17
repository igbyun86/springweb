package com.springweb.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springweb.api.model.EmpVO;
import com.springweb.api.model.ResponseVO;
import com.springweb.api.service.ApiService;

@RestController
@RequestMapping(value="/api")
public class ApiController {

	protected final Logger log = LogManager.getLogger();

	@Resource(name="apiService")
	ApiService apiService;

	/**
	 * 조회
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/selapi",produces={"application/json; charset=UTF-8"})
	public @ResponseBody Map<String, Object> apiGet(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 필수 파라미터 체크
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResponseVO resVO = new ResponseVO();
		try {

			resultMap.put("header", resVO);

			List<EmpVO> list = apiService.selApiData(new HashMap<String, Object>());

			resultMap.put("resultList", list);
		} catch (Exception e) {
			resVO.setErrMsg(e.getMessage());
			resultMap.put("header", resVO);
		}

		return resultMap;
	}

	/**
	 * 등록
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/emp", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody Map<String, Object> apiPost(HttpServletRequest req, HttpServletResponse res) throws Exception {


		return null;
	}

	/**
	 * 수정
	 * @param empno
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/emp/{empno}", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody Map<String, Object> apiPut(@PathVariable("empno") String empno, HttpServletRequest req, HttpServletResponse res) throws Exception {


		return null;
	}

	/**
	 * 삭제
	 * @param empno
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/emp/{empno}", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody Map<String, Object> apiDelete(@PathVariable("empno") String empno, HttpServletRequest req, HttpServletResponse res) throws Exception {


		return null;
	}
}

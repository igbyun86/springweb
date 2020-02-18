package com.springweb.api;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springweb.api.model.EmpVO;
import com.springweb.api.model.ErrorType;
import com.springweb.api.model.ResponseVO;
import com.springweb.api.model.ResultType;
import com.springweb.api.service.ApiService;
import com.springweb.framework.exception.ApiExceptionBadRequest;
import com.springweb.framework.exception.ApiExceptionUnauthorized;
import com.springweb.framework.util.StringUtil;

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
	@GetMapping(value = "/emp",produces={"application/json; charset=UTF-8"})
	public @ResponseBody Map<String, Object> apiGet(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 필수 파라미터 체크
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResponseVO resVO = new ResponseVO();
		try {

			/**
			 * api key 체크
			 */
			apiKeyCheck(req);

			resVO.setResultCd(ResultType.SUCCESS.getValue());
			resultMap.put("header", resVO);

			// api data 조회
			resultMap.put("resultList", apiService.selApiData(new HashMap<String, Object>()));
		}
		catch (ApiExceptionUnauthorized aeu) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(aeu.getErrorType());
			resVO.setErrMsg(aeu.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		catch (Exception e) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(ErrorType.ETC_ERROR.toString());
			resVO.setErrMsg(e.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
	public @ResponseBody Map<String, Object> apiPost(@RequestBody EmpVO empVO, HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 필수 파라미터 체크
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResponseVO resVO = new ResponseVO();
		try {

			/**
			 * api key 체크
			 */
			apiKeyCheck(req);

			/**
			 * 필수 파라미터 체크
			 */
			if (StringUtil.isNullOrBlank(empVO.getEmpno())) {
				throw new ApiExceptionBadRequest(ErrorType.REQUIRE_PARAM_VALUE.toString(), "사번은 필수입력 사항입니다.");
			}

			resVO.setResultCd(ResultType.SUCCESS.getValue());
			resultMap.put("header", resVO);

			// api data 등록
			apiService.insApiData(empVO);

		}
		catch (ApiExceptionUnauthorized aeu) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(aeu.getErrorType());
			resVO.setErrMsg(aeu.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		catch (ApiExceptionBadRequest aeb) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(aeb.getErrorType());
			resVO.setErrMsg(aeb.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		catch (Exception e) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(ErrorType.ETC_ERROR.toString());
			resVO.setErrMsg(this.getSQLExceptionMessage(e));
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		return resultMap;
	}

	/**
	 * 수정
	 * @param empno
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/emp", produces = {"application/json; charset=UTF-8"})
	public @ResponseBody Map<String, Object> apiPut(@RequestBody EmpVO empVO, HttpServletRequest req, HttpServletResponse res) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResponseVO resVO = new ResponseVO();
		try {

			/**
			 * api key 체크
			 */
			apiKeyCheck(req);

			/**
			 * 필수 파라미터 체크
			 */
			if (StringUtil.isNullOrBlank(empVO.getEmpno())) {
				throw new ApiExceptionBadRequest(ErrorType.REQUIRE_PARAM_VALUE.toString(), "사번은 필수입력 사항입니다.");
			}

			resVO.setResultCd(ResultType.SUCCESS.getValue());
			resultMap.put("header", resVO);

			// api data 수정
			apiService.updApiData(empVO);

		}
		catch (ApiExceptionUnauthorized aeu) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(aeu.getErrorType());
			resVO.setErrMsg(aeu.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		catch (ApiExceptionBadRequest aeb) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(aeb.getErrorType());
			resVO.setErrMsg(aeb.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		catch (Exception e) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(ErrorType.ETC_ERROR.toString());
			resVO.setErrMsg(this.getSQLExceptionMessage(e));
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		return resultMap;
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

		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResponseVO resVO = new ResponseVO();
		try {

			/**
			 * api key 체크
			 */
			apiKeyCheck(req);

			/**
			 * 필수 파라미터 체크
			 */
			if (StringUtil.isNullOrBlank(empno)) {
				throw new ApiExceptionBadRequest(ErrorType.REQUIRE_PARAM_VALUE.toString(), "사번은 필수입력 사항입니다.");
			}

			resVO.setResultCd(ResultType.SUCCESS.getValue());
			resultMap.put("header", resVO);

			// api data 삭제
			apiService.delApiData(empno);

		}
		catch (ApiExceptionUnauthorized aeu) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(aeu.getErrorType());
			resVO.setErrMsg(aeu.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		catch (ApiExceptionBadRequest aeb) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(aeb.getErrorType());
			resVO.setErrMsg(aeb.getMessage());
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		catch (Exception e) {
			resVO.setResultCd(ResultType.ERROR.getValue());
			resVO.setErrCd(ErrorType.ETC_ERROR.toString());
			resVO.setErrMsg(this.getSQLExceptionMessage(e));
			resultMap.put("header", resVO);

			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		return resultMap;
	}

	/**
	 * header에서 api key 체크
	 */
	private void apiKeyCheck(HttpServletRequest req) {
		/**
		 * api key 체크
		 */
		String apiKey = null;
		Enumeration<String> headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = (String)headerNames.nextElement();

			if(StringUtil.isEqual("apikey", headerName)){
				apiKey = req.getHeader(headerName);
				break;
			}
		}

		String serviceKey = "111aaabbb";	// api 테스트로 비교대상 key값(운영에 따라 DB에서 비교할 수 있음)
		if (!StringUtil.isEqual(apiKey, serviceKey)) {
			throw new ApiExceptionUnauthorized(ErrorType.UNAUTHORIZED.toString(), "인증되지 않은 요청입니다");
		}
	}

	/**
	 * DB 키 중복인 경우 사용자 에러 메시지 처리
	 * @param ex
	 * @return
	 */
	private String getSQLExceptionMessage(Exception ex) {
		String sErrorMessage = "";

		if(ex instanceof org.springframework.dao.DuplicateKeyException){
			sErrorMessage = "이미 등록된 정보를 입력하였습니다.";
		}
		else{
			Throwable root = ExceptionUtils.getRootCause(ex);	// 마지막 에러
			if(root == null) root = ex;

			if(root instanceof java.sql.SQLException){
				java.sql.SQLException ex1 =  (java.sql.SQLException) root;
				sErrorMessage = ex1.getMessage();
			}
		}

		return sErrorMessage;
	}

}

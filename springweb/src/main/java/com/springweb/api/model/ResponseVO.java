package com.springweb.api.model;

import java.io.Serializable;

public class ResponseVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 243420094193973071L;

	/**
	 * 결과코드(00: 성공, 99: 에러)
	 */
	private String resultCd;

	/**
	 * 에러코드
	 */
	private String errCd;

	/**
	 * 에러메시지
	 */
	private String errMsg;


	public String getResultCd() {
		return resultCd;
	}

	public void setResultCd(String resultCd) {
		this.resultCd = resultCd;
	}

	public String getErrCd() {
		return errCd;
	}

	public void setErrCd(String errCd) {
		this.errCd = errCd;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}




}

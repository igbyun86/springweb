package com.springweb.framework.exception;

public class ApiExceptionBadRequest extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -1935755642593529067L;

	/**
	 * 에러 type
	 */
	private String errorType;

	/**
	 * 에러메시지
	 */
	private String message;

	public ApiExceptionBadRequest(String errorType, String errorMessage) {
		this.errorType = errorType;
		this.message = errorMessage;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

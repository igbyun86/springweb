package com.springweb.framework.exception;

public class ApiExceptionUnauthorized extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 3557156497594993045L;

	/**
	 * 에러 type
	 */
	private String errorType;

	/**
	 * 에러메시지
	 */
	private String message;

	public ApiExceptionUnauthorized(String errorType, String errorMessage) {
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

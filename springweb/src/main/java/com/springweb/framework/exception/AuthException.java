package com.springweb.framework.exception;

/**
 * 권한 없음 에러
 * @author big
 *
 */
public class AuthException extends RuntimeException {

	private static final long serialVersionUID = -2387190898882297870L;


	public AuthException() {
		super();
	}

	public AuthException(String message) {
		super(message);
	}

}

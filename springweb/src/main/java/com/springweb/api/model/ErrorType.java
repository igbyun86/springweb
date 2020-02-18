package com.springweb.api.model;

public enum ErrorType {
	UNAUTHORIZED				// 인증되지 않은 요청
	, REQUIRE_PARAM				// 필수값 필드 없음
	, REQUIRE_PARAM_VALUE		// 필수값 필드의 값 없음
	, ETC_ERROR					// 에러 메시지
}

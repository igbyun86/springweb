package com.springweb.api.model;

public enum ResultType {
	SUCCESS("00")			// 성공
	,ERROR("99");			// 에러

	private String value;

	ResultType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}

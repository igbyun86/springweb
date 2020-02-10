package com.springweb.framework.util;

public class StringUtil {

	/**
	 * 문자열이 null 이거나 blank 인지 체크
	 * @param param 문자열
	 * @return Boolean true/false
	 */
	public static boolean isNullOrBlank(String arg) {
		return arg == null || arg.trim().length() == 0;
	}

	/**
	* 공백이나 null이 아닐 경우 문자열 리턴, 그외에는 null 리턴
	* @param val
	* @return
	*/
	public static String toString(Object val){
		String result = null;
		if(val != null && !isNullOrBlank(val.toString())){
			/*result = val.toString();*/
			result =String.valueOf(val);
		}
		return result;
	}
}

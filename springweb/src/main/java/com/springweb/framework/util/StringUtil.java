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
	 * 문자열이 null 이거나 blank 인지 체크
	 * @param param 문자열
	 * @return Boolean true/false
	 */
	public static boolean isNullOrBlank(Object arg)
	{
		return ( (arg == null) ? true: isNullOrBlank(String.valueOf(arg)));
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

	/**
	 * 같은 문자열인지 비교
	 * @param val1
	 * @param val2
	 * @return Boolean true/false
	 */
	public static boolean isEqual(Object val1, Object val2){
		if(StringUtil.isNullOrBlank(val1)) return false;
		if(StringUtil.isNullOrBlank(val2)) return false;
		if(String.valueOf(val1).equals(String.valueOf(val2))) return true;

		return false;
	}

	/**
	 * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
	 * @param source 원본 문자열
	 * @param subject 원본 문자열에 포함된 특정 문자열
	 * @param object 변환할 문자열
	 * @return sb.toString() 새로운 문자열로 변환된 문자열
	 */
	public static String replace(String source, String subject, String object) {
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		String srcStr = source;

		while (srcStr.indexOf(subject) >= 0) {
			preStr = srcStr.substring(0, srcStr.indexOf(subject));
			nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
			srcStr = nextStr;
			rtnStr.append(preStr).append(object);
		}
		rtnStr.append(nextStr);

		return rtnStr.toString();
	}
}

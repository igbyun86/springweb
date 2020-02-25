package com.springweb.comm.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Constants {

	// main page
	public static final String COMMON_MAIN_PAGE = "comm/main";

	// exception page
	public static final String COMMON_EXCEPTION_PAGE = "comm/exception";


	// filter 제외 url
	private static Set<String> exclusionFilter = new HashSet<String>();
	static {
		exclusionFilter.add("/ddd");
	}

	//  수정불가능한 set을 return(unmodifiableSet - Returns an unmodifiable view of the specified set)
	public static final Set<String>  SITE_EXCLUSION_FILTER = Collections.unmodifiableSet(exclusionFilter);
}

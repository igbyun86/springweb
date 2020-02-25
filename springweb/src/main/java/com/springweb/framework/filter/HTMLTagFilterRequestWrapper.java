package com.springweb.framework.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.springweb.comm.config.Constants;
import com.springweb.framework.util.XssUtil;

/**
 * HTML tag filter
 * @author big
 *
 */
public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {

	public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public String[] getParameterValues(String parameter) {
		String values[] = super.getParameterValues(parameter);


		String requestPath = super.getPathInfo();
		if(requestPath != null) {
			boolean isMatch = false;
			for (String ePath : Constants.SITE_EXCLUSION_FILTER) {
				if (requestPath.startsWith(ePath)) {
					isMatch = true;
					break;
				}
			}
			if(isMatch) {
				return XssUtil.xssValidateExcept(values);
			}
		}

		return XssUtil.xssValidate(values);
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);

		String requestPath = super.getPathInfo();
		if(requestPath != null) {
			boolean isMatch = false;
			for (String ePath : Constants.SITE_EXCLUSION_FILTER) {
				if (requestPath.startsWith(ePath)) {
					isMatch = true;
					break;
				}
			}
			if(isMatch) {
				return XssUtil.xssValidateExcept(value);
			}
		}
		return XssUtil.xssValidate(value);
	}
}

package com.springweb.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HTMLTagFilter implements Filter{


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String contentType = request.getContentType();
		if (contentType == null) {
			contentType = "text/html";
		}
		else {
			contentType = contentType.toLowerCase();
		}

		if (contentType.indexOf("json") > -1) {
			chain.doFilter(new RwBodyRequestWrapper((HttpServletRequest)request), response);
		}
		else {
			chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest)request), response);
		}



	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {

	}
}

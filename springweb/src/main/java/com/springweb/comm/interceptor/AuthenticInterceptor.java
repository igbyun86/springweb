package com.springweb.comm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticInterceptor extends HandlerInterceptorAdapter {

	protected final Logger log = LogManager.getLogger();

	/**
	 * Controller 호출 전 실행
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.debug("Controller 호출 전 실행");

		return true;
	}

	/**
	 * Controller가 수행되고 화면이 보여지기 전 실행
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

		log.debug("Controller가 수행되고 화면이 보여지기 전 실행");
	}

	/**
	 * 클라이언트에 뷰를 통해 응답을 전송한뒤 실행
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		log.debug("클라이언트에 뷰를 통해 응답을 전송한뒤 실행");
	}


}

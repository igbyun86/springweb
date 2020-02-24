package com.springweb.comm.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.springweb.framework.exception.AuthException;

@Component
public class ExceptionResolver implements HandlerExceptionResolver {
	protected final Logger log = LogManager.getLogger();


	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {


		ModelAndView mav = new ModelAndView();

		String contentType = request.getContentType();
		if (contentType == null) {
			contentType = "text/html";
		}
		else {
			contentType = contentType.toLowerCase();
		}

		/**
		 * contentType에 따라 view를 결정해준다.
		 */
		if (contentType.indexOf("json") > -1 || contentType.indexOf("multipart") > -1) {
			response.setContentType("application/json");
			mav.setViewName("MappingJacksonJsonView");
		}
		else if (contentType.indexOf("xml") > -1) {
			response.setContentType("application/xml");
		}
		else {
			response.setContentType("text/html");
			mav.setViewName("/comm/exception");		// jsp 경로
		}

		/**
		 * 오류 class 정보
		 */
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Class<?> clazz = handlerMethod.getMethod().getDeclaringClass();

		log.debug("오류 발생 class : " + clazz.getName());
		log.debug("오류 발생 method : " + handlerMethod.getMethod().getName());


		/**
		 * exception 별 처리
		 */
		if (ex instanceof AuthException) {
			mav.setViewName("/main/login");
		}


		return mav;
	}

}

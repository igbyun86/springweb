package com.springweb.comm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	protected final Logger log = LogManager.getLogger();

	/**
	 * 인증에 실패한 경우
	 */
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		log.debug("로그인 실패!");


		request.setAttribute("errMsg", exception.getMessage());
		request.setAttribute("user_id", request.getParameter("user_id"));

		request.getRequestDispatcher("/login").forward(request, response);

	}
}

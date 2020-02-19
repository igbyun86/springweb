package com.springweb.comm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	protected final Logger log = LogManager.getLogger();


	/**
	 * 인증에 성공 후
	 */
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {

		log.debug("로그인 성공!");


		setDefaultTargetUrl("/main");	//로그인 성공 후 이동할 url


		super.onAuthenticationSuccess(req, res, authentication);
	}


}

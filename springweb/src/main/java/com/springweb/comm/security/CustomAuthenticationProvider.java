package com.springweb.comm.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.springweb.framework.util.StringUtil;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	protected final Logger log = LogManager.getLogger();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user_id = StringUtil.toString(authentication.getPrincipal());		// id
		String password = StringUtil.toString(authentication.getCredentials());		// password

		log.debug("로그인 인증 프로세스!!!");

		/**
		 * id / password 체크 test
		 */
		if (!"test".equals(user_id) || !"1234".equals(password)) {
			throw new BadCredentialsException("아이디와 비밀번호가 일치하지 않습니다.");
		}

		Map<String, String> userMap = new HashMap<>();
		userMap.put("empno", user_id);


		return (Authentication)new UsernamePasswordAuthenticationToken(userMap, "", new ArrayList());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

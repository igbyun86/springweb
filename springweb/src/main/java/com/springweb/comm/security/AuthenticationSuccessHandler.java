package com.springweb.comm.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.springweb.comm.session.SessionBinding;
import com.springweb.comm.vo.UserInfo;
import com.springweb.framework.util.SessionUtil;
import com.springweb.framework.util.WebUtil;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	protected final Logger log = LogManager.getLogger();


	/**
	 * 인증에 성공 후
	 */
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {

		log.debug("로그인 성공!");

		Map<String, String> userMap = (Map<String, String>) authentication.getPrincipal();

		/**
		 * 사용자 정보 DB 조회해서 session에 입력
		 */
		String empno = userMap.get("empno");
		String accessIp = WebUtil.getClientIpAddr(req);
		UserInfo userInfo = new UserInfo();
		userInfo.setEMPNO(empno);
		userInfo.setACCESS_IP(accessIp);


		HttpSession session;
		try {
			session = req.getSession();

			// 기존에 session이 있다면 삭제
			if (session.isNew()) {
				session.removeAttribute("UserInfo");
			}

			// session에 userInfo 입력
			SessionUtil.setUserInfo(session, userInfo);

			SessionBinding.bind(session, empno + "|" + accessIp);

		} catch (Exception e) {
			e.printStackTrace();
		}


		//로그인 성공 후 이동할 url
		setDefaultTargetUrl("/main");

		super.onAuthenticationSuccess(req, res, authentication);
	}


}

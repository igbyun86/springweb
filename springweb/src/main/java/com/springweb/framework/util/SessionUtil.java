package com.springweb.framework.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springweb.comm.vo.UserInfo;

/**
 * Session Util
 * @author big
 *
 */
public class SessionUtil {

	private final static String USER_INFO_SESSION_NM = UserInfo.class.getName();


	/**
	 * get UserInfo
	 * @return
	 */
	public static UserInfo getUserInfo() {
		try {
			return (UserInfo) getAttribute(USER_INFO_SESSION_NM);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * set UserInfo
	 * @param userInfo
	 */
	public static void setUserInfo(HttpSession httpSession, UserInfo userInfo) {
		try {
			setAttribute(httpSession, USER_INFO_SESSION_NM, userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * userInfo 삭제
	 * @param userInfo
	 */
	public static void removeUserInfo() {
		try {
			removeAttribute(USER_INFO_SESSION_NM);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * attribute 값을 가져 오기 위한 method
	 *
	 * @param String  attribute key name
	 * @return Object attribute obj
	 */
	public static Object getAttribute(String name) throws Exception {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttribute.getRequest().getSession();
		return (Object)httpSession.getAttribute(name);
	}

	/**
	 * attribute 설정 method
	 *
	 * @param String  attribute key name
	 * @param Object  attribute obj
	 * @return void
	 */
	public static void setAttribute(String name, Object object) throws Exception {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttribute.getRequest().getSession();

		httpSession.setAttribute(name, object);
	}

	/**
	 * attribute 설정 method
	 * @param httpSession
	 * @param name
	 * @param object
	 * @throws Exception
	 */
	public static void setAttribute(HttpSession httpSession, String name, Object object) throws Exception {
		httpSession.setAttribute(name, object);
	}

	/**
	 * 설정한 attribute 삭제
	 *
	 * @param String  attribute key name
	 * @return void
	 */
	public static void removeAttribute(String name) throws Exception {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttribute.getRequest().getSession();

		httpSession.removeAttribute(name);
	}

	/**
	 * session id
	 *
	 * @param void
	 * @return String SessionId 값
	 */
	public static String getSessionId() throws Exception {
		return RequestContextHolder.getRequestAttributes().getSessionId();
	}
}

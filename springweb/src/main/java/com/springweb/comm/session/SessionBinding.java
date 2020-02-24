package com.springweb.comm.session;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionBinding implements HttpSessionBindingListener, Serializable {

	private static final long serialVersionUID = -8706165552849577959L;

	protected final Logger log = LogManager.getLogger();

	public static void bind(HttpSession session, String userId) {
		session.setAttribute(userId, new SessionBinding());
	}

	/**
	 * session이 할당될 때 호출
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();

		if (session != null) {
			log.info("HttpSessionBindingEvent BOUND as " + event.getName() + " to " + session.getId());
		}
	}

	/**
	 * session이 제거될 때 호출
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		if (session != null) {
			String eName = event.getName();
			log.info("HttpSessionBindingEvent UNBOUND as " + eName + " to " + session.getId());
		}
	}
}

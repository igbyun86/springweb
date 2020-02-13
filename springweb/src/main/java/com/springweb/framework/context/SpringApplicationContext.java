package com.springweb.framework.context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

	protected static final Logger log = LogManager.getLogger();

	private static ApplicationContext APPLICATION_CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		APPLICATION_CONTEXT = applicationContext;
	}

	public static Object getBean(String beanName) {
		return APPLICATION_CONTEXT.getBean(beanName);
	}

	public static Object getBean(Class<?> classType) {
		return APPLICATION_CONTEXT.getBean(classType);
	}

	public static <T> T getBean(String beanName, Class<T> classType) {
		return classType.cast(APPLICATION_CONTEXT.getBean(beanName));
	}
}

package com.springweb.framework.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class QuartzUtil {


	/**
	 * Cron 표현식 설정
	 * @param date
	 * @return
	 */
	public static String generateCronExpression(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		return String.format("%1$s %2$s %3$s ? * MON-FRI", cal.get(Calendar.SECOND), cal.get(Calendar.MINUTE), cal.get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * Cron 표현식 설정
	 * @param date
	 * @return
	 */
	public static String generateCronExpression(Date date, String exp) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		return String.format("%1$s %2$s %3$s %s", cal.get(Calendar.SECOND), cal.get(Calendar.MINUTE), cal.get(Calendar.HOUR_OF_DAY), exp);
	}
}

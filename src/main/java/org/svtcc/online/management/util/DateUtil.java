package org.svtcc.online.management.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static int daysBetween(Date firstDate, Date secondDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(secondDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
}

package com.master.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.dao.DataAccessException;

public class DateUtil {
	/**
	 * 时间显示
	 * 
	 * @param time
	 *            自1900年开始的毫秒数
	 * @param pattern
	 * @return
	 */
	public static String show(Long time, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(cal.getTime());
	}

	/**
	 * 以默认方式显示时间("yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param time
	 *            自1900年开始的毫秒数
	 * @return
	 */
	public static String show(Long time) {
		return show(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static Timestamp currentTimestamp() throws DataAccessException {
		return DaoUtil.getCommonDao().currentTimestamp();
	}
}

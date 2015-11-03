/**
 * Copyright (c) since 2015 www.baguix.com, All Rights Reserved!
 */
package com.baguix.utils.data;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <b>当前日期获取类</b>
 *  
 * @author Suguang(Scott)
 * @since 1.0
 */
public class NowDateTool {
	// 日志
	private static final Logger logger = Logger.getLogger(NowDateTool.class);
	// 单例模式
	private static NowDateTool instance;
	private static NowDateTool getInstance(){
		if(instance == null){
			instance = new NowDateTool();
		}
		return instance;
	}

	// 构造器
	private NowDateTool() {

	}

	/**
	 * 根据格式获取当前时间字符串<br>
	 * G 年代标志符<br>
	 * y年  M月  d日  h时(1~12)  H时(0~23)  m分  s秒  S毫秒<br>
	 * E 星期<br>
	 * D一年中的第几天  F一月中第几个星期几  w一年中第几个星期  W 一月中第几个星期<br>
	 * a 上午 / 下午 标记符<br>
	 * k 时 在一天中 (1~24)  K 时 在上午或下午 (0~11)<br>
	 * z 时区<br>
	 *
	 * 例子：<br>
	 * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
	 * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
	 *
	 * @see java.text.SimpleDateFormat
	 * @param formatStr 日期的格式，参考SimpleDateFormat
	 * @return String 字符串的日期格式，如"2002-1-1 22:10:59"
	 */
	public static final String now(String formatStr) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		String dStr = formatter.format(cal.getTime());
		return dStr;
	}

	/**
	 * 获取如（2015-05-16）的当前日期。
	 * @return String 当前日期
	 */
	public static final String getDate() {
		String dStr = now(DateTool.YMD);
		return dStr;
	}

	/**
	 * 获取如（2015-05-16 18:06:38）的当前时间。
	 * @return String 当前时间
	 */
	public static final String getDateTime() {
		String dStr = now(DateTool.YMDHMS);
		return dStr;
	}

	/**
	 * 获取如（2015年05月16日）的当前时间。
	 * @return String 当前日期
	 */
	public static final String getCNDate() {
		String dStr = now(DateTool.CN_YMD);
		return dStr;
	}

	/**
	 * 获取如（2015年05月16日18时06分38秒）的当前时间。
	 * @return String 当前时间
	 */
	public static final String getCNDateTime() {
		String dStr = now(DateTool.CN_YMDHMS);
		return dStr;
	}

	/**
	 * 获取如（2015-5-16）的当前时间。
	 * @return String 当前日期
	 */
	public static final String getShortDate() {
		String dStr = now(DateTool.SHORT_YMD);
		return dStr;
	}

	/**
	 * 获取如（2015-5-16 18:28:9）的当前时间。
	 * @return String 当前日期
	 */
	public static final String getShortDateTime() {
		String dStr = now(DateTool.SHORT_YMDHMS);
		return dStr;
	}

	/**
	 * 获取如（2015年2月2日）的当前时间。
	 * @return String 当前日期
	 */
	public static final String getCNShortDate() {
		String dStr = now(DateTool.CN_SHORT_YMD);
		return dStr;
	}

	/**
	 * 获取如（2015年2月2日19时41分57秒）的当前时间。
	 * @return String 当前日期
	 */
	public static final String getCNShortDateTime() {
		String dStr = now(DateTool.CN_SHORT_YMDHMS);
		return dStr;
	}

	/**
	 * 获取如2015-5-16 18:28:9中的（2015）的当前4位格式的年份。
	 * @return String 当前年份
	 */
	public static final String getYear4() {
		String dStr = now("yyyy");
		return dStr;
	}

	/**
	 * 获取如2015-5-16 18:28:9中的（15）的当前2位格式的年份。
	 * @return String 当前年份
	 */
	public static final String getYear2() {
		String dStr = now("yy");
		return dStr;
	}

	/**
	 * 获取如2015-5-16 18:28:9中的（05）的当前2位格式的月份。
	 * @return String 当前月份
	 */
	public static final String getMonth2() {
		String dStr = now("MM");
		return dStr;
	}

	/**
	 * 获取如2015-5-16 18:28:9中的（5）的当前1位格式的月份。
	 * @return String 当前月份
	 */
	public static final String getMonth1() {
		String dStr = now("M");
		return dStr;
	}

	/**
	 * 获取如2015-5-02 18:28:9中的（02）的当前2位格式的日数。
	 * @return String 当前日数
	 */
	public static final String getDay2() {
		String dStr = now("dd");
		return dStr;
	}

	/**
	 * 获取如2015-5-02 18:28:9中的（2）的当前1位格式的日数。
	 * @return String 当前日数
	 */
	public static final String getDay1() {
		String dStr = now("d");
		return dStr;
	}

	/**
	 * 获取当前是星期几（如：星期六）。
	 * @return String 当前星期几
	 */
	public static final String getWeekName() {
		String dStr = now("E");
		return dStr;
	}

	/**
	 * 获取当前是星期几（如：2 - 星期二）。
	 * @return String 当前星期几
	 */
	public static int getWeek() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK) - 1;
		return day;
	}

	/**
	* 获取当前日期是当年的第几周（非ISO标准）<br>
	* 普通认识：1月1日是本年第一周，12月31日是本年最后一周。
	* @see #getWeekOfYearISO()
	* @return  int 第几周
	*/
	public static int getWeekOfYear() {
		return DateTool.getWeekOfYear(new Date());
	}

	/**
	* 获取当前日期是当年的第几周（Joda的算法）<br>
	* 以星期天作为一周的第一天，那么如果1月1日是星期2，3，4则这一周属于本年，即：12月31日为第1周。<br>
	* 如果是星期5，6，日，1那么这一周属于上一年。即：12月31日可能为第52或53周。<br>
	* 
	* 关于周日历的详情请参考：<a href='http://zh.wikipedia.org/wiki/ISO%E9%80%B1%E6%97%A5%E6%9B%86'></a>
	* @return int 第几周
	*/
	public static int getWeekOfYearISO() {
		return DateTool.getWeekOfYearISO(new Date());
	}

	/**
	 * 获取如2015-05-16 07:28:9中的（07）—— 2位当前小时数。
	 * @return String 获取当前小时数
	 */
	public static final String getHour2() {
		String dStr = now("HH");
		return dStr;
	}

	/**
	 * 获取如2015-05-16 07:28:9中的（7）—— 1位当前小时数。
	 * @return String 获取当前小时数
	 */
	public static final String getHour1() {
		String dStr = now("H");
		return dStr;
	}

	/**
	 * 获取如2015-05-16 07:08:09中的（08）—— 2位当前分钟数。
	 * @return String 获取当前分钟数
	 */
	public static final String getMinute2() {
		String dStr = now("mm");
		return dStr;
	}

	/**
	 * 获取如2015-05-16 07:08:09中的（8）—— 1位当前分钟数。
	 * @return String 获取当前分钟数
	 */
	public static final String getMinute1() {
		String dStr = now("m");
		return dStr;
	}

	/**
	 * 获取如2015-05-16 07:08:09中的（09）—— 2位当前秒钟数。
	 * @return String 获取当前秒钟数
	 */
	public static final String getSecond2() {
		String dStr = now("ss");
		return dStr;
	}

	/**
	 * 获取如2015-05-16 07:08:09中的（9）—— 1位当前秒钟数。
	 * @return String 获取当前秒钟数
	 */
	public static final String getSecond1() {
		String dStr = now("s");
		return dStr;
	}

	/**
	 * 获取如2015-05-16 07:08:09(/2015/05/16)文件路径形式。
	 * @return String 获取当前日期形式的路径
	 */
	public static final String getDateFilePath() {
		String dStr = now("/yyyy/MM/dd");
		return dStr;
	}

	/**
	 * 获取如2015-05-16 07:08:09(/2015/05/16/07/08/09)文件路径形式。
	 * @return String 获取当前时间形式的路径
	 */
	public static final String getDateTimeFilePath() {
		String dStr = now("/yyyy/MM/dd/HH/mm/ss");
		return dStr;
	}
	
	/**
	 * 获取如N年前的今天（N年前的某月某日），如：今天是2015-05-17，2年前2013-05-17
	 * @param n n年前
	 * @return  String，如：2013-05-17
	 */
	public static final String beforeNYear(int n) {
		Date now = new Date();
		String dStr = DateTool.beforeNYear(now, n);
		return dStr;
	}
	
	/**
	 * 获取如N年后的今天（N年后的某月某日），如：今天是2015-05-17，2年后2017-05-17
	 * @param n n年后
	 * @return  String，如：2017-05-17
	 */
	public static final String afterNYear(int n) {
		Date now = new Date();
		String dStr = DateTool.afterNYear(now, n);
		return dStr;
	}
	
	/**
	 * 获取如N天前，如：今天是2015-05-17，3天前是2015-05-14
	 * @param n n天前
	 * @return  String，如：2015-05-14
	 */
	public static final String beforeNDay(int n) {
		Date now = new Date();
		String dStr = DateTool.beforeNDay(now,n);
		return dStr;
	}
	
	/**
	 * 获取如N天后，如：今天是2015-05-17，3天后是2015-05-20
	 * @param n n天后
	 * @return  String，如：2015-05-20
	 */
	public static final String afterNDay(int n) {
		Date now = new Date();
		String dStr = DateTool.afterNDay(now, n);
		return dStr;
	}
}

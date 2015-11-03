/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.data;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <b>日期工具类</b><br>
 * 若本类不够用，可使用org.joda.time包。
 * @author Scott(SG)
 */
public class DateTool {
    // 单例模式
    private static DateTool instance;
    private static DateTool getInstance(){
        if(instance == null){
            instance = new DateTool();
        }
        return instance;
    }

    /** 日期格式:yyyy-MM-dd */
    public static final String YMD = "yyyy-MM-dd";

    /** 日期格式:yyyy-MM-dd HH:mm:ss */
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /** 时间格式:HH:mm:ss */
    public static final String HMS = "HH:mm:ss";

    /** 日期格式:yyyy-MM-dd HH:mm:ss SSS */
    public static final String YMDHMSS = "yyyy-MM-dd HH:mm:ss SSS";

    /** 短日期格式:yyyy-M-d */
    public static final String SHORT_YMD = "yyyy-M-d";

    /** 短日期格式:yyyy-M-d H:m:s */
    public static final String SHORT_YMDHMS = "yyyy-M-d H:m:s";

    /** 短日期格式:yyyy-M-d H:m:s S */
    public static final String SHORT_YMDHMSS = "yyyy-M-d H:m:s S";

    /** 中文日期格式:yyyy年MM月dd日 */
    public static final String CN_YMD = "yyyy年MM月dd日";

    /** 中文日期格式:HH时mm分ss秒 */
    public static final String CN_HMS = "HH时mm分ss秒";

    /** 中文日期格式:yyyy年MM月dd日 HH时mm分ss秒 */
    public static final String CN_YMDHMS = "yyyy年MM月dd日HH时mm分ss秒";

    /** 中文日期格式:yyyy年MM月dd日 HH时mm分ss秒 SSS毫秒 */
    public static final String CN_YMDHMSS = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";

    /** 中文短日期格式:yyyy年M月d日 */
    public static final String CN_SHORT_YMD = "yyyy年M月d日";

    /** 中文短日期格式:yyyy年M月d日 H时m分s秒 */
    public static final String CN_SHORT_YMDHMS = "yyyy年M月d日H时m分s秒";

    /** 中文短日期格式:yyyy年M月d日 H时m分s秒 S毫秒 */
    public static final String CN_SHORT_YMDHMSS = "yyyy年M月d日H时m分s秒S毫秒";

    // 隐藏构造器
    private DateTool() {
    }

    /**
     * <b>格式化日期</b><br>
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
     * @param date 日期
     * @param formatStr 日期的格式，参考SimpleDateFormat
     * @return String 字符串的日期格式，如"2002-1-1 22:10:59"
     */
    public static final String dateFormat(Date date, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String dStr = formatter.format(date);
        return dStr;
    }

    /**
     * <b>取得日期yyyy-MM-dd HH:mm:ss格式的字符串</b><br>
     * @param date 日期
     * @return 字符串的日期格式(yyyy-MM-dd HH:mm:ss)，如"2002-1-1 22:10:59"
     */
    public static final String getDateTimeStr(Date date) {
        String dStr = dateFormat(date, YMDHMS);
        return dStr;
    }

    /**
     * <b>取得日期yyyy-MM-dd格式的字符串</b><br>
     * @param date 日期
     * @return 字符串的日期格式(yyyy-MM-dd)，如"2002-1-1"
     */
    public static final String getDateStr(Date date) {
        String dStr = dateFormat(date, YMD);
        return dStr;
    }

    /**
     * <b>取得时间HH:mm:ss格式的字符串</b><br>
     * @param date 日期
     * @return 字符串的时间格式(HH:mm:ss)，如"22:10:59"
     */
    public static final String getTimeStr(Date date) {
        String dStr = dateFormat(date, HMS);
        return dStr;
    }

    /**
     * <b>取得日期yyyy年MM月dd日HH时mm分ss秒中文格式的字符串</b><br>
     * @param date 日期
     * @return 字符串的中文日期格式(yyyy年MM月dd日HH时mm分ss秒)，如"2002年12月01日22时10分59秒"
     */
    public static final String getCNDateTimeStr(Date date) {
        String dStr = dateFormat(date, CN_YMDHMS);
        return dStr;
    }

    /**
     * <b>取得日期yyyy年MM月dd日中文格式的字符串</b><br>
     * @param date 日期
     * @return 字符串的中文日期格式(yyyy年MM月dd日)，如"2002年12月01日"
     */
    public static final String getCNDateStr(Date date) {
        String dStr = dateFormat(date, CN_YMD);
        return dStr;
    }

    /**
     * <b>取得日期HH时mm分ss秒中文格式的字符串</b><br>
     * @param date 日期
     * @return 字符串的中文日期格式(HH时mm分ss秒)，如"22时10分59秒"
     */
    public static final String getCNTimeStr(Date date) {
        String dStr = dateFormat(date, CN_HMS);
        return dStr;
    }

    /**
     * <b>获取当前日期是当年的第几周（非ISO标准）</b><br>
     * 说明：<br>
     *     按普通认识：1月1日是本年第一周，12月31日是本年最后一周。
     * @param date 日期
     * @return  int 第几周
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        // 两年交接处，如果周数是1（第二年的第一周），但在本月中不是第一周（年末）
        if ((c.get(Calendar.WEEK_OF_YEAR) == 1) && (c.get(Calendar.WEEK_OF_MONTH) != 1)) {
            c.add(Calendar.DATE, -7);
            return c.get(Calendar.WEEK_OF_YEAR) + 1;
        } else {
            return c.get(Calendar.WEEK_OF_YEAR);
        }
    }

    /**
     * <b>获取当前日期是当年的第几周（Joda的算法，符合ISO日历标准）</b><br>
     *     说明：<br>
     *     以星期天作为一周的第一天，那么如果1月1日是星期2，3，4则这一周属于本年，即：12月31日为第1周。<br>
     *     如果是星期5，6，日，1那么这一周属于上一年。即：12月31日可能为第52或53周。<br>
     * @param date 日期
     * @return int 第几周
     */
    public static int getWeekOfYearISO(Date date) {
        DateTime dt = new DateTime(date);
        DateTime.Property week = dt.weekOfWeekyear();
        return week.get();
    }

    /**
     * <b>获取如N年前的今天（N年前的某月某日）</b><br>
     *     例如：今天是2015-05-17，2年前2013-05-17
     * @param date 日期
     * @param n n年前
     * @return  String，如：2013-05-17
     */
    public static final String beforeNYear(Date date, int n) {
        String dStr = "";
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy");
        String dy=f1.format(date);

        SimpleDateFormat f2 = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dend=f2.format(date);
        int year = Integer.parseInt(dy);
        n = year - n;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d = formatter.parse(Integer.toString(n)+ "-" + dend);
            dStr=formatter.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new DataException("com.baguix.utils.data.DateTool#beforeNYear: 年份格式错误!");
        }
        return dStr;
    }

    /**
     * <b>获取如N年后的那天（N年后的某月某日）</b><br>
     *     例如：date是2015-05-17，2年后2017-05-17
     * @param date 日期
     * @param n n年后
     * @return  String，如：2017-05-17
     */
    public static final String afterNYear(Date date, int n) {
        String dStr = "";
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy");
        String dy=f1.format(date);

        SimpleDateFormat f2 = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dend=f2.format(date);
        int year = Integer.parseInt(dy);
        n = year + n;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d = formatter.parse(Integer.toString(n)+ "-" + dend);
            dStr=formatter.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new DataException("com.baguix.utils.data.DateTool#afterNYear: 年份格式错误!");
        }
        return dStr;
    }

    /**
     * <b>获取如N天前</b><br>
     *     例如：今天是2015-05-17，3天前是2015-05-14
     * @param date 日期
     * @param n n天前
     * @return  String，如：2015-05-14
     */
    public static final String beforeNDay(Date date, int n) {
        long time = (date.getTime() / 1000) - 60 * 60 * 24 * n;
        date.setTime(time * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat(DateTool.YMDHMS);
        String dStr = formatter.format(date);
        return dStr;

    }

    /**
     * <b>获取如N天后</b><br>
     *     例如：今天是2015-05-17，3天后是2015-05-20
     * @param date 日期
     * @param n n天后
     * @return  String，如：2015-05-20
     */
    public static final String afterNDay(Date date, int n) {
        long time = (date.getTime() / 1000) + 60 * 60 * 24 * n;
        date.setTime(time * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat(DateTool.YMDHMS);
        String dStr = formatter.format(date);
        return dStr;
    }

    /**
     * 计算是否闰年
     * @param year 格式如："2015"
     * @return  boolean 是否闰年
     */
    public static final boolean isLeapYear(String year){
        int y;
        y = ValueTool.str2Int(year);
        if(y>0) {
            return isLeapYear(y);
        }
        return false;
    }

    /**
     * 计算是否闰年
     * @param year 格式如：2015(整数)
     * @return boolean 是否闰年
     */
    public static final boolean isLeapYear(int year){
        if(year>0) {
            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

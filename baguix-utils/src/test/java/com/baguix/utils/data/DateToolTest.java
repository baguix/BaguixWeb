package com.baguix.utils.data;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateTool Tester.
 *
 * @author Scott
 * @version 1.0
 * @since <pre>八月 21, 2015</pre>
 */
public class DateToolTest {
    private static final Logger logger = LoggerFactory.getLogger(DateToolTest.class);
    private Date date1 ;
    private Date date2 ;
    @Before
    public void before() throws Exception {
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        date1 = formater.parse("2015-02-25 03:45:04 123");
        date2 = formater.parse("2015-12-05 23:45:04 003");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: DateFormat(Date date, String formatStr)
     */
    @Test
    public void testDateFormat() throws Exception {
        // 测试DateTool中的所有格式常量
        assertEquals("2015-02-25 03:45:04", DateTool.dateFormat(date1, DateTool.YMDHMS));
        assertEquals("2015-02-25", DateTool.dateFormat(date1, DateTool.YMD));
        assertEquals("03:45:04", DateTool.dateFormat(date1, DateTool.HMS));
        assertEquals("2015-02-25 03:45:04 123", DateTool.dateFormat(date1, DateTool.YMDHMSS));
        assertEquals("2015-2-25", DateTool.dateFormat(date1, DateTool.SHORT_YMD));
        assertEquals("2015-2-25 3:45:4", DateTool.dateFormat(date1, DateTool.SHORT_YMDHMS));
        assertEquals("2015-2-25 3:45:4 123", DateTool.dateFormat(date1, DateTool.SHORT_YMDHMSS));
        assertEquals("2015年02月25日", DateTool.dateFormat(date1, DateTool.CN_YMD));
        assertEquals("2015年02月25日03时45分04秒", DateTool.dateFormat(date1, DateTool.CN_YMDHMS));
        assertEquals("2015年02月25日03时45分04秒123毫秒", DateTool.dateFormat(date1, DateTool.CN_YMDHMSS));
        assertEquals("2015年2月25日", DateTool.dateFormat(date1, DateTool.CN_SHORT_YMD));
        assertEquals("2015年2月25日3时45分4秒", DateTool.dateFormat(date1, DateTool.CN_SHORT_YMDHMS));
        assertEquals("2015年2月25日3时45分4秒123毫秒", DateTool.dateFormat(date1, DateTool.CN_SHORT_YMDHMSS));

        assertEquals("2015-12-05 23:45:04", DateTool.dateFormat(date2, DateTool.YMDHMS));
        assertEquals("2015-12-05", DateTool.dateFormat(date2, DateTool.YMD));
        assertEquals("23:45:04", DateTool.dateFormat(date2, DateTool.HMS));
        assertEquals("2015-12-05 23:45:04 003", DateTool.dateFormat(date2, DateTool.YMDHMSS));
        assertEquals("2015-12-5", DateTool.dateFormat(date2, DateTool.SHORT_YMD));
        assertEquals("2015-12-5 23:45:4", DateTool.dateFormat(date2, DateTool.SHORT_YMDHMS));
        assertEquals("2015-12-5 23:45:4 3", DateTool.dateFormat(date2, DateTool.SHORT_YMDHMSS));
        assertEquals("2015年12月05日", DateTool.dateFormat(date2, DateTool.CN_YMD));
        assertEquals("2015年12月05日23时45分04秒", DateTool.dateFormat(date2, DateTool.CN_YMDHMS));
        assertEquals("2015年12月05日23时45分04秒003毫秒", DateTool.dateFormat(date2, DateTool.CN_YMDHMSS));
        assertEquals("2015年12月5日", DateTool.dateFormat(date2, DateTool.CN_SHORT_YMD));
        assertEquals("2015年12月5日23时45分4秒", DateTool.dateFormat(date2, DateTool.CN_SHORT_YMDHMS));
        assertEquals("2015年12月5日23时45分4秒3毫秒", DateTool.dateFormat(date2, DateTool.CN_SHORT_YMDHMSS));
    }

    /**
     * Method: getDateTimeStr(Date date)
     */
    @Test
    public void testGetDateTimeStr() throws Exception {
        assertEquals("2015-02-25 03:45:04", DateTool.getDateTimeStr(date1));
        assertEquals("2015-12-05 23:45:04", DateTool.getDateTimeStr(date2));
    }

    /**
     * Method: getDateStr(Date date)
     */
    @Test
    public void testGetDateStr() throws Exception {
        assertEquals("2015-02-25", DateTool.getDateStr(date1));
        assertEquals("2015-12-05", DateTool.getDateStr(date2));
    }

    /**
     * Method: getTimeStr(Date date)
     */
    @Test
    public void testGetTimeStr() throws Exception {
        assertEquals("03:45:04", DateTool.getTimeStr(date1));
        assertEquals("23:45:04", DateTool.getTimeStr(date2));
    }

    /**
     * Method: getCNDateTimeStr(Date date)
     */
    @Test
    public void testGetCNDateTimeStr() throws Exception {
        assertEquals("2015年02月25日03时45分04秒", DateTool.getCNDateTimeStr(date1));
        assertEquals("2015年12月05日23时45分04秒", DateTool.getCNDateTimeStr(date2));
    }

    /**
     * Method: getCNDateStr(Date date)
     */
    @Test
    public void testGetCNDateStr() throws Exception {
        assertEquals("2015年02月25日", DateTool.getCNDateStr(date1));
        assertEquals("2015年12月05日", DateTool.getCNDateStr(date2));
    }

    /**
     * Method: getWeekOfYear(Date date)
     */
    @Test
    public void testGetWeekOfYear() throws Exception {
        assertEquals(9, DateTool.getWeekOfYear(date1));
        assertEquals(49, DateTool.getWeekOfYear(date2));
    }

    /**
     * Method: getWeekOfYearISO(Date date)
     */
    @Test
    public void testGetWeekOfYearISO() throws Exception {
        assertEquals(9, DateTool.getWeekOfYearISO(date1));
        assertEquals(49, DateTool.getWeekOfYearISO(date2));
    }

    /**
     * Method: beforeNYear(Date date, int n)
     */
    @Test
     public void testBeforeNYear() throws Exception {
        assertEquals("2010-02-25 03:45:04", DateTool.beforeNYear(date1,5));
    }

    /**
     * Method: afterNYear(Date date, int n)
     */
    @Test
    public void testAfterNYearNYear() throws Exception {
        assertEquals("2020-02-25 03:45:04", DateTool.afterNYear(date1,5));
    }

    /**
     * Method: beforeNDay(Date date, int n)
     */
    @Test
    public void testBeforeNDay() throws Exception {
        assertEquals("2015-02-20 03:45:04", DateTool.beforeNDay(date1, 5));
    }

    /**
     * Method: afterNDay(Date date, int n)
     */
    @Test
    public void testAfterNDay() throws Exception {
        assertEquals("2015-03-02 03:45:04", DateTool.afterNDay(date1, 5));
    }

    /**
     * Method: isLeapYear(String year)
     */
    @Test
    public void testIsLeapYearString() throws Exception {
        assertTrue(DateTool.isLeapYear("2000"));
        assertTrue(DateTool.isLeapYear("2004"));
        assertTrue(DateTool.isLeapYear("2008"));
        assertFalse(DateTool.isLeapYear("1900"));
    }

    /**
     * Method: isLeapYear(int year)
     */
    @Test
    public void testIsLeapYearInt() throws Exception {
        assertTrue(DateTool.isLeapYear(2000));
        assertTrue(DateTool.isLeapYear(2004));
        assertTrue(DateTool.isLeapYear(2008));
        assertFalse(DateTool.isLeapYear(1900));
    }
}

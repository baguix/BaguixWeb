package com.baguix.utils.data;

import com.baguix.utils.doc.HtmlTool;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * RegexTool 测试.
 *
 * @author Scott
 */
public class RegexToolTest {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(RegexToolTest.class);

    @Test
    public void testInteger() throws Exception {
        // 整型
        assertTrue(RegexTool.isMatch("1", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("1234567890", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("+1234567890", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("0000", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("-1234567890", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("-1", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("+1", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("0", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("+0", RegexTool.INTEGER));
        assertTrue(RegexTool.isMatch("-0", RegexTool.INTEGER));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("1.0", RegexTool.INTEGER));
        assertFalse(RegexTool.isMatch("+1.0", RegexTool.INTEGER));
        assertFalse(RegexTool.isMatch("-1.0", RegexTool.INTEGER));
        assertFalse(RegexTool.isMatch("", RegexTool.INTEGER));
    }

    @Test
    public void testIntegerN() throws Exception {
        // 正整型
        assertTrue(RegexTool.isMatch("1", RegexTool.INTEGER_NEGATIVE));
        assertTrue(RegexTool.isMatch("+1", RegexTool.INTEGER_NEGATIVE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("-1", RegexTool.INTEGER_NEGATIVE));
        assertFalse(RegexTool.isMatch("-1.0", RegexTool.INTEGER_NEGATIVE));
        assertFalse(RegexTool.isMatch("0", RegexTool.INTEGER_NEGATIVE));
        assertFalse(RegexTool.isMatch("+0", RegexTool.INTEGER_NEGATIVE));
        assertFalse(RegexTool.isMatch("", RegexTool.INTEGER_NEGATIVE));
    }

    @Test
    public void testIntegerP() throws Exception {
        // 负整型
        assertTrue(RegexTool.isMatch("-1", RegexTool.INTEGER_POSITIVE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("2", RegexTool.INTEGER_POSITIVE));
        assertFalse(RegexTool.isMatch("+10", RegexTool.INTEGER_POSITIVE));
        assertFalse(RegexTool.isMatch("-1.0", RegexTool.INTEGER_POSITIVE));
        assertFalse(RegexTool.isMatch("+1.0", RegexTool.INTEGER_POSITIVE));
        assertFalse(RegexTool.isMatch("0", RegexTool.INTEGER_POSITIVE));
        assertFalse(RegexTool.isMatch("+0", RegexTool.INTEGER_POSITIVE));
        assertFalse(RegexTool.isMatch("", RegexTool.INTEGER_POSITIVE));
    }

    @Test
    public void testDouble() throws Exception {
        // 实型
        assertTrue(RegexTool.isMatch("1.23", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("+1.23", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("-1.23", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("123.0", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("-123.0", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("+123.0", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("0.123", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("+0.123", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("-0.123", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("0.0", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("-0.0", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("+0.0", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("-123.", RegexTool.REAL));
        assertTrue(RegexTool.isMatch(".123", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("1,000.23", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("+1,000.23", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("-1,000.23", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("1,000.00", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("+1,000.00", RegexTool.REAL));
        assertTrue(RegexTool.isMatch("-1,000.00", RegexTool.REAL));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("0", RegexTool.REAL));
        assertFalse(RegexTool.isMatch("123", RegexTool.REAL));
        assertFalse(RegexTool.isMatch("+123", RegexTool.REAL));
        assertFalse(RegexTool.isMatch("-123", RegexTool.REAL));
        assertFalse(RegexTool.isMatch("abcd中文", RegexTool.REAL));
        assertFalse(RegexTool.isMatch("", RegexTool.REAL));
    }

    @Test
    public void testDoubleN() {
        //  正实型
        assertTrue(RegexTool.isMatch("1.23", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("+1.23", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("123.0", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("+123.0", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("0.123", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("+0.123", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch(".123", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("+.123", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("1,000,000.23", RegexTool.REAL_NEGATIVE));
        assertTrue(RegexTool.isMatch("+1,000.23", RegexTool.REAL_NEGATIVE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("-1.23", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("-123.0", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("-0.123", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("0.0", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("-0.0", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("+0.0", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("-123.", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("0", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("123", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("+123", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("-123", RegexTool.REAL_NEGATIVE));
        assertFalse(RegexTool.isMatch("", RegexTool.REAL_NEGATIVE));
    }

    @Test
    public void testDoubleP() {
        //  负实型
        assertTrue(RegexTool.isMatch("-1.23", RegexTool.REAL_POSITIVE));
        assertTrue(RegexTool.isMatch("-123.0", RegexTool.REAL_POSITIVE));
        assertTrue(RegexTool.isMatch("-0.123", RegexTool.REAL_POSITIVE));
        assertTrue(RegexTool.isMatch("-123.", RegexTool.REAL_POSITIVE));
        assertTrue(RegexTool.isMatch("-.123", RegexTool.REAL_POSITIVE));
        assertTrue(RegexTool.isMatch("-1,000.123", RegexTool.REAL_POSITIVE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("1.23", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("+1.23", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("123.0", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("+123.0", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("0.123", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("+0.123", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch(".123", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("0.0", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("-0.0", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("+0.0", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("0", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("123", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("+123", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("-123", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("-1,000", RegexTool.REAL_POSITIVE));
        assertFalse(RegexTool.isMatch("", RegexTool.REAL_POSITIVE));
    }

    @Test
    public void testMoney() {
        //  金钱类型
        assertTrue(RegexTool.isMatch("1000", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("1,000", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("1000.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("1,000.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("1,000.1", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("1,000.10", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("0.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("0.01", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("0.10", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("0.1", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("1234567.89", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("1,234,567.89", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1000", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1,000", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1000.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1,000.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1,000.1", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1,000.10", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+0.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+0.01", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+0.10", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+0.1", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1234567.89", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("+1,234,567.89", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1000", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1,000", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1000.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1,000.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1,000.1", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1,000.10", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-0.00", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-0.01", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-0.10", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-0.1", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1234567.89", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("-1,234,567.89", RegexTool.MONEY));
        assertTrue(RegexTool.isMatch("0,234,567.89", RegexTool.MONEY));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("07.899", RegexTool.MONEY));
        assertFalse(RegexTool.isMatch("1,234,567.899", RegexTool.MONEY));
        assertFalse(RegexTool.isMatch("", RegexTool.MONEY));
        assertFalse(RegexTool.isMatch("abcd", RegexTool.MONEY));
    }
    @Test
    public void testDate(){
        assertTrue(RegexTool.isMatch("2013-05-06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013-5-6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013-5-06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013-05-6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013-05-06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013/05/06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013/5/6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013/5/06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013/05/6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013_05_06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013_5_6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013_5_06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013_05_6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013.05.06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013.5.6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013.5.06", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013.05.6", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("20130506", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("201356", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013506", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2013056", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("5000-01-01", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2000-2-29", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2004-2-29", RegexTool.DATE));
        assertTrue(RegexTool.isMatch("2400-2-29", RegexTool.DATE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("13-5-6", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("13/5/6", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("13_5_6", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("13.5.6", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("abcd", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("abcd-aa-bb", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("1799-12-31", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("2013-12-32", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("2013-13-2", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("2013-2-29", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("1900-2-29", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("2100-2-29", RegexTool.DATE));
        assertFalse(RegexTool.isMatch("2013-2-30", RegexTool.DATE));
    }

    @Test
    public void testMonth(){
        assertTrue(RegexTool.isMatch("1", RegexTool.MONTH));
        assertTrue(RegexTool.isMatch("01", RegexTool.MONTH));
        assertTrue(RegexTool.isMatch("10", RegexTool.MONTH));
        assertTrue(RegexTool.isMatch("11", RegexTool.MONTH));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("0", RegexTool.MONTH));
        assertFalse(RegexTool.isMatch("13", RegexTool.MONTH));
        assertFalse(RegexTool.isMatch("30", RegexTool.MONTH));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.MONTH));
        assertFalse(RegexTool.isMatch("2013-2-30", RegexTool.MONTH));
        assertFalse(RegexTool.isMatch("a", RegexTool.DAY));
    }

    @Test
    public void testDAY(){
        assertTrue(RegexTool.isMatch("1", RegexTool.DAY));
        assertTrue(RegexTool.isMatch("01", RegexTool.DAY));
        assertTrue(RegexTool.isMatch("10", RegexTool.DAY));
        assertTrue(RegexTool.isMatch("11", RegexTool.DAY));
        assertTrue(RegexTool.isMatch("29", RegexTool.DAY));
        assertTrue(RegexTool.isMatch("30", RegexTool.DAY));
        assertTrue(RegexTool.isMatch("31", RegexTool.DAY));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("0", RegexTool.DAY));
        assertFalse(RegexTool.isMatch("32", RegexTool.DAY));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.DAY));
        assertFalse(RegexTool.isMatch("2013-2-30", RegexTool.DAY));
        assertFalse(RegexTool.isMatch("a", RegexTool.DAY));
    }

    @Test
    public void testAGE(){
        assertTrue(RegexTool.isMatch("10", RegexTool.AGE));
        assertTrue(RegexTool.isMatch("11", RegexTool.AGE));
        assertTrue(RegexTool.isMatch("100", RegexTool.AGE));
        assertTrue(RegexTool.isMatch("123", RegexTool.AGE));
        assertTrue(RegexTool.isMatch("145", RegexTool.AGE));
        assertTrue(RegexTool.isMatch("150", RegexTool.AGE));
        assertTrue(RegexTool.isMatch("1", RegexTool.AGE));
        assertTrue(RegexTool.isMatch("01", RegexTool.AGE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("0", RegexTool.AGE));
        assertFalse(RegexTool.isMatch("151", RegexTool.AGE));
        assertFalse(RegexTool.isMatch("13.1", RegexTool.AGE));
        assertFalse(RegexTool.isMatch("a", RegexTool.AGE));
        assertFalse(RegexTool.isMatch("1,0", RegexTool.AGE));
    }

    @Test
    public void testZipcode(){
        assertTrue(RegexTool.isMatch("530209", RegexTool.ZIPCODE));
        assertTrue(RegexTool.isMatch("530001", RegexTool.ZIPCODE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("0", RegexTool.ZIPCODE));
        assertFalse(RegexTool.isMatch("000000", RegexTool.ZIPCODE));
        assertFalse(RegexTool.isMatch("abcdef", RegexTool.ZIPCODE));
        assertFalse(RegexTool.isMatch("a", RegexTool.ZIPCODE));
    }

    @Test
    public void testEmail(){
        assertTrue(RegexTool.isMatch("abc@qq.com", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a123@qq.com", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a_1@qq.com", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("abc@126.com", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a123@126.com", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a_1@126.com", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("abc@126.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a123@126.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a_1@126.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("abc@126.org", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a123@126.org", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a_1@126.org", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("abc@126.gov.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a123@126.gov.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a_1@126.gov.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("abc@mail.gov.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a123@mail.gov.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("a_1@mail.gov.cn", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("ABC@MAIL.GOV.CN", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("A123@MAIL.GOV.CN", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("A_1@MAIL.GOV.CN", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("855532378@qq.com", RegexTool.EMAIL));
        assertTrue(RegexTool.isMatch("13897586582@189.com", RegexTool.EMAIL));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("1*a@qq.com", RegexTool.EMAIL));
        assertFalse(RegexTool.isMatch("a@abc*.123", RegexTool.EMAIL));
        assertFalse(RegexTool.isMatch("a@abc*.*", RegexTool.EMAIL));
        assertFalse(RegexTool.isMatch("a", RegexTool.EMAIL));
        assertFalse(RegexTool.isMatch("www.baidu.com", RegexTool.EMAIL));
    }

    @Test
    public  void testPhone(){
        assertTrue(RegexTool.isMatch("110", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("114", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("119", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("10000", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("10086", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("1008611", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("12593", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("12315", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("96106", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("2754548", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("12547852", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("215457852", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("4008869888", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("400-670-6666", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("8009995558", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("800-999-5558", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("010-68301671", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("0771-2756178", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("0771-2756178-605", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("021-63355533", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("021-63355533-344", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("0571-85172244", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("025-83593186", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("0551-3601000", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("0954-3927889", RegexTool.PHONE));
        assertTrue(RegexTool.isMatch("18609549666", RegexTool.PHONE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("AF.com", RegexTool.PHONE));
        assertFalse(RegexTool.isMatch("a", RegexTool.PHONE));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.PHONE));
    }

    @Test
    public void testMobile(){
        assertTrue(RegexTool.isMatch("13013559999", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13176970000", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13369989277", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13405393597", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13576970000", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13669989277", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13705393597", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13805495757", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("13969989277", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("14269714111", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("14769714111", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("15020940999", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("15220940999", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("15520940999", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("15762084234", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("18325495456", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("18754962000", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("8618909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("08618909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("008618909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("+8618909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("+08618909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("+008618909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("(86)18909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("(086)18909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("(0086)18909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("(+86)18909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("(+086)18909549666", RegexTool.MOBILE));
        assertTrue(RegexTool.isMatch("(+0086)18909549666", RegexTool.MOBILE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("157620842341", RegexTool.MOBILE));
        assertFalse(RegexTool.isMatch("AF.com", RegexTool.MOBILE));
        assertFalse(RegexTool.isMatch("a", RegexTool.PHONE));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.PHONE));
    }

    @Test
    public void testIPAddress(){
        assertTrue(RegexTool.isMatch("127.0.0.1", RegexTool.IPADDRESS));
        assertTrue(RegexTool.isMatch("10.0.0.1", RegexTool.IPADDRESS));
        assertTrue(RegexTool.isMatch("172.16.0.1", RegexTool.IPADDRESS));
        assertTrue(RegexTool.isMatch("192.168.0.1", RegexTool.IPADDRESS));
        assertTrue(RegexTool.isMatch("192.168.000.001", RegexTool.IPADDRESS));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("192.168.256.0", RegexTool.IPADDRESS));
        assertFalse(RegexTool.isMatch("192,168,256,0", RegexTool.IPADDRESS));
        assertFalse(RegexTool.isMatch("192.-10.20.0", RegexTool.IPADDRESS));
        assertFalse(RegexTool.isMatch("123", RegexTool.IPADDRESS));
        assertFalse(RegexTool.isMatch("AF.com", RegexTool.IPADDRESS));
        assertFalse(RegexTool.isMatch("a", RegexTool.IPADDRESS));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.IPADDRESS));
    }

    @Test
    public void testIdentifier(){
        assertTrue(RegexTool.isMatch("a123", RegexTool.IDENTIFIER));
        assertTrue(RegexTool.isMatch("a_123", RegexTool.IDENTIFIER));
        assertTrue(RegexTool.isMatch("_123", RegexTool.IDENTIFIER));
        assertTrue(RegexTool.isMatch("_abc", RegexTool.IDENTIFIER));
        assertTrue(RegexTool.isMatch("abc", RegexTool.IDENTIFIER));
        assertTrue(RegexTool.isMatch("a1b2c3", RegexTool.IDENTIFIER));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("123abc", RegexTool.IDENTIFIER));
        assertFalse(RegexTool.isMatch("a*", RegexTool.IDENTIFIER));
        assertFalse(RegexTool.isMatch("中文", RegexTool.IDENTIFIER));
        assertFalse(RegexTool.isMatch("1.0", RegexTool.IDENTIFIER));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.IDENTIFIER));
    }

    @Test
    public void testUserID(){
        assertTrue(RegexTool.isMatch("a123", RegexTool.USERID));
        assertTrue(RegexTool.isMatch("abc", RegexTool.USERID));
        assertTrue(RegexTool.isMatch("a1b2c3", RegexTool.USERID));
        assertTrue(RegexTool.isMatch("a_123", RegexTool.USERID));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("a1234567890123456789012345", RegexTool.USERID));
        assertFalse(RegexTool.isMatch("_123", RegexTool.USERID));
        assertFalse(RegexTool.isMatch("_abc", RegexTool.USERID));
        assertFalse(RegexTool.isMatch("123abc", RegexTool.USERID));
        assertFalse(RegexTool.isMatch("a*", RegexTool.USERID));
        assertFalse(RegexTool.isMatch("中文", RegexTool.USERID));
        assertFalse(RegexTool.isMatch("1.0", RegexTool.USERID));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.USERID));
    }

    @Test
    public void testUserName(){
        assertTrue(RegexTool.isMatch("张绍隆", RegexTool.USERNAME));
        assertTrue(RegexTool.isMatch("山口一郎", RegexTool.USERNAME));
        assertTrue(RegexTool.isMatch("斯琴格日乐艾买提", RegexTool.USERNAME));
        assertTrue(RegexTool.isMatch("Scott Gates", RegexTool.USERNAME));
        assertTrue(RegexTool.isMatch("jobs.jone", RegexTool.USERNAME));
        assertTrue(RegexTool.isMatch("s bill spring", RegexTool.USERNAME));
        assertTrue(RegexTool.isMatch("Scott", RegexTool.USERNAME));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("Scott.", RegexTool.USERNAME));
        assertFalse(RegexTool.isMatch("张C", RegexTool.USERNAME));
        assertFalse(RegexTool.isMatch("Scott ", RegexTool.USERNAME));
        assertFalse(RegexTool.isMatch("*a", RegexTool.USERNAME));
        assertFalse(RegexTool.isMatch("1.0", RegexTool.USERNAME));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.USERNAME));
    }

    @Test
    public void testChinese(){
        assertTrue(RegexTool.isMatch("中文", RegexTool.CHINESE));
        assertTrue(RegexTool.isMatch("龜楍", RegexTool.CHINESE));
        assertTrue(RegexTool.isMatch("丿", RegexTool.CHINESE));
        assertTrue(RegexTool.isMatch("ㄠ", RegexTool.CHINESE));
        assertTrue(RegexTool.isMatch("￥", RegexTool.CHINESE));
        assertTrue(RegexTool.isMatch("ㄠ", RegexTool.CHINESE));
        assertTrue(RegexTool.isMatch("龦", RegexTool.CHINESE));
        assertTrue(RegexTool.isMatch("龧", RegexTool.CHINESE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("Scott", RegexTool.CHINESE));
        assertFalse(RegexTool.isMatch("中文s", RegexTool.CHINESE));
        assertFalse(RegexTool.isMatch("*a", RegexTool.CHINESE));
        assertFalse(RegexTool.isMatch("1.0", RegexTool.CHINESE));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.CHINESE));
        //uFFE6
        assertFalse(RegexTool.isMatch("￦", RegexTool.CHINESE));
    }

    @Test
    public void testChineseNormal(){
        assertTrue(RegexTool.isMatch("中文", RegexTool.CHINESE_NORMAL));
        assertTrue(RegexTool.isMatch("龜楍龥", RegexTool.CHINESE_NORMAL));
        assertTrue(RegexTool.isMatch("丿", RegexTool.CHINESE_NORMAL));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("Scott", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("￦", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("中文s", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("*a", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("1.0", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("ㄠ", RegexTool.CHINESE_NORMAL));
        //uFFE5
        assertFalse(RegexTool.isMatch("￥", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("龦", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("龧", RegexTool.CHINESE_NORMAL));
        assertFalse(RegexTool.isMatch("龨", RegexTool.CHINESE_NORMAL));
    }

    @Test
    public void testUTF8 () {
        String strUtf8 = "\\u0022";
        String strChinese;
        try {
            strChinese = new String(strUtf8.getBytes("UTF-8"),  "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            strChinese = "decode error";
        }
        logger.info("string={}", strChinese);
    }

    @Test
    public void testASCIIChar(){
        assertTrue(RegexTool.isMatch("abc,dde", RegexTool.ASCII_CHAR));
        assertTrue(RegexTool.isMatch("abc or 1+1=0", RegexTool.ASCII_CHAR));
        assertTrue(RegexTool.isMatch("abc or 1!=0", RegexTool.ASCII_CHAR));
        assertTrue(RegexTool.isMatch("\"", RegexTool.ASCII_CHAR));
        assertTrue(RegexTool.isMatch("*a", RegexTool.ASCII_CHAR));
        assertTrue(RegexTool.isMatch("1.0", RegexTool.ASCII_CHAR));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("abc", RegexTool.ASCII_CHAR));
        assertFalse(RegexTool.isMatch("ABC", RegexTool.ASCII_CHAR));
        assertFalse(RegexTool.isMatch("123", RegexTool.ASCII_CHAR));
        assertFalse(RegexTool.isMatch("abc123", RegexTool.ASCII_CHAR));
        assertFalse(RegexTool.isMatch("ABC123", RegexTool.ASCII_CHAR));
        assertFalse(RegexTool.isMatch("abcABC", RegexTool.ASCII_CHAR));
        assertFalse(RegexTool.isMatch("abcABC123", RegexTool.ASCII_CHAR));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.ASCII_CHAR));
    }

    @Test
    public void testSecurityString(){
        assertTrue(RegexTool.isMatch("abc", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("ABC", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("123", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("abc123", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("ABC123", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("abcABC", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("abcABC123", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("abc“ABC”123", RegexTool.SECURITY_STRING));
        assertTrue(RegexTool.isMatch("abc“中文”123 H&M 100% 32℃", RegexTool.SECURITY_STRING));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("abc,dde", RegexTool.SECURITY_STRING));
        assertFalse(RegexTool.isMatch("abc or 1+1=0", RegexTool.SECURITY_STRING));
        assertFalse(RegexTool.isMatch("abc or 1!=0", RegexTool.SECURITY_STRING));
        assertFalse(RegexTool.isMatch("\"", RegexTool.SECURITY_STRING));
    }

    @Test
    public void testURL(){
        assertTrue(RegexTool.isMatch("http://www.baidu.com", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.a-b.com", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.126.com", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.baidu.com.cn", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://spring.cn", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.126.org.hk", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080", RegexTool.URL));
        assertTrue(RegexTool.isMatch("https://www.gx-gx.gov.cn", RegexTool.URL));
        assertTrue(RegexTool.isMatch("ftp://www.gx-gx.gov.cn", RegexTool.URL));
        assertTrue(RegexTool.isMatch("www.baidu.com", RegexTool.URL));
        assertTrue(RegexTool.isMatch("www.126.com", RegexTool.URL));
        assertTrue(RegexTool.isMatch("www.a-b.com", RegexTool.URL));
        assertTrue(RegexTool.isMatch("spring.io", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080/abc/a.do", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080/abc/a.do?ds=123&aa=3abcd", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080/abc/a.do?ds=123&aa='3abcd'", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080/abc/a.do?ds=123&aa='3%20abcd'", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080/abc/?s=123", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080/?a=123", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.gx-gx.gov.cn:8080?a=123", RegexTool.URL)); // 这可能是一个bug
        assertTrue(RegexTool.isMatch("http://www.baidu.com:8080//a.html", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://www.baidu.com:8080/s?key=%23%ef", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://map.baidu.com/?panoid=09007200001410141530244486H&panotype=street&heading=355.95&pitch=0&l=17&tn=B_NORMAL_MAP&sc=0&newmap=1&shareurl=1&pid=09007200001410141530244486H", RegexTool.URL));
        assertTrue(RegexTool.isMatch("https://paimai.taobao.com/pmp_album/30272896.htm?spm=a2166.1607598.1998371552.27.8eTDWW", RegexTool.URL));
        assertTrue(RegexTool.isMatch("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=02049043_14_pg&wd=%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%20%5Cw&oq=java%20%E6%8B%92%E7%BB%9D%E8%AE%BF%E9%97%AE&rsv_pq=f92cf67f000354c4&rsv_t=8018Sc5lOx4UyBBPpd1SFUqg2L5jeXgN4lwHgyLTjk9WpIszwcKzMY7QQq5MTNndjWvNEbM&rsv_enter=1&inputT=6413&rsv_sug3=47&rsv_sug1=36&rsv_sug2=0&rsv_sug4=6412", RegexTool.URL));
        assertTrue(RegexTool.isMatch("http://map.baidu.com/?panoid='09007200001410141530244486H'&panotype='street'&heading=355.95&pitch=0&l=17&tn=B_NORMAL_MAP&sc=0&newmap=1&shareurl=1&pid=09007200001410141530244486H", RegexTool.URL));
        assertTrue(RegexTool.isMatch("https://paimai.taobao.com/pmp_album/30272896.htm?spm='a2166.1607598.1998371552.27.8eTDWW'", RegexTool.URL));
        assertTrue(RegexTool.isMatch("https://www.baidu.com/s?ie='utf-8'&f=8&rsv_bp=1&rsv_idx=2&tn=02049043_14_pg&wd='%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%20%5Cw&oq=java%20%E6%8B%92%E7%BB%9D%E8%AE%BF%E9%97%AE'&rsv_pq=f92cf67f000354c4&rsv_t=8018Sc5lOx4UyBBPpd1SFUqg2L5jeXgN4lwHgyLTjk9WpIszwcKzMY7QQq5MTNndjWvNEbM&rsv_enter=1&inputT=6413&rsv_sug3=47&rsv_sug1=36&rsv_sug2=0&rsv_sug4=6412", RegexTool.URL));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("abc,dde", RegexTool.URL));
        assertFalse(RegexTool.isMatch("www", RegexTool.URL));
        assertFalse(RegexTool.isMatch("www=ddd", RegexTool.URL));
        assertFalse(RegexTool.isMatch("http://www.baidu.com/s.do?'a'=123", RegexTool.URL));
        assertFalse(RegexTool.isMatch("http://www/123", RegexTool.URL));
    }
    @Test
    public void testIdCard(){
        assertTrue(RegexTool.isMatch("511702198101111956", RegexTool.IDCARD));
        assertTrue(RegexTool.isMatch("370283790911703", RegexTool.IDCARD));
        assertTrue(RegexTool.isMatch("11000019790225207X", RegexTool.IDCARD));
        assertTrue(RegexTool.isMatch("34052419800101001X", RegexTool.IDCARD));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("abc", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("ABC", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("123", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("5117021981011119561", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("51170219810111195", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("abc123", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("ABC123", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("abcABC", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("abcABC123", RegexTool.IDCARD));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.IDCARD));
    }

    @Test
    public void testLicencePlate(){
        assertTrue(RegexTool.isMatch("鄂C11111", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("黑B1084B", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("川XBU297", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("冀R911X0", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("粤PCP278", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("广KCP278", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("空JCP278", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("警KCP278", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("广K-CP278", RegexTool.LICENCE_PLATE));
        assertTrue(RegexTool.isMatch("广K CP278", RegexTool.LICENCE_PLATE));
        // ====================================================================================================
        assertFalse(RegexTool.isMatch("abc", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("ABC", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("123", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("田B1084B", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("鄂C1111", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("鄂C111111", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("abc123", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("ABC123", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("abcABC", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("abcABC123", RegexTool.LICENCE_PLATE));
        assertFalse(RegexTool.isMatch("0x49", RegexTool.LICENCE_PLATE));
    }
    @Test
    public void testIsMatch() throws Exception {
        assertTrue(RegexTool.isMatch("123","\\d*"));
        assertTrue(RegexTool.isMatch("123abc_","\\w*"));
        assertTrue(RegexTool.isMatch(" abc","\\s[a-z]*"));
        assertTrue(RegexTool.isMatch(".","\\."));
        assertTrue(RegexTool.isMatch("aaa","a{3}"));
    }

    @Ignore
    public void testFilter() throws Exception {
        String HTML = "<a>简单测试</a><div class=\"s_form_wrapper\">复杂测试</div><input type='text' value='11' />";
        String CSS = "< style > #head>*{display:none;}\n" +
                "#head .s-isindex-wrap{display:none;}\n" +
                "#nv{display: none !important;}\n" +
                "body #s_tab,body #wrapper_wrapper,body #u,body #result_logo{display:block;}\n" +
                "body #wrapper,body #head,#head #head_wrapper,body #s_fm,body #s_form_wrapper,body #form,body #wrapper_wrapper,body #u,body #s_tab,body #result_logo img,#u div,#u div a,#head .bdsug ul{display: block;}\n" +
                "body #result_logo,#u *,#head .bdsug ul li b{display: inline;}\n" +
                "body #s_tab *,body #s_kw_wrap,body #kw,body #su,body #s_btn_wr,body #about_is,#u .c-icon{display: inline-block;}\n" +
                "#head .bdsug ul li{display: list-item;}\n" +
                "#head .head_wrapper{padding-top: 0px !important;}\n" +
                ".s-bottom-ctner{display:none !important;}" +
                "</style >";
        String JAVASCRIPT = "<script type=\"text/javascript\"> \n" +
                "    var sampling = Math.random() < 0.001;\n" +
                "    var page_begintime = (+new Date());\n" +
                "    (sampling) && ((new Image()).src = \"http://isdspeed.qq.com/cgi-bin/r.cgi?flag1=7839&flag2=7&flag3=8&15=1000&r=\" + Math.random());\n" +
                "\n" +
                "    var biz = \"MzAwODMzMzA5Ng==\";\n" +
                "    var sn = \"014868e2520ef53e22968acaffc05dc0\" || \"\";\n" +
                "    var mid = \"221766444\" || \"\";\n" +
                "    var idx = \"1\" || \"\" ;\n" +
                "</script>";
        String DOC = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\" />\n" +
                "<link rel=\"dns-prefetch\" href=\"http://mimg.127.net\">\n" +
                "<link rel=\"dns-prefetch\" href=\"https://mail.126.com\">\n" +
                "<link rel=\"dns-prefetch\" href=\"http://iplocator.mail.163.com\">\n" +
                "<meta name=\"description\" content=\"网易126免费邮箱--你的专业电子邮局。14年邮箱运营经验，系统快速稳定，垃圾邮件拦截率超过98%，邮箱容量自动翻倍，支持高达3G超大附件，提供免费网盘及手机号码邮箱服务。\">\n" +
                "<meta name=\"keywords\" content=\"邮件，邮箱，电子邮件，电子邮箱，网易邮箱，126邮箱，免费邮箱，mail，email，网盘\">\n" +
                "<title>126网易免费邮--你的专业电子邮局</title>\n" +
                "<link rel=\"shortcut icon\" href=\"http://www.126.com/favicon.ico\" />\n" +
                "<style type=\"text/css\">\n" +
                "/* css reset */\n" +
                "body{color:#000;background:#fff;font-size:12px;line-height:166.6%;text-align:center;}\n" +
                "body.move{-webkit-transition:padding 0.3s ease;-moz-transition:padding 0.3s ease;-o-transition:padding 0.3s ease;-ms-transition:padding 0.3s ease;transition:padding 0.3s ease;}\n" +
                "/* 主题控制栏 */\n" +
                ".themeCtrl{position:absolute;right:50%;bottom:12px;margin-right:-405px;text-align:right;}\n" +
                ".themeCtrl a{float:left;display:inline;}\n" +
                "</ style>\n" +
                "\n" +
                "<script id=\"jsOption\" type=\"text/javascript\">\n" +
                "//当前域名配置\n" +
                "var gOption = {\n" +
                "\t\"sDomain\" : \"126.com\",\n" +
                "\t\"sCookieDomain\" : \"126.com\",\n" +
                "\t\"sAutoLoginUrl\" : \"http://mail.126.com/entry/cgi/ntesdoor?hid=10010102&lightweight=1&verifycookie=1&language=-1&from=web&df=webmail126\",\n" +
                "\t\"sSslAction\" : \"https://mail.126.com/entry/cgi/ntesdoor?\",\n" +
                "\t\"product\" : \"mail126\",\n" +
                "\t\"url\" : \"http://mail.126.com/entry/cgi/ntesdoor?\",\n" +
                "\t\"url2\" : \"http://mail.126.com/errorpage/error126.htm\",\n" +
                "\t\"gad\" : \"126.com\"\n" +
                "}\n" +
                "document.domain = '126.com';\n" +
                "</script>\n" +
                "<script id=\"jsBase\" type=\"text/javascript\" src=\"http://mimg.127.net/index/lib/scripts/base_v5.min.js\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<!--登录提示弹框-->\n" +
                "<div class=\"enhttp\" style=\"display:none\" id=\"enhttpblock\">\n" +
                "\t<div class=\"enhttpbox\">\n" +
                "\t\t<div class=\"topborder\"></div>\n" +
                "\t\t<div class=\"ct\">\n" +
                "\t\t\t<div class=\"inner\">\n" +
                "\t\t\t\t<p class=\"txt-tips\">登录过程有点慢哦，可能是由于网络问题造成的。</p>\n" +
                "\t\t\t\t<p id=\"enhttpTips\" class=\"txt-normal\"><span id=\"backwards\">3</span>&nbsp;秒后自动尝试普通加密方式登录</p>\n" +
                "\t\t\t\t<p class=\"txt-normal\"><a id=\"idLoginBtn\" class=\"httplogin\" href=\"javascript:void(0)\">确&nbsp;&nbsp;定</a></p>\n" +
                "\t\t\t\t<p class=\"txt-line\"></p>\n" +
                "\t\t\t\t<p class=\"txt-advice\">无法登录邮箱？<a class=\"txt-advicelink\" href=\"http://help.mail.163.com/feedback.do?m=add&categoryName=%e7%99%bb%e5%bd%95\" target=\"_blank\">意见反馈&gt;&gt;</a></p>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"bottomborder\"></div>\n" +
                "\t</div>\n" +
                "\t<iframe class=\"httploginframe\" src=\"about:blank\" id=\"frameforlogin\" name=\"frameforlogin\" style=\"overflow:hidden;border:0\">登录iframe</iframe>\n" +
                "</div>\n" +
                "<!--反垃圾-->\n" +
                "<a href=\"http://uinfo.mail.163.com/cgi-bin/hseed/two.pl\"></a>\n" +
                "</body>\n" +
                "</html>";

        String str = RegexTool.filter(CSS.toLowerCase(), RegexTool.CSS);
        logger.info("css={}", str);
        str = RegexTool.filter(JAVASCRIPT.toLowerCase(), RegexTool.JS);
        logger.info("js={}", str);
        str = RegexTool.filter(DOC.toLowerCase(), RegexTool.HTML);
        logger.info("doc={}", str);
        str = RegexTool.filter(CSS.toLowerCase(), RegexTool.CSS_TAG);
        logger.info("css_tag={}", str);
        str = RegexTool.filter(JAVASCRIPT.toLowerCase(), RegexTool.JS_TAG);
        logger.info("js_tag={}", str);
        str = RegexTool.filter(HTML.toLowerCase(), RegexTool.HTML_TAG);
        logger.info("html_tag={}", str);
        String prefix = "前面的内容---->";
        String subfix = "<----后面的内容";
        HtmlTool ht = new HtmlTool();
        String web = ht.getHTML("http://www.baidu.com", 300000);
        web = prefix + RegexTool.filter(web.toLowerCase(), RegexTool.HTML) + subfix;
        logger.info("百度:{}", web);
        web = ht.getHTML("http://www.taobao.com", 300000);
        web = prefix + RegexTool.filter(web.toLowerCase(), RegexTool.HTML) + subfix;
        logger.info("淘宝:{}", web);
        web = ht.getHTML("http://www.sina.com", 300000);
        web = prefix + RegexTool.filter(web.toLowerCase(), RegexTool.HTML) + subfix;
        logger.info("新浪:{}", web);
    }
} 

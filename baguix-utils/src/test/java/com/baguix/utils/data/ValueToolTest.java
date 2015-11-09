package com.baguix.utils.data;

import org.apache.commons.lang3.StringUtils;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/** 
* ValueTool Tester. 
* 
* @author Scott(Su Guang) 
* @version 1.0 
*/

public class ValueToolTest {

    private static final Logger logger = LoggerFactory.getLogger(ValueToolTest.class);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public void test(){
        StringUtils.isEmpty("");
    }

    /**
     * Method: obj2String(Object obj)
     */
    @Test
    public void testObj2String() throws Exception {
        Assert.assertEquals(ValueTool.obj2String("中abc123@？＋"), "中abc123@？＋");
        Assert.assertEquals(ValueTool.obj2String(null), "");
    }

    /**
     * Method: nullObj2String(Object obj, String s)
     */
    @Test
    public void testNullObj2String() throws Exception {
        Assert.assertEquals(ValueTool.nullObj2String("中abc123@？＋", "oo"), "中abc123@？＋");
        Assert.assertEquals(ValueTool.nullObj2String(null, "oo"), "oo");
    }

    /**
     * Method: obj2int(Object obj)
     */
    @Test
    public void testObj2int() throws Exception {
        Assert.assertEquals(ValueTool.obj2int("123"), 123);
        Assert.assertEquals(ValueTool.obj2int("1234567890"), 1234567890);
        Assert.assertEquals(ValueTool.obj2int("12345678901"), 0);
        Assert.assertEquals(ValueTool.obj2int("0000"), 0);
        Assert.assertEquals(ValueTool.obj2int("0001"), 1);
        Assert.assertEquals(ValueTool.obj2int("123.00"), 0);
        Assert.assertEquals(ValueTool.obj2int("+1"), 1);
        Assert.assertEquals(ValueTool.obj2int("-1"), -1);
        Assert.assertEquals(ValueTool.obj2int("1,000"), 1000);
        Assert.assertEquals(ValueTool.obj2int("+1,000"), 1000);
        Assert.assertEquals(ValueTool.obj2int("-1,000"), -1000);
        Assert.assertEquals(ValueTool.obj2int("abcd"), 0);
        Assert.assertEquals(ValueTool.obj2int("1.11"), 0);
        Assert.assertEquals(ValueTool.obj2int(null), 0);
    }

    /**
     * Method: nullObj2int(Object obj, int i)
     */
    @Test
    public void testNullObj2int() throws Exception {
        Assert.assertEquals(ValueTool.nullObj2int("123", 9999), 123);
        Assert.assertEquals(ValueTool.nullObj2int("1234567890", 9999), 1234567890);
        Assert.assertEquals(ValueTool.nullObj2int("12345678901", 9999), 9999);
        Assert.assertEquals(ValueTool.nullObj2int(null, 9999), 9999);
    }

    /**
     * Method: getString(String s)
     */
    @Test
    public void testGetString() throws Exception {
        Assert.assertEquals(ValueTool.getString("中abc123@？＋"), "中abc123@？＋");
        Assert.assertEquals(ValueTool.getString(""), "");
        Assert.assertEquals(ValueTool.getString(null), "");
    }

    /**
     * Method: null2String(String s, String str)
     */
    @Test
    public void testNull2String() throws Exception {
        Assert.assertEquals(ValueTool.null2String("中abc123@？＋", "错误字符串"), "中abc123@？＋");
        Assert.assertEquals(ValueTool.null2String("", "错误字符串"), "");
        Assert.assertEquals(ValueTool.null2String(null, "错误字符串"), "错误字符串");
    }

    /**
     * Method: str2Int(String s)
     */
    @Test
    public void testStr2IntS() throws Exception {
        Assert.assertEquals(ValueTool.str2Int("123"), 123);
        Assert.assertEquals(ValueTool.str2Int("1234567890"), 1234567890);
        Assert.assertEquals(ValueTool.str2Int("2.0"), 0);
        Assert.assertEquals(ValueTool.str2Int("2.1"), 0);
        Assert.assertEquals(ValueTool.str2Int("中abc123@？＋"), 0);
        Assert.assertEquals(ValueTool.str2Int("12345678901"), 0);
        Assert.assertEquals(ValueTool.str2Int(null), 0);
    }

    /**
     * Method: str2Int(String s, int i)
     */
    @Test
    public void testStr2IntForSI() throws Exception {
        Assert.assertEquals(ValueTool.str2Int("123",9999), 123);
        Assert.assertEquals(ValueTool.str2Int("1234567890",9999), 1234567890);
        Assert.assertEquals(ValueTool.str2Int("2.0",9999), 9999);
        Assert.assertEquals(ValueTool.str2Int("2.1",9999), 9999);
        Assert.assertEquals(ValueTool.str2Int("中abc123@？＋",9999), 9999);
        Assert.assertEquals(ValueTool.str2Int("12345678901",9999), 9999);
    }

    /**
     * Method: str2Long(String s)
     */
    @Test
    public void testStr2LongS() throws Exception {
        Assert.assertEquals(ValueTool.str2Long("123"), 123L);
        Assert.assertEquals(ValueTool.str2Long("12345678901"), 12345678901L);
        Assert.assertEquals(ValueTool.str2Long("123456789012345678901234567890"), 0L);
        Assert.assertEquals(ValueTool.str2Long("2.0"), 0L);
        Assert.assertEquals(ValueTool.str2Long("2.1"), 0L);
        Assert.assertEquals(ValueTool.str2Long("中abc123@？＋"), 0L);
    }

    /**
     * Method: str2Long(String s, long l)
     */
    @Test
    public void testStr2LongForSL() throws Exception {
        Assert.assertEquals(ValueTool.str2Long("123",9999L), 123L);
        Assert.assertEquals(ValueTool.str2Long("12345678901",9999L), 12345678901L);
        Assert.assertEquals(ValueTool.str2Long("123456789012345678901234567890",9999L), 9999L);
        Assert.assertEquals(ValueTool.str2Long("2.0",9999L), 9999L);
        Assert.assertEquals(ValueTool.str2Long("2.1",9999L), 9999L);
        Assert.assertEquals(ValueTool.str2Long("中abc123@？＋",9999L), 9999L);
    }

    /**
     * Method: str2Money(String s)
     */
    @Test
    public void testStr2MoneyS() throws Exception {
        Assert.assertEquals(ValueTool.str2Money("123"), new BigDecimal("123.00"));
        Assert.assertEquals(ValueTool.str2Money("0000"), new BigDecimal("0.00"));
        Assert.assertEquals(ValueTool.str2Money("0123"), new BigDecimal("123.00"));
        Assert.assertEquals(ValueTool.str2Money("0000.00"), new BigDecimal("0.00"));
        Assert.assertEquals(ValueTool.str2Money("0123.00"), new BigDecimal("123.00"));
        Assert.assertEquals(ValueTool.str2Money("12345678901"), new BigDecimal("12345678901.00"));
        Assert.assertEquals(ValueTool.str2Money("123456789012345678901234567890"), new BigDecimal("123456789012345678901234567890.00"));
        Assert.assertEquals(ValueTool.str2Money("2.0"), new BigDecimal("2.00"));
        Assert.assertEquals(ValueTool.str2Money("2.1"), new BigDecimal("2.10"));
        Assert.assertEquals(ValueTool.str2Money(null), new BigDecimal("0.00"));
        Assert.assertEquals(ValueTool.str2Money("中abc123@？＋"), new BigDecimal("0.00"));

        //银行家舍入进位（单5进位，双5不进）
        Assert.assertEquals(ValueTool.str2Money("2.135"), new BigDecimal("2.14"));
        Assert.assertEquals(ValueTool.str2Money("2.132"), new BigDecimal("2.13"));
        Assert.assertEquals(ValueTool.str2Money("2.13499999"), new BigDecimal("2.13"));
        Assert.assertEquals(ValueTool.str2Money("2.136"), new BigDecimal("2.14"));

        Assert.assertEquals(ValueTool.str2Money("2.145"), new BigDecimal("2.14"));
        Assert.assertEquals(ValueTool.str2Money("2.144999999"), new BigDecimal("2.14"));
        Assert.assertEquals(ValueTool.str2Money("2.145000001"), new BigDecimal("2.15"));
        Assert.assertEquals(ValueTool.str2Money("2.146"), new BigDecimal("2.15"));
    }

    /**
     * Method: obj2Money(Object obj)
     */
    @Test
    public void testStr2MoneyObj() throws Exception {
        Assert.assertEquals(ValueTool.obj2Money(new Integer("123")), new BigDecimal("123.00"));
        Assert.assertEquals(ValueTool.obj2Money(new Long("12345678901")), new BigDecimal("12345678901.00"));
        Assert.assertEquals(ValueTool.obj2Money("123456789012345678901234567890"), new BigDecimal("123456789012345678901234567890.00"));
        Assert.assertEquals(ValueTool.obj2Money(new Float("2.0")), new BigDecimal("2.00"));
        Assert.assertEquals(ValueTool.obj2Money(new Float("2.1")), new BigDecimal("2.10"));
        Assert.assertEquals(ValueTool.obj2Money(null), new BigDecimal("0.00"));
        Assert.assertEquals(ValueTool.obj2Money("中abc123@？＋"), new BigDecimal("0.00"));
    }

    @Test
    public void testStr2ChineseMoney() throws Exception{
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.23", 1), "壹圆贰角叁分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.03", 1), "壹圆零叁分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.00", 1), "壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.10", 1), "壹圆壹角");
        Assert.assertEquals(ValueTool.str2ChineseMoney("0.10", 1), "壹角");
        Assert.assertEquals(ValueTool.str2ChineseMoney("0.12", 1), "壹角贰分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("0.02", 1), "贰分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("0.94", 1), "玖角肆分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.11499999999", 1), "壹圆壹角壹分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.115", 1), "壹圆壹角贰分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.1149999", 1), "壹圆壹角壹分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.1249999", 1), "壹圆壹角贰分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.125", 1), "壹圆壹角贰分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1.125000001", 1), "壹圆壹角叁分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("93231",1), "玖萬叁仟贰佰叁拾壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("320231",1), "叁拾贰萬零贰佰叁拾壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("301231",1), "叁拾萬壹仟贰佰叁拾壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300231",1), "叁拾萬零贰佰叁拾壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300031",1), "叁拾萬零叁拾壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("301031",1), "叁拾萬壹仟零叁拾壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("301030",1), "叁拾萬壹仟零叁拾圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("3",1), "叁圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("30",1), "叁拾圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300",1), "叁佰圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("3000",1), "叁仟圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("30000",1), "叁萬圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300000",1), "叁拾萬圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("3000000",1), "叁佰萬圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("30000000",1), "叁仟萬圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300000000",1), "叁億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("3000000000",1), "叁拾億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("30000000000",1), "叁佰億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300000000000",1), "叁仟億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("3000000000000",1), "叁萬億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("30000000000000",1), "叁拾萬億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300000000000000",1), "叁佰萬億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("3000000000000000",1), "叁仟萬億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("30000000000000000",1), "叁億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("100000000000000000",1), "壹拾億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1000000000000000000",1), "壹佰億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("10000000000000000000",1), "壹仟億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("100000000000000000000",1), "壹萬億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("1000000000000000000000",1), "壹拾萬億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("10000000000000000000000",1), "壹佰萬億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("100000000000000000000000",1), "壹仟萬億億圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("100000000000000000000001",1), "壹仟萬億億零壹圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300000303",1), "叁億零叁佰零叁圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300000330.03",1), "叁億零叁佰叁拾圆零叁分");
        Assert.assertEquals(ValueTool.str2ChineseMoney("300000000000000003",1), "叁拾億億零叁圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("30000000000000000003",1), "叁仟億億零叁圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("3000000300000003",1), "叁仟萬零叁億零叁圆整");
        Assert.assertEquals(ValueTool.str2ChineseMoney("123456789.01",0), "壹亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元零壹分");
        Assert.assertEquals(ValueTool.str2ChineseMoney(null, 1), "零圆整");
    }
    @Ignore
    public void testPrintMoney(){
        logger.info(ValueTool.str2Money("1,000").toString());
        logger.info("1.23 = {}",ValueTool.str2ChineseMoney("1.23", 0));
        logger.info("1.03 = {}",ValueTool.str2ChineseMoney("1.03", 0));
        logger.info("1.20 = {}",ValueTool.str2ChineseMoney("1.20",0));
        logger.info("1.00 = {}",ValueTool.str2ChineseMoney("1.00",0));
        logger.info("0.20 = {}",ValueTool.str2ChineseMoney("0.20",0));
        logger.info("0.23 = {}",ValueTool.str2ChineseMoney("0.23",0));
        logger.info("0.03 = {}",ValueTool.str2ChineseMoney("0.03",0));
        logger.info("0.2 = {}",ValueTool.str2ChineseMoney("0.2",0));
        logger.info("1.2 = {}",ValueTool.str2ChineseMoney("1.2",0));
        logger.info("1 = {}",ValueTool.str2ChineseMoney("1",0));
        logger.info("123456789 = {}",ValueTool.str2ChineseMoney("123456789",0));
        logger.info("101 = {}",ValueTool.str2ChineseMoney("101",0));
        logger.info("1001 = {}",ValueTool.str2ChineseMoney("1001",0));
        logger.info("11001 = {}",ValueTool.str2ChineseMoney("11001",0));
        logger.info("101000 = {}",ValueTool.str2ChineseMoney("101000",0));
        logger.info("1000101000 = {}",ValueTool.str2ChineseMoney("1000101000",0));
        logger.info("1000000000000 = {}",ValueTool.str2ChineseMoney("1000000000000",0));
        logger.info("10001000101000 = {}",ValueTool.str2ChineseMoney("10001000101000",0));
        logger.info("100010001000101000 = {}",ValueTool.str2ChineseMoney("100010001000101000",0));
        logger.info("100010000000101000 = {}",ValueTool.str2ChineseMoney("100010000000101000",0));
        logger.info("1000100010000000101000 = {}",ValueTool.str2ChineseMoney("1000100010000000101000",0));
        logger.info("100000000000000000000000 = {}",ValueTool.str2ChineseMoney("100000000000000000000000", 0));
        logger.info("100000000000000000000001 = {}",ValueTool.str2ChineseMoney("100000000000000000000001", 0));
        logger.info("100100010000000000000000 = {}",ValueTool.str2ChineseMoney("100100010000000000000000", 0));
        logger.info("100100010001000000000000 = {}",ValueTool.str2ChineseMoney("100100010001000000000000", 0));
        logger.info("100100010001000100000000 = {}",ValueTool.str2ChineseMoney("100100010001000100000000", 0));
        logger.info("1001000100010001000000000 = {}",ValueTool.str2ChineseMoney("1001000100010001000000000", 0));
        logger.info("abcd = {}",ValueTool.str2ChineseMoney("abcd", 0));
        logger.info("0.005 = {}",ValueTool.str2ChineseMoney("0.005", 0));
        logger.info("0.015 = {}",ValueTool.str2ChineseMoney("0.015", 0));
    }

    /**
     * Method: formatStr(String exp, Object[] values)
     */
    @Test
    public void testFormatStr(){
        String exp = "您好{0}，{1}将竭诚为您服务！";
        String[] values = {"张三","Baguix工作室"};
        String str = ValueTool.formatStr(exp,values);
        Assert.assertEquals(str,"您好张三，Baguix工作室将竭诚为您服务！");
    }
} 

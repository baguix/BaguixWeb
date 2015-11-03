/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/15.
 */
package com.baguix.utils.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * <b>正则表达式工具类</b>
 *
 * @author Scott(SG)
 */
public class RegexTool {

    /**
     * <b>整型</b><br>
     *     匹配 (1) (+1) (-1) (0) (1,000) (-1,000)；<br>
     *     不匹配 (1.0)。
     */
    public static final String	INTEGER = "^[-|+]?(\\d{1,3}(,\\d{3})*|\\d*)";

    /**
     * <b>正整型</b><br>
     *     匹配 (1) (+1) (1,000)；<br>
     *     不匹配 (1.0)；<br>
     *     注意 (0) (+0)不是正整数。
     */
    public static final String	INTEGER_NEGATIVE = "^[+]?[1-9]\\d{0,2}(,\\d{3})*";

    /**
     * <b>负整型</b><br>
     *     匹配 (-1) (-1,000)；<br>
     *     不匹配 (-1.0)；<br>
     *     注意 (0) (-0)不是负整数。
     */
    public static final String	INTEGER_POSITIVE = "^-[1-9]\\d{0,2}(,\\d{3})*";

    /**
     * <b>实型</b><br>
     *     匹配 (123.) (123.0) (1.23) (+1.23) (-1.23) (0.123) (0.0) (.123) (1,000.00) (+1,000.00) (-1,000.00) 。
     */
    public static final String	REAL ="^[-|+]?((\\d{1,3}(,\\d{3})*\\.\\d*)|(0\\.\\d*[1-9]\\d*)|(0?\\.0+)|(\\.\\d*[1-9]\\d*))";

    /**
     * <b>正实型</b><br>
     *     匹配 (123.) (123.0) (1.23) (+1.23) (0.123) (.123) (1,000.00) (+1,000.00)；<br>
     *     注意 (0.0) (+0.0)不是正实数。
     */
    public static final String	REAL_NEGATIVE ="^[+]?(([1-9]\\d{0,2}(,\\d{3})*\\.\\d*)|(0\\.\\d*[1-9]\\d*)|(\\.\\d*[1-9]\\d*))";

    /**
     * 负实型<br>
     *     匹配 (-123.) (-123.0) (-1.23) (-0.123) (-.123) (-1,000.00)；<br>
     *     注意 (0.0) (-0.0)不是负实数。
     */
    public static final String	REAL_POSITIVE ="^-(([1-9]\\d{0,2}(,\\d{3})*\\.\\d*)|(0\\.\\d*[1-9]\\d*)|(\\.\\d*[1-9]\\d*))";

    /**
     * <b>货币型</b><br>
     *     匹配:<br>
     *         "1000" "1,000" "1000.00"  "1,000.00" "1,000.1" "1,000.10"、<br>
     *         "0.10" "+0.10" "-0.10" "0.00"
     */
    public static final String MONEY="^[-|+]?([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(\\.[0-9]{1,2})?$";

    /**
     * <b>日期型</b><br>
     *     匹配 YYYY-MM-DD YYYY/MM/DD、YYYY_MM_DD、YYYYMMDD、YYYY.MM.DD的形式；<br>
     *     不匹配 YY-MM-DD；<br>
     *     注意 1800年以前不支持。
     */
    public static final String DATE = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";

    /**
     * <b>月份</b><br>
     *     匹配 一年的12个月(01～09、10～12或1～12)。
     */
    public static final String  MONTH="^(0?[1-9]|1[0-2])$";

    /**
     * <b>日份</b><br>
     *     匹配 一个月的31天(01～09或1～31)。
     */
    public static final String  DAY="^((0?[1-9])|((1|2)[0-9])|30|31)$";

    /**
     * <b>年龄</b><br>
     *     匹配 1-150岁
     *     注意 0岁 不匹配
     */
    public static final String  AGE="^(0?[1-9])|[1-9][0-9]|1[0-4][0-9]|150$";

    /**
     * <b>国内邮编</b><br>
     *     我国邮政编码是6位数字组成.前两位数字表示(省、自治区、直辖市);前三位表示(邮区代号);前四位表示(市（县）的编号);最后两位数字表示(邮件投递局、所)
     */
    public static final String  ZIPCODE="[1-9]\\d{5}(?!\\d)";

    /**
     * <b>Email地址</b><br>
     *     匹配 (8323582@qq.com) (13854574125@189.com) (abc_123@126.com) (abcde@sina.com.cn)
     */
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * <b>固定电话/手机</b><br>
     *     匹配 (119) (12315) (1008611) (400、800电话) (手机) (0771-2545214) (010-32545214) (0755-32545214)<br>
     *     注意 间隔符支持(-) (_) (－) (—)，如：0771_5420145<br>
     *          由于电话号码比较广泛，本正则式也可以匹配手机号码
     */
    public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";

    /**
     * <b>手机号码</b><br>
     *     匹配 (13) (14) (15) (17) (18)开头的手机号码<br>
     *     匹配 +86 +086 +0086 86 086 0086 (+86) (+086) (+0086) (086) (0086) 格式国家前缀
     */
    public static final String MOBILE ="^((\\+?0{0,2}86)|(\\(\\+?0{0,2}86\\))|)?(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

    /**
     * <b>IP地址</b><br>
     *     匹配 (127.0.0.1) (10.0.0.1) (192.168.0.1) (192.168.000.001)
     */
    public static final String IPADDRESS = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";

    /**
     * <b>标识符</b><br>
     *     匹配 字母或下划线开头，并由数字、26个英文字母、下划线组成的字符串
     */
    public static final String IDENTIFIER="^[A-Za-z_]\\w*$";

    /**
     * <b>登录帐号</b><br>
     *     匹配 0-25字符，以字母开头，英文字母、数字和下划线组成的字符串
     */
    public static final String USERID="^[A-Za-z]\\w{0,24}$";

    /**
     * <b>人名</b><br>
     *     匹配 15字以内的中文名，注意 赵C这种名字不能匹配；<br>
     *     匹配 英文名(Scott Gates) (jobs.jone)，注意 空格或.不能出现在头和尾。
     */
    public static final String USERNAME="(^[A-Za-z][A-Za-z|\\s|\\.]{0,30}[A-Za-z]$)|(^[\\u0391-\\uFFE5]{1,15}$)";

    /**
     *
     * <b>中文字符</b><br>
     *     匹配 全字符集
     */
    public static final String CHINESE="^[\\u0391-\\uFFE5]+$";

    /**
     * <b>中文字符</b><br>
     *     匹配 常用中文字符集
     */
    public static final String CHINESE_NORMAL="^[\\u4E00-\\u9FA5]+$";

    /**
     * <b>ASCII码英文符号</b><br>
     *     匹配 字符串中包含编程中使用的英文字符
     *     匹配 (空格) (!) (=) (\) (+) 等非a-zA-Z0-9的ASCII字符
     *     不匹配 (回车) (退格) (制表)
     */
    public static final String ASCII_CHAR=".*[\\u0020-\\u002f|\\u003a-\\u0040|\\u005b-\\u0060|\\u007b-\\u007e].*";

    /**
     * <b>安全字符</b><br>
     *     匹配 英文、数字、空格、常用中文字符<br>
     *     匹配 半角字符 仅包含(.) (%) (&amp;) (#) (@)<br>
     *     匹配 全角字符 仅包含(“) (”) (‘) (’) (。) (，) (—) (《) (》) (·) (？) (；) (：) (【) (】) (（) (）) (…) (￥) (％) (＆) (℃)）<br>
     */
    public static final String SECURITY_STRING="^[a-z|A-Z|0-9|\\u4E00-\\u9FA5|\\s" +
            "|\\u002e|\\u0025|\\u0026|\\u0023|\\u0040" +
            "|“|”|‘|’|。|，|—|《|》|·|？|；|：|【|】|（|）|…|￥|％|＆|℃" +
            "|１|２|３|４|５|６|７|８|９|０" +
            "|ａ|ｂ|ｃ|ｄ|ｅ|ｆ|ｇ|ｈ|ｉ|ｊ|ｋ|ｌ|ｍ|ｎ|ｏ|ｐ|ｑ|ｒ|ｓ|ｔ|ｕ|ｖ|ｗ|ｘ|ｙ|ｚ" +
            "|Ａ|Ｂ|Ｃ|Ｄ|Ｅ|Ｆ|Ｇ|Ｈ|Ｉ|Ｊ|Ｋ|Ｌ|Ｍ|Ｎ|Ｏ|Ｐ|Ｑ|Ｒ|Ｓ|Ｔ|Ｕ|Ｖ|Ｗ|Ｘ|Ｙ|Ｚ]+$";

    /**
     * <b>URL</b><br>
     *     匹配 (http) (https) (ftp)<br>
     *     例如 http://www.baidu.com<br>
     *          http://www.baidu.com.cn<br>
     *          http://www.baidu.com<br>
     *          http://baidu.com<br>
     *          http://www.baidu.com:8080<br>
     *          http://www.baidu.com:8080?id=123<br>
     *          http://www.baidu.com:8080/s.do?id=123<br>
     *          http://www.baidu.com:8080/s?id=123<br>
     *          http://www.baidu.com:8080/s?key=%23%ef<br>
     *          http://www.baidu.com:8080/s?id='123'<br>
     *          http://www.baidu.com:8080?id=%3d<br>
     *          http://www.baidu.com:8080//a.html<br>
     *          http://baidu.com
     */
    public static final String URL = "^(http|https|ftp)?(://)?" +   // 协议名
            "(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))+" +   // 域名
            "((:\\d+)?)" +   // 端口
            "(/(\\w*(-\\w*)*))*(\\.?(\\w)*)" +   // 目录和文件
            "(\\?)?" +  // 后面都是参数部分
            "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*='?\\S*'?)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

    /**
     * <b>身份证号</b><br>
     *     说明 18位身份证号码编码规则：根据〖中华人民共和国国家标准 GB 11643-1999〗<br>
     *          本正则表达式无法判断身份证真伪。<br>
     *          若判断身份号的真伪，则必须根据《GB 11643-1999》最后一位校验码的生成方法另写校验方法。
     */
    public static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|81|82)[0-9]{4})" + // 地址码
            "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9]" +  // 出生日期码（18位身份证）
            "[0-9]{3}" +   // 顺序码
            "[X|x|0-9])" +  // 校验码
            "|" +
            "([0-9]{2}[0|1][0-9][0-3][0-9]" + // 出生日期码（15位身份证）
            "[0-9]{3}))";

    /**
     * <b>车牌</b><br>
     *     说明 92式车牌标准，字头和字母为一段，后5位数字为一段<br>
     *     匹配 (桂A12345) (桂A 12345) (桂A-12345)格式<br>
     *     匹配 冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤川青藏琼宁渝京津沪<br>
     *     匹配 警学试临时<br>
     *     匹配 WJ军京空海北沈兰济南广成
     */
    public static final String	LICENCE_PLATE = "^[冀|豫|云|辽|黑|湘|皖|鲁|新|苏|浙|赣|鄂|桂|甘|晋|蒙|陕|吉|闽|贵|粤|川|青|藏|琼|宁|渝|京|津|沪|警|学|试|临时|WJ|军|京|空|海|北|沈|兰|济|南|广|成]"
            + "{1}[A-Z]{1}[-|\\s]?[A-Z_0-9]{5}$";

    public static final String JS_TAG = "<[\\s]*script[^>]*>|<[\\s]*/[\\s]*script[\\s]*>";
    public static final String CSS_TAG = "<[\\s]*style[^>]*>|<[\\s]*/[\\s]*style[\\s]*>";
    public static final String HTML_TAG="<[\\s]*[^>]*>|<[\\s]*/.*[\\s]*>";
    public static final String HTML="<!?[\\s]*[^>]*>.*<[\\s]*/[^>]*[\\s]*>|<[^>]*>";
    public static final String CSS = "<[\\s]*style[^>]*>[\\s\\S]*<[\\s]*/[\\s]*style[\\s]*>";
    public static final String JS="<[\\s]*script[^>]*>[\\s\\S]*<[\\s]*/[\\s]*script[\\s]*>";

    /**
     * <b>匹配是否符合正则表达式</b><br>
     * 匹配则返回true
     * @param str 需匹配的字符串
     * @param pattern 正则表达式
     * @return 是否匹配(boolean)
     */
    public static final boolean isMatch(String str, String pattern) {
        if (null == str || str.trim().length() <= 0)
            return false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * <b>根据正则表达式过滤字符串</b><br>
     *
     * @param str 需要过滤的字符串
     * @param pattern 正则表达式
     * @return 过滤后的字符串(String)
     */
    public static final String filter(String str, String pattern) {
        if (null == str || str.trim().length() <= 0)
            return "";
        Pattern p = Pattern.compile(pattern,Pattern.DOTALL);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/22.
 */
package com.baguix.utils.security;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * <b>编码解码工具类</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class EnDecodeTool {
    // 单例模式
    private static EnDecodeTool instance;
    private static EnDecodeTool getInstance(){
        if(instance == null){
            instance = new EnDecodeTool();
        }
        return instance;
    }

    // 隐藏构造器
    private EnDecodeTool(){
    }

    /**
     * <b>NATIVE转为ASCII</b><br>
     *     说明：*.properties文件经过native2ascii.exe工具进行UTF-8编码后。英文保留不变，但中文变成以\\u开头的编码<br>
     *     例如：baguix工作室 = <pre>baguix\u5DE5\u4F5C\u5BA4</pre>
     * @param nativ 普通字符串
     * @return String ASCII字符串
     */
    public static String Native2AscII(String nativ){
        if(nativ!=null && !"".equals(nativ)) {
            return StringEscapeUtils.escapeJava(nativ);
        }
        return "";
    }

    /**
     * <b>ASCII转为NATIVE</b><br>
     * @see EnDecodeTool#Native2AscII(String)
     * @param ascii ASCII字符串
     * @return String 普通字符串
     */
    public static String AscII2Native(String ascii){
        if(ascii!=null && !"".equals(ascii)) {
            return StringEscapeUtils.unescapeJava(ascii);
        }
        return "";
    }

    /**
     * <b>字符串转为Unicode</b><br>
     *     说明：所有的字符都变成以\\u开头的编码<br>
     *     例如：baguix工作室 = <pre>\u0062\u0061\u0067\u0075\u0069\u0078\u5de5\u4f5c\u5ba4</pre>
     * @see EnDecodeTool#Native2AscII(String)
     * @param str 普通字符串
     * @return String Unicode编码
     */
    public static String Str2Unicode(String str) {
        if (str != null && !"".equals(str)) {
            StringBuffer unicode = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                char achar = str.charAt(i);
                String hex = Integer.toHexString(achar).toUpperCase();
                if(hex.length()<=2){
                    hex = "00"+hex;
                }
                unicode.append("\\u" + hex);
            }
            return unicode.toString();
        }
        return "";
    }

    /**
     * <b>Unicode转为字符串</b><br>
     * @see EnDecodeTool#Str2Unicode(String)
     * @param unicode ASCII字符串
     * @return String Unicode编码
     */
    public static String Unicode2Str(String unicode){
        if(unicode!=null && !"".equals(unicode)) {
            StringBuffer sb = new StringBuffer();
            String[] hex = unicode.split("\\\\u");
            for (int i = 1; i < hex.length; i++) {
                int data = Integer.parseInt(hex[i], 16);
                sb.append((char) data);
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * <b>URIEncoding，采用RFC-1738标准</b><br>
     *     说明：较老的一种编码方式，导致%20本应是空格，但可能会被解析为加号(+)<br>
     *     baguix工作室 = baguix%E5%B7%A5%E4%BD%9C%E5%AE%A4
     * @param str
     * @return
     */
    public static String oldURIEncoding(String str) throws Exception{
        if (str != null && !"".equals(str)) {
            return URLEncoder.encode(str, "UTF-8");
        }
        return "";
    }

    /**
     * <b>URIEncoding，采用RFC-3986标准</b><br>
     *     RFC3986文档规定，Url中只允许包含英文字母（a-zA-Z）、数字（0-9）、-_.~4个特殊字符以及所有保留字符。<br>
     *     JAVA的简单解决方法是自行替换+号为%20<br>
     *     说明：baguix工作室 = baguix%E5%B7%A5%E4%BD%9C%E5%AE%A4
     * @param str 字符串
     * @param charset 编码
     * @return
     */
    public static String URIEncoding(String str, String charset) throws Exception{
        if (str != null && !"".equals(str)) {
            return URLEncoder.encode(str, charset).replace("+","%20");
        }
        return "";
    }

    /**
     * <b>将字符串有某种编码转变成另一种编码</b><br>
     * @param str 编码的字符串
     * @param origincharset 原始编码格式
     * @param targetcharset 目标编码格式
     * @return String 编码后的字符串
     */
    public static String changeStrCharset(String str,Charset origincharset,Charset targetcharset){
        String string="";
        if(str!=null && !"".equals(str) && origincharset!=null && targetcharset!=null){
            string =  new String(str.getBytes(origincharset),targetcharset);
        }
        return string;
    }

    /**
     * <b>返回十六进制字符串</b><br>
     * @param str 字符串
     * @return
     */
    public static String str2Hex(String str) {
        StringBuffer sb = new StringBuffer();
        if(str!=null && !"".equals(str)) {
            byte[] arr = str.getBytes();
            for (int i = 0; i < arr.length; ++i) {
                sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
            }
        }
        return sb.toString();
    }

    /**
     * <b>返回十六进制字符串</b><br>
     * @param arr 字符串
     * @return
     */
    public static String byte2Hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
}

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/15.
 */
package com.baguix.utils.data;

/**
 * <b>系统常量</b>
 *
 * @author Scott(SG)
 */
public class Constants {
    // 版权信息
    public static final String COPYRIGHT = "baguix.com";
    public static final String COMPANY = "八桂星工作室";
    public static final String AUTHORIZATION = "BaguixWeb";
    public static final String URL = "www.baguix.com";
    public static final String LICENSE = "www.baguix.com";
    // 编码
    public static final String GB = "GB2312";
    public static final String GBK = "GBK";
    public static final String UTF8 = "UTF-8";
    public static final String ISO = "ISO-8859-1";
    // 金额大写
    public static final char[] CHINESE_NUMBER={'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
    // 金额级数
    public static final String[] MONEY_SERIES={"","厘","分","角","元","拾","佰","仟","万","亿"};
    public static final String[] TRADITIONAL_MONEY_SERIES={"","厘","分","角","圆","拾","佰","仟","萬","億"};
    // 繁体格式
    public static final int SIMPLIFIED = 0;
    public static final int TRADITIONAL = 1;
    // 允许和禁止
    public static final int ENABLED = 1;
    public static final int DISABLED = 0;
    // 显示和隐藏
    public static final int SHOW = 1;
    public static final int HIDE = 0;
    // AB
    // "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%&,()*+,-./:;<=>?[]^_、{|}` "
    // "EhiFjD\"PG_xeN*0]^vZW {Oy4、o$Cm}qM`Tw5cBt!#6%r2kR9=l>?[a&,(g)V1X.ud;<H7s/U8bKn|IJ,-LQp+zAY:fS3"
    // "YsXig,l8.qv&:On=Fw;uAa#hEeRp!4>IyxLCdVz} j79)?\"`Nc|_<{J6T-3BMSPQ,rW*(b01Hmfk[KoG$2+%Z、^Ut5/]D"
    public static final char[][] a_b = {
            {89, 115, 88, 105, 103, 44, 108, 56, 46, 113, 118, 38, 58, 79, 110, 61, 70, 119, 59, 117, 65, 97, 35, 104, 69, 101, 82, 112, 33, 52, 62, 73, 121, 120, 76, 67, 100, 86, 122, 125, 32, 106, 55, 57, 41, 63, 34, 96, 78, 99, 124, 95, 60, 123, 74, 54, 84, 45, 51, 66, 77, 83, 80, 81, 44, 114, 87, 42, 40, 98, 48, 49, 72, 109, 102, 107, 91, 75, 111, 71, 36, 50, 43, 37, 90, 12289, 94, 85, 116, 53, 47, 93, 68},
            {69, 104, 105, 70, 106, 68, 34, 80, 71, 95, 120, 101, 78, 42, 48, 93, 94, 118, 90, 87, 32, 123, 79, 121, 52, 12289, 111, 36, 67, 109, 125, 113, 77, 96, 84, 119, 53, 99, 66, 116, 33, 35, 54, 37, 114, 50, 107, 82, 57, 61, 108, 62, 63, 91, 97, 38, 44, 40, 103, 41, 86, 49, 88, 46, 117, 100, 59, 60, 72, 55, 115, 47, 85, 56, 98, 75, 110, 124, 73, 74, 44, 45, 76, 81, 112, 43, 122, 65, 89, 58, 102, 83, 51}
    };
}

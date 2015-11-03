/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/15.
 */
package com.baguix.utils.data;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * <b>数值转换工具</b><br>
 *
 * @author Scott(SG)
 */
public class ValueTool {

    /**
     * <b>对象转为字符串，null转换为空字符串</b><br>
     *
     * @param obj 对象
     * @return String  如果对象为null,返回为空("")，否则返回obj.toString()
     */
    public static String obj2String(Object obj) {
        String str;
        try {
            if (obj != null) {
                str = obj.toString();
            } else {
                return "";
            }
        } catch (Exception e) {
            str = "";
        }
        return str;
    }

    /**
     * <b>对象转换为字符串，null转换为指定字符串</b><br>
     *
     * @param obj 对象
     * @param s   空值对应的内容
     * @return String 如果对象为null,返回为s，否则返回obj.toString()
     */
    public static String nullObj2String(Object obj, String s) {
        String str;
        try {
            if (obj != null) {
                str = obj.toString();
            } else {
                return s;
            }
        } catch (Exception e) {
            str = s;
        }
        return str;
    }

    /**
     * <b>对象转换为整型数</b><br>
     *
     * @param obj 对象
     * @return int 如果对象为null,返回为0，否则返回obj.toString().trim().parseInt()
     */
    public static int obj2int(Object obj) {
        int i;
        try {
            if (obj != null) {
                String str = obj.toString().trim().replace(",", "");
                if (RegexTool.isMatch(str, RegexTool.INTEGER)) {
                    i = Integer.parseInt(str);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

    /**
     * <b>对象转换为整型数,，null转换为指定数值i</b><br>
     *
     * @param obj 对象
     * @param i   指定数值
     * @return int 如果对象为null,返回为i，否则返回obj.toString().trim().parseInt()
     */
    public static int nullObj2int(Object obj, int i) {
        int in;
        try {
            if (obj != null) {
                String str = obj.toString().trim().replace(",", "");
                if (RegexTool.isMatch(str, RegexTool.INTEGER)) {
                    in = Integer.parseInt(str);
                } else {
                    return i;
                }
            } else {
                return i;
            }
        } catch (Exception e) {
            in = i;
        }
        return in;
    }

    /**
     * <b>对象转换为金额，保留2位小数，银行家舍入进位；非数值和null转换为0.00</b><br>
     *
     * @param obj 对象
     * @return String 如果字符串为null,则返回0
     */
    public static BigDecimal obj2Money(Object obj) {
        BigDecimal i = new BigDecimal("0.00");
        try {
            if (obj != null) {
                String s = obj.toString().replace(",", "");
                return new BigDecimal(s).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            } else {
                return new BigDecimal("0.00");
            }
        } catch (Exception e) {
            i = new BigDecimal("0.00");
        }
        return i;
    }

    /**
     * <b>字符转换函数，null转换为空字符串</b><br>
     *
     * @param s 字符串
     * @return String 如果字符串为null,返回为空(""),否则返回该字符串
     */
    public static String getString(String s) {
        return s == null ? "" : s;
    }


    /**
     * <b>填充字符串的{}占位符</b><br>
     *     例如："我是中国人，我来自北京，今年22岁"=formatStr("我是{0},我来自{1},今年{2}岁", "中国人", "北京", "22");
     * @param exp 以{n}为占位符的表达式
     * @param values 字符串数值数组
     * @return 格式化后的字符串
     */
    public static String formatStr(String exp, Object[] values) {
        return MessageFormat.format(exp, values);
    }

    /**
     * <b>对象转为字符串，null转换为指定字符串</b><br>
     *
     * @param s   字符串
     * @param str 要转换成的字符串
     * @return String 如果字符串为null,返回为指定字符串str,否则返回该字符串
     */
    public static String null2String(String s, String str) {
        return s == null ? str : s;
    }

    /**
     * <b>字符转换函数，null转换为0</b><br>
     *
     * @param s 字符串
     * @return String 如果字符串为null或程序异常,返回0
     */
    public static int str2Int(String s) {
        int i;
        try {
            if (s != null && RegexTool.isMatch(s.trim().replace(",", ""), RegexTool.INTEGER)) {
                i = Integer.parseInt(s);
            } else {
                i = 0;
            }
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

    /**
     * <b>字符转换函数，null转换为0</b><br>
     *
     * @param s 字符串
     * @param i 指定空值或转换出错返回该整数
     * @return String 如果字符串为null,返回i
     */
    public static int str2Int(String s, int i) {
        int in;
        try {
            if (s != null && RegexTool.isMatch(s.trim().replace(",", ""), RegexTool.INTEGER)) {
                in = Integer.parseInt(s);
            } else {
                return i;
            }
        } catch (Exception e) {
            in = i;
        }
        return in;
    }

    /**
     * <b>字符转换函数，null转换为0</b><br>
     *
     * @param s 字符串
     * @return String 如果字符串为null,返回0
     */
    public static long str2Long(String s) {
        long i;
        try {
            if (s != null && RegexTool.isMatch(s.trim().replace(",", ""), RegexTool.INTEGER)) {
                i = Long.parseLong(s);
            } else {
                return 0L;
            }
        } catch (Exception e) {
            i = 0L;
        }
        return i;
    }

    /**
     * <b>字符转换函数，null转换为0</b><br>
     *
     * @param s 字符串
     * @param l 指定的长整型
     * @return String 如果字符串为null,返回0
     */
    public static long str2Long(String s, long l) {
        long i;
        try {
            if (s != null && RegexTool.isMatch(s.trim().replace(",", ""), RegexTool.INTEGER)) {
                i = Long.parseLong(s);
            } else {
                return l;
            }
        } catch (Exception e) {
            i = l;
        }
        return i;
    }

    /**
     * <b>字符转换为金钱函数，保留2位小数，银行家舍入进位；非数值和null转换为0.00</b><br>
     *
     * @param s 字符串
     * @return String 如果字符串为null,返回0
     */
    public static BigDecimal str2Money(String s) {
        BigDecimal i;
        try {
            s = s.trim().replace(",", "");
            i = new BigDecimal(s).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        } catch (Exception e) {
            i = new BigDecimal("0.00");
        }
        return i;
    }

    /**
     * <b>字符串转换为中文金额函数:</b><br>
     * 1. 最大以亿做单位; <br>
     * 2. 在千万亿亿级内正确（即，1000000000000000000000000不能显示壹亿亿亿元整，而返回“数值过大”）; <br>
     * 3. 保留2位小数; <br>
     * 4. 银行家舍入进位； <br>
     * 5. 非数值和null转换为零圆
     *
     * @param s    对象
     * @param type 0：简体；1：繁体
     * @return String 如果字符串为null,返回0
     */
    public static String str2ChineseMoney(String s, int type) {
        char[] CHINESE_NUMBER = Constants.CHINESE_NUMBER;
        String[] MONEY_SERIES;
        switch (type) {
            case Constants.SIMPLIFIED:
                MONEY_SERIES = Constants.MONEY_SERIES;
                break;
            case Constants.TRADITIONAL:
                MONEY_SERIES = Constants.TRADITIONAL_MONEY_SERIES;
                break;
            default:
                MONEY_SERIES = Constants.MONEY_SERIES;
                break;
        }

        BigDecimal num = str2Money(s);
        if (num != null && num.doubleValue() == 0) {
            return CHINESE_NUMBER[0] + MONEY_SERIES[4] + "整";
        }
        StringBuffer cn = new StringBuffer();
        if (num != null) {
            String n = num.toString();
            // 级数:'个0','十1','百2','千3','万4','十万5',...
            int k = 0;
            String[] nlist = n.split("\\.");
            if (nlist[0].length() > 24) {
                return "数值过大。";
            }
            for (int i = nlist[0].length() - 1; i >= 0; i--) {
                // 级数K特殊位置处理
                switch (k) {
                    // 个位
                    case 0:
                        cn = cn.insert(0, MONEY_SERIES[0]);
                        break;
                    // 万位
                    case 4:
                        if (nlist[0].length() > 7 && (nlist[0].charAt(i) - 48) == 0 && (nlist[0].charAt(i - 1) - 48) == 0 && (nlist[0].charAt(i - 2) - 48) == 0 && (nlist[0].charAt(i - 3) - 48) == 0) {
                            break;
                        }
                        cn = cn.insert(0, MONEY_SERIES[8]);
                        break;
                    // 亿位
                    case 8:
                        if (nlist[0].length() > 11 && (nlist[0].charAt(i) - 48) == 0 && (nlist[0].charAt(i - 1) - 48) == 0 && (nlist[0].charAt(i - 2) - 48) == 0 && (nlist[0].charAt(i - 3) - 48) == 0) {
                            break;
                        }
                        cn = cn.insert(0, MONEY_SERIES[9]);
                        break;
                    // 万亿位
                    case 12:
                        if (nlist[0].length() > 15 && (nlist[0].charAt(i) - 48) == 0 && (nlist[0].charAt(i - 1) - 48) == 0 && (nlist[0].charAt(i - 2) - 48) == 0 && (nlist[0].charAt(i - 3) - 48) == 0) {
                            break;
                        } else if ((nlist[0].charAt(i + 1) - 48) == 0 && (nlist[0].charAt(i + 2) - 48) == 0 && (nlist[0].charAt(i + 3) - 48) == 0 && (nlist[0].charAt(i + 4) - 48) == 0) {
                            cn = cn.insert(0, MONEY_SERIES[9]);
                            cn = cn.insert(0, MONEY_SERIES[8]);
                            break;
                        } else {
                            cn = cn.insert(0, MONEY_SERIES[8]);
                        }
                        break;
                    // 亿亿位
                    case 16:
                        if (nlist[0].length() > 19 && (nlist[0].charAt(i) - 48) == 0 && (nlist[0].charAt(i - 1) - 48) == 0 && (nlist[0].charAt(i - 2) - 48) == 0 && (nlist[0].charAt(i - 3) - 48) == 0) {
                            break;
                        }
                        cn = cn.insert(0, MONEY_SERIES[9]);
                        cn = cn.insert(0, MONEY_SERIES[9]);
                        break;
                    // 万亿亿位，级数归零，超过千万亿亿位则数值出错，需要更多位数请往下扩展此程序结构。
                    case 20:
                        if (nlist[0].length() > 23 && (nlist[0].charAt(i) - 48) == 0 && (nlist[0].charAt(i - 1) - 48) == 0 && (nlist[0].charAt(i - 2) - 48) == 0 && (nlist[0].charAt(i - 3) - 48) == 0) {
                            break;
                        } else if ((nlist[0].charAt(i + 1) - 48) == 0 && (nlist[0].charAt(i + 2) - 48) == 0 && (nlist[0].charAt(i + 3) - 48) == 0 && (nlist[0].charAt(i + 4) - 48) == 0) {
                            cn = cn.insert(0, MONEY_SERIES[9]);
                            cn = cn.insert(0, MONEY_SERIES[9]);
                            cn = cn.insert(0, MONEY_SERIES[8]);
                            break;
                        } else {
                            cn = cn.insert(0, MONEY_SERIES[8]);
                        }
                        k = 0;
                        break;
                    default:
                        break;
                }
                // 当前位是0，
                if ((nlist[0].charAt(i) - 48) == 0) {
                    if (k % 4 != 0) {
                        if ((nlist[0].charAt(i + 1) - 48) != 0) {
                            cn.insert(0, CHINESE_NUMBER[0]);
                        }
                    }
                } else {
                    if (k % 4 != 0) {
                        cn.insert(0, MONEY_SERIES[k % 4 + 4]);
                    }
                    cn.insert(0, CHINESE_NUMBER[nlist[0].charAt(i) - 48]);
                }
                k++;
            }

            // 小数点前有数值，则加“元”字。
            boolean numflag;
            if (cn.length() > 0) {
                cn.append(MONEY_SERIES[4]);
                numflag = true;
            } else {
                numflag = false;
            }

            // 处理小数部分
            if ((nlist[1].charAt(0) - 48 == 0) && (nlist[1].charAt(1) - 48 == 0)) {
                cn.append("整");
            } else if ((nlist[1].charAt(0) - 48 == 0) && (nlist[1].charAt(1) - 48 != 0)) {
                if (numflag) {
                    cn.append(CHINESE_NUMBER[0]);
                }
                cn.append(CHINESE_NUMBER[nlist[1].charAt(1) - 48]);
                cn.append(MONEY_SERIES[2]);
            } else {
                if (nlist[1].charAt(0) - 48 != 0) {
                    cn.append(CHINESE_NUMBER[nlist[1].charAt(0) - 48]);
                    cn.append(MONEY_SERIES[3]);
                }
                if (nlist[1].charAt(1) - 48 != 0) {
                    cn.append(CHINESE_NUMBER[nlist[1].charAt(1) - 48]);
                    cn.append(MONEY_SERIES[2]);
                }
            }
        }
        return cn.toString();
    }

}

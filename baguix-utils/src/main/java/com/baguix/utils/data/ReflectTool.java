/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/25.
 */
package com.baguix.utils.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>反射工具</b><br>
 *
 * @author Scott(SG)
 */

@SuppressWarnings("unchecked")
public class ReflectTool<T> {
    /**
     * <b>把JavaBean转入map</b><br>
     *     说明：只支持int/long/doubl/boolean/String/Date/BigDecimal几种类型的属性进行数据转换<br>
     *     例如：<br>
     *         Map map=new HashMap();<br>
     *         ReflectTool<T> rt  = new ReflectTool<T>();<br>
     *         map = rt.bean2Map(obj);<br>
     *
     * @param obj javabean
     * @return 返回Map<属性名，属性值>
     */
    public Map simpleBean2Map(T obj){
        // 定义数据模型
        Map map = new HashMap();
        // 获取实体类的所有属性，返回Field数组
        Field[] field = obj.getClass().getDeclaredFields();
        // 遍历所有属性
        for (int i = 0; i < field.length; i++) {
            // 获取属性的名字
            String name = field[i].getName();
            if (name != null && name != "serialVersionUID") {
                // 将属性的首字符大写，方便构造get方法
                String getter = "get" + name.substring(0, 1).toUpperCase()
                        + name.substring(1);
                String isser = "is" + name.substring(0, 1).toUpperCase()
                        + name.substring(1);
                String type = field[i].getGenericType().toString();
                try {
                    // 反射开始
                    if (type.equals("class java.lang.String")) {
                        Method m = obj.getClass().getMethod(getter);
                        String value = (String) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value);
                        }
                    }
                    if (type.equals("class java.lang.Integer")) {
                        Method m = obj.getClass().getMethod(getter);
                        Integer value = (Integer) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("int")) {
                        Method m = obj.getClass().getMethod(getter);
                        Integer value = (Integer) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Short")) {
                        Method m = obj.getClass().getMethod(getter);
                        Short value = (Short) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("short")) {
                        Method m = obj.getClass().getMethod(getter);
                        Short value = (Short) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Long")) {
                        Method m = obj.getClass().getMethod(getter);
                        Long value = (Long) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("long")) {
                        Method m = obj.getClass().getMethod(getter);
                        Long value = (Long) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Double")) {
                        Method m = obj.getClass().getMethod(getter);
                        Double value = (Double) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("double")) {
                        Method m = obj.getClass().getMethod(getter);
                        Double value = (Double) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Boolean")|| type.equals("boolean")) {
                        Method m = obj.getClass().getMethod(isser);
                        Boolean value = (Boolean) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                    if (type.equals("class java.util.Date")) {
                        Method m = obj.getClass().getMethod(getter);
                        Date value = (Date) m.invoke(obj);
                        if (value != null) {
                            map.put(name, DateTool.getDateStr(value));
                        }
                    }
                    if (type.equals("java.math.BigDecimal")) {
                        Method m = obj.getClass().getMethod(getter);
                        BigDecimal value = (BigDecimal) m.invoke(obj);
                        if (value != null) {
                            map.put(name, value.toString());
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * <b>把JavaBean转入map</b><br>
     *     说明：只支持int/long/doubl/boolean/String/Date/BigDecimal几种类型的属性进行数据转换<br>
     *     例如：<br>
     *         Map map=new HashMap();<br>
     *         ReflectTool<T> rt  = new ReflectTool<T>();<br>
     *         map = rt.bean2Map(obj);<br>
     *     注意：<br>
     *         因为是生成模版，所以取值要这种格式<br>
     *         map.get("${field}")；<br>
     *
     * @param obj javabean
     * @return 返回Map<属性名，属性值>
     */
    public Map simpleBean2TemplateMap(T obj){
        // 定义数据模型
        Map map = new HashMap();
        // 获取实体类的所有属性，返回Field数组
        Field[] field = obj.getClass().getDeclaredFields();
        // 遍历所有属性
        for (int i = 0; i < field.length; i++) {
            // 获取属性的名字
            String name = field[i].getName();
            if (name != null && name != "serialVersionUID") {
                // 将属性的首字符大写，方便构造get方法
                String getter = "get" + name.substring(0, 1).toUpperCase()
                        + name.substring(1);
                String type = field[i].getGenericType().toString();
                try {
                    // 反射开始
                    if (type.equals("class java.lang.String")) {
                        Method m = obj.getClass().getMethod(getter);
                        String value = (String) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value);
                        }
                    }
                    if (type.equals("class java.lang.Integer")) {
                        Method m = obj.getClass().getMethod(getter);
                        Integer value = (Integer) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("int")) {
                        Method m = obj.getClass().getMethod(getter);
                        Integer value = (Integer) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Short")) {
                        Method m = obj.getClass().getMethod(getter);
                        Short value = (Short) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("short")) {
                        Method m = obj.getClass().getMethod(getter);
                        Short value = (Short) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Long")) {
                        Method m = obj.getClass().getMethod(getter);
                        Long value = (Long) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("long")) {
                        Method m = obj.getClass().getMethod(getter);
                        Long value = (Long) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Double")) {
                        Method m = obj.getClass().getMethod(getter);
                        Double value = (Double) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("double")) {
                        Method m = obj.getClass().getMethod(getter);
                        Double value = (Double) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("class java.lang.Boolean")||type.equals("boolean")) {
                        Method m = obj.getClass().getMethod(getter);
                        Boolean value = (Boolean) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                    if (type.equals("class java.util.Date")) {
                        Method m = obj.getClass().getMethod(getter);
                        Date value = (Date) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", DateTool.getDateStr(value));
                        }
                    }
                    if (type.equals("java.math.BigDecimal")) {
                        Method m = obj.getClass().getMethod(getter);
                        BigDecimal value = (BigDecimal) m.invoke(obj);
                        if (value != null) {
                            map.put("${" + name + "}", value.toString());
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}

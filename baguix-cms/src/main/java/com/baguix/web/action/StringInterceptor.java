package com.baguix.web.action;

import com.alibaba.fastjson.JSON;
import com.baguix.web.model.easyui.Messager;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 字符串拦截器，拦截提交的非法数据。
 * 例如：SQL语句、JS语句
 * Created by Scott on 2015/7/8.
 */

public class StringInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView model) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        Enumeration<String> names = request.getParameterNames();
        Messager m = new Messager();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                String v = values[i].toLowerCase();
                v = StringEscapeUtils.escapeEcmaScript(v);
                if (sqlValidate(v)) {
                    m.setMsg("提交的数据有异常，请清空不必要的格式后提交。");
                    m.setObj(null);
                    m.setSuccess(false);
                    String json = JSON.toJSONStringWithDateFormat(m, "yyyy-MM-dd HH:mm:ss");
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().print(json);
                }
            }
        }
    }

    //效验
    private static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
}

package com.baguix.web.action.weixin;

import com.baguix.utils.doc.XmlTool;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ZhiXin9i on 2016/2/22.
 */
public class WXUtils {

    public static Map<String,String> xml2Map(HttpServletRequest request){
        Map<String,String> map  = new HashMap<>();
        InputStream is = null;
        try {
            is = request.getInputStream();
            map = XmlTool.xml2Map(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  map;
    }

    public static String obj2XMl(Object obj){
        return XmlTool.obj2XmlStr(obj,"xml");
    }
}

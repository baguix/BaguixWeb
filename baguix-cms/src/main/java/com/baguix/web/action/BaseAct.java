package com.baguix.web.action;

import com.alibaba.fastjson.JSON;
import com.baguix.web.model.easyui.Messager;

/**
 * Created by ZhiXin9i on 2016/2/26.
 */
public class BaseAct {

    public String getMessager(String msg, Object o){
        Messager m = new Messager();
        try {
            m.setMsg(msg);
            m.setObj(o);
            m.setSuccess(true);
        }catch (Exception e){
            m.setMsg(e.getMessage());
            m.setObj(null);
            m.setSuccess(false);
        }
        String json = JSON.toJSONStringWithDateFormat(m, "yyyy-MM-dd HH:mm:ss");
        return json;
    }
}

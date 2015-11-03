/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/16.
 */
package com.baguix.web.common.db;

import com.baguix.utils.doc.XmlTool;
import com.baguix.web.common.cache.SysData;
import org.jdom2.Element;

import java.util.List;

/**
 * <b>读取classpath/dbsrc.xml并映射</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class DBSourceMapping {

    /**
     * <b>获取/WEB-INF/sysdata/dbsrc.xml中某个表对应方法的数据源配置</b><br>
     * @param bean  表映射类名，如：com.baguix.web.model.db.manctrl.TNavMenu
     * @param method 方法名，如：save、delete、update、saveOrUpdate、repair
     * @return 数据源，如：master,slave
     */
    public String getMap(String bean, String method){
        Element root = SysData.xml.get("dbsrc");
        String xpath = "/beans/bean[@name='"+bean+"']/method[@name='"+method+"']";
        List<Element> map;
        map = XmlTool.getElementsByXPath(root,xpath);
        if(map!=null && map.size()>0) {
            return map.get(0).getText();
        }
        return "";
    }

    public String getMapFromHibernate(String key){
        return  SysData.springHibernate.get(key);
    }
}

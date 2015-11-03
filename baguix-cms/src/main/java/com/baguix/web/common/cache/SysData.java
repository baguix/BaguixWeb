/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/17.
 */
package com.baguix.web.common.cache;

import com.baguix.web.model.page.manctrl.ImageInfo;
import com.baguix.web.model.page.manctrl.SiteInfo;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>系统数据cache</b><br>
 * 包括：xml文件，数据源配置，网站基本设置
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class SysData {
    // 所有 /WEB-INF/classes/sysdata/ 下的xml文件
    public static Map<String, Element> xml = new HashMap<>();
    // 数据源
    public static Map<String, String> springHibernate = new HashMap<>();
    // 系统参数
    public static SiteInfo siteInfo = new SiteInfo();
    public static ImageInfo imageInfo = new ImageInfo();
    // 摘要数据
    public static Map<String, String> overview = new HashMap<>();
    // 后台管理中心默认左树数据
    public static String lefttree = "";
    // 后台管理中心导航菜单
    public static HashMap<String, String> userNavMap = new HashMap<>();
    // 字典数据
    public static HashMap<String,String> dictMap = new HashMap<>();
    // 构造器
    public SysData() {
        loadData();
    }

    /**
     * 缓存数据加载
     */
    public void loadData() {
        // 系统 /WEB-INF/classes/sysdata/ 下的XML文件
        CacheI xmlData = new XmlCache();
        xmlData.loadData();
        // 载入 /WEB-INF/sprintconf/spring-hibernate.xml 中的数据源配置
        CacheI hiberanteData = new SpringHibernateCache();
        hiberanteData.loadData();
        // 处理  /WEB-INF/classes/sysdata/BeanMapping/siteinfo.xml 文件
        CacheI siData = new SiteInfoCache();
        siData.loadData();
        // 图片处理 /WEB-INF/classes/sysdata/BeanMapping/imageinfo.xml 文件
        CacheI iiData = new ImageInfoCache();
        iiData.loadData();
        // 载入 /WEB-INF/classes/sysdata/overview.properties 文件
        CacheI overviewData = new OverviewCache();
        overviewData.loadData();
        // 载入 /WEB-INF/classes/sysdata/lefttree.json 文件
        CacheI lefttreeData = new LeftTreeCache();
        lefttreeData.loadData();
    }

}

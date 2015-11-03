/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/6.
 */
package com.baguix.web.common.cache;

import com.baguix.utils.doc.XmlTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>spring-hibernate数据源缓存器</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class SpringHibernateCache implements CacheI {
    private static final Logger logger = LoggerFactory.getLogger("Spring-Hibernate数据源加载器");

    @Override
    public void loadData() {
        Map<String, String> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("springconf");
        sb.append(FileManager.getFileSeparator());
        sb.append("spring-hibernate.xml");
        Element root = XmlTool.getXMLRoot(sb.toString());
        /*
        Hibernate关于数据源的关键配置样例如下：
            <!-- 读写分离数据源配置 -->
            <bean id="dataSource" class="com.baguix.web.common.db.DynamicDataSource">
                <property name="targetDataSources">
                    <map key-type="java.lang.String">
                        <entry key="master" value-ref="masterDataSource"/>
                        <entry key="slave" value-ref="slaveDataSource"/>
                    </map>
                </property>
                <property name="defaultTargetDataSource" ref="masterDataSource"/>
            </bean>
        */
        String ns = "http://www.springframework.org/schema/beans";
        // 获得entry列表
        String xpath1 = "/ns:beans/ns:bean[@id='dataSource']/ns:property[@name='targetDataSources']/ns:map/ns:entry";
        List<Element> dbsrc = XmlTool.getElementsByXPath(root, xpath1, ns);
        // 获得defaultTargetDataSource
        String xpath2 = "/ns:beans/ns:bean[@id='dataSource']/ns:property[@name='defaultTargetDataSource']";
        Element defaultSrc = XmlTool.getElementsByXPath(root, xpath2, ns).get(0);
        String defaultDbSrc = defaultSrc.getAttributeValue("ref");

        // 遍历entry并生成 springHibernate
        if (dbsrc != null && dbsrc.size() > 0) {
            for (Element e : dbsrc) {
                String key = e.getAttributeValue("key").toString();
                map.put(key, key);
                // 如果是默认数据源，则给springHibernate增加一项默认数据源
                if (StringUtils.isEmpty(map.get("default")) && StringUtils.isNotEmpty(defaultDbSrc)
                        && defaultDbSrc.equals(e.getAttributeValue("value-ref"))) {
                    map.put("default", key);
                }
            }
            if (StringUtils.isEmpty(map.get("default"))) {
                logger.error("Load default hibernate datasource failure! Please check {}, like:<property name=\"defaultTargetDataSource\" ref=\"masterDataSource\"/>", sb.toString());
            } else {
                logger.info("Load successful (hibernate datasource) in {}", sb.toString());
            }
        } else {
            logger.error("Load failure (hibernate datasource)! Please check {}", sb.toString());
        }
        SysData.springHibernate = map;
    }

}

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/17.
 */
package com.baguix.web.common.db;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <b>数据源选择AOP切面</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

public class DataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    public void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();

        // 取表名
        String realClass = new String();
        Type type = target.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) target.getClass().getGenericSuperclass();
            realClass = parameterizedType.getActualTypeArguments()[0].toString().substring(6);
        }

        // 取数据源相关配置
        DBSourceMapping dbSrcMap = new DBSourceMapping();
        String src = dbSrcMap.getMap(realClass, method);
        String srcHibernate = dbSrcMap.getMapFromHibernate(src);

        if (StringUtils.isNotEmpty(realClass)) {
            // 处理1: 如果dbsrc.xml有配置，则按xml配置选择数据源。
            if (StringUtils.isNotEmpty(method)) {
                if (StringUtils.isNotEmpty(src) && StringUtils.isNotEmpty(srcHibernate) && StringUtils.equals(src,srcHibernate)) {
                    DynamicDataSourceHolder.putDataSource(src);
                    logger.debug("1使用数据源：{}", src);
                }
                else {
                    DynamicDataSourceHolder.putDataSource(dbSrcMap.getMapFromHibernate("default"));
                    logger.debug("2使用数据源：{}", dbSrcMap.getMapFromHibernate("default"));
                }
            }
        }
        // 处理2: 读取选择默认数据源，即(master)。参考：<property name="defaultTargetDataSource" ref="masterDataSource"/>的配置。
        else {
            DynamicDataSourceHolder.putDataSource(dbSrcMap.getMapFromHibernate("default"));
            logger.debug("3使用数据源：{}",dbSrcMap.getMapFromHibernate("default"));
        }
    }
}
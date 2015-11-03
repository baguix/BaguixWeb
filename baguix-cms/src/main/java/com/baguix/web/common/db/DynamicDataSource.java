/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/17.
 */
package com.baguix.web.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <b>动态数据源</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class DynamicDataSource  extends AbstractRoutingDataSource{

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }
}

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/17.
 */

package com.baguix.web.common.db;

/**
 * <b>动态数据源</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class DynamicDataSourceHolder {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }
    public static String getDataSouce() {
        return holder.get();
    }
}

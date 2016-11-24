package com.baguix.web.common.db;

import org.flywaydb.core.Flyway;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;

/**
 * Created by Scott on 2016/5/2.
 */
public class FlywayMigrate {
    private DataSource dataSource;

    public void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        // 设置接受flyway进行版本管理的多个数据库
        flyway.setSchemas("flywaydemo");
        // 设置存放flyway metadata数据的表名
        flyway.setTable("schema_version");
        // 设置flyway扫描sql升级脚本、java升级脚本的目录路径或包路径
        flyway.setLocations("flyway/migrations", "com.baguix.web.common.db.migrations");
        // 设置sql脚本文件的编码
        flyway.setEncoding("UTF-8");
        flyway.migrate();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        // SQL文件名的格式：V开头+版本号+双下划线+描述
        flyway.setDataSource("jdbc:mysql://localhost:3306/sys?" +
                "useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull", "root", "");
        flyway.baseline();
        //flyway.migrate();
    }

}

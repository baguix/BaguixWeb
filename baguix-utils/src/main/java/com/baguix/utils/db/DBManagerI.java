/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/13.
 */
package com.baguix.utils.db;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * <b>数据库管理抽象类/b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public interface DBManagerI {
    /**
     * <b>获取数据库连接</b><br>
     *
     * @param dbHost     主机地址
     * @param dbPort     主机端口，留空则默认3306
     * @param dbName     数据库名，留空则不连数据库
     * @param dbUser     数据库连接用户
     * @param dbPassword 数据库链接密码
     * @return 连接（Connection）
     * @throws Exception
     */
    Connection getConn(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception;

    /**
     * <b>创建数据库</b><br>
     *
     * @param dbHost     主机地址
     * @param dbPort     主机端口，留空则默认3306
     * @param dbName     数据库名，留空则建立数据库
     * @param dbUser     数据库连接用户
     * @param dbPassword 数据库链接密码
     * @throws Exception
     */
    void createDb(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception;

    /**
     * <b>创建表</b><br>
     *
     * @param dbHost     主机地址
     * @param dbPort     主机端口
     * @param dbName     数据库名，留空则建立数据库
     * @param dbUser     数据库连接用户
     * @param dbPassword 数据库链接密码
     * @param sqlList    数据库DDL语句
     * @throws Exception
     */
    void createTable(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, List<String> sqlList) throws Exception;

    /**
     * <b>执行SQL</b><br>
     *
     * @param dbHost     主机地址
     * @param dbPort     主机端口
     * @param dbName     数据库名，留空则建立数据库
     * @param dbUser     数据库连接用户
     * @param dbPassword 数据库链接密码
     * @param ddlList    DDL语句列表
     * @throws IOException
     */
    void executeSQL(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, List<String> ddlList) throws Exception;

    /**
     * 从文件中读取sql。“/*” 或 “#” 开头为注释，“;”为sql结束。
     *
     * @param fileName sql文件地址
     * @return list of sql
     * @throws Exception
     */
    List<String> readSql(String fileName) throws Exception;

    /**
     * <b>备份数据库</b><br>
     *
     * @param binPath    执行命令的地址
     * @param backupPath 备份文件保存目录
     * @param dbHost     主机地址
     * @param dbPort     主机端口
     * @param dbName     数据库名
     * @param dbUser     数据库连接用户
     * @param dbPassword 数据库链接密码
     * @return 系统消息（String）
     * @throws IOException
     */
    String dbBackup(String binPath, String backupPath, String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws IOException;

    /**
     * <b>还原数据库</b><br>
     *
     * @param binPath     执行命令的地址
     * @param restoreFile 备份文件的路径
     * @param dbHost      主机地址
     * @param dbPort      主机端口
     * @param dbName      数据库名
     * @param dbUser      数据库连接用户
     * @param dbPassword  数据库链接密码
     * @throws Exception
     */
    void dbRestore(String binPath, String restoreFile, String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) throws Exception;
}

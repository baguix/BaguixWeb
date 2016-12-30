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

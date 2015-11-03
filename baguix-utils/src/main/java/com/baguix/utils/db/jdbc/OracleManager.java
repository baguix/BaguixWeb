/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/3.
 */
package com.baguix.utils.db.jdbc;

import com.baguix.utils.data.Constants;
import com.baguix.utils.data.NowDateTool;
import com.baguix.utils.db.DBManagerI;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>数据库操作类</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class OracleManager implements DBManagerI {

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
    public Connection getConn(String dbHost, String dbPort,
                                     String dbName, String dbUser, String dbPassword) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://");
        sb.append(dbHost);
        sb.append(":");
        if (StringUtils.isEmpty(dbPort)) {
            sb.append("3306");
        } else {
            sb.append(dbPort);
        }
        if (StringUtils.isNotEmpty(dbName)) {
            sb.append("/");
            sb.append(dbName);
        }
        sb.append("?user=");
        sb.append(dbUser);
        sb.append("&password=");
        sb.append(dbPassword);
        sb.append("&characterEncoding=utf8");
        return DriverManager.getConnection(sb.toString());
    }

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
    public void createDb(String dbHost, String dbPort, String dbName,
                                String dbUser, String dbPassword) throws Exception {
        Connection conn = getConn(dbHost, dbPort, "", dbUser, dbPassword);
        if (StringUtils.isNotEmpty(dbName)) {
            Statement stat = conn.createStatement();
            String sql = "drop database if exists " + dbName;
            stat.execute(sql);
            sql = "create database " + dbName + " CHARACTER SET UTF8";
            stat.execute(sql);
            stat.close();
            conn.close();
        }
    }

    /**
     * <b>更改数据库编码</b><br>
     *
     * @param dbHost     主机地址
     * @param dbPort     主机端口
     * @param dbName     数据库名，留空则建立数据库
     * @param dbUser     数据库连接用户
     * @param dbPassword 数据库链接密码
     * @throws Exception
     */
    public void changeDbCharsetUTF8(String dbHost, String dbPort,
                                           String dbName, String dbUser, String dbPassword) throws Exception {
        Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
        Statement stat = conn.createStatement();
        String sql = "ALTER DATABASE " + dbName + " CHARACTER SET UTF8";
        stat.execute(sql);
        stat.close();
        conn.close();
    }

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
    public void createTable(String dbHost, String dbPort, String dbName,
                                   String dbUser, String dbPassword, List<String> sqlList)
            throws Exception {
        executeSQL(dbHost, dbPort, dbName, dbUser, dbPassword, sqlList);
    }

    public void executeSQL(String dbHost, String dbPort, String dbName,
                                  String dbUser, String dbPassword, List<String> ddlList)
            throws Exception {
        Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
        Statement stat = conn.createStatement();
        for (String dllsql : ddlList) {
            stat.execute(dllsql);
        }
        stat.close();
        conn.close();
    }

    /**
     * 读取sql语句。“/*” 或 “#” 开头为注释，“;”为sql结束。
     *
     * @param fileName sql文件地址
     * @return list of sql
     * @throws Exception
     */
    public List<String> readSql(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), Constants.UTF8));
        List<String> sqlList = new ArrayList<String>();
        StringBuilder sqlSb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            if (s.startsWith("/*") || s.startsWith("#")
                    || (s != null && !"".equals(s))) {
                continue;
            }
            if (s.endsWith(";")) {
                sqlSb.append(s);
                sqlSb.setLength(sqlSb.length() - 1);
                sqlList.add(sqlSb.toString());
                sqlSb.setLength(0);
            } else {
                sqlSb.append(s);
            }
        }
        br.close();
        return sqlList;
    }

    public String dbBackup(String mysqlBinPath, String backupPath, String dbHost, String dbPort, String dbName,
                                  String dbUser, String dbPassword) throws IOException {
        String backupFile = backupPath + dbName + "_" + NowDateTool.now("yyyy-MM-dd-HH-mm-ss-SSS") + ".sql";
        String cmd = mysqlBinPath + "mysqldump --host={0} --port={1} --user={2} --password={3} --opt {4} > \"{5}\"";
        Object[] arr = {dbHost, dbPort, dbUser, dbPassword, dbName, backupFile};
        String backup = MessageFormat.format(cmd, arr);
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd /c " + backup);
        return backupFile;
    }

    public void dbRestore(String mysqlBinPath, String restoreFile, String dbHost, String dbPort, String dbName,
                                 String dbUser, String dbPassword) throws Exception {
        String mysql = "mysql " + "-h" + dbHost + " -u" + dbUser + " -p" + dbPassword + " " + dbName + " < " + restoreFile;
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd /c " + mysql);
    }
}

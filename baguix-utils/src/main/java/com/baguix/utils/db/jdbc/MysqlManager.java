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
public class MysqlManager implements DBManagerI {

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

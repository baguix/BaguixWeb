/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/3.
 */
package com.baguix.utils.db.jdbc;

import com.baguix.utils.db.DBManagerI;
import java.io.IOException;

/**
 * <b>数据库操作类</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class OracleManager implements DBManagerI {

    public String dbBackup(String mysqlBinPath, String backupPath, String dbHost, String dbPort, String dbName,
                                  String dbUser, String dbPassword) throws IOException {
        //TODO
        return null;
    }

    public void dbRestore(String mysqlBinPath, String restoreFile, String dbHost, String dbPort, String dbName,
                                 String dbUser, String dbPassword) throws Exception {
        //TODO
    }
}

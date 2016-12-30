package com.baguix.utils.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/** 
* mysql Tester. 
* 
* @author Scott(SG)
*/ 
public class MysqlManagerTest {
    private static final Logger logger = LoggerFactory.getLogger(MysqlManagerTest.class);
    private DBManagerI mysql;
    @Before
    public void before() throws Exception {
        mysql = DBManagerFactory.getDBManager("mysql");
    } 
    
    @After
    public void after() throws Exception { 
    }

    /**
     *
     * Method: dbBackup(String backupPath, String dbHost, String dbPort, String dbName, String dbUser, String dbPassword)
     *
     */
    @Ignore
    public void testDbBackup() throws Exception {
        String msg = mysql.dbBackup("D:\\MySQL\\mysql-5.6.25-winx64-master\\bin\\","d:\\", "localhost","3307","sss","root","");
        File file = new File(msg);
        // 停留时间给数据库导出。
        Thread.sleep(1000);
        assertTrue(file.exists());
    }

    /**
     *
     * Method: dbRestore(String backupPath, String dbHost, String dbPort, String dbName, String dbUser, String dbPassword)
     *
     */
    @Ignore
    public void testDbRestore() throws Exception {
        String msg = mysql.dbBackup("D:\\MySQL\\mysql-5.6.25-winx64-master\\bin\\","d:\\", "localhost","3307","sss","root","");
        logger.info(msg);
    }
    } 

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
     * Method: getConn(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) 
     * 
     */
    @Ignore
    public void testGetConn() throws Exception { 
        //TODO: Test goes here... 
    } 
    
    /**
     * 
     * Method: createDb(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) 
     * 
     */
    @Ignore
    public void testCreateDb() throws Exception { 
        mysql.createDb("localhost", "3307", "sss", "root", "");
    } 
    
    /**
     * 
     * Method: changeDbCharset(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword) 
     * 
     */
    @Ignore
    public void testChangeDbCharset() throws Exception { 
        //TODO: Test goes here... 
    } 
    
    /**
     * 
     * Method: createTable(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, List<String> sqlList) 
     * 
     */
    @Ignore
    public void testCreateTable() throws Exception {
        String sql1 = "CREATE TABLE IF NOT EXISTS `ss_category` (\n" +
                "  `CATEGORY_ID` varchar(36) NOT NULL,\n" +
                "  `CATEGORY_CTIME` datetime DEFAULT NULL,\n" +
                "  `CATEGORY_DESC` longtext,\n" +
                "  `CATEGORY_HOMENUM` int(11) DEFAULT NULL,\n" +
                "  `CATEGORY_ISDEL` char(1) DEFAULT NULL,\n" +
                "  `CATEGORY_ISONLINE` char(1) DEFAULT NULL,\n" +
                "  `CATEGORY_KEYWORD` longtext,\n" +
                "  `CATEGORY_LEVEL` int(11) DEFAULT NULL,\n" +
                "  `CATEGORY_MAXARTICLERANK` bigint(20) DEFAULT NULL,\n" +
                "  `CATEGORY_MAXSUBRANK` bigint(20) DEFAULT NULL,\n" +
                "  `APPDICT_MTIME` datetime DEFAULT NULL,\n" +
                "  `CATEGORY_OPENMODE` varchar(20) DEFAULT NULL,\n" +
                "  `CATEGORY_PARENTIDSTR` longtext,\n" +
                "  `CATEGORY_PARENTNAMESTR` longtext,\n" +
                "  `CATEGORY_PROPERTIES` longtext,\n" +
                "  `CATEGORY_RANK` bigint(20) DEFAULT NULL,\n" +
                "  `CATEGORY_SHOWMODE` longtext,\n" +
                "  `shownum` int(11) NOT NULL,\n" +
                "  `CATEGORY_THUMB` varchar(200) DEFAULT NULL,\n" +
                "  `CATEGORY_THUMBSTR` longtext,\n" +
                "  `CATEGORY_TITLE` varchar(200) DEFAULT NULL,\n" +
                "  `CATEGORY_TYPE` varchar(200) DEFAULT NULL,\n" +
                "  `CATEGORY_URL` varchar(200) DEFAULT NULL,\n" +
                "  `CATEGORY_PID` varchar(36) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`CATEGORY_ID`),\n" +
                "  KEY `FK_5m83k2ss6y7qgqphpxf3btopa` (`CATEGORY_PID`),\n" +
                "  CONSTRAINT `FK_5m83k2ss6y7qgqphpxf3btopa` FOREIGN KEY (`CATEGORY_PID`) REFERENCES `ss_category` (`CATEGORY_ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        String sql2 =  "CREATE TABLE IF NOT EXISTS `ss_article` (\n" +
                "  `ARTICLE_ID` varchar(36) NOT NULL,\n" +
                "  `ARTICLE_ABSTRACTS` varchar(200) DEFAULT NULL,\n" +
                "  `ARTICLE_CATEGORYID` longtext,\n" +
                "  `ARTICLE_CATEGORYIDS` longtext,\n" +
                "  `ARTICLE_CATEGORYNAME` longtext,\n" +
                "  `ARTICLE_CONTENT` longtext,\n" +
                "  `ARTICLE_CTIME` datetime DEFAULT NULL,\n" +
                "  `ARTICLE_FILE` longtext,\n" +
                "  `ARTICLE_FILENAME` longtext,\n" +
                "  `ARTICLE_IMG` longtext,\n" +
                "  `ARTICLE_KEYWORD` longtext,\n" +
                "  `APPDICT_MTIME` datetime DEFAULT NULL,\n" +
                "  `ARTICLE_PAGEWORDNUM` bigint(20) DEFAULT NULL,\n" +
                "  `ARTICLE_PROPERTIES` longtext,\n" +
                "  `ARTICLE_RANK` int(11) DEFAULT NULL,\n" +
                "  `ARTICLE_READNUM` bigint(20) DEFAULT NULL,\n" +
                "  `ARTICLE_SOURCE` varchar(100) DEFAULT NULL,\n" +
                "  `ARTICLE_STATE` char(1) DEFAULT NULL,\n" +
                "  `ARTICLE_THUMBFILE` longtext,\n" +
                "  `ARTICLE_TITLE` varchar(200) DEFAULT NULL,\n" +
                "  `ARTICLE_TITLECOLOR` varchar(15) DEFAULT NULL,\n" +
                "  `ARTICLE_TYPE` varchar(50) DEFAULT NULL,\n" +
                "  `ARTICLE_UPIMGSTR` longtext,\n" +
                "  `ARTICLE_URL` varchar(200) DEFAULT NULL,\n" +
                "  `ARTICLE_WRITER` varchar(100) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ARTICLE_ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        List<String> sqllist = new ArrayList<>();
        sqllist.add(sql1);
        sqllist.add(sql2);
        mysql.createTable("localhost","3307","sss","root","",sqllist);
    }


    /**
     *
     * Method: executeDDL(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, String domain, String cxtPath, String port)
     *
     */
    @Ignore
    public void testExecuteDDL() throws Exception {
        List<String> sqllist = new ArrayList<>();
        String sql =  "DROP TABLE ss_article;";
        sqllist.add(sql);
        mysql.executeSQL("localhost","3307","sss","root","",sqllist);
    }
    /**
     * 
     * Method: updateConfig(String dbHost, String dbPort, String dbName, String dbUser, String dbPassword, String domain, String cxtPath, String port) 
     * 
     */ 
    @Test
    public void testUpdateConfig() throws Exception { 
        //TODO: Test goes here... 
    } 
    
    /**
     * 
     * Method: readSql(String fileName) 
     * 
     */ 
    @Test
    public void testReadSql() throws Exception { 
        //TODO: Test goes here... 
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

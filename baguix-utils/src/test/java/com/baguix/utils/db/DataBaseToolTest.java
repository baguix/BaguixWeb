package com.baguix.utils.db;

import com.baguix.utils.db.jdbc.DataBaseTool;
import org.junit.Test;

/**
 * Created by Administrator on 2016/12/30.
 */
public class DataBaseToolTest {
    @Test
    public void testDBT(){
        String USERNAME = "root";
        // 数据库密码
        String PASSWORD = "nnssl2008wgzx";
        // 驱动信息
        String DRIVER = "com.mysql.jdbc.Driver";
        // 数据库地址
        String URL = "jdbc:mysql://192.168.254.100:3306/jfinal?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

//        DataBaseTool conn = new DataBaseTool(DRIVER, URL, USERNAME, PASSWORD);
//
//		String sql = "select * from tuser";
//		ResultSet rs = conn.query(sql);
//
//		String sql = "select * from tuser where name like ?";
//		ResultSet rs = conn.query(sql,"王五");
//
//		String sql = "select * from tuser where age>? and age<?";
//		ResultSet rs = conn.query(sql,10,30);
//
//		String sql = "select * from tuser where age>? and age<?";
//		ResultSet rs = conn.query(sql,1.0,20.521551215);
//
//		String sql = "select * from tuser where rate>? and rate<?";
//		ResultSet rs = conn.query(sql,0.1,1.2);
//
//		String sql = "select * from tuser where birthday>? and birthday<?";
//		ResultSet rs = conn.query(sql,"2016-12-25","2017-12-31");
//
//		String sql = "select * from tuser where birthday>? and birthday<?";
//		ResultSet rs = conn.query(sql,"2015-5-01","2017-12-01");
//
//
//		while (rs.next()) {
//			String id = rs.getString("uid");
//			String name = rs.getString("name");
//			System.out.println(id+" "+name);
//		}
//
//		String sql = "update tuser set age=80,rate=? where name like ?";
//		conn.update(sql,1.333,"%张%");
//
//		conn.createDatabase("cread");
//
//		List<String> sql = conn.readSqlFromFile("c:/bak.sql");
//		for (String s : sql) {
//			System.out.println(s);
//		}
//
//      conn.execSqlFromFile("c:/bak.sql");
//        conn.closeAll();
    }
}

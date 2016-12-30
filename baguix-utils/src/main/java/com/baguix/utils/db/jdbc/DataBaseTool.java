package com.baguix.utils.db.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>数据库工具</b><br>
 *
 * @author Scott(SG)
 */

public class DataBaseTool {
    private Logger logger = LoggerFactory.getLogger(DataBaseTool.class);

    // 数据库用户名
    private String username = "";
    // 数据库密码
    private String password = "";
    // 数据库地址
    private String url = "";
    private Connection connection;
    private PreparedStatement pstmt;
    private Statement stat;
    private ResultSet rs;

    public DataBaseTool(String driver, String url, String username, String password) {
        try {
            this.url = url;
            this.username = username;
            this.password = password;
            Class.forName(driver);
            getConnection();
            logger.info("数据库连接（无连接池）成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * <b>创建数据库</b>
     *
     * @param dbname 数据库名
     */
    public void createDatabase(String dbname) {
        try {
            if (dbname != null) {
                stat = connection.createStatement();
                String sql = "drop database if exists " + dbname;
                stat.execute(sql);
                sql = "create database " + dbname + " CHARACTER SET UTF8";
                stat.execute(sql);
                logger.info("新数据库：" + dbname + "创建成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>从文件读取sql语句。</b><br>
     * 注意：<br>
     * “/*” 或 “#” 开头为注释行，直接忽略，因此,/*跨行注释会出错；<br>
     * “;”为SQL单句结束。<br>
     *
     * @param fileName
     * @return List<String>sql列表
     */
    public List<String> readSqlFromFile(String fileName) {
        List<String> sqlList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            StringBuilder sqlSb = new StringBuilder();
            String s = null;
            while ((s = br.readLine()) != null) {
                if ((s != null && !"".equals(s)) && (s.startsWith("/*") || s.startsWith("#"))) {
                    continue;
                }
                if (s.endsWith(";")) {
                    sqlSb.append(s);
                    sqlList.add(sqlSb.toString());
                    sqlSb.setLength(0);
                } else {
                    sqlSb.append(s);
                }
            }
            br.close();
        } catch (IOException e) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return sqlList;
    }

    /**
     * <b>从文件执行sql语句。</b><br>
     * 注意：<br>
     * “/*” 或 “#” 开头为注释行，直接忽略，因此,/*跨行注释会出错；<br>
     * “;”为SQL单句结束。<br>
     *
     * @param fileName
     */
    public void execSqlFromFile(String fileName) {
        List<String> sqls = readSqlFromFile(fileName);
        execSqlList(sqls);
    }

    /**
     * <b>批量执行SQL</b>
     *
     * @param sqllist
     */
    public void execSqlList(List<String> sqllist) {
        try {
            stat = connection.createStatement();
            logger.info("==================== Execute SQL ====================");
            for (String sql : sqllist) {
                logger.info(sql);
                stat.execute(sql);
            }
            logger.info("=============== Execute SQL complete. ===============");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>用单句SQL查询</b>
     *
     * @param sql
     *            SQL语句
     * @param params
     *            参数
     * @return ResultSet 数据结果集
     */
    public ResultSet query(String sql, Object... params) {
        if (connection != null) {
            try {
                pstmt = connection.prepareStatement(sql);
                if (params != null && params.length > 0) {
                    fillSql(pstmt, params);
                }
                logger.info(pstmtSQL(pstmt));
                rs = pstmt.executeQuery();
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * <b>用单句SQL更新</b>
     *
     * @param sql
     *            SQL语句
     * @param params
     *            参数
     * @return ResultSet 数据结果集
     */
    public int update(String sql, Object... params) {
        if (connection != null) {
            try {
                pstmt = connection.prepareStatement(sql);
                if (params != null && params.length > 0) {
                    fillSql(pstmt, params);
                }
                logger.info(pstmtSQL(pstmt));
                return pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * 关闭所有数据库连接
     *
     * @return boolean 是否成功
     */
    public boolean closeAll() {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (stat != null)
                stat.close();
            if (connection != null)
                connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否日期
     * @param str 字符串
     * @return boolean是否日期
     */
    private boolean isdate(String str) {
        Pattern pattern = Pattern.compile(
                "^([0-9]{4})((0([1-9]{1}))|(1[0-2]))(([0-2]([0-9]{1}))|(3[0|1]))(([0-1]([0-9]{1}))|(2[0-4]))([0-5]([0-9]{1}))([0-5]([0-9]{1}))");
        Matcher matcher = pattern.matcher(str);
        boolean bool = matcher.matches();
        return bool;
    }

    /**
     * <b>填充SQL语句</b>
     *
     * @param pstmt 预处理语句
     * @param params 参数
     */
    private void fillSql(PreparedStatement pstmt, Object[] params) {
        try{
            for (int i = 0; i < params.length; i++) {
                Object p = params[i];
                if (p instanceof Integer) {
                    pstmt.setInt(i + 1, Integer.parseInt(p.toString()));
                } else if (p instanceof Long) {
                    pstmt.setLong(i + 1, Long.parseLong(p.toString()));
                } else if (p instanceof Double) {
                    pstmt.setDouble(i + 1, Double.parseDouble(p.toString()));
                } else if (isdate(p.toString())) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try {
                        Date date = format.parse(p.toString());
                        pstmt.setDate(i + 1, new java.sql.Date(date.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    pstmt.setString(i + 1, p.toString());
                }
            }
        }catch(NumberFormatException | SQLException e){
            e.printStackTrace();

        }
    }

    /**
     * <b>从预处理语句(PreparedStatement)中提取完整的SQL语句</b>
     * @param pstmt 预编译语句
     * @return String SQL语句
     */
    private String pstmtSQL(PreparedStatement pstmt) {
        String str = pstmt.toString();
        int index = str.indexOf(": ") + 1;
        return str.substring(index);
    }
}


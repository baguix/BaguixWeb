/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/13.
 */
package com.baguix.utils.db;

import com.baguix.utils.db.jdbc.MysqlManager;
import com.baguix.utils.db.jdbc.OracleManager;
import org.apache.commons.lang3.StringUtils;

/**
 * <b>类的说明</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class DBManagerFactory {

    public static DBManagerI getDBManager(String dbname) {
        if (StringUtils.isNotEmpty(dbname)) {
            dbname = dbname.toLowerCase();
            switch (dbname) {
                case "mysql":
                    new MysqlManager();
                    break;
                case "oracle":
                    new OracleManager();
                    break;
            }
        }
        return new MysqlManager();
    }
}

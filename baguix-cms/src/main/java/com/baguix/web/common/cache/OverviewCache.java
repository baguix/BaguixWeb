/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/6.
 */
package com.baguix.web.common.cache;

import com.baguix.utils.doc.PropertiesTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>Overview缓存器</b><br>
 * 说明：载入 /WEB-INF/classes/sysdata/overview.properties 文件
 * @author Scott(SG)
 * @since 1.0
 */
public class OverviewCache implements CacheI{
    private static final Logger logger = LoggerFactory.getLogger("OverView加载器");

    @Override
    public void loadData() {
        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("sysdata");
        sb.append(FileManager.getFileSeparator());
        sb.append("overview.properties");
        PropertiesTool pt = new PropertiesTool();
        pt.mapFile(SysData.overview, sb.toString());
        if (SysData.overview != null && SysData.overview.size() > 0) {
            logger.info("Load successful {}", sb.toString());
        } else {
            logger.error("Load failure {}", sb.toString());
        }
    }
}

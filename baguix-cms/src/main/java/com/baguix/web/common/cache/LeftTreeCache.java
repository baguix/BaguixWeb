/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/6.
 */
package com.baguix.web.common.cache;

import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>后台管理系统左树缓存器</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class LeftTreeCache implements CacheI {

    private static final Logger logger = LoggerFactory.getLogger("LeftTree加载器");

    @Override
    public void loadData() {
        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("sysdata");
        sb.append(FileManager.getFileSeparator());
        sb.append("lefttree.json");
        String json = FileManager.readStrFromFile(sb.toString(), "UTF-8");
        SysData.lefttree = StringUtils.remove(json, "\t|\r|\n");
        logger.debug("管理后台LeftTree缓存成功.");
    }
}

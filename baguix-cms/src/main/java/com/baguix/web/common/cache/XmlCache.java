/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/6.
 */
package com.baguix.web.common.cache;

import com.baguix.utils.doc.XmlTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>XMl加载器</b><br>
 * 说明: 给SysDataCache加载系统 /WEB-INF/classes/sysdata/ 目录下的所有XML文件，Key为文件名
 * @author Scott(SG)
 * @since 1.0
 */
public class XmlCache implements CacheI {
    private static final Logger logger = LoggerFactory.getLogger("XML加载器");

    @Override
    public void loadData() {
        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("sysdata");
        SysData.xml = XmlTool.getFolderXMLRoot(sb.toString());
        if (SysData.xml != null && SysData.xml.size() > 0) {
            logger.info("Load successful {}*.xml", sb.toString() + FileManager.getFileSeparator());
        } else {
            logger.error("Load failure {}*.xml", sb.toString() + FileManager.getFileSeparator());
        }
    }
}

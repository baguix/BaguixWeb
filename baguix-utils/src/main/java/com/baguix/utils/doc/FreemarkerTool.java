/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.doc;

import com.baguix.utils.file.FileManager;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * <b>Freemarker(.ftl)模版文件工具类</b><br>
 *
 * @author Scott(SG)
 */
public class FreemarkerTool {
    private Configuration cfg = new Configuration();

    /**
     * <b>根据模版生成文件</b><br>
     *
     * @param tempalateurl, 格式：D:\\XX\\src\\com\\
     * @param ftl,          格式：abc.ftl
     * @param root,         Freemarker的数据模型
     * @param fileurl,      格式：D:\\XX\\src\\com\\
     * @param file,         格式：abc.java
     * @throws IOException
     * @throws TemplateException
     */

    public void genFileByFTL(String tempalateurl, String ftl, Map root, String fileurl, String file) throws IOException, TemplateException {
        cfg.setDirectoryForTemplateLoading(new File(tempalateurl));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");

        Template temp = cfg.getTemplate(ftl);
        temp.setEncoding("UTF-8");
        FileManager.newFolder(fileurl);
        File afile = new File(fileurl + FileManager.getFileSeparator() + file);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afile), "UTF-8"));
        temp.process(root, out);
        out.flush();
        out.close();
    }

    public void genFileByFTL(String tempalate, Map root, String file) throws IOException, TemplateException {
        if(tempalate!=null && !"".equals(tempalate) && root!=null && file!=null && !"".equals(file)) {
            File tempfile = new File(tempalate);
            File ffile = new File(file);
            File tempfolder = tempfile.getParentFile();
            File ffolder = ffile.getParentFile();
            cfg.setDirectoryForTemplateLoading(tempfolder);
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            cfg.setDefaultEncoding("UTF-8");

            Template temp = cfg.getTemplate(tempfile.getName());
            temp.setEncoding("UTF-8");
            FileManager.newFolder(ffolder);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ffile), "UTF-8"));
            temp.process(root, out);
            out.flush();
            out.close();
        }
    }
}

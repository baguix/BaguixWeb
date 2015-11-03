/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.doc;

import com.baguix.utils.security.EnDecodeTool;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <b>properties文件操作类</b><br>
 *
 * @author Scott(SG)
 */
public class PropertiesTool {
    /**
     * <b>把*.properties文件映射称HashMap。</b>
     * @param file
     * @return map
     */
    public Map<String,String> mapFile(String file){
        Map<String, String> map = new HashMap<String, String>();
        if(file!=null && !"".equals(file)) {
            Properties prop = new Properties();
            FileInputStream fs = null;
            InputStream in = null;
            try {
                fs = new FileInputStream(file);
                in = new BufferedInputStream(fs);
                prop.load(in);
                Enumeration en = prop.propertyNames();
                while (en.hasMoreElements()) {
                    String key = (String) en.nextElement();
                    String value = prop.getProperty(key);
                    map.put(key, value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fs != null) {
                        fs.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * <b>把*.properties文件映射称HashMap。</b><br>
     *     说明：允许map有初始值，未找到响应的*.properties文件则按初始值返回。
     * @param map
     * @param file
     * @return map
     */
    public Map<String,String> mapFile(Map<String,String> map, String file){
        if(file!=null && !"".equals(file)) {
            Properties prop = new Properties();
            FileInputStream fs = null;
            InputStream in = null;
            File f = new File(file);
            if(f.exists()) {
                try {
                    fs = new FileInputStream(f);
                    in = new BufferedInputStream(fs);
                    prop.load(in);
                    Enumeration en = prop.propertyNames();
                    while (en.hasMoreElements()) {
                        String key = (String) en.nextElement();
                        String value = prop.getProperty(key);
                        map.put(key, value);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fs != null) {
                            fs.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return map;
    }

    /**
     * <b>从HashMap把数据写入*.properties文件。</b>
     * @param file
     * @return
     */
    public void map2File(String file, Map<String,String> map, String comment){
        if(file!=null && !"".equals(file) && map!=null) {
            Properties prop = new Properties();
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(file);
                prop.load(is);
                os = new FileOutputStream(file);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    prop.setProperty(entry.getKey(), entry.getValue());
                }
                prop.store(os, comment);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * <b>利用Freemarker模版，从HashMap把数据写入*.properties文件。</b>
     * @param file
     * @return
     */
    public void map2FileByFreemarker(String file, String template, Map<String,String> map){
        if(file!=null && !"".equals(file) &&template!=null & !"".equals(template) && map!=null) {
            FreemarkerTool ft = new FreemarkerTool();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map.put(entry.getKey(), EnDecodeTool.Str2Unicode(entry.getValue()));
            }
            try {
                ft.genFileByFTL(template, map, file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}

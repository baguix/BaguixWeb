/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.doc;

import com.baguix.utils.data.ReflectTool;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <b>Word(.doc)文件生成器</b><br>
 *     必须制作一个.doc的模版文件，根据传入的obj进行渲染。<br>
 *     例如： WordGenerator<Bean> wg = new WordGenerator(template, bean);
 *
 * @author Scott(SG)
 */
@SuppressWarnings("unchecked")
public class WordGenerator<T> {
    private String template;
    private T obj;

    // 构造方法
    public WordGenerator(String template, T obj) {
        this.template = template;
        this.obj = obj;
    }

    /**
     * <b>根据Model对象渲染Word模版文件</b><br/>
     * @return POI文档模型（HWPFDocument）
     */
    public HWPFDocument getDocByTemplate() {
        // 定义数据模型
        ReflectTool<T> rt  = new ReflectTool<T>();
        Map map = rt.simpleBean2Map(obj);
        try {
            HWPFDocument wd = writeDoc(map);
            if(wd != null){
                return wd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <b>得到输出流（用于文件下载）</b><br/>
     * @return 文档模型
     */
    public ByteArrayOutputStream genDoc() {
        //完成反射后，将数据写入word文档
        HWPFDocument wd = getDocByTemplate();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            if(wd != null){
                wd.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return os;
    }

    /**
     * <b>得到输出流（用于文件下载）</b><br/>
     * @return 文档模型
     */
    public ByteArrayOutputStream getDocOS(HWPFDocument wd) {
        //完成反射后，将数据写入word文档
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            if(wd != null){
                wd.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return os;
    }

    /**
     * <b>填充信息</b><br>
     * @param hdt Word文档POI模型
     * @param model 文档中想替换的内容
     * @param value 替换为的内容
     */
    public void replaceText(HWPFDocument hdt, String model, String value){
        //读取word文本内容
        Range range = hdt.getRange();
        range.replaceText(model, value);
    }

    private HWPFDocument writeDoc(Map map) {
        try {
            File f = new File(template);
            if(f.exists()) {
                FileInputStream fs = new FileInputStream(f);
                HWPFDocument hdt = new HWPFDocument(fs);
                //读取word文本内容
                Range range = hdt.getRange();
                //替换文本内容
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    range.replaceText(entry.getKey().toString(), entry.getValue().toString());
                }
                return hdt;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Setter && Getter
    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}

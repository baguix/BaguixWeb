/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/15.
 */
package com.baguix.utils.doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>HTML文件处理工具</b>
 *
 * @author Scott(SG)
 */
public class HtmlTool {
    /**
     * <b>根据URL获取某网址的HTML代码</b>
     * @param url 网址
     * @param timeout 几秒后超时
     * @return String格式的HTML代码
     */
    public String getHTML(String url, int timeout) {
        Document doc;
        if(url!=null){
            try {
                doc = Jsoup.connect(url).timeout(timeout).get();
                return doc.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * <b>根据HTML代码获取图片路径</b>
     * @param html HTML代码
     * @return <img>标签的src
     */
    public List<String> getImg(String html) {
        List<String> list = new ArrayList<String>();
        Document doc = Jsoup.parse(html, "gbk");
        Elements img_list = doc.select("img");
        for (Element e : img_list) {
            list.add(e.attr("src"));
        }
        return list;
    }
}

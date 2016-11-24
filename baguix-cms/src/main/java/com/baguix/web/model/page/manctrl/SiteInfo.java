/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */

package com.baguix.web.model.page.manctrl;

import com.baguix.utils.data.ReflectTool;
import com.baguix.utils.doc.XmlTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>网站信息</b><br>
 *
 * @author Scott
 */

// @XStreamAlias("...") 别名
// @XStreamAsAttribute 作为节点的属性显示
// @XStreamOmitField 忽略
// @XStreamConverter(CalendarConverter.class)类型转换，CalendarConverter.class是一个implements Converter的转换工具类

@XStreamAlias("siteinfo")
public class SiteInfo {
    // 网站标题
    private String title = "BaguixWeb";
    // 网站标题分隔符
    private String titleSeparator = "-";
    // 网站副标题
    private String subTitle = "开源信息化平台";
    // 网站地址
    private String siteUrl = "www.baguix.com";
    // 网站关键词
    private String keywords = "信息化开发平台,开源项目,开源平台,开源系统";
    // 网站描述
    private String description = "Baguix致力于打造高效的javaWeb开发平台";
    // ICP备案号
    private String icp = "桂ICP备20150403号";
    // 网警备案号
    private String police = "桂2015765号";
    // 网警备案代码
    private String policeCode = "<div align=center><a id='_gx_gangting' href='http://www.gx.cyberpolice.cn/AlarmInfo/getTishi.do?icon=gangting&checkCode=a3a93069f6e1fdbf4e3e5484e85c71c4' target='_blank' style=' text-decoration: none;display: block;'><img src='images/cyberhome.gif' alt='广西网警虚拟岗亭' border='0'> </a> <a id='_gx_baicp' href='http://www.gx.cyberpolice.cn/AlarmInfo/getTishi.do?icon=baicp&checkCode=a3a93069f6e1fdbf4e3e5484e85c71c4' target='_blank' style='text-decoration: none;display: block;'><img src='images/baicp.gif' alt='广西网警ICP备案' border='0'></a></div><div style='margin-top:5px;' align=center>网警备案号：桂2015765号</div>";
    // 网站版权
    private String copyright = "Baguix工作室版权所有©2015 (Copyright © 2015 baguixing Corporation, All Rights Reserved)";
    // 授权码
    private String license = "abcdef123456";

    //Setter && Getter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSeparator() {
        return titleSeparator;
    }

    public void setTitleSeparator(String titleSeparator) {
        this.titleSeparator = titleSeparator;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getPoliceCode() {
        return policeCode;
    }

    public void setPoliceCode(String policeCode) {
        this.policeCode = policeCode;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * <b>把siteinfo转为Map</b><br>
     *
     * @param si SiteInfo实例
     * @return 返回Map(String, String)
     */
    public Map<String, String> toHashMap(SiteInfo si) {
        Map<String, String> map = new HashMap<>();
        ReflectTool<SiteInfo> rt = new ReflectTool<>();
        map = rt.simpleBean2Map(si);
        return map;
    }

    /**
     * <b>把SiteInfo转为XML文件</b><br>
     *
     * @param si   SiteInfo实例
     * @param file XML文件
     */
    public void toXMLFile(SiteInfo si, String file) {
        XmlTool.obj2XmlWithCDATA(si, "siteinfo", file);
    }

    public void toXMLFile(SiteInfo si) {
        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("sysdata");
        sb.append(FileManager.getFileSeparator());
        sb.append("BeanMapping");
        sb.append(FileManager.getFileSeparator());
        sb.append("siteinfo.xml");
        XmlTool.obj2XmlWithCDATA(si, "siteinfo", sb.toString());
    }

    /**
     * <b>把xml文件转换为SiteInfo</b><br>
     *
     * @param file XML文件
     * @return
     */
    public SiteInfo fromXMLFile(String file) {
        SiteInfo si = (SiteInfo) XmlTool.xml2Object(file, SiteInfo.class, "siteinfo");
        return si;
    }

    public SiteInfo fromXMLFile() {
        SiteInfo si = new SiteInfo();
        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("sysdata");
        sb.append(FileManager.getFileSeparator());
        sb.append("BeanMapping");
        sb.append(FileManager.getFileSeparator());
        sb.append("siteinfo.xml");
        si = (SiteInfo) XmlTool.xml2Object(sb.toString(), SiteInfo.class, "siteinfo");

        return si;
    }
}

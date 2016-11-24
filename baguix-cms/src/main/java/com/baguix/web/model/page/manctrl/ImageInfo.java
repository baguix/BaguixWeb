/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */

package com.baguix.web.model.page.manctrl;

import com.baguix.utils.data.ReflectTool;
import com.baguix.utils.doc.XmlTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>网站信息</b><br>
 *
 * @author Scott
 */
@XStreamAlias("imageinfo")
public class ImageInfo {
    // 是否裁图: false不裁切、true裁切
    private boolean cutPic = false;
    // 图片宽度(普通相机4:3，单反相机3:2)
    private int picWidth = 600;
    // 图片高度
    private int picHeight = 400;
    // 是否生成缩略图: false不生成、true生成
    private boolean thumbPic = true;
    // 图片边框: false无边框、true有边框
    private boolean border = false;
    // 边框粗细
    private int borderSize = 3;
    //边框颜色
    private String borderColor = "#000000";
    // 图片宽度(普通相机4:3，单反相机3:2)
    private int thumbWidth = 150;
    // 图片高度
    private int thumbHeight = 100;
    // 水印: 0无、1文字、2图片
    private int watermark = 0;
    // 水印位置: southeast右下角、southwest左下角、northwest左上角、northeast右上角、center中间
    private String watermarkPosition = "southeast";
    // 水印文字内容
    private String markText = "www.baguix.com";
    // 水印文字颜色
    private String markTextColor = "#ff0000";
    // 水印文字字体大小
    private int markTextSize = 24;
    // 水印图片路径
    private String markImg = "/upfile/sys/logo.png";
    // 水印图片路径串
    private String markImgStr = "/upfile/sys/logo1.png,/upfile/sys/logo2.png";
    // 水印图片路径
    private int markImgAlpha = 25;

    //Setter && Getter
    public int getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    public int getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    public boolean isCutPic() {
        return cutPic;
    }

    public void setCutPic(boolean cutPic) {
        this.cutPic = cutPic;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public boolean isThumbPic() {
        return thumbPic;
    }

    public void setThumbPic(boolean thumbPic) {
        this.thumbPic = thumbPic;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public int getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(int thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public int getThumbHeight() {
        return thumbHeight;
    }

    public void setThumbHeight(int thumbHeight) {
        this.thumbHeight = thumbHeight;
    }

    public int getWatermark() {
        return watermark;
    }

    public void setWatermark(int watermark) {
        this.watermark = watermark;
    }

    public String getWatermarkPosition() {
        return watermarkPosition;
    }

    public void setWatermarkPosition(String watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
    }

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText;
    }

    public String getMarkTextColor() {
        return markTextColor;
    }

    public void setMarkTextColor(String markTextColor) {
        this.markTextColor = markTextColor;
    }

    public int getMarkTextSize() {
        return markTextSize;
    }

    public void setMarkTextSize(int markTextSize) {
        this.markTextSize = markTextSize;
    }

    public String getMarkImg() {
        return markImg;
    }

    public void setMarkImg(String markImg) {
        this.markImg = markImg;
    }

    public String getMarkImgStr() {
        return markImgStr;
    }

    public void setMarkImgStr(String markImgStr) {
        this.markImgStr = markImgStr;
    }

    public int getMarkImgAlpha() {
        return markImgAlpha;
    }

    public void setMarkImgAlpha(int markImgAlpha) {
        this.markImgAlpha = markImgAlpha;
    }

    /**
     * <b>把ImageInfo转为Map</b><br>
     *
     * @param ii ImageInfo
     * @return 返回Map(String, String)
     */
    public Map<String, String> toHashMap(ImageInfo ii) {
        Map<String, String> map = new HashMap<>();
        ReflectTool<ImageInfo> rt = new ReflectTool<>();
        map = rt.simpleBean2Map(ii);
        return map;
    }

    /**
     * <b>把SiteInfo转为XML文件</b><br>
     *
     * @param si   SiteInfo实例
     * @param file XML文件
     */
    public void toXMLFile(ImageInfo si, String file) {
        XmlTool.obj2XmlWithCDATA(si, "imageinfo", file);
    }

    public void toXMLFile(ImageInfo si) {

        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("sysdata");
        sb.append(FileManager.getFileSeparator());
        sb.append("BeanMapping");
        sb.append(FileManager.getFileSeparator());
        sb.append("imageinfo.xml");
        XmlTool.obj2XmlWithCDATA(si, "imageinfo", sb.toString());
    }

    /**
     * <b>把xml文件转换为SiteInfo</b><br>
     *
     * @param file XML文件
     * @return
     */
    public ImageInfo fromXMLFile(String file) {
        ImageInfo si = (ImageInfo) XmlTool.xml2Object(file, ImageInfo.class, "imageinfo");
        return si;
    }

    public ImageInfo fromXMLFile() {
        ImageInfo si = new ImageInfo();
        StringBuilder sb = new StringBuilder();
        sb.append(PathTool.getClassesPath());
        sb.append(FileManager.getFileSeparator());
        sb.append("sysdata");
        sb.append(FileManager.getFileSeparator());
        sb.append("BeanMapping");
        sb.append(FileManager.getFileSeparator());
        sb.append("imageinfo.xml");
        si = (ImageInfo) XmlTool.xml2Object(sb.toString(), ImageInfo.class, "imageinfo");
        return si;
    }
}

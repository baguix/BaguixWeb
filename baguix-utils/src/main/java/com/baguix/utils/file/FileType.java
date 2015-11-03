/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/23.
 */
package com.baguix.utils.file;

/**
 * <b>文件类型</b><br>
 * 比判断扩展名更专业的文件判断方法，使用文件的头信息进行识别：<br>
 * 以下未列出的文件可以采用Hex编辑器查看文件得到文件头,纯文本文件的文件头以文本内的字符串决定因此不一定正确。<br>
 * 以下是常用文件的头： <br>
 *     <ul>
 *         <li>JPEG (jpg)，文件头：FFD8FFE</li>
 *         <li>PNG (png)，文件头：89504E47</li>
 *         <li>GIF (gif)，文件头：47494638</li>
 *         <li>TIFF (tif)，文件头：49492A00</li>
 *         <li>Windows Bitmap (bmp)，文件头：424D</li>
 *         <li>CAD (dwg)，文件头：41433130</li>
 *         <li>Adobe Photoshop (psd)，文件头：38425053</li>
 *         <li>Rich Text Format (rtf)，文件头：7B5C727466</li>
 *         <li>XML (xml)，文件头：3C3F786D6C</li>
 *         <li>HTML (html)，文件头：68746D6C3E</li>
 *         <li>Email [thorough only] (eml)，文件头：44656C69766572792D646174653A</li>
 *         <li>Outlook Express (dbx)，文件头：CFAD12FEC5FD746F</li>
 *         <li>Outlook (pst)，文件头：2142444E</li>
 *         <li>MS Word/Excel (xls.or.doc)，文件头：D0CF11E0</li>
 *         <li>MS Word2007/Excel2007 (.docx or .xlsx)，文件头：504b030414000600</li>
 *         <li>MS Access (mdb)，文件头：5374616E64617264204A</li>
 *         <li>WordPerfect (wpd)，文件头：FF575043</li>
 *         <li>Postscript (eps.or.ps)，文件头：252150532D41646F6265</li>
 *         <li>Adobe Acrobat (pdf)，文件头：255044462D312E</li>
 *         <li>Quicken (qdf)，文件头：AC9EBD8F</li>
 *         <li>Windows Password (pwl)，文件头：E3828596</li>
 *         <li>ZIP Archive (zip)，文件头：504B0304</li>
 *         <li>RAR Archive (rar)，文件头：52617221</li>
 *         <li>Wave (wav)，文件头：57415645</li>
 *         <li>AVI (avi)，文件头：41564920</li>
 *         <li>MP4(.mp4)，文件头：0000001866747970</li>
 *         <li>Real Audio (ram)，文件头：2E7261FD</li>
 *         <li>Real Media (rm)，文件头：2E524D46</li>
 *         <li>MPEG (mpg)，文件头：000001BA</li>
 *         <li>MPEG (mpg)，文件头：000001B3</li>
 *         <li>Quicktime (mov)，文件头：6D6F6F76</li>
 *         <li>Windows Media (asf)，文件头：3026B2758E66CF11</li>
 *         <li>MIDI (mid)，文件头：4D546864</li>
 *     </ul>
 *
 * @author Scott(SG)
 * @since 1.0
 */

/**
 * 文件类型枚取
 */
public enum FileType {
    JPEG("FFD8FFE"),
    PNG("89504E47"),
    GIF("47494638"),
    TIFF("49492A00"),
    BMP("424D"),

    /**
     * CAD.
     */
    DWG("41433130"),

    /**
     * Adobe Photoshop.
     */
    PSD("38425053"),

    /**
     * Rich Text Format.
     */
    RTF("7B5C727466"),

    /**
     * XML.
     */
    XML("3C3F786D6C"),

    /**
     * HTML.
     */
    HTML("68746D6C3E"),

    /**
     * Email [thorough only].
     */
    EML("44656C69766572792D646174653A"),

    /**
     * Outlook Express.
     */
    DBX("CFAD12FEC5FD746F"),

    /**
     * Outlook (pst).
     */
    PST("2142444E"),

    /**
     * MS Word/Excel.
     */
    XLS_DOC("D0CF11E0"),

    /**
     * MS Access.
     */
    MDB("5374616E64617264204A"),

    /**
     * WordPerfect.
     */
    WPD("FF575043"),

    /**
     * Postscript.
     */
    EPS("252150532D41646F6265"),

    /**
     * Adobe Acrobat.
     */
    PDF("255044462D312E"),

    /**
     * Quicken.
     */
    QDF("AC9EBD8F"),

    /**
     * Windows Password.
     */
    PWL("E3828596"),

    /**
     * ZIP Archive.
     */
    ZIP("504B0304"),

    /**
     * RAR Archive.
     */
    RAR("52617221"),

    /**
     * Wave.
     */
    WAV("57415645"),

    /**
     * AVI.
     */
    AVI("41564920"),

    /**
     * Real Audio.
     */
    RAM("2E7261FD"),

    /**
     * Real Media.
     */
    RM("2E524D46"),

    /**
     * MPEG (mpg).
     */
    MPG("000001BA"),

    /**
     * Quicktime.
     */
    MOV("6D6F6F76"),

    /**
     * Windows Media.
     */
    ASF("3026B2758E66CF11"),

    /**
     * MIDI.
     */
    MID("4D546864");

    private String value = "";

    // 构造器
    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


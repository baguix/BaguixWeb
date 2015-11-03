/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/28.
 */
package com.baguix.utils.net;

import com.baguix.utils.doc.PropertiesTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import com.baguix.utils.security.EncryptTool;
import org.apache.commons.mail.EmailAttachment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Email基本信息类</b><br>
 *
 * @author Scott(SG)
 */
public class EmailBean {
    // 发件邮箱smtp服务器地址
    private String smtp;
    // 发件邮箱用户名
    private String user;
    // 发件邮箱密码
    private String password;
    // 收件人邮箱
    private List<String> mailTo;
    // 邮件主题
    private String mailSubject;
    // 邮件内容
    private String mailContent;
    // 邮件内容编码
    private String mailCharset;
    // 邮件附件
    private EmailAttachment[] attachFileNames;
    // 开启SSL
    private boolean ssl;
    // 开启TLS
    private boolean tls;


    // 构造器
    public EmailBean(){
        String confPath = PathTool.getClassesPath()+ FileManager.getFileSeparator()+"email.properties";
        File config = new File(confPath);
        if(config.exists()) {
            PropertiesTool pt = new PropertiesTool();
            Map<String,String> map = new HashMap<>();
            pt.mapFile(map, confPath);
            if (smtp == null || "".equals(smtp)) {
                this.smtp = EncryptTool.strongDecrypt(map.get("smtp"));
            }
            if (user == null || "".equals(user)) {
                this.user = EncryptTool.strongDecrypt(map.get("user"));
            }
            if (password == null || "".equals(password)) {
                this.password = EncryptTool.strongDecrypt(map.get("password"));
            }
        }
    }

    // Setter && Getter

    /**
     * <b>获得发件邮箱smtp服务器地址</b>
     */
    public String getSmtp() {
        return smtp;
    }

    /**
     * <b>设置发件邮箱smtp服务器地址</b><br>
     * 如：smtp.qq.com
     */
    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    /**
     * <b>获得发件邮箱用户名</b>
     */
    public String getUser() {
        return user;
    }

    /**
     * <b>设置发件邮箱用户名</b><br>
     * 如：10000@qq.com
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * <b>获得发件邮箱密码</b>
     */
    public String getPassword() {
        return password;
    }

    /**
     * <b>设置发件邮箱密码</b>
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <b>获得收件人邮箱密码</b>
     */
    public List<String> getMailTo() {
        return mailTo;
    }

    /**
     * <b>设置发件邮箱密码</b>
     *
     * @param mailTo 一个或多个地址
     */
    public void setMailTo(List<String> mailTo) {
        this.mailTo = mailTo;
    }

    public void setMailTo(String mailTo) {
        if (mailTo != null && !"".equals(mailTo)) {
            List<String> to = new ArrayList<String>();
            to.add(mailTo);
            this.mailTo = to;
        } else {
            List<String> to = new ArrayList<String>();
            to.add("");
            this.mailTo = to;
        }
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getMailCharset() {
        return mailCharset;
    }

    public void setMailCharset(String mailCharset) {
        this.mailCharset = mailCharset;
    }

    public EmailAttachment[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(EmailAttachment[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isTls() {
        return tls;
    }

    public void setTls(boolean tls) {
        this.tls = tls;
    }
}

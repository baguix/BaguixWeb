/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/25.
 */
package com.baguix.utils.doc;

/**
 * <b>测试Bean</b><br>
 *
 * @author Scott(SG)
 */
public class UserBean {
    private String user = "张三";
    private String sex = "男";
    private String idcard = "1234567890";
    private String copyright = "baguix.com";
    private String comany = "八桂星工作室";

    // Setter && Getter
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getComany() {
        return comany;
    }

    public void setComany(String comany) {
        this.comany = comany;
    }
}

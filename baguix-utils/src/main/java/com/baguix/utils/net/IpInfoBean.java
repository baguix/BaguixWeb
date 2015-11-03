/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/1.
 */
package com.baguix.utils.net;

/**
 * <b>IP信息</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class IpInfoBean {
    // IP地址
    private String ip;
    // 国家
    private String country;
    // 省份（自治区）
    private String area;
    // 地城
    private String city;
    // 运营商
    private String isp;


    // Setter && Getter
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}

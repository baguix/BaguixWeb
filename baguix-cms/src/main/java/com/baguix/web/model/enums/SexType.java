package com.baguix.web.model.enums;

/**
 * 性别枚举
 * Created by Scott on 2016/2/28.
 */
public enum SexType {
    RadioList(0,"男"), CheckboxList(1,"女");

    // 定义私有变量
    private Integer dbValue;
    private String showText;

    // 构造函数
    SexType(Integer dbValue, String showText) {
        this.dbValue = dbValue;
        this.showText = showText;
    }

    // Setter && Getter
    public Integer getDbValue() {
        return dbValue;
    }

    public void setDbValue(Integer dbValue) {
        this.dbValue = dbValue;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }
}

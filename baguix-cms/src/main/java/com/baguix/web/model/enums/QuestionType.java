package com.baguix.web.model.enums;

/**
 * 考试系统题型枚举(整形）
 * Created by Scott on 2016/2/24.
 */
public enum QuestionType {
    NOTYPE(0,"未知"),
    SELECT(1,"单选"),
    MULTISELECT(2,"多选"),
    TRUEFALSE(3,"判断"),
    FILLBLANK(4,"填空");

    // 定义私有变量
    private Integer dbValue;
    private String showText;

    // 构造函数
    QuestionType(Integer dbValue, String showText) {
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


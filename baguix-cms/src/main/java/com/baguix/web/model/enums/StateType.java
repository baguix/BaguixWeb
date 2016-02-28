package com.baguix.web.model.enums;

/**
 * 数据库记录状态枚举(整形）
 * Created by Scott on 2016/2/24.
 */
public enum StateType {
    // 草稿
    DRAFT(0),
    // 显示
    SHOW(1),
    // 隐藏
    HIDE(2),
    // 暂删
    DELETE(3),
    // 删除
    REMOVE(4);

    // 定义私有变量
    private Integer StateType;

    // 构造函数

    StateType(Integer stateType) {
        StateType = stateType;
    }

    // 取出中文名
    public String getText() {
        String text;
        switch (this.StateType) {
            case 0:
                text = "草稿";
                break;
            case 1:
                text = "显示";
                break;
            case 2:
                text = "隐藏";
                break;
            case 3:
                text = "暂删";
                break;
            case 4:
                text = "删除";
                break;
            default:
                text = "未知";
        }
        return text;
    }

    // Setter && Getter
    public void setStateType(Integer stateType) {
        StateType = stateType;
    }

    public Integer getStateType() {
        return StateType;
    }
}


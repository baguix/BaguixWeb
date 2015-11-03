package com.baguix.web.model.easyui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Easyui 的 Tree 组件数据。</b><br>
 *     参考：官方的data.json
 */
public class TreeData implements Serializable{
    //序列化ID
    private static final long serialVersionUID = 48L;

    // ID
    private String id = "";
    // 标题
    private String text = "";
    // 状态：closed，open
    private String state = "closed";
    // 图标
    private String iconCls = "icon-save";
    // 属性
    private Map<String, Object> attributes = new HashMap<>();

    // 是否选中
    private boolean checked = false;

    private List<TreeData> children = new ArrayList<>();

    // Setter && Getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<TreeData> getChildren() {
        return children;
    }

    public void setChildren(List<TreeData> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

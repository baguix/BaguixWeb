package com.baguix.web.model.page.exam;


import com.baguix.web.model.db.core.ECommonField;
import com.baguix.web.model.db.exam.TAbsQuestion;
import com.baguix.web.model.db.exam.TKnowledge;
import com.baguix.web.model.enums.StateType;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Scott on 2016/3/19.
 */
public class Knowledge {
    // 标识符
    private String id;
    // 标题
    private String text;
    // 内容
    private String content;
    // 层级
    private int level;
    // 公共字段
    //状态
    private StateType status;
    //排序值
    private int rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;

    // ============================树形Grid用的字段============================
    private String iconCls;
    private String state;
    private String pid; // 父目录ID
    private List<Knowledge> children;
    private Map<String, Object> attributes;
    // ============================树形用的字段============================

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public StateType getStatus() {
        return status;
    }

    public void setStatus(StateType status) {
        this.status = status;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Knowledge> getChildren() {
        return children;
    }

    public void setChildren(List<Knowledge> children) {
        this.children = children;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}

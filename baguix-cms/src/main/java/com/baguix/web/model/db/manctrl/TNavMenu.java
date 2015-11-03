/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.db.manctrl;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

/**
 * Description：Database model: 管理界面导航菜单
 *
 * @author Scott
 */

@Entity
@Table(name = "SS_NAVMENU", schema = "")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sysCache")
@Proxy(lazy = false)

public class TNavMenu implements Serializable {
    //序列化ID
    private static final long serialVersionUID = 48L;
    //标识符
    private String id;

    //============================自定义字段============================
    //标题
    private String title;
    //图标
    private String icon;
    //类型(bar，menu，left)
    private String type;
    //跳转地址(单击后直接链接到的地址，适用于单击跳转型，无则留空)
    private String url;
    //目标(link，menu，dialog)
    private String target;
    //树形菜单
    private TNavMenu parent;
    private Set<TNavMenu> children = new HashSet<TNavMenu>(0);
    //级别
    private int level;
    //是否有子菜单
    private boolean hassub;
    //============================公共字段============================
    //排序值
    private int rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;

    //无参数的构造器
    public TNavMenu() {
        this.ctime = new Date();
    }

    //Setter && Getter
    @Id
    @Column(name = "NAVMENU_ID", unique = true, nullable = false, length = 36)
    public String getId() {
        if (this.id != null) {
            return this.id;
        }
        return UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAVMENU_TITLE", length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "NAVMENU_ICON", length = 200)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "NAVMENU_TYPE", length = 200)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "NAVMENU_URL", length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "NAVMENU_TARGET", length = 200)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NAVMENU_PID")
    public TNavMenu getParent() {
        return this.parent;
    }

    public void setParent(TNavMenu parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    public Set<TNavMenu> getChildren() {
        return this.children;
    }

    public void setChildren(Set<TNavMenu> children) {
        this.children = children;
    }

    @Column(name = "NAVMENU_LEVEL", length = 200)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "NAVMENU_HASSUB", length = 200)
    public boolean getHassub() {
        return hassub;
    }

    public void setHassub(boolean hassub) {
        this.hassub = hassub;
    }

    @Column(name = "NAVMENU_RANK", length = 20)
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NAVMENU_CTIME")
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NAVMENU_MTIME")
    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}
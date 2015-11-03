/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/27.
 */
package com.baguix.web.model.db.core;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

/**
 * <b>系统模块（导航菜单）</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
@Entity
@Table(name = "SS_CORE_MODULE", schema = "")

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sysCache")
@Proxy(lazy = false)

public class TModule  implements Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    //标题
    private String title;
    // 模块说明
    private String note;
    //图标
    private String icon;
    //类型(bar，menu，left)
    private String type;
    //跳转地址(单击后直接链接到的地址，适用于单击跳转型，无则留空)
    private String url;
    //目标(link，menu，dialog)
    private String target;
    //树形菜单
    private TModule parent;
    private Set<TModule> children = new HashSet<>(0);
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
    // 权限（一对多）
    private List<TPermission> permissions;

    // Setter && Getter
    @Id
    @Column(name = "MODULE_ID", unique = true, nullable = false, length = 36)
    public String getId() {
        if (this.id != null) {
            return this.id;
        }
        return UUID.randomUUID().toString();
    }
    
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "MODULE_TITLE", length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(name = "MODULE_NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Column(name = "MODULE_ICON", length = 200)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "MODULE_TYPE", length = 200)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "MODULE_URL", length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "MODULE_TARGET", length = 200)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODULE_PID")
    public TModule getParent() {
        return this.parent;
    }

    public void setParent(TModule parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    public Set<TModule> getChildren() {
        return this.children;
    }

    public void setChildren(Set<TModule> children) {
        this.children = children;
    }

    @Column(name = "MODULE_LEVEL", length = 200)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "MODULE_HASSUB", length = 200)
    @org.hibernate.annotations.Type(type="yes_no")
    public boolean isHassub() {
        return hassub;
    }

    public void setHassub(boolean hassub) {
        this.hassub = hassub;
    }

    @Column(name = "MODULE_RANK", length = 20)
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODULE_CTIME")
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODULE_MTIME")
    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    public List<TPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TPermission> permissions) {
        this.permissions = permissions;
    }
}

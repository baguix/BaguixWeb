/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/26.
 */
package com.baguix.web.model.db.core;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * <b>权限</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Entity
@Table(name = "SS_CORE_PERMISSION", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TPermission implements java.io.Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    // 权限名称
    private String name;
    // 权限说明
    private String note;
    //排序值
    private int rank;
    // 模块(多对一)
    private TModule module;
    // 角色(多对一)
    private TRole role;

    // Setter && Getter

    @Id
    @Column(name = "PERMISSION_ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "PERMISSION_NAME", length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(name = "PERMISSION_NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "PERMISSION_RANK", length = 20)
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    public TModule getModule() {
        return module;
    }

    public void setModule(TModule module) {
        this.module = module;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    public TRole getRole() {
        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }
}

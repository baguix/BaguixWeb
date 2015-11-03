/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/26.
 */
package com.baguix.web.model.db.core;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * <b>角色</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Entity
@Table(name = "SS_CORE_ROLE", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)
public class TRole implements java.io.Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    // 角色名称
    private String name;
    // 角色说明
    private String note;
    // 角色-用户（多对多）
    private Set<TUserRole> roleuser;
    // 角色-权限（一对多）
    private List<TPermission> permissions;

    // Setter && Getter
    @Id
    @Column(name = "ROLE_ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "ROLE_NAME", length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(name = "ROLE_NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public Set<TUserRole> getRoleuser() {
        return roleuser;
    }

    public void setRoleuser(Set<TUserRole> roleuser) {
        this.roleuser = roleuser;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public List<TPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TPermission> permissions) {
        this.permissions = permissions;
    }
}

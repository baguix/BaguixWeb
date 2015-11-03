/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/26.
 */
package com.baguix.web.model.db.core;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * <b>角色</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Entity
@Table(name = "SS_CORE_USERROLE", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TUserRole implements java.io.Serializable {
    // 序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    // 用户
    private TUser user;
    // 角色
    private TRole role;

    @Id
    @Column(name = "USERROLE_ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    public TRole getRole() {
        return role;
    }

    public void setRole(TRole role) {
        this.role = role;
    }
}

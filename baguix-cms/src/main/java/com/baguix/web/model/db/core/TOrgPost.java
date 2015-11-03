/*
 * Copyright (c) 2014-8-16 Scott All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Description：组织岗位关系表 多对多中间表
 * 
 * @author Scott
 */

@Entity
@Table(name = "SS_CORE_ORGPOST", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TOrgPost implements java.io.Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;

	// 标识符
	private String id;
	// ==============================关系=================================
	private TOrg org;
	private TPost post;

	// Setter && Getter
	@Id
	@Column(name = "ORGPOST_ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	public TOrg getOrg() {
		return org;
	}

	public void setOrg(TOrg org) {
		this.org = org;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
	public TPost getPost() {
		return post;
	}

	public void setPost(TPost post) {
		this.post = post;
	}
}

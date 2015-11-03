/*
 * Copyright (c) 2014-8-15 Scott All Rights Reserved!
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
 * Description: 组织机构、部门、人员间的关系
 * 多对多双向关联中间表
 * 
 * @author Scott
 */

@Entity
@Table(name = "SS_CORE_ORGRELATION", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TOrgRel implements java.io.Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	
	// 标识符
	private String id;
	
	
	private TOrg parent;
	private TOrg child;

	
	//Setter && Getter
	@Id
	@Column(name = "ORGREL_ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public TOrg getParent() {
		return parent;
	}
	public void setParent(TOrg parent) {
		this.parent = parent;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CID")
	public TOrg getChild() {
		return child;
	}
	public void setChild(TOrg child) {
		this.child = child;
	}
	
}

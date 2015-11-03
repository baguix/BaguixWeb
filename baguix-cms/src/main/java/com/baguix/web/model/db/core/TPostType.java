/*
 * Copyright (c) 2014-8-16 Scott All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Description：组织机构里的岗位类型
 * 和岗位一对多关系
 * 下含多个岗位。比如：“销售经理”类型，包括：图象部销售经理、网络部销售经理
 * @author Scott
 */

@Entity
@Table(name = "SS_CORE_POSTTYPE", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TPostType implements java.io.Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	// 标识符
	private String id;
	//岗位类型名称
	private String name;
	//岗位类型说明
	private String note;
	
//====================================关系====================================
	//岗位:一对多
	private Set<TPost> posts;
	
	
	//Setter && Getter
	@Id
	@Column(name = "POSTTYPE_ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "POSTTYPE_NAME", length = 500)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Lob
	@Column(name = "POSTTYPE_NOTE", length = 2000)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "posttype")
	public Set<TPost> getPosts() {
		return posts;
	}

	public void setPosts(Set<TPost> posts) {
		this.posts = posts;
	}
	
}

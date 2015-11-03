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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Description：组织机构里的岗位
 * 
 * @author Scott
 */


@Entity
@Table(name = "SS_CORE_POST", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TPost implements java.io.Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	
	// 标识符
	private String id;
	//岗位名称
	private String name;
	//岗位说明
	private String note;
	
//====================================关系====================================
	//所属部门：多对多
	private Set<TOrgPost> postorg;
	
	//所属岗位类型：多对一
	private TPostType posttype;
	
	@Id
	@Column(name = "POST_ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "POST_NAME", length = 500)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Lob
	@Column(name = "POST_NOTE", length = 2000)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_TYPEID")
	public TPostType getPosttype() {
		return posttype;
	}

	public void setPosttype(TPostType posttype) {
		this.posttype = posttype;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
	public Set<TOrgPost> getPostorg() {
		return postorg;
	}

	public void setPostorg(Set<TOrgPost> postorg) {
		this.postorg = postorg;
	}

}

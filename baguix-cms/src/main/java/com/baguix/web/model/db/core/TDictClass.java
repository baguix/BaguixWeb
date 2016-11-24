/*
 * Copyright (c) 2014-1-24 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Description：Database model: 字典表分类
 *
 * @author Scott
 */

@Entity
@Table(name = "SS_CORE_DICTCLASS", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TDictClass implements Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	// 标识符
	private String id;

	// ============================自定义字段============================
	// 标题
	private String title;
	// 备注
	private String comment;
	// 字典项
	private Set<TDict> dict;
	// ============================公共字段============================
	// 排序值
	private int rank;
	// 创建时间
	private Date ctime;
	// 修改时间
	private Date mtime;

	// 无参数的构造器
	public TDictClass() {
		this.ctime = new Date();
	}

	// Setter && Getter
	@Id
	//@Column(name = "DICTCLASS_ID", unique = true, nullable = false, length = 36)
	@Column(name = "DICTCLASS_ID", nullable = false, length = 36)//Oracle不能出现unique关键字
	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DICTCLASS_TITLE", length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Column(name = "DICTCLASS_COMMENT", length = 2000)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dictClass")
	public Set<TDict> getDict() {
		return dict;
	}

	public void setDict(Set<TDict> dict) {
		this.dict = dict;
	}

	@Column(name = "DICT_RANK", length = 20)
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DICT_CTIME")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DICT_MTIME")
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}

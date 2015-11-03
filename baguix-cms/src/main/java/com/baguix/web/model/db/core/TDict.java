/*
 * Copyright (c) 2014-1-24 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Description：Database model: 字典表
 * 
 * @author Scott
 */

@Entity
@Table(name = "SS_DICT", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TDict implements Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	// 标识符
	private String id;

	// ============================自定义字段============================
	// 名称(唯一)
	private String name;
	// 标题
	private String title;
	// 备注
	private String comment;
	// 类型：单选列表（如：●是  ○否）、多选列表（如：■男装、■红色、□衬衣）、ComboBox单选、ComboBox多选、ComboTree单选、ComboTree多选
	private String type;
	// 字典项
	private Set<TDictItem> item;
	// ============================公共字段============================
	// 排序值
	private int rank;
	// 创建时间
	private Date ctime;
	// 修改时间
	private Date mtime;

	// 无参数的构造器
	public TDict() {
		this.ctime = new Date();
	}

	// Setter && Getter
	@Id
	//@Column(name = "DICT_ID", unique = true, nullable = false, length = 36)
	@Column(name = "DICT_ID", nullable = false, length = 36)//Oracle不能出现unique关键字
	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Lob
	@Column(name = "DICT_NAME", length = 1000)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DICT_TITLE", length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Column(name = "DICT_COMMENT", length = 2000)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name = "DICT_TYPE", length = 250)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="dict", cascade={CascadeType.ALL})
	public Set<TDictItem> getItem() {
		return item;
	}

	public void setItem(Set<TDictItem> item) {
		this.item = item;
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

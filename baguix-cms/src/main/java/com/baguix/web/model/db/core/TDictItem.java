/*
 * Copyright (c) 2014-1-24 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

/**
 * Description：Database model: 字典项目表
 * 
 * @author Scott
 */

@Entity
@Table(name = "SS_DICTITEM", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class TDictItem implements Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	// 标识符
	private String id;

	// ============================自定义字段============================
	// 标题
	private String title;
	// 值
	private String value;
	// 是否默认
	private boolean def;

	//属于哪个字典
	private TDict dict;
	// ===========================树形==========================
	private TDictItem parent;
	private Set<TDictItem> child= new HashSet<TDictItem>(0);
	private int level;
	// ============================公共字段============================
	// 排序值
	private int rank;
	// 创建时间
	private Date ctime;
	// 修改时间
	private Date mtime;

	// 无参数的构造器
	public TDictItem() {
		this.ctime = new Date();
	}

	// Setter && Getter
	@Id
	//@Column(name = "DICTITEM_ID", unique = true, nullable = false, length = 36)
	@Column(name = "DICTITEM_ID", nullable = false, length = 36)//Oracle不能出现unique关键字
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
	@Column(name = "DICTITEM_TITLE", length = 2000)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="DICTITEM_DEFAULT")
	@org.hibernate.annotations.Type(type = "yes_no")
	public boolean getDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DICT_ID")
	public TDict getDict() {
		return dict;
	}

	public void setDict(TDict dict) {
		this.dict = dict;
	}
	
	@Lob
	@Column(name = "DICTITEM_VALUE", length = 2000)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public TDictItem getParent() {
		return parent;
	}

	public void setParent(TDictItem parent) {
		this.parent = parent;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public Set<TDictItem> getChild() {
		return child;
	}

	public void setChild(Set<TDictItem> child) {
		this.child = child;
	}

	@Column(name = "DICTITEM_LEVEL", length = 20)
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "DICTITEM_RANK", length = 20)
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DICTITEM_CTIME")
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

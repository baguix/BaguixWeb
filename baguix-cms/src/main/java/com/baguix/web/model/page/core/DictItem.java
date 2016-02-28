/*
 * Copyright (c) 2014-1-24 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.page.core;

import java.util.*;
import java.io.Serializable;

/**
* Description：Database model: 字典项目表
* @author Scott
*/

public class DictItem implements Serializable{
    //序列化ID
    private static final long serialVersionUID = 48L;
    //标识符
    private String id;
    
    //============================自定义字段============================
    //标题
	// 标题
	private String title;
	// 值
	private String value;
	// 是否默认
	private String def;
	// 字典ID
	private String dictid;
	// 层级
	private int level;

	// ============================树形Grid用的字段============================
	private String iconCls;
	private String state;
	private String pid; // 父目录ID
	private List<DictItem> children;
	private Map<String, Object> attributes;
	// ============================树形用的字段============================

    //============================公共字段============================
    //排序值
    private int rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;
    //页面DataGrid数据
    private int page;
	private int rows;
	private String sort;
	private String order;
    
    //Setter && Getter
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title=title;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<DictItem> getChildren() {
		return children;
	}

	public void setChildren(List<DictItem> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}


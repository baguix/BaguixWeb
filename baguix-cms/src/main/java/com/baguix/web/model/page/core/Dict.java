/*
 * Copyright (c) 2014-1-24 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.page.core;

import java.util.*;
import java.io.Serializable;

/**
* Description：Database model: 字典表
* @author Scott
*/

public class Dict implements Serializable{
    //序列化ID
    private static final long serialVersionUID = 1L;
    //标识符
    private String id;

	//删除或多选时使用的ID列表
	private String ids;
	
    //============================自定义字段============================
    //名称
    private String name;
    //标题
    private String title;
    //备注
    private String comment;
	//类型
	private String type;
    //状态
    private String state;
	//字典分类
	private String dcid;
	//字典分类名称
	private String dctitle;
    //字典项
    private String itemids;
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
	
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
	
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title=title;
    }
	
    public String getComment(){
        return comment;
    }
    
    public void setComment(String comment){
        this.comment=comment;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState(){
        return state;
    }
    
    public void setState(String state){
        this.state=state;
    }

	public String getDcid() {
		return dcid;
	}

	public void setDcid(String dcid) {
		this.dcid = dcid;
	}

	public String getDctitle() {
		return dctitle;
	}

	public void setDctitle(String dctitle) {
		this.dctitle = dctitle;
	}

	public String getItemids() {
		return itemids;
	}

	public void setItemids(String itemids) {
		this.itemids = itemids;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
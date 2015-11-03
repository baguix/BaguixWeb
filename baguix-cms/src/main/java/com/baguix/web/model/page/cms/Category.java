/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.page.cms;

import java.util.*;
import java.io.Serializable;

/**
 * Description：Database model: 文章栏目页面模型
 * 
 * @author Scott
 */

public class Category implements Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	// 标识符
	private String id;

	// 删除或多选时使用的ID列表
	private String ids;

	// ============================页面数据============================
	// 标题
	private String title;
	// 关键词
	private String keyword;
	// 描述
	private String desc;
	// 外部链接
	private String url;
	// 缩略图
	private String thumb;
	// 缩略图字符串
	private String thumbstr;
	// 类型
	private String type;
	// 级别
	private int level;

	// 打开模式（_blank,_self）
	private String openmode;
	// 显示模式
	private String showmode;
	// 栏目列表记录数
	private int shownum;
	// 首页列表显示记录数
	private int homenum;
	// 属性
	private String proper;
	// 是否上线
	private boolean isonline;
	// 最大子栏目排序值
	private long maxsubrank;
	// 最大文章排序值
	private long maxartrank;
	// ============================公共字段============================
	// 排序值
	private long rank;
	// 创建时间
	private Date ctime;
	// 修改时间
	private Date mtime;
	// 是否暂删
	private boolean isdel;
	// ============================页面DataGrid数据============================
	private int page;
	private int rows;
	private String sort;
	private String order;
	// 自定义的查询条件（过滤表达式形式）
	private String qcondition;

	// ============================树形Grid用的字段============================
	private String iconCls;
	private String state;
	private String pid; // 父目录ID
	private List<Category> children;
	private Map<String, Object> attributes;
	// ============================树形用的字段============================
	private String text;

	// Setter && Getter
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getThumbstr() {
		return thumbstr;
	}

	public void setThumbstr(String thumbstr) {
		this.thumbstr = thumbstr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getOpenmode() {
		return openmode;
	}

	public void setOpenmode(String openmode) {
		this.openmode = openmode;
	}

	public String getShowmode() {
		return showmode;
	}

	public void setShowmode(String showmode) {
		this.showmode = showmode;
	}

	public int getShownum() {
		return shownum;
	}

	public void setShownum(int shownum) {
		this.shownum = shownum;
	}

	public int getHomenum() {
		return homenum;
	}

	public void setHomenum(int homenum) {
		this.homenum = homenum;
	}

	public String getProper() {
		return proper;
	}

	public void setProper(String proper) {
		this.proper = proper;
	}

	public boolean getIsonline() {
		return isonline;
	}

	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}

	public long getMaxsubrank() {
		return maxsubrank;
	}

	public void setMaxsubrank(long maxsubrank) {
		this.maxsubrank = maxsubrank;
	}

	public long getMaxartrank() {
		return maxartrank;
	}

	public void setMaxartrank(long maxartrank) {
		this.maxartrank = maxartrank;
	}

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
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

	public boolean isIsdel() {
		return isdel;
	}

	public void setIsdel(boolean isdel) {
		this.isdel = isdel;
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

	public String getQcondition() {
		return qcondition;
	}

	public void setQcondition(String qcondition) {
		this.qcondition = qcondition;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}

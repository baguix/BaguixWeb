/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.page.cms;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：Database model: 文章
 * 
 * @author Scott
 */

public class Article implements Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	// 标识符
	private String id;

	// 删除或多选时使用的ID列表
	private String ids;

	// ============================自定义字段============================
	// 类型(single、news)
	private String type;
	// 栏目字符串(新闻-新闻动态-晚间新闻)
	private String categoryid;
	// 栏目ID列表(35,44,10)便于检索
	private String categoryids;
	// 栏目名称，便于查看
	private String categoryname;
	// 来源
	private String source;
	// 作者
	private String writer;
	// 文章标题
	private String title;
	// 标题颜色
	private String titlecolor;
	// 外部链接
	private String url;
	// 关键词
	private String keyword;
	// 摘要
	private String abstracts;
	// 内容
	private String content;
	// 上传图片列表
	private String upimgstr;
	// 上传图片原名列表
	private String img;
	// 缩略图文件名
	private String thumbfile;
	// 附件名列表
	private String filename;
	// 附件文件位置列表
	private String file;
	// 分页字数
	private long pagewordnum;
	// 阅读数
	private long readnum;
	// 是否显示
	private boolean state;
	// 属性
	private String proper;
	// ============================公共字段============================
	// 排序值
	private int rank;
	// 创建时间
	private Date ctime;
	private Date startctime;
	private Date endctime;
	// 修改时间
	private Date mtime;
	private Date startmtime;
	private Date endmtime;
	// 页面DataGrid数据
	private int page;
	private int rows;
	private String sort;
	private String order;
	// 自定义的查询条件（过滤表达式形式）
	private String qcondition;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryids() {
		return categoryids;
	}

	public void setCategoryids(String categoryids) {
		this.categoryids = categoryids;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlecolor() {
		return titlecolor;
	}

	public void setTitlecolor(String titlecolor) {
		this.titlecolor = titlecolor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpimgstr() {
		return upimgstr;
	}

	public void setUpimgstr(String upimgstr) {
		this.upimgstr = upimgstr;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getThumbfile() {
		return thumbfile;
	}

	public void setThumbfile(String thumbfile) {
		this.thumbfile = thumbfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public long getPagewordnum() {
		return pagewordnum;
	}

	public void setPagewordnum(long pagewordnum) {
		this.pagewordnum = pagewordnum;
	}

	public long getReadnum() {
		return readnum;
	}

	public void setReadnum(long readnum) {
		this.readnum = readnum;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getProper() {
		return proper;
	}

	public void setProper(String proper) {
		this.proper = proper;
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

	public Date getStartctime() {
		return startctime;
	}

	public void setStartctime(Date startctime) {
		this.startctime = startctime;
	}

	public Date getEndctime() {
		return endctime;
	}

	public void setEndctime(Date endctime) {
		this.endctime = endctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Date getStartmtime() {
		return startmtime;
	}

	public void setStartmtime(Date startmtime) {
		this.startmtime = startmtime;
	}

	public Date getEndmtime() {
		return endmtime;
	}

	public void setEndmtime(Date endmtime) {
		this.endmtime = endmtime;
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

	public String getQcondition() {
		return qcondition;
	}

	public void setQcondition(String qcondition) {
		this.qcondition = qcondition;
	}
}

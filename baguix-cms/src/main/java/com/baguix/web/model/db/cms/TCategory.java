/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.model.db.cms;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

/**
* Description：Database model: 文章栏目
* 还用于构建前台网站的菜单。
* @author Scott
*/

@Entity
@Table(name = "SS_CMS_CATEGORY", schema = "")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Proxy(lazy = false)
@DynamicInsert(true)
@DynamicUpdate(true)

public class TCategory implements Serializable{
    //序列化ID
    private static final long serialVersionUID = 48L;
    //标识符
    private String id;
    
    //============================自定义字段============================
    //树形父节点
    private TCategory parent;
    //记录父节点和本节点ID字串，以逗号隔开，便于文章检索
    private String parentIdStr;
    //记录父节点和本节点名字字串，以-隔开，便于文章查看
    private String parentNameStr;
    //树形子节点集合
	private Set<TCategory> children = new HashSet<TCategory>(0);
    //标题
    private String title;
    //关键词
    private String keyword;
    //描述
    private String desc;
    //外部链接
    private String url;
    //缩略图
    private String thumb;
    //缩略图字符串
    private String thumbstr;
    //类型
    private String type;
    //级别
    private int level;

    //打开模式（_blank,_self）
    private String openmode;
    //显示模式(列表、列表+摘要、图片)
    private String showmode;
    //本栏目列表记录数（进入子栏目后，列表列出的条数）
    private int shownum;
    //首页列表显示记录数（首页列表列出的条数）
    private int homenum;
    //属性
    private String proper;
    
    //是否上线
    private boolean isonline;
    //最大子栏目排序值
    private long maxsubrank;
    //最大文章排序值
    private long maxartrank;
    
    //============================公共字段============================
    //排序值
    private long rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;
    //是否暂删
    private boolean isdel;
    
    //无参数的构造器
    public TCategory(){
        this.ctime = new Date();
    }
    
    //Setter && Getter
    @Id
	@Column(name = "CATEGORY_ID", unique = true, length = 36)
	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_PID")
	public TCategory getParent() {
		return this.parent;
	}

	public void setParent(TCategory parent) {
		this.parent = parent;
	}
	@Lob
	@Column(name = "CATEGORY_PARENTIDSTR", length = 2000)
	public String getParentIdStr() {
		return parentIdStr;
	}

	public void setParentIdStr(String parentIdStr) {
		this.parentIdStr = parentIdStr;
	}
	@Lob
	@Column(name = "CATEGORY_PARENTNAMESTR", length = 2000)
	public String getParentNameStr() {
		return parentNameStr;
	}

	public void setParentNameStr(String parentNameStr) {
		this.parentNameStr = parentNameStr;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
	public Set<TCategory> getChildren() {
		return this.children;
	}

	public void setChildren(Set<TCategory> children) {
		this.children = children;
	}
	
	
    @Column(name = "CATEGORY_TITLE", length = 200)
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title=title;
    }
	
	@Lob
    @Column(name = "CATEGORY_KEYWORD", length = 2000)
    public String getKeyword(){
        return keyword;
    }
    
    public void setKeyword(String keyword){
        this.keyword=keyword;
    }
	
    @Column(name = "CATEGORY_DESC", length = 2000)
    
    public String getDesc(){
        return desc;
    }
    
    public void setDesc(String desc){
        this.desc=desc;
    }
	
    @Column(name = "CATEGORY_URL", length = 200)
    
    public String getUrl(){
        return url;
    }
    
    public void setUrl(String url){
        this.url=url;
    }
	
    @Column(name = "CATEGORY_THUMB", length = 200)
    public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	@Lob
	@Column(name = "CATEGORY_THUMBSTR", length = 2000)
	public String getThumbstr() {
		return thumbstr;
	}

	public void setThumbstr(String thumbstr) {
		this.thumbstr = thumbstr;
	}

	@Column(name = "CATEGORY_TYPE", length = 200)
	
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CATEGORY_LEVEL", length = 200)
    
    public int getLevel(){
        return level;
    }
    
    public void setLevel(int level){
        this.level=level;
    }
	
    @Column(name = "CATEGORY_OPENMODE", length = 20)
    
    public String getOpenmode(){
        return openmode;
    }
    
    public void setOpenmode(String openmode){
        this.openmode=openmode;
    }
	
	@Lob
    @Column(name = "CATEGORY_SHOWMODE", length = 2000)
    
    public String getShowmode(){
        return showmode;
    }
    
    public void setShowmode(String showmode){
        this.showmode=showmode;
    }
	
	@Column(name = "CATEGORY_SHOWNUM")
	public void setShownum(int shownum) {
		this.shownum = shownum;
	}
    
    public int getShownum() {
		return shownum;
	}
    
	
    @Column(name = "CATEGORY_HOMENUM")
    public int getHomenum() {
		return homenum;
	}

	public void setHomenum(int homenum) {
		this.homenum = homenum;
	}
	
	@Lob
    @Column(name = "CATEGORY_PROPERTIES", length=2000)
	public String getProper() {
		return proper;
	}

	public void setProper(String proper) {
		this.proper = proper;
	}


	@Column(name = "CATEGORY_ISONLINE")
    @org.hibernate.annotations.Type(type="yes_no")
    public boolean getIsonline() {
		return isonline;
	}

	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}
	
	@Column(name = "CATEGORY_MAXSUBRANK")
	public long getMaxsubrank() {
		return maxsubrank;
	}

	public void setMaxsubrank(long maxsubrank) {
		this.maxsubrank = maxsubrank;
	}
	
	@Column(name = "CATEGORY_MAXARTICLERANK")
	public long getMaxartrank() {
		return maxartrank;
	}

	public void setMaxartrank(long maxartrank) {
		this.maxartrank = maxartrank;
	}

	@Column(name = "CATEGORY_ISDEL")
    @org.hibernate.annotations.Type(type="yes_no")
    public boolean getIsdel(){
        return isdel;
    }
    
    public void setIsdel(boolean isdel){
        this.isdel=isdel;
    }
    
    @Column(name = "CATEGORY_RANK", length=20)
    public long getRank() {
        return rank;
    }
    
    public void setRank(long rank) {
        this.rank = rank;
    }
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CATEGORY_CTIME")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPDICT_MTIME")
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}


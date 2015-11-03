/*
 * Copyright (c) 2014-8-15 Scott All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Description: 组织机构/人事关系父类 
 * 部门(TDept)、人员(TUser)继承映射实现多对多组织机构。
 * 中间表独立成一个表，将多对多拆分成两个多对一。
 * 
 * @author Scott
 */

@Entity
@Table(name = "SS_CORE_ORG", schema = "")
//继承影射，每个实体类建立一个独立表
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

@DynamicInsert(true)
@DynamicUpdate(true)

public class TOrg implements java.io.Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	
	// 标识符
	private String id;
	// 名称
	private String name;
	// 组织机构代码/编号
	private String num;
	// 地址
	private String address;
	// 邮编
	private String zip;
	// 电话号码
	private String phone;
	// 电子邮件
	private String email;
	// 传真
	private String fax;
	// 简介
	private String note;
	
	//级别，用于限制上下级的约束关系
	private int level;
	
//===========================自身多对多========================
	// 父
	private Set<TOrgRel> parents;
	// 子
	private Set<TOrgRel> children;
	
//==========================岗位多对多==========================
	private Set<TOrgPost> orgpost;
	

//============================公共字段============================
    //是否暂删
    private boolean isdel;
    //排序值
    private int rank;
    //创建时间
    private Date ctime;
    //修改时间
    private Date mtime;	
	
	//Setter && Getter
	@Id
	@Column(name = "ORG_ID", nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "ORG_NAME", length = 500)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "ORG_NUM", length = 50)
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	@Column(name = "ORG_ADDRESS", length = 50)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "ORG_ZIP", length = 50)
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Column(name = "ORG_PHONE", length = 50)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "ORG_EMAIL", length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "ORG_FAX", length = 50)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name = "ORG_NOTE", length = 50)
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	@Column(name = "ORG_LEVEL", length = 50)
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public Set<TOrgRel> getParents() {
		return parents;
	}
	public void setParents(Set<TOrgRel> parents) {
		this.parents = parents;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "child")
	public Set<TOrgRel> getChildren() {
		return children;
	}
	public void setChildren(Set<TOrgRel> children) {
		this.children = children;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "org")
	public Set<TOrgPost> getOrgpost() {
		return orgpost;
	}
	public void setOrgpost(Set<TOrgPost> orgpost) {
		this.orgpost = orgpost;
	}
	
    @Column(name = "ORG_ISDEL")
    @org.hibernate.annotations.Type(type="yes_no")
    public boolean getIsdel(){
        return isdel;
    }
    
    public void setIsdel(boolean isdel){
        this.isdel=isdel;
    }
    
    @Column(name = "ORG_RANK")
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORG_CTIME")
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORG_MTIME")
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}

/*
 * Copyright (c) 2014-8-15 Scott All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * Description：人员类
 * @author Scott
 * 2014-8-15
 */

/*
//集合成一张总表的配法
@Entity
@DiscriminatorValue("user")
*/
//每个子类一张表的配法
@Entity
@Table(name = "SS_CORE_USER", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)


@TypeDefs( {
	  @TypeDef(name = "encryptedString",
	      typeClass = org.jasypt.hibernate4.type.EncryptedStringType.class, 
	      parameters = {
	        @Parameter(name = "encryptorRegisteredName",
	            value = "strongHibernateEncryptor")
	      })
})

public class TUser extends TOrg implements java.io.Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	
// ==================================基本信息（其他信息从TOrg继承）==================================
	// 性别
	private String sex;
	// 出生日期
	private Date birthday;
	// 头像
	private String photo;
	// 头像曾经上传过的图片
	private String photostr;
	// 手机
	private String mobile;

// ==================================系统安全==================================
	// 登录名
	private String uid;
	// 密码
	private String password;
	// 系统访问时间
	private Date accessTimeBe;
	private Date accessTimeEn;
	private int accessTimeFlag;
	// 系统访问IP
	private String accessIp;
	private int accessIpFlag;
	// 是否禁止冻结用户账户
	private int activeFlag;
	private int freezeFlag;

	// 角色多对多
	private Set<TUserRole> userRoles;

	// Setter && Getter
	
	@Column(name = "USER_SEX", length = 10)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "USER_PHOTO", length = 100)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Lob
	@Column(name = "USER_PHOTOSTR", length = 2000)
	public String getPhotostr() {
		return photostr;
	}

	public void setPhotostr(String photostr) {
		this.photostr = photostr;
	}
	
	@Column(name = "USER_MOBILE", length = 100)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "USER_UID", nullable=false,unique=true, length = 100)
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "USER_PASSWORD", length = 500)
	@Type(type = "encryptedString")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_ACCESSTIME_BEGIN")
	public Date getAccessTimeBe() {
		return accessTimeBe;
	}

	public void setAccessTimeBe(Date accessTimeBe) {
		this.accessTimeBe = accessTimeBe;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_ACCESSTIME_END")
	public Date getAccessTimeEn() {
		return accessTimeEn;
	}

	public void setAccessTimeEn(Date accessTimeEn) {
		this.accessTimeEn = accessTimeEn;
	}
	
	@Column(name = "USER_ACCESSTIME_FLAG")
	public int getAccessTimeFlag() {
		return accessTimeFlag;
	}

	public void setAccessTimeFlag(int accessTimeFlag) {
		this.accessTimeFlag = accessTimeFlag;
	}
	
	@Column(name = "USER_ACCESS_IP")
	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	
	@Column(name = "USER_ACCESSIP_FLAG")
	public int getAccessIpFlag() {
		return accessIpFlag;
	}

	public void setAccessIpFlag(int accessIpFlag) {
		this.accessIpFlag = accessIpFlag;
	}
	
	@Column(name = "USER_ACTIVE_FLAG")
	public int getActiveFlag() {
		return activeFlag;
	}
	
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	@Column(name = "USER_FREEZE_FLAG")
	public int getFreezeFlag() {
		return freezeFlag;
	}

	public void setFreezeFlag(int freezeFlag) {
		this.freezeFlag = freezeFlag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<TUserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<TUserRole> userRoles) {
		this.userRoles = userRoles;
	}
}

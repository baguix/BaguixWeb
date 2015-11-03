/*
 * Copyright (c) 2014-8-15 Scott All Rights Reserved!
 */
package com.baguix.web.model.db.core;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Description：部门类
 * 
 * @author Scott
 */

@Entity
@Table(name = "SS_CORE_DEPT", schema = "")

@DynamicInsert(true)
@DynamicUpdate(true)

public class TDept extends TOrg implements java.io.Serializable {
	// 序列化ID
	private static final long serialVersionUID = 48L;
	
	//大部分属性从TOrg继承
	
	// Setter && Getter
	
	
}

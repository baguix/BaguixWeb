/*
 * Copyright (c) 2014-4-4 Scott and SSStudio All Rights Reserved!
 */

package com.baguix.web.model.page.manctrl;

/**
* Description：Page model: 首页风格
* @author Scott
*/

public class HomeStyle {
	//首页logo款式
	private String logostyle;
	//页头logo图片或Flash路径
	private String logo;
	//首页菜单款式
	private String menustyle;
	//首页幻灯片款式
	private String slidestyle;
	//套装款式
	private String webstyle;
	
	//Setter && Getter
	public String getLogostyle() {
		return logostyle;
	}
	public void setLogostyle(String logostyle) {
		this.logostyle = logostyle;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getMenustyle() {
		return menustyle;
	}
	public void setMenustyle(String menustyle) {
		this.menustyle = menustyle;
	}
	public String getSlidestyle() {
		return slidestyle;
	}
	public void setSlidestyle(String slidestyle) {
		this.slidestyle = slidestyle;
	}
	public String getWebstyle() {
		return webstyle;
	}
	public void setWebstyle(String webstyle) {
		this.webstyle = webstyle;
	}
}

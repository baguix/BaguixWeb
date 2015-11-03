/*
 * Copyright (c) 2014-1-24 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.taglib;

import com.baguix.web.common.cache.SysData;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * <b>根据字典名列出字典</b><br>
 *
 * @author Scott
 */
public class DictionaryTag extends BodyTagSupport {
	private static final long serialVersionUID = 48L;
	private String id = "";
	private String name = "";
	
	//字典名称
	private String dictname = "";
	private String style = "";
	
	//初值
	private String init = "";

	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspTagException {
		try {
			String html  = SysData.dictMap.get(this.getDictname());
			if(StringUtils.isEmpty(init) ){
				//无初值时，去掉标签后面跟随的赋值语句
				if(html.indexOf("initBegin")>0){
					html = html.substring(0,html.indexOf("initBegin"));
				}
				html = html.replace("[$id$]", id).replace("[$name$]", name).replace("[$style$]", style);
			}else{
				//有初值时
				html = html.replace("initBegin", "").replace("initEnd", "");
				html = html.replace("[$id$]", id).replace("[$name$]", name).replace("[$style$]", style).replace("[$init$]", init);
			}
			
			pageContext.getOut().write(html);
		} catch (Exception e) {
			System.out.println(e);
		}
		return EVAL_PAGE;
	}
	
	// Setter && Getter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDictname() {
		return dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}

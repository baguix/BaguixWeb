/*
 * Copyright (c) 2014-1-24 Scott and SSStudio All Rights Reserved!
 */
package com.baguix.web.taglib;

import com.baguix.web.common.cache.SysData;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <b>根据用户ID列出后台管理系统菜单栏自定义JSP标签</b><br>
 *
 * @author Scott
 */
public class AdminMenuBarTag extends BodyTagSupport {
	private static final long serialVersionUID = 48L;
	private String id = "";
	// 执行顺序:
	// 1.doStartTag()：容器在遇开始标签的时会调用这个方法
	// 2.doInitBody()：在第一次处理标签主体内容时，他讲对主体进行初始化的工作
	// 3.setBodyContent()：提供BodyContent实例的一个引用，该实例为此标签处理类将主计算结果加以缓存
	// 4.doAfterBody()：如果标签有主体内容，容器执行标签主体后，会调用这个方法
	// 5.doEndTag()：容器在遇到结束标签时会调用这个方法
	public int doStartTag() {
		// 可以返回EVAL_BODY_INCLUDE 或 SKIP_BODY
		// 若返回SKIP_BODY则接下来的doInitBody(),setBodyContent(), doAfterBody()三个方法不会被执行，而直接执行doEndTag()方法。
		try {
			String html  = SysData.userNavMap.get(this.getId());
			pageContext.getOut().write(html);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspTagException {
		/*try {
			String html  = SysData.userNavMap.get(this.getId());
			pageContext.getOut().write(html);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return EVAL_PAGE;
	}
	
	
	// Setter && Getter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

/*
 * Copyright 2009-2010 the original author or authors.
 */
package com.baguix.web.common.cache;

import com.baguix.web.model.page.manctrl.SiteInfo;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
/**
 * 将缓存数据存入servlet. <br>
 */
public class LoadCacheServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext context;
	
	private static final Logger logger = Logger.getLogger("缓存加载");
	
	public LoadCacheServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	public void init() throws ServletException {
		context = this.getServletContext();
		loadOverview(context);
		loadSiteInfo(context);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 重载Application缓存
		loadSiteInfoFromXML();
		loadSiteInfo(context);
		// 信息响应
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE>");
		out.println("<html lang=\"zh-CN\">");
		out.println("  <head><title>Baguix</title></head>");
		out.println("  <body>");
		out.println("Baguix工作室成立于2015年10月1日，是由一群追求创新，热爱技术的爱好者共同发起成立的。成立之初，其宗旨定位为“求实，创新，分享”，工作室业务主要为Web开发。");
		out.println("  </body>");
		out.println("</html>");
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 重载Application缓存
		loadSiteInfoFromXML();
		loadSiteInfo(context);
		// 信息响应
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE>");
		out.println("<html lang=\"zh-CN\">");
		out.println("  <head><title>BaguixWeb</title></head>");
		out.println("  <body>");
		out.println("Baguix工作室成立于2015年10月1日，是由一群追求创新，热爱技术的爱好者共同发起成立的。成立之初，其宗旨定位为“求实，创新，分享”，工作室业务主要为Web开发。");
		out.println("  </body>");
		out.println("</html>");
		out.flush();
		out.close();
		out.flush();
		out.close();
	}

	private void loadSiteInfoFromXML(){
		SiteInfo si = new SiteInfo();
		SysData.siteInfo = si.fromXMLFile();
		logger.debug("SiteInfo.xml文件缓存成功！");
	}

	private void loadSiteInfo(ServletContext context){
		SiteInfo si = SysData.siteInfo;
		Map<String, String > siteinfo  = si.toHashMap(si);
		logger.debug("系统Application域即将缓存siteinfo数据。");
		// 遍历siteinfo.properties
		for (Map.Entry<String,String> entry: siteinfo.entrySet()){
			context.setAttribute(entry.getKey(), entry.getValue());
		}
		logger.debug("siteinfo缓存成功！");
	}

	private void loadOverview(ServletContext context){
		Map<String, String > overview  = SysData.overview;
		logger.debug("系统Application域即将缓存overview数据。");
		// 遍历siteinfo.properties
		for (Map.Entry<String,String> entry: overview.entrySet()){
			context.setAttribute(entry.getKey(), entry.getValue());
		}
		logger.debug("overview缓存成功！");
	}
}
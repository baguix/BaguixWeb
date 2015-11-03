package com.baguix.web.service.cms;
import java.util.List;

import com.baguix.web.model.db.cms.TArticle;
import com.baguix.web.model.page.cms.Article;
import com.baguix.web.model.page.cms.Category;
import com.baguix.web.service.BaseServiceI;

public interface GetArticleServiceI  extends BaseServiceI<TArticle>{
	/**
	 * 根据ID找该栏目
	 * @param cid
	 * @return
	 */
	public Category getCategory(String cid);
	
	/**
	 * 根据标题找该栏目
	 * @param cid
	 * @return
	 */
	public Category getCategoryByTitle(String ctitle);
	
	/**
	 * 根据属性列出栏目框和栏目里的文章（按rank值排序）
	 * @param prop
	 * @return - 栏目列表
	 */
	public List<Category> listCategoryByProp(String prop);
	
	/**
	 * 列出最新(全局)
	 * @param count - 列出条数
	 * @return - 文章列表
	 */
	public List<Article> listNew(int count, String type);
	
	/**
	 * 列出最新(单栏目)
	 * @param count - 列出条数
	 * @return - 文章列表
	 */
	public List<Article> listNew(String cid, int count, String type);
	
	/**
	 * 根据栏目标题列出最新(单栏目)
	 * @param count - 列出条数
	 * @return - 文章列表
	 */
	public List<Article> listNewByTitle(String ctitle, int count, String type);
	
	/**
	 * 列出最热门(全局)
	 * @param count - 列出条数
	 * @return - 文章列表
	 */
	public List<Article> listHot(int count, String type);
	
	/**
	 * 列出最热门(单栏目)
	 * @param count - 列出条数
	 * @return - 文章列表
	 */
	public List<Article> listHot(String cid, int count, String type);
	
	/**
	 * 根据栏目标题列出最热门(单栏目)
	 * @param count - 列出条数
	 * @return - 文章列表
	 */
	public List<Article> listHotByTitle(String ctitle, int count, String type);
	
	/**
	 * 根据ID找该文章
	 * @param cid
	 * @return
	 */
	public Article getArticle(String id);
	
	
	/**
	 * 根据属性列出符合条件的文章
	 * @param prop
	 * @return - 栏目列表
	 */
	public List<Article> listArticleByProp(String prop);
	
	/**
	 * 列出所有网站公告
	 * @return - 公告栏
	 */
	
	public List<Article> listAnnouncement();
	/**
	 * 按数量列出网站公告
	 * @return - 公告栏
	 */
	public List<Article> listAnnouncement(int count);
}
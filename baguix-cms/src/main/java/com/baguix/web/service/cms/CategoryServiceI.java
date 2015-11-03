package com.baguix.web.service.cms;

import java.util.List;

import com.baguix.web.model.db.cms.TCategory;
import com.baguix.web.model.page.cms.Category;
import com.baguix.web.service.BaseServiceI;


public interface CategoryServiceI extends BaseServiceI<TCategory>{
	
	public Category add(Category category);
	public Category edit(Category category);
	//临时删
	public void delete(String id);
	//彻底删
	public void remove(String id);
	
	//重置栏目排序值
	public void resetRank(String id);
	
	/**
	 * 根据目录ID获得该目录下的子节点
	 * 用于异步加载
	 * @param id
	 * @return
	 */
	public List<Category> getCategoryTree(String id);
	
	/**
	 * 根据目录ID获得该目录下所有子树(TreeGrid)
	 * @param id
	 * @return
	 */
	public List<Category> getSubCategoryTree(String pid);
	
	/**
	 * 根据目录ID获得该目录下所有子树(Tree)
	 * @param id
	 * @return
	 */
	public List<Category> getTree(String pid);
	
	/**
	 * 复制数据库模型为页面模型
	 * @param t
	 * @param p
	 */
	public void changeModel(TCategory t, Category p);
	public void changeModel(Category p, TCategory t);
}

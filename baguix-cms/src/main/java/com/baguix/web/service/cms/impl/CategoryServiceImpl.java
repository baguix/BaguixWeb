package com.baguix.web.service.cms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baguix.web.service.impl.BaseServiceImpl;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.cms.TCategory;
import com.baguix.web.model.page.cms.Category;
import com.baguix.web.service.cms.CategoryServiceI;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<TCategory> implements CategoryServiceI {
	
	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);
	
	private BaseDaoI<TCategory> dao;
	
	public BaseDaoI<TCategory> getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(BaseDaoI<TCategory> dao) {
		this.dao = dao;
	}

	@Override
	public Category add(Category category) {
		TCategory t = new TCategory();
		String pid = category.getPid();
		TCategory parent = dao.getById(TCategory.class, pid);
		if(parent != null){
			//如果子栏目的排序值大于父栏目的最大子栏目排序值，则更新父栏目的子栏目最大排序值
			if(parent.getMaxsubrank() < category.getRank()){
				parent.setMaxsubrank(category.getRank());
				dao.update(parent);
			}
			changeModel(category,t);
			t.setId(UUID.randomUUID().toString());
			
			t.setParentIdStr(parent.getParentIdStr()+","+t.getId());
			if(parent.getId().equals("news")){
				t.setParentNameStr(t.getTitle());
			}
			else{
				t.setParentNameStr(parent.getParentNameStr()+"＞"+t.getTitle());
			}
			t.setCtime(new Date());
			t.setType("news");
			t.setParent(parent);
			t.setMaxsubrank(0);
			dao.save(t);
			//补全对象内容
			category.setIconCls("icon-category");
			category.setId(t.getId());
		}
		return category;
	}

	@Override
	public Category edit(Category p) {
		TCategory t = dao.getById(TCategory.class, p.getId());
		if(t != null){
			t.setTitle(p.getTitle());
			t.setKeyword(p.getKeyword());
			t.setDesc(p.getDesc());
			t.setUrl(p.getUrl());
			t.setThumb(p.getThumb());
			t.setThumbstr(p.getThumbstr());
			t.setLevel(p.getLevel());
			t.setOpenmode(p.getOpenmode());
			t.setShowmode(p.getShowmode());
			t.setShownum(p.getShownum());
			t.setHomenum(p.getHomenum());
			t.setProper(p.getProper());
			t.setIsonline(p.getIsonline());
			t.setRank(p.getRank());
			t.setMtime(new Date());
			t.setType("news");
			dao.saveOrUpdate(t);
		}
		TCategory parent = t.getParent();
		if(parent != null){
			//如果子栏目的排序值大于父栏目的最大子栏目排序值，则更新父栏目的子栏目最大排序值
			if(parent.getMaxsubrank() < p.getRank()){
				parent.setMaxsubrank(p.getRank());
				dao.update(parent);
			}
		}
		return p;
	}
	
	@Override
	public void delete(String id) {
		// 如果有子栏目，先递归删除子栏目，再删自己
		TCategory t = dao.getById(TCategory.class, id);
		if (t.getChildren() != null) {
			if (t.getChildren().size() > 0) {
				for(TCategory c: t.getChildren()){
					if(!c.getIsdel()){
						delete(c.getId());
					}
				}
			}
		}
		//删除自己
		String hql = "update TCategory t set t.isdel=true where t.id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		dao.executeHql(hql,params);
	}

	@Override
	public void remove(String id) {
		// 如果有子栏目，先递归删除子栏目，再删自己
		TCategory t = dao.getById(TCategory.class, id);
		if (t.getChildren() != null) {
			if (t.getChildren().size() > 0) {
				for (TCategory c : t.getChildren()) {
					remove(c.getId());
				}
			}
		}
		// 删除自己
		String hql = "delete TCategory t where t.id =:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		dao.executeHql(hql,params);
	}
	
	@Override
	public void resetRank(String id){
		TCategory node = dao.getById(TCategory.class, id);
		String hql = "from TCategory t where t.type='news' and t.parent.id=:id and t.isdel=false order by t.rank asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<TCategory> child = dao.find(hql,params);
		if(child != null && !child.isEmpty()){
			node.setMaxsubrank( 10*child.size() );
			for(int i=0;i<child.size();i++){
				TCategory t = child.get(i);
				t.setRank(10*(i+1));
				dao.saveOrUpdate(t);
				resetRank(t.getId());
			}
			dao.saveOrUpdate(node);
		}
		
	}

	@Override
	public List<Category> getCategoryTree(String id) {
		List<Category> nl = new ArrayList<Category>();
		try{
			String hql = null;
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isEmpty(id)) {
				// 查询根目录
				hql = "from TCategory t where t.type='news' and t.level=0 and t.isdel=false order by t.rank asc";
			} else {
				hql = "from TCategory t where t.parent.id=:id and t.type='news' and t.isdel=false";
				params.put("id", id);
			}
			List<TCategory> l = dao.find(hql, params);
			if (l != null && l.size() > 0) {
				for (TCategory t : l) {
					Category p = new Category();
					BeanUtils.copyProperties(t, p);
					Set<TCategory> set = t.getChildren();
					if (set != null && !set.isEmpty()) {
						p.setIconCls(null);
						// 节点以打开文件夹的形式体现
						p.setState("closed"); 
					} else {
						// 节点以关闭文件夹的形式体现
						p.setState("open"); 
					}
					p.setIconCls("icon-category");
					nl.add(p);
				}
			}
		}
		catch (Exception e) {
			logger.error(e);
		} 
		return nl;
	}

	@Override
	public List<Category> getSubCategoryTree(String pid) {
		List<Category> nl = new ArrayList<Category>();
		try {
			String hql = "from TCategory t where t.parent.id=:pid and t.type='news' and t.level>=0 and t.isdel=false order by t.rank asc, t.ctime desc, t.mtime desc";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", pid);
			List<TCategory> l = dao.find(hql,params);
			if (l != null && l.size() > 0) {
				for (TCategory t : l) {
					Set<TCategory> set = t.getChildren();
					Category p = new Category();
					changeModel(t, p);
					Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("url", t.getUrl());
					p.setAttributes(attributes);
					p.setIconCls("icon-category");
					if (set != null && !set.isEmpty()) {
						p.setChildren(getSubCategoryTree(p.getId()));
					}
					nl.add(p);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return nl;
	}
	
	
	@Override
	public List<Category> getTree(String pid){
		List<Category> nl = new ArrayList<Category>();
		try {
			String hql = "from TCategory t where t.parent.id=:pid and t.type='news' and t.level>=0 and t.isdel=false order by t.rank asc, t.ctime desc, t.mtime desc";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", pid);
			List<TCategory> l = dao.find(hql,params);
			if (l != null && l.size() > 0) {
				for (TCategory t : l) {
					Set<TCategory> set = t.getChildren();
					Category p = new Category();
					p.setId(t.getId());
					p.setText(t.getTitle());
					p.setPid(t.getParent().getId());
					Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("cid", p.getId());
					attributes.put("cids", t.getParentIdStr());
					attributes.put("cnames", t.getParentNameStr());
					p.setIconCls("icon-category");
					if (set != null && !set.isEmpty()) {
						attributes.put("haschild", "1");
						p.setChildren(getTree(p.getId()));
					}
					else {
						attributes.put("haschild", "0");
					}
					p.setAttributes(attributes);
					nl.add(p);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return nl;
	}

	/**
	 * 手动转换Category和TCategory
	 * @param t 数据库模型
	 * @param p 页面模型
	 */
	@Override
	public void changeModel(TCategory t, Category p){
		String[] ignore ={"children","parent","parentIdStr"};
		BeanUtils.copyProperties(t, p, ignore);
		if(t.getParent()!=null){
			p.setPid(t.getParent().getId());
		}
	}

	/**
	 * 手动转换Category和TCategory
	 * @param p 页面模型
	 * @param t 数据库模型
	 */
	@Override
	public void changeModel(Category p, TCategory t){
		String[] ignore ={"children","parent","parentIdStr"};
		BeanUtils.copyProperties(p, t, ignore);
	}
}

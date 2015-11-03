package com.baguix.web.service.cms.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.cms.TArticle;
import com.baguix.web.model.db.cms.TCategory;
import com.baguix.web.model.page.cms.Article;
import com.baguix.web.model.page.cms.Category;
import com.baguix.web.service.cms.GetArticleServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;

@Service("getArticleService")
public class GetArticleServiceImpl extends BaseServiceImpl<TArticle> implements GetArticleServiceI {
	
	private BaseDaoI<TArticle> dao;
	
	@Autowired
	public void setDao(BaseDaoI<TArticle> dao) {
		this.dao = dao;
	}
	private BaseDaoI<TCategory> cdao;
	
	@Autowired
	public void setCdao(BaseDaoI<TCategory> cdao) {
		this.cdao = cdao;
	}

	@Override
	public Category getCategory(String cid) {
		Category pc =  new Category();
		TCategory tc = cdao.getById(TCategory.class, cid);
		if (tc != null){
			BeanUtils.copyProperties(tc, pc);
		}
		return pc;
	}
	
	@Override
	public Category getCategoryByTitle(String ctitle) {
		Category pc =  new Category();
		String hql = "from TCategory t where " +
				"t.isonline=true and t.title=:title";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", ctitle);
		TCategory tc = cdao.getByHql(hql,params);
		if (tc != null){
			BeanUtils.copyProperties(tc, pc);
		}
		return pc;
	}

	@Override
	public List<Category> listCategoryByProp(String prop) {
		List<Category> pc =  new ArrayList<Category>();
		String hql = "from TCategory t where " +
				"t.isonline=true and t.type=:type and t.proper like :prop " +
				"order by t.rank";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type","news");
		params.put("prop", "%%"+prop+"%%");
		List<TCategory> tc = cdao.find(hql,params);
		if(tc != null){
			changeCategoryModel(tc, pc);
		}
		return pc;
	}

	@Override
	public List<Article> listNew(int count, String type) {
		List<Article> pl = new ArrayList<Article>();
		String hql = "from TArticle t where " +
				"t.state=true and t.type=:type " +
				"order by t.ctime desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		List<TArticle> tl = dao.find(hql,params,0,count);
		if(tl !=null){
			changeModel(tl, pl);
		}
		return pl;
	}
	
	@Override
	public List<Article> listNew(String cid, int count, String type) {
		List<Article> pl = new ArrayList<Article>();
		String hql = "from TArticle t where " +
				"t.state=true and t.type=:type and t.categoryids like :cid " +
				"order by t.ctime desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("cid", "%%"+cid+"%%");
		List<TArticle> tl = dao.find(hql,params,0,count);
		if(tl !=null){
			changeModel(tl, pl);
		}
		return pl;
	}
	
	@Override
	public List<Article> listNewByTitle(String ctitle, int count, String type) {
		List<Article> pl = new ArrayList<Article>();
		Category c = getCategoryByTitle(ctitle);
		if(c != null){
			pl = listNew(c.getId(),count,type); 
		}
		return pl;
	}
	
	@Override
	public List<Article> listHot(int count, String type) {
		List<Article> pl = new ArrayList<Article>();
		String hql = "from TArticle t where " +
				"t.state=true and t.type=:type " +
				"order by t.readnum desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		List<TArticle> tl = dao.find(hql,params,0,count);
		if(tl !=null){
			changeModel(tl, pl);
		}
		return pl;
	}
	
	@Override
	public List<Article> listHot(String cid, int count, String type) {
		List<Article> pl = new ArrayList<Article>();
		String hql = "from TArticle t where " +
				"t.state=true and t.type=:type and t.categoryids like :cid " +
				"order by t.readnum desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("cid", "%%"+cid+"%%");
		List<TArticle> tl = dao.find(hql,params,0,count);
		if(tl !=null){
			changeModel(tl, pl);
		}
		return pl;
	}
	
	@Override
	public List<Article> listHotByTitle(String ctitle, int count, String type) {
		List<Article> pl = new ArrayList<Article>();
		Category c = getCategoryByTitle(ctitle);
		if(c != null){
			pl = listHot(c.getId(),count,type); 
		}
		return pl;
	}

	@Override
	public Article getArticle(String id) {
		Article p = new Article();
		TArticle t = dao.getById(TArticle.class, id);
		if(t != null){
			BeanUtils.copyProperties(t, p);
		}
		return p;
	}

	@Override
	public List<Article> listArticleByProp(String prop) {
		List<Article> p =  new ArrayList<Article>();
		String hql = "from TArticle t where " +
				"t.state=true and t.type=:type and t.proper like :prop " +
				"order by t.ctime";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type","news");
		params.put("prop", "%%"+prop+"%%");
		List<TArticle> t = dao.find(hql,params);
		if(t != null){
			changeModel(t, p);
		}
		return p;
	}
	
	@Override
	public List<Article> listAnnouncement() {
		List<Article> p =  new ArrayList<Article>();
		String hql = "from TArticle t where " +
				"t.state=true and t.type=:type " +
				"order by t.ctime";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type","announcement");
		List<TArticle> t = dao.find(hql,params);
		if(t != null){
			changeModel(t, p);
		}
		return p;
	}

	@Override
	public List<Article> listAnnouncement(int count) {
		List<Article> p =  new ArrayList<Article>();
		String hql = "from TArticle t where " +
				"t.state=true and t.type=:type " +
				"order by t.ctime";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type","announcement");
		List<TArticle> t = dao.find(hql,params,0,count);
		if(t != null){
			changeModel(t, p);
		}
		return p;
	}

	//private
	private void changeModel(List<TArticle> l, List<Article> nl) {
		if (l != null && l.size() > 0) {
			for (TArticle t : l) {
				Article u = new Article();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}
	
	private void changeCategoryModel(List<TCategory> l, List<Category> nl) {
		if (l != null && l.size() > 0) {
			for (TCategory t : l) {
				Category u = new Category();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}
}

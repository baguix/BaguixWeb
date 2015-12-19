package com.baguix.web.service.cms.impl;

import com.baguix.utils.db.QueryConditionFilter;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.cms.TSinglePage;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.cms.Article;
import com.baguix.web.service.cms.SinglePageServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("singlePageService")
public class SinglePageServiceImpl extends BaseServiceImpl<TSinglePage> implements SinglePageServiceI {
	
	private BaseDaoI<TSinglePage> dao;
	
	public BaseDaoI<TSinglePage> getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(BaseDaoI<TSinglePage> dao) {
		this.dao = dao;
	}
	
	@Override
	synchronized public Article add(Article art) {
		TSinglePage t = new TSinglePage();
		BeanUtils.copyProperties(art, t);
		t.setId(UUID.randomUUID().toString());
		t.setCtime(new Date());
		t.setMtime(new Date());
		dao.save(t);
		BeanUtils.copyProperties(t, art);
		art.setContent(null);
		art.setAbstracts(null);
		return art;
	}
	
	@Override
	public void delete(String ids) {
		String[] nids = ids.split(",");
		String hql = "update TSinglePage t set t.isdel=true where and  t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		dao.executeHql(hql);
	}
	
	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete TSinglePage t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		dao.executeHql(hql);
	}

	@Override
	synchronized public Article edit(Article art) {
		TSinglePage t = new TSinglePage();
		BeanUtils.copyProperties(art, t);
		t.setMtime(new Date());
		dao.saveOrUpdate(t);
		BeanUtils.copyProperties(t, art);
		art.setContent("");
		art.setAbstracts("");
		return art;
	}

	@Override
	public Article view(Article art) {
		TSinglePage t = dao.getById(TSinglePage.class, art.getId());
		BeanUtils.copyProperties(t, art);
		return art;
	}
	
	@Override
	public Article view(String id) {
		Article art = new Article();
		TSinglePage t = dao.getById(TSinglePage.class, id);
		BeanUtils.copyProperties(t, art);
		return art;
	}
	
	@Override
	public DataGrid datagrid(Article art) {
		DataGrid dg = new DataGrid();
		String hql = "from TSinglePage t where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(art, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(art, hql);
		List<TSinglePage> l = dao.find(hql, params, art.getPage(), art.getRows());
		List<Article> nl = new ArrayList<Article>();
		changeModel(l, nl);
		dg.setTotal(dao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
	
	@Override
	public DataGrid search(Article art) {
		if(art.getQcondition()==null){
			return datagrid(art);
		}
		else if(StringUtils.isEmpty(art.getQcondition()) ){
			return datagrid(art);
		}
		else{
			DataGrid dg = new DataGrid();
			String hql = "from TSinglePage t where 1=1 ";
			Map<String, Object> params = new HashMap<String, Object>();
			if(art.getQcondition()!= null){
				QueryConditionFilter qcf = new QueryConditionFilter();
				qcf.addFilter(art.getQcondition());
				hql += qcf.getWhereHql();
				params = qcf.getParams();
				if (art.getSort() != null) {
					hql += " order by t." + art.getSort() + " " + art.getOrder() + ", "+ qcf.getOrderHql();
				}
				else{
					hql += " order by " + qcf.getOrderHql();
				}
			}
			String totalHql = "select count(*) " + hql;
			List<TSinglePage> l = dao.find(hql, params, art.getPage(), art.getRows());
			List<Article> nl = new ArrayList<Article>();
			changeModel(l, nl);
			dg.setTotal(dao.count(totalHql, params));
			dg.setRows(nl);
			return dg;
		}
		
	}
	
	//private
	private void changeModel(List<TSinglePage> l, List<Article> nl) {
		if (l != null && l.size() > 0) {
			for (TSinglePage t : l) {
				Article u = new Article();
				BeanUtils.copyProperties(t, u);
				u.setAbstracts("");
				u.setContent("");
				nl.add(u);
			}
		}
	}

	private String addOrder(Article art, String hql) {
		if (art.getSort() != null) {
			hql += " order by " + art.getSort() + " " + art.getOrder();
		}
		return hql;
	}

	private String addWhere(Article art, String hql, Map<String, Object> params) {
		if ( StringUtils.isNotEmpty(art.getTitle()) ) {
			hql += " where t.title like :title";
			params.put("title", "%%" + art.getTitle().trim() + "%%");
		}
		return hql;
	}
}

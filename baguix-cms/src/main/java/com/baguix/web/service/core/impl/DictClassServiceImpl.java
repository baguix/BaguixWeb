package com.baguix.web.service.core.impl;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.TDictClass;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.core.DictClass;
import com.baguix.web.service.core.DictClassServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("dictClassService")
public class DictClassServiceImpl extends BaseServiceImpl<TDictClass> implements DictClassServiceI {
	
	private BaseDaoI<TDictClass> dao;
	
	public BaseDaoI<TDictClass> getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(BaseDaoI<TDictClass> dao) {
		this.dao = dao;
	}
	
	@Override
	synchronized public DictClass add(DictClass dictClass) {
		TDictClass t = new TDictClass();
		BeanUtils.copyProperties(dictClass, t);
		t.setId(UUID.randomUUID().toString());
		t.setCtime(new Date());
		dao.save(t);
		BeanUtils.copyProperties(t, dictClass);
		return dictClass;
	}
	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete TDictClass t where t.id in (";
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
	synchronized public DictClass edit(DictClass dictClass) {
		TDictClass t = new TDictClass();
		BeanUtils.copyProperties(dictClass, t);
		t.setMtime(new Date());
		dao.saveOrUpdate(t);
		BeanUtils.copyProperties(t, dictClass);
		return dictClass;
	}

	@Override
	public DictClass view(DictClass dictClass) {
		TDictClass t = dao.getById(TDictClass.class, dictClass.getId());
		BeanUtils.copyProperties(t, dictClass);
		return dictClass;
	}
	
	@Override
	public DataGrid datagrid(DictClass dictClass) {
		DataGrid dg = new DataGrid();
		String hql = "from TDictClass t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(dictClass, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(hql);
		List<TDictClass> l = dao.find(hql, params, dictClass.getPage(), dictClass.getRows());
		List<DictClass> nl = new ArrayList<DictClass>();
		changeModel(l, nl);
		dg.setTotal(dao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public List<DictClass> list() {
		String hql = "from TDictClass t order by t.rank asc";
		List<TDictClass> l = dao.find(hql);
		List<DictClass> nl = new ArrayList<DictClass>();
		DictClass dc = new DictClass();
		dc.setTitle("全部");
		dc.setComment("列出所有字典。");
		nl.add(dc);
		changeModel(l, nl);
		return nl;
	}

	@Override
	public DictClass search(String where) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//private
	private void changeModel(List<TDictClass> l, List<DictClass> nl) {
		if (l != null && l.size() > 0) {
			for (TDictClass t : l) {
				DictClass u = new DictClass();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(String hql) {
		hql += " order by t.rank asc";
		return hql;
	}

	private String addWhere(DictClass dictClass, String hql, Map<String, Object> params) {
		if (dictClass.getTitle() != null && !dictClass.getTitle().trim().equals("")) {
			hql += " where t.title like :title";
			params.put("title", "%%" + dictClass.getTitle().trim() + "%%");
		}
		return hql;
	}
}

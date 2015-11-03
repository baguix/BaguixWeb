package com.baguix.web.service.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.core.Dict;
import com.baguix.web.service.core.DictServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<TDict> implements DictServiceI {
	
	private BaseDaoI<TDict> dao;
	
	public BaseDaoI<TDict> getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(BaseDaoI<TDict> dao) {
		this.dao = dao;
	}
	
	@Override
	synchronized public Dict add(Dict dict) {
		TDict t = new TDict();
		BeanUtils.copyProperties(dict, t);
		t.setId(UUID.randomUUID().toString());
		t.setCtime(new Date());
		dao.save(t);
		BeanUtils.copyProperties(t, dict);
		return dict;
	}
	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete TDict t where t.id in (";
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
	synchronized public Dict edit(Dict dict) {
		TDict t = new TDict();
		BeanUtils.copyProperties(dict, t);
		t.setMtime(new Date());
		dao.saveOrUpdate(t);
		BeanUtils.copyProperties(t, dict);
		return dict;
	}

	@Override
	public Dict view(Dict dict) {
		TDict t = dao.getById(TDict.class, dict.getId());
		BeanUtils.copyProperties(t, dict);
		return dict;
	}
	
	@Override
	public DataGrid datagrid(Dict dict) {
		DataGrid dg = new DataGrid();
		String hql = "from TDict t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(dict, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(dict, hql);
		List<TDict> l = dao.find(hql, params, dict.getPage(), dict.getRows());
		List<Dict> nl = new ArrayList<Dict>();
		changeModel(l, nl);
		dg.setTotal(dao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public Dict search(String where) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//private
	private void changeModel(List<TDict> l, List<Dict> nl) {
		if (l != null && l.size() > 0) {
			for (TDict t : l) {
				Dict u = new Dict();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(Dict dict, String hql) {
		if (dict.getSort() != null) {
			hql += " order by " + dict.getSort() + " " + dict.getOrder();
		}
		return hql;
	}

	private String addWhere(Dict dict, String hql, Map<String, Object> params) {
		if (dict.getName() != null && !dict.getName().trim().equals("")) {
			hql += " where t.name like :name";
			params.put("name", "%%" + dict.getName().trim() + "%%");
		}
		return hql;
	}
}

package com.baguix.web.service.manctrl.impl;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.manctrl.TNavMenu;
import com.baguix.web.model.page.manctrl.LeftTree;
import com.baguix.web.service.manctrl.NavMenuServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("navMenuService")
public class NavMenuServiceImpl extends BaseServiceImpl<TNavMenu> implements NavMenuServiceI {
	
	private BaseDaoI<TNavMenu> dao;
	
	public BaseDaoI<TNavMenu> getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(BaseDaoI<TNavMenu> dao) {
		this.dao = dao;
	}
	
	@Override
	public List<TNavMenu> findBarByRank() {
		String hql = "from TNavMenu t where t.type='bar' order by t.rank asc, t.ctime desc, t.mtime desc";
		List<TNavMenu> l = dao.find(hql);
		return l;
	}
	
	@Override
	public List<TNavMenu> findChildByRank(TNavMenu parent, String type) {
		String hql = "from TNavMenu t where t.parent.id=:pid and t.type=:type order by t.rank asc, t.ctime desc, t.mtime desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", parent.getId());
		params.put("type", type);
		List<TNavMenu> l = dao.find(hql, params);
		return l;
	}
	
	@Override
	public List<LeftTree> getLeftTreeNode(TNavMenu menu) {
		//TODO 异步加载
		List<LeftTree> lt = new ArrayList<LeftTree>();
		return lt;
	}
	
	@Override
	public List<LeftTree> getLeftTree(String pid) {
		List<LeftTree> lt = new ArrayList<LeftTree>();
		String hql = "from TNavMenu t where t.parent.id=:pid and t.type='tree' order by t.rank asc, t.ctime desc, t.mtime desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		List<TNavMenu> l = dao.find(hql, params);
		if (l != null && l.size() > 0) {
			for (TNavMenu nm : l) {
				if(nm.getHassub()){  
					//如果该节点有子节点，先将自己加入lt
					LeftTree t = new LeftTree();
					changeModel(nm,t);
					Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("url", nm.getUrl());
					t.setAttributes(attributes);
					t.setState("closed");
					
				    //再进入递归程序，将子节点加入lt
					t.setChildren(getLeftTree(nm.getId()));
					
					lt.add(t);
				}
				else{
					LeftTree t = new LeftTree();
					changeModel(nm,t);
					Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("url", nm.getUrl());
					t.setAttributes(attributes);
					lt.add(t);
				}
			}
		}
		return lt;
	}
	
	//private
	/**
	 * 手动转换LeftTree和TNavMenu
	 * @param tm 导航菜单节点
	 * @param lt 左树
	 */
	private void changeModel(TNavMenu tm, LeftTree lt){
		lt.setId(tm.getId());
		lt.setText(tm.getTitle());
		lt.setPid(tm.getParent().getId());
		lt.setPtext(tm.getParent().getTitle());
		lt.setIconCls(tm.getIcon());
	}
}

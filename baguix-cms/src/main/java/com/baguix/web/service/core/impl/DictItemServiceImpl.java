package com.baguix.web.service.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.service.core.DictItemServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;

@Service("dictItemService")
public class DictItemServiceImpl extends BaseServiceImpl<TDictItem> implements DictItemServiceI {
	
	private BaseDaoI<TDictItem> dao;
	
	public BaseDaoI<TDictItem> getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(BaseDaoI<TDictItem> dao) {
		this.dao = dao;
	}
}

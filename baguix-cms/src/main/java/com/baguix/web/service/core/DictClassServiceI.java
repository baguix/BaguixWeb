package com.baguix.web.service.core;

import com.baguix.web.model.db.core.TDictClass;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.core.Dict;
import com.baguix.web.model.page.core.DictClass;
import com.baguix.web.service.BaseServiceI;

import java.util.List;

public interface DictClassServiceI extends BaseServiceI<TDictClass> {
	
	//Web
	public DictClass add(DictClass dictClass);
	public void remove(String ids);
	public DataGrid datagrid(DictClass dictClass);
	public List<DictClass> list();
	public DictClass edit(DictClass dictClass);
	public DictClass view(DictClass dictClass);
	public DictClass search(String where);
}
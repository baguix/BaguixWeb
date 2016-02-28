package com.baguix.web.service.core;

import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.core.Dict;
import com.baguix.web.service.BaseServiceI;

public interface DictServiceI  extends BaseServiceI<TDict> {
	
	// 管理
	Dict add(Dict dict);
	Dict edit(Dict dict);
	void delete(String ids);
	void remove(String ids);
	// 查看
	DataGrid datagrid(Dict dict);
	Dict view(Dict dict);
	Dict search(String where);
	// 系统缓存管理
	void inCache(Dict dict);
	void clsCache(String dictName);
}
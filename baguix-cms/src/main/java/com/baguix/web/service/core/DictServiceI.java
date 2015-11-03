package com.baguix.web.service.core;

import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.core.Dict;
import com.baguix.web.service.BaseServiceI;

public interface DictServiceI  extends BaseServiceI<TDict> {
	
	//Web
	public Dict add(Dict dict);
	public void remove(String ids);
	public DataGrid datagrid(Dict dict);
	public Dict edit(Dict dict);
	public Dict view(Dict dict);
	public Dict search(String where);
}
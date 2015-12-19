package com.baguix.web.service.cms;

import com.baguix.web.model.db.cms.TNews;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.cms.Article;
import com.baguix.web.service.BaseServiceI;

public interface NewsServiceI  extends BaseServiceI<TNews>{
	Article add(Article art);
	void remove(String ids);
	void delete(String ids);
	DataGrid datagrid(Article art);
	Article edit(Article art);
	Article view(Article art);
	Article view(String id);
	DataGrid search(Article art);
}
package com.baguix.web.service.cms;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.cms.Article;
import com.baguix.web.model.db.cms.TArticle;
import com.baguix.web.service.BaseServiceI;

public interface AnnouncementServiceI  extends BaseServiceI<TArticle>{
	
	//web
	public Article add(Article art);
	public void remove(String ids);
	public void delete(String ids);
	public DataGrid datagrid(Article art);
	public Article edit(Article art);
	public Article view(Article art);
	public Article view(String id);
	public DataGrid search(Article art);
}
package com.baguix.web.service.manctrl;
import java.util.List;

import com.baguix.web.model.db.manctrl.TNavMenu;
import com.baguix.web.model.page.manctrl.LeftTree;
import com.baguix.web.service.BaseServiceI;

public interface NavMenuServiceI  extends BaseServiceI<TNavMenu>{
	//TagLib
	/**
	 * 获得导航栏按钮
	 */
	public List<TNavMenu> findBarByRank();
	/**
	 * 获得子菜单
	 */
	public List<TNavMenu> findChildByRank(TNavMenu parent, String type);
	
	
	//LeftTree
	/**
	 * 获得该菜单下的子树
	 * @param pid
	 * @return
	 */
	public List<LeftTree> getLeftTree(String pid);
	
	/**
	 * 异步加载子节点
	 * @param menu
	 * @return
	 */
	public List<LeftTree> getLeftTreeNode(TNavMenu menu);
	
}
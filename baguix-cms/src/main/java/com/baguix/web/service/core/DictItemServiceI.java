package com.baguix.web.service.core;

import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.page.core.DictItem;
import com.baguix.web.service.BaseServiceI;

import java.util.List;

public interface DictItemServiceI  extends BaseServiceI<TDictItem>{

    public DictItem addOrEdit(DictItem dictItem);
    //彻底删
    public void remove(String id);
    public List<DictItem> getTree(String dictId, String pid);
    public List<DictItem> getTreeByLevel(String dictId, String pid, int level);
}
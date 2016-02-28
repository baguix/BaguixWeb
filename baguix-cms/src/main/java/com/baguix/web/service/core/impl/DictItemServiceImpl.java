package com.baguix.web.service.core.impl;

import com.baguix.web.common.cache.SysData;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.model.page.core.DictItem;
import com.baguix.web.service.core.DictItemServiceI;
import com.baguix.web.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("dictItemService")
public class DictItemServiceImpl extends BaseServiceImpl<TDictItem> implements DictItemServiceI {
    @Autowired
    private BaseDaoI<TDictItem> dao;
    @Autowired
    private BaseDaoI<TDict> dictDao;

    @Override
    public DictItem addOrEdit(DictItem dictItem) {
        // 更新数据库
        TDictItem t = new TDictItem();
        changeModel(dictItem, t);
        dao.saveOrUpdate(t);
        dictItem.setId(t.getId());
        return dictItem;
    }

    @Override
    public void remove(String id) {
        TDictItem t = dao.getById(TDictItem.class, id);
        if (t != null) {
            if (t.getChild() != null && t.getChild().size() > 0) {
                for (TDictItem di : t.getChild()) {
                    remove(di.getId());
                }
            }
            // 删除自己
            String hql = "delete TDictItem t where t.id =:id";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            dao.executeHql(hql, params);
        }

    }

    @Override
    public List<DictItem> getTree(String dictId, String pid) {
        List<DictItem> list = new ArrayList<DictItem>();
        try {
            String hql;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("dictId", dictId);
            if (pid != null && !"".equals(pid)) {
                hql = "from TDictItem t where t.parent.id=:pid and t.dict.id=:dictId and t.level>=0  order by t.rank asc";
                params.put("pid", pid);
            } else {
                hql = "from TDictItem t where t.dict.id=:dictId and t.level=0  order by t.rank asc";
            }
            List<TDictItem> tlist = dao.find(hql, params);
            if (tlist != null && tlist.size() > 0) {
                for (TDictItem tdi : tlist) {
                    DictItem di = new DictItem();
                    changeModel(tdi, di);
                    Set<TDictItem> set = tdi.getChild();
                    if (set != null && !set.isEmpty()) {
                        di.setChildren(getTree(dictId, tdi.getId()));
                    }
                    list.add(di);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DictItem> getTreeByLevel(String dictId, String pid, int level) {
        List<DictItem> list = new ArrayList<DictItem>();
        if (level > 0) {
            try {
                String hql;
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("dictId", dictId);
                if (pid != null && !"".equals(pid)) {
                    hql = "from TDictItem t where t.parent.id=:pid and t.dict.id=:dictId and t.level>=0  order by t.rank asc";
                    params.put("pid", pid);
                } else {
                    hql = "from TDictItem t where t.dict.id=:dictId and t.level=0  order by t.rank asc";
                }
                List<TDictItem> tlist = dao.find(hql, params);
                if (tlist != null && tlist.size() > 0) {
                    for (TDictItem tdi : tlist) {
                        DictItem di = new DictItem();
                        changeModel(tdi, di);
                        Set<TDictItem> set = tdi.getChild();
                        if (set != null && !set.isEmpty()) {
                            di.setChildren(getTreeByLevel(dictId, tdi.getId(), level - 1));
                        }
                        list.add(di);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //private
    private void changeModel(List<TDictItem> l, List<DictItem> nl) {
        if (l != null && l.size() > 0) {
            for (TDictItem t : l) {
                DictItem u = new DictItem();
                // 目标对象属性"def", "dictid", "pid", "children"不自动复制,手动增加
                BeanUtils.copyProperties(t, u, "def", "dictid", "pid", "children");
                if (t.getDef()) {
                    u.setDef("true");
                } else {
                    u.setDef("false");
                }
                u.setDictid(t.getDict().getId());
                TDictItem parent = t.getParent();
                if (parent != null) {
                    u.setPid(parent.getId());
                }
                nl.add(u);
            }
        }
    }

    private void changeModel(TDictItem t, DictItem u) {
        BeanUtils.copyProperties(t, u, "def", "dictid", "pid", "children");
        if (t.getDef()) {
            u.setDef("true");
        } else {
            u.setDef("false");
        }
        u.setDictid(t.getDict().getId());
        TDictItem parent = t.getParent();
        if (parent != null) {
            u.setPid(parent.getId());
        }
    }

    private void changeModel(DictItem u, TDictItem t) {
        BeanUtils.copyProperties(u, t, "def", "dict", "parent", "child");
        String def = u.getDef();
        if (def != null && "true".equals(def)) {
            t.setDef(true);
        } else {
            t.setDef(false);
        }
        TDict dict = dictDao.getById(TDict.class, u.getDictid());
        t.setDict(dict);
        t.setCtime(new Date());
        t.setMtime(new Date());
        String pid = u.getPid();
        if (pid != null && !"".equals(pid)) {
            TDictItem parent = dao.getById(TDictItem.class, pid);
            if (parent != null) {
                t.setParent(parent);
            }
        }
    }

    private String addOrder(DictItem dict, String hql) {
        if (dict.getSort() != null) {
            hql += " order by " + dict.getSort() + " " + dict.getOrder();
        }
        return hql;
    }

    private String addWhere(DictItem dict, String hql, Map<String, Object> params) {
        if (dict.getDictid() != null && !dict.getDictid().trim().equals("")) {
            hql += " where t.dict.id = :dictid";
            params.put("dictid", dict.getDictid().trim());
        }
        return hql;
    }
}

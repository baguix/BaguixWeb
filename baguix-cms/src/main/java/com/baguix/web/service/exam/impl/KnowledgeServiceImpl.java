package com.baguix.web.service.exam.impl;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.ECommonField;
import com.baguix.web.model.db.exam.TKnowledge;
import com.baguix.web.model.enums.StateType;
import com.baguix.web.model.page.exam.Knowledge;
import com.baguix.web.service.exam.KnowledgeServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Scott on 2016/3/28.
 */

@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeServiceI {
    @Autowired
    private BaseDaoI<TKnowledge> dao;

    @Override
    public Knowledge add(Knowledge knowledge) {
        knowledge.setCtime(new Date());
        knowledge.setStatus(StateType.SHOW);
        // 更新数据库
        TKnowledge t = new TKnowledge();
        page2db(knowledge, t);
        dao.saveOrUpdate(t);
        knowledge.setId(t.getId());
        return knowledge;
    }

    @Override
    public Knowledge edit(Knowledge knowledge) {
        // 更新数据库
        TKnowledge t = dao.getById(TKnowledge.class, knowledge.getId());
        t.setText(knowledge.getText());
        t.setContent(knowledge.getContent());
        ECommonField cf = new ECommonField();
        cf.setMtime(new Date());
        cf.setRank(knowledge.getRank());
        cf.setStatus(StateType.SHOW);
        t.setCommonfield(cf);
        dao.saveOrUpdate(t);
        db2page(t,knowledge);
        return knowledge;
    }

    @Override
    public void remove(String id) {
        TKnowledge t = dao.getById(TKnowledge.class, id);
        if (t != null) {
            if (t.getChildren() != null && t.getChildren().size() > 0) {
                for (TKnowledge di : t.getChildren()) {
                    remove(di.getId());
                }
            }
            // 删除自己
            String hql = "delete TKnowledge t where t.id =:id";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            dao.executeHql(hql, params);
        }
    }

    @Override
    public void del(String id) {
        TKnowledge t = dao.getById(TKnowledge.class, id);
        if (t != null) {
            if (t.getChildren() != null && t.getChildren().size() > 0) {
                for (TKnowledge di : t.getChildren()) {
                    del(di.getId());
                }
            }
            // 删除自己
            ECommonField cf = t.getCommonfield();
            cf.setStatus(StateType.DELETE);
            t.setCommonfield(cf);
            dao.update(t);
        }
    }

    @Override
    public List<Knowledge> getTree(String pid) {
        List<Knowledge> list = new ArrayList<Knowledge>();
        try {
            String hql;
            Map<String, Object> params = new HashMap<String, Object>();
            if (pid != null && !"".equals(pid)) {
                hql = " from TKnowledge t " +
                        " where t.parent.id=:pid and t.commonfield.status=:status " +
                        " order by t.commonfield.rank asc";
                // 参数
                params.put("pid", pid);
                params.put("status", StateType.SHOW);
            } else {
                hql = "from TKnowledge t " +
                        " where t.level=0 and t.commonfield.status=:status " +
                        " order by t.commonfield.rank asc";
                // 参数
                params.put("status", StateType.SHOW);
            }
            List<TKnowledge> tlist = dao.find(hql, params);
            if (tlist != null && tlist.size() > 0) {
                for (TKnowledge t : tlist) {
                    Knowledge p = new Knowledge();
                    db2page(t, p);
                    Set<TKnowledge> set = t.getChildren();
                    if (set != null && !set.isEmpty()) {
                        p.setChildren(getTree(t.getId()));
                    }
                    p.setIconCls("icon-introduce");
                    list.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Knowledge> getTreeByLevel(String dictId, String pid, int level) {
        return null;
    }

    private void db2page(TKnowledge t, Knowledge p) {
        BeanUtils.copyProperties(t, p, "commonfield", "pid", "children");
        // 公共字段
        p.setRank(t.getCommonfield().getRank());
        p.setCtime(t.getCommonfield().getCtime());
        p.setMtime(t.getCommonfield().getMtime());
        p.setStatus(t.getCommonfield().getStatus());

        // 树形关系
        TKnowledge parent = t.getParent();
        if (parent != null) {
            p.setPid(parent.getId());
        }
    }

    public void page2db(Knowledge p, TKnowledge t) {
        BeanUtils.copyProperties(p, t, "parent", "children");
        // 公共字段
        ECommonField cf = new ECommonField();
        cf.setRank(p.getRank());
        cf.setCtime(p.getCtime());
        cf.setMtime(p.getMtime());
        cf.setStatus(p.getStatus());
        t.setCommonfield(cf);

        // 树形关系
        String pid = p.getPid();
        if (pid != null && !"".equals(pid)) {
            TKnowledge parent = dao.getById(TKnowledge.class, pid);
            if (parent != null) {
                t.setParent(parent);
            }
        }
    }
}

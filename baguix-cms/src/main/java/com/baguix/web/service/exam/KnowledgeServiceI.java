package com.baguix.web.service.exam;

import com.baguix.web.model.page.exam.Knowledge;

import java.util.List;

/**
 * Created by Scott on 2016/3/28.
 */
public interface KnowledgeServiceI {
    public Knowledge add(Knowledge knowledge);
    public Knowledge edit(Knowledge knowledge);
    //彻底删
    public void remove(String id);
    //临时删
    public void del(String id);
    public List<Knowledge> getTree(String pid);
    public List<Knowledge> getTreeByLevel(String dictId, String pid, int level);
}

package com.baguix.web.service.util.impl;

import com.baguix.web.common.cache.SysData;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.cms.TArticle;
import com.baguix.web.model.db.cms.TCategory;
import com.baguix.web.model.db.cms.TNews;
import com.baguix.web.model.db.cms.TSinglePage;
import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.service.cms.NewsServiceI;
import com.baguix.web.service.core.DictItemServiceI;
import com.baguix.web.service.core.DictServiceI;
import com.baguix.web.service.util.InitServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("initNewsService")
public class InitNewsImpl implements InitServiceI {
    @Autowired
    private BaseDaoI<TArticle> dao;
    @Autowired
    private NewsServiceI service;


    @Override
    synchronized public void repair() {
        initNews();
    }

    @Override
    public void cache() {
    }

    @Override
    public void reload() {

    }

    //文章
    private void initNews() {

        TNews news1 = new TNews();
        news1.setTitle("新闻1");
        news1.setThumbfile0("news0");
        news1.setThumbfile3("news3");
        news1.setThumbfile6("news6");
        dao.saveOrUpdate(news1);

        TNews news2 = new TNews();
        news2.setTitle("新闻2");
        news1.setThumbfile0("news0");
        news1.setThumbfile3("news3");
        news1.setThumbfile6("news6");
        dao.saveOrUpdate(news2);

        TSinglePage single1 = new TSinglePage();
        single1.setTitle("单篇1");
        single1.setThumbfile0("news0");
        single1.setThumbfile3("news3");
        dao.saveOrUpdate(single1);
    }
}

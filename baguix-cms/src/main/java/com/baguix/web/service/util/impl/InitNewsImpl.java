package com.baguix.web.service.util.impl;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.cms.TArticle;
import com.baguix.web.model.db.cms.TNews;
import com.baguix.web.model.db.cms.TSinglePage;
import com.baguix.web.model.db.core.ECommonField;
import com.baguix.web.model.enums.StateType;
import com.baguix.web.service.cms.NewsServiceI;
import com.baguix.web.service.util.InitServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        ECommonField cf = new ECommonField();
        cf.setCtime(new Date());
        cf.setMtime(new Date());

        TNews news1 = new TNews();
        news1.setTitle("新闻1");
        news1.setThumbfile0("news0");
        news1.setThumbfile3("news3");
        news1.setThumbfile6("news6");
        news1.setCommon(cf);
        dao.saveOrUpdate(news1);

        TNews news2 = new TNews();
        news2.setTitle("新闻2");
        news2.setThumbfile0("news0");
        news2.setThumbfile3("news3");
        news2.setThumbfile6("news6");
        news2.setCommon(cf);
        dao.saveOrUpdate(news2);

        TSinglePage single1 = new TSinglePage();
        single1.setTitle("单篇1");
        single1.setThumbfile0("news0");
        single1.setThumbfile3("news3");
        single1.setCommon(cf);
        dao.saveOrUpdate(single1);
    }
}

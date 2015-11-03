package com.baguix.web.service.util.impl;

import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.cms.TCategory;
import com.baguix.web.service.util.InitServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("initCategoryService")
public class InitCategoryServiceImpl implements InitServiceI {

    private BaseDaoI<TCategory> categoryDao;

    @Autowired
    public void setCategoryDao(BaseDaoI<TCategory> categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    synchronized public void repair() {
        initCategoryData();
    }

    @Override
    public void cache() {

    }

    @Override
    public void reload() {

    }

    //修复网站栏目
    private void initCategoryData() {
        //网站栏目
        TCategory category = new TCategory();
        category.setId("news");
        category.setTitle("网站栏目");
        category.setParentIdStr("news");
        category.setParentNameStr("");
        category.setKeyword("单篇文章");
        category.setDesc("用于构建网站栏目");
        category.setProper(" ");
        category.setLevel(0);
        category.setUrl("");
        //其下的子栏目可以形成网站栏目架构
        category.setType("news");
        category.setParent(null);
        category.setIsonline(true);
        category.setIsdel(false);
        category.setShowmode("1");//列表
        category.setOpenmode("1");//_blank
        category.setMaxartrank(0);
        category.setMaxsubrank(30);
        categoryDao.saveOrUpdate(category);

        //============================ 一级菜单 ====================================
        TCategory xxgk = new TCategory();
        xxgk.setId("gk");
        xxgk.setTitle("学校概况");
        xxgk.setKeyword("XXXX学校概况");
        xxgk.setDesc("介绍学校整体情况。例如：历史沿革、学校管理、校园、教师、学生情况等");
        xxgk.setProper("导航,菜单");
        xxgk.setUrl("");
        xxgk.setLevel(1);
        xxgk.setType("news");
        xxgk.setParent(category);
        xxgk.setIsonline(true);
        xxgk.setShownum(10);
        xxgk.setHomenum(10);
        xxgk.setRank(10);
        xxgk.setCtime(new Date());
        xxgk.setMtime(new Date());
        xxgk.setShowmode("1");//列表
        xxgk.setOpenmode("1");//_blank
        xxgk.setMaxartrank(0);
        xxgk.setMaxsubrank(20);
        categoryDao.saveOrUpdate(xxgk);
        xxgk.setParentIdStr(category.getId() + "," + xxgk.getId());
        //不需要记录根节点
        xxgk.setParentNameStr(xxgk.getTitle());
        categoryDao.saveOrUpdate(xxgk);

        TCategory xwzx = new TCategory();
        xwzx.setId("xw");
        xwzx.setTitle("新闻中心");
        xwzx.setKeyword("XXXX学校新闻中心");
        xwzx.setDesc("发布校园新闻");
        xwzx.setProper("导航,菜单");
        xwzx.setUrl("");
        xwzx.setLevel(1);
        xwzx.setType("news");
        xwzx.setParent(category);
        xwzx.setIsonline(true);
        xwzx.setShownum(10);
        xwzx.setHomenum(10);
        xwzx.setRank(20);
        xwzx.setCtime(new Date());
        xwzx.setMtime(new Date());
        xwzx.setShowmode("1");//列表
        xwzx.setOpenmode("");//_blank
        xwzx.setMaxartrank(0);
        xwzx.setMaxsubrank(0);
        categoryDao.saveOrUpdate(xwzx);
        xwzx.setParentIdStr(category.getId() + "," + xwzx.getId());
        xwzx.setParentNameStr(xwzx.getTitle());
        categoryDao.saveOrUpdate(xwzx);

        TCategory dt = new TCategory();
        dt.setId("dt");
        dt.setTitle("党团活动");
        dt.setKeyword("XXXX学校党团活动新闻");
        dt.setDesc("党团活动信息");
        dt.setProper("导航,菜单");
        dt.setUrl("");
        dt.setLevel(1);
        dt.setType("news");
        dt.setParent(category);
        dt.setIsonline(true);
        dt.setShownum(10);
        dt.setHomenum(10);
        dt.setRank(30);
        dt.setCtime(new Date());
        dt.setMtime(new Date());
        dt.setShowmode("1");//列表
        dt.setOpenmode("1");//_blank
        dt.setMaxartrank(0);
        dt.setMaxsubrank(0);
        categoryDao.saveOrUpdate(dt);
        dt.setParentIdStr(category.getId() + "," + dt.getId());
        dt.setParentNameStr(dt.getTitle());
        categoryDao.saveOrUpdate(dt);

        //============================ 二级菜单 ====================================
        TCategory xxjj = new TCategory();
        xxjj.setId("xxjj");
        xxjj.setTitle("学校简介");
        xxjj.setKeyword("XXXX学校新闻中心");
        xxjj.setDesc("一篇图文混排的学校简介文章");
        xxjj.setProper("导航,菜单");
        xxjj.setUrl("");
        xxjj.setLevel(2);
        xxjj.setType("news");
        xxjj.setParent(xxgk);
        xxjj.setIsonline(true);
        xxjj.setShownum(10);
        xxjj.setHomenum(10);
        xxjj.setRank(10);
        xxjj.setCtime(new Date());
        xxjj.setMtime(new Date());
        xxjj.setShowmode("1");//列表
        xxjj.setOpenmode("1");//_blank
        xxjj.setMaxartrank(0);
        xxjj.setMaxsubrank(0);
        categoryDao.saveOrUpdate(xxjj);
        xxjj.setParentIdStr(xxgk.getParentIdStr() + "," + xxjj.getId());
        xxjj.setParentNameStr(xxgk.getParentNameStr() + "＞" + xxjj.getTitle());
        categoryDao.saveOrUpdate(xxjj);

        TCategory xyfj = new TCategory();
        xyfj.setId("xyfj");
        xyfj.setTitle("校园风景");
        xyfj.setKeyword("XXXX学校校园风景");
        xyfj.setDesc("一组展示美丽的校园风景的图片。");
        xyfj.setProper("导航,菜单");
        xyfj.setUrl("");
        xyfj.setLevel(2);
        xyfj.setType("news");
        xyfj.setParent(xxgk);
        xyfj.setIsonline(true);
        xyfj.setShownum(10);
        xyfj.setHomenum(10);
        xyfj.setRank(20);
        xyfj.setCtime(new Date());
        xyfj.setMtime(new Date());
        xyfj.setShowmode("1");//列表
        xyfj.setOpenmode("1");//_blank
        xyfj.setMaxartrank(0);
        xyfj.setMaxsubrank(0);
        categoryDao.saveOrUpdate(xyfj);
        xyfj.setParentIdStr(xxgk.getParentIdStr() + "," + xyfj.getId());
        xyfj.setParentNameStr(xxgk.getParentNameStr() + "＞" + xyfj.getTitle());
        categoryDao.saveOrUpdate(xyfj);
    }
}
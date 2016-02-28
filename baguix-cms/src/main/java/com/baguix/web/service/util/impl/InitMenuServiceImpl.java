package com.baguix.web.service.util.impl;

import com.baguix.web.common.cache.SysData;
import com.baguix.web.dao.BaseDaoI;
import com.baguix.web.model.db.core.*;
import com.baguix.web.model.db.manctrl.TNavMenu;
import com.baguix.web.service.manctrl.NavMenuServiceI;
import com.baguix.web.service.util.InitServiceI;
import org.apache.log4j.Logger;
import org.apache.poi.hmef.attribute.TNEFAttribute;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("initMenuService")
public class InitMenuServiceImpl implements InitServiceI {

    private static final Logger logger = Logger.getLogger(InitMenuServiceImpl.class);

    @Autowired
    private NavMenuServiceI navMenuService;
    @Autowired
    private BaseDaoI<TNavMenu> navMenuDao;
    @Autowired
    private BaseDaoI<TUser> userDao;
    @Autowired
    private BaseDaoI<TRole> roleDao;
    @Autowired
    private BaseDaoI<TUserRole> userroleDao;
    @Autowired
    private BaseDaoI<TModule> moduleDao;
    @Autowired
    private BaseDaoI<TPermission> permissionDao;

    @Override
    synchronized public void repair() {
        initSysMenu();
    }

    private void initSysMenu() {
        /**
         * 一、导航（type=bar）的URL写成#。
         *
         * 二、菜单（type=menu）的URL写成SS_main_menuTree(id)得到子树，例如：SS_main_menuTree('sitestyle')
         *
         * 三、左树（type=tree）的URL数据要配合：js/main.js的实现函数(SS_main_menuTree)
         * 操作：
         * 1.addTabHref(###地址###);			 		1.以href形式在main_tabs上增加一个tab
         * 2.addTabIframe(###地址###);		 		2.以iFrame形式在main_tabs上增加一个tab
         * 3.doAction(###地址`~参数###);				3.执行一个Struts2的action，结果以JSON形式返回到一个EasyUI的Messager上。
         * 4.doJSCode(###代码###);					4.执行一段JavaScript代码。
         * 5.goURLSelf(###地址###);					5.在自身窗体打开一个URL链接
         * 6.goURLBlank(###地址###);					6.在新窗口打开一个URL链接
         */


        TNavMenu gen = new TNavMenu();
        gen.setId("setting");
        gen.setTitle("常规设置");
        gen.setIcon("icon-pc");
        gen.setType("bar");
        gen.setLevel(0);
        gen.setTarget("link");
        gen.setUrl("#");
        gen.setHassub(true);
        gen.setCtime(new Date());
        gen.setRank(10);
        navMenuDao.saveOrUpdate(gen);

        TNavMenu updatelog = new TNavMenu();
        updatelog.setId("updatelog");
        updatelog.setParent(gen);
        updatelog.setTitle("网站说明");
        updatelog.setIcon("icon-baseinfo");
        updatelog.setType("menu");
        updatelog.setLevel(1);
        updatelog.setTarget("link");
        updatelog.setUrl("goURLBlank(###system-logger.html###);");
        gen.setHassub(true);
        updatelog.setCtime(new Date());
        updatelog.setRank(10);
        navMenuDao.saveOrUpdate(updatelog);

        TNavMenu style = new TNavMenu();
        style.setId("sitestyle");
        style.setParent(gen);
        style.setTitle("网站风格");
        style.setIcon("icon-sitestyle");
        style.setType("menu");
        style.setLevel(1);
        style.setTarget("link");
        style.setUrl("SS_main_menuTree('sitestyle')");
        gen.setHassub(true);
        style.setCtime(new Date());
        style.setRank(20);
        navMenuDao.saveOrUpdate(style);

        TNavMenu home = new TNavMenu();
        home.setId("sitehome");
        home.setParent(style);
        home.setTitle("首页风格");
        home.setIcon("icon-homestyle");
        home.setType("tree");
        home.setLevel(1);
        home.setTarget("link");
        home.setUrl("addTabIframe(###/manctrl/style/home.do###);");
        gen.setHassub(false);
        home.setCtime(new Date());
        home.setRank(10);
        navMenuDao.saveOrUpdate(home);



        TNavMenu fgx1 = new TNavMenu();
        fgx1.setId("sep1");
        fgx1.setParent(gen);
        fgx1.setTitle("-");
        fgx1.setType("menu");
        fgx1.setIcon("");
        fgx1.setLevel(1);
        fgx1.setRank(20);
        navMenuDao.saveOrUpdate(fgx1);

        TNavMenu homead = new TNavMenu();
        homead.setId("homead");
        homead.setParent(gen);
        homead.setTitle("附带内容");
        homead.setIcon("icon-countersign");
        homead.setType("menu");
        homead.setLevel(1);
        homead.setTarget("link");
        homead.setUrl("SS_main_menuTree('homead')");
        homead.setCtime(new Date());
        homead.setRank(30);
        navMenuDao.saveOrUpdate(homead);

        TNavMenu link = new TNavMenu();
        link.setId("links");
        link.setParent(homead);
        link.setTitle("友情链接");
        link.setIcon("icon-link");
        link.setType("tree");
        link.setLevel(1);
        link.setTarget("link");
        link.setUrl("goURLSelf(###http://www.soso.com###);");
        link.setCtime(new Date());
        link.setRank(10);
        navMenuDao.saveOrUpdate(link);

        TNavMenu ads = new TNavMenu();
        ads.setId("ads");
        ads.setParent(homead);
        ads.setTitle("广告管理");
        ads.setIcon("icon-ads");
        ads.setType("tree");
        ads.setLevel(1);
        ads.setTarget("link");
        ads.setUrl("goURLSelf(######);");
        ads.setCtime(new Date());
        ads.setRank(20);
        navMenuDao.saveOrUpdate(ads);

        TNavMenu btm = new TNavMenu();
        btm.setId("bottom");
        btm.setParent(homead);
        btm.setTitle("底部栏目");
        btm.setIcon("icon-pagefoot");
        btm.setType("tree");
        btm.setLevel(1);
        btm.setTarget("link");
        btm.setUrl("goURLBlank(###http://www.126.com###)");
        btm.setCtime(new Date());
        btm.setRank(30);
        navMenuDao.saveOrUpdate(btm);

        TNavMenu doc = new TNavMenu();
        doc.setId("docman");
        doc.setTitle("内容管理");
        doc.setIcon("icon-content");
        doc.setType("bar");
        doc.setLevel(0);
        doc.setTarget("link");
        doc.setUrl("#");
        doc.setCtime(new Date());
        doc.setRank(20);
        navMenuDao.saveOrUpdate(doc);

        TNavMenu cate = new TNavMenu();
        cate.setId("category");
        cate.setParent(doc);
        cate.setTitle("栏目管理");
        cate.setIcon("icon-category");
        cate.setType("menu");
        cate.setLevel(1);
        cate.setTarget("link");
        cate.setUrl("SS_main_menuTree('category')");
        cate.setCtime(new Date());
        cate.setRank(10);
        navMenuDao.saveOrUpdate(cate);

        TNavMenu struts = new TNavMenu();
        struts.setId("struts");
        struts.setParent(cate);
        struts.setTitle("网站栏目");
        struts.setIcon("icon-sitestrut");
        struts.setType("tree");
        struts.setLevel(1);
        struts.setTarget("link");
        struts.setUrl("addTabHref(###manctrl/cms/category.do###)");
        struts.setCtime(new Date());
        struts.setRank(10);
        navMenuDao.saveOrUpdate(struts);

//		TNavMenu tree = new TNavMenu();
//		tree.setId("tree");
//		tree.setParent(cate);
//		tree.setTitle("结构调整");
//		tree.setIcon("icon-tree");
//		tree.setType("tree");
//		tree.setLevel(1);
//		tree.setTarget("link");
//		tree.setUrl("doJSCode(###alert('尚未实现！');###)");
//		tree.setCtime(new Date());
//		tree.setRank(20);
//		navMenuDao.saveOrUpdate(tree);

        TNavMenu resetrank = new TNavMenu();
        resetrank.setId("resetrank");
        resetrank.setParent(cate);
        resetrank.setTitle("重置排序");
        resetrank.setIcon("icon-rank");
        resetrank.setType("tree");
        resetrank.setLevel(1);
        resetrank.setTarget("link");
        resetrank.setUrl("doAction(###manctrl/category!resetRank.action###)");//左树节点直接执行Action，结果返回弹窗      的样例。
        resetrank.setCtime(new Date());
        resetrank.setRank(30);
        navMenuDao.saveOrUpdate(resetrank);

        TNavMenu art = new TNavMenu();
        art.setId("art");
        art.setParent(doc);
        art.setTitle("文章管理");
        art.setIcon("icon-articles");
        art.setType("menu");
        art.setLevel(1);
        art.setTarget("link");
        art.setUrl("SS_main_menuTree('art')");
        art.setRank(20);
        navMenuDao.saveOrUpdate(art);

        TNavMenu ggnews = new TNavMenu();
        ggnews.setId("announcement");
        ggnews.setParent(art);
        ggnews.setTitle("网站公告");
        ggnews.setIcon("icon-notice");
        ggnews.setType("tree");
        ggnews.setLevel(1);
        ggnews.setTarget("link");
        ggnews.setUrl("addTabHref(###manctrl/announcement/manager.action###)");
        ggnews.setCtime(new Date());
        ggnews.setRank(10);
        navMenuDao.saveOrUpdate(ggnews);

        TNavMenu snews = new TNavMenu();
        snews.setId("singlenews");
        snews.setParent(art);
        snews.setTitle("单篇文章");
        snews.setIcon("icon-article");
        snews.setType("tree");
        snews.setLevel(1);
        snews.setTarget("link");
        snews.setUrl("addTabHref(###manctrl/singlepage/manager.action###)");
        snews.setCtime(new Date());
        snews.setRank(20);
        navMenuDao.saveOrUpdate(snews);

        TNavMenu news = new TNavMenu();
        news.setId("news");
        news.setParent(art);
        news.setTitle("新闻管理");
        news.setIcon("icon-news");
        news.setType("tree");
        news.setLevel(1);
        news.setTarget("link");
        news.setUrl("addTabHref(###manctrl/news/manager.action###)");
        news.setCtime(new Date());
        news.setRank(30);
        navMenuDao.saveOrUpdate(news);

//		TNavMenu notice = new TNavMenu();
//		notice.setId("notice");
//		notice.setParent(doc);
//		notice.setTitle("短信通知");
//		notice.setIcon("icon-mobile");
//		notice.setType("menu");
//		notice.setLevel(1);
//		notice.setTarget("link");
//		notice.setUrl("javascript:menuto('notice')");
//		notice.setCtime(new Date());
//		notice.setRank(20);
//		navMenuDao.saveOrUpdate(notice);
//
//		TNavMenu waitmsg = new TNavMenu();
//		waitmsg.setId("waitmsg");
//		waitmsg.setParent(notice);
//		waitmsg.setTitle("待发通知");
//		waitmsg.setIcon("icon-message");
//		waitmsg.setType("tree");
//		waitmsg.setLevel(1);
//		waitmsg.setTarget("link");
//		waitmsg.setUrl("javascript:menuto('waitmsg')");
//		waitmsg.setCtime(new Date());
//		waitmsg.setRank(30);
//		navMenuDao.saveOrUpdate(waitmsg);
//
//		TNavMenu sendmsg = new TNavMenu();
//		sendmsg.setId("special");
//		sendmsg.setParent(notice);
//		sendmsg.setTitle("已发通知");
//		sendmsg.setIcon("icon-email");
//		sendmsg.setType("tree");
//		sendmsg.setLevel(1);
//		sendmsg.setTarget("link");
//		sendmsg.setUrl("javascript:menuto('special')");
//		sendmsg.setCtime(new Date());
//		sendmsg.setRank(30);
//		navMenuDao.saveOrUpdate(sendmsg);

        TNavMenu sys = new TNavMenu();
        sys.setId("sysman");
        sys.setTitle("系统管理");
        sys.setIcon("icon-system");
        sys.setType("bar");
        sys.setLevel(0);
        sys.setTarget("link");
        sys.setUrl("#");
        sys.setCtime(new Date());
        sys.setRank(30);
        navMenuDao.saveOrUpdate(sys);

        TNavMenu site = new TNavMenu();
        site.setId("siteconf");
        site.setParent(sys);
        site.setTitle("网站设置");
        site.setIcon("icon-site");
        site.setType("menu");
        site.setLevel(1);
        site.setTarget("link");
        site.setUrl("SS_main_menuTree('siteconf')");
        site.setCtime(new Date());
        site.setRank(10);
        navMenuDao.saveOrUpdate(site);


        TNavMenu info = new TNavMenu();
        info.setId("siteinfo");
        info.setParent(site);
        info.setTitle("基本信息");
        info.setIcon("icon-siteinfo");
        info.setType("tree");
        info.setLevel(1);
        info.setTarget("link");
        info.setUrl("doJSCode(###SS_sys_Dialog('网站基本信息','siteinfo.do',800,600);###)");
        info.setCtime(new Date());
        info.setRank(10);
        navMenuDao.saveOrUpdate(info);

        TNavMenu images = new TNavMenu();
        images.setId("imagesetting");
        images.setParent(site);
        images.setTitle("图片/水印");
        images.setIcon("icon-picture");
        images.setType("tree");
        images.setLevel(1);
        images.setTarget("link");
        images.setUrl("doJSCode(###SS_sys_Dialog('图片处理参数','imageinfo.do',600,600);###)");
        images.setCtime(new Date());
        images.setRank(30);
        navMenuDao.saveOrUpdate(images);

        TNavMenu siteoperation = new TNavMenu();
        siteoperation.setId("siteoperation");
        siteoperation.setParent(sys);
        siteoperation.setTitle("网站运维");
        siteoperation.setIcon("icon-siteconfig");
        siteoperation.setType("menu");
        siteoperation.setLevel(1);
        siteoperation.setHassub(true);
        siteoperation.setTarget("link");
        siteoperation.setUrl("SS_main_menuTree('siteoperation')");
        siteoperation.setCtime(new Date());
        siteoperation.setRank(20);
        navMenuDao.saveOrUpdate(siteoperation);

		TNavMenu db = new TNavMenu();
		db.setId("database");
		db.setParent(siteoperation);
		db.setTitle("数据管理");
		db.setIcon("icon-database");
		db.setType("menu");
		db.setLevel(2);
		db.setTarget("link");
		db.setUrl("SS_main_menuTree('database')");
		db.setCtime(new Date());
		db.setRank(10);
		navMenuDao.saveOrUpdate(db);

		TNavMenu dbback = new TNavMenu();
		dbback.setId("dbback");
		dbback.setParent(db);
		dbback.setTitle("数据库备份");
		dbback.setIcon("icon-dbbackup");
		dbback.setType("tree");
		dbback.setLevel(1);
		dbback.setTarget("link");
		dbback.setUrl("manctrl/dbback.jsp");
		dbback.setCtime(new Date());
		dbback.setRank(10);
		navMenuDao.saveOrUpdate(dbback);

        TNavMenu dbdown = new TNavMenu();
        dbdown.setId("datadown");
        dbdown.setParent(db);
        dbdown.setTitle("数据包下载");
        dbdown.setIcon("icon-downloaddb");
        dbdown.setType("tree");
        dbdown.setLevel(1);
        dbdown.setTarget("link");
        dbdown.setUrl("manctrl/datadown.jsp");
        dbdown.setCtime(new Date());
        dbdown.setRank(20);
        navMenuDao.saveOrUpdate(dbdown);

		TNavMenu fileman = new TNavMenu();
		fileman.setId("fileman");
		fileman.setParent(db);
		fileman.setTitle("文件管理");
		fileman.setIcon("icon-hole");
		fileman.setType("tree");
		fileman.setLevel(1);
		fileman.setTarget("link");
		fileman.setUrl("manctrl/filemanager/file.jsp");
		fileman.setCtime(new Date());
		fileman.setRank(30);
		navMenuDao.saveOrUpdate(fileman);

        TNavMenu dict = new TNavMenu();
        dict.setId("dict");
        dict.setParent(db);
        dict.setTitle("字典管理");
        dict.setIcon("icon-dictionary");
        dict.setType("tree");
        dict.setLevel(1);
        dict.setTarget("link");
        dict.setUrl("addTabHref(###core/dict/manager.do###)");
        dict.setCtime(new Date());
        dict.setRank(40);
        navMenuDao.saveOrUpdate(dict);

        TNavMenu syslogger = new TNavMenu();
        syslogger.setId("syslogger");
        syslogger.setParent(siteoperation);
        syslogger.setTitle("日志系统");
        syslogger.setIcon("icon-logger");
        syslogger.setType("menu");
        syslogger.setLevel(2);
        syslogger.setTarget("link");
        syslogger.setUrl("SS_main_menuTree('syslogger')");
        syslogger.setCtime(new Date());
        syslogger.setRank(20);
        navMenuDao.saveOrUpdate(syslogger);

        TNavMenu frontlogger = new TNavMenu();
        frontlogger.setId("frontlogger");
        frontlogger.setParent(syslogger);
        frontlogger.setTitle("前台日志");
        frontlogger.setIcon("icon-sitelogger");
        frontlogger.setType("tree");
        frontlogger.setLevel(1);
        frontlogger.setTarget("link");
        frontlogger.setUrl("manctrl/serverlogger.jsp");//左树节点在主窗体嵌入式打开URL    的样例
        frontlogger.setCtime(new Date());
        frontlogger.setRank(10);
        navMenuDao.saveOrUpdate(frontlogger);

		TNavMenu operlogger = new TNavMenu();
        operlogger.setId("operlogger");
        operlogger.setParent(syslogger);
        operlogger.setTitle("后台日志");
        operlogger.setIcon("icon-backlogger");
        operlogger.setType("tree");
        operlogger.setLevel(1);
        operlogger.setTarget("link");
        operlogger.setUrl("manctrl/operlogger.jsp");//左树节点在主窗体嵌入式打开URL    的样例
        operlogger.setCtime(new Date());
        operlogger.setRank(20);
		navMenuDao.saveOrUpdate(operlogger);

        TNavMenu sysmsg = new TNavMenu();
        sysmsg.setId("sysmsg");
        sysmsg.setParent(siteoperation);
        sysmsg.setTitle("通知系统");
        sysmsg.setIcon("icon-sysmessage");
        sysmsg.setType("menu");
        sysmsg.setLevel(2);
        sysmsg.setTarget("link");
        sysmsg.setUrl("SS_main_menuTree('sysmsg')");
        sysmsg.setCtime(new Date());
        sysmsg.setRank(30);
        navMenuDao.saveOrUpdate(sysmsg);

        TNavMenu emailsetting = new TNavMenu();
        emailsetting.setId("emailsetting");
        emailsetting.setParent(sysmsg);
        emailsetting.setTitle("电子邮件");
        emailsetting.setIcon("icon-email");
        emailsetting.setType("tree");
        emailsetting.setLevel(1);
        emailsetting.setTarget("link");
        emailsetting.setUrl("manctrl/operlogger.jsp");//左树节点在主窗体嵌入式打开URL    的样例
        emailsetting.setCtime(new Date());
        emailsetting.setRank(10);
        navMenuDao.saveOrUpdate(emailsetting);

        TNavMenu smssetting = new TNavMenu();
        smssetting.setId("smssetting");
        smssetting.setParent(sysmsg);
        smssetting.setTitle("手机短信");
        smssetting.setIcon("icon-sms");
        smssetting.setType("tree");
        smssetting.setLevel(1);
        smssetting.setTarget("link");
        smssetting.setUrl("manctrl/operlogger.jsp");//左树节点在主窗体嵌入式打开URL    的样例
        smssetting.setCtime(new Date());
        smssetting.setRank(20);
        navMenuDao.saveOrUpdate(smssetting);

        TNavMenu weixinsetting = new TNavMenu();
        weixinsetting.setId("weixinsetting");
        weixinsetting.setParent(sysmsg);
        weixinsetting.setTitle("微信设置");
        weixinsetting.setIcon("icon-weixin");
        weixinsetting.setType("tree");
        weixinsetting.setLevel(1);
        weixinsetting.setTarget("link");
        weixinsetting.setUrl("manctrl/operlogger.jsp");//左树节点在主窗体嵌入式打开URL    的样例
        weixinsetting.setCtime(new Date());
        weixinsetting.setRank(30);
        navMenuDao.saveOrUpdate(weixinsetting);

        TNavMenu usermanager = new TNavMenu();
        usermanager.setId("usermanager");
        usermanager.setParent(sys);
        usermanager.setTitle("用户权限");
        usermanager.setIcon("icon-userauth");
        usermanager.setType("menu");
        usermanager.setLevel(1);
        usermanager.setTarget("link");
        usermanager.setUrl("SS_main_menuTree('usermanager')");
        usermanager.setCtime(new Date());
        usermanager.setRank(30);
        navMenuDao.saveOrUpdate(usermanager);

        TNavMenu chpwd = new TNavMenu();
        chpwd.setId("changepwd");
        chpwd.setParent(sys);
        chpwd.setTitle("更改密码");
        chpwd.setIcon("icon-unlock");
        chpwd.setType("menu");
        chpwd.setLevel(1);
        chpwd.setTarget("dialog");
        chpwd.setUrl("SS_main_doJSCode(SS_chpwd_showDialog())");//导航菜单直接运行JS，打开EasyUI对话框(dialog)的样例
        chpwd.setCtime(new Date());
        chpwd.setRank(50);
        navMenuDao.saveOrUpdate(chpwd);



        TNavMenu logout = new TNavMenu();
        logout.setId("logout");
        logout.setParent(sys);
        logout.setTitle("安全退出");
        logout.setIcon("icon-logout");
        logout.setType("menu");
        logout.setLevel(1);
        logout.setTarget("link");
        logout.setUrl("SS_main_goURLSelf(logout.action)");//导航菜单打开_self窗口    的样例
        logout.setCtime(new Date());
        logout.setRank(60);
        navMenuDao.saveOrUpdate(logout);


        TNavMenu pubsite = new TNavMenu();
        pubsite.setId("pubsite");
        pubsite.setParent(sys);
        pubsite.setTitle("前台页面");
        pubsite.setIcon("icon-page");
        pubsite.setType("menu");
        pubsite.setLevel(1);
        pubsite.setTarget("link");
        pubsite.setUrl("SS_main_goURLBlank(ss_basepath)");//导航菜单打开_blank窗口    的样例
        pubsite.setCtime(new Date());
        pubsite.setRank(60);
        navMenuDao.saveOrUpdate(pubsite);

        //initUser();
    }

    @Override
    public void cache() {
        //菜单表
        menuInMemory();
    }

    @Override
    public void reload() {
        //菜单表
        menuInMemory();
        logger.info("用户菜单重新缓存完毕！");
    }

    /**
     * 生成Easy UI html字符串，缓存
     */
    private void menuInMemory() {
        List<TNavMenu> barlist = navMenuService.findBarByRank();
        StringBuilder html = new StringBuilder();
        html.append("<div id=\"main_navmenu\">&nbsp;&nbsp;&nbsp;&nbsp;");

        for (Iterator<TNavMenu> iter = barlist.iterator(); iter.hasNext(); ) {
            TNavMenu nb = (TNavMenu) iter.next();
            html.append("<a href=\"");
            html.append(nb.getUrl());
            html.append("\"  class=\"easyui-menubutton\" data-options=\"menu:'#");
            html.append(nb.getId());
            html.append("',iconCls:'");
            html.append(nb.getIcon());
            html.append("'\">");
            html.append(nb.getTitle());
            html.append("</a>&nbsp;&nbsp;");
        }
        for (Iterator<TNavMenu> iter = barlist.iterator(); iter.hasNext(); ) {
            TNavMenu nb = (TNavMenu) iter.next();
            html.append("<div id=\"");
            html.append(nb.getId());
            html.append("\" class=\"easyui-menu\" style=\"width:120px;\">");
            html.append(getTreeStr(nb, navMenuService));
            html.append("</div>");
        }

        html.append("</div><!--main_navmenu-->");
        SysData.userNavMap.put("admin", html.toString());
    }

    //递归生成树
    private String getTreeStr(TNavMenu root, NavMenuServiceI navMenuService) {
        List<TNavMenu> sublist = navMenuService.findChildByRank(root, "menu");
        StringBuffer html = new StringBuffer();
        if (sublist != null && sublist.size() > 0) {
            for (Iterator<TNavMenu> iter = sublist.iterator(); iter.hasNext(); ) {
                TNavMenu nm = (TNavMenu) iter.next();
                if (nm.getTitle().equals("-")) {
                    html.append("<div class=\"menu-sep\"></div>");
                } else {
                    if (nm.getHassub()) {
                        html.append("<div data-options=\"iconCls:'");
                        html.append(nm.getIcon());
                        html.append("'\" onclick=\"");
                        html.append(nm.getUrl());
                        html.append("\">");
                        html.append("<span>");
                        html.append(nm.getTitle());
                        html.append("</span><div style=\"width:120px;\">");
                        //递归遍历树
                        html.append(getTreeStr(nm, navMenuService));
                        html.append("</div></div>");
                    } else {
                        html.append("<div data-options=\"iconCls:'");
                        html.append(nm.getIcon());
                        html.append("'\" onclick=\"");
                        html.append(nm.getUrl());
                        html.append("\">");
                        html.append(nm.getTitle());
                        html.append("</div>");
                    }
                }
            }
        }
        return html.toString();
    }

    private void initUser() {
        // 所有模块全部权限（最高权限）
        List<TPermission> allPermissions = new ArrayList<>();
        String hql = "from TNavMenu t order by t.rank asc, t.ctime desc, t.mtime desc";
        List<TNavMenu> navMenus = navMenuService.find(hql);
        for (TNavMenu nav : navMenus) {
            TModule m = new TModule();
            BeanUtils.copyProperties(nav, m, "children", "parent");
            m.setCtime(new Date());
            moduleDao.saveOrUpdate(m);
            TPermission moduleView = new TPermission();
            moduleView.setId(UUID.randomUUID().toString());
            moduleView.setName(m.getTitle() + "-访问");
            moduleView.setNote(m.getTitle() + "-访问");
            moduleView.setModule(m);
            moduleView.setRank(10);
            permissionDao.saveOrUpdate(moduleView);
            allPermissions.add(moduleView);
        }

        TRole role = new TRole();
        role.setId(UUID.randomUUID().toString());
        role.setName("系统管理员");
        role.setNote("该角色管理整个系统所有功能");
        role.setPermissions(allPermissions);
        roleDao.saveOrUpdate(role);

        TUser user = new TUser();
        user.setUid("admin");
        user.setPassword("admin");
        user.setName("超级管理员");
        user.setSex("男");
        user.setEmail("8555323@qq.com");
        user.setId(UUID.randomUUID().toString());
        userDao.saveOrUpdate(user);
        TUserRole ur = new TUserRole();
        ur.setId(UUID.randomUUID().toString());
        ur.setRole(role);
        ur.setUser(user);
        userroleDao.saveOrUpdate(ur);
    }
}

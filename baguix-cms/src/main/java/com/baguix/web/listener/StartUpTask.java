/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/17.
 */

package com.baguix.web.listener;

import com.baguix.web.common.cache.SysData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import com.baguix.web.service.util.InitServiceI;


/**
 * <b>启动任务（单次任务）</b><br>
 * 注意：本类中的任务只执行一次，多次任务为计划任务
 * @see com.baguix.web.common.scheduler.QuartzJob
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Service
public class StartUpTask implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger("Baguix系统信息");

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            initTask();
            run(evt);
        }
    }

    /**
     * <b>系统xml、properties数据载入</b><br>
     */
    private void initTask() {
        logger.info("BaguixWeb initialization: ");
        new SysData();
        logger.info("BaguixWeb initialization complete!");
    }

    /**
     * <b>Spring单次任务</b><br>
     */
    private void run(ContextRefreshedEvent evt) {
        logger.info("系统菜单数据修复中...");
        InitServiceI navMenuService = (InitServiceI) evt.getApplicationContext().getBean("initMenuService");
        navMenuService.repair();
        logger.info("系统菜单数据修复完毕.");

        logger.info("系统菜单数据缓存中...");
        navMenuService.cache();
        logger.info("系统菜单数据缓存完毕.");

        logger.info("系统字典数据修复中...");
        InitServiceI dictService = (InitServiceI) evt.getApplicationContext().getBean("initDictService");
        dictService.repair();
        logger.info("系统字典数据修复完毕.");

        logger.info("系统字典数据缓存中...");
        dictService.cache();
        logger.info("系统字典数据缓存完毕.");

        logger.info("网站栏目数据修复中...");
        InitServiceI catoryService = (InitServiceI) evt.getApplicationContext().getBean("initCategoryService");
        catoryService.repair();
        logger.info("网站栏目数据修复完毕.");

        logger.info("网站新闻数据修复中...");
        InitServiceI newsService = (InitServiceI) evt.getApplicationContext().getBean("initNewsService");
        newsService.repair();
        logger.info("网站新闻数据修复完毕.");
    }
}

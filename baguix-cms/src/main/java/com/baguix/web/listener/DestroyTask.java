package com.baguix.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2015/7/9.
 */
public class DestroyTask implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger("系统消息");

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Ignore
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("springconf/spring-quartz.xml");
        logger.info("销毁操作开始：");
        logger.info("...");
        logger.info("销毁操作结束！");
    }

}
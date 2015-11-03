package com.baguix.web.common.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Scott on 2015/7/8.
 */

public class QuartzJob {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    public void execute() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sf.format(d);
        logger.debug("Quartz计划任务。"+time);
    }
}

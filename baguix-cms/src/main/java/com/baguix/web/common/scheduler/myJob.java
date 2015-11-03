package com.baguix.web.common.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <b>计划任务</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

public class myJob {

    private static final Logger logger = LoggerFactory.getLogger(myJob.class);

    public void execute() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sf.format(d);
        logger.debug("计划任务。"+time);
    }
}

package com.hand.hls.util.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hand.hls.sys.service.IQuartzUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class People {
    @Autowired
    IQuartzUtilService iQuartzUtilService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public synchronized void birth() {
//		logger.info("人出生耗时操作10s");
//		Util.time_10s();
        logger.debug(df.format(new Date()) + "---------->人出生");
        String mainJob = iQuartzUtilService.getScheduleJobName("people_birth");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

    public synchronized void life() {
//		logger.info("人生活耗时操作8s");
//		Util.time_8s();
        logger.debug(df.format(new Date()) + "---------->人生活");
        String mainJob = iQuartzUtilService.getScheduleJobName("people_life");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

    public synchronized void death() {
//		logger.info("人死亡耗时操作6s");
//		Util.time_6s();
        logger.debug(df.format(new Date()) + "---------->人死亡");
        String mainJob = iQuartzUtilService.getScheduleJobName("people_death");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

}

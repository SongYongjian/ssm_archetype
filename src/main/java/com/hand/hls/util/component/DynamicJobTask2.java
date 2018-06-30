package com.hand.hls.util.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hand.hls.sys.service.IQuartzUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 动态job 可以动态获取数据库配置自动修改job
 */
@Component
public class DynamicJobTask2 {
    @Autowired
    IQuartzUtilService iQuartzUtilService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public synchronized void birth() {
        logger.trace(df.format(new Date()) + "---------->人出生");
        String mainJob = iQuartzUtilService.getScheduleJobName("people_birth");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

    public synchronized void life() {
        logger.trace(df.format(new Date()) + "---------->人生活");
        String mainJob = iQuartzUtilService.getScheduleJobName("people_life");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

    public synchronized void death() {
        logger.trace(df.format(new Date()) + "---------->人死亡");
        String mainJob = iQuartzUtilService.getScheduleJobName("people_death");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

}

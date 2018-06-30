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
public class DynamicJobTask1 {
    @Autowired
    IQuartzUtilService iQuartzUtilService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void starting() {
        logger.info(df.format(new Date()) + "---------->车辆启动");
        String mainJob = iQuartzUtilService.getScheduleJobName("car_starting");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

    public void running() {
        logger.info(df.format(new Date()) + "---------->车辆开动");
        String mainJob = iQuartzUtilService.getScheduleJobName("car_running");
        iQuartzUtilService.executeChildJobs(mainJob);
    }

    public void stop() {
        logger.info(df.format(new Date()) + "---------->车辆停止");
        String mainJob = iQuartzUtilService.getScheduleJobName("car_stop");
        iQuartzUtilService.executeChildJobs(mainJob);
    }
}

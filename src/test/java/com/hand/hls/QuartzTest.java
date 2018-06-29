package com.hand.hls;

import com.hand.hls.sys.service.IQuartzUtilService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class QuartzTest {
    @Autowired
    IQuartzUtilService iQuartzUtilService;
    private Logger logger = Logger.getLogger(QuartzTest.class);
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

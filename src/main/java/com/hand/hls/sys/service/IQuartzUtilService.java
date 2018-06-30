package com.hand.hls.sys.service;

import com.hand.hls.sys.dto.ScheduleJob;
import com.hand.hls.util.component.SpringContextUtil;
import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;

import java.util.ArrayList;
import java.util.List;

public interface IQuartzUtilService {

    String getScheduleJobName(String jobName);

    void createChildJob(ScheduleJob job) throws Exception;

    void executeChildJobs(String mainJob);

    Scheduler getScheduler();



    List<String> getJobNames();
}

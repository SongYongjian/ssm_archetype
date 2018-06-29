package com.hand.hls.sys.mapper;


import com.hand.hls.sys.dto.ScheduleJob;
import com.hand.hls.sys.dto.User;

import java.util.List;

public interface ScheduleJobMapper {

    List<ScheduleJob>getAllScheduleJobs();
    ScheduleJob selectScheduleJob(ScheduleJob scheduleJob);
}
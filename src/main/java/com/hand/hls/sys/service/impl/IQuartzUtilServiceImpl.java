package com.hand.hls.sys.service.impl;


import com.hand.hls.sys.dto.ScheduleJob;
import com.hand.hls.sys.mapper.ScheduleJobMapper;
import com.hand.hls.sys.service.IQuartzUtilService;
import com.hand.hls.util.component.SpringContextUtil;
import com.hand.hls.util.component.UUIDGenerator;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("quartzUtilService")
public class IQuartzUtilServiceImpl implements IQuartzUtilService {
    // 公用Scheduler
    @Override
    public Scheduler getScheduler() {
        Scheduler scheduler = (StdScheduler) SpringContextUtil.getApplicationContext()
                .getBean("schedulerFactoryBean"); // 同一个对象
        return scheduler;
    }

    @Autowired
    ScheduleJobMapper scheduleJobMapper;

    @Override
    public String getScheduleJobName(String jobName) {
        if (jobNames.contains(jobName)) { // 如果队列中有id，说明其子任务需要执行
            return jobName;
        }
        return null;
    }


    @Override
    public void createChildJob(ScheduleJob job) throws Exception {
        job.setJobName(UUIDGenerator.getUUID() + job.getJobName()); //只是当前任务的名字而已，暂时的标记作用，不影响配置
        MethodInvokingJobDetailFactoryBean methodInvJobDetailFB = new MethodInvokingJobDetailFactoryBean();
        // 设置Job组名称
        methodInvJobDetailFB.setGroup(job.getJobGroup());
        // 设置Job名称
        methodInvJobDetailFB.setName(job.getJobName()); // 注意设置的顺序，如果在管理Job类提交到计划管理类之后设置就会设置不上
        // 定义的任务类为Spring的定义的Bean则调用 getBean方法
        if (job.getIsSpringBean().equals("1")) {// 是Spring中定义的Bean
            methodInvJobDetailFB
                    .setTargetObject(SpringContextUtil.getApplicationContext().getBean(job.getTargetObject()));
        } else {// 不是就直接new
            methodInvJobDetailFB.setTargetObject(Class.forName(job.getClazz()).newInstance());
        }
        // 设置任务方法
        methodInvJobDetailFB.setTargetMethod(job.getTargetMethod());
        // 将管理Job类提交到计划管理类
        methodInvJobDetailFB.afterPropertiesSet();

        /** 并发设置 */
        methodInvJobDetailFB.setConcurrent(job.getConcurrent().equals("1") ? true : false);

        JobDetail jobDetail = (JobDetail) methodInvJobDetailFB.getObject();// 动态
        jobDetail.getJobDataMap().put("scheduleJob", job);

        // 不按照表达式
        Trigger trigger = TriggerBuilder.newTrigger()
                // 保证键值不一样
                .withIdentity(job.getJobName(), job.getJobGroup())
                //.usingJobData("","")//可以存储数据
                .build();
        /**
         原理：
         因为是立即执行,没有用到表达式嘛，所以按照实际的调度创建顺序依次执行
         */
        getScheduler().standby(); //暂时停止 任务都安排完之后统一启动 解决耗时任务按照顺序部署后执行紊乱的问题
        getScheduler().scheduleJob(jobDetail, trigger);// 注入到管理类
        //logger.info("子:" + job.getJobGroup() + "." + job.getJobName() + "创建完毕");
    }

    @Override
    public void executeChildJobs(String mainJob) {
        if (mainJob != null) {
            ScheduleJob scheduleJob = new ScheduleJob();
            scheduleJob.setJobName(mainJob);
            String childs = scheduleJobMapper.selectScheduleJob(scheduleJob).getChildJobs();
            if (null != childs) { //有子任务时才执行
                String[] childsJobId = childs.split(",");
                for (String childJobId : childsJobId) {
                    //System.out.println(childJobId); 打印当前任务的子任务
                    if (!"".equals(childJobId)) {
                        ScheduleJob jobId = new ScheduleJob();
                        jobId.setId(childJobId);
                        ScheduleJob job = scheduleJobMapper.selectScheduleJob(jobId);
                        try {
                            this.createChildJob(job);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        try {
            getScheduler().start();//任务安排完之后启动 解决耗时任务按照顺序部署后执行紊乱的问题
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
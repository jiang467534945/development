package com.platform.common.utils;

import com.platform.upms.model.ScheduleJob;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 2017年10月24日  闫增宝
 * 定时器管理类 ======>>> 任务运行入口
 * DisallowConcurrentExecution注解可实现有状态的任务Job
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    public final Logger log = Logger.getLogger(this.getClass());

    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        //后台控制台打印提示信息
        TaskUtils.invokMethod(scheduleJob);
    }

}

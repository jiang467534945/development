package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.common.page.Page;
import com.platform.common.utils.QuartzJobFactory;
import com.platform.common.utils.UUIDFactory;
import com.platform.upms.dao.ScheduleJobDao;
import com.platform.upms.model.ScheduleJob;
import com.platform.upms.service.ScheduleJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yanzengbao on 2017/10/23.
 */
@Service
@Transactional
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJob> implements ScheduleJobService {

    @Autowired
    private ScheduleJobDao scheduleJobDao;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 页面list  ====>>分页
     *
     * @param page
     * @param scheduleJob
     * @return
     */
    @Override
    public Page<ScheduleJob> findByPage(Page<ScheduleJob> page, ScheduleJob scheduleJob) {
        page.setResults(scheduleJobDao.findByPage(page, scheduleJob));
        return page;
    }

    /**
     * 后台保存任务 ======>>到数据库
     *
     * @param scheduleJob
     */
    @Override
    public void addTask(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setJobId(UUIDFactory.getStringId());
        scheduleJob.setJobStatus("1");//默认添加任务的时候为启动状态
        scheduleJobDao.addTask(scheduleJob);
    }

    /**
     * 查询数据库所有定时任务
     * @return
     */
    @Override
    public List<ScheduleJob> findAll() {
        return scheduleJobDao.findAll();
    }


    /**
     * 把数据库中存的定时任务 加载到定时器
     *
     * @param job
     * @throws SchedulerException
     */
    @Override
    public void addJob(ScheduleJob job) throws SchedulerException {

        if (job == null || !"1".equals(job.getJobStatus())) {
            return;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //在这里把它设计成一个Job对应一个trigger，两者的分组及名称相同，方便管理，条理也比较清晰，在创建任务时如果不存在新建一个，如果已经存在则更新任务
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                    .withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }


    /**
     * 初始化定时任务 =======>> 项目启动运行的任务
     * 2017-10-24 闫增宝
     */
    @Override
    public void initScheduler() {
        try {
            List<ScheduleJob> list = findAll();
            for (ScheduleJob scheduleJob : list) {
                if ("1".equals(scheduleJob.getJobStatus())) {
                    addJob(scheduleJob);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改定时任务的执行状态
     * @param jobId
     * @param jobStatus
     * @throws SchedulerException
     */
    @Override
    public void changeStatus(String jobId, String jobStatus) throws SchedulerException {

        ScheduleJob job = scheduleJobDao.selectById(jobId);
        if (job == null) {
            return;
        }
        if ("0".equals(jobStatus)) {
            deleteJob(job);//移除定时器中的一个job
            job.setJobStatus("0");//修改状态
        } else if ("1".equals(jobStatus)) {
            job.setJobStatus("1");
            addJob(job);
        }
        job.setUpdateTime(new Date());
        scheduleJobDao.updateJob(job);
    }

    /**
     * 2017-10-25 闫增宝
     * 移除定时器中的一个job ======>>>并不是删除数据库中存的任务
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 2017-10-25  闫增宝
     * 删除数据库中的定时任务信息
     * @param ids
     * @throws Exception
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception {
        //1、移除job中的任务
        List<ScheduleJob> scheduleJobs = scheduleJobDao.selectByIds(ids);
        for (ScheduleJob sj:scheduleJobs) {
            if (sj!=null){
                deleteJob(sj);//移除定时器中的一个job
            }
        }
        //2、删除数据库中添加的任务信息
        scheduleJobDao.deleteByIds(ids);
    }

    /**
     * 修改数据库中存储的cron表达式
     * @param jobId
     * @param cronExpression
     * @throws SchedulerException
     */
    @Override
    public void updateCron(String jobId, String cronExpression) throws SchedulerException {
        ScheduleJob job = scheduleJobDao.selectById(jobId);
        if (job == null) {
            return;
        }
        job.setCronExpression(cronExpression);
        //如果是正在运行的定时任务，则需更新定时器中的执行时间
        if ("1".equals(job.getJobStatus())) {
            updateJobCron(job);
        }
        job.setUpdateTime(new Date());
        scheduleJobDao.updateJob(job);
    }


    /**
     * 2017-10-25 闫增宝
     * 更新job时间表达式 ======>>>定时器中的配置
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

}

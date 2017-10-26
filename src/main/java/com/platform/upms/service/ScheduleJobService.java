package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.common.page.Page;
import com.platform.upms.model.ScheduleJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by yanzengbao on 2017/10/23.
 */
public interface ScheduleJobService extends BaseService<ScheduleJob>{

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<ScheduleJob> findByPage(Page<ScheduleJob> page, ScheduleJob scheduleJob);

    /**
     * 保存定时任务（保存到数据库）
     * @param scheduleJob
     */
    void addTask(ScheduleJob scheduleJob);

    /**
     * 初始化定时任务
     */
    void initScheduler();

    /**
     * 添加任务 （添加到Job任务）
     * @param scheduleJob
     */
    void addJob(ScheduleJob scheduleJob) throws SchedulerException;

    /**
     * 查询所有任务（数据库）
     * @return
     */
    List<ScheduleJob> findAll();

    /**
     * 修改定时任务的执行状态
     * @param jobId
     * @param jobStatus
     * @throws SchedulerException
     */
    void changeStatus(String jobId, String jobStatus) throws SchedulerException;

    /**
     * 2017-10-25  闫增宝
     * 删除数据库中的定时任务信息
     * @param ids
     * @throws Exception
     */
    void deleteByIds(String[] ids) throws Exception;

    void updateCron(String jobId, String cronExpression) throws SchedulerException;
}

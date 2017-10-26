package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.common.page.Page;
import com.platform.upms.model.ScheduleJob;

import java.util.List;

/**
 * Created by yanzengbao on 2017/10/23.
 */
@MyBatisDao
public interface ScheduleJobDao extends BaseDao<ScheduleJob>{

    List<ScheduleJob> findByPage(Page<ScheduleJob> page, ScheduleJob scheduleJob);

    void addTask(ScheduleJob scheduleJob);

    List<ScheduleJob> findAll();

    ScheduleJob selectById(String jobId);

    List<ScheduleJob> selectByIds(String[] ids);

    void deleteByIds(String[] ids);

    void updateJob(ScheduleJob job);
}

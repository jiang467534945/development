package com.platform.upms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 动态定时任务表（保存定时执行的任务信息）
 * Created by yanzengbao on 2017/10/20.
 */
public class ScheduleJob {

    /**
     * 唯一标识 ID
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 是否启动任务 1：启动  0：停止
     */
    private String jobStatus;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 描述
     */
    private String description;
    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;
    /**
     * spring bean
     */
    //private String springId;
    /**
     * 任务调用的方法名
     */
    private String methodName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public ScheduleJob(){}

    public ScheduleJob(String jobId, String jobName, String jobGroup, String jobStatus, String cronExpression, String description, String beanClass, /*String springId,*/ String methodName, Date createTime, Date updateTime) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobStatus = jobStatus;
        this.cronExpression = cronExpression;
        this.description = description;
        this.beanClass = beanClass;
        //this.springId = springId;
        this.methodName = methodName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ScheduleJob(String jobId, String jobStatus) {
        this.jobId = jobId;
        this.jobStatus = jobStatus;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

//    public String getSpringId() {
//        return springId;
//    }
//
//    public void setSpringId(String springId) {
//        this.springId = springId;
//    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

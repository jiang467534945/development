package com.platform.upms.controller;

import com.platform.common.page.Page;
import com.platform.upms.model.ScheduleJob;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.ScheduleJobService;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * 2017-10-24 闫增宝
 * 任务调度
 */
@Controller
@RequestMapping("/scheduleJob")
public class ScheduleJobController {

	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 弹出添加页面
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(){
		return "/scheduleJob/add.jsp";
	}


	/**
	 * 动态定时任务页面
	 * @param modelMap
	 * @return list 页面
	 */
	@RequestMapping(value = "/list")
	public String list(ModelMap modelMap, Page<ScheduleJob> page) {
		Page<ScheduleJob> pageList = scheduleJobService.findByPage(page,null);
		modelMap.addAttribute("pageList", pageList);
		return "/scheduleJob/list.jsp";
	}


	/**
	 * 2017-10-25 闫增宝
	 * 保存定时任务到数据库并更新任务
	 * @param scheduleJob
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Tip save(ScheduleJob scheduleJob){

		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		}catch (Exception e){
			e.printStackTrace();
			return new Tip(1,"cron表达式有误，不能被解析！");
		}

		Object obj = null;
		try {
//			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
//				obj = SpringUtils.getBean(scheduleJob.getSpringId());
//			} else {
				Class clazz = Class.forName(scheduleJob.getBeanClass());
				obj = clazz.newInstance();
//			}
		}catch (Exception e){
			e.printStackTrace();
		}

		if (obj == null) {
			return new Tip(1,"未找到目标类！");
		} else {
			Class clazz = obj.getClass();
			Method method = null;
			try {
				method = clazz.getMethod(scheduleJob.getMethodName(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (method == null) {
				return new Tip(1,"未找到目标方法！");
			}
		}

		try {
			//保存任务
			scheduleJobService.addTask(scheduleJob);
			//初始化定时任务
			scheduleJobService.initScheduler();
			return new Tip("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new Tip(1,"保存失败，检查 name group 组合是否有重复！");
		}
	}

	/**
	 * 2017-10-25 闫增宝
	 * 修改任务状态
	 * @param jobId 任务id
	 * @param jobStatus 任务状态
	 * @return
	 */
	@RequestMapping(value = "/changeJobStatus")
	@ResponseBody
	public Tip changeJobStatus(String jobId,String jobStatus){
		try {
			//修改定时任务的执行状态
			scheduleJobService.changeStatus(jobId,jobStatus);
			//初始化定时任务
			scheduleJobService.initScheduler();
			if ("0".equals(jobStatus)){
				return new Tip("任务已停止！");
			}else{
				return new Tip("任务已启动！");
			}
		}catch (SchedulerException e){
			e.printStackTrace();
			return new Tip(1,"任务状态修改失败！");
		}
	}

	/**
	 * 删除数据库中的定时任务信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public Tip deletes(String []ids){
		try {
			scheduleJobService.deleteByIds(ids);
			//初始化定时任务
			scheduleJobService.initScheduler();
			return new Tip("删除成功！");
		} catch(Exception e) {
			e.printStackTrace();
			return new Tip(1,"删除失败！");
		}
	}

	/**
	 * 弹出修改cron页面
	 * @param cron cron表达式
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/editCron")
	public String editCron(String cron, String jobId, ModelMap modelMap){
		modelMap.addAttribute("jobId",jobId);
		modelMap.addAttribute("cron",cron);
		return "/scheduleJob/editCron.jsp";
	}

	/**
	 * 修改cron 表达式
	 * @param jobId
	 * @param cronExpression
	 * @return
	 */
	@RequestMapping(value = "/updateCron")
	@ResponseBody
	public Tip updateCron(String jobId, String cronExpression){
		System.out.println(jobId+"------------"+cronExpression);
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		} catch (Exception e) {
			e.printStackTrace();
			return new Tip(1,"cron表达式有误，不能被解析！");
		}

		try {
			scheduleJobService.updateCron(jobId, cronExpression);
			return new Tip("cron更新成功！");
		} catch (SchedulerException e) {
			e.printStackTrace();
			return new Tip(1,"cron更新失败！");
		}
	}
}

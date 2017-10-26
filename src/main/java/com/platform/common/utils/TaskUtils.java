package com.platform.common.utils;

import com.platform.upms.model.ScheduleJob;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 动态定时任务用到的工具类
 */
public class TaskUtils {

	public final static Logger log = Logger.getLogger(TaskUtils.class);
	
	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */

	public static void invokMethod(ScheduleJob scheduleJob) {
		Object object = null;
		Class clazz = null;
		/*if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringUtils.getBean(scheduleJob.getSpringId());
		} else */if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (object == null) {
			log.error("未启动成功，请检查是否配置正确！任务名称 = [" + scheduleJob.getJobName() + "]");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("未启动成功，方法名设置错误！任务名称 = [" + scheduleJob.getJobName() + "]");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		System.out.println("启动成功，任务名称 = [" + scheduleJob.getJobName() + "]");
	}

}

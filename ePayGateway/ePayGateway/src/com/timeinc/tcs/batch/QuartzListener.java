package com.timeinc.tcs.batch;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzListener implements ServletContextListener {
        Scheduler scheduler = null;

        @Override
	public void contextInitialized(ServletContextEvent servletContext) {
		System.out.println("Context Initialized");
		try {
			// Setup the Job class and the Job group
			JobDetail job = newJob(QuartzJob.class).withIdentity(
					"CronQuartzJob", "Group").build();

			JobDetail androidJob = newJob(QuartzAndroidJob.class).withIdentity(
					"CronQuartzJob1", "Group1").build();
			
			// Create a Trigger that fires every 5 minutes.
			Trigger trigger = newTrigger()
					.withIdentity("TriggerName", "Group")
					.withSchedule(
							CronScheduleBuilder
									.cronSchedule("0 15 11 1/1 * ? *")).build();
			
			// Create a Trigger that everyday at 11:30 PM EST.
			Trigger trigger1 = newTrigger()
					.withIdentity("TriggerName1", "Group1")
					.withSchedule(
							CronScheduleBuilder
									.cronSchedule("0 30 23 1/1 * ? *")).build();
			
			// Setup the Job and Trigger with Scheduler & schedule jobs
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
			scheduler.scheduleJob(androidJob, trigger1);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

        @Override
        public void contextDestroyed(ServletContextEvent servletContext) {
                System.out.println("Context Destroyed");
                try 
                {
                        scheduler.shutdown();
                } 
                catch (SchedulerException e) 
                {
                        e.printStackTrace();
                }
        }
}

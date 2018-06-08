package com.wtytest.job;

import org.quartz.*;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;


public class SchedulerUtils {
    public static final String JOB_VO = "jobVo";

    public static Date getNextValidTimeAfter(String cronExpression, Date curTime) throws ParseException {
        CronExpression expression;
        expression = new CronExpression(cronExpression);
        return expression.getNextValidTimeAfter(curTime);
    }

    public static ScheduleBuilder getScheduleBuilder(String cronExpression) {
        return CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
    }

    public static Trigger buildTrigger(BaseScheduleTask task) {
        Trigger trigger;
        String cronExpression = task.getCronExpression();
        if (StringUtils.isEmpty(cronExpression)) {
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(task.getName() + "_trigger", task.getGroup())
                    .startNow()
                    .build();
        } else {
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(task.getName() + "_trigger", task.getGroup())
                    .withSchedule(SchedulerUtils.getScheduleBuilder(cronExpression))
                    .build();
        }
        return trigger;
    }

    public static JobDetail buildJob(BaseScheduleTask task, Class jobClazz) {
        JobDataMap map = new JobDataMap();
        map.put(JOB_VO, task);
        return JobBuilder.newJob().ofType(jobClazz)
                .withIdentity(task.getName(), task.getGroup())
                .usingJobData(map)
                .build();
    }

    public static TriggerKey buildTriggerKey(BaseScheduleTask task) {
        return TriggerKey.triggerKey(task.getName(), task.getGroup());
    }

    public static JobKey buildJobKey(BaseScheduleTask task) {
        return JobKey.jobKey(task.getName(), task.getGroup());
    }


}

package com.wtytest.service;

import org.quartz.*;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;


public class SchedulerUtils {
    public static final String JOB_KEY_CRON = "job_key_cron";
    public static final String JOB_KEY_LANGUAGE = "job_key_language";
    public static final String JOB_KEY_CONTENT = "job_key_content";
    private static final String JOB_KEY_DEFAULT_GROUP = "job_key_default_group";

    public static Date getNextValidTimeAfter(String cronExpression, Date curTime) throws ParseException {
        CronExpression expression;
        expression = new CronExpression(cronExpression);
        return expression.getNextValidTimeAfter(curTime);
    }

    public static ScheduleBuilder getScheduleBuilder(String cronExpression) {
        return CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
    }

    public static Trigger buildTrigger(ScriptExecuteVo vo) {
        Trigger trigger;
        if (StringUtils.isEmpty(vo.getCronExpression())) {
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(vo.getName() + "_trigger", vo.getName()) // group要改成用户id
                    .startNow()
                    .build();
        } else {
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(vo.getName() + "_trigger", vo.getName()) // group要改成用户id
                    .withSchedule(SchedulerUtils.getScheduleBuilder(vo.getCronExpression()))
                    .build();
        }
        return trigger;
    }

    public static JobDetail buildJob(ScriptExecuteVo vo) {
        // 要根据scriptId查出来下面这些属性
        return JobBuilder.newJob().ofType(ScriptExecuteJob.class)
                .withIdentity(vo.getName(), JOB_KEY_DEFAULT_GROUP)
                .usingJobData(JOB_KEY_CRON, vo.getCronExpression())
                .usingJobData(JOB_KEY_LANGUAGE, vo.getLanguage())
                .usingJobData(JOB_KEY_CONTENT, vo.getContent())
                .build();
    }

}

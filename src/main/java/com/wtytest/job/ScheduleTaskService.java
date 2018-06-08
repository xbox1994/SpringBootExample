package com.wtytest.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wtytest.job.SchedulerUtils.*;

@Service
@Slf4j
public class ScheduleTaskService {

    @Autowired
    Scheduler scheduler;

    public void createTask(BaseScheduleTask task, Class jobClass) throws SchedulerException {
        log.info("create schedule job: {} in {}", task.getName(), task.getGroup());
        JobDetail jobDetail = buildJob(task, jobClass);
        Trigger trigger = buildTrigger(task);

        JobKey key = jobDetail.getKey();
        if (scheduler.checkExists(key)) {
            log.info("delete existed schedule job: {} in {}", task.getName(), task.getGroup());
            scheduler.deleteJob(key);
        }

        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void deleteTask(BaseScheduleTask task) throws SchedulerException {
        log.info("delete schedule job: {} in {}", task.getName(), task.getGroup());
        TriggerKey key = buildTriggerKey(task);
        scheduler.pauseTrigger(key);
        scheduler.unscheduleJob(key);
        scheduler.deleteJob(JobKey.jobKey(task.getName(), task.getGroup()));
    }

    public void pauseTask(BaseScheduleTask task) throws SchedulerException {
        log.info("pause schedule job: {} in {}", task.getName(), task.getGroup());
        scheduler.pauseJob(buildJobKey(task));
    }

    public void resumeTask(BaseScheduleTask task) throws SchedulerException {
        log.info("resume schedule job: {} in {}", task.getName(), task.getGroup());
        scheduler.resumeJob(buildJobKey(task));
    }
}

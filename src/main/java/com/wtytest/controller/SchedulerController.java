package com.wtytest.controller;

import com.wtytest.service.SchedulerUtils;
import com.wtytest.service.ScriptExecuteVo;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/schedule")
public class SchedulerController {

    @Autowired
    Scheduler scheduler;

    @PostMapping
    public String post(@RequestBody @Valid ScriptExecuteVo vo) throws SchedulerException {
        // 在数据库中根据vo创建Task
        scheduler.scheduleJob(SchedulerUtils.buildJob(vo), SchedulerUtils.buildTrigger(vo));
        return "success";
    }

    @DeleteMapping
    public String delete(@RequestBody @Valid ScriptExecuteVo vo) throws SchedulerException {
        TriggerKey key = TriggerKey.triggerKey(vo.getName(), vo.getName());
        scheduler.pauseTrigger(key);
        scheduler.unscheduleJob(key);
        scheduler.deleteJob(JobKey.jobKey(vo.getName(), vo.getName()));
        return "success";
    }

}

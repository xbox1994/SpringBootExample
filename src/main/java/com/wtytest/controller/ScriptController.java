package com.wtytest.controller;

import com.wtytest.job.ScheduleTaskService;
import com.wtytest.job.ScriptExecuteBo;
import com.wtytest.job.ScriptExecuteJob;
import com.wtytest.job.ScriptExecuteVo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/schedule/script")
@Slf4j
public class ScriptController {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @PostMapping
    public String post(@RequestBody @Valid ScriptExecuteVo vo) throws SchedulerException {
        ScriptExecuteBo bo = buildScriptExecuteBo(vo);
        scheduleTaskService.createTask(bo, ScriptExecuteJob.class);
        return "success";
    }

    @PostMapping("/pause")
    public String pause(@RequestBody @Valid ScriptExecuteVo vo) throws SchedulerException {
        ScriptExecuteBo bo = buildScriptExecuteBo(vo);
        scheduleTaskService.pauseTask(bo);
        return "success";
    }

    @PostMapping("/resume")
    public String resumeTask(@RequestBody @Valid ScriptExecuteVo vo) throws SchedulerException {
        ScriptExecuteBo bo = buildScriptExecuteBo(vo);
        scheduleTaskService.resumeTask(bo);
        return "success";
    }

    @DeleteMapping
    public String delete(@RequestBody @Valid ScriptExecuteVo vo) throws SchedulerException {
        ScriptExecuteBo bo = buildScriptExecuteBo(vo);
        scheduleTaskService.deleteTask(bo);
        return "success";
    }

    private ScriptExecuteBo buildScriptExecuteBo(ScriptExecuteVo vo) {
        ScriptExecuteBo bo = new ScriptExecuteBo();
        BeanUtils.copyProperties(vo, bo);
        bo.setName(vo.getScriptId());
        bo.setGroup("current user id");
        return bo;
    }

}

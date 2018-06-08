package com.wtytest.job;

import com.wtytest.ssh.SSHManager;
import com.wtytest.ssh.SSHOutput;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class ScriptExecuteJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScriptExecuteBo bo = (ScriptExecuteBo) jobExecutionContext.getJobDetail().getJobDataMap().get(SchedulerUtils.JOB_VO);

        // 要根据scriptId查出来username password ip
        for (String instanceId : bo.getInstances()) {
            log.info("执行：scriptId:{}, instanceId: {}", bo.getScriptId(), instanceId);
            // 向e_script_execution_log插入新数据

            // 执行中
            String status = "success";
            String userName = "ubuntu";
            String password = "ubuntu";
            String connectionIP = "13.250.14.139";
            SSHManager instance = new SSHManager(userName, password, connectionIP);
            SSHOutput output;
            output = instance.sendCommand("#!/bin/bash\n" +
                    "touch aaa\n" +
                    "ls -la aaa\n" +
                    "lww;\n" +
                    "pwd");
            if (output == null) {
                status = "error";
                output = SSHOutput.empty();
            }
            instance.close();
            // 执行结束

            // 将结果更新到e_script_execution_log
            log.info("结果：status:{}, scriptId:{}, instanceId: {} stdout:{}, stderr:{}", status, bo.getScriptId(), instanceId, output.getStdout(), output.getStderr());
        }

    }
}

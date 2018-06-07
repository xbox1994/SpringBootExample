package com.wtytest.service;

import com.wtytest.ssh.SSHManager;
import com.wtytest.ssh.SSHOutput;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import static com.wtytest.service.SchedulerUtils.*;

@Slf4j
public class ScriptExecuteJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String name = jobDetail.getKey().getName();
        String cron = (String) jobDataMap.get(JOB_KEY_CRON);
        String language = (String) jobDataMap.get(JOB_KEY_LANGUAGE);
        String content = (String) jobDataMap.get(JOB_KEY_CONTENT);

        log.info("执行：{},{},{},{}", name, cron, language, content);
        // 向e_script_execution_log插入数据
        String userName = "ubuntu";
        String password = "ubuntu";
        String connectionIP = "13.250.14.139";
        SSHManager instance = new SSHManager(userName, password, connectionIP);
        SSHOutput output = null;
        try {
            output = instance.sendCommand("#!/bin/bash\n" +
                    "touch aaa\n" +
                    "ls -la aaa\n" +
                    "lww;\n" +
                    "pwd").orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(output.getStdout());
        System.err.println(output.getStderr());
        // 将结果更新到e_script_execution_log
        instance.close();

    }
}

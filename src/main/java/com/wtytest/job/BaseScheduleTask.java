package com.wtytest.job;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseScheduleTask {
    private String name;
    private String group;
    private String cronExpression;
}

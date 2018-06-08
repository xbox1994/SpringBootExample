package com.wtytest.job;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ScriptExecuteVo {
    @NotNull
    private String scriptId;
    @NotNull
    private List<String> instances;
    private String cronExpression;
}

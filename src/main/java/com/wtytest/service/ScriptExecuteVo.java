package com.wtytest.service;

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
    @NotNull
    private String name;
    @NotNull
    private String language;
    @NotNull
    private String content;
    private String cronExpression;
}

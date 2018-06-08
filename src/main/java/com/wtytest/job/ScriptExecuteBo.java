package com.wtytest.job;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ScriptExecuteBo extends BaseScheduleTask implements Serializable {
    @NotNull
    private String scriptId;
    @NotNull
    private List<String> instances;
}

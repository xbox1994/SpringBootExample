package com.wtytest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo")     // 通过这里配置使下面的映射都在/users下
public class DemoController {

    @GetMapping
    public String get(ModelMap model) {
        return "hello";
    }

}
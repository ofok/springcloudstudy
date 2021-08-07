package com.jiuge.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ScopeRefershController {

    @Value("${common.name}")
    private String name;
    @Value("${common.age}")
    private String age;

    @GetMapping("/common")
    public String hello() {
        return "name =" + name + ",age = " + age;
    }
}

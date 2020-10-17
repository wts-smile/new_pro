package com.seven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/test")
    public String test(){
// 注释
        return "test controller";
    }
}

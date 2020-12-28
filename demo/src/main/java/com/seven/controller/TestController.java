package com.seven.controller;

import com.seven.pojo.User;
import com.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/test")
    public User test(){
        // zhushi
        return userService.getUserByName("dzy");
    }
}

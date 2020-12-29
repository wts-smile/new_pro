package com.seven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageConntroller {
    @RequestMapping("/ping")
    public String pingPage(Model model) {
        return "ping";
    }

}

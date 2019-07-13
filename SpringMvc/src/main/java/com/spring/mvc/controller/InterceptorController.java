package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/interceptor")
@Controller
public class InterceptorController {

    @GetMapping("/start")
    public String start() {
        System.out.println("执行处理逻辑");
        return "/interceptor/welcome";
    }
}

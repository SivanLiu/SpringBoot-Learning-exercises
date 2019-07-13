package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/advice")
public class AdviceController {
    @GetMapping("/test")
    public String test(Date date, ModelMap modelMap) {
        //从数据模型中获取数据
        System.out.println(modelMap.get("project_name"));
        //打印日期参数
        System.out.println(format(date));
        //抛出异常，这样流转到控制器异常通知
        throw new RuntimeException("异常了，跳转到控制器通知的异常信息里");
    }

    private String format(Date date) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return simpleFormatter.format(date);
    }
}

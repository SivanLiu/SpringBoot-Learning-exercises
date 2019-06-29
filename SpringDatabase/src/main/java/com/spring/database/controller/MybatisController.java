package com.spring.database.controller;

import com.spring.database.pojo.User;
import com.spring.database.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private MybatisUserService mybatisUserService = null;

    @RequestMapping("/getUser")
    public User getUser(Long id) {
        return mybatisUserService.getUser(id);
    }
}

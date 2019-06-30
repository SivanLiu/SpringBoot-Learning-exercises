package com.spring.database.controller;

import com.spring.database.SexEnum;
import com.spring.database.pojo.User;
import com.spring.database.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private MybatisUserService mybatisUserService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        return mybatisUserService.getUser(id);
    }

    @RequestMapping("/insertUser")

    public void insertUser(Long id, String note, String userName) {
        User user = new User();
        user.setId(id);
        user.setSex(SexEnum.FEMALE);
        user.setNote(note);
        user.setUserName(userName);
        mybatisUserService.insertUser(user);
    }
}

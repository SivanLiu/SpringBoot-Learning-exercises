package com.spring.mvc.controller;

import com.spring.mvc.pojo.User;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
// @SessionAttributes 指定数据模型名称或者属性类型，保存到 Session 中
@SessionAttributes(names = { "user" }, types = Long.class)
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private UserService userService = null;

    //@SessionAttribute 从 HttpSession 中取出数据，填充控制器方法参数
    @GetMapping("/test")
    public String test(@SessionAttribute("id") Long id, Model model) {
        //根据类型保存到 Session 中
        model.addAttribute("id_new", id);
        User user = userService.getUser(id);
        //根据名称保存到 Session 中
        model.addAttribute("user", user);
        return "session/test";
    }

}

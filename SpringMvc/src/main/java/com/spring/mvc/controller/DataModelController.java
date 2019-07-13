package com.spring.mvc.controller;

import com.spring.mvc.pojo.User;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/data")
@Controller
public class DataModelController {

    //注入用户服务类
    @Autowired
    private UserService userService = null;

    //测试 Model 接口
    @GetMapping("/model")
    public String userModel(Long id, Model model) {
        User user = userService.getUser(id);
        //这里返回了字符串, 在 Spring MVC 中，会自动创建 ModelAndView 且绑定名称
        model.addAttribute("user", user);
        return "data/user";
    }

    //测试 ModelMpa 类
    @GetMapping("/modelMap")
    public ModelAndView userModelMap(Long id, ModelMap modelMap) {
        User user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        //设置视图名称
        modelAndView.setViewName("data/user");
        //设置数据模型，此处 modelMap 并没有与 mv 绑定, 这步系统会自动处理
        modelMap.put("user", user);
        return modelAndView;
    }

    //测试 ModelAndView
    @GetMapping("/mav")
    public ModelAndView userModelAndView(Long id, ModelAndView modelAndView) {
        User user = userService.getUser(id);
        //设置数据模型
        modelAndView.addObject("user", user);
        //设置视图名称
        modelAndView.setViewName("data/user");
        return modelAndView;
    }
}

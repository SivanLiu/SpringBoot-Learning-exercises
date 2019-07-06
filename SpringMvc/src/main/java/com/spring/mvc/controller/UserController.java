package com.spring.mvc.controller;

import com.spring.mvc.pojo.User;
import com.spring.mvc.pojo.ValidaterPojo;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    public ModelAndView details(Long id) {
        User user = userService.getUser(id);
        //模型和视图
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/details");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public User insertUser(String userName, String note) {
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        return user;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Map<String, Object> updateUserName(Long id, String userName) {
        User user = userService.updateUserName(id, userName);
        boolean flag = user != null;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(Long id) {
        int result = userService.deleteUser(id);
        boolean flag = result == 1;
        String message = flag ? "删除成功" : "删除失败";
        return resultMap(flag, message);
    }

    @RequestMapping("/detailsForJson")
    public ModelAndView detailsForJson(Long id) {
        User user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

//    @RequestMapping("/table")
//    public ModelAndView table() {
//        List<User> userList = userService.findUsers(null, null);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("user/table");
//        modelAndView.addObject("userList", userList);
//        return modelAndView;
//    }

    @RequestMapping("/table")
    public ModelAndView table() {
        // 访问模型层得到数据
        List<User> userList = userService.findUsers(null, null);
        // 模型和视图
        ModelAndView mv = new ModelAndView();
        // 定义模型视图
        mv.setViewName("user/table");
        // 加入数据模型
        mv.addObject("userList", userList);
        // 返回模型和视图
        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(@RequestParam(value = "userName", required = false) String userName,
                           @RequestParam(value = "note", required = false) String note) {
        List<User> userList = userService.findUsers(userName, note);
        return userList;
    }


    @GetMapping("valid/page")
    public String validatePage() {
        return "user/pojo";
    }

    /**
     * 解析验证参数错误
     *
     * @param validaterPojo 需要验证的 POJO, 使用注解 @Valid 表示验证
     * @param errors        错误信息，它由 Spring MVC 通过验证 POJO 后自动填充
     * @return 错误信息
     */
    @RequestMapping(value = "/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidaterPojo validaterPojo, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        //获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError objectError : oes) {
            String key;
            String msg;
            //字段错误
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                key = fieldError.getField();
            } else {
                //非字段错误
                key = objectError.getObjectName();
            }
            //错误信息
            msg = objectError.getDefaultMessage();
            System.out.println("gggg " + msg);
            errMap.put(key, msg);
        }
        return errMap;
    }

    private Map<String, Object> resultMap(boolean success, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        return result;
    }
}

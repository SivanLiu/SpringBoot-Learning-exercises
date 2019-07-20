package com.spring.webfluex.controller;

import com.spring.webfluex.pojo.User;
import com.spring.webfluex.service.UserService;
import com.spring.webfluex.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserController {

    @Autowired
    private UserService userService;

    //获取用户
    @GetMapping("/user/id")
    public Mono<UserVo> getUser(@PathVariable Long id) {
        return userService.getUser(id).map(this::translate);
    }

    //新增用户
    @PostMapping("/user")
    public Mono<UserVo> insertUser(@RequestBody User user) {
        return userService.insertUser(user).map(this::translate);
    }

    //更新用户
    @PostMapping("/user")
    public Mono<UserVo> updateUser(@RequestBody User user) {
        return userService.updatetUser(user).map(this::translate);
    }

    //删除用户
    @DeleteMapping("/user/id")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return userService.deletetUser(id);
    }

    //查询用户
    @GetMapping("/user/{userName}/{note}")
    public Flux<UserVo> findUsers(@PathVariable String userName, @PathVariable String note){
        return userService.findUsers(userName, note)
                .map(this::translate);
    }

    private UserVo translate(User user) {
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUserName());
        userVo.setSexCode(user.getSex().getCode());
        userVo.setSexName(user.getSex().getName());
        userVo.setNote(user.getNote());
        userVo.setId(user.getId());
        return userVo;
    }
}

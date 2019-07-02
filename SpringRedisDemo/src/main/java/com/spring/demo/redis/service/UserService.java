package com.spring.demo.redis.service;

import com.spring.demo.redis.pojo.User;

import java.util.List;

public interface UserService {
    //获取单个用户
    User getUser(Long id);

    //保存用户
    User insertUser(User user);

    //修改用户
    User updateUserName(Long id, String userName);

    //查找用户, 指定 mybatis 的参数名称
    List<User> findUsers(String userName, String note);

    //删除用户
    int deleteUser(Long id);
}

package com.spring.demo.redis.dao;

import com.spring.demo.redis.pojo.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    //获取单个用户
    User getUser(Long id);

    //保存用户
    int insertUser(User user);

    //修改用户
    int updateUser(User user);

    //查询用户, 指定 MyBatis 的参数名称
    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);

    //删除用户
    int deleteUser(Long id);
}

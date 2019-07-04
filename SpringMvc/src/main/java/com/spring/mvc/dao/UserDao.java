package com.spring.mvc.dao;

import com.spring.mvc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
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

package com.spring.mvc.rest.dao;

import com.spring.mvc.rest.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public User getUser(Long id);

    public int insertUser(User user);

    public List<User> findUsers(@Param("userName") String userName, @Param("note") String note,
                                @Param("start") int start, @Param("limit") int limit);

    public int updateUser(User user);

    public int updateUserName(@Param("id") Long id, @Param("userName") String userName);

    public int deleteUser(Long id);
}

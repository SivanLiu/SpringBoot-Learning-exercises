package com.spring.database.dao;

import com.spring.database.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MybatisUserDao {
    public User getUser(Long id);
}

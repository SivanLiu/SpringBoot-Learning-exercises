package com.spring.database.service.impl;

import com.spring.database.dao.MybatisUserDao;
import com.spring.database.pojo.User;
import com.spring.database.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisUserServiceImpl implements MybatisUserService {

    @Autowired
    private MybatisUserDao mybatisUserDao = null;

    @Override
    public User getUser(Long id) {
        return mybatisUserDao.getUser(id);
    }
}

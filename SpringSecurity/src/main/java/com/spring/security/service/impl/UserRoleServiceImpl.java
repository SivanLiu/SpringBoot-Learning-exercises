package com.spring.security.service.impl;

import com.spring.security.dao.RoleDao;
import com.spring.security.dao.UserDao;
import com.spring.security.pojo.DatabaseRole;
import com.spring.security.pojo.DatabaseUser;
import com.spring.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserDao userDao = null;
    @Autowired
    private RoleDao roleDao = null;

    @Override
    @Cacheable(value = "redisCache", key = "'redis_user_'+#userName")
    @Transactional
    public DatabaseUser getUserByName(String userName) {
        return userDao.getUser(userName);
    }

    @Override
    @Cacheable(value = "redisCache", key = "'redis_user_role_'+#userName")
    @Transactional
    public List<DatabaseRole> findRolesByUserName(String userName) {
        return roleDao.findRolesByUserName(userName);
    }

}

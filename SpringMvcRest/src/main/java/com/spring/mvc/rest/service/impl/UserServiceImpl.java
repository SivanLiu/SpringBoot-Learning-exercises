package com.spring.mvc.rest.service.impl;

import com.spring.mvc.rest.dao.UserDao;
import com.spring.mvc.rest.pojo.User;
import com.spring.mvc.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    /**
     * 插入用户, 最后 mybatis 会回填 id, 取结果 id 缓存用户
     *
     * @param id
     * @return
     */
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    /**
     * 获取 id, 取参数 id 缓存用户
     *
     * @param user
     * @return
     */
    @Override
    public User insertUser(User user) {
        userDao.insertUser(user);
        return user;
    }

    /**
     * 更新数据后, 更新缓存, 如果 condition 配置项使结果返回 null, 不缓存
     *
     * @param id
     * @param userName
     * @return
     */
    @Override
    public int updateUserName(Long id, String userName) {
        return userDao.updateUserName(id, userName);
    }

    /**
     * 命中率低, 所以不采用缓存
     */

    @Override
    public List<User> findUsers(String userName, String note, int start, int limit) {
        return userDao.findUsers(userName, note, start, limit);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    /**
     * 移除缓存
     *
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}

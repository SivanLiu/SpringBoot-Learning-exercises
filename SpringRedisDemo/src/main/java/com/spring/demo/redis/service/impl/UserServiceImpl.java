package com.spring.demo.redis.service.impl;

import com.spring.demo.redis.dao.UserDao;
import com.spring.demo.redis.pojo.User;
import com.spring.demo.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    /**
     * 插入用户, 最后 mybatis 会回填 id, 取结果 id 缓存用户
     * @param id
     * @return
     */
    @Override
    @Transactional
    @Cacheable(value = "redisCache", key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    /**
     * 获取 id, 取参数 id 缓存用户
     * @param user
     * @return
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", key = " 'redis_user_' + #result.id")
    public User insertUser(User user) {
        userDao.insertUser(user);
        return user;
    }

    /**
     * 更新数据后, 更新缓存, 如果 condition 配置项使结果返回 null, 不缓存
     * @param id
     * @param userName
     * @return
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != 'null '", key = "'redis_user_'+#id")
    public User updateUserName(Long id, String userName) {
        User user = this.getUser(id);
        if (user == null) {
            return null;
        }
        user.setUserName(userName);
        userDao.updateUser(user);
        return user;
    }

    /**
     * 命中率低, 所以不采用缓存
     */

    @Override
    @Transactional
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName, note);
    }

    /**
     * 移除缓存
     * @param id
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}

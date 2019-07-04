package com.spring.mvc.service.impl;

import com.spring.mvc.dao.UserDao;
import com.spring.mvc.pojo.User;
import com.spring.mvc.service.UserService;
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
    public List<User> findUsers(String userName, String note) {
        System.out.println("gggg userName = " + userName + " note = " + note);
        return userDao.findUsers(userName, note);
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

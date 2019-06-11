package com.sivan.spring.demo.service;

import com.sivan.spring.demo.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);
}

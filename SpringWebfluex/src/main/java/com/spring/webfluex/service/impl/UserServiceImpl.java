package com.spring.webfluex.service.impl;

import com.spring.webfluex.pojo.User;
import com.spring.webfluex.repository.UserRepository;
import com.spring.webfluex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> updatetUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<Void> deletetUser(Long id) {
        Mono<Void> result = userRepository.deleteById(id);
        return result;
    }

    @Override
    public Flux<User> findUsers(String userName, String note) {
        return userRepository.findByUserNameLikeAndNoteLike(userName, note);
    }
}

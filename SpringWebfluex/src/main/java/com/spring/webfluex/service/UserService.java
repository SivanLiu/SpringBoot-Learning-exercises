package com.spring.webfluex.service;

import com.spring.webfluex.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> getUser(Long id);

    Mono<User> insertUser(User user);

    Mono<User> updatetUser(User user);

    Mono<Void> deletetUser(Long id);

    Flux<User> findUsers(String userName, String note);

}

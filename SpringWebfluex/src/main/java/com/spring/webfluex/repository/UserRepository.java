package com.spring.webfluex.repository;

import com.spring.webfluex.pojo.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long> {

    /**
     * 对用户名和备注进行模糊查询
     * @param userName
     * @param note
     * @return
     */
    public Flux<User> findByUserNameLikeAndNoteLike(String userName, String note);
}

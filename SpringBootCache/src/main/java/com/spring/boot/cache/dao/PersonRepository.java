package com.spring.boot.cache.dao;

import com.spring.boot.cache.domin.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

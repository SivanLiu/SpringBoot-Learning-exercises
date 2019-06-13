package com.spring.boot.transaction.dao;

import com.spring.boot.transaction.domin.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

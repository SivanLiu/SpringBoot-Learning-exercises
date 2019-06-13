package com.spring.boot.cache.service;

import com.spring.boot.cache.domin.Person;

public interface DemoService {

    public Person save(Person person);

    public void remove(Long id);

    public Person findOne(Person person);
}

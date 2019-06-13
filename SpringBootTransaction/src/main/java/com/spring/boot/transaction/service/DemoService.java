package com.spring.boot.transaction.service;

import com.spring.boot.transaction.domin.Person;

public interface DemoService {
    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithoutRollBack(Person person);
}

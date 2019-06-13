package com.spring.boot.transaction.service;

import com.spring.boot.transaction.domin.Person;
import com.spring.boot.transaction.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    PersonRepository personRepository;

    @Transactional(rollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if("汪云飞".equals(p.getName())){
            throw new IllegalArgumentException("汪云飞已经存在,数据将回滚");
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);

        if("汪云飞".equals(p.getName())){
            throw new IllegalArgumentException("汪云飞已经存在,数据将不会回滚");
        }
        return p;
    }
}

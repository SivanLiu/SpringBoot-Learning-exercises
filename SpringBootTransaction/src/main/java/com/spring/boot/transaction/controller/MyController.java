package com.spring.boot.transaction.controller;

import com.spring.boot.transaction.domin.Person;
import com.spring.boot.transaction.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/rollback")
    public Person rollBack(Person person){
        return demoService.savePersonWithRollBack(person);
    }

    @RequestMapping("/norollback")
    public Person noRollBack(Person person){
        return demoService.savePersonWithoutRollBack(person);
    }

}

package com.sivan.spring.demo;

import com.sivan.spring.demo.config.BookComponent;
import com.sivan.spring.demo.config.BookProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SivanSpringApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    BookProperties bookProperties;

    @Autowired
    BookComponent bookComponent;

    @Test
    public void testBookProperties() {
        Assert.assertEquals(bookProperties.getName(),"[Spring Boot 2.x Core Action]");
        Assert.assertEquals(bookProperties.getWriter(),"BYSocket");
    }

    @Test
    public void testBookComponent() {
        Assert.assertEquals(bookComponent.getName(),"[Spring Boot 2.x Core Action]");
        Assert.assertEquals(bookComponent.getWriter(),"BYSocket");
    }

}

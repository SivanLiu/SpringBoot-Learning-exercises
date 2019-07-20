package com.spring.webfluex;

import com.spring.webfluex.client.UserPojo;
import com.spring.webfluex.client.WebClientTest;
import com.spring.webfluex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.spring.webfluex", exclude = { WebClientTest.class, UserPojo.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })

@EnableReactiveMongoRepositories(basePackages = "com.spring.webfluex.repository")
public class WebfluexApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebfluexApplication.class, args);

    }
}

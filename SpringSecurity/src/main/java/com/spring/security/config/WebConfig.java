package com.spring.security.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    //增加映射关系

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login/page").setViewName("login");
        registry.addViewController("/logout/page").setViewName("logout_welcome");
        registry.addViewController("/logout").setViewName("logout");

    }
}

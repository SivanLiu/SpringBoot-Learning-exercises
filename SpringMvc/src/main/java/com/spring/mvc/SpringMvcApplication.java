package com.spring.mvc;

import com.spring.mvc.interceptor.MultiInterceptor1;
import com.spring.mvc.interceptor.MultiInterceptor2;
import com.spring.mvc.interceptor.MultiInterceptor3;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@SpringBootApplication(scanBasePackages = "com.spring.mvc")
@MapperScan(basePackages = "com.spring.mvc", annotationClass = Repository.class)
public class SpringMvcApplication implements WebMvcConfigurer {

    //国际化拦截器
    private LocaleChangeInterceptor localeChangeInterceptor = null;

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    //国际化解析器，注意，这个 Bean Name 要为 localeResolver
    @Bean(name = "localeResolver")
    public LocaleResolver initLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        //默认国际化区域
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }

    //创建国际化拦截器
    public LocaleChangeInterceptor localeChangeInterceptor(){
        if(localeChangeInterceptor!=null){
            return localeChangeInterceptor;
        }

        localeChangeInterceptor = new LocaleChangeInterceptor();
        //设置参数名
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //给处理器增加国际化拦截器
        registry.addInterceptor(localeChangeInterceptor());
        //注册拦截器到 Spring MVC 机制中
        InterceptorRegistration interceptorRegistration1 = registry.addInterceptor(new MultiInterceptor1());
        //指定拦截匹配模式
        interceptorRegistration1.addPathPatterns("/interceptor/*");

        InterceptorRegistration interceptorRegistration2 = registry.addInterceptor(new MultiInterceptor2());
        interceptorRegistration2.addPathPatterns("/interceptor/*");

        InterceptorRegistration interceptorRegistration3 = registry.addInterceptor(new MultiInterceptor3());
        interceptorRegistration3.addPathPatterns("/interceptor/*");
    }

}

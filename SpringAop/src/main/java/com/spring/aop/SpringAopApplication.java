package com.spring.aop;

import com.spring.aop.aspect.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.spring.aop.aspect")
public class SpringAopApplication {
    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
        //        HelloService helloService = new HelloServiceImpl();
        //        HelloService proxy = (HelloService)ProxyBean.getProxyBean(helloService, new MyInterceptor());
        //
        //        proxy.sayHello("ssssss");
        //        System.out.println("------------------------");
        //        proxy.sayHello(null);

    }

}

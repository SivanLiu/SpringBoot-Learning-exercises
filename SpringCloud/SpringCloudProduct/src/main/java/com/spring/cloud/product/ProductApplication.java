package com.spring.cloud.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.spring.cloud.product")
@EnableFeignClients(basePackages = "com.spring.cloud.product")
// 启动断路器
//@EnableCircuitBreaker
//自定义扫描包
@ComponentScan(basePackages = "com.springboot.chapter17.product")
//开启Spring Boot应用、服务发现和断路器功能
@SpringCloudApplication
public class ProductApplication {

    //初始化 RestTemplate
    @LoadBalanced
    @Bean(name = "restTemplate")
    public RestTemplate initRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}

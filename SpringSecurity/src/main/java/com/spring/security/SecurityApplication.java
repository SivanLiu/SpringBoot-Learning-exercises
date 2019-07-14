package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.spring.security")
public class SecurityApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编辑器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //使用内存存储
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userDetailsManagerConfigurer = auth
                .inMemoryAuthentication()
                //设置密码编码器
                .passwordEncoder(passwordEncoder);
        userDetailsManagerConfigurer.withUser("admin")
                //可通过passwordEncoder.encode("abc")得到加密后的密码
                .password("$2a$10$5OpFvQlTIbM9Bx2pfbKVzurdQXL9zndm1SrAjEkPyIuCcZ7CqR6je")
                .authorities("ROLE_USER", "ROLE_ADMIN");
        userDetailsManagerConfigurer.withUser("myUser")
                // 可通过passwordEncoder.encode("123456")得到加密后的密码
                .password("$2a$10$ezW1uns4ZV63FgCLiFHJqOI6oR6jaaPYn33jNrxnkHZ.ayAFmfzLS").
                authorities("ROLE_USER");
    }
}

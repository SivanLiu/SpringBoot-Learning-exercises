package com.spring.database;

import com.spring.database.dao.MybatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDatabaseApplication {
    @Autowired
    SqlSessionFactory sqlSessionFactory = null;

    @Bean
    public MapperFactoryBean<MybatisUserDao> initMybatisUserDao() {
        MapperFactoryBean<MybatisUserDao> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(MybatisUserDao.class);
        bean.setSqlSessionFactory(sqlSessionFactory);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDatabaseApplication.class, args);
    }

}

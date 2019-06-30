package com.spring.database;

import com.spring.database.plugin.MyPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Properties;

@SpringBootApplication
@MapperScan(
        //指定扫描包
        basePackages = "com.spring.database",
        //指定 SqlSessionFactory，如果 sqlSessionTemplate 被指定，则作废
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定 sqlSessionTemplate， 将忽略 sqlSessionFactory 的配置
        sqlSessionTemplateRef = "sqlSessionTemplate",
//        markerInterface = Class.class; //限定扫描接口，不常用
        annotationClass = Repository.class
)
public class SpringDatabaseApplication {
    @Autowired
    SqlSessionFactory sqlSessionFactory = null;

//    @Bean
//    public MapperFactoryBean<MybatisUserDao> initMybatisUserDao() {
//        MapperFactoryBean<MybatisUserDao> bean = new MapperFactoryBean<>();
//        bean.setMapperInterface(MybatisUserDao.class);
//        bean.setSqlSessionFactory(sqlSessionFactory);
//        return bean;
//    }

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.spring.database");
//        mapperScannerConfigurer.setAnnotationClass(Repository.class);
//        return mapperScannerConfigurer;
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDatabaseApplication.class, args);
    }

    @PostConstruct
    public void initMyBatis() {
        //插件实例
        Interceptor plugin = new MyPlugin();
        Properties properties = new Properties();
        properties.setProperty("key1", "value1");
        properties.setProperty("key2", "value2");
        properties.setProperty("key3", "value3");
        plugin.setProperties(properties);

        //在 sqlSessionFactory 中添加插件
        sqlSessionFactory.getConfiguration().addInterceptor(plugin);
    }

}

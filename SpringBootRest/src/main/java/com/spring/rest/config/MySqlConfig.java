package com.spring.rest.config;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.stereotype.Component;

//解决 jpa 自动创建表字符编码问题 默认为 ENGINE=MyISAM DEFAULT CHARSET=latin1
@Component
public class MySqlConfig extends MySQL5Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
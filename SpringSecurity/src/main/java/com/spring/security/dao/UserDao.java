package com.spring.security.dao;

import com.spring.security.pojo.DatabaseUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {
	
	public DatabaseUser getUser(String userName);
}

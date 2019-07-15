package com.spring.security.dao;
import com.spring.security.pojo.DatabaseRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleDao {
	
	public List<DatabaseRole> findRolesByUserName(String userName);
}

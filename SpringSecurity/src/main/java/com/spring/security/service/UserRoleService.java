package com.spring.security.service;

import com.spring.security.pojo.DatabaseRole;
import com.spring.security.pojo.DatabaseUser;

import java.util.List;


public interface UserRoleService {

	public DatabaseUser getUserByName(String userName);

	public List<DatabaseRole> findRolesByUserName(String userName);
}

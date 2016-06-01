package com.sample.login.service.impl;

import javax.inject.Inject;
import com.sample.common.model.User;
import com.sample.login.dao.UserDao;
import com.sample.login.service.UserService;


public class UserServiceImpl implements UserService {
	@Inject
	private UserDao userDao;
	
	public User getUser(String name) {	
    	return userDao.getUser(name);
    }

	@Override
	public String hello() {
		return "Hello World!";
	}
}

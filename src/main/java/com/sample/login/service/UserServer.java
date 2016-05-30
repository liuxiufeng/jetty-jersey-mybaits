package com.sample.login.service;

import com.sample.common.model.User;
import com.sample.login.dao.UserDao;

public class UserServer {
	private UserDao userDao = new UserDao();
	
	public User getUser(String name) {	
    	return userDao.getUser(name);
    }
}

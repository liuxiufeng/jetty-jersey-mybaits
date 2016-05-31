package com.sample.login.service;

import com.sample.common.model.User;

public interface UserService {
	
	User getUser(String name);
	
	String hello();
}

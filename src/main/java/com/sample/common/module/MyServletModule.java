package com.sample.common.module;

import com.google.inject.servlet.ServletModule;
import com.sample.login.dao.UserDao;
import com.sample.login.dao.impl.UserDaoImpl;
import com.sample.login.service.UserService;
import com.sample.login.service.impl.UserServiceImpl;

public class MyServletModule extends ServletModule {
	 @Override
     protected void configureServlets() {
         bind(UserService.class).to(UserServiceImpl.class);
         bind(UserDao.class).to(UserDaoImpl.class);
     }
}

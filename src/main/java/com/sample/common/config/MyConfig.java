package com.sample.common.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.sample.login.dao.UserDao;
import com.sample.login.dao.impl.UserDaoImpl;
import com.sample.login.service.UserService;
import com.sample.login.service.impl.UserServiceImpl;

@ApplicationPath("v1")
public class MyConfig extends ResourceConfig  {
    public MyConfig(){
    	packages("com.sample.login");
    	
    	register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UserServiceImpl.class).to(UserService.class);
                bind(UserDaoImpl.class).to(UserDao.class);
            }
        });
    }
}

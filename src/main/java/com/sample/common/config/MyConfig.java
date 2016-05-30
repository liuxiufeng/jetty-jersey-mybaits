package com.sample.common.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.sample.login.service.UserServer;

@ApplicationPath("/*")
public class MyConfig extends ResourceConfig {

    public MyConfig() {
        packages("com.sample.login.service");

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(UserServer.class);
            }
        });
    }
}

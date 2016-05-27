package com.sample.login.webapp;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.sample.common.model.User;
import com.sample.login.service.UserServer;

@Path("/token")
public class tokenWebapp {
	private UserServer userServer = new UserServer();
	
    @GET()
    public String validate(
    		@DefaultValue("") @QueryParam("name") String name,
    	    @DefaultValue("") @QueryParam("pwd") String pwd){
    	
    	User user = userServer.getUser(name);
    	if (user == null) {
    		return "The name is not correct!";
    	} else if (!user.getPwd().equals(pwd)) {
    		return "The password is not correct!";
    	} else {
    		return user.toString();
    	}	
    }
}

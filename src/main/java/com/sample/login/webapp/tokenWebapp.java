package com.sample.login.webapp;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.sample.common.model.User;
import com.sample.common.utils.MsgUtils;
import com.sample.login.service.UserServer;

@Path("/token")
public class tokenWebapp {
	private final String NAME_ERROR_CODE = "001001";
	private final String PWD_ERROR_CODE = "001002";
	private final String DATABASE_ERROR_CODE = "000001";
	
	private UserServer userServer = new UserServer();

	@GET()
	public String validate(
			@DefaultValue("") @QueryParam("name") String name,
			@DefaultValue("") @QueryParam("pwd") String pwd) {

		try {
			User user = userServer.getUser(name);
			if (user == null) {
				return MsgUtils.getMessage(NAME_ERROR_CODE);
			} else if (!user.getPwd().equals(pwd)) {
				return MsgUtils.getMessage(PWD_ERROR_CODE);
			} else {
				return "login success.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return MsgUtils.getMessage(DATABASE_ERROR_CODE);
		}

	}
}

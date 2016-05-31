package com.sample.login.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.google.inject.Inject;
import com.sample.common.model.User;
import com.sample.common.utils.MsgUtils;
import com.sample.login.service.UserService;

@Path("/token")
public class TokenResource {
	private final String NAME_ERROR_CODE = "001001";
	private final String PWD_ERROR_CODE = "001002";
	private final String DATABASE_ERROR_CODE = "000001";
	
	@Inject
	private UserService userService;

	@POST()
	public String validate(
			@DefaultValue("") @FormParam("name") String name,
			@DefaultValue("") @FormParam("pwd") String pwd) {

		try {
			User user = userService.getUser(name);
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
	
	@GET
	public String hello() {
		return userService.hello();
	}
}

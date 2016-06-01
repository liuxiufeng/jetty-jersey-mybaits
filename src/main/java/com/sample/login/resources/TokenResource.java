package com.sample.login.resources;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.alibaba.fastjson.JSON;
import com.sample.common.base.ResourceBase;
import com.sample.common.config.Config;
import com.sample.common.model.Result;
import com.sample.common.model.User;
import com.sample.common.utils.MsgUtils;
import com.sample.login.service.UserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Path("/token")
public class TokenResource extends ResourceBase {

	private final String NAME_ERROR_CODE = "001001";
	private final String PWD_ERROR_CODE = "001002";
	private final String DATABASE_ERROR_CODE = "000001";

	@Inject
	private UserService userService;

	@POST()
	public String validate(@DefaultValue("") @FormParam("name") String name,
			@DefaultValue("") @FormParam("pwd") String pwd) {
		Result rst = new Result();
		rst.setSuccess(false);
		try {
			User user = userService.getUser(name);
			if (user == null) {
				rst.setMsg(MsgUtils.getMessage(NAME_ERROR_CODE));
				return JSON.toJSONString(rst);
			} else if (!user.getPwd().equals(pwd)) {
				rst.setMsg(MsgUtils.getMessage(PWD_ERROR_CODE));
				return JSON.toJSONString(rst);
			} else {
				rst.setSuccess(true);
				rst.setAccess_token(this.createToken(user));

				return JSON.toJSONString(rst);
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

	private String createToken(User user) {
		JwtBuilder builder = Jwts.builder();
		builder.setExpiration(new Date(System.currentTimeMillis() + 360000)).setSubject(user.getName())
				.signWith(SignatureAlgorithm.HS512, Config.KEY);
		return builder.compact();
	}

}

package com.sample.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public class ResourceBase {
	@Context
	protected HttpServletRequest request;

}

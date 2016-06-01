package com.sample.login.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

public class TokenResourceTest {
    private static String TOKEN_URL = "http://localhost:8081";
	
	@Test
	public void testLogin() throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(TOKEN_URL).path("v1/token");
		Form form = new Form();
		form.param("name", "seven");
		form.param("pwd", "seven");
		
		String response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE) ,String.class);
		
		System.out.println(response);
		assertThat(response, is("login success."));
	}
	
	@Test
	public void testHello() throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(TOKEN_URL).path("v1/token");
		
		String response = webTarget.request().get(String.class);
		
		System.out.println(response);
		assertThat(response, is("Hello World!"));
	}
}

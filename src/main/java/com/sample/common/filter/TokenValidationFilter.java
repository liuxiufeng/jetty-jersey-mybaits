package com.sample.common.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.common.config.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class TokenValidationFilter implements Filter {
	private HashSet ignoreset = new HashSet();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String url = filterConfig.getInitParameter("ignoreset");
		if (url != null) {
			ignoreset.add(url);
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		if (!ignoreset.contains(url)) {
			String authHeader = req.getHeader("Authorization");
			if (authHeader == null || authHeader.startsWith("Bearer ")) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			String token = authHeader.substring(7, authHeader.length());
			try {
				Jws<Claims> enToekn = Jwts.parser().setSigningKey(Config.KEY).parseClaimsJws(token);
				Date exp = enToekn.getBody().getExpiration();
				if (exp.before(new Date())) {
					res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
			} catch (Exception e) {
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

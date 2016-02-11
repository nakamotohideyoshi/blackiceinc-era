package com.blackiceinc.era.services.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GCDLogoutSuccessHandler implements LogoutSuccessHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onLogoutSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		System.out.println(" ******************* LOGOUT **************");
		redirectStrategy.sendRedirect(request, response, "/login");
	}

}
package com.blackiceinc.era.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EraUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String DOMAIN = "domain";


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String domain = request.getParameter(DOMAIN);

        return super.attemptAuthentication(request, response);
    }

}

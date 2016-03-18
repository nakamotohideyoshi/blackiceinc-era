package com.blackiceinc.era.services.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class EraAuthDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, EraWebAuthDetails> {

    @Override
    public EraWebAuthDetails buildDetails(HttpServletRequest context) {
        return new EraWebAuthDetails(context);
    }


}

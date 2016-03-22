package com.blackiceinc.era.services.security.exception;

import org.springframework.security.core.AuthenticationException;

public class LdapAuthenticationException extends AuthenticationException {
    public LdapAuthenticationException(String msg) {
        super(msg);
    }
}

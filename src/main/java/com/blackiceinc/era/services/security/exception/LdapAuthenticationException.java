package com.blackiceinc.era.services.security.exception;

import org.springframework.security.core.AuthenticationException;

public class LdapAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 863819047251311623L;

    public LdapAuthenticationException(String msg) {
        super(msg);
    }
}

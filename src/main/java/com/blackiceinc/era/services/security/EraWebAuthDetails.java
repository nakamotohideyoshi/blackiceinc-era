package com.blackiceinc.era.services.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class EraWebAuthDetails extends WebAuthenticationDetails {
    public static final String DOMAIN = "domain";

    private String domain;

    public EraWebAuthDetails(HttpServletRequest request) {
        super(request);
        this.domain = request.getParameter(DOMAIN);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}

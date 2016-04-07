package com.blackiceinc.era.services.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class EraWebAuthDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 5830953199941965213L;

    public static final String PARAM_DOMAIN = "domain";

    private String domain;

    public EraWebAuthDetails(HttpServletRequest request) {
        super(request);
        this.domain = request.getParameter(PARAM_DOMAIN);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EraWebAuthDetails that = (EraWebAuthDetails) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(domain, that.domain)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(domain)
                .toHashCode();
    }
}

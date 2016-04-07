package com.blackiceinc.era.web.model;

import com.blackiceinc.era.services.security.model.LdapConfig;

public class LdapAuth {
    private String username;
    private String password;
    private LdapConfig ldapConfig;

    public LdapAuth() {
        this.username = "";
        this.password = "";
        this.ldapConfig = new LdapConfig();
    }

    public LdapAuth(String username, String password, LdapConfig ldapConfig) {
        this.username = username;
        this.password = password;
        this.ldapConfig = ldapConfig;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LdapConfig getLdapConfig() {
        return ldapConfig;
    }

    public void setLdapConfig(LdapConfig ldapConfig) {
        this.ldapConfig = ldapConfig;
    }
}

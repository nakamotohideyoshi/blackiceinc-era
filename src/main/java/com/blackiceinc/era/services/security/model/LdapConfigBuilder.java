package com.blackiceinc.era.services.security.model;

import java.util.List;

public class LdapConfigBuilder {
    private List<String> ldapServers;
    private Integer ldapServerPort;
    private String baseDn;
    private String bindDn;
    private String bindPassword;
    private String userFilter;
    private String groupFilter;

    public LdapConfigBuilder setLdapServers(List<String> ldapServers) {
        this.ldapServers = ldapServers;
        return this;
    }

    public LdapConfigBuilder setLdapServerPort(Integer ldapServerPort) {
        this.ldapServerPort = ldapServerPort;
        return this;
    }

    public LdapConfigBuilder setBaseDn(String baseDn) {
        this.baseDn = baseDn;
        return this;
    }

    public LdapConfigBuilder setBindDn(String bindDn) {
        this.bindDn = bindDn;
        return this;
    }

    public LdapConfigBuilder setBindPassword(String bindPassword) {
        this.bindPassword = bindPassword;
        return this;
    }

    public LdapConfigBuilder setUserFilter(String userFilter) {
        this.userFilter = userFilter;
        return this;
    }

    public LdapConfigBuilder setGroupFilter(String groupFilter) {
        this.groupFilter = groupFilter;
        return this;
    }

    public LdapConfig createLdapConfig() {
        return new LdapConfig(ldapServers, ldapServerPort, baseDn, bindDn, bindPassword, userFilter, groupFilter);
    }
}
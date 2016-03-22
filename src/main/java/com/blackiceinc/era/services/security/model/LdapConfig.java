package com.blackiceinc.era.services.security.model;

import java.util.List;

public class LdapConfig {

    private List<String> ldapServers;
    private Integer ldapServerPort;
    private String baseDn;
    private String bindDn;
    private String bindPassword;
    private String userFilter;
    private String groupFilter;

    public LdapConfig(List<String> ldapServers, Integer ldapServerPort, String baseDn, String bindDn, String bindPassword,
                      String userFilter, String groupFilter) {
        this.ldapServers = ldapServers;
        this.ldapServerPort = ldapServerPort;
        this.baseDn = baseDn;
        this.bindDn = bindDn;
        this.bindPassword = bindPassword;
        this.userFilter = userFilter;
        this.groupFilter = groupFilter;
    }

    public LdapConfig() {

    }

    public List<String> getLdapServers() {
        return ldapServers;
    }

    public void setLdapServers(List<String> ldapServers) {
        this.ldapServers = ldapServers;
    }

    public Integer getLdapServerPort() {
        return ldapServerPort;
    }

    public void setLdapServerPort(Integer ldapServerPort) {
        this.ldapServerPort = ldapServerPort;
    }

    public String getBaseDn() {
        return baseDn;
    }

    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }

    public String getBindDn() {
        return bindDn;
    }

    public void setBindDn(String bindDn) {
        this.bindDn = bindDn;
    }

    public String getBindPassword() {
        return bindPassword;
    }

    public void setBindPassword(String bindPassword) {
        this.bindPassword = bindPassword;
    }

    public String getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }

    public String getGroupFilter() {
        return groupFilter;
    }

    public void setGroupFilter(String groupFilter) {
        this.groupFilter = groupFilter;
    }
}

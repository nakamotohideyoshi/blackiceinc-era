package com.blackiceinc.era.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class LdapServerConfig {

    @Autowired
    private Environment env;

    @Bean(name = "vibEmbeddedLdapAuthProvider")
    public LdapAuthenticationProvider getVibEmbeddedLdapAuthProvider() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA)) {
            return getLdapAuthenticationProvider(Arrays.asList("ldap://127.0.0.1:33389"), "dc=springframework,dc=org",
                    new String[]{"uid={0},ou=people"}, "ou=groups");
        }
        return null;
    }

    @Bean(name = "vibOnsiteNorthLdapAuthProvider")
    public LdapAuthenticationProvider getVibOnsiteNorthLdapAuthProvider() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_ONSITE)) {
            String[] hosts = env.getProperty("ldap.north.vib.corp.host").split("/");
            String port = env.getProperty("ldap.north.vib.corp.port");
            String baseDn = env.getProperty("ldap.north.vib.corp.base-dn");
            String[] userDnPatterns = env.getProperty("ldap.north.vib.corp.user-dn-patterns").split(";");
            String groupSearchBase = env.getProperty("ldap.north.vib.corp.group-search-base");

            return getLdapAuthenticationProvider(generateHosts(hosts, port), baseDn, userDnPatterns, groupSearchBase);
        }

        return null;
    }

    @Bean(name = "vibOnsiteSouthLdapAuthProvider")
    public LdapAuthenticationProvider getVibOnsiteSouthLdapAuthProvider() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_ONSITE)) {
            String[] hosts = env.getProperty("ldap.south.vib.corp.host").split("/");
            String port = env.getProperty("ldap.south.vib.corp.port");
            String baseDn = env.getProperty("ldap.south.vib.corp.base-dn");
            String[] userDnPatterns = env.getProperty("ldap.south.vib.corp.user-dn-patterns").split(";");
            String groupSearchBase = env.getProperty("ldap.south.vib.corp.group-search-base");

            return getLdapAuthenticationProvider(generateHosts(hosts, port), baseDn, userDnPatterns, groupSearchBase);
        }

        return null;
    }

    private LdapAuthenticationProvider getLdapAuthenticationProvider(List<String> urls, String baseDn, String[] userDnPatterns, String groupSearchBase) {
        DefaultSpringSecurityContextSource defaultSpringSecurityContextSource = new DefaultSpringSecurityContextSource(urls, baseDn);
        defaultSpringSecurityContextSource.afterPropertiesSet();

        BindAuthenticator bindAuthenticator = new BindAuthenticator(defaultSpringSecurityContextSource);
        bindAuthenticator.setUserDnPatterns(userDnPatterns);

        DefaultLdapAuthoritiesPopulator defaultLdapAuthoritiesPopulator = new DefaultLdapAuthoritiesPopulator(defaultSpringSecurityContextSource, groupSearchBase);

        LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(bindAuthenticator, defaultLdapAuthoritiesPopulator);

        return ldapAuthenticationProvider;
    }

    private List<String> generateHosts(String[] hosts, String port) {
        List<String> urls = new ArrayList<>();

        for (String host : hosts) {
            urls.add(MessageFormat.format("ldap://{0}:{1}", host, port));
        }

        return urls;
    }

}

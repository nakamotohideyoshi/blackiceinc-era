package com.blackiceinc.era.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(LdapServerConfig.class);

    @Bean(name = "vibEmbeddedLdapAuthProvider")
    public LdapAuthenticationProvider getVibEmbeddedLdapAuthProvider() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA)) {
            log.info("getVibEmbeddedLdapAuthProvider()");
            return getLdapAuthenticationProvider(Arrays.asList("ldap://127.0.0.1:33389"), "dc=springframework,dc=org",
                    new String[]{"uid={0},ou=people"}, "ou=groups");
        }
        return null;
    }

    @Bean(name = "vibOnsiteNorthLdapAuthProvider")
    public LdapAuthenticationProvider getVibOnsiteNorthLdapAuthProvider() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_ONSITE)) {
            log.info("getVibOnsiteNorthLdapAuthProvider()");
            String[] hosts = env.getProperty("ldap.north.vib.corp.host").split("/");
            String port = env.getProperty("ldap.north.vib.corp.port");
            String baseDn = env.getProperty("ldap.north.vib.corp.base-dn");
            String[] userDnPatterns = env.getProperty("ldap.north.vib.corp.user-dn-patterns").split(";");
            String groupSearchBase = env.getProperty("ldap.north.vib.corp.group-search-base");

            List<String> urls = generateHosts(hosts, port);
            log.info("-------- Onsite North LDAP configuration ----------");
            printLdapParameters(port, baseDn, userDnPatterns, groupSearchBase, urls);
            log.info("---------------------------------------------------");
            return getLdapAuthenticationProvider(urls, baseDn, userDnPatterns, groupSearchBase);
        }

        return null;
    }

    @Bean(name = "vibOnsiteSouthLdapAuthProvider")
    public LdapAuthenticationProvider getVibOnsiteSouthLdapAuthProvider() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_ONSITE)) {
            log.info("getVibOnsiteSouthLdapAuthProvider()");
            String[] hosts = env.getProperty("ldap.south.vib.corp.host").split("/");
            String port = env.getProperty("ldap.south.vib.corp.port");
            String baseDn = env.getProperty("ldap.south.vib.corp.base-dn");
            String[] userDnPatterns = env.getProperty("ldap.south.vib.corp.user-dn-patterns").split(";");
            String groupSearchBase = env.getProperty("ldap.south.vib.corp.group-search-base");

            List<String> urls = generateHosts(hosts, port);
            log.info("-------- Onsite South LDAP configuration ----------");
            printLdapParameters(port, baseDn, userDnPatterns, groupSearchBase, urls);
            log.info("---------------------------------------------------");
            return getLdapAuthenticationProvider(urls, baseDn, userDnPatterns, groupSearchBase);
        }

        return null;
    }

    private void printLdapParameters(String port, String baseDn, String[] userDnPatterns, String groupSearchBase, List<String> urls) {
        log.info("hosts : {}", StringUtils.join(urls, ","));
        log.info("port : {}", port);
        log.info("baseDn : {}", baseDn);
        log.info("userDnPatterns : {}", StringUtils.join(userDnPatterns, ","));
        log.info("groupSearchBase : {}", groupSearchBase);
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

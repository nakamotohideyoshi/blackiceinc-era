package com.blackiceinc.era.config;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class LdapServerConfig {

    private final Logger log = LoggerFactory.getLogger(LdapServerConfig.class);
    @Autowired
    private Environment env;

    @Bean(name = "vibEmbeddedLdapAuthProvider")
    public LdapAuthenticationProvider getVibEmbeddedLdapAuthProvider() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA)) {
            log.info("getVibEmbeddedLdapAuthProvider()");
            return getLdapAuthenticationProvider(Collections.singletonList("ldap://127.0.0.1:33389"), "DC=north,DC=vib,DC=corp",
                    new String[]{"cn={0},ou=Account Services"}, "ou=Account Services");
        }
        return null;
    }

    private LdapAuthenticationProvider getLdapAuthenticationProvider(List<String> urls, String baseDn, String[] userDnPatterns, String groupSearchBase) {
        DefaultSpringSecurityContextSource defaultSpringSecurityContextSource = new DefaultSpringSecurityContextSource(urls, baseDn);
        defaultSpringSecurityContextSource.afterPropertiesSet();

        BindAuthenticator bindAuthenticator = new BindAuthenticator(defaultSpringSecurityContextSource);
        bindAuthenticator.setUserDnPatterns(userDnPatterns);

        DefaultLdapAuthoritiesPopulator defaultLdapAuthoritiesPopulator = new DefaultLdapAuthoritiesPopulator(defaultSpringSecurityContextSource, groupSearchBase);

        return new LdapAuthenticationProvider(bindAuthenticator, defaultLdapAuthoritiesPopulator);
    }

}

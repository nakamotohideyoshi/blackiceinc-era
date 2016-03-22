package com.blackiceinc.era.services.security;

import com.blackiceinc.era.config.Constants;
import com.blackiceinc.era.persistence.erau.repository.UserRepository;
import com.blackiceinc.era.services.security.exception.LdapAuthenticationException;
import com.blackiceinc.era.services.security.model.LdapConfig;
import com.blackiceinc.era.services.security.model.LdapConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class EraAuthenticationProvider implements AuthenticationProvider {

    public static final String NORTH_VIB_CORP = "north.vib.corp";
    public static final String SOUTH_VIB_CORP = "south.vib.corp";

    private final Logger log = LoggerFactory.getLogger(EraAuthenticationProvider.class);

    @Autowired
    @Qualifier("vibEmbeddedLdapAuthProvider")
    private LdapAuthenticationProvider vibEmbeddedLdapAuthProvider;

    @Autowired
    private LdapAuthenticateService ldapAuthenticateService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EraUserDetailsService eraUserDetailsService;

    @Autowired
    private Environment env;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();

        log.debug("Authenticating {}", name);

        org.springframework.security.core.userdetails.User byUsername = eraUserDetailsService.loadUserByUsername(name);
        try {
            Authentication authenticate;
            if (env.acceptsProfiles(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA)) {
                log.info("Authenticate to embedded ldap server username : {}", name);
                // authenticate to embedded ldap server
                authenticate = vibEmbeddedLdapAuthProvider.authenticate(authentication);
            } else if (env.acceptsProfiles(Constants.SPRING_PROFILE_ONSITE)) {
                // check which domain to use
                EraWebAuthDetails details = (EraWebAuthDetails) authentication.getDetails();
                String domain = details.getDomain();
                authenticate = authenticateToOnsiteDomain(domain, authentication);
            } else {
                throw new InternalAuthenticationServiceException("Missing application configuration");
            }
            if (authenticate.isAuthenticated()) {
                return new UsernamePasswordAuthenticationToken(authenticate.getPrincipal(), authenticate.getCredentials(),
                        byUsername.getAuthorities());
            } else {
                log.info("User : {} not authenticated.", name);
                return authenticate;
            }
        } catch (BadCredentialsException ex) {
            log.info("Bad credentials for username : {}", name);
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private Authentication authenticateToOnsiteDomain(String domain, Authentication authentication) throws AuthenticationException {
        log.info("authenticateToOnsiteDomain(domain, authentication) domain : {}, name : ", domain, authentication.getName());
        if (NORTH_VIB_CORP.equals(domain)) {
            log.info("vibOnsiteNorthLdapAuthProvider.authenticate(authentication);");
            boolean authenticate = ldapAuthenticateService.authenticate((String) authentication.getPrincipal(),
                    (String) authentication.getCredentials(),
                    getNorthVibCorpConfig());
            if (!authenticate) {
                throw new LdapAuthenticationException("LDAP Authentication failed");
            } else {
                return authentication;
            }
        } else if (SOUTH_VIB_CORP.equals(domain)) {
            log.info("vibOnsiteSouthLdapAuthProvider.authenticate(authentication);");

            boolean authenticate = ldapAuthenticateService.authenticate((String) authentication.getPrincipal(),
                    (String) authentication.getCredentials(),
                    getSouthVibCorpConfig());
            if (!authenticate) {
                throw new LdapAuthenticationException("LDAP Authentication failed");
            } else {
                return authentication;
            }
        } else {
            throw new InternalAuthenticationServiceException("Missing application configuration");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public LdapConfig getNorthVibCorpConfig() {
        return new LdapConfigBuilder()
                .setLdapServers(Arrays.asList(env.getProperty("ldap.north.vib.corp.host").split("/")))
                .setLdapServerPort(Integer.valueOf(env.getProperty("ldap.north.vib.corp.port")))
                .setBaseDn(env.getProperty("ldap.north.vib.corp.base-dn"))
                .setBindDn(env.getProperty("ldap.north.vib.corp.bind-dn"))
                .setBindPassword(env.getProperty("ldap.north.vib.corp.bind-password"))
                .setUserFilter(env.getProperty("ldap.north.vib.corp.user-filter"))
                .setGroupFilter(env.getProperty("ldap.north.vib.corp.group-filter"))
                .createLdapConfig();
    }

    public LdapConfig getSouthVibCorpConfig() {
        return new LdapConfigBuilder()
                .setLdapServers(Arrays.asList(env.getProperty("ldap.south.vib.corp.host").split("/")))
                .setLdapServerPort(Integer.valueOf(env.getProperty("ldap.south.vib.corp.port")))
                .setBaseDn(env.getProperty("ldap.south.vib.corp.base-dn"))
                .setBindDn(env.getProperty("ldap.south.vib.corp.bind-dn"))
                .setBindPassword(env.getProperty("ldap.south.vib.corp.bind-password"))
                .setUserFilter(env.getProperty("ldap.south.vib.corp.user-filter"))
                .setGroupFilter(env.getProperty("ldap.south.vib.corp.group-filter"))
                .createLdapConfig();
    }
}

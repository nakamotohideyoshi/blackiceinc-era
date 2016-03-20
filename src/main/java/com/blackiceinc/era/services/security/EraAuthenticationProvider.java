package com.blackiceinc.era.services.security;

import com.blackiceinc.era.config.Constants;
import com.blackiceinc.era.persistence.erau.model.Role;
import com.blackiceinc.era.persistence.erau.model.User;
import com.blackiceinc.era.persistence.erau.repository.UserRepository;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EraAuthenticationProvider implements AuthenticationProvider {

    public static final String NORTH_VIB_CORP = "north.vib.corp";
    public static final String SOUTH_VIB_CORP = "south.vib.corp";

    private final Logger log = LoggerFactory.getLogger(EraAuthenticationProvider.class);

    @Autowired
    @Qualifier("vibEmbeddedLdapAuthProvider")
    private LdapAuthenticationProvider vibEmbeddedLdapAuthProvider;

    @Autowired
    @Qualifier("vibOnsiteNorthLdapAuthProvider")
    private LdapAuthenticationProvider vibOnsiteNorthLdapAuthProvider;

    @Autowired
    @Qualifier("vibOnsiteSouthLdapAuthProvider")
    private LdapAuthenticationProvider vibOnsiteSouthLdapAuthProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment env;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();

        log.debug("Authenticating {}", name);

        User byUsername = userRepository.findByUsernameIgnoreCase(name);
        if (byUsername != null) {
            Authentication authenticate;
            if (env.acceptsProfiles(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA)) {
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
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

                Set<Role> roles = byUsername.getRoles();
                for (Role role : roles) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
                }
                return new UsernamePasswordAuthenticationToken(authenticate.getPrincipal(), authenticate.getCredentials(),
                        grantedAuthorities);
            } else {
                return authenticate;
            }
        } else {
            throw new BadCredentialsException("Username does not exist");
        }
    }

    private Authentication authenticateToOnsiteDomain(String domain, Authentication authentication) {
        if (NORTH_VIB_CORP.equals(domain)) {
            return vibOnsiteNorthLdapAuthProvider.authenticate(authentication);
        } else if (SOUTH_VIB_CORP.equals(domain)) {
            return vibOnsiteSouthLdapAuthProvider.authenticate(authentication);
        } else {
            throw new InternalAuthenticationServiceException("Missing application configuration");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
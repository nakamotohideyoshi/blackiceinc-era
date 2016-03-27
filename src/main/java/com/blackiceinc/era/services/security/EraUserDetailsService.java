package com.blackiceinc.era.services.security;

import com.blackiceinc.era.persistence.erau.model.Role;
import com.blackiceinc.era.persistence.erau.model.User;
import com.blackiceinc.era.persistence.erau.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EraUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(EraUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername username : {}", username);

        User byUsername = userRepository.findByUsernameIgnoreCase(username);

        if (byUsername != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            Set<Role> roles = byUsername.getRoles();
            for (Role role : roles) {
                log.info("Role : ( {} - {} ) added to user : {}.", role.getName(), role.getDisplayName(), username);
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(username, "LDAP", grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("Username does not exist");
        }
    }
}

package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.Role;
import com.blackiceinc.era.persistence.erau.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("username_test");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setModified(new Timestamp(System.currentTimeMillis()));
        user.setEnabled(Boolean.TRUE);

        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(Role.ADMINISTRATOR_GROUP);
        roles.add(adminRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

}
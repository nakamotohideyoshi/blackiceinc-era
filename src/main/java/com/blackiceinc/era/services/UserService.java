package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.Role;
import com.blackiceinc.era.persistence.erau.model.User;
import com.blackiceinc.era.persistence.erau.repository.RoleRepository;
import com.blackiceinc.era.persistence.erau.repository.UserRepository;
import com.blackiceinc.era.web.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    public static final int PAGE_0 = 0;
    public static final int PAGE_SIZE_25 = 25;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Page<User> findUsersByParams(Integer page,
                                        Integer length) {
        int pageNumber = (page != null) ? page : PAGE_0;
        int pageSize = (length != null) ? length : PAGE_SIZE_25;

        return userRepository.findAll(new PageRequest(pageNumber, pageSize));
    }

    public User save(UserDTO userDTO) {
        return userRepository.save(getUserFromUserDTO(userDTO));
    }

    public long countUsers() {
        return userRepository.count();
    }

    public void delete(long id) {
        userRepository.delete(id);
    }

    private User getUserFromUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());

        String role = userDTO.getRole();
        Role roleEntity = roleRepository.findOne(role);
        Set<Role> roles = new HashSet<>();
        roles.add(roleEntity);
        user.setRoles(roles);

        long currentTimeMillis = System.currentTimeMillis();
        user.setModified(new Timestamp(currentTimeMillis));
        user.setCreated(new Timestamp(currentTimeMillis));
        user.setEnabled(Boolean.TRUE);
        return user;
    }

    private UserDTO getUserDtoFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}

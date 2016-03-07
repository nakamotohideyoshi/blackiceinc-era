package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.persistence.erau.model.Role;
import com.blackiceinc.era.persistence.erau.model.User;
import com.blackiceinc.era.persistence.erau.repository.RoleRepository;
import com.blackiceinc.era.services.UserService;
import com.blackiceinc.era.web.rest.dto.UserDTO;
import com.blackiceinc.era.web.rest.model.CRUDResponseObj;
import com.blackiceinc.era.web.rest.model.DeleteResponse;
import com.blackiceinc.era.web.rest.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(RunCalculatorResource.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<User> getAll(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "length", required = false) Integer length) throws URISyntaxException {

        Page<User> users = userService.findUsersByParams(page, length);

        return users;
    }

    @RequestMapping(value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        Response res = new Response();
        if (userDTO.getId() != null) {
            res.setMessage("A new User cannot already have an ID");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        try {
            UserDTO savedEntity = userService.save(userDTO);
            res.setContent(savedEntity);
            res.setTotalElements(userService.countUsers());
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            res.setMessage("Cannot Add Duplicate Entries! A Record With These Values Already Exists.");
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/user",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteData(@RequestParam String idListStr) throws URISyntaxException {
        DeleteResponse res = new DeleteResponse();
        String[] idList = idListStr.split("\\|");
        HttpStatus returnStatus = HttpStatus.OK;

        for (String id : idList) {
            try {
                userService.delete(Long.parseLong(id));
                res.addRecordResponse(new CRUDResponseObj(id, true));
            } catch (NumberFormatException ex) {
                res.addRecordResponse(new CRUDResponseObj(id, false, "Invalid number formatting"));
                returnStatus = HttpStatus.NOT_FOUND;
            } catch (EmptyResultDataAccessException ex) {
                res.addRecordResponse(new CRUDResponseObj(id, false, "Data with id=" + id + " does not exist."));
                returnStatus = HttpStatus.NOT_FOUND;
            } catch (DataIntegrityViolationException ex) {
                res.addRecordResponse(new CRUDResponseObj(id, false, "Cannot Be Deleted Due To Foreign Key Constraint."));
                returnStatus = HttpStatus.CONFLICT;
            }
        }

        res.setTotalElements(userService.countUsers());
        return new ResponseEntity<Response>(res, returnStatus);
    }

    @RequestMapping(value = "/user/roles",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getUserRoles() {
        return roleRepository.findAll();
    }


}

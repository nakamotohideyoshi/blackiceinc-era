package com.blackiceinc.era.web.rest;


import com.blackiceinc.era.persistence.erau.model.Bookmark;
import com.blackiceinc.era.persistence.erau.model.User;
import com.blackiceinc.era.persistence.erau.repository.BookmarkRepository;
import com.blackiceinc.era.persistence.erau.repository.UserRepository;
import com.blackiceinc.era.services.security.SecurityUtils;
import com.blackiceinc.era.web.rest.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookmarkResource {
    private final Logger log = LoggerFactory.getLogger(BookmarkResource.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @RequestMapping(value = "/bookmark",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bookmark>> getAll() {

        User user = getAuthenticatedUser();

        List<Bookmark> bookmarkList = bookmarkRepository.findAllByUserId(user.getId());

        return new ResponseEntity<>(bookmarkList, HttpStatus.OK);
    }

    @RequestMapping(value = "/bookmark",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestBody Bookmark bookmark) throws URISyntaxException {
        Response res = new Response();

        try {
            User user = getAuthenticatedUser();
            bookmark.setUserId(user.getId());
            bookmark.setType("CREDIT_RISK");
            Bookmark result = bookmarkRepository.save(bookmark);
            res.setContent(result);

            return ResponseEntity.created(new URI("/api/bookmark/" + result.getId())).body(res);
        } catch (DataIntegrityViolationException ex) {
            res.setMessage("A Bookmark with same name already exists.");
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/bookmark",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestBody Bookmark bookmark) throws URISyntaxException {
        if (bookmark.getId() == null) {
            return create(bookmark);
        }

        Response res = new Response();
        try {

            User user = getAuthenticatedUser();
            bookmark.setUserId(user.getId());
            Bookmark result = bookmarkRepository.save(bookmark);
            res.setContent(result);

            return ResponseEntity.ok().body(res);
        } catch (DataIntegrityViolationException ex) {
            res.setMessage("A Bookmark with same name already exists.");
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/bookmark/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookmarkRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    private User getAuthenticatedUser() {
        String username = SecurityUtils.getCurrentLogin();
        return userRepository.findByUsernameIgnoreCase(username);
    }
}

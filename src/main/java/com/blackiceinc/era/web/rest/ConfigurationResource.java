package com.blackiceinc.era.web.rest;


import com.blackiceinc.era.web.rest.model.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(ConfigurationResource.class);

    @RequestMapping(value = "/configuration",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Configuration>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "length", required = false) Integer length) {
        log.debug("REST request to get all Configurations");

        List<Configuration> result = new ArrayList<Configuration>();
        result.add(new Configuration("VIB_ERA_Configuration v1.11.xlsx", "NULL"));
        result.add(new Configuration("VIB_ERA_Configuration v1.12.xlsx", "NULL"));
        result.add(new Configuration("VIB_ERA_Configuration v1.13.xlsx", "NULL"));
        result.add(new Configuration("VIB_ERA_Configuration v2.00.xlsx", "NULL"));
        result.add(new Configuration("VIB_ERA_Configuration v2.01.xlsx", "NULL"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Configuration> create(@RequestBody Configuration configuration) {
        log.debug("REST request to save Configuration");

        // save into repository
        Configuration savedConfig = configuration;

        return ResponseEntity.ok(savedConfig);
    }

    @RequestMapping(value = "/configuration/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Configuration : {}", id);

        // delete configuration from repository
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/configuration/{id}/import",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> importConfig(@PathVariable Long id) {
        log.debug("REST request to import Configuration : {}", id);

        // import configuration and set status to 'CURRENT'

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/configuration/export",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> exportConfig() {
        log.debug("REST request to export current Configuration");

        // export current Configuration

        return ResponseEntity.ok().build();
    }

}


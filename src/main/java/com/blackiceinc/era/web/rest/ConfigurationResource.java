package com.blackiceinc.era.web.rest;


import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import com.blackiceinc.era.persistence.erau.repository.ConfigFileRepository;
import com.blackiceinc.era.services.ConfigFileService;
import com.blackiceinc.era.services.ConfigurationExportImportService;
import com.blackiceinc.era.services.ImportExportMessageProvider;
import com.blackiceinc.era.web.rest.model.CRUDResponseObj;
import com.blackiceinc.era.web.rest.model.DeleteResponse;
import com.blackiceinc.era.web.rest.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(ConfigurationResource.class);

    @Autowired
    private ConfigFileService configFileService;

    @Autowired
    private ConfigurationExportImportService configurationExportImportService;

    @Autowired
    private ConfigFileRepository configFileRepository;

    @Autowired
    private ImportExportMessageProvider importExportMessageProvider;

    @RequestMapping(value = "/configuration",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ConfigFile>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                   @RequestParam(value = "length", required = false) Integer length,
                                                   @RequestParam(value = "name", required = false) String name) {
        log.debug("REST request to get all Configurations");

        Page<ConfigFile> configFiles = configFileService.findConfigFilesByParams(page, length, name);

        importExportMessageProvider.addMessage("CONFIG");

        return new ResponseEntity<>(configFiles, HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestParam(value = "file", required = false) MultipartFile file) throws URISyntaxException {
        log.debug("REST request to save ConfigFileDTO");

        Response res = new Response();

        ConfigFile savedEntity;

        savedEntity = configFileService.save(file);
        res.setContent(savedEntity);
        res.setTotalElements(configFileRepository.count());

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/configuration",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteResponse> delete(@RequestParam String idListStr) {
        log.debug("REST request to delete Configurations : {}", idListStr);

        DeleteResponse res = new DeleteResponse();
        String[] idList = idListStr.split("\\|");
        HttpStatus returnStatus = HttpStatus.OK;

        for (String id : idList) {
            configFileService.delete(Long.parseLong(id));
            res.addRecordResponse(new CRUDResponseObj(id, true));
        }

        res.setTotalElements(configFileRepository.count());
        return new ResponseEntity<>(res, returnStatus);
    }

    @RequestMapping(value = "/configuration/{id}/import",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> importConfig(@PathVariable Long id) {
        log.info("REST request to import ConfigFile.id : {}", id);
        long start = System.currentTimeMillis();
        Response res = new Response();
        try {
            ConfigFile configFile = configFileRepository.findOne(id);
            res.setContent(configurationExportImportService.importConfigurationFromExcelIntoDb(configFile));
            log.info("Final import of configFile.id : {} finished in {} ms", id, System.currentTimeMillis() - start);
        } catch (Exception ex) {
            log.error("Error making an import", ex);
            res.setMessage("Error importing configuration!");
            return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/{id}/export",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> exportConfig(@PathVariable Long id) {
        log.debug("REST request to import ConfigFile : {}", id);
        Response res = new Response();
        try {
            // export current ConfigFileDTO
            ConfigFile configFile = configFileRepository.findOne(id);
            configurationExportImportService.exportConfigurationFromDbIntoExcel(configFile);
        } catch (Exception ex) {
            log.error("Error making an export", ex);
            res.setMessage("Error importing configuration!");
            return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/download",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> downloadFile(@Param(value = "id") Long id) throws FileNotFoundException {
        ConfigFile configFile = configFileRepository.findOne(id);

        FileInputStream fileInputStream = new FileInputStream(new File(configFile.getFilePath()));

        return ResponseEntity
                .ok()
                .contentType(
                        MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(new InputStreamResource(fileInputStream));
    }

}


package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.services.dataextraction.DataExtractService;
import com.blackiceinc.era.services.exception.DataExtractionEntityException;
import com.blackiceinc.era.services.exception.DataExtractionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api")
public class DataExtractResource {

    private final Logger log = LoggerFactory.getLogger(DataExtractResource.class);

    @Autowired
    private DataExtractService dataExtractService;

    @RequestMapping(value = "/data-extraction/extract", method = RequestMethod.GET)
    public ResponseEntity<Void> extract(@RequestParam String entity, HttpServletResponse response) throws IOException {
        log.debug("Requesting extraction for entity : {}", entity);

        try {
            Path extractedExcel = dataExtractService.writeToCsvFile(entity);

            response.setHeader("Content-Disposition", "attachment;filename=\"" + entity + ".csv\"");
            response.setContentType("application/csv");

            Cookie c = new Cookie("fileDownload", "true");
            c.setPath("/");
            response.addCookie(c);

            ServletOutputStream outStream = response.getOutputStream();

            outStream.write(Files.readAllBytes(extractedExcel));
            outStream.close();
            response.flushBuffer();

            Files.delete(extractedExcel);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataExtractionException ex) {
            log.error("Error in DataExtractionService", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DataExtractionEntityException ex) {
            log.error("Entity exception", ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

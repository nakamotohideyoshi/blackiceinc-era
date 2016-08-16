package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.services.exception.StressTestingException;
import com.blackiceinc.era.services.stresstesting.StressTestingService;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class StressTestingResource {

    private final Logger log = LoggerFactory.getLogger(StressTestingResource.class);

    @Autowired
    private StressTestingService stressTestingService;

    @RequestMapping(value = "/stress-testing", method = RequestMethod.GET)
    public ResponseEntity<Void> download(HttpServletResponse response) throws IOException {
        log.debug("Requesting extraction for stress-testing document");

        try {
            String name = "VIB Stress Testing";
            response.setHeader("Content-Disposition", "attachment;filename=\"" + name + ".xlsm\"");
            response.setContentType("application/vnd.ms-excel.sheet.macroEnabled.12");

            Cookie c = new Cookie("fileDownload", "true");
            c.setPath("/");
            response.addCookie(c);

            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(IOUtils.toByteArray(new FileInputStream(stressTestingService.getStressTestingExcelPath().toFile())));
            outStream.close();
            response.flushBuffer();
        } catch (StressTestingException e) {
            log.error("Error while preparing stress-testing data", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

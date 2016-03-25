package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.services.MeasurementSensitivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MsResource {

    @Autowired
    private MeasurementSensitivityService measurementSensitivityService;

}

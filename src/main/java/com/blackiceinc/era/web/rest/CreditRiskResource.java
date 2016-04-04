package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import com.blackiceinc.era.services.CreditRiskService;
import com.blackiceinc.era.services.MeasurementSensitivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CreditRiskResource {

    @Autowired
    private MeasurementSensitivityService msService;

    @Autowired
    private CreditRiskService creditRiskService;

    @RequestMapping(value = "/credit-risk/filter-options", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List> getFilterOptions(HttpServletRequest request) throws URISyntaxException, SQLException {
        HashMap<String, List> stringListHashMap = new HashMap<>();

        stringListHashMap = creditRiskService.getFilterOptions();

        return stringListHashMap;
    }

    @RequestMapping(value = "/credit-risk",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<MeasurementSensitivity>> getAll(
            @RequestParam Map<String, String> allRequestParams) throws URISyntaxException {

//        PageImpl<MeasurementSensitivity> page1 = new PageImpl<>(getMSDummy());
        Page<MeasurementSensitivity> msPage = msService.findMsByParams(allRequestParams);

        return new ResponseEntity<>(msPage, HttpStatus.OK);
//        return new ResponseEntity<>(page1, HttpStatus.OK);
    }


}

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

    private List<MeasurementSensitivity> getMSDummy() {
        List<MeasurementSensitivity> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            MeasurementSensitivity ms = new MeasurementSensitivity();
            ms.setMeasurementNbr(String.valueOf(i));
            ms.setSnapshotDate(new Date(System.currentTimeMillis()));
            ms.setAssetClassFinal("Asset Class Final " + String.valueOf(i));
            ms.setExposureTypeCode("Exposure Type Code " + String.valueOf(i));
            ms.setEraEntityType("Era Entity Type " + String.valueOf(i));
            ms.setEraProductTypeFinal("Era Product Type Final " + String.valueOf(i));
            ms.setRiskWeightFinal(0.5 + i);
            ms.setEadBeforeCcfLcyAmt(10.5 + i);
            ms.setRwaAmt(33.33 + i);
            ms.setRegCap(50.0 + i);

            ms.setLoadJobNbr(2L + i);
            ms.setScenarioId("1" + String.valueOf(i));
            list.add(ms);
        }

        return list;
    }
}

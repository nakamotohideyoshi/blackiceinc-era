package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import com.blackiceinc.era.services.CreditRiskService;
import com.blackiceinc.era.services.MeasurementSensitivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
    private MeasurementSensitivityService measurementSensitivityService;

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
    public ResponseEntity<PageImpl<MeasurementSensitivity>> getAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "length", required = false) Integer length,
            @RequestParam(value = "snapshotDate", required = false) Date snapshotDate,
            @RequestParam(value = "loadJobNbr", required = false) BigDecimal loadJobNbr,
            @RequestParam(value = "scenarioId", required = false) String scenarioId,
            @RequestParam(value = "industry", required = false) String industry,
            @RequestParam(value = "profitCentre", required = false) String profitCentre,
            @RequestParam(value = "assetClass", required = false) String assetClass,
            @RequestParam(value = "exposureType", required = false) String exposureType,
            @RequestParam(value = "entityType", required = false) String entityType,
            @RequestParam(value = "productType", required = false) String productType,
            @RequestParam(value = "riskRatingFrom", required = false) String riskRatingFrom,
            @RequestParam(value = "riskRatingTo", required = false) String riskRatingTo) throws URISyntaxException {


        PageImpl<MeasurementSensitivity> page1 = new PageImpl<>(getMSDummy());
//        Page<MeasurementSensitivity> msPage = measurementSensitivityService
//                .findMsByParams(page, length);

//        return new ResponseEntity<>(msPage, HttpStatus.OK);
        return new ResponseEntity<>(page1, HttpStatus.OK);
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

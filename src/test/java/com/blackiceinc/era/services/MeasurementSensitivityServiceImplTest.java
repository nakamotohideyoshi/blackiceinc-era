package com.blackiceinc.era.services;

import com.blackiceinc.era.BlackiceincEraApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
public class MeasurementSensitivityServiceImplTest {

    private final Logger log = LoggerFactory.getLogger(MeasurementSensitivityServiceImplTest.class);

    @Autowired
    MeasurementSensitivityService msService;

    @Test
    public void testGetSums() throws Exception {

        Map<String, String> params = new HashMap<>();
//        params.put("snapshotDate", "2016-01-31");
//        params.put("loadJobNbr", "1");
//        params.put("scenarioId", "1");
//        params.put("industry", "0301");
        params.put("entityType", "SOV");
//        params.put("exposureType", "DRAWN");

        Map<String, Float> sums = msService.getSums(params);
        Set<String> strings = sums.keySet();
        for (String string : strings) {
            Float aFloat = sums.get(string);
            log.info(string + ": " + aFloat.toString());
        }
    }
}
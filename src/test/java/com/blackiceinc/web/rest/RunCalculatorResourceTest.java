package com.blackiceinc.web.rest;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import com.blackiceinc.era.persistence.erau.repository.RunCalculatorRepository;
import com.blackiceinc.era.services.RunCalculatorService;
import com.blackiceinc.era.web.rest.RunCalculatorResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasToString;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@IntegrationTest
public class RunCalculatorResourceTest {

    private MockMvc restRunCalculatorMockMvc;

    @Autowired
    private RunCalculatorRepository runCalculatorRepository;

    @Mock
    private RunCalculatorService runCalculatorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RunCalculatorResource runCalculatorResource = new RunCalculatorResource();
        ReflectionTestUtils.setField(runCalculatorResource, "runCalculatorRepository", runCalculatorRepository);
        ReflectionTestUtils.setField(runCalculatorResource, "runCalculatorService", runCalculatorService);

        this.restRunCalculatorMockMvc = MockMvcBuilders.standaloneSetup(runCalculatorResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    @Transactional
    public void getAllRunCalculator() throws Exception {
        ArrayList<RunCalculator> runCalculators = new ArrayList<>();

        RunCalculator runCalculator = new RunCalculator();
        runCalculator.setId(1l);
        runCalculator.setSnapshotDate(new Date(Calendar.getInstance().getTimeInMillis()));
        runCalculator.setLoadJobNbr(1l);
        runCalculator.setScenarioId("1");
        runCalculators.add(runCalculator);


        Page<RunCalculator> page = new PageImpl<RunCalculator>(runCalculators);
        when(runCalculatorService.findRunCalculationByParams(anyInt(), anyInt(), any(Date.class), any(BigDecimal.class),
                anyString())).thenReturn(page);

        // Get runCalculation
        restRunCalculatorMockMvc.perform(get("/api/runCalculator"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[*].id").value(hasItem(runCalculator.getId().intValue())))
                .andExpect(jsonPath("$.content.[*].snapshotDate").value(hasItem(runCalculator.getSnapshotDate().toString())))
                .andExpect(jsonPath("$.content.[*].loadJobNbr").value(hasItem(runCalculator.getLoadJobNbr().intValue())))
                .andExpect(jsonPath("$.content.[*].scenarioId").value(hasItem(runCalculator.getScenarioId())));
    }

}

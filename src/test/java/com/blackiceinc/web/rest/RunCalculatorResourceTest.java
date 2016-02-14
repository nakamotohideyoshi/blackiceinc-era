package com.blackiceinc.web.rest;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import com.blackiceinc.era.persistence.erau.repository.RunCalculatorRepository;
import com.blackiceinc.era.services.RunCalculatorService;
import com.blackiceinc.era.web.rest.RunCalculatorResource;
import org.junit.Before;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasValue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@IntegrationTest
public class RunCalculatorResourceTest {

    public static final Date DEFAULT_SNAPSHOT_DATE = new Date(Calendar.getInstance().getTimeInMillis());
    public static final long DEFAULT_LOAD_JOB_NBR = 1l;
    public static final String DEFAULT_SCENARIO_ID = "1";
    private MockMvc restRunCalculatorMockMvc;

    @Mock
    private RunCalculatorRepository runCalculatorRepository;

    @Mock
    private RunCalculatorService runCalculatorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private RunCalculator runCalculator;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RunCalculatorResource runCalculatorResource = new RunCalculatorResource();
        ReflectionTestUtils.setField(runCalculatorResource, "runCalculatorRepository", runCalculatorRepository);
        ReflectionTestUtils.setField(runCalculatorResource, "runCalculatorService", runCalculatorService);

        this.restRunCalculatorMockMvc = MockMvcBuilders.standaloneSetup(runCalculatorResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        runCalculator = new RunCalculator();
        runCalculator.setSnapshotDate(DEFAULT_SNAPSHOT_DATE);
        runCalculator.setLoadJobNbr(DEFAULT_LOAD_JOB_NBR);
        runCalculator.setScenarioId(DEFAULT_SCENARIO_ID);
    }

    @Test
    @Transactional
    public void getAllRunCalculator() throws Exception {
        ArrayList<RunCalculator> runCalculators = new ArrayList<>();

        runCalculator.setId(1l);
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

    @Test
    @Transactional
    public void createRunCalculator() throws Exception {
        RunCalculator savedRunCalculator = createRunCalcObj(2l, DEFAULT_SNAPSHOT_DATE, DEFAULT_LOAD_JOB_NBR, DEFAULT_SCENARIO_ID);
        when(runCalculatorRepository.save(runCalculator)).thenReturn(savedRunCalculator);

        // Create the WorkEntry
        MockHttpServletRequestBuilder response = post("/api/runCalculator");
        ResultActions perform = restRunCalculatorMockMvc.perform(response
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(runCalculator)));
        perform
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void deleteRunCalculator() throws Exception {
        String idListStr = "1|2";

        // Create the WorkEntry
        ResultActions perform = restRunCalculatorMockMvc.perform(delete("/api/runCalculator").param("idListStr", idListStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.deleteSuccessResultMap.[*]").value(hasItem(true)));
    }

    @Test
    @Transactional
    public void checkIfRunCalculatorExists() throws Exception {
        Page<RunCalculator> page = new PageImpl<RunCalculator>(new ArrayList<RunCalculator>());
        when(runCalculatorService.findRunCalculationByParams(anyInt(), anyInt(), any(Date.class), any(BigDecimal.class),
                anyString())).thenReturn(page);

        restRunCalculatorMockMvc.perform(get("/api/runCalculator/check")
                .param("hashKey", "object:104")
                .param("snapshotDate", "2016-01-01")
                .param("loadJobNbr", "1")
                .param("scenarioId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.exists").value(eq(false)))
                .andExpect(jsonPath("$.hashKey").value(is("object:104")));
    }

    @Test
    @Transactional
    public void runCalculatorProcedure() throws Exception {
        // Create the WorkEntry
        MockHttpServletRequestBuilder response = post("/api/runCalculator/runCalculation");
        ResultActions perform = restRunCalculatorMockMvc.perform(response
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(runCalculator)));
        perform
                .andExpect(status().isOk());
    }

    private RunCalculator createRunCalcObj(Long id, Date snapshotDate, Long loadJobNbr, String scenarioId) {
        RunCalculator runCalculator = new RunCalculator();
        runCalculator.setId(id);
        runCalculator.setSnapshotDate(snapshotDate);
        runCalculator.setLoadJobNbr(loadJobNbr);
        runCalculator.setScenarioId(scenarioId);

        return runCalculator;
    }

}

package com.blackiceinc.web.rest;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.repository.RunCalculatorRepository;
import com.blackiceinc.era.web.rest.RunCalculatorResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@IntegrationTest
public class RunCalculatorResourceTest {

    private MockMvc restRunCalculatorMockMvc;

    @Autowired
    private RunCalculatorRepository runCalculatorRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RunCalculatorResource runCalculatorResource = new RunCalculatorResource();
        ReflectionTestUtils.setField(runCalculatorResource, "runCalculatorRepository", runCalculatorRepository);

        this.restRunCalculatorMockMvc = MockMvcBuilders.standaloneSetup(runCalculatorResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    @Transactional
    public void getAllRunCalculator() {

    }

}

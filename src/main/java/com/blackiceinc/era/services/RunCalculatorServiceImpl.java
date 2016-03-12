package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityDaoCustom;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityRepository;
import com.blackiceinc.era.persistence.erau.repository.RunCalculatorRepository;
import com.blackiceinc.era.persistence.erau.specifications.RunCalculatorSpecificationsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RunCalculatorServiceImpl implements RunCalculatorService {

    public static final int PAGE_0 = 0;
    public static final int PAGE_SIZE_25 = 25;
    private static final String SNAPSHOT_DATE = "snapshotDate";
    private static final String LOAD_JOB_NBR = "loadJobNbr";
    private static final String SCENARIO_ID = "scenarioId";
    private final Logger log = LoggerFactory.getLogger(RunCalculatorServiceImpl.class);

    @Autowired
    private Environment env;

    @Autowired
    private RunCalculatorRepository runCalculatorRepository;

    @Autowired
    private MeasurementSensitivityDaoCustom measurementSensitivityDaoCustom;

    @Override
    public Map<String, List> getFilterOptions() throws SQLException {
        Map<String, List> filterOptions = new HashMap<>();

        List<Date> snapshotDateList = new ArrayList<>();
        List<BigDecimal> loadJobNbrList = new ArrayList<>();
        List<String> scenarioIdList = new ArrayList<>();

        fillFilterFromRunCalculatorDB(snapshotDateList, loadJobNbrList, scenarioIdList);

        filterOptions.put(SNAPSHOT_DATE, snapshotDateList);
        filterOptions.put(LOAD_JOB_NBR, loadJobNbrList);
        filterOptions.put(SCENARIO_ID, scenarioIdList);

        return filterOptions;
    }

    @Override
    public Page<RunCalculator> findRunCalculationByParams(Integer page,
                                                          Integer length,
                                                          Date snapshotDate,
                                                          BigDecimal loadJobNbr,
                                                          String scenarioId) {
        Specification<RunCalculator> specRunCalculator = getRunCalculatorSpecification(snapshotDate, loadJobNbr, scenarioId);

        int pageNumber = (page != null) ? page : PAGE_0;
        int pageSize = (length != null) ? length : PAGE_SIZE_25;

        return runCalculatorRepository.findAll(specRunCalculator, new PageRequest(pageNumber, pageSize));
    }

    @Override
    public List<Date> getSnapshotDateOptions() throws SQLException {
        List<Date> snapshotDateOptions = new ArrayList<>();

        Connection conn = DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
        conn.setAutoCommit(false);

        Statement stmt = conn.createStatement();
        long start = System.currentTimeMillis();
        log.info("Starting taking distinct snapshotData MEASUREMENT_SENSITIVITY data");
        ResultSet resultSet = stmt.executeQuery("select DISTINCT SNAPSHOT_DATE from MEASUREMENT_SENSITIVITY");
        log.info("MEASUREMENT_SENSITIVITY data took {} ms", System.currentTimeMillis() - start);
        while (resultSet.next()) {
            Date snapshotDate = resultSet.getDate("SNAPSHOT_DATE");
            if (!snapshotDateOptions.contains(snapshotDate)) {
                snapshotDateOptions.add(snapshotDate);
            }
        }

        return snapshotDateOptions;
    }

    @Transactional
    public void delete(Long id){
        RunCalculator runCalculator = runCalculatorRepository.findOne(id);

        measurementSensitivityDaoCustom.delete(runCalculator.getSnapshotDate(),
                runCalculator.getLoadJobNbr(), runCalculator.getScenarioId());

        runCalculatorRepository.delete(id);

    }

    private Specification<RunCalculator> getRunCalculatorSpecification(Date snapshotDate,
                                                                       BigDecimal loadJobNbr,
                                                                       String scenarioId) {
        RunCalculatorSpecificationsBuilder builder = new RunCalculatorSpecificationsBuilder();
        if (snapshotDate != null) {
            builder.with("snapshotDate", ":", snapshotDate, "", "");
        }

        if (loadJobNbr != null) {
            builder.with("loadJobNbr", ":", loadJobNbr, "", "");
        }

        if (scenarioId != null) {
            builder.with("scenarioId", ":", scenarioId, "", "");
        }

        return builder.build();
    }

    private void fillFilterFromRunCalculatorDB(List<Date> snapshotDateList, List<BigDecimal> loadJobNbrList, List<String> scenarioIdList) throws SQLException {
        Connection conn = DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
        conn.setAutoCommit(false);

        Statement stmt = conn.createStatement();

        long start = System.currentTimeMillis();
        log.info("Starting taking distinct data from RUN_CALCULATOR");
        ResultSet resultSet = stmt.executeQuery("select DISTINCT SNAPSHOT_DATE, LOAD_JOB_NBR, SCENARIO_ID from RUN_CALCULATOR");
        log.info("distinct RUN_CALCULATOR data took {} ms", System.currentTimeMillis() - start);
        while (resultSet.next()) {
            Date snapshotDate = resultSet.getDate("SNAPSHOT_DATE");
            BigDecimal loadJobNbr = resultSet.getBigDecimal("LOAD_JOB_NBR");
            String scenarioId = resultSet.getString("SCENARIO_ID");

            if (!snapshotDateList.contains(snapshotDate)) {
                snapshotDateList.add(snapshotDate);
            }

            if (!loadJobNbrList.contains(loadJobNbr)) {
                loadJobNbrList.add(loadJobNbr);
            }

            if (!scenarioIdList.contains(scenarioId)) {
                scenarioIdList.add(scenarioId);
            }
        }
    }

}

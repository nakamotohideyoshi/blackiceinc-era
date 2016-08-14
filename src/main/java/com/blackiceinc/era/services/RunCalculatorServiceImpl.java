package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityDaoCustom;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;

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

        long start = System.currentTimeMillis();

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            log.info("Starting taking distinct snapshotData INSTRUMENT table");
            resultSet = stmt.executeQuery("select DISTINCT SNAPSHOT_DATE from INSTRUMENT");
            log.info("extracting data took {} ms", System.currentTimeMillis() - start);

            while (resultSet.next()) {
                Date snapshotDate = resultSet.getDate("SNAPSHOT_DATE");
                if (!snapshotDateOptions.contains(snapshotDate)) {
                    snapshotDateOptions.add(snapshotDate);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }


        return snapshotDateOptions;
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
        return conn;
    }

    @Transactional
    public void delete(Long id) {
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
            builder.with(SNAPSHOT_DATE, ":", snapshotDate, "", "");
        }

        if (loadJobNbr != null) {
            builder.with(LOAD_JOB_NBR, ":", loadJobNbr, "", "");
        }

        if (scenarioId != null) {
            builder.with(SCENARIO_ID, ":", scenarioId, "", "");
        }

        return builder.build();
    }

    private void fillFilterFromRunCalculatorDB(List<Date> snapshotDateList, List<BigDecimal> loadJobNbrList, List<String> scenarioIdList) throws SQLException {
        long start = System.currentTimeMillis();

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            log.info("Starting taking distinct data from ERA_RUN_CALCULATOR");
            resultSet = stmt.executeQuery("select DISTINCT SNAPSHOT_DATE, LOAD_JOB_NBR, SCENARIO_ID from ERA_RUN_CALCULATOR");
            log.info("distinct ERA_RUN_CALCULATOR data took {} ms", System.currentTimeMillis() - start);

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
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void executeRunCalcProcedure(RunCalculator runCalculator) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("Running procedure CALC_RUN for runCalculator : {}", runCalculator.toString());

        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = getConnection();
            callableStatement = conn.prepareCall("{call CALC_RUN (?, ?, ?)}");
            callableStatement.setString(1, runCalculator.getScenarioId());
            callableStatement.setLong(2, runCalculator.getLoadJobNbr());
            callableStatement.setDate(3, runCalculator.getSnapshotDate());
            callableStatement.executeUpdate();

            log.info("Procedure CALC_RUN for runCalculator : {} finished in {} ms", runCalculator.toString(), System.currentTimeMillis() - start);
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    log.error("Error while trying to close statement", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error("Error while trying to close connection", e);
                }
            }
        }
    }

}

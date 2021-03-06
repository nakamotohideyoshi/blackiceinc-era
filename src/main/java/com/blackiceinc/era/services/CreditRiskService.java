package com.blackiceinc.era.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreditRiskService {

    private final Logger log = LoggerFactory.getLogger(CreditRiskService.class);

    @Autowired
    private Environment env;

    public Map<String, List> getFilterOptions() throws SQLException {
        HashMap<String, List> filterOptions = new HashMap<>();

        List<Date> snapshotDateList = new ArrayList<>();
        List<Integer> loadJobNbrList = new ArrayList<>();
        List<String> scenarioIdList = new ArrayList<>();

        List<String> industryList = new ArrayList<>();
        List<String> profitCentreList = new ArrayList<>();

        List<String> assetClassList = new ArrayList<>();
        List<String> exposureTypeList = new ArrayList<>();
        List<String> entityTypeList = new ArrayList<>();
        List<String> productTypeList = new ArrayList<>();

        long start = System.currentTimeMillis();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            log.info("Starting taking distinct data from MEASUREMENT_SENSITIVITY");
            resultSet = statement.executeQuery("SELECT DISTINCT " +
                    "  SNAPSHOT_DATE,\n" +
                    "  LOAD_JOB_NBR,\n" +
                    "  SCENARIO_ID,\n" +
                    "  ASSET_CLASS_FINAL,\n" +
                    "  EXPOSURE_TYPE_CODE,\n" +
                    "  ERA_ENTITY_TYPE,\n" +
                    "  ERA_PRODUCT_TYPE_FINAL,\n" +
                    "  ORG_UNIT\n" +
                    "FROM MEASUREMENT_SENSITIVITY ms");
            log.info("distinct MEASUREMENT_SENSITIVITY data took {} ms", System.currentTimeMillis() - start);

            while (resultSet.next()) {
                Date snapshotDate = resultSet.getDate("SNAPSHOT_DATE");
                Integer loadJobNbr = resultSet.getInt("LOAD_JOB_NBR");
                String scenarioId = resultSet.getString("SCENARIO_ID");

                String assetClass = resultSet.getString("ASSET_CLASS_FINAL");
                String exposureType = resultSet.getString("EXPOSURE_TYPE_CODE");
                String entityType = resultSet.getString("ERA_ENTITY_TYPE");
                String productType = resultSet.getString("ERA_PRODUCT_TYPE_FINAL");

                String profitCentre = resultSet.getString("ORG_UNIT");

                if (shouldAdd(snapshotDate, snapshotDateList)) {
                    snapshotDateList.add(snapshotDate);
                }

                if (shouldAdd(loadJobNbr, loadJobNbrList)) {
                    loadJobNbrList.add(loadJobNbr);
                }

                if (shouldAdd(scenarioId, scenarioIdList)) {
                    scenarioIdList.add(scenarioId);
                }

                if (shouldAdd(assetClass, assetClassList)) {
                    assetClassList.add(assetClass);
                }

                if (shouldAdd(exposureType, exposureTypeList)) {
                    exposureTypeList.add(exposureType);
                }

                if (shouldAdd(entityType, entityTypeList)) {
                    entityTypeList.add(entityType);
                }

                if (shouldAdd(productType, productTypeList)) {
                    productTypeList.add(productType);
                }

                if (shouldAdd(profitCentre, profitCentreList)){
                    profitCentreList.add(profitCentre);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        industryList.addAll(getIndustryCodes());

        filterOptions.put("snapshotDate", snapshotDateList);
        filterOptions.put("loadJobNbr", loadJobNbrList);
        filterOptions.put("scenarioId", scenarioIdList);
        filterOptions.put("industry", industryList);
        filterOptions.put("profitCentre", profitCentreList);
        filterOptions.put("assetClass", assetClassList);
        filterOptions.put("exposureType", exposureTypeList);
        filterOptions.put("entityType", entityTypeList);
        filterOptions.put("productType", productTypeList);

        return filterOptions;
    }

    private boolean shouldAdd(Date date, List<Date> list) {
        return date != null && !list.contains(date);
    }

    private boolean shouldAdd(String value, List<String> list) {
        return value != null && !list.contains(value);
    }

    private boolean shouldAdd(Integer value, List<Integer> list) {
        return value != null && !list.contains(value);
    }

    private List<String> getIndustryCodes() throws SQLException {
        List<String> industryList = new ArrayList<>();

        long start = System.currentTimeMillis();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            log.info("Starting taking distinct data from CUSTOMER");
            resultSet = statement.executeQuery("SELECT DISTINCT INDUSTRY_CODE FROM customer c");
            log.info("distinct CUSTOMER data took {} ms", System.currentTimeMillis() - start);

            while (resultSet.next()) {
                String industryCode = resultSet.getString("INDUSTRY_CODE");

                if (!industryList.contains(industryCode)) {
                    industryList.add(industryCode);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }


        return industryList;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
    }

}

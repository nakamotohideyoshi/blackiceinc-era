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

        ResultSet distinctMSResultSet = getDistinctMSResultSet();
        while (distinctMSResultSet.next()) {
            Date snapshotDate = distinctMSResultSet.getDate("SNAPSHOT_DATE");
            Integer loadJobNbr = distinctMSResultSet.getInt("LOAD_JOB_NBR");
            String scenarioId = distinctMSResultSet.getString("SCENARIO_ID");

            String assetClass = distinctMSResultSet.getString("ASSET_CLASS_FINAL");
            String exposureType = distinctMSResultSet.getString("EXPOSURE_TYPE_CODE");
            String entityType = distinctMSResultSet.getString("ERA_ENTITY_TYPE");
            String productType = distinctMSResultSet.getString("ERA_PRODUCT_TYPE_FINAL");

            if (!snapshotDateList.contains(snapshotDate)) {
                snapshotDateList.add(snapshotDate);
            }

            if (!loadJobNbrList.contains(loadJobNbr)) {
                loadJobNbrList.add(loadJobNbr);
            }

            if (!scenarioIdList.contains(scenarioId)) {
                scenarioIdList.add(scenarioId);
            }

            if (!assetClassList.contains(assetClass)) {
                assetClassList.add(assetClass);
            }

            if (!exposureTypeList.contains(exposureType)) {
                exposureTypeList.add(exposureType);
            }

            if (!entityTypeList.contains(entityType)) {
                entityTypeList.add(entityType);
            }

            if (!productTypeList.contains(productType)) {
                productTypeList.add(productType);
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

    private List<String> getIndustryCodes() throws SQLException {
        List<String> industryList = new ArrayList<>();
        ResultSet distinctCustomerResultSet = getDistinctCustomerIndustryCodeResultSet();
        while (distinctCustomerResultSet.next()) {
            String industryCode = distinctCustomerResultSet.getString("INDUSTRY_CODE");

            if (!industryList.contains(industryCode)) {
                industryList.add(industryCode);
            }
        }
        return industryList;
    }


    private ResultSet getDistinctMSResultSet() throws SQLException {
        ResultSet resultSet = null;
        long start = System.currentTimeMillis();
        log.info("Starting taking distinct data from MEASUREMENT_SENSITIVITY");

        Connection conn = null;
        Statement statement = null;
        try{
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT DISTINCT " +
                    "  SNAPSHOT_DATE,\n" +
                    "  LOAD_JOB_NBR,\n" +
                    "  SCENARIO_ID,\n" +
                    "  ASSET_CLASS_FINAL,\n" +
                    "  EXPOSURE_TYPE_CODE,\n" +
                    "  ERA_ENTITY_TYPE,\n" +
                    "  ERA_PRODUCT_TYPE_FINAL\n" +
                    "FROM MEASUREMENT_SENSITIVITY ms");
            log.info("distinct MEASUREMENT_SENSITIVITY data took {} ms", System.currentTimeMillis() - start);
        }finally {
            if (statement!=null){
                statement.close();
            }
            if (conn!=null){
                conn.close();
            }
        }
        return resultSet;
    }

    private ResultSet getDistinctCustomerIndustryCodeResultSet() throws SQLException {
        ResultSet resultSet = null;

        long start = System.currentTimeMillis();
        log.info("Starting taking distinct data from CUSTOMER");
        Connection conn = null;
        Statement statement = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT DISTINCT INDUSTRY_CODE FROM customer c");
            log.info("distinct CUSTOMER data took {} ms", System.currentTimeMillis() - start);
        } finally {
            if (statement!=null){
                statement.close();
            }
            if (conn!=null){
                conn.close();
            }
        }
        return resultSet;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
    }

}

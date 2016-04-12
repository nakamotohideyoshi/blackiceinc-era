package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.DbUtils;
import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityRepository;
import com.blackiceinc.era.persistence.erau.specifications.MsSpecificationsBuilder;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeasurementSensitivityServiceImpl implements MeasurementSensitivityService {

    public static final int PAGE_0 = 0;
    public static final int PAGE_SIZE_25 = 25;

    private final Logger log = LoggerFactory.getLogger(MeasurementSensitivityServiceImpl.class);

    @Autowired
    private MeasurementSensitivityRepository msRepository;

    @Autowired
    private DbUtils dbUtils;

    @Override
    public Page<MeasurementSensitivity> findMsByParams(Map<String, String> params) {
        Specification<MeasurementSensitivity> specMs = createMsSpecification(params);

        String page = params.get("page");
        String length = params.get("length");


        int pageNumber = (page != null) ? Integer.parseInt(page) : PAGE_0;
        int pageSize = (length != null) ? Integer.parseInt(length) : PAGE_SIZE_25;

        return msRepository.findAll(specMs, new PageRequest(pageNumber, pageSize));
    }

    @Override
    public Map<String, Float> getSums(Map<String, String> allRequestParams) throws SQLException {
        Map<String, Float> result = new HashMap<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        long start = System.currentTimeMillis();

        try {
            conn = dbUtils.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            StringBuilder query = new StringBuilder();

            query
                    .append("SELECT ")
                    .append("SUM(ms.EAD_BEFORE_CCF_LCY_AMT) as sumosbal, ")
                    .append("SUM(ms.RWA_AMT) as sumrwa, ")
                    .append("SUM(ms.REG_CAP) as sumregcap ")
                    .append("FROM MEASUREMENT_SENSITIVITY ms ")
                    .append("INNER JOIN CUSTOMER cus on ms.CUSTOMER_ID=cus.CUSTOMER_ID ");

            List<Condition> filtersForQuery = getFiltersForQuery(allRequestParams);
            if (!filtersForQuery.isEmpty()) {
                query.append("WHERE ")
                        .append(getConditionsAsString(filtersForQuery));
            }

            log.info("Selecting SUM for O/S Bal, RWA Amt and Reg Cap from MEASUREMENT_SENSITIVITY table");
            resultSet = stmt.executeQuery(query.toString());
            log.info("SUM took {} ms", System.currentTimeMillis() - start);

            if (resultSet.next()) {
                result.put("sumosbal", resultSet.getFloat("sumosbal"));
                result.put("sumrwa", resultSet.getFloat("sumrwa"));
                result.put("sumregcap", resultSet.getFloat("sumregcap"));
            }

        } finally {
            DbUtils.close(resultSet);
            DbUtils.close(stmt);
            DbUtils.close(conn);
        }

        return result;
    }

    private String getConditionsAsString(List<Condition> conditions) {
        StringBuilder sb = new StringBuilder();

        int index = 0;
        for (Condition condition : conditions) {
            if (index == 0) {
                sb.append(condition.createQueryCondition());
            } else {
                sb.append(" AND ").append(condition.createQueryCondition());
            }
            index++;
        }

        return sb.toString();
    }

    private List<Condition> getFiltersForQuery(Map<String, String> params) {
        List<Condition> conditions = new ArrayList<>();

        String snapshotDate = params.get("snapshotDate");
        if (snapshotDate != null) {
            conditions.add(new Condition("ms.SNAPSHOT_DATE", snapshotDate, Condition.Type.DATE));
        }

        String loadJobNbr = params.get("loadJobNbr");
        if (loadJobNbr != null) {
            conditions.add(new Condition("ms.LOAD_JOB_NBR", loadJobNbr, Condition.Type.INT));
        }

        String scenarioId = params.get("scenarioId");
        if (scenarioId != null) {
            conditions.add(new Condition("ms.SCENARIO_ID", scenarioId, Condition.Type.INT));
        }

        String industry = params.get("industry");
        if (industry != null) {
            conditions.add(new Condition("cus.INDUSTRY_CODE", industry, Condition.Type.STRING));
        }

        String profitCentre = params.get("profitCentre");
        // TODO: Profit centre will be done when column is ready in database
//        if (profitCentre!=null){
//            builder.with("orgUnit", ":", profitCentre, "", "");
//        }


        String assetClass = params.get("assetClass");
        if (assetClass != null) {
            conditions.add(new Condition("ms.ASSET_CLASS_FINAL", assetClass, Condition.Type.STRING));
        }

        String exposureType = params.get("exposureType");
        if (exposureType != null) {
            conditions.add(new Condition("ms.EXPOSURE_TYPE_CODE", exposureType, Condition.Type.STRING));
        }

        String entityType = params.get("entityType");
        if (entityType != null) {
            conditions.add(new Condition("ms.ERA_ENTITY_TYPE", entityType, Condition.Type.STRING));
        }

        String productType = params.get("productType");
        if (productType != null) {
            conditions.add(new Condition("ms.ERA_PRODUCT_TYPE_FINAL", productType, Condition.Type.STRING));
        }

        return conditions;
    }


    private Specification<MeasurementSensitivity> createMsSpecification(Map<String, String> params) {
        MsSpecificationsBuilder builder = new MsSpecificationsBuilder();

        String snapshotDate = params.get("snapshotDate");
        if (snapshotDate != null) {
            builder.with("snapshotDate", ":", ISODateTimeFormat.dateTimeParser().parseDateTime(snapshotDate).toLocalDate().toDate(), "", "");
        }

        String loadJobNbr = params.get("loadJobNbr");
        if (loadJobNbr != null) {
            builder.with("loadJobNbr", ":", loadJobNbr, "", "");
        }

        String scenarioId = params.get("scenarioId");
        if (scenarioId != null) {
            builder.with("scenarioId", ":", scenarioId, "", "");
        }

        String industry = params.get("industry");
        if (industry != null) {
            builder.with("industryCode", "I", industry, "", "");
        }

        String profitCentre = params.get("profitCentre");
//        if (profitCentre!=null){
//            builder.with("orgUnit", ":", profitCentre, "", "");
//        }


        String assetClass = params.get("assetClass");
        if (assetClass != null) {
            builder.with("assetClassFinal", ":", assetClass, "", "");
        }

        String exposureType = params.get("exposureType");
        if (exposureType != null) {
            builder.with("exposureTypeCode", ":", exposureType, "", "");
        }

        String entityType = params.get("entityType");
        if (entityType != null) {
            builder.with("eraEntityType", ":", entityType, "", "");
        }

        String productType = params.get("productType");
        if (productType != null) {
            builder.with("eraProductTypeFinal", ":", productType, "", "");
        }

        String riskRatingFrom = params.get("riskRatingFrom");
        String riskRatingTo = params.get("riskRatingTo");


        return builder.build();
    }


    private static class Condition {
        Object value;
        String column;
        Type type;

        private Condition(String column, Object value, Type type) {
            this.column = column;
            this.value = value;
            this.type = type;
        }

        public String createQueryCondition() {
            switch (type) {
                case DATE:
                    return column + "=" + "TO_DATE('" + value + "', 'YYYY-MM-DD')";
                case INT:
                    return column + "=" + value;
                case STRING:
                    return column + "=" + "'" + value + "'";
                default:
                    return null;
            }

        }

        public enum Type {
            STRING, INT, DATE, LONG, FLOAT
        }
    }

}

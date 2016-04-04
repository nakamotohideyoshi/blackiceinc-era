package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityRepository;
import com.blackiceinc.era.persistence.erau.specifications.MsSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MeasurementSensitivityServiceImpl implements MeasurementSensitivityService {

    public static final int PAGE_0 = 0;
    public static final int PAGE_SIZE_25 = 25;

    @Autowired
    private MeasurementSensitivityRepository msRepository;

    @Override
    public Page<MeasurementSensitivity> findMsByParams(Map<String, String> params) {
        Specification<MeasurementSensitivity> specMs = createMsSpecification(params);

        String page = params.get("page");
        String length = params.get("length");


        int pageNumber = (page != null) ? Integer.parseInt(page) : PAGE_0;
        int pageSize = (length != null) ? Integer.parseInt(length) : PAGE_SIZE_25;

        return msRepository.findAll(specMs, new PageRequest(pageNumber, pageSize));
    }

    private Specification<MeasurementSensitivity> createMsSpecification(Map<String, String> params) {
        MsSpecificationsBuilder builder = new MsSpecificationsBuilder();

        String snapshotDate = params.get("snapshotDate");
        if (snapshotDate!=null){
            builder.with("snapshotDate", ":", snapshotDate, "", "");
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
        if (industry!=null){
            builder.with("industryCode", "I", industry, "", "");
        }

        String profitCentre = params.get("profitCentre");
//        if (profitCentre!=null){
//            builder.with("orgUnit", ":", profitCentre, "", "");
//        }


        String assetClass = params.get("assetClass");
        if (assetClass!=null){
            builder.with("assetClassFinal", ":", assetClass, "", "");
        }

        String exposureType = params.get("exposureType");
        if (exposureType!=null){
            builder.with("exposureTypeCode", ":", exposureType, "", "");
        }

        String entityType = params.get("entityType");
        if (entityType!=null){
            builder.with("eraEntityType", ":", entityType, "", "");
        }

        String productType = params.get("productType");
        if (productType!=null){
            builder.with("eraProductTypeFinal", ":", productType, "", "");
        }

        String riskRatingFrom = params.get("riskRatingFrom");
        String riskRatingTo = params.get("riskRatingTo");


        return builder.build();
    }

}

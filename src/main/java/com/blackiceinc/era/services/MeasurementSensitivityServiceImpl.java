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
        Specification<MeasurementSensitivity> specMs = createMsSpecification();

        String page = params.get("page");
        String length = params.get("length");
        String snapshotDate = params.get("snapshotDate");
        String loadJobNbr = params.get("loadJobNbr");
        String scenarioId = params.get("scenarioId");
        String industry = params.get("industry");
        String profitCentre = params.get("profitCentre");
        String assetClass = params.get("assetClass");
        String exposureType = params.get("exposureType");
        String entityType = params.get("entityType");
        String productType = params.get("productType");
        String riskRatingFrom = params.get("riskRatingFrom");
        String riskRatingTo = params.get("riskRatingTo");

        int pageNumber = (page != null) ? Integer.parseInt(page) : PAGE_0;
        int pageSize = (length != null) ? Integer.parseInt(length) : PAGE_SIZE_25;

        return msRepository.findAll(specMs, new PageRequest(pageNumber, pageSize));
    }

    private Specification<MeasurementSensitivity> createMsSpecification() {
        MsSpecificationsBuilder builder = new MsSpecificationsBuilder();

        // TODO: to be implemented filtering

        return builder.build();
    }

}

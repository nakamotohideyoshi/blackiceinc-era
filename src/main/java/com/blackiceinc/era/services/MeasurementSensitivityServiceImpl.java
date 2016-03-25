package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityRepository;
import com.blackiceinc.era.persistence.erau.specifications.MsSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MeasurementSensitivityServiceImpl implements MeasurementSensitivityService {

    public static final int PAGE_0 = 0;
    public static final int PAGE_SIZE_25 = 25;

    @Autowired
    private MeasurementSensitivityRepository msRepository;

    @Override
    public Page<MeasurementSensitivity> findMsByParams(Integer page,
                                                       Integer length) {
        Specification<MeasurementSensitivity> specMs = createMsSpecification();

        int pageNumber = (page != null) ? page : PAGE_0;
        int pageSize = (length != null) ? length : PAGE_SIZE_25;

        return msRepository.findAll(specMs, new PageRequest(pageNumber, pageSize));
    }

    private Specification<MeasurementSensitivity> createMsSpecification() {
        MsSpecificationsBuilder builder = new MsSpecificationsBuilder();

        // TODO: to be implemented filtering

        return builder.build();
    }

}

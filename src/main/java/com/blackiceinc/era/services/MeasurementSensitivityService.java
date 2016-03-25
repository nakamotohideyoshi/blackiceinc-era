package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import org.springframework.data.domain.Page;

public interface MeasurementSensitivityService {
    Page<MeasurementSensitivity> findMsByParams(Integer page, Integer length);
}

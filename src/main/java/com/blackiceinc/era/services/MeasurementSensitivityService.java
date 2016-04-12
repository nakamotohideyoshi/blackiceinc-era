package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import org.springframework.data.domain.Page;

import java.sql.SQLException;
import java.util.Map;

public interface MeasurementSensitivityService {
    Page<MeasurementSensitivity> findMsByParams(Map<String, String> params);

    Map<String, Float> getSums(Map<String, String> allRequestParams) throws SQLException;
}

package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface MeasurementSensitivityRepository extends JpaRepository<MeasurementSensitivity, Long> {

    List<MeasurementSensitivity> findAllBySnapshotDateAndLoadJobNbrAndScenarioId(Date snapshotDate, Long loadJobNbr,
                                                                                 String scenarioId);

}

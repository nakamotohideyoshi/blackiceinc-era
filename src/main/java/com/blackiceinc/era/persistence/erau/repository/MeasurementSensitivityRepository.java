package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Date;
import java.util.List;

public interface MeasurementSensitivityRepository extends JpaRepository<MeasurementSensitivity, Long>, JpaSpecificationExecutor<MeasurementSensitivity> {

    List<MeasurementSensitivity> findAllBySnapshotDateAndLoadJobNbrAndScenarioId(Date snapshotDate, Long loadJobNbr,
                                                                                 String scenarioId);

}

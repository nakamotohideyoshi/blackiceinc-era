package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeasurementSensitivityRepository extends JpaRepository<MeasurementSensitivity,Long> {

//    @Query("select DISTINCT SNAPSHOT_DATE, LOAD_JOB_NBR, SCENARIO_ID from MEASUREMENT_SENSITIVITY")
//    List<MeasurementSensitivity> findAllDistinctBySnapshotDateAndLoadJobNbrAndScenarioId();

}

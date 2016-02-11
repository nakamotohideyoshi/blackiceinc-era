package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface RunCalculatorRepository extends JpaRepository<RunCalculator, Long>, JpaSpecificationExecutor<RunCalculator> {

    @Procedure(name = "RunCalculator.runCalculation")
    void runCalculatorStoredProcedure(@Param("P_SCENARIO_ID") String scenarioId,
                                      @Param("P_LOAD_JOB_NBR") Integer loadJobNbr,
                                      @Param("P_SNAPSHOT_DATE") Date snapshotDate);

    @Procedure(name = "RunCalculator.runCalculationTest")
    void runCalculatorStoredProcedureTest(@Param("P_SCENARIO_ID") String scenarioId,
                                      @Param("P_LOAD_JOB_NBR") Integer loadJobNbr,
                                      @Param("P_SNAPSHOT_DATE") Date snapshotDate);

}

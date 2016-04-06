package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RunCalculatorService {
    Map<String, List> getFilterOptions() throws SQLException;

    Page<RunCalculator> findRunCalculationByParams(Integer page,
                                                   Integer length,
                                                   Date snapshotDate,
                                                   BigDecimal loadJobNbr,
                                                   String scenarioId);

    List<Date> getSnapshotDateOptions() throws SQLException;

    void delete(Long id);

    void executeRunCalcProcedure(RunCalculator runCalculator) throws SQLException;
}

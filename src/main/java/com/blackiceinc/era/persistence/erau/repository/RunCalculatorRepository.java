package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RunCalculatorRepository extends JpaRepository<RunCalculator, Long>, JpaSpecificationExecutor<RunCalculator> {


}

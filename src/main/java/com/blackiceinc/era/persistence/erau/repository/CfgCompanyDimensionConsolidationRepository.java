package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimensionConsolidation;
import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimensionConsolidationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgCompanyDimensionConsolidationRepository extends JpaRepository<CfgCompanyDimensionConsolidation, CfgCompanyDimensionConsolidationKey> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

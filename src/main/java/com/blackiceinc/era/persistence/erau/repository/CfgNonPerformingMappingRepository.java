package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgNonPerformingMapping;
import com.blackiceinc.era.persistence.erau.model.CfgNonPerformingMappingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgNonPerformingMappingRepository extends JpaRepository<CfgNonPerformingMapping, CfgNonPerformingMappingKey> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

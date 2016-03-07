package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgOpsProductTypeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgOpsProductTypeMappingRepository extends JpaRepository<CfgOpsProductTypeMapping, String> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

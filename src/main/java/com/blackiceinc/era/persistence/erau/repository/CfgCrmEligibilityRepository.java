package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibility;
import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibilityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgCrmEligibilityRepository extends JpaRepository<CfgCrmEligibility, CfgCrmEligibilityKey> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

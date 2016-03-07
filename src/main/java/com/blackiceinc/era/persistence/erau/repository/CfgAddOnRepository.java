package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgAddOn;
import com.blackiceinc.era.persistence.erau.model.CfgAddOnKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgAddOnRepository extends JpaRepository<CfgAddOn, CfgAddOnKey> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

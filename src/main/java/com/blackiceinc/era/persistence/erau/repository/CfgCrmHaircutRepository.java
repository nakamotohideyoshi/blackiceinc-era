package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircut;
import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircutKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgCrmHaircutRepository extends JpaRepository<CfgCrmHaircut, CfgCrmHaircutKey> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

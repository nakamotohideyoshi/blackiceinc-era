package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgReclassCheckDefRepository extends JpaRepository<CfgReclassCheckDef, String> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

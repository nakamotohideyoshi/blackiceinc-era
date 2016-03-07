package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgEntityTypeMapping;
import com.blackiceinc.era.persistence.erau.model.CfgEntityTypeMappingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgEntityTypeMappingRepository extends JpaRepository<CfgEntityTypeMapping, CfgEntityTypeMappingKey> {

    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();

}

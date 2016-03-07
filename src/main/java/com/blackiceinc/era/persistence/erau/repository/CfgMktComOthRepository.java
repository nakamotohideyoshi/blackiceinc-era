package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktComOth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgMktComOthRepository extends JpaRepository<CfgMktComOth, String> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}

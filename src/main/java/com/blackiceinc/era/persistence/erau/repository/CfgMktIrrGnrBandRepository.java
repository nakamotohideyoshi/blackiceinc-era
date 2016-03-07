package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgMktIrrGnrBandRepository extends JpaRepository<CfgMktIrrGnrBand, String> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();
}
package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgReclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgReclassRepository extends JpaRepository<CfgReclass, String> {

    public static String INSERT = "insert";

    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();

//    @Modifying
//    @Query(name = INSERT, nativeQuery = true)
    void insert(String checkNo, String description, String eraEntityTypeIn,
                String eraProductTypeIn, String check, String eraEntityTypeOut,
                String eraProductTypeOut);

}

package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgRating;
import com.blackiceinc.era.persistence.erau.model.CfgRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgRatingRepository extends JpaRepository<CfgRating, CfgRatingKey> {

    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();

}

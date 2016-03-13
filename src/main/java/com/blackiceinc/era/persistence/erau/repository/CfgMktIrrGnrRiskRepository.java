package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CfgMktIrrGnrRiskRepository extends JpaRepository<CfgMktIrrGnrRisk, String> {
    @Modifying
    @Query("delete from #{#entityName}")
    void deleteAll();

//    @Query(value = "INSERT INTO " +
//            "CFG_MKT_IRR_GNR_RISK " +
//            "(ZONE_CODE, BAND_CODE, CURRENCY, COUPON_RATE_START, COUPON_RATE_END, MATURITY_BAND_START, MATURITY_BAND_END, RISK_WEIGHT) " +
//            "VALUES " +
//            "(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
//    void insert(String zoneCode, String bandCode, String currency, Long couponRateStart,
//                Long couponRateEnd, Long maturityBandStart,Long maturityBandEnd, Double riskWeight);
}

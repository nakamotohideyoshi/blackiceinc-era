package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgReclass;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

@Repository
public class MeasurementSensitivityDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void delete(Date snapshotDate, Long loadJobNbr, String scenarioId) {
        this.em.createNativeQuery("DELETE FROM MEASUREMENT_SENSITIVITY WHERE SNAPSHOT_DATE=?1 AND LOAD_JOB_NBR=?2 AND SCENARIO_ID=?3")
                .setParameter(1, snapshotDate)
                .setParameter(2, loadJobNbr)
                .setParameter(3, scenarioId)

                .executeUpdate();
    }

}

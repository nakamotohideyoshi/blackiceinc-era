package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompany;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCompanyDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCompany cfgCompany = (CfgCompany) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_COMPANY " +
                "(" +
                "COMPANY_CODE, " +
                "COMPANY_NAME, " +
                "INCORPORATION_COUNTRY" +
                ") " +
                "VALUES " +
                "(" +
                ":companyCode, " +
                ":companyName, " +
                ":incorporationCountry" +
                ")")
                .setParameter("companyCode",
                        cfgCompany.getCompanyCode(), new StringType())
                .setParameter("companyName",
                        cfgCompany.getCompanyName(), new StringType())
                .setParameter("incorporationCountry",
                        cfgCompany.getIncorporationCountry(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_COMPANY").executeUpdate();
    }

}

package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktProductMapping;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktProductMappingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktProductMapping cfgMktProductMapping = (CfgMktProductMapping) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_PRODUCT_MAPPING " +
                "( " +
                "MKT_PRODUCT_TYPE, " +
                "CONTRACT_TYPE, " +
                "EXCHANGED_TRADED, " +
                "INSTRUMENT_TYPE, " +
                "TABLE_NAME, " +
                "UNDERLYING_TYPE " +
                ") " +
                "VALUES " +
                "(" +
                ":mktProductType, " +
                ":contractType, " +
                ":exchangedTraded, " +
                ":instrumentType, " +
                ":tableName, " +
                ":underlyingType " +
                ")")
                .setParameter("mktProductType", cfgMktProductMapping.getMktProductType(), new StringType())
                .setParameter("contractType", cfgMktProductMapping.getContractType(), new StringType())
                .setParameter("exchangedTraded", cfgMktProductMapping.getExchangedTraded(), new StringType())
                .setParameter("instrumentType", cfgMktProductMapping.getInstrumentType(), new StringType())
                .setParameter("tableName", cfgMktProductMapping.getTableName(), new StringType())
                .setParameter("underlyingType", cfgMktProductMapping.getUnderlyingType(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_PRODUCT_MAPPING").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}

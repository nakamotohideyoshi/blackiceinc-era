package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrSpcRisk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@Transactional
public class CfgMktIrrSpcRiskRepositoryTest {

    @Autowired
    private CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository;

    @Autowired
    private CfgMktIrrSpcRiskDaoCustom cfgMktIrrSpcRiskDaoCustom;

    @Test
    public void testSave() {
        CfgMktIrrSpcRisk cfgMktIrrSpcRisk = new CfgMktIrrSpcRisk();
        cfgMktIrrSpcRisk.setMktAssetClass("mkt_asset_class");
        cfgMktIrrSpcRisk.setIssueRiskBucket("issue_risk_bucket");
        cfgMktIrrSpcRisk.setIssuerRiskBucket("issuer_risk_bucket");
        cfgMktIrrSpcRisk.setResidualMaturityStart("residual_maturity_start");
        cfgMktIrrSpcRisk.setResidualMaturityEnd("residual_maturity_end");
        cfgMktIrrSpcRisk.setInstrumentGroup("instrument_group");
        cfgMktIrrSpcRisk.setRiskWeight(new Double(5));
        cfgMktIrrSpcRisk.setSeq(500L);

        cfgMktIrrSpcRiskDaoCustom.insert(cfgMktIrrSpcRisk);
//        cfgMktIrrSpcRiskRepository.save(cfgMktIrrSpcRisk);
    }

}
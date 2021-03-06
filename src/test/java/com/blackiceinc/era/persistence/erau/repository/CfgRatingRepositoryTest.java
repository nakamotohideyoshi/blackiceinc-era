package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgRating;
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
public class CfgRatingRepositoryTest {

    @Autowired
    private CfgRatingRepository cfgRatingRepository;

    @Autowired
    private CfgRatingDaoCustom cfgRatingDaoCustom;

    @Test
    public void testSave(){
        CfgRating cfgRating = new CfgRating();
        cfgRating.setAgencyCode("agency_code");
        cfgRating.setRating("rating");
        cfgRating.setQualifying("qualifying");
        cfgRating.setRiskBucket(1L);

        cfgRatingDaoCustom.insert(cfgRating);
//        cfgRatingRepository.save(cfgRating);
    }

}
package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.ConfigApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConfigAppRepository extends JpaRepository<ConfigApp, Long>, JpaSpecificationExecutor<ConfigApp> {

    ConfigApp findOneByKey(String key);

}

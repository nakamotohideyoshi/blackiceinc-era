package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CfgFinancialBookRepository extends JpaRepository<CfgFinancialBook, String> {
}

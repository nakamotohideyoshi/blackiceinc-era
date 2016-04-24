package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConfigFileRepository extends JpaRepository<ConfigFile, Long>, JpaSpecificationExecutor<ConfigFile> {
    ConfigFile findOneByFileName(String fileName);

    Page<ConfigFile> findByFileNameContainingOrderByModifiedDesc(String fileName, Pageable pageable);

    Page<ConfigFile> findAllByOrderByModifiedDesc(Pageable pageable);

    ConfigFile findOneByStatus(String current);
}

package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ConfigFileService {
    Page<ConfigFile> findConfigFilesByParams(Integer page, Integer length, String name);

    ConfigFile save(MultipartFile file);

    void delete(long id);

}

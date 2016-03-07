package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.ConfigFile;

public interface ConfigurationExportImportService {

    void exportConfigurationFromDbIntoFile(Long id);

    void importConfigurationFromFileIntoDb(ConfigFile configFile) throws Exception;

}

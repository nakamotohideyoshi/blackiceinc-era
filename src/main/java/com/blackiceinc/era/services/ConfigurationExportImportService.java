package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import com.blackiceinc.era.services.exception.CfgImportExportException;

public interface ConfigurationExportImportService {

    void exportConfigurationFromDbIntoExcel(ConfigFile configFile) throws CfgImportExportException;

    ConfigFile importConfigurationFromExcelIntoDb(ConfigFile configFile) throws CfgImportExportException;

}

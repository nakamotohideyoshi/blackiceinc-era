package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import com.blackiceinc.era.persistence.erau.repository.ConfigFileRepository;
import com.blackiceinc.era.services.cfgimport.CfgImportService;
import com.blackiceinc.era.services.exception.CfgImportException;
import com.blackiceinc.era.services.exception.CfgImportExportException;
import com.blackiceinc.era.services.exception.model.CfgMessage;
import org.apache.commons.codec.CharEncoding;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigurationExportImportServiceImpl implements ConfigurationExportImportService {

    private static final String CURRENT = "CURRENT";
    private static final String NULL = "NULL";

    private static final Logger log = LoggerFactory.getLogger(ConfigurationExportImportServiceImpl.class);
    @Autowired
    List<CfgImportService> cfgImportServiceList;
    @Autowired
    private ConfigFileRepository configFileRepository;

    @Override
    public void exportConfigurationFromDbIntoExcel(ConfigFile configFile) {
        log.info("Starting export for configFile with id : {}", configFile.toString());
        long start = System.currentTimeMillis();

        try {
            FileInputStream fis = new FileInputStream(new File(getDecodedPath(configFile)));
            XSSFWorkbook workbook = openWorkbook(fis);

            for (CfgImportService cfgImportService : cfgImportServiceList) {
                cfgImportService.makeImportIntoExcelFromDb(workbook);
            }

            closeFileInputStream(fis);

            //Write the workbook in file system
            writeWorkbookIntoFile(configFile, workbook);
        } catch (FileNotFoundException e) {
            throwFileNotFoundEx(configFile, e);
        }

        log.info("Export for configFile : {} finished in {} ms", configFile.toString(), System.currentTimeMillis() - start);
    }

    private void throwFileNotFoundEx(ConfigFile configFile, FileNotFoundException e) {
        log.error("File not found. filePath : {}", configFile.getFilePath(), e);
        throw new CfgImportExportException("File not found", e);
    }

    @Override
    @Transactional
    public ConfigFile importConfigurationFromExcelIntoDb(ConfigFile configFile) {
        log.info("Starting import for configFile : {}", configFile.toString());
        long start = System.currentTimeMillis();

        parseAndImportIntoDb(configFile);

        ConfigFile savedConfig = updateConfigFileStatusToCurrent(configFile);
        log.info("Import for configFile : {} finished in {} ms", configFile.toString(), System.currentTimeMillis() - start);

        return savedConfig;
    }

    private void parseAndImportIntoDb(ConfigFile configFile) {
        try {
            FileInputStream fis = new FileInputStream(new File(configFile.getFilePath()));

            XSSFWorkbook workbook = openWorkbook(fis);

            List<CfgMessage> errors = makeImportIntoDb(workbook);

            if (!errors.isEmpty()) {
                throw new CfgImportExportException(errors);
            }

            closeFileInputStream(fis);
        } catch (FileNotFoundException e) {
            throwFileNotFoundEx(configFile, e);

        }
    }

    private ConfigFile updateConfigFileStatusToCurrent(ConfigFile configFile) {
        ConfigFile oneByStatusCurrent = configFileRepository.findOneByStatus(CURRENT);
        if (oneByStatusCurrent != null) {
            oneByStatusCurrent.setStatus(NULL);
            configFileRepository.save(oneByStatusCurrent);
        }

        configFile.setStatus(CURRENT);
        return configFileRepository.save(configFile);
    }

    private List<CfgMessage> makeImportIntoDb(XSSFWorkbook workbook) {
        List<CfgMessage> errors = new ArrayList<>();
        for (CfgImportService cfgImportService : cfgImportServiceList) {
            try {
                cfgImportService.makeImportIntoDb(workbook);
            } catch (CfgImportException ex) {
                log.error(ex.getCfgMessage().getMsg(), ex);
                errors.add(ex.getCfgMessage());
            }
        }
        return errors;
    }

    private void closeFileInputStream(FileInputStream fis) {
        try {
            fis.close();
        } catch (IOException e) {
            throw new CfgImportExportException("Error closing file strem", e);
        }
    }

    private XSSFWorkbook openWorkbook(FileInputStream fis) {
        try {
            return new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new CfgImportExportException("Error opening excel workbook", e);
        }
    }

    private String getDecodedPath(ConfigFile configFile) {
        try {
            return URLDecoder.decode(configFile.getFilePath(), CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error("Error decoding path : {}", configFile.getFilePath());
            throw new CfgImportExportException(e);
        }
    }

    private void writeWorkbookIntoFile(ConfigFile configFile, XSSFWorkbook workbook) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(configFile.getFilePath()));
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            throwFileNotFoundEx(configFile, e);
        } catch (IOException e) {
            throw new CfgImportExportException(e);
        }
    }
}

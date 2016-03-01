package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.CfgCompany;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import com.blackiceinc.era.persistence.erau.repository.ConfigFileRepository;
import com.blackiceinc.era.services.excel.mapper.CfgCompanyObjectMapper;
import com.blackiceinc.era.services.excel.mapper.CfgFinancialObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class ConfigFileServiceImpl implements ConfigFileService {

    public static final int PAGE_0 = 0;
    public static final int PAGE_SIZE_25 = 25;
    public static final String FINANCIAL_BOOK = "FINANCIAL_BOOK";
    public static final String COMPANY = "COMPANY";

    private static Logger log = LoggerFactory.getLogger(ConfigFileServiceImpl.class);


    @Value("${storage-path}")
    private String storagePath;

    private CfgFinancialObjectMapper cfgFinancialBookObjectMapper;
    private CfgCompanyObjectMapper cfgCompanyObjectMapper;

    private ConfigFileRepository configFileRepository;

    @Autowired
    public ConfigFileServiceImpl(ConfigFileRepository configFileRepository, CfgFinancialObjectMapper cfgFinancialBookObjectMapper,
                                 CfgCompanyObjectMapper cfgCompanyObjectMapper) {
        this.configFileRepository = configFileRepository;
        this.cfgFinancialBookObjectMapper = cfgFinancialBookObjectMapper;
        this.cfgCompanyObjectMapper = cfgCompanyObjectMapper;
    }

    @Override
    public Page<ConfigFile> findConfigFilesByParams(Integer page, Integer length, String name) {
        int pageNumber = (page != null) ? page : PAGE_0;
        int pageSize = (length != null) ? length : PAGE_SIZE_25;

        Page<ConfigFile> byFileNameContaining = null;
        if (name != null && !name.isEmpty()) {
            byFileNameContaining = configFileRepository.findByFileNameContainingOrderByModifiedDesc(name, new PageRequest(pageNumber, pageSize));
        } else {
            byFileNameContaining = configFileRepository.findAllByOrderByModifiedDesc(new PageRequest(pageNumber, pageSize));
        }
        return byFileNameContaining;
    }

    @Override
    public ConfigFile save(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = storagePath + fileName;

        //store file to disk
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));

        ConfigFile configFile = configFileRepository.findOneByFileName(fileName);
        if (configFile != null) {
            // overwrite file and update
        } else {
            // create file
            configFile = new ConfigFile();
            configFile.setFileName(fileName);
            configFile.setFilePath(filePath);
            configFile.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }

        configFile.setStatus("NULL");
        configFile.setModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        configFile = configFileRepository.save(configFile);

        return configFile;
    }

    @Override
    public void delete(long id) {
        ConfigFile configFile = configFileRepository.getOne(id);
        configFileRepository.delete(id);
        if (!FileUtils.deleteQuietly(new File(configFile.getFilePath()))) {
            log.warn("File {} could not be deleted.", configFile.getFilePath());
        }
    }

    @Override
    public void exportConfiguration(Long id) {
        ConfigFile configFile = configFileRepository.findOne(id);

        try {
            FileInputStream file = new FileInputStream(new File(configFile.getFilePath()));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet financialBookSheet = workbook.getSheet(FINANCIAL_BOOK);
            List<CfgFinancialBook> cfgFinancialBookList = cfgFinancialBookObjectMapper.extractCfgFinancialBooks(financialBookSheet);

            XSSFSheet companySheet = workbook.getSheet(COMPANY);
            List<CfgCompany> cfgCompaniesList = cfgCompanyObjectMapper.extractCfgCompanies(companySheet);

            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importConfiguration(Long id) {
        ConfigFile configFile = configFileRepository.findOne(id);

        try {
            URL resource = this.getClass().getResource("/VIB_ERA_Configuration_Empty.xlsx");
            FileInputStream file = new FileInputStream(new File(resource.getPath()));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet financialBookSheet = workbook.getSheet(FINANCIAL_BOOK);
            cfgFinancialBookObjectMapper.importCfgFinancialBooks(financialBookSheet);

            XSSFSheet companySheet = workbook.getSheet(COMPANY);
            cfgCompanyObjectMapper.importCfgCompanies(companySheet);

            file.close();

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(configFile.getFilePath()));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

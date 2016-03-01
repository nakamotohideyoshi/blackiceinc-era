package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.CfgCompany;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyRepository;
import com.blackiceinc.era.persistence.erau.repository.CfgFinancialBookRepository;
import com.blackiceinc.era.persistence.erau.repository.ConfigFileRepository;
import com.blackiceinc.era.services.excel.mapper.CfgCompanyObjectMapper;
import com.blackiceinc.era.services.excel.mapper.CfgFinancialObjectMapper;
import org.apache.commons.codec.CharEncoding;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class ConfigFileServiceImplTest {

    private ConfigFileService configFileService;

    @Mock
    private ConfigFileRepository configFileRepository;

    @Mock
    private CfgFinancialBookRepository cfgFinancialBookRepository;

    @Mock
    private CfgCompanyRepository cfgCompanyRepository;

    private CfgFinancialObjectMapper cfgFinancialObjectMapper;

    private CfgCompanyObjectMapper cfgCompanyObjectMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        cfgFinancialObjectMapper = new CfgFinancialObjectMapper(cfgFinancialBookRepository);
        cfgCompanyObjectMapper = new CfgCompanyObjectMapper(cfgCompanyRepository);
        configFileService = new ConfigFileServiceImpl(configFileRepository,
                cfgFinancialObjectMapper, cfgCompanyObjectMapper);
    }

    @Test
    public void testExportConfiguration() throws Exception {

        URL resource = this.getClass().getResource("/VIB_ERA_Configuration v1.11.xlsx");

        ConfigFile configFile = new ConfigFile();
        configFile.setFilePath(URLDecoder.decode(resource.getPath(), CharEncoding.UTF_8));
        when(configFileRepository.findOne(anyLong())).thenReturn(configFile);

        configFileService.exportConfiguration(1L);

    }

    @Test
    public void testImportConfiguration() throws UnsupportedEncodingException {
        File file = new File("D://howtodoinjava_demo.xlsx");
        ConfigFile configFile = new ConfigFile();
        configFile.setFilePath(URLDecoder.decode(file.getPath(), CharEncoding.UTF_8));
        when(configFileRepository.findOne(anyLong())).thenReturn(configFile);

        List<CfgFinancialBook> cfgFinancialBookList = new ArrayList<>();
        cfgFinancialBookList.add(createCfgFinancialBookListObj("book_code_1", "book_desc_1", "T"));
        cfgFinancialBookList.add(createCfgFinancialBookListObj("book_code_2", "book_desc_2", "F"));
        when(cfgFinancialBookRepository.findAll()).thenReturn(cfgFinancialBookList);

        List<CfgCompany> cfgCompanyList = new ArrayList<>();
        cfgCompanyList.add(createCfgCompany("comp_code_1", "comp_name_1", "VN"));
        cfgCompanyList.add(createCfgCompany("comp_code_2", "comp_name_2", "VN"));
        when(cfgCompanyRepository.findAll()).thenReturn(cfgCompanyList);

        configFileService.importConfiguration(1L);
    }

    private CfgFinancialBook createCfgFinancialBookListObj(String bookCode, String bookDesc, String tradingFlag){
        CfgFinancialBook cfgFinancialBook = new CfgFinancialBook();
        cfgFinancialBook.setBookCode(bookCode);
        cfgFinancialBook.setBookDesc(bookDesc);
        cfgFinancialBook.setTradingFlag(tradingFlag);
        return cfgFinancialBook;
    }

    private CfgCompany createCfgCompany(String companyCode, String companyName, String incorporationCountry) {
        CfgCompany cfgCompany = new CfgCompany();
        cfgCompany.setCompanyCode(companyCode);
        cfgCompany.setCompanyName(companyName);
        cfgCompany.setIncorporationCountry(incorporationCountry);
        return cfgCompany;
    }
}
package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.*;
import com.blackiceinc.era.persistence.erau.repository.ConfigFileRepository;
import com.blackiceinc.era.services.excel.mapper.*;
import org.apache.commons.codec.CharEncoding;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;

@Service
public class ConfigurationExportImportServiceImpl implements ConfigurationExportImportService {

    public static final String CURRENT = "CURRENT";
    public static final String NULL = "NULL";
    public static final String FINANCIAL_BOOK = "FINANCIAL_BOOK";
    public static final String COMPANY = "COMPANY";
    public static final String COMPANY_LINKAGE = "COMPANY_LINKAGE";
    public static final String COMPANY_DIMENSION = "COMPANY_DIMENSION";
    public static final String COMPANY_DIMENSION_CONSOLIDATION = "COMPANY_DIMENSION_CONSOLIDATION";
    public static final String ENTITY_TYPE = "ENTITY_TYPE";
    public static final String ENTITY_TYPE_MAPPING = "ENTITY_TYPE_MAPPING";
    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String PRODUCT_TYPE_MAPPING = "PRODUCT_TYPE_MAPPING";
    public static final String ASSET_CLASS = "ASSET_CLASS";
    public static final String ASSET_CLASS_MAPPING = "ASSET_CLASS_MAPPING";
    public static final String NON_PERFORMING_MAPPING = "NON_PERFORMING_MAPPING";
    public static final String AGENCY_ELIGIBILITY = "AGENCY_ELIGIBILITY";
    public static final String RATING = "RATING";
    public static final String CREDIT_MEASURE = "CREDIT_MEASURE";
    public static final String RISK_WEIGHT_MAPPING = "RISK_WEIGHT_MAPPING";
    public static final String CCF_MAPPING = "CCF_MAPPING";
    public static final String ADD_ON = "ADD_ON";
    public static final String CRM_ELIGIBILITY = "CRM_ELIGIBILITY";
    public static final String CRM_HAIRCUT = "CRM_HAIRCUT";
    public static final String RECLASS = "RECLASS";
    public static final String RECLASS_CHECK_DEF = "RECLASS_CHECK_DEF";
    public static final String RECLASS_CHECK_TYPE = "RECLASS_CHECK_TYPE";
    public static final String MKT_PRODUCT_TYPE = "MKT_PRODUCT_TYPE";
    public static final String MKT_PRODUCT_MAPPING = "MKT_PRODUCT_MAPPING";
    public static final String MKT_ASSET_CLASS = "MKT_ASSET_CLASS";
    public static final String MKT_ASSET_CLASS_MAPPING = "MKT_ASSET_CLASS_MAPPING";
    public static final String MKT_IRR_SPC_RISK = "MKT_IRR_SPC_RISK";
    public static final String MKT_IRR_GNR_RISK = "MKT_IRR_GNR_RISK";
    public static final String MKT_IRR_GNR_BAND = "MKT_IRR_GNR_BAND";
    public static final String MKT_IRR_GNR_INTRA = "MKT_IRR_GNR_INTRA";
    public static final String MKT_IRR_GNR_INTER = "MKT_IRR_GNR_INTER";
    public static final String MKT_EQT_SPC = "MKT_EQT_SPC";
    public static final String MKT_EQT_GNR = "MKT_EQT_GNR";
    public static final String MKT_COM_DRT = "MKT_COM_DRT";
    public static final String MKT_COM_OTH = "MKT_COM_OTH";
    public static final String MKT_FX = "MKT_FX";
    public static final String OPS_PRODUCT_TYPE = "OPS_PRODUCT_TYPE";
    public static final String OPS_PRODUCT_TYPE_MAPPING = "OPS_PRODUCT_TYPE_MAPPING";
    public static final String OPS_FORMULA = "OPS_FORMULA";
    public static final String OPS_RISK = "OPS_RISK";
    public static final String CAP_ELEMENTS = "CAP_ELEMENTS";
    public static final String CAP_ELEMENTS_TYPE = "CAP_ELEMENTS_TYPE";
    public static final String CAP_ELEMENTS_LIMIT = "CAP_ELEMENTS_LIMIT";
    public static final String CAP_ELEMENTS_MAPPING = "CAP_ELEMENTS_MAPPING";
    public static final String CAP_ELEMENTS_FORMULA = "CAP_ELEMENTS_FORMULA";
    private static final Logger log = LoggerFactory.getLogger(ConfigurationExportImportServiceImpl.class);
    private final CfgFinancialBookObjectMapper cfgFinancialBookObjectMapper;
    private final CfgCompanyObjectMapper cfgCompanyObjectMapper;
    private final CfgCompanyLinkageObjectMapper cfgCompanyLinkageObjectMapper;
    private final CfgCompanyDimensionObjectMapper cfgCompanyDimensionObjectMapper;
    private final CfgCompanyDimensionConsolidationObjectMapper cfgCompanyDimensionConsolidationObjectMapper;

    private final CfgEntityTypeObjectMapper cfgEntityTypeObjectMapper;
    private final CfgEntityTypeMappingObjectMapper cfgEntityTypeMappingObjectMapper;

    private final CfgProductTypeObjectMapper cfgProductTypeObjectMapper;
    private final CfgProductTypeMappingObjectMapper cfgProductTypeMappingObjectMapper;

    private final CfgAssetClassObjectMapper cfgAssetClassObjectMapper;
    private final CfgAssetClassMappingObjectMapper cfgAssetClassMappingObjectMapper;

    private CfgNonPerformingMappingObjectMapper cfgNonPerformingMappingObjectMapper;


    private CfgAgencyEligibilityObjectMapper cfgAgencyEligibilityObjectMapper;

    private CfgRatingObjectMapper cfgRatingObjectMapper;

    private CfgCreditMeasureObjectMapper cfgCreditMeasureObjectMapper;

    private CfgRiskWeightMappingObjectMapper cfgRiskWeightMappingObjectMapper;

    private CfgCcfMappingObjectMapper cfgCcfMappingObjectMapper;

    private CfgAddOnObjectMapper cfgAddOnObjectMapper;

    private CfgCrmEligibilityObjectMapper cfgCrmEligibilityObjectMapper;
    private CfgCrmHaircutObjectMapper cfgCrmHaircutObjectMapper;

    private CfgReclassObjectMapper cfgReclassObjectMapper;
    private CfgReclassCheckDefObjectMapper cfgReclassCheckDefObjectMapper;
    private CfgReclassCheckTypeObjectMapper cfgReclassCheckTypeObjectMapper;

    private CfgMktProductTypeObjectMapper cfgMktProductTypeObjectMapper;

    private CfgMktProductMappingObjectMapper cfgMktProductMappingObjectMapper;
    private CfgMktAssetClassObjectMapper cfgMktAssetClassObjectMapper;
    private CfgMktAssetClassMappingObjectMapper cfgMktAssetClassMappingObjectMapper;
    private CfgMktIrrSpcRiskObjectMapper cfgMktIrrSpcRiskObjectMapper;
    private CfgMktIrrGnrRiskObjectMapper cfgMktIrrGnrRiskObjectMapper;
    private CfgMktIrrGnrBandObjectMapper cfgMktIrrGnrBandObjectMapper;
    private CfgMktIrrGnrIntraObjectMapper cfgMktIrrGnrIntraObjectMapper;
    private CfgMktIrrGnrInterObjectMapper cfgMktIrrGnrInterObjectMapper;
    private CfgMktEqtSpcObjectMapper cfgMktEqtSpcObjectMapper;
    private CfgMktEqtGnrObjectMapper cfgMktEqtGnrObjectMapper;
    private CfgMktComDrtObjectMapper cfgMktComDrtObjectMapper;
    private CfgMktComOthObjectMapper cfgMktComOthObjectMapper;
    private CfgMktFxObjectMapper cfgMktFxObjectMapper;

    private CfgOpsProductTypeObjectMapper cfgOpsProductTypeObjectMapper;
    private CfgOpsProductTypeMappingObjectMapper cfgOpsProductTypeMappingObjectMapper;
    private CfgOpsFormulaObjectMapper cfgOpsFormulaObjectMapper;
    private CfgOpsRiskObjectMapper cfgOpsRiskObjectMapper;

    private CfgCapElementsObjectMapper cfgCapElementsObjectMapper;
    private CfgCapElementsTypeObjectMapper cfgCapElementsTypeObjectMapper;
    private CfgCapElementsLimitObjectMapper cfgCapElementsLimitObjectMapper;
    private CfgCapElementsMappingObjectMapper cfgCapElementsMappingObjectMapper;
    private CfgCapElementsFormulaObjectMapper cfgCapElementsFormulaObjectMapper;


    private ConfigFileRepository configFileRepository;
    private ImportConfigIntoDb importConfigIntoDb;


    @Autowired
    public ConfigurationExportImportServiceImpl(ConfigFileRepository configFileRepository,
                                                ImportConfigIntoDb importConfigIntoDb,
                                                CfgFinancialBookObjectMapper cfgFinancialBookObjectMapper,
                                                CfgCompanyObjectMapper cfgCompanyObjectMapper,
                                                CfgCompanyLinkageObjectMapper cfgCompanyLinkageObjectMapper,
                                                CfgCompanyDimensionObjectMapper cfgCompanyDimensionObjectMapper,
                                                CfgCompanyDimensionConsolidationObjectMapper cfgCompanyDimensionConsolidationObjectMapper,
                                                CfgEntityTypeObjectMapper cfgEntityTypeObjectMapper,
                                                CfgEntityTypeMappingObjectMapper cfgEntityTypeMappingObjectMapper,
                                                CfgProductTypeObjectMapper cfgProductTypeObjectMapper,
                                                CfgProductTypeMappingObjectMapper cfgProductTypeMappingObjectMapper,
                                                CfgAssetClassObjectMapper cfgAssetClassObjectMapper,
                                                CfgAssetClassMappingObjectMapper cfgAssetClassMappingObjectMapper,
                                                CfgNonPerformingMappingObjectMapper cfgNonPerformingMappingObjectMapper,
                                                CfgAgencyEligibilityObjectMapper cfgAgencyEligibilityObjectMapper,
                                                CfgRatingObjectMapper cfgRatingObjectMapper,
                                                CfgCreditMeasureObjectMapper cfgCreditMeasureObjectMapper,
                                                CfgRiskWeightMappingObjectMapper cfgRiskWeightMappingObjectMapper,
                                                CfgCcfMappingObjectMapper cfgCcfMappingObjectMapper,
                                                CfgAddOnObjectMapper cfgAddOnObjectMapper,
                                                CfgCrmEligibilityObjectMapper cfgCrmEligibilityObjectMapper,
                                                CfgCrmHaircutObjectMapper cfgCrmHaircutObjectMapper,
                                                CfgReclassObjectMapper cfgReclassObjectMapper,
                                                CfgReclassCheckDefObjectMapper cfgReclassCheckDefObjectMapper, CfgReclassCheckTypeObjectMapper cfgReclassCheckTypeObjectMapper, CfgMktProductTypeObjectMapper cfgMktProductTypeObjectMapper, CfgMktProductMappingObjectMapper cfgMktProductMappingObjectMapper, CfgMktAssetClassObjectMapper cfgMktAssetClassObjectMapper, CfgMktAssetClassMappingObjectMapper cfgMktAssetClassMappingObjectMapper, CfgMktIrrSpcRiskObjectMapper cfgMktIrrSpcRiskObjectMapper, CfgMktIrrGnrRiskObjectMapper cfgMktIrrGnrRiskObjectMapper, CfgMktIrrGnrBandObjectMapper cfgMktIrrGnrBandObjectMapper, CfgMktIrrGnrIntraObjectMapper cfgMktIrrGnrIntraObjectMapper, CfgMktIrrGnrInterObjectMapper cfgMktIrrGnrInterObjectMapper, CfgMktEqtSpcObjectMapper cfgMktEqtSpcObjectMapper, CfgMktEqtGnrObjectMapper cfgMktEqtGnrObjectMapper, CfgMktComDrtObjectMapper cfgMktComDrtObjectMapper, CfgMktComOthObjectMapper cfgMktComOthObjectMapper, CfgMktFxObjectMapper cfgMktFxObjectMapper, CfgOpsProductTypeObjectMapper cfgOpsProductTypeObjectMapper, CfgOpsProductTypeMappingObjectMapper cfgOpsProductTypeMappingObjectMapper, CfgOpsFormulaObjectMapper cfgOpsFormulaObjectMapper, CfgOpsRiskObjectMapper cfgOpsRiskObjectMapper, CfgCapElementsObjectMapper cfgCapElementsObjectMapper, CfgCapElementsTypeObjectMapper cfgCapElementsTypeObjectMapper, CfgCapElementsLimitObjectMapper cfgCapElementsLimitObjectMapper, CfgCapElementsMappingObjectMapper cfgCapElementsMappingObjectMapper, CfgCapElementsFormulaObjectMapper cfgCapElementsFormulaObjectMapper) {
        this.configFileRepository = configFileRepository;
        this.importConfigIntoDb = importConfigIntoDb;

        this.cfgFinancialBookObjectMapper = cfgFinancialBookObjectMapper;
        this.cfgCompanyObjectMapper = cfgCompanyObjectMapper;
        this.cfgCompanyLinkageObjectMapper = cfgCompanyLinkageObjectMapper;
        this.cfgCompanyDimensionObjectMapper = cfgCompanyDimensionObjectMapper;
        this.cfgCompanyDimensionConsolidationObjectMapper = cfgCompanyDimensionConsolidationObjectMapper;

        this.cfgEntityTypeObjectMapper = cfgEntityTypeObjectMapper;
        this.cfgEntityTypeMappingObjectMapper = cfgEntityTypeMappingObjectMapper;

        this.cfgProductTypeObjectMapper = cfgProductTypeObjectMapper;
        this.cfgProductTypeMappingObjectMapper = cfgProductTypeMappingObjectMapper;

        this.cfgAssetClassObjectMapper = cfgAssetClassObjectMapper;
        this.cfgAssetClassMappingObjectMapper = cfgAssetClassMappingObjectMapper;

        this.cfgNonPerformingMappingObjectMapper = cfgNonPerformingMappingObjectMapper;

        this.cfgAgencyEligibilityObjectMapper = cfgAgencyEligibilityObjectMapper;

        this.cfgRatingObjectMapper = cfgRatingObjectMapper;

        this.cfgCreditMeasureObjectMapper = cfgCreditMeasureObjectMapper;

        this.cfgRiskWeightMappingObjectMapper = cfgRiskWeightMappingObjectMapper;

        this.cfgCcfMappingObjectMapper = cfgCcfMappingObjectMapper;

        this.cfgAddOnObjectMapper = cfgAddOnObjectMapper;

        this.cfgCrmEligibilityObjectMapper = cfgCrmEligibilityObjectMapper;
        this.cfgCrmHaircutObjectMapper = cfgCrmHaircutObjectMapper;

        this.cfgReclassObjectMapper = cfgReclassObjectMapper;
        this.cfgReclassCheckDefObjectMapper = cfgReclassCheckDefObjectMapper;
        this.cfgReclassCheckTypeObjectMapper = cfgReclassCheckTypeObjectMapper;
        this.cfgMktProductTypeObjectMapper = cfgMktProductTypeObjectMapper;
        this.cfgMktProductMappingObjectMapper = cfgMktProductMappingObjectMapper;
        this.cfgMktAssetClassObjectMapper = cfgMktAssetClassObjectMapper;
        this.cfgMktAssetClassMappingObjectMapper = cfgMktAssetClassMappingObjectMapper;
        this.cfgMktIrrSpcRiskObjectMapper = cfgMktIrrSpcRiskObjectMapper;
        this.cfgMktIrrGnrRiskObjectMapper = cfgMktIrrGnrRiskObjectMapper;
        this.cfgMktIrrGnrBandObjectMapper = cfgMktIrrGnrBandObjectMapper;
        this.cfgMktIrrGnrIntraObjectMapper = cfgMktIrrGnrIntraObjectMapper;
        this.cfgMktIrrGnrInterObjectMapper = cfgMktIrrGnrInterObjectMapper;
        this.cfgMktEqtSpcObjectMapper = cfgMktEqtSpcObjectMapper;
        this.cfgMktEqtGnrObjectMapper = cfgMktEqtGnrObjectMapper;
        this.cfgMktComDrtObjectMapper = cfgMktComDrtObjectMapper;
        this.cfgMktComOthObjectMapper = cfgMktComOthObjectMapper;
        this.cfgMktFxObjectMapper = cfgMktFxObjectMapper;

        this.cfgOpsProductTypeObjectMapper = cfgOpsProductTypeObjectMapper;
        this.cfgOpsProductTypeMappingObjectMapper = cfgOpsProductTypeMappingObjectMapper;
        this.cfgOpsFormulaObjectMapper = cfgOpsFormulaObjectMapper;
        this.cfgOpsRiskObjectMapper = cfgOpsRiskObjectMapper;

        this.cfgCapElementsObjectMapper = cfgCapElementsObjectMapper;
        this.cfgCapElementsTypeObjectMapper = cfgCapElementsTypeObjectMapper;
        this.cfgCapElementsLimitObjectMapper = cfgCapElementsLimitObjectMapper;
        this.cfgCapElementsMappingObjectMapper = cfgCapElementsMappingObjectMapper;
        this.cfgCapElementsFormulaObjectMapper = cfgCapElementsFormulaObjectMapper;
    }

    @Override
    public void exportConfigurationFromDbIntoFile(Long id) {
        log.info("Starting export for configFile with id : {}", id);
        long start = System.currentTimeMillis();

        ConfigFile configFile = configFileRepository.findOne(id);

        try {
            FileInputStream file = new FileInputStream(new File(URLDecoder.decode(configFile.getFilePath(), CharEncoding.UTF_8)));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet financialBookSheet = workbook.getSheet(FINANCIAL_BOOK);
            cfgFinancialBookObjectMapper.importData(financialBookSheet);

            XSSFSheet companySheet = workbook.getSheet(COMPANY);
            cfgCompanyObjectMapper.importData(companySheet);

            XSSFSheet companyLinkageSheet = workbook.getSheet(COMPANY_LINKAGE);
            cfgCompanyLinkageObjectMapper.importData(companyLinkageSheet);

            XSSFSheet companyDimensionSheet = workbook.getSheet(COMPANY_DIMENSION);
            cfgCompanyDimensionObjectMapper.importData(companyDimensionSheet);

            XSSFSheet companyDimensionConsolidationSheet = workbook.getSheet(COMPANY_DIMENSION_CONSOLIDATION);
            cfgCompanyDimensionConsolidationObjectMapper.importData(companyDimensionConsolidationSheet);

            XSSFSheet entityTypeSheet = workbook.getSheet(ENTITY_TYPE);
            cfgEntityTypeObjectMapper.importData(entityTypeSheet);

            XSSFSheet entityTypeMappingSheet = workbook.getSheet(ENTITY_TYPE_MAPPING);
            cfgEntityTypeMappingObjectMapper.importData(entityTypeMappingSheet);

            XSSFSheet productTypeSheet = workbook.getSheet(PRODUCT_TYPE);
            cfgProductTypeObjectMapper.importData(productTypeSheet);

            XSSFSheet productTypeMappingSheet = workbook.getSheet(PRODUCT_TYPE_MAPPING);
            cfgProductTypeMappingObjectMapper.importData(productTypeMappingSheet);

            XSSFSheet assetClassSheet = workbook.getSheet(ASSET_CLASS);
            cfgAssetClassObjectMapper.importData(assetClassSheet);

            XSSFSheet assetClassMappingSheet = workbook.getSheet(ASSET_CLASS_MAPPING);
            cfgAssetClassMappingObjectMapper.importCfgAssetClassMapping(assetClassMappingSheet);

            XSSFSheet nonPerformingMappingSheet = workbook.getSheet(NON_PERFORMING_MAPPING);
            cfgNonPerformingMappingObjectMapper.importData(nonPerformingMappingSheet);

            XSSFSheet agencyEligibilitySheet = workbook.getSheet(AGENCY_ELIGIBILITY);
            cfgAgencyEligibilityObjectMapper.importData(agencyEligibilitySheet);

            XSSFSheet ratingSheet = workbook.getSheet(RATING);
            cfgRatingObjectMapper.importData(ratingSheet);

            XSSFSheet creditMeasureSheet = workbook.getSheet(CREDIT_MEASURE);
            cfgCreditMeasureObjectMapper.importData(creditMeasureSheet);

            XSSFSheet riskWeightMappingSheet = workbook.getSheet(RISK_WEIGHT_MAPPING);
            cfgRiskWeightMappingObjectMapper.importData(riskWeightMappingSheet);

            XSSFSheet ccfMappingSheet = workbook.getSheet(CCF_MAPPING);
            cfgCcfMappingObjectMapper.importData(ccfMappingSheet);

            XSSFSheet addOnSheet = workbook.getSheet(ADD_ON);
            cfgAddOnObjectMapper.importData(addOnSheet);

            XSSFSheet crmEligibilitySheet = workbook.getSheet(CRM_ELIGIBILITY);
            cfgCrmEligibilityObjectMapper.importData(crmEligibilitySheet);

            XSSFSheet crmHaircutSheet = workbook.getSheet(CRM_HAIRCUT);
            cfgCrmHaircutObjectMapper.importData(crmHaircutSheet);

            XSSFSheet reclassSheet = workbook.getSheet(RECLASS);
            cfgReclassObjectMapper.importData(reclassSheet);

            XSSFSheet reclassCheckDefSheet = workbook.getSheet(RECLASS_CHECK_DEF);
            cfgReclassCheckDefObjectMapper.importData(reclassCheckDefSheet);

            XSSFSheet reclassCheckTypeSheet = workbook.getSheet(RECLASS_CHECK_TYPE);
            cfgReclassCheckTypeObjectMapper.importData(reclassCheckTypeSheet);

            XSSFSheet mktProductTypeSheet = workbook.getSheet(MKT_PRODUCT_TYPE);
            cfgMktProductTypeObjectMapper.importData(mktProductTypeSheet);

            XSSFSheet mktProductMappingSheet = workbook.getSheet(MKT_PRODUCT_MAPPING);
            cfgMktProductMappingObjectMapper.importData(mktProductMappingSheet);

            XSSFSheet mktAssetClassSheet = workbook.getSheet(MKT_ASSET_CLASS);
            cfgMktAssetClassObjectMapper.importData(mktAssetClassSheet);

            XSSFSheet mktAssetClassMappingSheet = workbook.getSheet(MKT_ASSET_CLASS_MAPPING);
            cfgMktAssetClassMappingObjectMapper.importData(mktAssetClassMappingSheet);

            XSSFSheet mktIrrSpcRiskSheet = workbook.getSheet(MKT_IRR_SPC_RISK);
            cfgMktIrrSpcRiskObjectMapper.importData(mktIrrSpcRiskSheet);

            XSSFSheet mktIrrGnrRiskSheet = workbook.getSheet(MKT_IRR_GNR_RISK);
            cfgMktIrrGnrRiskObjectMapper.importData(mktIrrGnrRiskSheet);

            XSSFSheet mktIrrGnrBandSheet = workbook.getSheet(MKT_IRR_GNR_BAND);
            cfgMktIrrGnrBandObjectMapper.importData(mktIrrGnrBandSheet);

            XSSFSheet mktIrrGnrIntraSheet = workbook.getSheet(MKT_IRR_GNR_INTRA);
            cfgMktIrrGnrIntraObjectMapper.importData(mktIrrGnrIntraSheet);

            XSSFSheet mktIrrGnrInterSheet = workbook.getSheet(MKT_IRR_GNR_INTER);
            cfgMktIrrGnrInterObjectMapper.importData(mktIrrGnrInterSheet);

            XSSFSheet mktEqtSpcSheet = workbook.getSheet(MKT_EQT_SPC);
            cfgMktEqtSpcObjectMapper.importData(mktEqtSpcSheet);

            XSSFSheet mktEqtGnrSheet = workbook.getSheet(MKT_EQT_GNR);
            cfgMktEqtGnrObjectMapper.importData(mktEqtGnrSheet);

            XSSFSheet mktComDrtSheet = workbook.getSheet(MKT_COM_DRT);
            cfgMktComDrtObjectMapper.importData(mktComDrtSheet);

            XSSFSheet mktComOthSheet = workbook.getSheet(MKT_COM_OTH);
            cfgMktComOthObjectMapper.importData(mktComOthSheet);

            XSSFSheet mktFxSheet = workbook.getSheet(MKT_FX);
            cfgMktFxObjectMapper.importData(mktFxSheet);

            XSSFSheet opsProductTypeSheet = workbook.getSheet(OPS_PRODUCT_TYPE);
            cfgOpsProductTypeObjectMapper.importData(opsProductTypeSheet);

            XSSFSheet opsProductTypeMappingSheet = workbook.getSheet(OPS_PRODUCT_TYPE_MAPPING);
            cfgOpsProductTypeMappingObjectMapper.importData(opsProductTypeMappingSheet);

            XSSFSheet opsFormulaSheet = workbook.getSheet(OPS_FORMULA);
            cfgOpsFormulaObjectMapper.importData(opsFormulaSheet);

            XSSFSheet opsRiskSheet = workbook.getSheet(OPS_RISK);
            cfgOpsRiskObjectMapper.importData(opsRiskSheet);

            XSSFSheet capElementsSheet = workbook.getSheet(CAP_ELEMENTS);
            cfgCapElementsObjectMapper.importData(capElementsSheet);

            XSSFSheet capElementsTypeSheet = workbook.getSheet(CAP_ELEMENTS_TYPE);
            cfgCapElementsTypeObjectMapper.importData(capElementsTypeSheet);

            XSSFSheet capElementsLimitSheet = workbook.getSheet(CAP_ELEMENTS_LIMIT);
            cfgCapElementsLimitObjectMapper.importData(capElementsLimitSheet);

            XSSFSheet capElementsMappingSheet = workbook.getSheet(CAP_ELEMENTS_MAPPING);
            cfgCapElementsMappingObjectMapper.importData(capElementsMappingSheet);

            XSSFSheet capElementsFormulaSheet = workbook.getSheet(CAP_ELEMENTS_FORMULA);
            cfgCapElementsFormulaObjectMapper.importData(capElementsFormulaSheet);

            file.close();

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(configFile.getFilePath()));
            workbook.write(out);
            out.close();

        } catch (Exception e) {
            log.error("Error exporting configuration from db into configFile : {}", configFile.toString(), e);
        }

        log.info("Export for configFile : {} finished in {} ms", configFile.toString(), System.currentTimeMillis() - start);
    }

    @Override
    @Transactional
    public ConfigFile importConfigurationFromFileIntoDb(ConfigFile configFile) throws Exception {
        log.info("Starting import for configFile : {}", configFile.toString());
        long start = System.currentTimeMillis();

        try {
            FileInputStream file = new FileInputStream(new File(configFile.getFilePath()));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet financialBookSheet = workbook.getSheet(FINANCIAL_BOOK);
            List<CfgFinancialBook> cfgFinancialBookList = cfgFinancialBookObjectMapper.extractData(financialBookSheet);
            importConfigIntoDb.importCfgFinancialBook(cfgFinancialBookList);

            XSSFSheet companySheet = workbook.getSheet(COMPANY);
            List<CfgCompany> cfgCompaniesList = cfgCompanyObjectMapper.extractData(companySheet);
            importConfigIntoDb.importCfgCompaniesList(cfgCompaniesList);

            XSSFSheet companyLinkageSheet = workbook.getSheet(COMPANY_LINKAGE);
            List<CfgCompanyLinkage> cfgCompaniesLinkageList = cfgCompanyLinkageObjectMapper.extractData(companyLinkageSheet);
            importConfigIntoDb.importCfgCompanyLinkage(cfgCompaniesLinkageList);

            XSSFSheet companyDimensionSheet = workbook.getSheet(COMPANY_DIMENSION);
            List<CfgCompanyDimension> cfgCompanyDimensions = cfgCompanyDimensionObjectMapper.extractData(companyDimensionSheet);
            importConfigIntoDb.importCfgCompanyDimensions(cfgCompanyDimensions);

            XSSFSheet companyDimensionConsolidationSheet = workbook.getSheet(COMPANY_DIMENSION_CONSOLIDATION);
            List<CfgCompanyDimensionConsolidation> cfgCompanyDimensionConsolidations = cfgCompanyDimensionConsolidationObjectMapper.extractData(companyDimensionConsolidationSheet);
            importConfigIntoDb.importCfgCompanyDimensionConsolidations(cfgCompanyDimensionConsolidations);

            XSSFSheet entityTypeSheet = workbook.getSheet(ENTITY_TYPE);
            List<CfgEntityType> cfgEntityTypes = cfgEntityTypeObjectMapper.extractData(entityTypeSheet);
            importConfigIntoDb.importCfgEntityTypes(cfgEntityTypes);

            XSSFSheet entityTypeMappingSheet = workbook.getSheet(ENTITY_TYPE_MAPPING);
            List<CfgEntityTypeMapping> cfgEntityTypeMappings = cfgEntityTypeMappingObjectMapper.extractData(entityTypeMappingSheet);
            importConfigIntoDb.importCfgEntityTypeMappings(cfgEntityTypeMappings);

            XSSFSheet productTypeSheet = workbook.getSheet(PRODUCT_TYPE);
            List<CfgProductType> cfgProductTypes = cfgProductTypeObjectMapper.extractData(productTypeSheet);
            importConfigIntoDb.importCfgProductTypes(cfgProductTypes);

            XSSFSheet productTypeMappingSheet = workbook.getSheet(PRODUCT_TYPE_MAPPING);
            List<CfgProductTypeMapping> cfgProductTypeMappings = cfgProductTypeMappingObjectMapper.extractData(productTypeMappingSheet);
            importConfigIntoDb.importCfgProductTypeMappings(cfgProductTypeMappings);

            XSSFSheet assetClassSheet = workbook.getSheet(ASSET_CLASS);
            List<CfgAssetClass> cfgAssetClass = cfgAssetClassObjectMapper.extractData(assetClassSheet);
            importConfigIntoDb.importCfgAssetClass(cfgAssetClass);

            XSSFSheet assetClassMappingSheet = workbook.getSheet(ASSET_CLASS_MAPPING);
            List<CfgAssetClassMapping> cfgAssetClassMappings = cfgAssetClassMappingObjectMapper.extractData(assetClassMappingSheet);
            importConfigIntoDb.importCfgAssetClassMappings(cfgAssetClassMappings);

            XSSFSheet nonPerformingMappingSheet = workbook.getSheet(NON_PERFORMING_MAPPING);
            List<CfgNonPerformingMapping> cfgNonPerformingMappings = cfgNonPerformingMappingObjectMapper.extractData(nonPerformingMappingSheet);
            importConfigIntoDb.importCfgNonPerformingMappings(cfgNonPerformingMappings);

            XSSFSheet agencyEligibilitySheet = workbook.getSheet(AGENCY_ELIGIBILITY);
            List<CfgAgencyEligibility> cfgAgencyEligibility = cfgAgencyEligibilityObjectMapper.extractData(agencyEligibilitySheet);
            importConfigIntoDb.importCfgAgencyEligibility(cfgAgencyEligibility);

            XSSFSheet ratingSheet = workbook.getSheet(RATING);
            List<CfgRating> cfgRatings = cfgRatingObjectMapper.extractData(ratingSheet);
            importConfigIntoDb.importCfgRatings(cfgRatings);

            XSSFSheet creditMeasureSheet = workbook.getSheet(CREDIT_MEASURE);
            List<CfgCreditMeasure> cfgCreditMeasures = cfgCreditMeasureObjectMapper.extractData(creditMeasureSheet);
            importConfigIntoDb.importCfgCreditMeasures(cfgCreditMeasures);

            XSSFSheet riskWeightMappingSheet = workbook.getSheet(RISK_WEIGHT_MAPPING);
            List<CfgRiskWeightMapping> cfgRiskWeightMappings = cfgRiskWeightMappingObjectMapper.extractData(riskWeightMappingSheet);
            importConfigIntoDb.importCfgRiskWeightMappings(cfgRiskWeightMappings);

            XSSFSheet ccfMappingSheet = workbook.getSheet(CCF_MAPPING);
            List<CfgCcfMapping> cfgCcfMappings = cfgCcfMappingObjectMapper.extractData(ccfMappingSheet);
            importConfigIntoDb.importCfgCcfMappings(cfgCcfMappings);

            XSSFSheet addOnSheet = workbook.getSheet(ADD_ON);
            List<CfgAddOn> cfgAddOns = cfgAddOnObjectMapper.extractData(addOnSheet);
            importConfigIntoDb.importCfgAddOns(cfgAddOns);

            XSSFSheet crmEligibilitySheet = workbook.getSheet(CRM_ELIGIBILITY);
            List<CfgCrmEligibility> cfgCrmEligibilities = cfgCrmEligibilityObjectMapper.extractData(crmEligibilitySheet);
            importConfigIntoDb.importCfgCrmEligibilities(cfgCrmEligibilities);

            XSSFSheet crmHaircutSheet = workbook.getSheet(CRM_HAIRCUT);
            List<CfgCrmHaircut> cfgCrmHaircuts = cfgCrmHaircutObjectMapper.extractData(crmHaircutSheet);
            importConfigIntoDb.importCfgCrmHaircuts(cfgCrmHaircuts);

            XSSFSheet reclassSheet = workbook.getSheet(RECLASS);
            List<CfgReclass> cfgReclasses = cfgReclassObjectMapper.extractData(reclassSheet);
            importConfigIntoDb.importCfgReclasses(cfgReclasses);

            XSSFSheet reclassCheckDefSheet = workbook.getSheet(RECLASS_CHECK_DEF);
            List<CfgReclassCheckDef> cfgReclassCheckDefs = cfgReclassCheckDefObjectMapper.extractData(reclassCheckDefSheet);
            importConfigIntoDb.importCfgReclassCheckDefs(cfgReclassCheckDefs);

            XSSFSheet reclassCheckTypeSheet = workbook.getSheet(RECLASS_CHECK_TYPE);
            List<CfgReclassCheckType> cfgReclassCheckTypes = cfgReclassCheckTypeObjectMapper.extractData(reclassCheckTypeSheet);
            importConfigIntoDb.importCfgReclassCheckTypes(cfgReclassCheckTypes);

            XSSFSheet mktProductTypeSheet = workbook.getSheet(MKT_PRODUCT_TYPE);
            List<CfgMktProductType> cfgMktProductTypes = cfgMktProductTypeObjectMapper.extractData(mktProductTypeSheet);
            importConfigIntoDb.importCfgMktProductTypes(cfgMktProductTypes);

            XSSFSheet mktProductMappingSheet = workbook.getSheet(MKT_PRODUCT_MAPPING);
            List<CfgMktProductMapping> cfgMktProductMappings = cfgMktProductMappingObjectMapper.extractData(mktProductMappingSheet);
            importConfigIntoDb.importCfgMktProductMappings(cfgMktProductMappings);

            XSSFSheet mktAssetClassSheet = workbook.getSheet(MKT_ASSET_CLASS);
            List<CfgMktAssetClass> cfgMktAssetClasses = cfgMktAssetClassObjectMapper.extractData(mktAssetClassSheet);
            importConfigIntoDb.importCfgMktAssetClasses(cfgMktAssetClasses);

            XSSFSheet mktAssetClassMappingSheet = workbook.getSheet(MKT_ASSET_CLASS_MAPPING);
            List<CfgMktAssetClassMapping> cfgMktAssetClassMappings = cfgMktAssetClassMappingObjectMapper.extractData(mktAssetClassMappingSheet);
            importConfigIntoDb.importCfgMktAssetClassMappings(cfgMktAssetClassMappings);

            XSSFSheet mktIrrSpcRiskSheet = workbook.getSheet(MKT_IRR_SPC_RISK);
            List<CfgMktIrrSpcRisk> cfgMktIrrSpcRisks = cfgMktIrrSpcRiskObjectMapper.extractData(mktIrrSpcRiskSheet);
            importConfigIntoDb.importCfgMktIrrSpcRisks(cfgMktIrrSpcRisks);

            XSSFSheet mktIrrGnrRiskSheet = workbook.getSheet(MKT_IRR_GNR_RISK);
            List<CfgMktIrrGnrRisk> cfgMktIrrGnrRisks = cfgMktIrrGnrRiskObjectMapper.extractData(mktIrrGnrRiskSheet);
            importConfigIntoDb.importCfgMktIrrGnrRisks(cfgMktIrrGnrRisks);

            XSSFSheet mktIrrGnrBandSheet = workbook.getSheet(MKT_IRR_GNR_BAND);
            List<CfgMktIrrGnrBand> cfgMktIrrGnrBands = cfgMktIrrGnrBandObjectMapper.extractData(mktIrrGnrBandSheet);
            importConfigIntoDb.importCfgMktIrrGnrBands(cfgMktIrrGnrBands);

            XSSFSheet mktIrrGnrIntraSheet = workbook.getSheet(MKT_IRR_GNR_INTRA);
            List<CfgMktIrrGnrIntra> cfgMktIrrGnrIntras = cfgMktIrrGnrIntraObjectMapper.extractData(mktIrrGnrIntraSheet);
            importConfigIntoDb.importCfgMktIrrGnrIntras(cfgMktIrrGnrIntras);

            XSSFSheet mktIrrGnrInterSheet = workbook.getSheet(MKT_IRR_GNR_INTER);
            List<CfgMktIrrGnrInter> cfgMktIrrGnrInters = cfgMktIrrGnrInterObjectMapper.extractData(mktIrrGnrInterSheet);
            importConfigIntoDb.importCfgMktIrrGnrInters(cfgMktIrrGnrInters);

            XSSFSheet mktEqtSpcSheet = workbook.getSheet(MKT_EQT_SPC);
            List<CfgMktEqtSpc> cfgMktEqtSpcs = cfgMktEqtSpcObjectMapper.extractData(mktEqtSpcSheet);
            importConfigIntoDb.importCfgMktEqtSpcs(cfgMktEqtSpcs);

            XSSFSheet mktEqtGnrSheet = workbook.getSheet(MKT_EQT_GNR);
            List<CfgMktEqtGnr> cfgMktEqtGnrs = cfgMktEqtGnrObjectMapper.extractData(mktEqtGnrSheet);
            importConfigIntoDb.importCfgMktEqtGnrs(cfgMktEqtGnrs);

            XSSFSheet mktComDrtSheet = workbook.getSheet(MKT_COM_DRT);
            List<CfgMktComDrt> cfgMktComDrts = cfgMktComDrtObjectMapper.extractData(mktComDrtSheet);
            importConfigIntoDb.importCfgMktComDrts(cfgMktComDrts);

            XSSFSheet mktComOthSheet = workbook.getSheet(MKT_COM_OTH);
            List<CfgMktComOth> cfgMktComOths = cfgMktComOthObjectMapper.extractData(mktComOthSheet);
            importConfigIntoDb.importCfgMktComOths(cfgMktComOths);

            XSSFSheet mktFxSheet = workbook.getSheet(MKT_FX);
            List<CfgMktFx> cfgMktFxes = cfgMktFxObjectMapper.extractData(mktFxSheet);
            importConfigIntoDb.importCfgMktFxes(cfgMktFxes);

            XSSFSheet opsProductTypeSheet = workbook.getSheet(OPS_PRODUCT_TYPE);
            List<CfgOpsProductType> cfgOpsProductTypes = cfgOpsProductTypeObjectMapper.extractData(opsProductTypeSheet);
            importConfigIntoDb.importCfgOpsProductTypes(cfgOpsProductTypes);

            XSSFSheet opsProductTypeMappingSheet = workbook.getSheet(OPS_PRODUCT_TYPE_MAPPING);
            List<CfgOpsProductTypeMapping> cfgOpsProductTypeMappings = cfgOpsProductTypeMappingObjectMapper.extractData(opsProductTypeMappingSheet);
            importConfigIntoDb.importCfgOpsProductTypeMappings(cfgOpsProductTypeMappings);

            XSSFSheet opsFormulaSheet = workbook.getSheet(OPS_FORMULA);
            List<CfgOpsFormula> cfgOpsFormulas = cfgOpsFormulaObjectMapper.extractData(opsFormulaSheet);
            importConfigIntoDb.importCfgOpsFormulas(cfgOpsFormulas);

            XSSFSheet opsRiskSheet = workbook.getSheet(OPS_RISK);
            List<CfgOpsRisk> cfgOpsRisks = cfgOpsRiskObjectMapper.extractData(opsRiskSheet);
            importConfigIntoDb.importCfgOpsRisks(cfgOpsRisks);

            XSSFSheet capElementsSheet = workbook.getSheet(CAP_ELEMENTS);
            List<CfgCapElements> cfgCapElementses = cfgCapElementsObjectMapper.extractData(capElementsSheet);
            importConfigIntoDb.importCfgCapElementses(cfgCapElementses);

            XSSFSheet capElementsTypeSheet = workbook.getSheet(CAP_ELEMENTS_TYPE);
            List<CfgCapElementsType> cfgCapElementsTypes = cfgCapElementsTypeObjectMapper.extractData(capElementsTypeSheet);
            importConfigIntoDb.importCfgCapElementsTypes(cfgCapElementsTypes);

            XSSFSheet capElementsLimitSheet = workbook.getSheet(CAP_ELEMENTS_LIMIT);
            List<CfgCapElementsLimit> cfgCapElementsLimits = cfgCapElementsLimitObjectMapper.extractData(capElementsLimitSheet);
            importConfigIntoDb.importCfgCapElementsLimits(cfgCapElementsLimits);

            XSSFSheet capElementsMappingSheet = workbook.getSheet(CAP_ELEMENTS_MAPPING);
            List<CfgCapElementsMapping> cfgCapElementsMappings = cfgCapElementsMappingObjectMapper.extractData(capElementsMappingSheet);
            importConfigIntoDb.importCfgCapElementsMappings(cfgCapElementsMappings);

            XSSFSheet capElementsFormulaSheet = workbook.getSheet(CAP_ELEMENTS_FORMULA);
            List<CfgCapElementsFormula> cfgCapElementsFormulas = cfgCapElementsFormulaObjectMapper.extractData(capElementsFormulaSheet);
            importConfigIntoDb.importCfgCapElementsFormulas(cfgCapElementsFormulas);

            file.close();

            ConfigFile oneByStatusCurrent = configFileRepository.findOneByStatus(CURRENT);
            if (oneByStatusCurrent != null) {
                oneByStatusCurrent.setStatus(NULL);
                configFileRepository.save(oneByStatusCurrent);
            }

            configFile.setStatus(CURRENT);
            ConfigFile savedConfig = configFileRepository.save(configFile);

            log.info("Import for configFile : {} finished in {} ms", configFile.toString(), System.currentTimeMillis() - start);

            return savedConfig;
        } catch (FileNotFoundException e) {
            log.error("File not found!", e);
            throw new Exception(e);
        } catch (IOException e) {
            log.error("IO Exception!", e);
            throw new Exception(e);
        }
    }
}

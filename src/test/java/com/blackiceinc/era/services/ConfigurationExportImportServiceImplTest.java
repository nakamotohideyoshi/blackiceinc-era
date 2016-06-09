package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.*;
import com.blackiceinc.era.persistence.erau.repository.*;
import com.blackiceinc.era.services.excelmapper.*;
import org.apache.commons.codec.CharEncoding;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by tmanev on 3/3/2016.
 */
public class ConfigurationExportImportServiceImplTest {

    private ConfigurationExportImportService configurationExportImportService;

    @Mock
    private ConfigFileRepository configFileRepository;

    @Mock
    private CfgFinancialBookRepository cfgFinancialBookRepository;
    @Mock
    private CfgCompanyRepository cfgCompanyRepository;
    @Mock
    private CfgCompanyLinkageRepository cfgCompanyLinkageRepository;
    @Mock
    private CfgCompanyDimensionRepository cfgCompanyDimensionRepository;
    @Mock
    private CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository;

    @Mock
    private CfgEntityTypeRepository cfgEntityTypeRepository;
    @Mock
    private CfgEntityTypeMappingRepository cfgEntityTypeMappingRepository;

    @Mock
    private CfgProductTypeRepository cfgProductTypeRepository;
    @Mock
    private CfgProductTypeMappingRepository cfgProductTypeMappingRepository;

    @Mock
    private CfgAssetClassRepository cfgAssetClassRepository;
    @Mock
    private CfgAssetClassMappingRepository cfgAssetClassMappingRepository;

    @Mock
    private CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository;

    @Mock
    private CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository;

    @Mock
    private CfgRatingRepository cfgRatingRepository;

    @Mock
    private CfgCreditMeasureRepository cfgCreditMeasureRepository;

    @Mock
    private CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository;

    @Mock
    private CfgCcfMappingRepository cfgCcfMappingRepository;

    @Mock
    private CfgAddOnRepository cfgAddOnRepository;

    @Mock
    private CfgCrmEligibilityRepository cfgCrmEligibilityRepository;

    @Mock
    private CfgCrmHaircutRepository cfgCrmHaircutRepository;

    @Mock
    private CfgReclassRepository cfgReclassRepository;
    @Mock
    private CfgReclassCheckDefRepository cfgReclassCheckDefRepository;
    @Mock
    private CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository;

    @Mock
    private CfgMktProductTypeRepository cfgMktProductTypeRepository;
    @Mock
    private CfgMktProductMappingRepository cfgMktProductMappingRepository;
    @Mock
    private CfgMktAssetClassRepository cfgMktAssetClassRepository;
    @Mock
    private CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository;
    @Mock
    private CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository;
    @Mock
    private CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository;
    @Mock
    private CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository;
    @Mock
    private CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;
    @Mock
    private CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;
    @Mock
    private CfgMktEqtSpcRepository cfgMktEqtSpcRepository;
    @Mock
    private CfgMktEqtGnrRepository cfgMktEqtGnrRepository;
    @Mock
    private CfgMktComDrtRepository cfgMktComDrtRepository;
    @Mock
    private CfgMktComOthRepository cfgMktComOthRepository;
    @Mock
    private CfgMktFxRepository cfgMktFxRepository;

    @Mock
    private CfgOpsProductTypeRepository cfgOpsProductTypeRepository;
    @Mock
    private CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository;
    @Mock
    private CfgOpsFormulaRepository cfgOpsFormulaRepository;
    @Mock
    private CfgOpsRiskRepository cfgOpsRiskRepository;

    @Mock
    private CfgCapElementsRepository cfgCapElementsRepository;
    @Mock
    private CfgCapElementsTypeRepository cfgCapElementsTypeRepository;
    @Mock
    private CfgCapElementsLimitRepository cfgCapElementsLimitRepository;
    @Mock
    private CfgCapElementsMappingRepository cfgCapElementsMappingRepository;
    @Mock
    private CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository;
    @Mock
    private CfgOtherAssetsRepository cfgOtherAssetsRepository;

    @Mock
    private CfgFxProdTypeRepository cfgFxProdTypeRepository;

    @Mock
    private CfgFxProdMappingRepository cfgFxProdMappingRepository;


    private CfgFinancialBookObjectMapper cfgFinancialBookObjectMapper;
    private CfgCompanyObjectMapper cfgCompanyObjectMapper;
    private CfgCompanyLinkageObjectMapper cfgCompanyLinkageObjectMapper;
    private CfgCompanyDimensionObjectMapper cfgCompanyDimensionObjectMapper;
    private CfgCompanyDimensionConsolidationObjectMapper cfgCompanyDimensionConsolidationObjectMapper;

    private CfgEntityTypeObjectMapper cfgEntityTypeObjectMapper;
    private CfgEntityTypeMappingObjectMapper cfgEntityTypeMappingObjectMapper;

    private CfgProductTypeObjectMapper cfgProductTypeObjectMapper;
    private CfgProductTypeMappingObjectMapper cfgProductTypeMappingObjectMapper;

    private CfgAssetClassObjectMapper cfgAssetClassObjectMapper;
    private CfgAssetClassMappingObjectMapper cfgAssetClassMappingObjectMapper;

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
    private CfgOtherAssetsObjectMapper cfgOtherAssetsObjectMapper;

    private CfgFxProdTypeObjectMapper cfgFxProdTypeObjectMapper;
    private CfgFxProdMappingObjectMapper cfgFxProdMappingObjectMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        cfgFinancialBookObjectMapper = new CfgFinancialBookObjectMapper(cfgFinancialBookRepository);
        cfgCompanyObjectMapper = new CfgCompanyObjectMapper(cfgCompanyRepository);
        cfgCompanyLinkageObjectMapper = new CfgCompanyLinkageObjectMapper(cfgCompanyLinkageRepository);
        cfgCompanyDimensionObjectMapper = new CfgCompanyDimensionObjectMapper(cfgCompanyDimensionRepository);
        cfgCompanyDimensionConsolidationObjectMapper = new CfgCompanyDimensionConsolidationObjectMapper(cfgCompanyDimensionConsolidationRepository);

        cfgEntityTypeObjectMapper = new CfgEntityTypeObjectMapper(cfgEntityTypeRepository);
        cfgEntityTypeMappingObjectMapper = new CfgEntityTypeMappingObjectMapper(cfgEntityTypeMappingRepository);

        cfgProductTypeObjectMapper = new CfgProductTypeObjectMapper(cfgProductTypeRepository);
        cfgProductTypeMappingObjectMapper = new CfgProductTypeMappingObjectMapper(cfgProductTypeMappingRepository);

        cfgAssetClassObjectMapper = new CfgAssetClassObjectMapper(cfgAssetClassRepository);
        cfgAssetClassMappingObjectMapper = new CfgAssetClassMappingObjectMapper(cfgAssetClassMappingRepository);

        cfgNonPerformingMappingObjectMapper = new CfgNonPerformingMappingObjectMapper(cfgNonPerformingMappingRepository);

        cfgAgencyEligibilityObjectMapper = new CfgAgencyEligibilityObjectMapper(cfgAgencyEligibilityRepository);

        cfgRatingObjectMapper = new CfgRatingObjectMapper(cfgRatingRepository);

        cfgCreditMeasureObjectMapper = new CfgCreditMeasureObjectMapper(cfgCreditMeasureRepository);

        cfgRiskWeightMappingObjectMapper = new CfgRiskWeightMappingObjectMapper(cfgRiskWeightMappingRepository);

        cfgCcfMappingObjectMapper = new CfgCcfMappingObjectMapper(cfgCcfMappingRepository);

        cfgAddOnObjectMapper = new CfgAddOnObjectMapper(cfgAddOnRepository);

        cfgCrmEligibilityObjectMapper = new CfgCrmEligibilityObjectMapper(cfgCrmEligibilityRepository);
        cfgCrmHaircutObjectMapper = new CfgCrmHaircutObjectMapper((cfgCrmHaircutRepository));

        cfgReclassObjectMapper = new CfgReclassObjectMapper(cfgReclassRepository);
        cfgReclassCheckDefObjectMapper = new CfgReclassCheckDefObjectMapper(cfgReclassCheckDefRepository);
        cfgReclassCheckTypeObjectMapper = new CfgReclassCheckTypeObjectMapper((cfgReclassCheckTypeRepository));

        cfgMktProductTypeObjectMapper = new CfgMktProductTypeObjectMapper(cfgMktProductTypeRepository);
        cfgMktProductMappingObjectMapper = new CfgMktProductMappingObjectMapper(cfgMktProductMappingRepository);
        cfgMktAssetClassObjectMapper = new CfgMktAssetClassObjectMapper(cfgMktAssetClassRepository);
        cfgMktAssetClassMappingObjectMapper = new CfgMktAssetClassMappingObjectMapper(cfgMktAssetClassMappingRepository);
        cfgMktIrrSpcRiskObjectMapper = new CfgMktIrrSpcRiskObjectMapper(cfgMktIrrSpcRiskRepository);
        cfgMktIrrGnrRiskObjectMapper = new CfgMktIrrGnrRiskObjectMapper(cfgMktIrrGnrRiskRepository);
        cfgMktIrrGnrBandObjectMapper = new CfgMktIrrGnrBandObjectMapper(cfgMktIrrGnrBandRepository);
        cfgMktIrrGnrIntraObjectMapper = new CfgMktIrrGnrIntraObjectMapper(cfgMktIrrGnrIntraRepository);
        cfgMktIrrGnrInterObjectMapper = new CfgMktIrrGnrInterObjectMapper(cfgMktIrrGnrInterRepository);
        cfgMktEqtSpcObjectMapper = new CfgMktEqtSpcObjectMapper(cfgMktEqtSpcRepository);
        cfgMktEqtGnrObjectMapper = new CfgMktEqtGnrObjectMapper(cfgMktEqtGnrRepository);
        cfgMktComDrtObjectMapper = new CfgMktComDrtObjectMapper(cfgMktComDrtRepository);
        cfgMktComOthObjectMapper = new CfgMktComOthObjectMapper(cfgMktComOthRepository);
        cfgMktFxObjectMapper = new CfgMktFxObjectMapper(cfgMktFxRepository);

        cfgOpsProductTypeObjectMapper = new CfgOpsProductTypeObjectMapper(cfgOpsProductTypeRepository);
        cfgOpsProductTypeMappingObjectMapper = new CfgOpsProductTypeMappingObjectMapper(cfgOpsProductTypeMappingRepository);
        cfgOpsFormulaObjectMapper = new CfgOpsFormulaObjectMapper(cfgOpsFormulaRepository);
        cfgOpsRiskObjectMapper = new CfgOpsRiskObjectMapper(cfgOpsRiskRepository);

        cfgCapElementsObjectMapper = new CfgCapElementsObjectMapper(cfgCapElementsRepository);
        cfgCapElementsTypeObjectMapper = new CfgCapElementsTypeObjectMapper(cfgCapElementsTypeRepository);
        cfgCapElementsLimitObjectMapper = new CfgCapElementsLimitObjectMapper(cfgCapElementsLimitRepository);
        cfgCapElementsMappingObjectMapper = new CfgCapElementsMappingObjectMapper(cfgCapElementsMappingRepository);
        cfgCapElementsFormulaObjectMapper = new CfgCapElementsFormulaObjectMapper(cfgCapElementsFormulaRepository);
        cfgOtherAssetsObjectMapper = new CfgOtherAssetsObjectMapper(cfgOtherAssetsRepository);

        cfgFxProdTypeObjectMapper = new CfgFxProdTypeObjectMapper(cfgFxProdTypeRepository);
        cfgFxProdMappingObjectMapper = new CfgFxProdMappingObjectMapper(cfgFxProdMappingRepository);


        configurationExportImportService = new ConfigurationExportImportServiceImpl();
    }

    @Test
    public void testExportConfiguration() throws Exception {
        URL resource = this.getClass().getResource("/VIB_ERA_Configuration v1.11.xlsx");

        mockConfigFile(resource);
        ConfigFile configFile = new ConfigFile();
        configFile.setId(1L);
        configurationExportImportService.exportConfigurationFromDbIntoExcel(configFile);
    }

    @Test
    public void testImportConfiguration() throws Exception {
        URL resource = getClass().getResource("/VIB_ERA_Configuration v1.11_for_export.xlsx");
        createMocksForImportTest(resource);

        ConfigFile configFile = new ConfigFile();
        configFile.setId(1L);
        configurationExportImportService.importConfigurationFromExcelIntoDb(configFile);
    }

    private void createMocksForImportTest(URL resource) throws UnsupportedEncodingException {
        mockConfigFile(resource);

        mockFinancialBookRepo();

        mockCompanyRepo();
        mockCompanyLinkRepo();
        mockCompanyDimensionRepo();
        mockCompanyDimensionConsolidationRepo();

        mockEntityTypeRepo();
        mockEntityTypeMappingRepo();

        mockProductTypeRepo();
        mockProductTypeMappingRepo();

        mockAssetClassRepo();
        mockAssetClassMappingRepo();

        mockNonPerformingMappingRepo();

        mockAgencyEligibilityRepo();

        mockRatingRepo();

        mockCreditMeasureRepo();

        mockRiskWeightMappingRepo();

        mockCcfMappingRepo();

        mockAddOnRepo();

        mockCrmEligibilityRepo();
        mockCrmHaircutRepo();

        mockReclassRepo();
        mockReclassCheckDefRepo();
        mockReclassCheckTypeRepo();

        mockMktProductTypeRepo();
        mockMktProductMappingRepo();
        mockMktAssetClassRepo();
        mockMktAssetClassMappingRepo();
        mockMktIrrSpcRiskRepo();
        mockMktIrrGnrRiskRepo();
        mockMktIrrGnrBandRepo();
        mockMktIrrGnrIntraRepo();
        mockMktIrrGnrInterRepo();
        mockMktEqtSpcRepo();
        mockMktEqtGnrRepo();
        mockMktComDrtRepo();
        mockMktComOthRepo();
        mockMktFxRepo();

        mockOpsProductTypeRepo();
        mockOpsProductTypeMappingRepo();
        mockOpsFormulaRepo();
        mockOpsRiskRepo();

        mockCapElementsRepo();
        mockCapElementsTypeRepo();
        mockCapElementsLimitRepo();
        mockCapElementsMappingRepo();
        mockCapElementsFormulaRepo();

        mockOtherAssetsRepo();
        mockFxProdTypeRepo();
        mockFxProdMappingRepo();
    }

    private void mockFxProdMappingRepo() {
        List<CfgFxProdMapping> cfgFxProdMappings = new ArrayList<>();

        cfgFxProdMappings.add(new CfgFxProdMapping("gl_code_1", "fx_prod_type_1", "gl_code_desc_1"));
        cfgFxProdMappings.add(new CfgFxProdMapping("gl_code_2", "fx_prod_type_2", "gl_code_desc_2"));

        when(cfgFxProdMappingRepository.findAll()).thenReturn(cfgFxProdMappings);
    }

    private void mockFxProdTypeRepo() {
        List<CfgFxProdType> cfgFxProdTypes = new ArrayList<>();

        cfgFxProdTypes.add(new CfgFxProdType("fx_prod_type_1", "fx_prod_type_desc_1"));
        cfgFxProdTypes.add(new CfgFxProdType("fx_prod_type_1", "fx_prod_type_desc_2"));

        when(cfgFxProdTypeRepository.findAll()).thenReturn(cfgFxProdTypes);
    }

    private void mockOtherAssetsRepo() {
        List<CfgOtherAssets> cfgOtherAssetses = new ArrayList<>();

        cfgOtherAssetses.add(new CfgOtherAssets("gl_code_1", "group_check_1", "heading_1", "gl_desc_1", "era_contract_type_1", "check_criteria_1", 2.5));
        cfgOtherAssetses.add(new CfgOtherAssets("gl_code_1", "group_check_2", "heading_2", "gl_desc_2", "era_contract_type_2", "check_criteria_2", 3.55));

        when(cfgOtherAssetsRepository.findAll()).thenReturn(cfgOtherAssetses);
    }

    private void mockCapElementsFormulaRepo() {
        List<CfgCapElementsFormula> cfgCapElementsMappings = new ArrayList<>();

        cfgCapElementsMappings.add(new CfgCapElementsFormula("cap_elements_1", "description_1", "formula_1"));
        cfgCapElementsMappings.add(new CfgCapElementsFormula("cap_elements_2", "description_2", "formula_2"));

        when(cfgCapElementsFormulaRepository.findAll()).thenReturn(cfgCapElementsMappings);
    }

    private void mockCapElementsMappingRepo() {
        List<CfgCapElementsMapping> cfgCapElementsMappings = new ArrayList<>();

        cfgCapElementsMappings.add(new CfgCapElementsMapping("cap_elements_1", "gl_code_1", "note_1"));
        cfgCapElementsMappings.add(new CfgCapElementsMapping("cap_elements_2", "gl_code_2", "note_2"));

        when(cfgCapElementsMappingRepository.findAll()).thenReturn(cfgCapElementsMappings);
    }

    private void mockCapElementsLimitRepo() {
        List<CfgCapElementsLimit> cfgCapElementsLimits = new ArrayList<>();

        cfgCapElementsLimits.add(new CfgCapElementsLimit("limit_type_1", "operator_1", new Double(0.5), "conso_table_1", "conso_field_1", "conso_field_value_1", "conso_amt_1"));
        cfgCapElementsLimits.add(new CfgCapElementsLimit("limit_type_2", "operator_2", new Double(0.6), "conso_table_2", "conso_field_2", "conso_field_value_2", "conso_amt_2"));

        when(cfgCapElementsLimitRepository.findAll()).thenReturn(cfgCapElementsLimits);
    }

    private void mockCapElementsTypeRepo() {
        List<CfgCapElementsType> cfgCapElementsTypes = new ArrayList<>();

        cfgCapElementsTypes.add(new CfgCapElementsType("cap_element_type_1", "description_1"));
        cfgCapElementsTypes.add(new CfgCapElementsType("cap_element_type_2", "description_2"));

        when(cfgCapElementsTypeRepository.findAll()).thenReturn(cfgCapElementsTypes);
    }

    private void mockCapElementsRepo() {
        List<CfgCapElements> cfgCapElementses = new ArrayList<>();

        cfgCapElementses.add(new CfgCapElements("cap_elements_1", "cap_elements_desc_1", "type_1"));
        cfgCapElementses.add(new CfgCapElements("cap_elements_2", "cap_elements_desc_2", "type_2"));

        when(cfgCapElementsRepository.findAll()).thenReturn(cfgCapElementses);
    }

    private void mockOpsRiskRepo() {
        List<CfgOpsRisk> cfgOpsRisks = new ArrayList<>();

        cfgOpsRisks.add(new CfgOpsRisk("code_1", new Double(0.1)));
        cfgOpsRisks.add(new CfgOpsRisk("code_2", new Double(0.2)));

        when(cfgOpsRiskRepository.findAll()).thenReturn(cfgOpsRisks);
    }

    private void mockOpsFormulaRepo() {
        List<CfgOpsFormula> cfgOpsFormulas = new ArrayList<>();

        cfgOpsFormulas.add(new CfgOpsFormula("basic_indicator_1", "formula_1"));
        cfgOpsFormulas.add(new CfgOpsFormula("basic_indicator_2", "formula_2"));

        when(cfgOpsFormulaRepository.findAll()).thenReturn(cfgOpsFormulas);
    }

    private void mockOpsProductTypeMappingRepo() {
        List<CfgOpsProductTypeMapping> cfgOpsProductTypeMappings = new ArrayList<>();

        cfgOpsProductTypeMappings.add(new CfgOpsProductTypeMapping("ops_product_type_1", "ops_gl_code_1", "ops_vib_code_1", "description_1"));
        cfgOpsProductTypeMappings.add(new CfgOpsProductTypeMapping("ops_product_type_2", "ops_gl_code_2", "ops_vib_code_2", "description_2"));

        when(cfgOpsProductTypeMappingRepository.findAll()).thenReturn(cfgOpsProductTypeMappings);
    }

    private void mockOpsProductTypeRepo() {
        List<CfgOpsProductType> cfgOpsProductTypes = new ArrayList<>();

        cfgOpsProductTypes.add(new CfgOpsProductType("ops_product_type_1", "ops_product_desc_1", "ops_bus_indicator_1"));
        cfgOpsProductTypes.add(new CfgOpsProductType("ops_product_type_2", "ops_product_desc_2", "ops_bus_indicator_2"));

        when(cfgOpsProductTypeRepository.findAll()).thenReturn(cfgOpsProductTypes);
    }

    private void mockMktFxRepo() {
        List<CfgMktFx> cfgMktComOths = new ArrayList<>();

        cfgMktComOths.add(new CfgMktFx("mkt_product_type_1", new Double(0.4)));
        cfgMktComOths.add(new CfgMktFx("mkt_product_type_2", new Double(0.4)));

        when(cfgMktFxRepository.findAll()).thenReturn(cfgMktComOths);
    }

    private void mockMktComOthRepo() {
        List<CfgMktComOth> cfgMktComOths = new ArrayList<>();

        cfgMktComOths.add(new CfgMktComOth("mkt_product_type_1", new Double(0.4)));
        cfgMktComOths.add(new CfgMktComOth("mkt_product_type_2", new Double(0.4)));

        when(cfgMktComOthRepository.findAll()).thenReturn(cfgMktComOths);
    }

    private void mockMktComDrtRepo() {
        List<CfgMktComDrt> cfgMktComDrts = new ArrayList<>();

        cfgMktComDrts.add(new CfgMktComDrt("mkt_product_type_1", new Double(0.4)));
        cfgMktComDrts.add(new CfgMktComDrt("mkt_product_type_2", new Double(0.4)));

        when(cfgMktComDrtRepository.findAll()).thenReturn(cfgMktComDrts);
    }

    private void mockMktEqtGnrRepo() {
        List<CfgMktEqtGnr> cfgMktEqtGnrs = new ArrayList<>();

        cfgMktEqtGnrs.add(new CfgMktEqtGnr("mkt_product_type_1", "underlying_1", new Double(0.4)));
        cfgMktEqtGnrs.add(new CfgMktEqtGnr("mkt_product_type_2", "underlying_2", new Double(0.4)));

        when(cfgMktEqtGnrRepository.findAll()).thenReturn(cfgMktEqtGnrs);
    }

    private void mockMktEqtSpcRepo() {
        List<CfgMktEqtSpc> cfgMktEqtSpcs = new ArrayList<>();

        cfgMktEqtSpcs.add(new CfgMktEqtSpc("mkt_product_type_1", "underlying_1", "diversified_equity_1", "diversified_index_1", "liquid_equity_1", new Double(0.4)));
        cfgMktEqtSpcs.add(new CfgMktEqtSpc("mkt_product_type_2", "underlying_2", "diversified_equity_2", "diversified_index_2", "liquid_equity_2", new Double(0.4)));

        when(cfgMktEqtSpcRepository.findAll()).thenReturn(cfgMktEqtSpcs);
    }

    private void mockMktIrrGnrInterRepo() {
        List<CfgMktIrrGnrInter> cfgMktIrrGnrInters = new ArrayList<>();

        cfgMktIrrGnrInters.add(new CfgMktIrrGnrInter("code_1", "zone_code1_1", "zone_code2_1", new Double(0.4)));
        cfgMktIrrGnrInters.add(new CfgMktIrrGnrInter("code_2", "zone_code1_2", "zone_code2_2", new Double(0.4)));

        when(cfgMktIrrGnrInterRepository.findAll()).thenReturn(cfgMktIrrGnrInters);
    }

    private void mockMktIrrGnrIntraRepo() {
        List<CfgMktIrrGnrIntra> cfgMktIrrGnrIntras = new ArrayList<>();

        cfgMktIrrGnrIntras.add(new CfgMktIrrGnrIntra("code_1", "zone_code_1", new Double(0.4)));
        cfgMktIrrGnrIntras.add(new CfgMktIrrGnrIntra("code_2", "zone_code2", new Double(0.5)));

        when(cfgMktIrrGnrIntraRepository.findAll()).thenReturn(cfgMktIrrGnrIntras);
    }

    private void mockMktIrrGnrBandRepo() {
        List<CfgMktIrrGnrBand> cfgMktIrrGnrBands = new ArrayList<>();

        cfgMktIrrGnrBands.add(new CfgMktIrrGnrBand("code_1", new Double(0.4)));
        cfgMktIrrGnrBands.add(new CfgMktIrrGnrBand("code_2", new Double(0.5)));

        when(cfgMktIrrGnrBandRepository.findAll()).thenReturn(cfgMktIrrGnrBands);
    }

    private void mockMktIrrGnrRiskRepo() {
        List<CfgMktIrrGnrRisk> cfgMktIrrGnrRisks = new ArrayList<>();

        cfgMktIrrGnrRisks.add(new CfgMktIrrGnrRisk("zone_code_1", "band_code_1", "currency_1", new Double(0.1),
                new Double(0.2), new Double(0.3), new Double(0.4), new Double(0.5), 100L));
        cfgMktIrrGnrRisks.add(new CfgMktIrrGnrRisk("zone_code_2", "band_code_2", "currency_2", new Double(0.6),
                new Double(0.7), new Double(0.8), new Double(0.9), new Double(0.10), 200L));

        when(cfgMktIrrGnrRiskRepository.findAll()).thenReturn(cfgMktIrrGnrRisks);
    }

    private void mockMktIrrSpcRiskRepo() {
        List<CfgMktIrrSpcRisk> cfgMktIrrSpcRisks = new ArrayList<>();

        cfgMktIrrSpcRisks.add(new CfgMktIrrSpcRisk("mkt_asset_class_1", "issue_risk_bucket_1", "issuer_risk_bucket_1", "residual_maturity_start_1", "residual_maturity_end_1", "instrument_group_1", new Double(0.3), 100L));
        cfgMktIrrSpcRisks.add(new CfgMktIrrSpcRisk("mkt_asset_class_2", "issue_risk_bucket_2", "issuer_risk_bucket_2", "residual_maturity_start_2", "residual_maturity_end_2", "instrument_group_2", new Double(0.5), 200L));

        when(cfgMktIrrSpcRiskRepository.findAll()).thenReturn(cfgMktIrrSpcRisks);
    }

    private void mockMktAssetClassMappingRepo() {
        List<CfgMktAssetClassMapping> cfgMktAssetClassMappings = new ArrayList<>();

        cfgMktAssetClassMappings.add(new CfgMktAssetClassMapping("mkt_asset_class_1", "era_entity_type_1", "mkt_product_type_1"));
        cfgMktAssetClassMappings.add(new CfgMktAssetClassMapping("mkt_asset_class_2", "era_entity_type_2", "mkt_product_type_2"));

        when(cfgMktAssetClassMappingRepository.findAll()).thenReturn(cfgMktAssetClassMappings);
    }

    private void mockMktAssetClassRepo() {
        List<CfgMktAssetClass> cfgMktAssetClasses = new ArrayList<>();

        cfgMktAssetClasses.add(new CfgMktAssetClass("mkt_asset_class_1", "mkt_asset_class_desc_1"));
        cfgMktAssetClasses.add(new CfgMktAssetClass("mkt_asset_class_2", "mkt_asset_class_desc_2"));

        when(cfgMktAssetClassRepository.findAll()).thenReturn(cfgMktAssetClasses);
    }

    private void mockMktProductMappingRepo() {
        List<CfgMktProductMapping> cfgMktProductMappings = new ArrayList<>();

        cfgMktProductMappings.add(new CfgMktProductMapping("mkt_product__type_1", "contract_type_1", "exchanged_traded_1", "instrument_type_1", "table_name_1", "underlying_type"));
        cfgMktProductMappings.add(new CfgMktProductMapping("mkt_product__type_2", "contract_type_2", "exchanged_traded_2", "instrument_type_2", "table_name_2", "underlying_type"));

        when(cfgMktProductMappingRepository.findAll()).thenReturn(cfgMktProductMappings);
    }

    private void mockMktProductTypeRepo() {
        List<CfgMktProductType> cfgReclassCheckTypes = new ArrayList<>();

        cfgReclassCheckTypes.add(new CfgMktProductType("mkt_product__type_1", "mkt_product_description_1", "mkt_product_category_1"));
        cfgReclassCheckTypes.add(new CfgMktProductType("mkt_product__type_2", "mkt_product_description_2", "mkt_product_category_2"));

        when(cfgMktProductTypeRepository.findAll()).thenReturn(cfgReclassCheckTypes);
    }

    private void mockReclassCheckTypeRepo() {
        List<CfgReclassCheckType> cfgReclassCheckTypes = new ArrayList<>();

        cfgReclassCheckTypes.add(new CfgReclassCheckType("check_type_1", "check_description_1", "where_clause_1", "conso_field_1", "amt_field_1"));
        cfgReclassCheckTypes.add(new CfgReclassCheckType("check_type_2", "check_description_2", "where_clause_2", "conso_field_2", "amt_field_2"));

        when(cfgReclassCheckTypeRepository.findAll()).thenReturn(cfgReclassCheckTypes);
    }

    private void mockReclassCheckDefRepo() {
        List<CfgReclassCheckDef> cfgReclassCheckDefs = new ArrayList<>();

        cfgReclassCheckDefs.add(new CfgReclassCheckDef("check_def_no_1", "description_1", "check_type_1", "operator_1", new Double(2), "currency_1"));
        cfgReclassCheckDefs.add(new CfgReclassCheckDef("check_def_no_2", "description_2", "check_type_2", "operator_2", new Double(2), "currency_2"));

        when(cfgReclassCheckDefRepository.findAll()).thenReturn(cfgReclassCheckDefs);
    }

    private void mockReclassRepo() {
        List<CfgReclass> cfgReclasses = new ArrayList<>();

        cfgReclasses.add(new CfgReclass("check_no_1", "description_1", "era_entity_type_in_1", "era_product_type_in_1", "check_1", "era_entity_type_out_1", "era_product_type_out_1"));
        cfgReclasses.add(new CfgReclass("check_no_2", "description_2", "era_entity_type_in_2", "era_product_type_in_2", "check_2", "era_entity_type_out_2", "era_product_type_out_2"));

        when(cfgReclassRepository.findAll()).thenReturn(cfgReclasses);
    }

    private void mockCrmHaircutRepo() {
        List<CfgCrmHaircut> cfgCrmHaircuts = new ArrayList<>();

        cfgCrmHaircuts.add(new CfgCrmHaircut("era_col_type_1", "era_entity_type_1", "risk_bucket_1", "min_residual_maturity_1", "max_residual_maturity_1", new Double(0.005), 500L));
        cfgCrmHaircuts.add(new CfgCrmHaircut("era_col_type_2", "era_entity_type_2", "risk_bucket_2", "min_residual_maturity_2", "max_residual_maturity_2", new Double(0.005), 500L));

        when(cfgCrmHaircutRepository.findAll()).thenReturn(cfgCrmHaircuts);
    }

    private void mockCrmEligibilityRepo() {
        List<CfgCrmEligibility> cfgCrmEligibilities = new ArrayList<>();

        cfgCrmEligibilities.add(new CfgCrmEligibility("era_entity_type_1", "era_product_type_1", "risk_bucket_1", "risk_weight_1", "eligibility_1", 500L));
        cfgCrmEligibilities.add(new CfgCrmEligibility("era_entity_type_2", "era_product_type_2", "risk_bucket_2", "risk_weight_2", "eligibility_2", 600L));

        when(cfgCrmEligibilityRepository.findAll()).thenReturn(cfgCrmEligibilities);
    }

    private void mockAddOnRepo() {
        List<CfgAddOn> cfgCcfMappings = new ArrayList<>();

        cfgCcfMappings.add(new CfgAddOn("era_product_type_1", "1", "5", new Double(0.1)));
        cfgCcfMappings.add(new CfgAddOn("era_product_type_2", "2", "4", new Double(0.2)));

        when(cfgAddOnRepository.findAll()).thenReturn(cfgCcfMappings);
    }

    private void mockCcfMappingRepo() {
        List<CfgCcfMapping> cfgCcfMappings = new ArrayList<>();

        cfgCcfMappings.add(new CfgCcfMapping("era_product_type_1", new Double(2), "unconditionally_cancellable_1",
                "maturity_start_1", "maturity_end_1", 200L));
        cfgCcfMappings.add(new CfgCcfMapping("era_product_type_2", new Double(3), "unconditionally_cancellable_2",
                "maturity_start_2", "maturity_end_2", 300L));

        when(cfgCcfMappingRepository.findAll()).thenReturn(cfgCcfMappings);
    }

    private void mockRiskWeightMappingRepo() {
        List<CfgRiskWeightMapping> cfgRiskWeightMappings = new ArrayList<>();

        cfgRiskWeightMappings.add(new CfgRiskWeightMapping("asset_class_1", "era_npl_code_1", "year_of_esablishment_1",
                "credit_measure1_1", "credit_measure1_beg_1", "credit_measure1_end_1", "credit_measure_2",
                "credit_measure_beg2_1", "credit_measure2_end_1", new Double(0.2), 200L));
        cfgRiskWeightMappings.add(new CfgRiskWeightMapping("asset_class_2", "era_npl_code_2", "year_of_esablishment_2",
                "credit_measure1_2", "credit_measure1_beg_2", "credit_measure1_end_2", "credit_measure_2",
                "credit_measure_beg2_2", "credit_measure2_end_2", new Double(0.2), 300L));

        when(cfgRiskWeightMappingRepository.findAll()).thenReturn(cfgRiskWeightMappings);
    }

    private void mockCreditMeasureRepo() {
        List<CfgCreditMeasure> cfgRatings = new ArrayList<>();

        cfgRatings.add(new CfgCreditMeasure("credit_measure_1", "credit_measure_desc_1"));
        cfgRatings.add(new CfgCreditMeasure("credit_measure_2", "credit_measure_desc_2"));

        when(cfgCreditMeasureRepository.findAll()).thenReturn(cfgRatings);
    }

    private void mockRatingRepo() {
        List<CfgRating> cfgRatings = new ArrayList<>();

        cfgRatings.add(new CfgRating("agency_code_1", "rating_1", "qualifying_1", 1L));
        cfgRatings.add(new CfgRating("agency_code_2", "rating_2", "qualifying_2", 2L));

        when(cfgRatingRepository.findAll()).thenReturn(cfgRatings);
    }

    private void mockAgencyEligibilityRepo() {
        List<CfgAgencyEligibility> cfgNonPerformingMappings = new ArrayList<>();

        cfgNonPerformingMappings.add(new CfgAgencyEligibility("agency_code_1", "agency_desc_1", "agency_type_1"));
        cfgNonPerformingMappings.add(new CfgAgencyEligibility("agency_code_2", "agency_desc_2", "agency_type_2"));

        when(cfgAgencyEligibilityRepository.findAll()).thenReturn(cfgNonPerformingMappings);
    }

    private void mockNonPerformingMappingRepo() {
        List<CfgNonPerformingMapping> cfgNonPerformingMappings = new ArrayList<>();

        cfgNonPerformingMappings.add(new CfgNonPerformingMapping("era_npl_code_1", "performance_status_1"));
        cfgNonPerformingMappings.add(new CfgNonPerformingMapping("era_npl_code_2", "performance_status_2"));

        when(cfgNonPerformingMappingRepository.findAll()).thenReturn(cfgNonPerformingMappings);
    }

    private void mockAssetClassMappingRepo() {
        List<CfgAssetClassMapping> cfgAssetClassMappings = new ArrayList<>();

        cfgAssetClassMappings.add(new CfgAssetClassMapping("asset_class_1", "entity_type_1", "product_type_1"));
        cfgAssetClassMappings.add(new CfgAssetClassMapping("asset_class_2", "entity_type_2", "product_type_2"));

        when(cfgAssetClassMappingRepository.findAll()).thenReturn(cfgAssetClassMappings);
    }

    private void mockAssetClassRepo() {
        List<CfgAssetClass> cfgAssetClasses = new ArrayList<>();

        cfgAssetClasses.add(new CfgAssetClass("era_asset_class_1", "era_asset_class_desc_1"));
        cfgAssetClasses.add(new CfgAssetClass("era_asset_class_2", "era_asset_class_desc_2"));

        when(cfgAssetClassRepository.findAll()).thenReturn(cfgAssetClasses);
    }

    private void mockProductTypeMappingRepo() {
        List<CfgProductTypeMapping> cfgProductTypeMappings = new ArrayList<>();

        cfgProductTypeMappings.add(new CfgProductTypeMapping("product_type_1", "table_name_1", "seniority_1",
                "contract_type_1", "on_off_1", "f_main_index_1", "f_recog_exch_1", "f_rated_1", "f_occu_1", "f_completed_1",
                "f_independent_valuer_1", "f_legaly_enforce_1", "underlying_1", "era_contract_type_1", 300L, "repayment_property_1"));
        cfgProductTypeMappings.add(new CfgProductTypeMapping("product_type_2", "table_name_2", "seniority_2",
                "contract_type_2", "on_off_2", "f_main_index_2", "f_recog_exch_2", "f_rated_2", "f_occu_2", "f_completed_2",
                "f_independent_valuer_2", "f_legaly_enforce_2", "underlying_2", "era_contract_type_2", 400L, "repayment_property_2"));

        when(cfgProductTypeMappingRepository.findAll()).thenReturn(cfgProductTypeMappings);
    }

    private void mockProductTypeRepo() {
        List<CfgProductType> cfgProductTypes = new ArrayList<>();

        cfgProductTypes.add(new CfgProductType("era_product_type_1", "era_product_desc_1", "era_product_category_1"));
        cfgProductTypes.add(new CfgProductType("era_product_type_2", "era_product_desc_2", "era_product_category_2"));

        when(cfgProductTypeRepository.findAll()).thenReturn(cfgProductTypes);
    }

    private void mockEntityTypeMappingRepo() {
        List<CfgEntityTypeMapping> cfgEntityTypeMappings = new ArrayList<>();

        cfgEntityTypeMappings.add(new CfgEntityTypeMapping("era_entity_type_1", "customer_type_1", "customer_sub_type_1"));
        cfgEntityTypeMappings.add(new CfgEntityTypeMapping("era_entity_type_2", "customer_type_2", "customer_sub_type_2"));

        when(cfgEntityTypeMappingRepository.findAll()).thenReturn(cfgEntityTypeMappings);
    }

    private void mockEntityTypeRepo() {
        List<CfgEntityType> cfgEntityTypes = new ArrayList<>();

        cfgEntityTypes.add(new CfgEntityType("era_entity_type_1", "entity_desc_1"));
        cfgEntityTypes.add(new CfgEntityType("era_entity_type_2", "entity_desc_2"));

        when(cfgEntityTypeRepository.findAll()).thenReturn(cfgEntityTypes);
    }

    private void mockCompanyDimensionConsolidationRepo() {
        List<CfgCompanyDimensionConsolidation> cfgCompanyDimensionConsolidations = new ArrayList<>();

        cfgCompanyDimensionConsolidations.add(new CfgCompanyDimensionConsolidation("company_code_1", "entity_code_1", "conso_mode_1", new Double(0.4)));
        cfgCompanyDimensionConsolidations.add(new CfgCompanyDimensionConsolidation("company_code_2", "entity_code_2", "conso_mode_2", new Double(0.6)));

        when(cfgCompanyDimensionConsolidationRepository.findAll()).thenReturn(cfgCompanyDimensionConsolidations);
    }

    private void mockCompanyDimensionRepo() {
        List<CfgCompanyDimension> cfgCompanyDimensions = new ArrayList<>();

        cfgCompanyDimensions.add(createCfgCompanyDimension("company_code_1", "financial_book_1"));
        cfgCompanyDimensions.add(createCfgCompanyDimension("company_code_2", "financial_book_2"));
        when(cfgCompanyDimensionRepository.findAll()).thenReturn(cfgCompanyDimensions);

    }

    private CfgCompanyDimension createCfgCompanyDimension(String companyCode, String financialBook) {
        CfgCompanyDimension cfgCompanyDimension = new CfgCompanyDimension();
        cfgCompanyDimension.setCompanyCode(companyCode);
        cfgCompanyDimension.setFinancialBook(financialBook);

        return cfgCompanyDimension;
    }

    private void mockCompanyLinkRepo() {
        List<CfgCompanyLinkage> cfgCompanyLinkages = new ArrayList<>();

        cfgCompanyLinkages.add(createCfgCompanyLinkage("child_code_1", "mother_code_1", new Double(0.5)));
        cfgCompanyLinkages.add(createCfgCompanyLinkage("child_code_2", "mother_code_2", new Double(0.4)));
        when(cfgCompanyLinkageRepository.findAll()).thenReturn(cfgCompanyLinkages);
    }

    private CfgCompanyLinkage createCfgCompanyLinkage(String childCode, String motherCode, Double linkWeight) {
        CfgCompanyLinkage cfgCompanyLinkage = new CfgCompanyLinkage();
        cfgCompanyLinkage.setChildCode(childCode);
        cfgCompanyLinkage.setMotherCode(motherCode);
        cfgCompanyLinkage.setLinkWeight(linkWeight);

        return cfgCompanyLinkage;
    }

    private void mockCompanyRepo() {
        List<CfgCompany> cfgCompanyList = new ArrayList<>();
        cfgCompanyList.add(createCfgCompany("comp_code_1", "comp_name_1", "VN"));
        cfgCompanyList.add(createCfgCompany("comp_code_2", "comp_name_2", "VN"));
        when(cfgCompanyRepository.findAll()).thenReturn(cfgCompanyList);
    }

    private void mockFinancialBookRepo() {
        List<CfgFinancialBook> cfgFinancialBookList = new ArrayList<>();
        cfgFinancialBookList.add(createCfgFinancialBookListObj("book_code_1", "book_desc_1", "T"));
        cfgFinancialBookList.add(createCfgFinancialBookListObj("book_code_2", "book_desc_2", "F"));
        when(cfgFinancialBookRepository.findAll()).thenReturn(cfgFinancialBookList);
    }

    private void mockConfigFile(URL resource) throws UnsupportedEncodingException {
        ConfigFile configFile = new ConfigFile();
        configFile.setFilePath(URLDecoder.decode(resource.getPath(), CharEncoding.UTF_8));
        when(configFileRepository.findOne(anyLong())).thenReturn(configFile);
    }

    private CfgFinancialBook createCfgFinancialBookListObj(String bookCode, String bookDesc, String tradingFlag) {
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
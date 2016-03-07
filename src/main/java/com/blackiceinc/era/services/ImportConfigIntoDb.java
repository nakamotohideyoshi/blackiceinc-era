package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.*;
import com.blackiceinc.era.persistence.erau.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportConfigIntoDb {

    @Autowired
    private CfgFinancialBookRepository cfgFinancialBookRepository;
    @Autowired
    private CfgCompanyRepository cfgCompanyRepository;
    @Autowired
    private CfgCompanyLinkageRepository cfgCompanyLinkageRepository;
    @Autowired
    private CfgCompanyDimensionRepository cfgCompanyDimensionRepository;
    @Autowired
    private CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository;

    @Autowired
    private CfgEntityTypeRepository cfgEntityTypeRepository;
    @Autowired
    private CfgEntityTypeMappingRepository cfgEntityTypeMappingRepository;

    @Autowired
    private CfgProductTypeRepository cfgProductTypeRepository;
    @Autowired
    private CfgProductTypeMappingRepository cfgProductTypeMappingRepository;

    @Autowired
    private CfgAssetClassRepository cfgAssetClassRepository;
    @Autowired
    private CfgAssetClassMappingRepository cfgAssetClassMappingRepository;

    @Autowired
    private CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository;

    @Autowired
    private CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository;

    @Autowired
    private CfgRatingRepository cfgRatingRepository;

    @Autowired
    private CfgCreditMeasureRepository cfgCreditMeasureRepository;

    @Autowired
    private CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository;

    @Autowired
    private CfgCcfMappingRepository cfgCcfMappingRepository;

    @Autowired
    private CfgAddOnRepository cfgAddOnRepository;

    @Autowired
    private CfgCrmEligibilityRepository cfgCrmEligibilityRepository;

    @Autowired
    private CfgCrmHaircutRepository cfgCrmHaircutRepository;

    @Autowired
    private CfgReclassRepository cfgReclassRepository;
    @Autowired
    private CfgReclassDaoCustom cfgReclassDaoCustom;
    @Autowired
    private CfgReclassCheckDefRepository cfgReclassCheckDefRepository;
    @Autowired
    private CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository;

    @Autowired
    private CfgMktProductTypeRepository cfgMktProductTypeRepository;
    @Autowired
    private CfgMktProductMappingRepository cfgMktProductMappingRepository;
    @Autowired
    private CfgMktAssetClassRepository cfgMktAssetClassRepository;
    @Autowired
    private CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository;
    @Autowired
    private CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository;
    @Autowired
    private CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository;
    @Autowired
    private CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository;
    @Autowired
    private CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;
    @Autowired
    private CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;
    @Autowired
    private CfgMktEqtSpcRepository cfgMktEqtSpcRepository;
    @Autowired
    private CfgMktEqtGnrRepository cfgMktEqtGnrRepository;
    @Autowired
    private CfgMktComDrtRepository cfgMktComDrtRepository;
    @Autowired
    private CfgMktComOthRepository cfgMktComOthRepository;
    @Autowired
    private CfgMktFxRepository cfgMktFxRepository;

    @Autowired
    private CfgOpsProductTypeRepository cfgOpsProductTypeRepository;
    @Autowired
    private CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository;
    @Autowired
    private CfgOpsFormulaRepository cfgOpsFormulaRepository;
    @Autowired
    private CfgOpsRiskRepository cfgOpsRiskRepository;

    @Autowired
    private CfgCapElementsRepository cfgCapElementsRepository;
    @Autowired
    private CfgCapElementsTypeRepository cfgCapElementsTypeRepository;
    @Autowired
    private CfgCapElementsLimitRepository cfgCapElementsLimitRepository;
    @Autowired
    private CfgCapElementsMappingRepository cfgCapElementsMappingRepository;
    @Autowired
    private CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository;

    public void importCfgFinancialBook(List<CfgFinancialBook> cfgFinancialBookList) {
        cfgFinancialBookRepository.deleteAll();
        for (CfgFinancialBook cfgFinancialBook:cfgFinancialBookList){
            cfgFinancialBookRepository.save(cfgFinancialBook);
        }
//        removeAllAndSaveNewList(cfgFinancialBookRepository, cfgFinancialBookList);
    }

    public void importCfgCompaniesList(List<CfgCompany> cfgCompaniesList) {
        removeAllAndSaveNewList(cfgCompanyRepository, cfgCompaniesList);
    }

    public void importCfgCompanyLinkage(List<CfgCompanyLinkage> cfgCompaniesLinkageList) {
        removeAllAndSaveNewList(cfgCompanyLinkageRepository, cfgCompaniesLinkageList);
    }

    public void importCfgCompanyDimensions(List<CfgCompanyDimension> cfgCompanyDimensions) {
        cfgCompanyDimensionRepository.deleteAll();
        for (CfgCompanyDimension cfgCompanyDimension:cfgCompanyDimensions){
            cfgCompanyDimensionRepository.save(cfgCompanyDimension);
        }
//        removeAllAndSaveNewList(cfgCompanyDimensionRepository, cfgCompanyDimensions);
    }

    public void importCfgCompanyDimensionConsolidations(List<CfgCompanyDimensionConsolidation> cfgCompanyDimensionConsolidations) {
        removeAllAndSaveNewList(cfgCompanyDimensionConsolidationRepository, cfgCompanyDimensionConsolidations);
    }

    public void importCfgEntityTypes(List<CfgEntityType> cfgEntityTypes) {
        removeAllAndSaveNewList(cfgEntityTypeRepository, cfgEntityTypes);
    }

    public void importCfgEntityTypeMappings(List<CfgEntityTypeMapping> cfgEntityTypeMappings) {
        removeAllAndSaveNewList(cfgEntityTypeMappingRepository, cfgEntityTypeMappings);
    }

    public void importCfgProductTypes(List<CfgProductType> cfgProductTypes) {
        removeAllAndSaveNewList(cfgProductTypeRepository, cfgProductTypes);
    }

    public void importCfgProductTypeMappings(List<CfgProductTypeMapping> cfgProductTypeMappings) {
        removeAllAndSaveNewList(cfgProductTypeMappingRepository, cfgProductTypeMappings);
    }

    public void importCfgAssetClass(List<CfgAssetClass> cfgAssetClass) {
        removeAllAndSaveNewList(cfgAssetClassRepository, cfgAssetClass);
    }

    public void importCfgAssetClassMappings(List<CfgAssetClassMapping> cfgAssetClassMappings) {
        removeAllAndSaveNewList(cfgAssetClassMappingRepository, cfgAssetClassMappings);
    }

    public void importCfgNonPerformingMappings(List<CfgNonPerformingMapping> cfgNonPerformingMappings) {
        removeAllAndSaveNewList(cfgNonPerformingMappingRepository, cfgNonPerformingMappings);
    }

    public void importCfgAgencyEligibility(List<CfgAgencyEligibility> cfgAgencyEligibility) {
        removeAllAndSaveNewList(cfgAgencyEligibilityRepository, cfgAgencyEligibility);
    }

    public void importCfgRatings(List<CfgRating> cfgRatings) {
        removeAllAndSaveNewList(cfgRatingRepository, cfgRatings);
    }

    public void importCfgCreditMeasures(List<CfgCreditMeasure> cfgCreditMeasures) {
        removeAllAndSaveNewList(cfgCreditMeasureRepository, cfgCreditMeasures);

    }

    public void importCfgRiskWeightMappings(List<CfgRiskWeightMapping> cfgRiskWeightMappings) {
        removeAllAndSaveNewList(cfgRiskWeightMappingRepository, cfgRiskWeightMappings);
    }

    public void importCfgCcfMappings(List<CfgCcfMapping> cfgCcfMappings) {
        removeAllAndSaveNewList(cfgCcfMappingRepository, cfgCcfMappings);
    }

    public void importCfgAddOns(List<CfgAddOn> cfgAddOns) {
        removeAllAndSaveNewList(cfgAddOnRepository, cfgAddOns);
    }

    public void importCfgCrmEligibilities(List<CfgCrmEligibility> cfgCrmEligibilities) {
        removeAllAndSaveNewList(cfgCrmEligibilityRepository, cfgCrmEligibilities);
    }

    public void importCfgCrmHaircuts(List<CfgCrmHaircut> cfgCrmHaircuts) {
        removeAllAndSaveNewList(cfgCrmHaircutRepository, cfgCrmHaircuts);
    }

    public void importCfgReclasses(List<CfgReclass> cfgReclasses) {
        cfgReclassRepository.deleteAll();
        for (CfgReclass cfgReclass: cfgReclasses){
            cfgReclassDaoCustom.insert(cfgReclass);
        }

//        removeAllAndSaveNewList(cfgReclassRepository, cfgReclasses);
    }

    public void importCfgReclassCheckDefs(List<CfgReclassCheckDef> cfgReclassCheckDefs) {
        removeAllAndSaveNewList(cfgReclassCheckDefRepository, cfgReclassCheckDefs);
    }

    public void importCfgReclassCheckTypes(List<CfgReclassCheckType> cfgReclassCheckTypes) {
        removeAllAndSaveNewList(cfgReclassCheckTypeRepository, cfgReclassCheckTypes);
    }

    public void importCfgMktProductTypes(List<CfgMktProductType> cfgMktProductTypes) {
        removeAllAndSaveNewList(cfgMktProductTypeRepository, cfgMktProductTypes);
    }

    public void importCfgMktProductMappings(List<CfgMktProductMapping> cfgMktProductMappings) {
        removeAllAndSaveNewList(cfgMktProductMappingRepository, cfgMktProductMappings);
    }

    public void importCfgMktAssetClasses(List<CfgMktAssetClass> cfgMktAssetClasses) {
        removeAllAndSaveNewList(cfgMktAssetClassRepository, cfgMktAssetClasses);
    }

    public void importCfgMktAssetClassMappings(List<CfgMktAssetClassMapping> cfgMktAssetClassMappings) {
        removeAllAndSaveNewList(cfgMktAssetClassMappingRepository, cfgMktAssetClassMappings);
    }

    public void importCfgMktIrrSpcRisks(List<CfgMktIrrSpcRisk> cfgMktIrrSpcRisks) {
        removeAllAndSaveNewList(cfgMktIrrSpcRiskRepository, cfgMktIrrSpcRisks);
    }

    public void importCfgMktIrrGnrRisks(List<CfgMktIrrGnrRisk> cfgMktIrrGnrRisks) {
        removeAllAndSaveNewList(cfgMktIrrGnrRiskRepository, cfgMktIrrGnrRisks);
    }

    public void importCfgMktIrrGnrBands(List<CfgMktIrrGnrBand> cfgMktIrrGnrBands) {
        removeAllAndSaveNewList(cfgMktIrrGnrBandRepository, cfgMktIrrGnrBands);
    }

    public void importCfgMktIrrGnrIntras(List<CfgMktIrrGnrIntra> cfgMktIrrGnrIntras) {
        removeAllAndSaveNewList(cfgMktIrrGnrIntraRepository, cfgMktIrrGnrIntras);
    }

    public void importCfgMktIrrGnrInters(List<CfgMktIrrGnrInter> cfgMktIrrGnrInters) {
        removeAllAndSaveNewList(cfgMktIrrGnrInterRepository, cfgMktIrrGnrInters);
    }

    public void importCfgMktEqtSpcs(List<CfgMktEqtSpc> cfgMktEqtSpcs) {
        removeAllAndSaveNewList(cfgMktEqtSpcRepository, cfgMktEqtSpcs);
    }

    public void importCfgMktEqtGnrs(List<CfgMktEqtGnr> cfgMktEqtGnrs) {
        removeAllAndSaveNewList(cfgMktEqtGnrRepository, cfgMktEqtGnrs);
    }

    public void importCfgMktComDrts(List<CfgMktComDrt> cfgMktComDrts) {
        removeAllAndSaveNewList(cfgMktComDrtRepository, cfgMktComDrts);
    }

    public void importCfgMktComOths(List<CfgMktComOth> cfgMktComOths) {
        removeAllAndSaveNewList(cfgMktComOthRepository, cfgMktComOths);
    }

    public void importCfgMktFxes(List<CfgMktFx> cfgMktFxes) {
        removeAllAndSaveNewList(cfgMktFxRepository, cfgMktFxes);
    }

    public void importCfgOpsProductTypes(List<CfgOpsProductType> cfgOpsProductTypes) {
        removeAllAndSaveNewList(cfgOpsProductTypeRepository, cfgOpsProductTypes);
    }

    public void importCfgOpsProductTypeMappings(List<CfgOpsProductTypeMapping> cfgOpsProductTypeMappings) {
        removeAllAndSaveNewList(cfgOpsProductTypeMappingRepository, cfgOpsProductTypeMappings);
    }

    public void importCfgOpsFormulas(List<CfgOpsFormula> cfgOpsFormulas) {
        removeAllAndSaveNewList(cfgOpsFormulaRepository, cfgOpsFormulas);
    }

    public void importCfgOpsRisks(List<CfgOpsRisk> cfgOpsRisks) {
        removeAllAndSaveNewList(cfgOpsRiskRepository, cfgOpsRisks);
    }

    public void importCfgCapElementses(List<CfgCapElements> cfgCapElementses) {
        removeAllAndSaveNewList(cfgCapElementsRepository, cfgCapElementses);
    }

    public void importCfgCapElementsTypes(List<CfgCapElementsType> cfgCapElementsTypes) {
        removeAllAndSaveNewList(cfgCapElementsTypeRepository, cfgCapElementsTypes);
    }


    public void importCfgCapElementsLimits(List<CfgCapElementsLimit> cfgCapElementsLimits) {
        removeAllAndSaveNewList(cfgCapElementsLimitRepository, cfgCapElementsLimits);
    }

    public void importCfgCapElementsMappings(List<CfgCapElementsMapping> cfgCapElementsMappings) {
        removeAllAndSaveNewList(cfgCapElementsMappingRepository, cfgCapElementsMappings);
    }

    public void importCfgCapElementsFormulas(List<CfgCapElementsFormula> cfgCapElementsFormulas) {
        removeAllAndSaveNewList(cfgCapElementsFormulaRepository, cfgCapElementsFormulas);
    }

    private void removeAllAndSaveNewList(JpaRepository repository, List<?> newItems) {
        repository.deleteAll();
        repository.save(newItems);
    }
}

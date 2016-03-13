package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.*;
import com.blackiceinc.era.persistence.erau.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportConfigIntoDb {

    private final Logger log = LoggerFactory.getLogger(ImportConfigIntoDb.class);

    @Autowired
    private CfgFinancialBookRepository cfgFinancialBookRepository;
    @Autowired
    private CfgFinancialBookDaoCustom cfgFinancialBookDaoCustom;

    @Autowired
    private CfgCompanyRepository cfgCompanyRepository;
    @Autowired
    private CfgCompanyLinkageRepository cfgCompanyLinkageRepository;
    @Autowired
    private CfgCompanyLinkageDaoCustom cfgCompanyLinkageDaoCustom;

    @Autowired
    private CfgCompanyDimensionRepository cfgCompanyDimensionRepository;
    @Autowired
    private CfgCompanyDimensionDaoCustom cfgCompanyDimensionDaoCustom;

    @Autowired
    private CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository;
    @Autowired
    private CfgCompanyDimensionConsolidationDaoCustom cfgCompanyDimensionConsolidationDaoCustom;

    @Autowired
    private CfgEntityTypeRepository cfgEntityTypeRepository;
    @Autowired
    private CfgEntityTypeMappingRepository cfgEntityTypeMappingRepository;

    @Autowired
    private CfgProductTypeRepository cfgProductTypeRepository;

    @Autowired
    private CfgProductTypeMappingRepository cfgProductTypeMappingRepository;
    @Autowired
    private CfgProductTypeMappingDaoCustom cfgProductTypeMappingDaoCustom;

    @Autowired
    private CfgAssetClassRepository cfgAssetClassRepository;
    @Autowired
    private CfgAssetClassDaoCustom cfgAssetClassDaoCustom;

    @Autowired
    private CfgAssetClassMappingRepository cfgAssetClassMappingRepository;
    @Autowired
    private CfgAssetClassMappingDaoCustom cfgAssetClassMappingDaoCustom;


    @Autowired
    private CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository;

    @Autowired
    private CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository;
    @Autowired
    private CfgAgencyEligibilityDaoCustom cfgAgencyEligibilityDaoCustom;

    @Autowired
    private CfgRatingRepository cfgRatingRepository;
    @Autowired
    private CfgRatingDaoCustom cfgRatingDaoCustom;

    @Autowired
    private CfgCreditMeasureRepository cfgCreditMeasureRepository;

    @Autowired
    private CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository;
    @Autowired
    private CfgRiskWeightMappingDaoCustom cfgRiskWeightMappingDaoCustom;

    @Autowired
    private CfgCcfMappingRepository cfgCcfMappingRepository;
    @Autowired
    private CfgCcfMappingDaoCustom cfgCcfMappingDaoCustom;

    @Autowired
    private CfgAddOnRepository cfgAddOnRepository;
    @Autowired
    private CfgAddOnDaoCustom cfgAddOnDaoCustom;

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
    private CfgMktProductMappingDaoCustom cfgMktProductMappingDaoCustom;

    @Autowired
    private CfgMktAssetClassRepository cfgMktAssetClassRepository;
    @Autowired
    private CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository;
    @Autowired
    private CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository;

    @Autowired
    private CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository;
    @Autowired
    private CfgMktIrrGnrRiskDaoCustom cfgMktIrrGnrRiskDaoCustom;

    @Autowired
    private CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository;
    @Autowired
    private CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;
    @Autowired
    private CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;

    @Autowired
    private CfgMktEqtSpcRepository cfgMktEqtSpcRepository;
    @Autowired
    private CfgMktEqtSpcDaoCustom cfgMktEqtSpcDaoCustom;

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
    private CfgOpsProductTypeDaoCustom cfgOpsProductTypeDaoCustom;

    @Autowired
    private CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository;
    @Autowired
    private CfgOpsProductTypeMappingDaoCustom cfgOpsProductTypeMappingDaoCustom;

    @Autowired
    private CfgOpsFormulaRepository cfgOpsFormulaRepository;
    @Autowired
    private CfgOpsRiskRepository cfgOpsRiskRepository;

    @Autowired
    private CfgCapElementsRepository cfgCapElementsRepository;
    @Autowired
    private CfgCapElementsDaoCustom cfgCapElementsDaoCustom;
    @Autowired
    private CfgCapElementsTypeRepository cfgCapElementsTypeRepository;
    @Autowired
    private CfgCapElementsTypeDaoCustom cfgCapElementsTypeDaoCustom;

    @Autowired
    private CfgCapElementsLimitRepository cfgCapElementsLimitRepository;
    @Autowired
    private CfgCapElementsLimitDaoCustom cfgCapElementsLimitDaoCustom;
    @Autowired
    private CfgCapElementsMappingRepository cfgCapElementsMappingRepository;
    @Autowired
    private CfgCapElementsMappingDaoCustom cfgCapElementsMappingDaoCustom;
    @Autowired
    private CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository;
    @Autowired
    private CfgCapElementsFormulaDaoCustom cfgCapElementsFormulaDaoCustom;

    public void importCfgFinancialBook(List<CfgFinancialBook> cfgFinancialBookList) {
        cfgFinancialBookRepository.deleteAll();
        for (CfgFinancialBook cfgFinancialBook : cfgFinancialBookList) {
            cfgFinancialBookDaoCustom.insert(cfgFinancialBook);
        }
    }

    public void importCfgCompaniesList(List<CfgCompany> cfgCompaniesList) {
        removeAllAndSaveNewList(cfgCompanyRepository, cfgCompaniesList);
    }

    public void importCfgCompanyLinkage(List<CfgCompanyLinkage> cfgCompaniesLinkageList) {
        cfgCompanyLinkageRepository.deleteAll();
        for (CfgCompanyLinkage cfgCompanyLinkage : cfgCompaniesLinkageList) {
            cfgCompanyLinkageDaoCustom.insert(cfgCompanyLinkage);
        }
    }

    public void importCfgCompanyDimensions(List<CfgCompanyDimension> cfgCompanyDimensions) {
        cfgCompanyDimensionRepository.deleteAll();
        for (CfgCompanyDimension cfgCompanyDimension : cfgCompanyDimensions) {
            cfgCompanyDimensionDaoCustom.insert(cfgCompanyDimension);
        }
    }

    public void importCfgCompanyDimensionConsolidations(List<CfgCompanyDimensionConsolidation> cfgCompanyDimensionConsolidations) {
        cfgCompanyDimensionConsolidationRepository.deleteAll();
        for (CfgCompanyDimensionConsolidation cfgCompanyDimensionConsolidation : cfgCompanyDimensionConsolidations) {
            cfgCompanyDimensionConsolidationDaoCustom.insert(cfgCompanyDimensionConsolidation);
        }
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
        cfgProductTypeMappingRepository.deleteAll();
        for (CfgProductTypeMapping cfgProductTypeMapping : cfgProductTypeMappings) {
            cfgProductTypeMappingDaoCustom.insert(cfgProductTypeMapping);
        }
    }

    public void importCfgAssetClass(List<CfgAssetClass> cfgAssetClass) {
        cfgAssetClassRepository.deleteAll();
        for (CfgAssetClass cfgAssetClas : cfgAssetClass) {
            cfgAssetClassDaoCustom.insert(cfgAssetClas);
        }
    }

    public void importCfgAssetClassMappings(List<CfgAssetClassMapping> cfgAssetClassMappings) {
        cfgAssetClassMappingRepository.deleteAll();
        for (CfgAssetClassMapping cfgAssetClassMapping : cfgAssetClassMappings) {
            cfgAssetClassMappingDaoCustom.insert(cfgAssetClassMapping);
        }
    }

    public void importCfgNonPerformingMappings(List<CfgNonPerformingMapping> cfgNonPerformingMappings) {
        removeAllAndSaveNewList(cfgNonPerformingMappingRepository, cfgNonPerformingMappings);
    }

    public void importCfgAgencyEligibility(List<CfgAgencyEligibility> cfgAgencyEligibility) {
        cfgAgencyEligibilityRepository.deleteAll();

        for (CfgAgencyEligibility agencyEligibility : cfgAgencyEligibility) {
            cfgAgencyEligibilityDaoCustom.insert(agencyEligibility);
        }
    }

    public void importCfgRatings(List<CfgRating> cfgRatings) {
        cfgRatingRepository.deleteAll();
        for (CfgRating cfgRating : cfgRatings) {
            cfgRatingDaoCustom.insert(cfgRating);
        }
    }

    public void importCfgCreditMeasures(List<CfgCreditMeasure> cfgCreditMeasures) {
        removeAllAndSaveNewList(cfgCreditMeasureRepository, cfgCreditMeasures);

    }

    public void importCfgRiskWeightMappings(List<CfgRiskWeightMapping> cfgRiskWeightMappings) {
        cfgRiskWeightMappingRepository.deleteAll();

        for (CfgRiskWeightMapping cfgRiskWeightMapping : cfgRiskWeightMappings) {
            try {
                cfgRiskWeightMappingDaoCustom.insert(cfgRiskWeightMapping);
            } catch (InvalidDataAccessResourceUsageException ex) {
                log.error("Error executing sql for entity : {}",
                        cfgRiskWeightMapping.toString(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public void importCfgCcfMappings(List<CfgCcfMapping> cfgCcfMappings) {
        cfgCcfMappingRepository.deleteAll();
        for (CfgCcfMapping cfgCcfMapping : cfgCcfMappings) {
            cfgCcfMappingDaoCustom.insert(cfgCcfMapping);
        }
    }

    public void importCfgAddOns(List<CfgAddOn> cfgAddOns) {
        cfgAddOnRepository.deleteAll();
        for (CfgAddOn cfgAddOn : cfgAddOns) {
            cfgAddOnDaoCustom.insert(cfgAddOn);
        }
//        removeAllAndSaveNewList(cfgAddOnRepository, cfgAddOns);
    }

    public void importCfgCrmEligibilities(List<CfgCrmEligibility> cfgCrmEligibilities) {
        removeAllAndSaveNewList(cfgCrmEligibilityRepository, cfgCrmEligibilities);
    }

    public void importCfgCrmHaircuts(List<CfgCrmHaircut> cfgCrmHaircuts) {
        removeAllAndSaveNewList(cfgCrmHaircutRepository, cfgCrmHaircuts);
    }

    public void importCfgReclasses(List<CfgReclass> cfgReclasses) {
        cfgReclassRepository.deleteAll();
        for (CfgReclass cfgReclass : cfgReclasses) {
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
        cfgMktProductMappingRepository.deleteAll();
        for (CfgMktProductMapping cfgMktProductMapping : cfgMktProductMappings) {
            cfgMktProductMappingDaoCustom.insert(cfgMktProductMapping);
        }
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
        cfgMktIrrGnrRiskRepository.deleteAll();
        for (CfgMktIrrGnrRisk cfgMktIrrGnrRisk : cfgMktIrrGnrRisks) {
            cfgMktIrrGnrRiskDaoCustom.insert(cfgMktIrrGnrRisk);
        }
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
        cfgMktEqtSpcRepository.deleteAll();
        for (CfgMktEqtSpc cfgMktEqtSpc : cfgMktEqtSpcs) {
            cfgMktEqtSpcDaoCustom.insert(cfgMktEqtSpc);
        }
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
        cfgOpsProductTypeRepository.deleteAll();
        for (CfgOpsProductType cfgOpsProductType : cfgOpsProductTypes) {
            cfgOpsProductTypeDaoCustom.insert(cfgOpsProductType);
        }
    }

    public void importCfgOpsProductTypeMappings(List<CfgOpsProductTypeMapping> cfgOpsProductTypeMappings) {
        cfgOpsProductTypeMappingRepository.deleteAll();
        for (CfgOpsProductTypeMapping cfgOpsProductTypeMapping : cfgOpsProductTypeMappings) {
            cfgOpsProductTypeMappingDaoCustom.insert(cfgOpsProductTypeMapping);
        }
    }

    public void importCfgOpsFormulas(List<CfgOpsFormula> cfgOpsFormulas) {
        removeAllAndSaveNewList(cfgOpsFormulaRepository, cfgOpsFormulas);
    }

    public void importCfgOpsRisks(List<CfgOpsRisk> cfgOpsRisks) {
        removeAllAndSaveNewList(cfgOpsRiskRepository, cfgOpsRisks);
    }

    public void importCfgCapElementses(List<CfgCapElements> cfgCapElementses) {
        cfgCapElementsRepository.deleteAll();
        for (CfgCapElements cfgCapElementse : cfgCapElementses) {
            cfgCapElementsDaoCustom.insert(cfgCapElementse);
        }
    }

    public void importCfgCapElementsTypes(List<CfgCapElementsType> cfgCapElementsTypes) {
        cfgCapElementsTypeRepository.deleteAll();
        for (CfgCapElementsType cfgCapElementsType : cfgCapElementsTypes) {
            cfgCapElementsTypeDaoCustom.insert(cfgCapElementsType);
        }
    }


    public void importCfgCapElementsLimits(List<CfgCapElementsLimit> cfgCapElementsLimits) {
        cfgCapElementsLimitRepository.deleteAll();
        for (CfgCapElementsLimit cfgCapElementsLimit : cfgCapElementsLimits) {
            cfgCapElementsLimitDaoCustom.insert(cfgCapElementsLimit);
        }
    }

    public void importCfgCapElementsMappings(List<CfgCapElementsMapping> cfgCapElementsMappings) {
        cfgCapElementsMappingRepository.deleteAll();
        for (CfgCapElementsMapping cfgCapElementsMapping : cfgCapElementsMappings) {
            cfgCapElementsMappingDaoCustom.insert(cfgCapElementsMapping);
        }
    }

    public void importCfgCapElementsFormulas(List<CfgCapElementsFormula> cfgCapElementsFormulas) {
        cfgCapElementsFormulaRepository.deleteAll();
        for (CfgCapElementsFormula cfgCapElementsFormula : cfgCapElementsFormulas) {
            cfgCapElementsFormulaDaoCustom.insert(cfgCapElementsFormula);
        }
    }

    private void removeAllAndSaveNewList(JpaRepository repository, List<?> newItems) {
        repository.deleteAll();
        repository.save(newItems);
    }
}

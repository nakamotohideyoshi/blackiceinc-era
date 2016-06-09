package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCompanyDimensionConsolidationDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCompanyDimensionConsolidationObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCompanyDimensionConsolidationImportService implements CfgImportService {

    public static final String COMPANY_DIMENSION_CONSOLIDATION = "COMPANY_DIMENSION_CONSOLIDATION";

    @Autowired
    private CfgCompanyDimensionConsolidationObjectMapper cfgCompanyDimensionConsolidationObjectMapper;

    @Autowired
    private CfgCompanyDimensionConsolidationDaoCustom cfgCompanyDimensionConsolidationDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(COMPANY_DIMENSION_CONSOLIDATION, workbook,
                cfgCompanyDimensionConsolidationObjectMapper, cfgCompanyDimensionConsolidationDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet companyDimensionConsolidationSheet = ImportUtil.getSheet(COMPANY_DIMENSION_CONSOLIDATION, workbook);
        cfgCompanyDimensionConsolidationObjectMapper.importData(companyDimensionConsolidationSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 4;
    }
}

package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCompanyDimensionDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCompanyDimensionObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCompanyDimensionImportService implements CfgImportService {
    public static final String COMPANY_DIMENSION = "COMPANY_DIMENSION";

    @Autowired
    private CfgCompanyDimensionObjectMapper cfgCompanyDimensionObjectMapper;

    @Autowired
    private CfgCompanyDimensionDaoCustom cfgCompanyDimensionDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(COMPANY_DIMENSION, workbook,
                cfgCompanyDimensionObjectMapper, cfgCompanyDimensionDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet companyDimensionSheet = ImportUtil.getSheet(COMPANY_DIMENSION, workbook);
        cfgCompanyDimensionObjectMapper.importData(companyDimensionSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 3;
    }
}

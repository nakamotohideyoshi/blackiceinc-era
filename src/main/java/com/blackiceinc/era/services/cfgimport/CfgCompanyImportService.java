package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCompanyDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCompanyObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCompanyImportService implements CfgImportService {

    public static final String COMPANY = "COMPANY";

    @Autowired
    private CfgCompanyObjectMapper cfgCompanyObjectMapper;

    @Autowired
    private CfgCompanyDaoCustom cfgCompanyDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(COMPANY, workbook,
                cfgCompanyObjectMapper, cfgCompanyDaoCustom);

    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet companySheet = ImportUtil.getSheet(COMPANY, workbook);
        cfgCompanyObjectMapper.importData(companySheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}

package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCompanyLinkageDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCompanyLinkageObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCompanyLinkageImportService implements CfgImportService {
    public static final String COMPANY_LINKAGE = "COMPANY_LINKAGE";

    @Autowired
    private CfgCompanyLinkageObjectMapper cfgCompanyLinkageObjectMapper;

    @Autowired
    private CfgCompanyLinkageDaoCustom cfgCompanyLinkageDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(COMPANY_LINKAGE, workbook,
                cfgCompanyLinkageObjectMapper, cfgCompanyLinkageDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet companyLinkageSheet = ImportUtil.getSheet(COMPANY_LINKAGE, workbook);
        cfgCompanyLinkageObjectMapper.importData(companyLinkageSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }
}

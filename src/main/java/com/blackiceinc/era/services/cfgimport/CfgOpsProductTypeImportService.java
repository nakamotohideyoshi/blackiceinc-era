package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgOpsProductTypeObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgOpsProductTypeImportService implements CfgImportService {
    public static final String OPS_PRODUCT_TYPE = "OPS_PRODUCT_TYPE";

    @Autowired
    private CfgOpsProductTypeObjectMapper cfgOpsProductTypeObjectMapper;

    @Autowired
    private CfgOpsProductTypeDaoCustom cfgOpsProductTypeDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(OPS_PRODUCT_TYPE, workbook,
                cfgOpsProductTypeObjectMapper, cfgOpsProductTypeDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet opsProductTypeSheet = ImportUtil.getSheet(OPS_PRODUCT_TYPE, workbook);
        cfgOpsProductTypeObjectMapper.importData(opsProductTypeSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 34;
    }
}

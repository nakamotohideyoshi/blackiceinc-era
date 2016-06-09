package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgOpsProductTypeMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgOpsProductTypeMappingImportService implements CfgImportService {
    public static final String OPS_PRODUCT_TYPE_MAPPING = "OPS_PRODUCT_TYPE_MAPPING";

    @Autowired
    private CfgOpsProductTypeMappingObjectMapper cfgOpsProductTypeMappingObjectMapper;

    @Autowired
    private CfgOpsProductTypeMappingDaoCustom cfgOpsProductTypeMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(OPS_PRODUCT_TYPE_MAPPING, workbook,
                cfgOpsProductTypeMappingObjectMapper, cfgOpsProductTypeMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet opsProductTypeMappingSheet = ImportUtil.getSheet(OPS_PRODUCT_TYPE_MAPPING, workbook);
        cfgOpsProductTypeMappingObjectMapper.importData(opsProductTypeMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 34;
    }
}

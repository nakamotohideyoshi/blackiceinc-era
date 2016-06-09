package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgEntityTypeMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgEntityTypeMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgEntityTypeMappingImportService implements CfgImportService {

    public static final String ENTITY_TYPE_MAPPING = "ENTITY_TYPE_MAPPING";

    @Autowired
    private CfgEntityTypeMappingObjectMapper cfgEntityTypeMappingObjectMapper;

    @Autowired
    CfgEntityTypeMappingDaoCustom cfgEntityTypeMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(ENTITY_TYPE_MAPPING, workbook,
                cfgEntityTypeMappingObjectMapper, cfgEntityTypeMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet entityTypeMappingSheet = ImportUtil.getSheet(ENTITY_TYPE_MAPPING, workbook);
        cfgEntityTypeMappingObjectMapper.importData(entityTypeMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 6;
    }
}

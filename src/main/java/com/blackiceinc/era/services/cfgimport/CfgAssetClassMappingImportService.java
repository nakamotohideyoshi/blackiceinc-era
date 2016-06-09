package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgAssetClassMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgAssetClassMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgAssetClassMappingImportService implements CfgImportService {
    public static final String ASSET_CLASS_MAPPING = "ASSET_CLASS_MAPPING";

    @Autowired
    private CfgAssetClassMappingObjectMapper cfgAssetClassMappingObjectMapper;

    @Autowired
    private CfgAssetClassMappingDaoCustom cfgAssetClassMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(ASSET_CLASS_MAPPING, workbook,
                cfgAssetClassMappingObjectMapper, cfgAssetClassMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet assetClassMappingSheet = ImportUtil.getSheet(ASSET_CLASS_MAPPING, workbook);
        cfgAssetClassMappingObjectMapper.importCfgAssetClassMapping(assetClassMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 9;
    }
}

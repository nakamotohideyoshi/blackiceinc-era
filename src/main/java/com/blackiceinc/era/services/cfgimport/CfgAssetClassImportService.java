package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgAssetClassDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgAssetClassObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgAssetClassImportService implements CfgImportService {
    public static final String ASSET_CLASS = "ASSET_CLASS";

    @Autowired
    private CfgAssetClassObjectMapper cfgAssetClassObjectMapper;

    @Autowired
    private CfgAssetClassDaoCustom cfgAssetClassDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(ASSET_CLASS, workbook,
                cfgAssetClassObjectMapper, cfgAssetClassDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet assetClassSheet = ImportUtil.getSheet(ASSET_CLASS, workbook);
        cfgAssetClassObjectMapper.importData(assetClassSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 9;
    }
}

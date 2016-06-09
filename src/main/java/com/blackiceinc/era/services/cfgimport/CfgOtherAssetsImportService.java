package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgOtherAssetsDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgOtherAssetsObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgOtherAssetsImportService implements CfgImportService {

    public static final String OTHER_ASSETS = "OTHER_ASSETS";

    @Autowired
    private CfgOtherAssetsObjectMapper cfgOtherAssetsObjectMapper;

    @Autowired
    private CfgOtherAssetsDaoCustom cfgOtherAssetsDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(OTHER_ASSETS, workbook,
                cfgOtherAssetsObjectMapper, cfgOtherAssetsDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet otherAssetsSheet = ImportUtil.getSheet(OTHER_ASSETS, workbook);
        cfgOtherAssetsObjectMapper.importData(otherAssetsSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 42;
    }
}

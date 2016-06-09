package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktAssetClassDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktAssetClassObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktAssetClassImportService implements CfgImportService {
    public static final String MKT_ASSET_CLASS = "MKT_ASSET_CLASS";

    @Autowired
    private CfgMktAssetClassObjectMapper cfgMktAssetClassObjectMapper;

    @Autowired
    private CfgMktAssetClassDaoCustom cfgMktAssetClassDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_ASSET_CLASS, workbook,
                cfgMktAssetClassObjectMapper, cfgMktAssetClassDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktAssetClassSheet = ImportUtil.getSheet(MKT_ASSET_CLASS, workbook);
        cfgMktAssetClassObjectMapper.importData(mktAssetClassSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 24;
    }
}

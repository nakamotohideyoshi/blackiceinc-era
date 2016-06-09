package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktAssetClassMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktAssetClassMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktAssetClassMappingImportService implements CfgImportService {
    public static final String MKT_ASSET_CLASS_MAPPING = "MKT_ASSET_CLASS_MAPPING";

    @Autowired
    private CfgMktAssetClassMappingObjectMapper cfgMktAssetClassMappingObjectMapper;

    @Autowired
    private CfgMktAssetClassMappingDaoCustom cfgMktAssetClassMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_ASSET_CLASS_MAPPING, workbook,
                cfgMktAssetClassMappingObjectMapper, cfgMktAssetClassMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktAssetClassMappingSheet = ImportUtil.getSheet(MKT_ASSET_CLASS_MAPPING, workbook);
        cfgMktAssetClassMappingObjectMapper.importData(mktAssetClassMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 24;
    }
}

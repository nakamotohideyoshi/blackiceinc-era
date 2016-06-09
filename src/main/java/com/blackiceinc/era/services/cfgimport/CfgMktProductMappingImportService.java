package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktProductMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktProductMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktProductMappingImportService implements CfgImportService {
    public static final String MKT_PRODUCT_MAPPING = "MKT_PRODUCT_MAPPING";

    @Autowired
    private CfgMktProductMappingObjectMapper cfgMktProductMappingObjectMapper;

    @Autowired
    private CfgMktProductMappingDaoCustom cfgMktProductMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_PRODUCT_MAPPING, workbook,
                cfgMktProductMappingObjectMapper, cfgMktProductMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktProductMappingSheet = ImportUtil.getSheet(MKT_PRODUCT_MAPPING, workbook);
        cfgMktProductMappingObjectMapper.importData(mktProductMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 24;
    }
}

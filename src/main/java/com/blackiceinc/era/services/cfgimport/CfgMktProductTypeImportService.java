package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktProductTypeDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktProductTypeObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktProductTypeImportService implements CfgImportService {
    public static final String MKT_PRODUCT_TYPE = "MKT_PRODUCT_TYPE";

    @Autowired
    private CfgMktProductTypeObjectMapper cfgMktProductTypeObjectMapper;

    @Autowired
    private CfgMktProductTypeDaoCustom cfgMktProductTypeDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_PRODUCT_TYPE, workbook,
                cfgMktProductTypeObjectMapper, cfgMktProductTypeDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktProductTypeSheet = ImportUtil.getSheet(MKT_PRODUCT_TYPE, workbook);
        cfgMktProductTypeObjectMapper.importData(mktProductTypeSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 21;
    }
}

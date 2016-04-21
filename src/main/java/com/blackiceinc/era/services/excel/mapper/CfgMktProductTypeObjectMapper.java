package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktProductType;
import com.blackiceinc.era.persistence.erau.repository.CfgMktProductTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktProductTypeObjectMapper extends AbstractObjectMapper<CfgMktProductType> {

    private CfgMktProductTypeRepository cfgMktProductTypeRepository;

    @Autowired
    public CfgMktProductTypeObjectMapper(CfgMktProductTypeRepository cfgMktProductTypeRepository) {
        this.cfgMktProductTypeRepository = cfgMktProductTypeRepository;
    }

    @Override
    CfgMktProductType createRow(Row row) {
        CfgMktProductType cfgMktProductType = new CfgMktProductType();

        cfgMktProductType.setMktProductType(getStringValue(row.getCell(0)));
        cfgMktProductType.setMktProductDesc(getStringValue(row.getCell(1)));
        cfgMktProductType.setMktProductCategory(getStringValue(row.getCell(2)));

        return cfgMktProductType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktProductType> all = cfgMktProductTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktProductType cfgMktProductType : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktProductType.getMktProductType());
            createCell(row, 1, cfgMktProductType.getMktProductDesc());
            createCell(row, 2, cfgMktProductType.getMktProductCategory());

            rowIndex++;
        }
    }
}

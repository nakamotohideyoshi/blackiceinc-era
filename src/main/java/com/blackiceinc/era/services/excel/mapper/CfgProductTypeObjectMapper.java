package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgProductType;
import com.blackiceinc.era.persistence.erau.repository.CfgProductTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgProductTypeObjectMapper extends AbstractObjectMapper<CfgProductType> {

    private CfgProductTypeRepository cfgProductTypeRepository;

    @Autowired
    public CfgProductTypeObjectMapper(CfgProductTypeRepository cfgProductTypeRepository) {
        this.cfgProductTypeRepository = cfgProductTypeRepository;
    }

    @Override
    CfgProductType createRow(Row row) {
        CfgProductType cfgProductType = new CfgProductType();

        cfgProductType.setEraProductType(getStringValue(row.getCell(0)));
        cfgProductType.setEraProductDesc(getStringValue(row.getCell(1)));
        cfgProductType.setEraProductCategory(getStringValue(row.getCell(2)));

        return cfgProductType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgProductType> all = cfgProductTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgProductType cfgProductType : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgProductType.getEraProductType());
            createCell(row, 1, cfgProductType.getEraProductDesc());
            createCell(row, 2, cfgProductType.getEraProductCategory());

            rowIndex++;
        }
    }
}

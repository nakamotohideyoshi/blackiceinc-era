package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgProductType;
import com.blackiceinc.era.persistence.erau.repository.CfgProductTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgProductTypeObjectMapper {

    CfgProductTypeRepository cfgProductTypeRepository;

    @Autowired
    public CfgProductTypeObjectMapper(CfgProductTypeRepository cfgProductTypeRepository){
        this.cfgProductTypeRepository = cfgProductTypeRepository;
    }

    public List<CfgProductType> extractCfgProductTypes(XSSFSheet sheet) {
        List<CfgProductType> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgProductTypeObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgProductType createCfgProductTypeObj(Row row) {

        CfgProductType cfgProductType = new CfgProductType();

        cfgProductType.setEraProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgProductType.setEraProductDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgProductType.setEraProductCategory(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgProductType;
    }

    public void importCfgProductTypes(XSSFSheet sheet) {
        List<CfgProductType> all = cfgProductTypeRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgProductType cfgProductType:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgProductType.getEraProductType());
            row.createCell(1).setCellValue(cfgProductType.getEraProductDesc());
            row.createCell(2).setCellValue(cfgProductType.getEraProductCategory());

            rowIndex++;
        }
    }
}

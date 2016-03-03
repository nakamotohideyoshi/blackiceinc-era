package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktProductType;
import com.blackiceinc.era.persistence.erau.repository.CfgMktProductTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktProductTypeObjectMapper {

    CfgMktProductTypeRepository cfgMktProductTypeRepository;

    @Autowired
    public CfgMktProductTypeObjectMapper(CfgMktProductTypeRepository cfgMktProductTypeRepository){
        this.cfgMktProductTypeRepository = cfgMktProductTypeRepository;
    }

    public List<CfgMktProductType> extractData(XSSFSheet sheet) {
        List<CfgMktProductType> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createRow(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgMktProductType createRow(Row row) {

        CfgMktProductType cfgMktProductType = new CfgMktProductType();

        cfgMktProductType.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktProductType.setMktProductDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktProductType.setMktProductCategory(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgMktProductType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktProductType> all = cfgMktProductTypeRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktProductType cfgMktProductType:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktProductType.getMktProductType());
            row.createCell(1).setCellValue(cfgMktProductType.getMktProductDesc());
            row.createCell(2).setCellValue(cfgMktProductType.getMktProductCategory());

            rowIndex++;
        }
    }
}

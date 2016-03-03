package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgOpsProductType;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgOpsProductTypeObjectMapper {

    CfgOpsProductTypeRepository cfgOpsProductTypeRepository;

    @Autowired
    public CfgOpsProductTypeObjectMapper(CfgOpsProductTypeRepository cfgOpsProductTypeRepository){
        this.cfgOpsProductTypeRepository = cfgOpsProductTypeRepository;
    }

    public List<CfgOpsProductType> extractData(XSSFSheet sheet) {
        List<CfgOpsProductType> result = new ArrayList<>();

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

    private CfgOpsProductType createRow(Row row) {

        CfgOpsProductType cfgOpsProductType = new CfgOpsProductType();

        cfgOpsProductType.setOpsProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgOpsProductType.setOpsProductDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgOpsProductType.setOpsBusIndicator(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgOpsProductType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsProductType> all = cfgOpsProductTypeRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsProductType cfgOpsProductType:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgOpsProductType.getOpsProductType());
            row.createCell(1).setCellValue(cfgOpsProductType.getOpsProductDesc());
            row.createCell(2).setCellValue(cfgOpsProductType.getOpsBusIndicator());

            rowIndex++;
        }
    }
}

package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckDef;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassCheckDefRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgReclassCheckDefObjectMapper {

    CfgReclassCheckDefRepository cfgReclassCheckDefRepository;

    @Autowired
    public CfgReclassCheckDefObjectMapper(CfgReclassCheckDefRepository cfgReclassCheckDefRepository){
        this.cfgReclassCheckDefRepository = cfgReclassCheckDefRepository;
    }

    public List<CfgReclassCheckDef> extractData(XSSFSheet sheet) {
        List<CfgReclassCheckDef> result = new ArrayList<>();

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

    private CfgReclassCheckDef createRow(Row row) {

        CfgReclassCheckDef cfgReclassCheckDef = new CfgReclassCheckDef();

        cfgReclassCheckDef.setCheckDefNo(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgReclassCheckDef.setDescription(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgReclassCheckDef.setCheckType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgReclassCheckDef.setOperator(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgReclassCheckDef.setThreshold(row.getCell(4) != null ? row.getCell(4).getNumericCellValue() : null);
        cfgReclassCheckDef.setCurrency(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

        return cfgReclassCheckDef;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgReclassCheckDef> all = cfgReclassCheckDefRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgReclassCheckDef cfgReclassCheckDef:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgReclassCheckDef.getCheckDefNo());
            row.createCell(1).setCellValue(cfgReclassCheckDef.getDescription());
            row.createCell(2).setCellValue(cfgReclassCheckDef.getCheckType());
            row.createCell(3).setCellValue(cfgReclassCheckDef.getOperator());
            row.createCell(4).setCellValue(cfgReclassCheckDef.getThreshold());
            row.createCell(5).setCellValue(cfgReclassCheckDef.getCurrency());

            rowIndex++;
        }
    }
}

package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgReclass;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgReclassObjectMapper {

    CfgReclassRepository cfgReclassRepository;

    @Autowired
    public CfgReclassObjectMapper(CfgReclassRepository cfgReclassRepository){
        this.cfgReclassRepository = cfgReclassRepository;
    }

    public List<CfgReclass> extractData(XSSFSheet sheet) {
        List<CfgReclass> result = new ArrayList<>();

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

    private CfgReclass createRow(Row row) {

        CfgReclass cfgReclass = new CfgReclass();

        cfgReclass.setDescription(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgReclass.setEraEntityTypeIn(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgReclass.setEraProductTypeIn(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgReclass.setCheck(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgReclass.setEraEntityTypeOut(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgReclass.setEraProductTypeOut(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

        return cfgReclass;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgReclass> all = cfgReclassRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgReclass cfgReclass:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgReclass.getDescription());
            row.createCell(1).setCellValue(cfgReclass.getEraEntityTypeIn());
            row.createCell(2).setCellValue(cfgReclass.getEraProductTypeIn());
            row.createCell(3).setCellValue(cfgReclass.getCheck());
            row.createCell(4).setCellValue(cfgReclass.getEraEntityTypeOut());
            row.createCell(5).setCellValue(cfgReclass.getEraProductTypeOut());

            rowIndex++;
        }
    }
}

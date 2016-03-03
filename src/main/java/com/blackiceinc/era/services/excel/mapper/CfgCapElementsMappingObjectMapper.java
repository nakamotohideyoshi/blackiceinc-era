package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsMapping;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCapElementsMappingObjectMapper {

    CfgCapElementsMappingRepository cfgCapElementsMappingRepository;

    @Autowired
    public CfgCapElementsMappingObjectMapper(CfgCapElementsMappingRepository cfgCapElementsMappingRepository){
        this.cfgCapElementsMappingRepository = cfgCapElementsMappingRepository;
    }

    public List<CfgCapElementsMapping> extractData(XSSFSheet sheet) {
        List<CfgCapElementsMapping> result = new ArrayList<>();

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

    private CfgCapElementsMapping createRow(Row row) {

        CfgCapElementsMapping cfgCapElementsMapping = new CfgCapElementsMapping();

        cfgCapElementsMapping.setCapElements(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCapElementsMapping.setGlCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCapElementsMapping.setNote(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgCapElementsMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsMapping> all = cfgCapElementsMappingRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsMapping cfgCapElementsMapping:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCapElementsMapping.getCapElements());
            row.createCell(1).setCellValue(cfgCapElementsMapping.getGlCode());
            row.createCell(2).setCellValue(cfgCapElementsMapping.getNote());

            rowIndex++;
        }
    }
}

package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElements;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCapElementsObjectMapper {

    CfgCapElementsRepository cfgCapElementsRepository;

    @Autowired
    public CfgCapElementsObjectMapper(CfgCapElementsRepository cfgCapElementsRepository){
        this.cfgCapElementsRepository = cfgCapElementsRepository;
    }

    public List<CfgCapElements> extractData(XSSFSheet sheet) {
        List<CfgCapElements> result = new ArrayList<>();

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

    private CfgCapElements createRow(Row row) {

        CfgCapElements cfgCapElements = new CfgCapElements();

        cfgCapElements.setCapElements(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCapElements.setCapElementsDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCapElements.setType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgCapElements;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElements> all = cfgCapElementsRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElements cfgCapElements:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCapElements.getCapElements());
            row.createCell(1).setCellValue(cfgCapElements.getCapElementsDesc());
            row.createCell(2).setCellValue(cfgCapElements.getType());

            rowIndex++;
        }
    }
}

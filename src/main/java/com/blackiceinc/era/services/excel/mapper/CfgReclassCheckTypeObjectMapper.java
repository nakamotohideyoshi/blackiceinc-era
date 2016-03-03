package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckType;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassCheckTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgReclassCheckTypeObjectMapper {

    CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository;

    @Autowired
    public CfgReclassCheckTypeObjectMapper(CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository){
        this.cfgReclassCheckTypeRepository = cfgReclassCheckTypeRepository;
    }

    public List<CfgReclassCheckType> extractData(XSSFSheet sheet) {
        List<CfgReclassCheckType> result = new ArrayList<>();

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

    private CfgReclassCheckType createRow(Row row) {

        CfgReclassCheckType cfgReclassCheckType = new CfgReclassCheckType();

        cfgReclassCheckType.setCheckDescription(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgReclassCheckType.setWhereClause(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgReclassCheckType.setConsoField(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgReclassCheckType.setAmtField(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);

        return cfgReclassCheckType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgReclassCheckType> all = cfgReclassCheckTypeRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgReclassCheckType cfgReclassCheckType:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgReclassCheckType.getCheckDescription());
            row.createCell(1).setCellValue(cfgReclassCheckType.getWhereClause());
            row.createCell(2).setCellValue(cfgReclassCheckType.getConsoField());
            row.createCell(3).setCellValue(cfgReclassCheckType.getAmtField());

            rowIndex++;
        }
    }
}

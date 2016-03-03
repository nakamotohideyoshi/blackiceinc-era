package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircut;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgCrmHaircutRepository;
import com.blackiceinc.era.persistence.erau.repository.CfgFinancialBookRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCrmHaircutObjectMapper {

    CfgCrmHaircutRepository cfgCrmHaircutRepository;

    @Autowired
    public CfgCrmHaircutObjectMapper(CfgCrmHaircutRepository cfgCrmHaircutRepository){
        this.cfgCrmHaircutRepository = cfgCrmHaircutRepository;
    }

    public List<CfgCrmHaircut> extractData(XSSFSheet sheet) {
        List<CfgCrmHaircut> result = new ArrayList<>();

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

    private CfgCrmHaircut createRow(Row row) {

        CfgCrmHaircut cfgCrmHaircut = new CfgCrmHaircut();

        cfgCrmHaircut.setEraColType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCrmHaircut.setEraEntityType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCrmHaircut.setRiskBucket(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgCrmHaircut.setMinResidualMaturity(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgCrmHaircut.setMaxResidualMaturity(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgCrmHaircut.setHaircut(row.getCell(5) != null ? row.getCell(5).getNumericCellValue() : null);

        return cfgCrmHaircut;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCrmHaircut> all = cfgCrmHaircutRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCrmHaircut cfgCrmHaircut:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCrmHaircut.getEraColType());
            row.createCell(1).setCellValue(cfgCrmHaircut.getEraEntityType());
            row.createCell(2).setCellValue(cfgCrmHaircut.getRiskBucket());
            row.createCell(3).setCellValue(cfgCrmHaircut.getMinResidualMaturity());
            row.createCell(4).setCellValue(cfgCrmHaircut.getMaxResidualMaturity());
            row.createCell(5).setCellValue(cfgCrmHaircut.getHaircut());

            rowIndex++;
        }
    }
}

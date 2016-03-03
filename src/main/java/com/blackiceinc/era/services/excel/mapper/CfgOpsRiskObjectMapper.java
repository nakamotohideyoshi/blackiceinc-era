package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgOpsRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsRiskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgOpsRiskObjectMapper {

    CfgOpsRiskRepository cfgOpsRiskRepository;

    @Autowired
    public CfgOpsRiskObjectMapper(CfgOpsRiskRepository cfgOpsRiskRepository){
        this.cfgOpsRiskRepository = cfgOpsRiskRepository;
    }

    public List<CfgOpsRisk> extractData(XSSFSheet sheet) {
        List<CfgOpsRisk> result = new ArrayList<>();

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

    private CfgOpsRisk createRow(Row row) {

        CfgOpsRisk cfgOpsRisk = new CfgOpsRisk();

        cfgOpsRisk.setCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgOpsRisk.setRiskWeight(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);

        return cfgOpsRisk;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsRisk> all = cfgOpsRiskRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsRisk cfgOpsRisk:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgOpsRisk.getCode());
            row.createCell(1).setCellValue(cfgOpsRisk.getRiskWeight());

            rowIndex++;
        }
    }
}

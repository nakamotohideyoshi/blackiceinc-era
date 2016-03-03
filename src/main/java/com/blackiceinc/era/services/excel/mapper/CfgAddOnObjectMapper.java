package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgAddOn;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgAddOnRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgAddOnObjectMapper {

    CfgAddOnRepository cfgAddOnRepository;

    @Autowired
    public CfgAddOnObjectMapper(CfgAddOnRepository cfgAddOnRepository){
        this.cfgAddOnRepository = cfgAddOnRepository;
    }

    public List<CfgAddOn> extractData(XSSFSheet sheet) {
        List<CfgAddOn> result = new ArrayList<>();

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

    private CfgAddOn createRow(Row row) {

        CfgAddOn cfgAddOn = new CfgAddOn();

        cfgAddOn.setEraProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgAddOn.setMaturityStart(row.getCell(1) != null ? Long.valueOf(row.getCell(1).getStringCellValue()) : null);
        cfgAddOn.setMaturityEnd(row.getCell(2) != null ? Long.valueOf(row.getCell(2).getStringCellValue()) : null);
        cfgAddOn.setRiskWeight(row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null);

        return cfgAddOn;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgAddOn> all = cfgAddOnRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgAddOn cfgAddOn:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgAddOn.getEraProductType());
            row.createCell(1).setCellValue(cfgAddOn.getMaturityStart());
            row.createCell(2).setCellValue(cfgAddOn.getMaturityEnd());
            row.createCell(3).setCellValue(cfgAddOn.getRiskWeight());

            rowIndex++;
        }
    }
}

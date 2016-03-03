package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgProductTypeMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgProductTypeMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgProductTypeMappingObjectMapper {

    CfgProductTypeMappingRepository cfgProductTypeMappingRepository;

    @Autowired
    public CfgProductTypeMappingObjectMapper(CfgProductTypeMappingRepository cfgProductTypeMappingRepository){
        this.cfgProductTypeMappingRepository = cfgProductTypeMappingRepository;
    }

    public List<CfgProductTypeMapping> extractCfgProductTypeMappping(XSSFSheet sheet) {
        List<CfgProductTypeMapping> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgProductTypeMappingObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgProductTypeMapping createCfgProductTypeMappingObj(Row row) {

        CfgProductTypeMapping cfgProductTypeMapping = new CfgProductTypeMapping();

        cfgProductTypeMapping.setProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgProductTypeMapping.setTableName(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgProductTypeMapping.setSeniority(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgProductTypeMapping.setEraContractType(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgProductTypeMapping.setOnOff(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgProductTypeMapping.setfMainIndex(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);
        cfgProductTypeMapping.setfRecogExch(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);
        cfgProductTypeMapping.setfRated(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : null);
        // repayment property suspicious
        cfgProductTypeMapping.setRepaymentProperty(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : null);
        cfgProductTypeMapping.setfCompleted(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : null);
        cfgProductTypeMapping.setfIndependantValuer(row.getCell(10) != null ? row.getCell(10).getStringCellValue() : null);
        cfgProductTypeMapping.setfLegallyEnforce(row.getCell(11) != null ? row.getCell(11).getStringCellValue() : null);
        cfgProductTypeMapping.setUnderlying(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : null);
        cfgProductTypeMapping.setSeq(row.getCell(13) != null ? Long.valueOf(row.getCell(13).getStringCellValue()) : null);

        return cfgProductTypeMapping;
    }

    public void importCfgProductTypeMappping(XSSFSheet sheet) {
        List<CfgProductTypeMapping> all = cfgProductTypeMappingRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgProductTypeMapping cfgFinancialBook:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgFinancialBook.getProductType());
            row.createCell(1).setCellValue(cfgFinancialBook.getTableName());
            row.createCell(2).setCellValue(cfgFinancialBook.getSeniority());
            row.createCell(3).setCellValue(cfgFinancialBook.getEraContractType());
            row.createCell(4).setCellValue(cfgFinancialBook.getOnOff());
            row.createCell(5).setCellValue(cfgFinancialBook.getfMainIndex());
            row.createCell(6).setCellValue(cfgFinancialBook.getfRecogExch());
            row.createCell(7).setCellValue(cfgFinancialBook.getfRated());
            row.createCell(8).setCellValue(cfgFinancialBook.getRepaymentProperty());
            row.createCell(9).setCellValue(cfgFinancialBook.getfCompleted());
            row.createCell(10).setCellValue(cfgFinancialBook.getfIndependantValuer());
            row.createCell(11).setCellValue(cfgFinancialBook.getfLegallyEnforce());
            row.createCell(12).setCellValue(cfgFinancialBook.getUnderlying());
            row.createCell(13).setCellValue(cfgFinancialBook.getSeq());

            rowIndex++;
        }
    }
}

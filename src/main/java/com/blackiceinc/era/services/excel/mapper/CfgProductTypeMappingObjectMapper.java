package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgProductTypeMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgProductTypeMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgProductTypeMappingObjectMapper extends AbstractObjectMapper {

    CfgProductTypeMappingRepository cfgProductTypeMappingRepository;

    @Autowired
    public CfgProductTypeMappingObjectMapper(CfgProductTypeMappingRepository cfgProductTypeMappingRepository) {
        this.cfgProductTypeMappingRepository = cfgProductTypeMappingRepository;
    }

    CfgProductTypeMapping createRow(Row row) {
        CfgProductTypeMapping cfgProductTypeMapping = new CfgProductTypeMapping();

        cfgProductTypeMapping.setProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgProductTypeMapping.setTableName(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgProductTypeMapping.setSeniority(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgProductTypeMapping.setEraContractType(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgProductTypeMapping.setOnOff(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgProductTypeMapping.setfMainIndex(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);
        cfgProductTypeMapping.setfRecogExch(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);
        cfgProductTypeMapping.setfRated(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : null);
        // TODO: repayment property suspicious
        cfgProductTypeMapping.setRepaymentProperty(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : null);
        cfgProductTypeMapping.setfCompleted(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : null);
        cfgProductTypeMapping.setfIndependantValuer(row.getCell(10) != null ? row.getCell(10).getStringCellValue() : null);
        cfgProductTypeMapping.setfLegallyEnforce(row.getCell(11) != null ? row.getCell(11).getStringCellValue() : null);
        cfgProductTypeMapping.setUnderlying(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : null);
        cfgProductTypeMapping.setSeq(row.getCell(13) != null ? (long) row.getCell(13).getNumericCellValue() : null);

        return cfgProductTypeMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgProductTypeMapping> all = cfgProductTypeMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgProductTypeMapping cfgFinancialBook : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgFinancialBook.getProductType());
            createCell(row, 1, cfgFinancialBook.getTableName());
            createCell(row, 2, cfgFinancialBook.getSeniority());
            createCell(row, 3, cfgFinancialBook.getEraContractType());
            createCell(row, 4, cfgFinancialBook.getOnOff());
            createCell(row, 5, cfgFinancialBook.getfMainIndex());
            createCell(row, 6, cfgFinancialBook.getfRecogExch());
            createCell(row, 7, cfgFinancialBook.getfRated());
            createCell(row, 8, cfgFinancialBook.getRepaymentProperty());
            createCell(row, 9, cfgFinancialBook.getfCompleted());
            createCell(row, 10, cfgFinancialBook.getfIndependantValuer());
            createCell(row, 11, cfgFinancialBook.getfLegallyEnforce());
            createCell(row, 12, cfgFinancialBook.getUnderlying());
            createCell(row, 13, cfgFinancialBook.getSeq());

            rowIndex++;
        }
    }
}

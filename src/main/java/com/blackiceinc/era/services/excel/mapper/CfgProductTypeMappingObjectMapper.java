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
public class CfgProductTypeMappingObjectMapper extends AbstractObjectMapper<CfgProductTypeMapping> {

    CfgProductTypeMappingRepository cfgProductTypeMappingRepository;

    @Autowired
    public CfgProductTypeMappingObjectMapper(CfgProductTypeMappingRepository cfgProductTypeMappingRepository) {
        this.cfgProductTypeMappingRepository = cfgProductTypeMappingRepository;
    }

    @Override
    CfgProductTypeMapping createRow(Row row) {
        CfgProductTypeMapping cfgProductTypeMapping = new CfgProductTypeMapping();

        cfgProductTypeMapping.setProductType(getStringValue(row.getCell(0)));
        cfgProductTypeMapping.setTableName(getStringValue(row.getCell(1)));
        cfgProductTypeMapping.setSeniority(getStringValue(row.getCell(2)));
        cfgProductTypeMapping.setEraContractType(getStringValue(row.getCell(3)));
        cfgProductTypeMapping.setOnOff(getStringValue(row.getCell(4)));
        cfgProductTypeMapping.setfMainIndex(getStringValue(row.getCell(5)));
        cfgProductTypeMapping.setfRecogExch(getStringValue(row.getCell(6)));
        cfgProductTypeMapping.setfRated(getStringValue(row.getCell(7)));
        // TODO: repayment property suspicious
        cfgProductTypeMapping.setRepaymentProperty(getStringValue(row.getCell(8)));
        cfgProductTypeMapping.setfCompleted(getStringValue(row.getCell(9)));
        cfgProductTypeMapping.setfIndependantValuer(getStringValue(row.getCell(10)));
        cfgProductTypeMapping.setfLegallyEnforce(getStringValue(row.getCell(11)));
        cfgProductTypeMapping.setUnderlying(getStringValue(row.getCell(12)));
        cfgProductTypeMapping.setSeq(getLongValue(row.getCell(13)));

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

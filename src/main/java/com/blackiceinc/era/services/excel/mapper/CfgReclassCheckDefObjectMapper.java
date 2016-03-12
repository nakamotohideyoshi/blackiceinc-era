package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckDef;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassCheckDefRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgReclassCheckDefObjectMapper extends AbstractObjectMapper {

    CfgReclassCheckDefRepository cfgReclassCheckDefRepository;

    @Autowired
    public CfgReclassCheckDefObjectMapper(CfgReclassCheckDefRepository cfgReclassCheckDefRepository) {
        this.cfgReclassCheckDefRepository = cfgReclassCheckDefRepository;
    }

    CfgReclassCheckDef createRow(Row row) {
        CfgReclassCheckDef cfgReclassCheckDef = new CfgReclassCheckDef();

        cfgReclassCheckDef.setCheckDefNo(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgReclassCheckDef.setDescription(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgReclassCheckDef.setCheckType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgReclassCheckDef.setOperator(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgReclassCheckDef.setThreshold(row.getCell(4) != null ? row.getCell(4).getNumericCellValue() : null);
        cfgReclassCheckDef.setCurrency(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

        return cfgReclassCheckDef;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgReclassCheckDef> all = cfgReclassCheckDefRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgReclassCheckDef cfgReclassCheckDef : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgReclassCheckDef.getCheckDefNo());
            createCell(row, 1, cfgReclassCheckDef.getDescription());
            createCell(row, 2, cfgReclassCheckDef.getCheckType());
            createCell(row, 3, cfgReclassCheckDef.getOperator());
            createCell(row, 4, cfgReclassCheckDef.getThreshold());
            createCell(row, 5, cfgReclassCheckDef.getCurrency());

            rowIndex++;
        }
    }
}

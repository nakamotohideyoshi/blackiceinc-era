package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckType;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassCheckTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgReclassCheckTypeObjectMapper extends AbstractObjectMapper {

    CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository;

    @Autowired
    public CfgReclassCheckTypeObjectMapper(CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository) {
        this.cfgReclassCheckTypeRepository = cfgReclassCheckTypeRepository;
    }

    CfgReclassCheckType createRow(Row row) {
        CfgReclassCheckType cfgReclassCheckType = new CfgReclassCheckType();

        cfgReclassCheckType.setCheckType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgReclassCheckType.setCheckDescription(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgReclassCheckType.setWhereClause(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgReclassCheckType.setConsoField(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgReclassCheckType.setAmtField(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);

        return cfgReclassCheckType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgReclassCheckType> all = cfgReclassCheckTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgReclassCheckType cfgReclassCheckType : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgReclassCheckType.getCheckType());
            createCell(row, 1, cfgReclassCheckType.getCheckDescription());
            createCell(row, 2, cfgReclassCheckType.getWhereClause());
            createCell(row, 3, cfgReclassCheckType.getConsoField());
            createCell(row, 4, cfgReclassCheckType.getAmtField());

            rowIndex++;
        }
    }
}

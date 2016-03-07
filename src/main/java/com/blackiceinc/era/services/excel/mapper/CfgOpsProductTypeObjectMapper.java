package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgOpsProductType;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeRepository;
import com.blackiceinc.era.services.excel.mapper.exception.PrimaryColumnMappingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgOpsProductTypeObjectMapper extends AbstractObjectMapper {

    CfgOpsProductTypeRepository cfgOpsProductTypeRepository;

    @Autowired
    public CfgOpsProductTypeObjectMapper(CfgOpsProductTypeRepository cfgOpsProductTypeRepository) {
        this.cfgOpsProductTypeRepository = cfgOpsProductTypeRepository;
    }

    CfgOpsProductType createRow(Row row) throws PrimaryColumnMappingException {

        CfgOpsProductType cfgOpsProductType = new CfgOpsProductType();

        Cell cell0 = row.getCell(0);
        if (cell0 != null) {
            cfgOpsProductType.setOpsProductType(cell0.getStringCellValue());
        } else {
            log.warn("Null value for a primary column");
            throw new PrimaryColumnMappingException();
        }

        cfgOpsProductType.setOpsProductDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgOpsProductType.setOpsBusIndicator(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgOpsProductType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsProductType> all = cfgOpsProductTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsProductType cfgOpsProductType : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgOpsProductType.getOpsProductType());
            row.createCell(1).setCellValue(cfgOpsProductType.getOpsProductDesc());
            row.createCell(2).setCellValue(cfgOpsProductType.getOpsBusIndicator());

            rowIndex++;
        }
    }
}

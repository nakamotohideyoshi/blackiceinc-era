package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgReclass;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgReclassObjectMapper extends AbstractObjectMapper {

    CfgReclassRepository cfgReclassRepository;

    @Autowired
    public CfgReclassObjectMapper(CfgReclassRepository cfgReclassRepository) {
        this.cfgReclassRepository = cfgReclassRepository;
    }

    CfgReclass createRow(Row row) {
        CfgReclass cfgReclass = new CfgReclass();

        cfgReclass.setCheckNo(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgReclass.setDescription(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgReclass.setEraEntityTypeIn(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgReclass.setEraProductTypeIn(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgReclass.setCheck(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgReclass.setEraEntityTypeOut(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);
        cfgReclass.setEraProductTypeOut(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);

        return cfgReclass;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgReclass> all = cfgReclassRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgReclass cfgReclass : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgReclass.getCheckNo());
            createCell(row, 1, cfgReclass.getDescription());
            createCell(row, 2, cfgReclass.getEraEntityTypeIn());
            createCell(row, 3, cfgReclass.getEraProductTypeIn());
            createCell(row, 4, cfgReclass.getCheck());
            createCell(row, 5, cfgReclass.getEraEntityTypeOut());
            createCell(row, 6, cfgReclass.getEraProductTypeOut());

            rowIndex++;
        }
    }
}

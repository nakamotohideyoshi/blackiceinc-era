package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgReclass;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgReclassObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgReclassRepository cfgReclassRepository;

    @Autowired
    public CfgReclassObjectMapper(CfgReclassRepository cfgReclassRepository) {
        this.cfgReclassRepository = cfgReclassRepository;
    }

    @Override
    CfgReclass createRow(Row row) {
        CfgReclass cfgReclass = new CfgReclass();

        cfgReclass.setCheckNo(getStringValue(row.getCell(0)));
        cfgReclass.setDescription(getStringValue(row.getCell(1)));
        cfgReclass.setEraEntityTypeIn(getStringValue(row.getCell(2)));
        cfgReclass.setEraProductTypeIn(getStringValue(row.getCell(3)));
        cfgReclass.setCheck(getStringValue(row.getCell(4)));
        cfgReclass.setEraEntityTypeOut(getStringValue(row.getCell(5)));
        cfgReclass.setEraAssetClassOut(getStringValue(row.getCell(6)));

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
            createCell(row, 6, cfgReclass.getEraAssetClassOut());

            rowIndex++;
        }
    }
}

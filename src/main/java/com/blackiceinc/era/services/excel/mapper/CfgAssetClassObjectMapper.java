package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgAssetClass;
import com.blackiceinc.era.persistence.erau.repository.CfgAssetClassRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgAssetClassObjectMapper extends AbstractObjectMapper{

    CfgAssetClassRepository cfgAssetClassRepository;

    @Autowired
    public CfgAssetClassObjectMapper(CfgAssetClassRepository cfgAssetClassRepository){
        this.cfgAssetClassRepository = cfgAssetClassRepository;
    }

    CfgAssetClass createRow(Row row) {

        CfgAssetClass cfgAssetClass = new CfgAssetClass();

        cfgAssetClass.setEraAssetClass(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgAssetClass.setEraAssetClassDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgAssetClass;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgAssetClass> all = cfgAssetClassRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgAssetClass cfgAssetClass:all){
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgAssetClass.getEraAssetClass());
            createCell(row, 1, cfgAssetClass.getEraAssetClassDesc());

            rowIndex++;
        }
    }
}

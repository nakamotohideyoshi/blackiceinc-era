package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFxProdType;
import com.blackiceinc.era.persistence.erau.repository.CfgFxProdTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgFxProdTypeObjectMapper extends AbstractObjectMapper<CfgFxProdType> {

    CfgFxProdTypeRepository cfgFxProdTypeRepository;

    @Autowired
    public CfgFxProdTypeObjectMapper(CfgFxProdTypeRepository cfgFxProdTypeRepository) {
        this.cfgFxProdTypeRepository = cfgFxProdTypeRepository;
    }


    @Override
    CfgFxProdType createRow(Row row) {
        CfgFxProdType cfgFxProdType = new CfgFxProdType();

        cfgFxProdType.setFxProdType(getStringValue(row.getCell(0)));
        cfgFxProdType.setFxProdTypeDesc(getStringValue(row.getCell(1)));

        return cfgFxProdType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgFxProdType> all = cfgFxProdTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgFxProdType cfgFxProdType : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgFxProdType.getFxProdType());
            createCell(row, 1, cfgFxProdType.getFxProdTypeDesc());

            rowIndex++;
        }
    }
}

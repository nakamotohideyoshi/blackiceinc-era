package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgEntityType;
import com.blackiceinc.era.persistence.erau.repository.CfgEntityTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgEntityTypeObjectMapper extends AbstractObjectMapper<CfgEntityType> {

    private CfgEntityTypeRepository cfgEntityTypeRepository;

    @Autowired
    public CfgEntityTypeObjectMapper(CfgEntityTypeRepository cfgEntityTypeRepository) {
        this.cfgEntityTypeRepository = cfgEntityTypeRepository;
    }

    @Override
    CfgEntityType createRow(Row row) {
        CfgEntityType cfgEntityType = new CfgEntityType();

        cfgEntityType.setEraEntityType(getStringValue(row.getCell(0)));
        cfgEntityType.setEntityDesc(getStringValue(row.getCell(1)));

        return cfgEntityType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgEntityType> all = cfgEntityTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgEntityType cfgEntityType : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgEntityType.getEraEntityType());
            createCell(row, 1, cfgEntityType.getEntityDesc());

            rowIndex++;
        }
    }
}

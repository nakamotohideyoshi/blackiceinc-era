package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgEntityTypeMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgEntityTypeMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class CfgEntityTypeMappingObjectMapper extends AbstractObjectMapper<CfgEntityTypeMapping> {

    CfgEntityTypeMappingRepository cfgEntityTypeMappingRepository;

    @Autowired
    public CfgEntityTypeMappingObjectMapper(CfgEntityTypeMappingRepository cfgEntityTypeMappingRepository) {
        this.cfgEntityTypeMappingRepository = cfgEntityTypeMappingRepository;
    }

    @Override
    CfgEntityTypeMapping createRow(Row row) {
        CfgEntityTypeMapping cfgEntityTypeMapping = new CfgEntityTypeMapping();

        cfgEntityTypeMapping.setEraEntityType(getStringValue(row.getCell(0)));
        cfgEntityTypeMapping.setCustomerType(getStringValue(row.getCell(1)));
        cfgEntityTypeMapping.setCustomerSubType(getStringValue(row.getCell(2)));

        return cfgEntityTypeMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgEntityTypeMapping> all = cfgEntityTypeMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;

        Iterator<CfgEntityTypeMapping> iterator = all.iterator();
        while (iterator.hasNext()) {
            CfgEntityTypeMapping cfgEntityTypeMapping = iterator.next();
            if (cfgEntityTypeMapping != null) {
                XSSFRow row = sheet.createRow(rowIndex);

                createCell(row, 0, cfgEntityTypeMapping.getEraEntityType());
                createCell(row, 1, cfgEntityTypeMapping.getCustomerType());
                createCell(row, 2, cfgEntityTypeMapping.getCustomerSubType());

                rowIndex++;
            }
        }
    }
}

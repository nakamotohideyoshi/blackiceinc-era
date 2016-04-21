package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsType;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCapElementsTypeObjectMapper extends AbstractObjectMapper<CfgCapElementsType> {

    private CfgCapElementsTypeRepository cfgCapElementsTypeRepository;

    @Autowired
    public CfgCapElementsTypeObjectMapper(CfgCapElementsTypeRepository cfgCapElementsTypeRepository) {
        this.cfgCapElementsTypeRepository = cfgCapElementsTypeRepository;
    }

    @Override
    CfgCapElementsType createRow(Row row) {
        CfgCapElementsType cfgCapElementsType = new CfgCapElementsType();

        cfgCapElementsType.setCapElementType(getStringValue(row.getCell(0)));
        cfgCapElementsType.setDescription(getStringValue(row.getCell(1)));

        return cfgCapElementsType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsType> all = cfgCapElementsTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsType cfgCapElementsType : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCapElementsType.getCapElementType());
            createCell(row, 1, cfgCapElementsType.getDescription());

            rowIndex++;
        }
    }
}

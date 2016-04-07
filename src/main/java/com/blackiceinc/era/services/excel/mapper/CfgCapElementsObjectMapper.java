package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElements;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCapElementsObjectMapper extends AbstractObjectMapper<CfgCapElements> {

    CfgCapElementsRepository cfgCapElementsRepository;

    @Autowired
    public CfgCapElementsObjectMapper(CfgCapElementsRepository cfgCapElementsRepository) {
        this.cfgCapElementsRepository = cfgCapElementsRepository;
    }

    @Override
    CfgCapElements createRow(Row row) {
        CfgCapElements cfgCapElements = new CfgCapElements();

        cfgCapElements.setCapElements(getStringValue(row.getCell(0)));
        cfgCapElements.setCapElementsDesc(getStringValue(row.getCell(1)));
        cfgCapElements.setType(getStringValue(row.getCell(2)));

        return cfgCapElements;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElements> all = cfgCapElementsRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElements cfgCapElements : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCapElements.getCapElements());
            createCell(row, 1, cfgCapElements.getCapElementsDesc());
            createCell(row, 2, cfgCapElements.getType());

            rowIndex++;
        }
    }
}

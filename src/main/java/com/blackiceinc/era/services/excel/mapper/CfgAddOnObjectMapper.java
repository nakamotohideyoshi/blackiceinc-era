package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgAddOn;
import com.blackiceinc.era.persistence.erau.repository.CfgAddOnRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgAddOnObjectMapper extends AbstractObjectMapper {

    private final Logger log = LoggerFactory.getLogger(CfgAddOnObjectMapper.class);

    CfgAddOnRepository cfgAddOnRepository;

    @Autowired
    public CfgAddOnObjectMapper(CfgAddOnRepository cfgAddOnRepository) {
        this.cfgAddOnRepository = cfgAddOnRepository;
    }

    CfgAddOn createRow(Row row) {

        CfgAddOn cfgAddOn = new CfgAddOn();

        cfgAddOn.setEraProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);

        Cell cell1 = row.getCell(1);
        if (cell1 != null) {
            switch (cell1.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgAddOn.setMaturityStart(String.valueOf((long) cell1.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgAddOn.setMaturityStart(cell1.getStringCellValue());
                    break;
            }
        }

        Cell cell2 = row.getCell(2);
        if (cell2 != null) {
            switch (cell2.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgAddOn.setMaturityEnd(String.valueOf((long) cell2.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    try {
                        cfgAddOn.setMaturityEnd(cell2.getStringCellValue());
                    } catch (NumberFormatException ex) {
                        log.error("Error mapping excel value into database. Sheet : {}, row : {}, column : {}",
                                row.getSheet().getSheetName(), row.getRowNum(), cell2.getColumnIndex(), ex);
                    }
                    break;
            }
        }

        cfgAddOn.setRiskWeight(row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null);

        return cfgAddOn;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgAddOn> all = cfgAddOnRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgAddOn cfgAddOn : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            createCell(row, 0, cfgAddOn.getEraProductType());
            createCell(row, 1, cfgAddOn.getMaturityStart());
            createCell(row, 2, cfgAddOn.getMaturityEnd());
            createCell(row, 3, cfgAddOn.getRiskWeight());

            rowIndex++;
        }
    }


}

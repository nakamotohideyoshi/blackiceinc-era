package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrInter;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrInterRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrGnrInterObjectMapper extends AbstractObjectMapper {

    CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;

    @Autowired
    public CfgMktIrrGnrInterObjectMapper(CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository) {
        this.cfgMktIrrGnrInterRepository = cfgMktIrrGnrInterRepository;
    }

    CfgMktIrrGnrInter createRow(Row row) {
        CfgMktIrrGnrInter cfgMktIrrGnrInter = new CfgMktIrrGnrInter();

        Cell cell0 = row.getCell(0);
        if (cell0 != null) {
            switch (cell0.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrInter.setCode(String.valueOf((long) cell0.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrInter.setCode(cell0.getStringCellValue());
                    break;
            }
        }

        Cell cell1 = row.getCell(1);
        if (cell1 != null) {
            switch (cell1.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrInter.setZoneCode1(String.valueOf((long) cell1.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrInter.setZoneCode1(cell1.getStringCellValue());
                    break;
            }
        }


        Cell cell2 = row.getCell(2);
        if (cell2 != null) {
            switch (cell2.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrInter.setZoneCode2(String.valueOf((long) cell2.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrInter.setZoneCode2(cell2.getStringCellValue());
                    break;
            }
        }

        cfgMktIrrGnrInter.setRiskWeight(row.getCell(3).getNumericCellValue());

        return cfgMktIrrGnrInter;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrInter> all = cfgMktIrrGnrInterRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrInter cfgMktIrrGnrInter : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrGnrInter.getCode());
            createCell(row, 1, cfgMktIrrGnrInter.getZoneCode1());
            createCell(row, 2, cfgMktIrrGnrInter.getZoneCode2());
            createCell(row, 3, cfgMktIrrGnrInter.getRiskWeight());

            rowIndex++;
        }
    }
}

package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrIntra;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrIntraRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrGnrIntraObjectMapper extends AbstractObjectMapper {

    CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;

    @Autowired
    public CfgMktIrrGnrIntraObjectMapper(CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository) {
        this.cfgMktIrrGnrIntraRepository = cfgMktIrrGnrIntraRepository;
    }

    CfgMktIrrGnrIntra createRow(Row row) {
        CfgMktIrrGnrIntra cfgMktIrrGnrIntra = new CfgMktIrrGnrIntra();

        Cell cell0 = row.getCell(0);
        if (cell0 != null) {
            switch (cell0.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrIntra.setCode(String.valueOf((long) cell0.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrIntra.setCode(cell0.getStringCellValue());
                    break;
            }
        }

        Cell cell1 = row.getCell(1);
        if (cell1 != null) {
            switch (cell1.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrIntra.setZoneCode(String.valueOf((long) cell1.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrIntra.setZoneCode(cell1.getStringCellValue());
                    break;
            }
        }


        cfgMktIrrGnrIntra.setRiskWeight(row.getCell(2) != null ? row.getCell(2).getNumericCellValue() : null);

        return cfgMktIrrGnrIntra;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrIntra> all = cfgMktIrrGnrIntraRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrIntra cfgMktIrrGnrIntra : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrGnrIntra.getCode());
            createCell(row, 1, cfgMktIrrGnrIntra.getZoneCode());
            createCell(row, 2, cfgMktIrrGnrIntra.getRiskWeight());

            rowIndex++;
        }
    }
}

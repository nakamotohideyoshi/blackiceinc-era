package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrBand;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrBandRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrGnrBandObjectMapper extends AbstractObjectMapper {

    CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository;

    @Autowired
    public CfgMktIrrGnrBandObjectMapper(CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository) {
        this.cfgMktIrrGnrBandRepository = cfgMktIrrGnrBandRepository;
    }

    CfgMktIrrGnrBand createRow(Row row) {
        CfgMktIrrGnrBand cfgMktIrrGnrBand = new CfgMktIrrGnrBand();

        Cell cell0 = row.getCell(0);
        if (cell0 != null) {
            switch (cell0.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrBand.setCode(String.valueOf((long) cell0.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrBand.setCode(cell0.getStringCellValue());
                    break;
            }
        }

        cfgMktIrrGnrBand.setRiskWeight(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);

        return cfgMktIrrGnrBand;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrBand> all = cfgMktIrrGnrBandRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrBand cfgMktIrrGnrBand : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrGnrBand.getCode());
            createCell(row, 1, cfgMktIrrGnrBand.getRiskWeight());

            rowIndex++;
        }
    }
}

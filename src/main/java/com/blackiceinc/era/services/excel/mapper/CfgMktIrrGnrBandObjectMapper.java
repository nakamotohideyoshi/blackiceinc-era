package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrBand;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrBandRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktIrrGnrBandObjectMapper {

    CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository;

    @Autowired
    public CfgMktIrrGnrBandObjectMapper(CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository){
        this.cfgMktIrrGnrBandRepository = cfgMktIrrGnrBandRepository;
    }

    public List<CfgMktIrrGnrBand> extractData(XSSFSheet sheet) {
        List<CfgMktIrrGnrBand> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createRow(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgMktIrrGnrBand createRow(Row row) {

        CfgMktIrrGnrBand cfgMktIrrGnrBand = new CfgMktIrrGnrBand();

        Cell cell0 = row.getCell(0);
        if (cell0!=null) {
            switch (cell0.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrBand.setCode(cell0 != null ? String.valueOf((long)cell0.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrBand.setCode(cell0 != null ? cell0.getStringCellValue() : null);
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
        for (CfgMktIrrGnrBand cfgMktIrrGnrBand:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktIrrGnrBand.getCode());
            row.createCell(1).setCellValue(cfgMktIrrGnrBand.getRiskWeight());

            rowIndex++;
        }
    }
}
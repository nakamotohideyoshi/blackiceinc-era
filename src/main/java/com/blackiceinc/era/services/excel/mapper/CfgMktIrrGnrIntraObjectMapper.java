package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrIntra;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrIntraRepository;
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
public class CfgMktIrrGnrIntraObjectMapper {

    CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;

    @Autowired
    public CfgMktIrrGnrIntraObjectMapper(CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository){
        this.cfgMktIrrGnrIntraRepository = cfgMktIrrGnrIntraRepository;
    }

    public List<CfgMktIrrGnrIntra> extractData(XSSFSheet sheet) {
        List<CfgMktIrrGnrIntra> result = new ArrayList<>();

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

    private CfgMktIrrGnrIntra createRow(Row row) {

        CfgMktIrrGnrIntra cfgMktIrrGnrIntra = new CfgMktIrrGnrIntra();

        Cell cell0 = row.getCell(0);
        if (cell0!=null){
            switch (cell0.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrIntra.setCode(cell0 != null ? String.valueOf((long)cell0.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrIntra.setCode(cell0 != null ? cell0.getStringCellValue() : null);
                    break;
            }
        }

        Cell cell1 = row.getCell(1);
        if (cell1!=null){
            switch (cell1.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrIntra.setZoneCode(cell1 != null ? String.valueOf((long)cell1.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrIntra.setZoneCode(cell1 != null ? cell1.getStringCellValue() : null);
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
        for (CfgMktIrrGnrIntra cfgMktIrrGnrIntra:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktIrrGnrIntra.getCode());
            row.createCell(1).setCellValue(cfgMktIrrGnrIntra.getZoneCode());
            row.createCell(2).setCellValue(cfgMktIrrGnrIntra.getRiskWeight());

            rowIndex++;
        }
    }
}

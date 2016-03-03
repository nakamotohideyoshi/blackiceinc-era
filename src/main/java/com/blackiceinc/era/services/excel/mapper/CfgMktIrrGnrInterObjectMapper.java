package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrInter;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrInterRepository;
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
public class CfgMktIrrGnrInterObjectMapper {

    CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;

    @Autowired
    public CfgMktIrrGnrInterObjectMapper(CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository){
        this.cfgMktIrrGnrInterRepository = cfgMktIrrGnrInterRepository;
    }

    public List<CfgMktIrrGnrInter> extractData(XSSFSheet sheet) {
        List<CfgMktIrrGnrInter> result = new ArrayList<>();

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

    private CfgMktIrrGnrInter createRow(Row row) {

        CfgMktIrrGnrInter cfgMktIrrGnrInter = new CfgMktIrrGnrInter();

        Cell cell0 = row.getCell(0);
        if (cell0!=null){
            switch (cell0.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrInter.setCode(cell0 != null ? String.valueOf((long)cell0.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrInter.setCode(cell0 != null ? cell0.getStringCellValue() : null);
                    break;
            }
        }

        Cell cell1 = row.getCell(1);
        if (cell1!=null){
            switch (cell1.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrInter.setZoneCode1(cell1 != null ? String.valueOf((long)cell1.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrInter.setZoneCode1(cell1 != null ? cell1.getStringCellValue() : null);
                    break;
            }
        }


        Cell cell2 = row.getCell(2);
        if (cell2!=null){
            switch (cell2.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgMktIrrGnrInter.setZoneCode2(cell2 != null ? String.valueOf((long)cell2.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgMktIrrGnrInter.setZoneCode2(cell2 != null ? cell2.getStringCellValue() : null);
                    break;
            }
        }

        cfgMktIrrGnrInter.setRiskWeight(row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null);

        return cfgMktIrrGnrInter;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrInter> all = cfgMktIrrGnrInterRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrInter cfgMktIrrGnrInter:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktIrrGnrInter.getCode());
            row.createCell(1).setCellValue(cfgMktIrrGnrInter.getZoneCode1());
            row.createCell(2).setCellValue(cfgMktIrrGnrInter.getZoneCode2());
            row.createCell(3).setCellValue(cfgMktIrrGnrInter.getRiskWeight());

            rowIndex++;
        }
    }
}

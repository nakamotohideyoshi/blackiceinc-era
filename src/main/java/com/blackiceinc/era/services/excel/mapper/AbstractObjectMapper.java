package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.services.excel.mapper.exception.CellMappingException;
import com.blackiceinc.era.services.excel.mapper.exception.PrimaryColumnMappingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractObjectMapper {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    public List extractData(XSSFSheet sheet) {
        List result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                try {
                    result.add(createRow(row));
                } catch (PrimaryColumnMappingException e) {
                    log.warn("Sheet : {} has null value for a primary column in database. Row : {}",
                            sheet.getSheetName(), rowIndex + 1);
                }
            }

            rowIndex++;
        }

        return result;
    }

    abstract Object createRow(Row row) throws PrimaryColumnMappingException;

    void createCell(XSSFRow row, int columnIndex, String value){
        if (value!=null){
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    void createCell(XSSFRow row, int columnIndex, Boolean value){
        if (value!=null){
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    void createCell(XSSFRow row, int columnIndex, Double value){
        if (value!=null){
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    void createCell(XSSFRow row, int columnIndex, Long value) {
        if (value!=null){
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    String getStringValue(Cell cell){
        String result = null;

        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    result = String.valueOf((long) cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
            }
        }

        return result;
    }

    Long getLongValue(Cell cell){
        Long result = null;

        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    result = (long) cell.getNumericCellValue();
                    break;
                case Cell.CELL_TYPE_STRING:
                    result = Long.valueOf(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = (long)cell.getNumericCellValue();
                    break;
            }
        }

        return result;
    }

    Double getDoubleValue(Cell cell) {
        Double result = null;

        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    result = cell.getNumericCellValue();
                    break;
                case Cell.CELL_TYPE_STRING:
                    result = Double.valueOf(cell.getStringCellValue());
                    break;
            }
        }

        return result;
    }

}

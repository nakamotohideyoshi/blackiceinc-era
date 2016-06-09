package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.utils.ModelUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractObjectMapper<T extends CfgObject> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public List<T> extractData(XSSFSheet sheet) {
        List<T> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                T rowObj = createRow(row);
                addRowObj(result, rowObj);
            }

            rowIndex++;
        }

        return result;
    }

    private void addRowObj(List<T> result, T rowObj) {
        try {
            if (!ModelUtils.areAllFieldsNull(rowObj)) {
                result.add(rowObj);
            }
        } catch (IllegalAccessException e) {
            log.error("Error accessing fields in object!", e);
        }
    }

    abstract T createRow(Row row);

    void createCell(XSSFRow row, int columnIndex, String value) {
        if (value != null) {
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    void createCell(XSSFRow row, int columnIndex, Boolean value) {
        if (value != null) {
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    void createCell(XSSFRow row, int columnIndex, Double value) {
        if (value != null) {
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    void createCell(XSSFRow row, int columnIndex, Long value) {
        if (value != null) {
            row.createCell(columnIndex).setCellValue(value);
        }
    }

    String getStringValue(Cell cell) {
        String result = null;

        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    result = new BigDecimal(Double.toString(cell.getNumericCellValue())).toPlainString();
                    break;
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                default:
                    log.warn("Unsupported cell mapping in sheet {}, row index : {}, column index {}",
                            cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex());
                    break;
            }
        }

        return result;
    }

    Long getLongValue(Cell cell) {
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
                    result = (long) cell.getNumericCellValue();
                    break;
                default:
                    log.warn("Unsupported cell mapping for getLongValue in sheet {}, row index : {}, column index {}",
                            cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex());
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
                default:
                    log.warn("Unsupported cell mapping for getDoubleValue in sheet {}, row index : {}, column index {}",
                            cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex());
                    break;
            }
        }

        return result;
    }

}

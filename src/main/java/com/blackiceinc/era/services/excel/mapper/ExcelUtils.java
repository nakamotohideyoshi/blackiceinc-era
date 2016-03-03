package com.blackiceinc.era.services.excel.mapper;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);

    public static void removeAllRowsExcelFirstOne(XSSFSheet sheet) {
        LOG.debug("Removing all rows except first one in sheet : {}, total rows : {}", sheet.getSheetName(),
                sheet.getPhysicalNumberOfRows());

        List<Row> rowsToDelete = new ArrayList<>();
        int rowIndex = 0;
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();

            if (rowIndex>0){
                rowsToDelete.add(row);
            }

            rowIndex++;
        }

        for (Row row:rowsToDelete){
            sheet.removeRow(row);
        }

    }

}

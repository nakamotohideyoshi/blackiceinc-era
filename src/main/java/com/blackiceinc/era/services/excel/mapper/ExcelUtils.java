package com.blackiceinc.era.services.excel.mapper;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelUtils {
    public static void removeAllRowsExceltFirstOne(XSSFSheet sheet){
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        if (physicalNumberOfRows>1) {
            for (int i = 1; i< physicalNumberOfRows;i++){
                sheet.removeRow(sheet.getRow(i));
            }
        }
    }

}

package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.services.exception.CfgImportException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.Ordered;

public interface CfgImportService extends Ordered {

    void makeImportIntoDb(XSSFWorkbook workbook) throws CfgImportException;

    void makeImportIntoExcelFromDb(XSSFWorkbook workbook);

}

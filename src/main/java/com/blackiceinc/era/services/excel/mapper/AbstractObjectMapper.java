package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.services.excel.mapper.exception.CellMappingException;
import com.blackiceinc.era.services.excel.mapper.exception.PrimaryColumnMappingException;
import org.apache.poi.ss.usermodel.Row;
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

}

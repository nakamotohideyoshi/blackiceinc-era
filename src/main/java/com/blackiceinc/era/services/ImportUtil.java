package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.CfgRepository;
import com.blackiceinc.era.services.excelmapper.AbstractObjectMapper;
import com.blackiceinc.era.services.exception.CfgImportException;
import com.blackiceinc.era.services.exception.model.CfgMessage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

import java.text.MessageFormat;
import java.util.List;

public class ImportUtil {

    private static void makeImport(List<? extends CfgObject> list, CfgRepository repository) {
        repository.deleteAll();
        insertAllIntoDb(list, repository);
    }

    private static void insertAllIntoDb(List<? extends CfgObject> list, CfgRepository repository) {
        for (CfgObject cfgObject : list) {
            repository.insert(cfgObject);
        }
    }

    public static void makeImportIntoDb(String sheetName, XSSFWorkbook workbook, AbstractObjectMapper<CfgObject> cfgObjectMapper, CfgRepository cfgRepository) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        List<CfgObject> cfgFinancialBookList = cfgObjectMapper.extractData(sheet);
        try {
            makeImport(cfgFinancialBookList, cfgRepository);
        } catch (InvalidDataAccessResourceUsageException ex) {
            throw new CfgImportException(new CfgMessage(CfgMessage.Type.ERROR,
                    MessageFormat.format("Error making import for sheet : {0}", sheetName)), ex);
        }
    }

    public static XSSFSheet getSheet(String sheetName, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        return sheet;
    }

}

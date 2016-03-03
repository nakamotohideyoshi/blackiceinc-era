package com.blackiceinc.era.services.excel.mapper;


import com.blackiceinc.era.persistence.erau.model.CfgCompany;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCompanyObjectMapper {

    private CfgCompanyRepository cfgCompanyRepository;

    @Autowired
    public CfgCompanyObjectMapper(CfgCompanyRepository cfgCompanyRepository) {
        this.cfgCompanyRepository = cfgCompanyRepository;
    }

    public List<CfgCompany> extractCfgCompanies(XSSFSheet companySheet) {
        List<CfgCompany> result = new ArrayList<>();

        // Iterate through each rows one by one
        Iterator<Row> rowIterator = companySheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgCompanyObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgCompany createCfgCompanyObj(Row row) {
        CfgCompany cfgCompany = new CfgCompany();

        cfgCompany.setCompanyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCompany.setCompanyName(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCompany.setIncorporationCountry(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgCompany;
    }

    public void importCfgCompanies(XSSFSheet companySheet) {
        List<CfgCompany> all = cfgCompanyRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(companySheet);
        int rowIndex = 1;
        for (CfgCompany cfgFinancialBook : all) {
            XSSFRow row = companySheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgFinancialBook.getCompanyCode());
            row.createCell(1).setCellValue(cfgFinancialBook.getCompanyName());
            row.createCell(2).setCellValue(cfgFinancialBook.getIncorporationCountry());

            rowIndex++;
        }
    }
}

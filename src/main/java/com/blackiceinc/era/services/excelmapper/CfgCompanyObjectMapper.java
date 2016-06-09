package com.blackiceinc.era.services.excelmapper;


import com.blackiceinc.era.persistence.erau.model.CfgCompany;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCompanyObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgCompanyRepository cfgCompanyRepository;

    @Autowired
    public CfgCompanyObjectMapper(CfgCompanyRepository cfgCompanyRepository) {
        this.cfgCompanyRepository = cfgCompanyRepository;
    }

    @Override
    CfgCompany createRow(Row row) {
        CfgCompany cfgCompany = new CfgCompany();

        cfgCompany.setCompanyCode(getStringValue(row.getCell(0)));
        cfgCompany.setCompanyName(getStringValue(row.getCell(1)));
        cfgCompany.setIncorporationCountry(getStringValue(row.getCell(2)));

        return cfgCompany;
    }

    public void importData(XSSFSheet companySheet) {
        List<CfgCompany> all = cfgCompanyRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(companySheet);
        int rowIndex = 1;
        for (CfgCompany cfgFinancialBook : all) {
            XSSFRow row = companySheet.createRow(rowIndex);

            createCell(row, 0, cfgFinancialBook.getCompanyCode());
            createCell(row, 1, cfgFinancialBook.getCompanyName());
            createCell(row, 2, cfgFinancialBook.getIncorporationCountry());

            rowIndex++;
        }
    }
}

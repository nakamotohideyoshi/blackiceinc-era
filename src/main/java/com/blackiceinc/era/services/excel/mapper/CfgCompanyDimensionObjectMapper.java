package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCompany;
import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimension;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyDimensionRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCompanyDimensionObjectMapper {
    private CfgCompanyDimensionRepository cfgCompanyDimensionRepository;

    @Autowired
    public CfgCompanyDimensionObjectMapper(CfgCompanyDimensionRepository cfgCompanyDimensionRepository){
        this.cfgCompanyDimensionRepository = cfgCompanyDimensionRepository;
    }

    public List<CfgCompanyDimension> extractCfgCompanyDimensions(XSSFSheet companySheet) {
        List<CfgCompanyDimension> result = new ArrayList<>();

        // Iterate through each rows one by one
        Iterator<Row> rowIterator = companySheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgCompanyDimensionObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgCompanyDimension createCfgCompanyDimensionObj(Row row) {
        CfgCompanyDimension cfgCompanyDimension = new CfgCompanyDimension();

        cfgCompanyDimension.setCompanyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCompanyDimension.setFinancialBook(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgCompanyDimension;
    }

    public void importCfgCompanyDimension(XSSFSheet companyDimensionSheet) {
        List<CfgCompanyDimension> all = cfgCompanyDimensionRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(companyDimensionSheet);
        int rowIndex = 1;
        for (CfgCompanyDimension cfgCompanyDimension : all) {
            XSSFRow row = companyDimensionSheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCompanyDimension.getCompanyCode());
            row.createCell(1).setCellValue(cfgCompanyDimension.getFinancialBook());

            rowIndex++;
        }
    }
}

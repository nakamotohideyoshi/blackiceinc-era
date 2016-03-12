package com.blackiceinc.era.services.excel.mapper;

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
public class CfgCompanyDimensionObjectMapper extends AbstractObjectMapper {
    private CfgCompanyDimensionRepository cfgCompanyDimensionRepository;

    @Autowired
    public CfgCompanyDimensionObjectMapper(CfgCompanyDimensionRepository cfgCompanyDimensionRepository){
        this.cfgCompanyDimensionRepository = cfgCompanyDimensionRepository;
    }

    CfgCompanyDimension createRow(Row row) {
        CfgCompanyDimension cfgCompanyDimension = new CfgCompanyDimension();

        cfgCompanyDimension.setCompanyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCompanyDimension.setFinancialBook(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgCompanyDimension;
    }

    public void importData(XSSFSheet companyDimensionSheet) {
        List<CfgCompanyDimension> all = cfgCompanyDimensionRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(companyDimensionSheet);
        int rowIndex = 1;
        for (CfgCompanyDimension cfgCompanyDimension : all) {
            XSSFRow row = companyDimensionSheet.createRow(rowIndex);

            createCell(row, 0, cfgCompanyDimension.getCompanyCode());
            createCell(row, 1, cfgCompanyDimension.getFinancialBook());

            rowIndex++;
        }
    }
}

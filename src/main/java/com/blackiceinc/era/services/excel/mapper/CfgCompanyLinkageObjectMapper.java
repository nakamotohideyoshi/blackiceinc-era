package com.blackiceinc.era.services.excel.mapper;


import com.blackiceinc.era.persistence.erau.model.CfgCompanyLinkage;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyLinkageRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCompanyLinkageObjectMapper {

    private CfgCompanyLinkageRepository cfgCompanyLinkageRepository;

    @Autowired
    public CfgCompanyLinkageObjectMapper(CfgCompanyLinkageRepository cfgCompanyLinkageRepository) {
        this.cfgCompanyLinkageRepository = cfgCompanyLinkageRepository;
    }

    public List<CfgCompanyLinkage> extractCfgCompanyLinkage(XSSFSheet companyLinkageSheet) {
        List<CfgCompanyLinkage> result = new ArrayList<>();

        // Iterate through each rows one by one
        Iterator<Row> rowIterator = companyLinkageSheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgCompanyLinkageObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgCompanyLinkage createCfgCompanyLinkageObj(Row row) {
        CfgCompanyLinkage cfgCompanyLinkage = new CfgCompanyLinkage();

        cfgCompanyLinkage.setChildCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCompanyLinkage.setMotherCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCompanyLinkage.setLinkWeight(row.getCell(2) != null ? row.getCell(2).getNumericCellValue() : null);

        return cfgCompanyLinkage;
    }

    public void importCfgCompanyLinkage(XSSFSheet companySheet) {
        List<CfgCompanyLinkage> all = cfgCompanyLinkageRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(companySheet);
        int rowIndex = 1;
        for (CfgCompanyLinkage cfgCompanyLinkage : all) {
            XSSFRow row = companySheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCompanyLinkage.getChildCode());
            row.createCell(1).setCellValue(cfgCompanyLinkage.getMotherCode());
            row.createCell(2).setCellValue(cfgCompanyLinkage.getLinkWeight());

            rowIndex++;
        }
    }
}

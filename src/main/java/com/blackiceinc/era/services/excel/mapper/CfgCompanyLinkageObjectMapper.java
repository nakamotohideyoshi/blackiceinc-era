package com.blackiceinc.era.services.excel.mapper;


import com.blackiceinc.era.persistence.erau.model.CfgCompanyLinkage;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyLinkageRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCompanyLinkageObjectMapper extends AbstractObjectMapper {

    private CfgCompanyLinkageRepository cfgCompanyLinkageRepository;

    @Autowired
    public CfgCompanyLinkageObjectMapper(CfgCompanyLinkageRepository cfgCompanyLinkageRepository) {
        this.cfgCompanyLinkageRepository = cfgCompanyLinkageRepository;
    }

    CfgCompanyLinkage createRow(Row row) {
        CfgCompanyLinkage cfgCompanyLinkage = new CfgCompanyLinkage();

        cfgCompanyLinkage.setChildCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCompanyLinkage.setMotherCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCompanyLinkage.setLinkWeight(row.getCell(2) != null ? row.getCell(2).getNumericCellValue() : null);

        return cfgCompanyLinkage;
    }

    public void importData(XSSFSheet companySheet) {
        List<CfgCompanyLinkage> all = cfgCompanyLinkageRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(companySheet);
        int rowIndex = 1;
        for (CfgCompanyLinkage cfgCompanyLinkage : all) {
            XSSFRow row = companySheet.createRow(rowIndex);

            createCell(row, 0, cfgCompanyLinkage.getChildCode());
            createCell(row, 1, cfgCompanyLinkage.getMotherCode());
            createCell(row, 2, cfgCompanyLinkage.getLinkWeight());

            rowIndex++;
        }
    }
}

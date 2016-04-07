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
public class CfgCompanyLinkageObjectMapper extends AbstractObjectMapper<CfgCompanyLinkage> {

    private CfgCompanyLinkageRepository cfgCompanyLinkageRepository;

    @Autowired
    public CfgCompanyLinkageObjectMapper(CfgCompanyLinkageRepository cfgCompanyLinkageRepository) {
        this.cfgCompanyLinkageRepository = cfgCompanyLinkageRepository;
    }

    @Override
    CfgCompanyLinkage createRow(Row row) {
        CfgCompanyLinkage cfgCompanyLinkage = new CfgCompanyLinkage();

        cfgCompanyLinkage.setChildCode(getStringValue(row.getCell(0)));
        cfgCompanyLinkage.setMotherCode(getStringValue(row.getCell(1)));
        cfgCompanyLinkage.setLinkWeight(getDoubleValue(row.getCell(2)));

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

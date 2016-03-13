package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrSpcRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrSpcRiskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrSpcRiskObjectMapper extends AbstractObjectMapper {

    CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository;

    @Autowired
    public CfgMktIrrSpcRiskObjectMapper(CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository) {
        this.cfgMktIrrSpcRiskRepository = cfgMktIrrSpcRiskRepository;
    }

    CfgMktIrrSpcRisk createRow(Row row) {
        CfgMktIrrSpcRisk cfgMktIrrSpcRisk = new CfgMktIrrSpcRisk();

        cfgMktIrrSpcRisk.setMktAssetClass(getStringValue(row.getCell(0)));
        cfgMktIrrSpcRisk.setIssueRiskBucket(getStringValue(row.getCell(1)));
        cfgMktIrrSpcRisk.setIssuerRiskBucket(getStringValue(row.getCell(2)));
        cfgMktIrrSpcRisk.setResidualMaturityStart(getStringValue(row.getCell(3)));
        cfgMktIrrSpcRisk.setResidualMaturityEnd(getStringValue(row.getCell(4)));
        cfgMktIrrSpcRisk.setInstrumentGroup(getStringValue(row.getCell(5)));
        cfgMktIrrSpcRisk.setRiskWeight(getDoubleValue(row.getCell(6)));

        return cfgMktIrrSpcRisk;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrSpcRisk> all = cfgMktIrrSpcRiskRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrSpcRisk cfgMktIrrSpcRisk : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrSpcRisk.getMktAssetClass());
            createCell(row, 1, cfgMktIrrSpcRisk.getIssueRiskBucket());
            createCell(row, 2, cfgMktIrrSpcRisk.getIssuerRiskBucket());
            createCell(row, 3, cfgMktIrrSpcRisk.getResidualMaturityStart());
            createCell(row, 4, cfgMktIrrSpcRisk.getResidualMaturityEnd());
            createCell(row, 5, cfgMktIrrSpcRisk.getInstrumentGroup());
            createCell(row, 6, cfgMktIrrSpcRisk.getRiskWeight());

            rowIndex++;
        }
    }
}

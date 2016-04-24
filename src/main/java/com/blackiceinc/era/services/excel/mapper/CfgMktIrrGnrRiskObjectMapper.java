package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrRiskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrGnrRiskObjectMapper extends AbstractObjectMapper<CfgMktIrrGnrRisk> {

    private CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository;

    @Autowired
    public CfgMktIrrGnrRiskObjectMapper(CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository) {
        this.cfgMktIrrGnrRiskRepository = cfgMktIrrGnrRiskRepository;
    }

    @Override
    CfgMktIrrGnrRisk createRow(Row row) {
        CfgMktIrrGnrRisk cfgMktIrrGnrRisk = new CfgMktIrrGnrRisk();

        cfgMktIrrGnrRisk.setZoneCode(getStringValue(row.getCell(0)));
        cfgMktIrrGnrRisk.setBandCode(getStringValue(row.getCell(1)));
        cfgMktIrrGnrRisk.setCurrency(getStringValue(row.getCell(2)));
        cfgMktIrrGnrRisk.setCouponRateStart(getDoubleValue(row.getCell(3)));
        cfgMktIrrGnrRisk.setCouponRateEnd(getDoubleValue(row.getCell(4)));
        cfgMktIrrGnrRisk.setMaturityBandStart(getDoubleValue(row.getCell(5)));
        cfgMktIrrGnrRisk.setMaturityBandEnd(getDoubleValue(row.getCell(6)));
        cfgMktIrrGnrRisk.setRiskWeight(getDoubleValue(row.getCell(7)));
        cfgMktIrrGnrRisk.setSeq(getLongValue(row.getCell(8)));

        return cfgMktIrrGnrRisk;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrRisk> all = cfgMktIrrGnrRiskRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrRisk cfgMktIrrGnrRisk : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrGnrRisk.getZoneCode());
            createCell(row, 1, cfgMktIrrGnrRisk.getBandCode());
            createCell(row, 2, cfgMktIrrGnrRisk.getCurrency());
            createCell(row, 3, cfgMktIrrGnrRisk.getCouponRateStart());
            createCell(row, 4, cfgMktIrrGnrRisk.getCouponRateEnd());
            createCell(row, 5, cfgMktIrrGnrRisk.getMaturityBandStart());
            createCell(row, 6, cfgMktIrrGnrRisk.getMaturityBandEnd());
            createCell(row, 7, cfgMktIrrGnrRisk.getRiskWeight());
            createCell(row, 8, cfgMktIrrGnrRisk.getSeq());

            rowIndex++;
        }
    }
}

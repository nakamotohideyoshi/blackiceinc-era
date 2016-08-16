package com.blackiceinc.era.services.stresstesting;

import com.blackiceinc.era.persistence.erau.DbUtils;
import com.blackiceinc.era.services.exception.StressTestingException;
import com.blackiceinc.era.services.stresstesting.model.ExcelMapping;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class StressTestingService {

    public static final String CURRENT_YEAR = "CURRENT_YEAR";
    public static final String COUNTRY_CODE = "COUNTRY_CODE";
    public static final String FORMULA = "FORMULA";
    public static final String PERIODS_YEAR = "PERIODS_YEAR";
    public static final String FIGURES_BASE = "FIGURES_BASE";
    public static final String CURRENCY = "CURRENCY";
    public static final String CONVERSION_RATE = "CONVERSION_RATE";
    private static final String STRESS_TESTING_VIB_STRESS_TESTING_XLSX = "/stress-testing/BEST BlackIce Enterprise Stress Testing v5.3.xlsm";
    private static final int ONE_MILLION = 1000000;

    private static Logger log = LoggerFactory.getLogger(StressTestingService.class);

    @Autowired
    private Environment env;

    @Autowired
    private DbUtils dbUtils;

    /**
     * Applies the mappings and returns the excel document where the configuration is being executed.
     *
     * @return XSSFWorkbook
     */
    public XSSFWorkbook prepareStressTestExcel() {
        XSSFWorkbook workbook = getWorkbook();

        XSSFSheet sheet = workbook.getSheet("Input");

        List<ExcelMapping> excelMappings = Configuration.getForStressTesting();
        for (ExcelMapping excelMapping : excelMappings) {
            int rowIndex = Integer.parseInt(excelMapping.getRow()) - 1;
            int colIndex = CellReference.convertColStringToIndex(excelMapping.getColumn());

            XSSFRow row = sheet.getRow(rowIndex);
            XSSFCell cell = row.getCell(colIndex);

            evaluateMappingValue(workbook, excelMapping, cell);
        }

        return workbook;
    }

    private XSSFWorkbook getWorkbook() {
        try {
            return new XSSFWorkbook(OPCPackage.open(getStressTestingExcelPath().toFile()));
        } catch (IOException | InvalidFormatException e) {
            throw new StressTestingException(e);
        }
    }

    private void evaluateMappingValue(XSSFWorkbook workbook, ExcelMapping excelMapping, XSSFCell cell) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        if (CURRENT_YEAR.equals(excelMapping.getValue())) {
            cell.setCellValue(DateTime.now().getYear());
        } else if (COUNTRY_CODE.equals(excelMapping.getValue())) {
            cell.setCellValue("VNM");
        } else if (FORMULA.equals(excelMapping.getValue())) {
            evaluateFunction(excelMapping, cell, evaluator);
        } else if (PERIODS_YEAR.equals(excelMapping.getValue())) {
            cell.setCellValue("12");
        } else if (FIGURES_BASE.equals(excelMapping.getValue())) {
            cell.setCellValue("1000000");
        } else if (CURRENCY.equals(excelMapping.getValue())) {
            cell.setCellValue("VND");
        } else if (CONVERSION_RATE.equals(excelMapping.getValue())) {
            cell.setCellValue("22290");
        } else {
            handleQueryEvaluation(excelMapping, cell);
        }
    }

    private void evaluateFunction(ExcelMapping excelMapping, XSSFCell cell, FormulaEvaluator evaluator) {
        try {
            evaluator.evaluateInCell(cell);
        } catch (Exception ex) {
            log.error("Error function evaluation excelMapping : {}", excelMapping.toString());
            throw new StressTestingException(ex);
        }
    }

    private void handleQueryEvaluation(ExcelMapping excelMapping, XSSFCell cell) {
        try {
            BigDecimal bigDecimal = executeQuery(excelMapping.getValue());
            if (bigDecimal != null) {
                cell.setCellValue(bigDecimal.doubleValue() / ONE_MILLION);
            } else {
                cell.setCellValue(0);
            }
        } catch (SQLException e) {
            log.error("Error executing query : {}", excelMapping.getValue());
            throw new StressTestingException(e);
        }
    }

    public Path getStressTestingExcelPath() {
        URL resource = this.getClass().getResource(STRESS_TESTING_VIB_STRESS_TESTING_XLSX);
        try {
            return Paths.get(resource.toURI());
        } catch (URISyntaxException e) {
            log.error("Error accessing to resource : {}", STRESS_TESTING_VIB_STRESS_TESTING_XLSX);
            throw new StressTestingException(e);
        }
    }

    private BigDecimal executeQuery(String query) throws SQLException {
        BigDecimal result = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = dbUtils.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                result = resultSet.getBigDecimal(1);
            }
        } finally {
            DbUtils.close(resultSet);
            DbUtils.close(stmt);
            DbUtils.close(conn);
        }

        return result;
    }

}

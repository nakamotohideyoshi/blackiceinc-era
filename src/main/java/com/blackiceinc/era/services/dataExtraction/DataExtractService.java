package com.blackiceinc.era.services.dataExtraction;

import com.blackiceinc.era.services.exception.DataExtractionEntityException;
import com.blackiceinc.era.services.exception.DataExtractionException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataExtractService {

    private final Logger log = LoggerFactory.getLogger(DataExtractService.class);

    @Autowired
    private Environment env;

    public byte[] extractEntityIntoExcel(String entity) {
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(entity);

        try {
            int columnSize = createColumnNames(entity, sheet);

            if (columnSize == 0) {
                throw new DataExtractionEntityException("Non existent entity : " + entity);
            }

            addDataRows(entity, sheet, columnSize);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
            workbook.close();

            return byteArrayOutputStream.toByteArray();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataExtractionException("SQLException");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new DataExtractionException("IOException");
        }
    }

    private void addDataRows(String entity, XSSFSheet sheet, int columnSize) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            long start = System.currentTimeMillis();
            log.info("Extracting data for entity : {}", entity);
            resultSet = stmt.executeQuery("select * from " + entity);
            log.info("Extracting data for entity : {}, took {} ms", entity, System.currentTimeMillis() - start);

            int rowIndex = 1;
            while (resultSet.next()) {
                XSSFRow row = sheet.createRow(rowIndex);
                for (int i = 1; i <= columnSize; i++) {
                    int columnIndex = i - 1;
                    Object object = resultSet.getObject(i);
                    if (object != null) {
                        row.createCell(columnIndex).setCellValue(object.toString());
                    }
                }
                rowIndex++;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private int createColumnNames(String entity, XSSFSheet customer) throws SQLException {
        XSSFRow row = customer.createRow(0);
        int index = 0;
        for (String column : getColumnNames(entity)) {
            row.createCell(index).setCellValue(column);
            index++;
        }
        return index;
    }

    private List<String> getColumnNames(String entity) throws SQLException {
        List<String> columns = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            long start = System.currentTimeMillis();
            log.info("Taking column names for entity : {}", entity);
            resultSet = stmt.executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='" + entity + "' ORDER BY COLUMN_ID ASC");
            log.info("Extracting column names for entity : {}, took {} ms", entity, System.currentTimeMillis() - start);

            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                columns.add(columnName);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return columns;
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
        return conn;
    }

}

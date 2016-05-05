package com.blackiceinc.era.services.dataExtraction;

import com.blackiceinc.era.services.exception.DataExtractionEntityException;
import com.blackiceinc.era.services.exception.DataExtractionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DataExtractService {

    private static final String QUOTES = "\"";
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private final Logger log = LoggerFactory.getLogger(DataExtractService.class);
    @Autowired
    private Environment env;

    public Path writeToCsvFile(String entity) {
        try {
            long start = System.currentTimeMillis();

            Path tempFile = Files.createTempFile("ext", ".csv");
            FileWriter fileWriter = new FileWriter(tempFile.toFile());

            //Write the CSV file header
            int columnSize = addColumnNames(fileWriter, entity);

            if (columnSize == 0) {
                throw new DataExtractionEntityException("Non existent entity : " + entity);
            }

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            addDataRows(fileWriter, entity, columnSize);

            fileWriter.flush();
            fileWriter.close();

            log.info("Extracting data for entity : {}, took {} ms", entity, System.currentTimeMillis() - start);

            return tempFile;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new DataExtractionException("IOException");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataExtractionException("SQLException");
        }
    }

    private void addDataRows(FileWriter fileWriter, String entity, int columnSize) throws SQLException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            resultSet = stmt.executeQuery("select * from " + entity);

            while (resultSet.next()) {
                addRowToFileWriter(columnSize, resultSet, fileWriter);
                fileWriter.append(NEW_LINE_SEPARATOR);
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

    private void addRowToFileWriter(int columnSize, ResultSet resultSet, FileWriter fileWriter) throws SQLException, IOException {
        for (int i = 1; i <= columnSize; i++) {
            Object object = resultSet.getObject(i);
            if (object != null) {
                if (object instanceof Timestamp) {
                    Timestamp timestamp = (Timestamp) object;
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(timestamp.getTime());
                    int minute = cal.get(Calendar.MINUTE);
                    int seconds = cal.get(Calendar.SECOND);
                    int milliseconds = cal.get(Calendar.MILLISECOND);
                    if (minute == 0 && seconds == 0 && milliseconds == 0) {
                        fileWriter.append(QUOTES).append(resultSet.getDate(i).toString()).append(QUOTES);
                    } else {
                        fileWriter.append(QUOTES).append(object.toString()).append(QUOTES);
                    }

                } else {
                    fileWriter.append(QUOTES).append(object.toString()).append(QUOTES);
                }
            }
            if (i != columnSize) {
                fileWriter.append(COMMA_DELIMITER);
            }
        }
    }

    private int addColumnNames(FileWriter fileWriter, String entity) throws SQLException, IOException {
        int index = 0;
        List<String> columnNames = getColumnNames(entity);
        int size = columnNames.size();
        for (String column : columnNames) {
            fileWriter.append(QUOTES).append(column).append(QUOTES);

            if (index != size - 1) {
                fileWriter.append(COMMA_DELIMITER);
            }
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

            resultSet = stmt.executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='" + entity + "' ORDER BY COLUMN_ID ASC");

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

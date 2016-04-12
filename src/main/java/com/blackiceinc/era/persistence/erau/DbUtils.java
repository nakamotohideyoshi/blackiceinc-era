package com.blackiceinc.era.persistence.erau;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DbUtils {

    @Autowired
    private Environment env;

    /**
     * Close a <code>Connection</code>, avoid closing if null.
     *
     * @param conn Connection to close.
     * @throws SQLException if a database access error occurs
     */
    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Close a <code>ResultSet</code>, avoid closing if null.
     *
     * @param rs ResultSet to close.
     * @throws SQLException if a database access error occurs
     */
    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * Close a <code>Statement</code>, avoid closing if null.
     *
     * @param stmt Statement to close.
     * @throws SQLException if a database access error occurs
     */
    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
        return conn;
    }


}

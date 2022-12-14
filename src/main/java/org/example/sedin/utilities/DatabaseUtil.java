package org.example.sedin.utilities;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;

public class DatabaseUtil {

    @Test
    public void TestNewDB() throws IOException {
        ResultSet rs = null;
        Statement statement = null;
        Connection conn = null;
        String dbConnectionString = PropertiesReader.getPropertiesFileValue("dbConnectionString");
        String dbUsername = PropertiesReader.getPropertiesFileValue("dbUsername");
        String dbPassword = PropertiesReader.getPropertiesFileValue("dbPassword");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbConnectionString, dbUsername, dbPassword);

            if (conn != null) {
                statement = conn.createStatement();
                String query = "select * from data";
                rs = statement.executeQuery((query));
                while (rs.next()) {
                    System.out.println(rs.getString(2));
                }
            }
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

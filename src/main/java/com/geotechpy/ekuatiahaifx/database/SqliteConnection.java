package com.geotechpy.ekuatiahaifx.database;
import java.sql.*;
public class SqliteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn;
            if (System.getProperty("os.name").startsWith("Windows")) {
                conn = DriverManager.getConnection("jdbc:sqlite:c:\\ekuatiahai\\ekuatiahai.db");
            } else {
                conn = DriverManager.getConnection("jdbc:sqlite:/opt/ekuatiahai/ekuatiahai.db");
            }

            return conn;
        } catch (Exception e) {
            return null;
        }
    }
}

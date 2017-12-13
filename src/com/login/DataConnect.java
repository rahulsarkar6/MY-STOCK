package com.login;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DataConnect {

    public static String hostName = "localhost";
    public static String portNumber = "3306";
    public static String databaseName = "STOCK_VISHAL";
    public static String userName = "root";
    public static String password = "admin";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + portNumber + "/" + databaseName, userName, password);
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}

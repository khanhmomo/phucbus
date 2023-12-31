package com.phucprod.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLConnection {
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String database = "company";
    private static final String username = "root";
    private static final String password = "admin";
    public static Connection getConnection() throws ClassNotFoundException {
        // Ok P oi, tai cai cau jdbc cua P no la qua nen K chua thay

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}

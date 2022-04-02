package com.scheduler.app.algorithm.databasejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static Connection connection = null;
    static
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private DatabaseConnection() {

    }
    public static Connection getConnection()
    {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(Config.db_url, Config.db_username, Config.db_password);
                connection.setAutoCommit(false);
                System.out.println("Connection Successful");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}

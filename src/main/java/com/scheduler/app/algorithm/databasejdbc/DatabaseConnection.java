package com.scheduler.app.algorithm.databasejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * The type  - Database Connection.
 * This class is responsible for creating a JDBC connection to the database for performing JOIN queries.
 */
public class DatabaseConnection {

    //Global connection object
    private static Connection connection = null;

    //Static block to make sure the necessary JDBC driver loads before connection is established.
    //Loading the mySQL JDBC driver before trying to establish the connection is crucial.
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

    /**
     * Opens a connection to the DB using the given properties.
     *
     * @return the connection
     */
    public static Connection openConnection()
    {
        if(connection == null) {

            try {
                connection = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUsername(), Config.getDbPassword());
                //Set the Auto commit to 'false' for controlling what the application commits to the DB.
                //Helps with avoiding issues during transaction processing.
                connection.setAutoCommit(false);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Return the connection object.
        return connection;
    }

}

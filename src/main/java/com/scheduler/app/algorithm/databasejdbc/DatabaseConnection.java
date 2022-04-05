package com.scheduler.app.algorithm.databasejdbc;

import com.scheduler.app.algorithm.util.EnvironmentalProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * The type  - Database Connection.
 * This class is responsible for creating a JDBC connection to the database for performing JOIN queries.
 */
public class DatabaseConnection {

    //Global connection object
    private static Connection connection = null;

    private static String dbUserName;
    private static String dbPassword;
    private static String dbName;

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

    public DatabaseConnection() {
        Properties properties = EnvironmentalProperties.getProperties("application.properties");
        this.dbUserName = properties.getProperty("spring.datasource.username");
        this.dbPassword = properties.getProperty("spring.datasource.password");
        this.dbName = properties.getProperty("spring.datasource.url");
    }

    /**
     * Opens a connection to the DB using the given properties.
     *
     * @return the connection
     */
    public Connection openConnection()
    {
        if(connection == null) {

            try {

                connection = DriverManager.getConnection(this.dbName, this.dbUserName, this.dbPassword);
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

    public static void closeConnection(Connection connection){

        if(connection != null){

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.scheduler.app.algorithm.databasejdbc;

public class Config {
    private static final String dbUsername = "CSCI5308_20_DEVINT_USER";
    private static final String dbPassword = "uFieyooQu0GouKiX";
    private static String dbUrl = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_20_DEVINT";

    public static String getDbUsername(){
        return dbUsername;
    }
    public static String getDbPassword(){
        return dbPassword;
    }
    public static String getDbUrl(){
        return dbUrl;
    }
}


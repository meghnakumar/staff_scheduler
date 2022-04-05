package com.scheduler.app.algorithm.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Component - Environmental Properties.
 * This class is a utility class responsible for loading the JDBC connection
 * properties from the properties file in the resource classpath.
 */
@Component
public class EnvironmentalProperties {

    private static final Properties properties = new Properties();

    /**
     * Gets properties.
     *
     * @param filename the filename
     * @return the properties
     */
    public static Properties getProperties(String filename) {

        try (InputStream input = EnvironmentalProperties.class.getClassLoader().getResourceAsStream(filename)) {

            // Load properties
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}

package com.scheduler.app.algorithm.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class EnvironmentalProperties {
    private static final Properties properties = new Properties();

    public static Properties getProperties(String filename) {
        try (InputStream input = EnvironmentalProperties.class.getClassLoader().getResourceAsStream(filename)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}

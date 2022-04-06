package com.scheduler.app.admin.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * File Resource Utility Class.
 * This class is utilised to read and parse the property files in the resource folder.
 */
@Component
public class FileResourceUtils {

    /**
     * Gets String Input Stream method returns a string based on the input stream provided.
     *
     * @param is the input stream
     * @return the string
     */
    public String getStringInputStream(InputStream is) {
        String result = "";
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets file from 'resource' folder as an Input stream.
     * Works everywhere, IDEA, unit test and JAR file.
     *
     * @param fileName the file name
     * @return the file from resource as an input stream.
     */
    public InputStream getFileFromResourceAsStream(String fileName) {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        //get a file from the resources folder
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


}


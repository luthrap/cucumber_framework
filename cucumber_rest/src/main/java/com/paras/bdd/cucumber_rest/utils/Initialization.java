package com.paras.bdd.cucumber_rest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Initialization {

    private static Properties properties = null;
    static {
        try {
            readProperties("src/test/resources/config.properties");
        }catch(Exception exception){
            //do nothing
        }
    }

    private static void readProperties(String fileName) throws IOException {
        File file = new File(fileName);
        properties = new Properties();
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
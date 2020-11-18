package com.paras.bdd.cucumber_selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Initialization {

    private static Properties properties = null;
    private static WebDriver driver;
    static {
        try {
            readProperties("src/test/resources/config.properties");
            initializeDriver();
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

    private static void initializeDriver(){
            System.setProperty("webdriver.chrome.driver", properties.getProperty("driver_path"));
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(properties.getProperty("base_url"));
            }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    public static WebDriver getDriver (){
        return driver;
    }
}
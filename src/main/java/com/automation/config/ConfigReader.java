package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Reader - Reads properties from config.properties file
 */
public class ConfigReader {
    
    private static Properties properties;
    private static final String CONFIG_PATH = "src/main/resources/config.properties";
    
    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
    
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWait", "10"));
    }
    
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicitWait", "20"));
    }
    
    public static String getTestDataPath() {
        return properties.getProperty("testDataPath", "src/test/resources/testdata/TestData.xlsx");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }
}

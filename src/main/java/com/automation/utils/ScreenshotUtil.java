package com.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot Utility - Captures screenshots during test execution
 */
public class ScreenshotUtil {
    
    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOT_PATH = "target/screenshots/";
    
    /**
     * Takes a screenshot and saves it to the specified location
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_PATH + fileName;
        
        try {
            // Create directory if it doesn't exist
            Path path = Paths.get(SCREENSHOT_PATH);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            
            // Take screenshot
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            Files.copy(source.toPath(), destination.toPath());
            
            logger.info("Screenshot saved: " + filePath);
            return filePath;
            
        } catch (IOException e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }
    
    /**
     * Takes a screenshot and returns it as base64 string
     */
    public static String takeScreenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    
    /**
     * Takes a screenshot and returns it as byte array
     */
    public static byte[] takeScreenshotAsBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

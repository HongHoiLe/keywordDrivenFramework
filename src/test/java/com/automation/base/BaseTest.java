package com.automation.base;

import com.automation.config.ConfigReader;
import com.automation.driver.DriverManager;
import com.automation.reports.ReportManager;
import com.automation.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Base Test - Parent class for all test classes
 */
public class BaseTest {
    
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    
    @BeforeSuite
    public void beforeSuite() {
        logger.info("========== Test Suite Started ==========");
        ReportManager.initReport();
    }
    
    @BeforeMethod
    public void beforeMethod() {
        logger.info("---------- Test Started ----------");
        DriverManager.initDriver();
    }
    
    @AfterMethod
    public void afterMethod(ITestResult result) {
        // Take screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test Failed: " + result.getName());
            String screenshotPath = ScreenshotUtil.takeScreenshot(
                DriverManager.getDriver(), 
                result.getName()
            );
            if (screenshotPath != null) {
                ReportManager.addScreenshotFromPath(screenshotPath);
            }
            ReportManager.logFail("Test Failed: " + result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test Passed: " + result.getName());
            ReportManager.logPass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test Skipped: " + result.getName());
            ReportManager.logSkip("Test Skipped");
            logger.infor("Test Skipped: ");
        }
        
        DriverManager.quitDriver();
        logger.info("---------- Test Ended ----------");
    }
    
    @AfterSuite
    public void afterSuite() {
        ReportManager.flushReport();
        logger.info("========== Test Suite Ended ==========");
    }
}

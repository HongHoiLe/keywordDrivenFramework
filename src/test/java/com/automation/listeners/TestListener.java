package com.automation.listeners;

import com.automation.driver.DriverManager;
import com.automation.reports.ReportManager;
import com.automation.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Test Listener - Listens to test events and performs actions
 */
public class TestListener implements ITestListener {
    
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    
    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite started: " + context.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite finished: " + context.getName());
        logger.info("Passed tests: " + context.getPassedTests().size());
        logger.info("Failed tests: " + context.getFailedTests().size());
        logger.info("Skipped tests: " + context.getSkippedTests().size());
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: " + result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getName());
        logger.error("Failure reason: " + result.getThrowable().getMessage());
        
        // Take screenshot on failure
        try {
            if (DriverManager.getDriver() != null) {
                String screenshot = ScreenshotUtil.takeScreenshot(
                    DriverManager.getDriver(), 
                    result.getName()
                );
                if (screenshot != null) {
                    logger.info("Screenshot saved: " + screenshot);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to take screenshot on failure", e);
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: " + result.getName());
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("Test failed within success percentage: " + result.getName());
    }
}

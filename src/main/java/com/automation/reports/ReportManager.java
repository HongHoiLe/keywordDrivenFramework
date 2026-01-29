package com.automation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Report Manager - Handles Extent Report generation
 */
public class ReportManager {
    
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String REPORT_PATH = "target/reports/";
    
    /**
     * Initializes the Extent Report
     */
    public static void initReport() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = REPORT_PATH + "TestReport_" + timestamp + ".html";
            
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Keyword Driven Framework Test Report");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
    }
    
    /**
     * Creates a new test in the report
     */
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }
    
    /**
     * Creates a new test with description
     */
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }
    
    /**
     * Gets the current test
     */
    public static ExtentTest getTest() {
        return test.get();
    }
    
    /**
     * Logs info message
     */
    public static void logInfo(String message) {
        if (test.get() != null) {
            test.get().log(Status.INFO, message);
        }
    }
    
    /**
     * Logs pass status
     */
    public static void logPass(String message) {
        if (test.get() != null) {
            test.get().log(Status.PASS, message);
        }
    }
    
    /**
     * Logs fail status
     */
    public static void logFail(String message) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, message);
        }
    }
    
    /**
     * Logs skip status
     */
    public static void logSkip(String message) {
        if (test.get() != null) {
            test.get().log(Status.SKIP, message);
        }
    }
    
    /**
     * Logs warning status
     */
    public static void logWarning(String message) {
        if (test.get() != null) {
            test.get().log(Status.WARNING, message);
        }
    }
    
    /**
     * Adds screenshot to report
     */
    public static void addScreenshot(String base64Screenshot) {
        if (test.get() != null) {
            test.get().addScreenCaptureFromBase64String(base64Screenshot);
        }
    }
    
    /**
     * Adds screenshot from path
     */
    public static void addScreenshotFromPath(String screenshotPath) {
        if (test.get() != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }
    
    /**
     * Flushes the report
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

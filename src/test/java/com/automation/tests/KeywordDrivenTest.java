package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.config.ConfigReader;
import com.automation.engine.KeywordEngine;
import com.automation.reports.ReportManager;
import com.automation.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * Keyword Driven Test - Main test class that executes tests from Excel
 */
public class KeywordDrivenTest extends BaseTest {
    
    private ExcelReader excelReader;
    private KeywordEngine keywordEngine;
    
    @DataProvider(name = "testCases")
    public Object[][] getTestCases() {
        String testDataPath = ConfigReader.getTestDataPath();
        excelReader = new ExcelReader(testDataPath);
        
        List<String> testCaseIds = excelReader.getTestCaseIds("TestSteps");
        Object[][] testCases = new Object[testCaseIds.size()][1];
        
        for (int i = 0; i < testCaseIds.size(); i++) {
            testCases[i][0] = testCaseIds.get(i);
        }
        
        return testCases;
    }
    
    @Test(dataProvider = "testCases")
    public void executeTest(String testCaseId) {
        logger.info("Executing test case: " + testCaseId);
        ReportManager.createTest(testCaseId);
        
        String testDataPath = ConfigReader.getTestDataPath();
        excelReader = new ExcelReader(testDataPath);
        keywordEngine = new KeywordEngine();
        
        // Get test steps for this test case
        List<Map<String, String>> testSteps = excelReader.getTestSteps("TestSteps", testCaseId);
        
        // Execute each step
        for (Map<String, String> step : testSteps) {
            String execute = step.get("Execute");
            
            // Skip steps marked as 'No'
            if (execute != null && execute.equalsIgnoreCase("no")) {
                String stepDesc = step.get("StepDescription");
                ReportManager.logSkip("Skipped: " + stepDesc);
                continue;
            }
            
            String stepNo = step.get("StepNo");
            String stepDesc = step.get("StepDescription");
            String keyword = step.get("Keyword");
            String locatorType = step.get("LocatorType");
            String locatorValue = step.get("LocatorValue");
            String testData = step.get("TestData");
            String testData2 = step.get("TestData2");
            
            try {
                ReportManager.logInfo("Step " + stepNo + ": " + stepDesc);
                keywordEngine.executeKeyword(keyword, locatorType, locatorValue, testData, testData2);
                ReportManager.logPass("Step " + stepNo + " executed successfully");
            } catch (Exception e) {
                ReportManager.logFail("Step " + stepNo + " failed: " + e.getMessage());
                throw e;
            }
        }
        
        excelReader.close();
    }
}

package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.engine.KeywordEngine;
import com.automation.reports.ReportManager;
import org.testng.annotations.Test;

/**
 * Sample Test - Demonstrates how to use keywords directly in code
 */
public class SampleTest extends BaseTest {
    
    @Test
    public void googleSearchTest() {
        ReportManager.createTest("Google Search Test", "Verify Google search functionality");
        
        KeywordEngine engine = new KeywordEngine();
        
        // Open Google
        ReportManager.logInfo("Opening Google homepage");
        engine.executeKeyword("openBrowser", null, null, "https://www.google.com", null);
        
        // Wait for page load
        ReportManager.logInfo("Waiting for page to load");
        engine.executeKeyword("waitForPageLoad", null, null, null, null);
        
        // Enter search text
        ReportManager.logInfo("Entering search text");
        engine.executeKeyword("enterText", "name", "q", "Selenium WebDriver", null);
        
        // Press Enter
        ReportManager.logInfo("Pressing Enter key");
        engine.executeKeyword("pressEnter", "name", "q", null, null);
        
        // Wait for results
        ReportManager.logInfo("Waiting for search results");
        engine.executeKeyword("waitForSeconds", null, null, "3", null);
        
        // Verify title contains search term
        ReportManager.logInfo("Verifying search results");
        engine.executeKeyword("verifyTitleContains", null, null, "Selenium WebDriver", null);
        
        ReportManager.logPass("Google Search Test completed successfully");
    }
    
    @Test
    public void sampleLoginTest() {
        ReportManager.createTest("Sample Login Test", "Verify login functionality on demo site");
        
        KeywordEngine engine = new KeywordEngine();
        
        // Open demo login page
        ReportManager.logInfo("Opening demo login page");
        engine.executeKeyword("openBrowser", null, null, "https://the-internet.herokuapp.com/login", null);
        
        // Wait for page load
        ReportManager.logInfo("Waiting for page to load");
        engine.executeKeyword("waitForPageLoad", null, null, null, null);
        
        // Enter username
        ReportManager.logInfo("Entering username");
        engine.executeKeyword("enterText", "id", "username", "tomsmith", null);
        
        // Enter password
        ReportManager.logInfo("Entering password");
        engine.executeKeyword("enterText", "id", "password", "SuperSecretPassword!", null);
        
        // Click login button
        ReportManager.logInfo("Clicking login button");
        engine.executeKeyword("click", "css", "button[type='submit']", null, null);
        
        // Wait for redirect
        ReportManager.logInfo("Waiting for login to complete");
        engine.executeKeyword("waitForSeconds", null, null, "2", null);
        
        // Verify successful login
        ReportManager.logInfo("Verifying successful login");
        engine.executeKeyword("verifyTextPresent", null, null, "You logged into a secure area!", null);
        
        ReportManager.logPass("Login Test completed successfully");
    }
}

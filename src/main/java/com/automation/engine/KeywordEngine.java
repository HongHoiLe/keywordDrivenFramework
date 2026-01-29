package com.automation.engine;

import com.automation.driver.DriverManager;
import com.automation.keywords.Keywords;
import com.automation.utils.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Map;

/**
 * Keyword Engine - Executes keywords based on test data
 */
public class KeywordEngine {
    
    private static final Logger logger = LogManager.getLogger(KeywordEngine.class);
    private Keywords keywords;
    
    public KeywordEngine() {
        this.keywords = new Keywords();
    }
    
    /**
     * Executes a single keyword with its parameters
     */
    public boolean executeKeyword(String keyword, String locatorType, String locatorValue, 
                                   String testData, String testData2) {
        boolean result = true;
        
        try {
            logger.info("Executing keyword: " + keyword);
            
            switch (keyword.toLowerCase()) {
                // Browser Actions
                case "openbrowser":
                case "open_browser":
                    keywords.openBrowser(testData);
                    break;
                    
                case "navigate":
                case "navigatetourl":
                    keywords.navigate(testData);
                    break;
                    
                case "closebrowser":
                case "close_browser":
                    keywords.closeBrowser();
                    break;
                    
                case "refreshpage":
                case "refresh":
                    keywords.refreshPage();
                    break;
                    
                case "navigateback":
                case "goback":
                    keywords.navigateBack();
                    break;
                    
                case "navigateforward":
                case "goforward":
                    keywords.navigateForward();
                    break;
                    
                // Click Actions
                case "click":
                    keywords.click(locatorType, locatorValue);
                    break;
                    
                case "doubleclick":
                case "double_click":
                    keywords.doubleClick(locatorType, locatorValue);
                    break;
                    
                case "rightclick":
                case "right_click":
                case "contextclick":
                    keywords.rightClick(locatorType, locatorValue);
                    break;
                    
                case "jsclick":
                case "javascriptclick":
                    keywords.jsClick(locatorType, locatorValue);
                    break;
                    
                // Input Actions
                case "entertext":
                case "enter_text":
                case "type":
                case "sendkeys":
                    keywords.enterText(locatorType, locatorValue, testData);
                    break;
                    
                case "cleartext":
                case "clear":
                    keywords.clearText(locatorType, locatorValue);
                    break;
                    
                case "jsentertext":
                case "javascriptentertext":
                    keywords.jsEnterText(locatorType, locatorValue, testData);
                    break;
                    
                // Dropdown Actions
                case "selectbyvisibletext":
                case "selectbytext":
                case "select":
                    keywords.selectByVisibleText(locatorType, locatorValue, testData);
                    break;
                    
                case "selectbyvalue":
                    keywords.selectByValue(locatorType, locatorValue, testData);
                    break;
                    
                case "selectbyindex":
                    keywords.selectByIndex(locatorType, locatorValue, Integer.parseInt(testData));
                    break;
                    
                // Checkbox and Radio
                case "checkcheckbox":
                case "check":
                    keywords.checkCheckbox(locatorType, locatorValue);
                    break;
                    
                case "uncheckcheckbox":
                case "uncheck":
                    keywords.uncheckCheckbox(locatorType, locatorValue);
                    break;
                    
                case "selectradiobutton":
                case "selectradio":
                    keywords.selectRadioButton(locatorType, locatorValue);
                    break;
                    
                // Mouse Actions
                case "mousehover":
                case "hover":
                    keywords.mouseHover(locatorType, locatorValue);
                    break;
                    
                case "draganddrop":
                case "drag_and_drop":
                    keywords.dragAndDrop(locatorType, locatorValue, testData, testData2);
                    break;
                    
                // Frame Actions
                case "switchtoframe":
                case "switch_to_frame":
                    keywords.switchToFrame(locatorType, locatorValue);
                    break;
                    
                case "switchtoframebynameorid":
                    keywords.switchToFrameByNameOrId(testData);
                    break;
                    
                case "switchtoframebyindex":
                    keywords.switchToFrameByIndex(Integer.parseInt(testData));
                    break;
                    
                case "switchtodefaultcontent":
                case "defaultcontent":
                    keywords.switchToDefaultContent();
                    break;
                    
                case "switchtoparentframe":
                case "parentframe":
                    keywords.switchToParentFrame();
                    break;
                    
                // Alert Actions
                case "acceptalert":
                case "accept_alert":
                    keywords.acceptAlert();
                    break;
                    
                case "dismissalert":
                case "dismiss_alert":
                    keywords.dismissAlert();
                    break;
                    
                case "entertextinalert":
                    keywords.enterTextInAlert(testData);
                    break;
                    
                // Window Actions
                case "switchtonewwindow":
                case "switchwindow":
                    keywords.switchToNewWindow();
                    break;
                    
                case "switchtowindowbytitle":
                    keywords.switchToWindowByTitle(testData);
                    break;
                    
                case "closecurrentwindow":
                case "closewindow":
                    keywords.closeCurrentWindow();
                    break;
                    
                // Scroll Actions
                case "scrolltoelement":
                case "scroll_to_element":
                    keywords.scrollToElement(locatorType, locatorValue);
                    break;
                    
                case "scrollbypixels":
                    String[] pixels = testData.split(",");
                    keywords.scrollByPixels(Integer.parseInt(pixels[0].trim()), 
                                           Integer.parseInt(pixels[1].trim()));
                    break;
                    
                case "scrolltobottom":
                    keywords.scrollToBottom();
                    break;
                    
                case "scrolltotop":
                    keywords.scrollToTop();
                    break;
                    
                // Keyboard Actions
                case "pressenter":
                    keywords.pressEnter(locatorType, locatorValue);
                    break;
                    
                case "presstab":
                    keywords.pressTab(locatorType, locatorValue);
                    break;
                    
                // Wait Actions
                case "wait":
                case "waitforseconds":
                case "sleep":
                    keywords.waitForSeconds(Integer.parseInt(testData));
                    break;
                    
                case "waitforelementvisible":
                    keywords.waitForElementVisible(locatorType, locatorValue);
                    break;
                    
                case "waitforelementclickable":
                    keywords.waitForElementClickable(locatorType, locatorValue);
                    break;
                    
                case "waitforelementinvisible":
                    keywords.waitForElementInvisible(locatorType, locatorValue);
                    break;
                    
                case "waitforpageload":
                    keywords.waitForPageLoad();
                    break;
                    
                // Verification Actions
                case "verifyelementdisplayed":
                case "iselementdisplayed":
                    result = keywords.isElementDisplayed(locatorType, locatorValue);
                    break;
                    
                case "verifyelementenabled":
                case "iselementenabled":
                    result = keywords.isElementEnabled(locatorType, locatorValue);
                    break;
                    
                case "verifyelementselected":
                case "iselementselected":
                    result = keywords.isElementSelected(locatorType, locatorValue);
                    break;
                    
                case "verifytextpresent":
                    result = keywords.verifyTextPresent(testData);
                    break;
                    
                case "verifytitle":
                    result = keywords.verifyTitle(testData);
                    break;
                    
                case "verifytitlecontains":
                    result = keywords.verifyTitleContains(testData);
                    break;
                    
                // Get Actions (for validation purposes)
                case "gettext":
                    String text = keywords.getText(locatorType, locatorValue);
                    logger.info("Got text: " + text);
                    if (testData != null && !testData.isEmpty()) {
                        result = text.equals(testData);
                    }
                    break;
                    
                case "gettextcontains":
                    String actualText = keywords.getText(locatorType, locatorValue);
                    logger.info("Got text: " + actualText);
                    result = actualText.contains(testData);
                    break;
                    
                case "getattribute":
                    String attrValue = keywords.getAttribute(locatorType, locatorValue, testData);
                    logger.info("Got attribute value: " + attrValue);
                    if (testData2 != null && !testData2.isEmpty()) {
                        result = attrValue.equals(testData2);
                    }
                    break;
                    
                case "gettitle":
                    String title = keywords.getTitle();
                    logger.info("Got title: " + title);
                    if (testData != null && !testData.isEmpty()) {
                        result = title.equals(testData);
                    }
                    break;
                    
                case "getcurrenturl":
                    String url = keywords.getCurrentUrl();
                    logger.info("Got URL: " + url);
                    if (testData != null && !testData.isEmpty()) {
                        result = url.equals(testData);
                    }
                    break;
                    
                // Screenshot
                case "takescreenshot":
                case "screenshot":
                    keywords.takeScreenshot();
                    break;
                    
                default:
                    logger.error("Keyword not found: " + keyword);
                    throw new RuntimeException("Keyword not found: " + keyword);
            }
            
            logger.info("Keyword executed successfully: " + keyword);
            
        } catch (Exception e) {
            logger.error("Error executing keyword: " + keyword, e);
            result = false;
            throw new RuntimeException("Error executing keyword: " + keyword + " - " + e.getMessage(), e);
        }
        
        return result;
    }
    
    /**
     * Executes all test steps from Excel data
     */
    public void executeTestSteps(List<Map<String, String>> testSteps) {
        for (Map<String, String> step : testSteps) {
            String execute = step.get("Execute");
            
            // Skip steps marked as 'No'
            if (execute != null && execute.equalsIgnoreCase("no")) {
                logger.info("Skipping step: " + step.get("Keyword"));
                continue;
            }
            
            String keyword = step.get("Keyword");
            String locatorType = step.get("LocatorType");
            String locatorValue = step.get("LocatorValue");
            String testData = step.get("TestData");
            String testData2 = step.get("TestData2");
            
            executeKeyword(keyword, locatorType, locatorValue, testData, testData2);
        }
    }
}

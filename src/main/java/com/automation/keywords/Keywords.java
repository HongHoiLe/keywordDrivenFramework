package com.automation.keywords;

import com.automation.config.ConfigReader;
import com.automation.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Keywords - Contains all reusable keyword actions for test automation
 */
public class Keywords {
    
    private static final Logger logger = LogManager.getLogger(Keywords.class);
    private WebDriver driver;
    private WebDriverWait wait;
    
    public Keywords() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }
    
    /**
     * Opens the browser and navigates to URL
     */
    public void openBrowser(String url) {
        logger.info("Opening browser and navigating to: " + url);
        driver.get(url);
    }
    
    /**
     * Navigates to a specific URL
     */
    public void navigate(String url) {
        logger.info("Navigating to: " + url);
        driver.navigate().to(url);
    }
    
    /**
     * Clicks on an element
     */
    public void click(String locatorType, String locatorValue) {
        logger.info("Clicking on element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        element.click();
    }
    
    /**
     * Enters text into an input field
     */
    public void enterText(String locatorType, String locatorValue, String text) {
        logger.info("Entering text '" + text + "' into element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Clears text from an input field
     */
    public void clearText(String locatorType, String locatorValue) {
        logger.info("Clearing text from element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        element.clear();
    }
    
    /**
     * Selects option from dropdown by visible text
     */
    public void selectByVisibleText(String locatorType, String locatorValue, String text) {
        logger.info("Selecting '" + text + "' from dropdown: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    
    /**
     * Selects option from dropdown by value
     */
    public void selectByValue(String locatorType, String locatorValue, String value) {
        logger.info("Selecting value '" + value + "' from dropdown: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        Select select = new Select(element);
        select.selectByValue(value);
    }
    
    /**
     * Selects option from dropdown by index
     */
    public void selectByIndex(String locatorType, String locatorValue, int index) {
        logger.info("Selecting index '" + index + "' from dropdown: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    
    /**
     * Checks a checkbox
     */
    public void checkCheckbox(String locatorType, String locatorValue) {
        logger.info("Checking checkbox: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        if (!element.isSelected()) {
            element.click();
        }
    }
    
    /**
     * Unchecks a checkbox
     */
    public void uncheckCheckbox(String locatorType, String locatorValue) {
        logger.info("Unchecking checkbox: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        if (element.isSelected()) {
            element.click();
        }
    }
    
    /**
     * Selects a radio button
     */
    public void selectRadioButton(String locatorType, String locatorValue) {
        logger.info("Selecting radio button: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        if (!element.isSelected()) {
            element.click();
        }
    }
    
    /**
     * Double clicks on an element
     */
    public void doubleClick(String locatorType, String locatorValue) {
        logger.info("Double clicking on element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }
    
    /**
     * Right clicks on an element
     */
    public void rightClick(String locatorType, String locatorValue) {
        logger.info("Right clicking on element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }
    
    /**
     * Hovers over an element
     */
    public void mouseHover(String locatorType, String locatorValue) {
        logger.info("Hovering over element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    
    /**
     * Drags element from source to target
     */
    public void dragAndDrop(String sourceLocatorType, String sourceLocatorValue, 
                           String targetLocatorType, String targetLocatorValue) {
        logger.info("Drag and drop from " + sourceLocatorValue + " to " + targetLocatorValue);
        WebElement source = waitForElement(sourceLocatorType, sourceLocatorValue);
        WebElement target = waitForElement(targetLocatorType, targetLocatorValue);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }
    
    /**
     * Switches to an iframe
     */
    public void switchToFrame(String locatorType, String locatorValue) {
        logger.info("Switching to frame: " + locatorType + "=" + locatorValue);
        WebElement frame = waitForElement(locatorType, locatorValue);
        driver.switchTo().frame(frame);
    }
    
    /**
     * Switches to frame by name or ID
     */
    public void switchToFrameByNameOrId(String nameOrId) {
        logger.info("Switching to frame by name/id: " + nameOrId);
        driver.switchTo().frame(nameOrId);
    }
    
    /**
     * Switches to frame by index
     */
    public void switchToFrameByIndex(int index) {
        logger.info("Switching to frame by index: " + index);
        driver.switchTo().frame(index);
    }
    
    /**
     * Switches to default content
     */
    public void switchToDefaultContent() {
        logger.info("Switching to default content");
        driver.switchTo().defaultContent();
    }
    
    /**
     * Switches to parent frame
     */
    public void switchToParentFrame() {
        logger.info("Switching to parent frame");
        driver.switchTo().parentFrame();
    }
    
    /**
     * Accepts an alert
     */
    public void acceptAlert() {
        logger.info("Accepting alert");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    
    /**
     * Dismisses an alert
     */
    public void dismissAlert() {
        logger.info("Dismissing alert");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }
    
    /**
     * Gets alert text
     */
    public String getAlertText() {
        logger.info("Getting alert text");
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
    
    /**
     * Enters text in alert prompt
     */
    public void enterTextInAlert(String text) {
        logger.info("Entering text in alert: " + text);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }
    
    /**
     * Switches to a new window
     */
    public void switchToNewWindow() {
        logger.info("Switching to new window");
        String mainWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    
    /**
     * Switches to window by title
     */
    public void switchToWindowByTitle(String title) {
        logger.info("Switching to window with title: " + title);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }
    
    /**
     * Closes current window
     */
    public void closeCurrentWindow() {
        logger.info("Closing current window");
        driver.close();
    }
    
    /**
     * Gets text from an element
     */
    public String getText(String locatorType, String locatorValue) {
        logger.info("Getting text from element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        return element.getText();
    }
    
    /**
     * Gets attribute value from an element
     */
    public String getAttribute(String locatorType, String locatorValue, String attribute) {
        logger.info("Getting attribute '" + attribute + "' from element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        return element.getAttribute(attribute);
    }
    
    /**
     * Verifies element is displayed
     */
    public boolean isElementDisplayed(String locatorType, String locatorValue) {
        logger.info("Checking if element is displayed: " + locatorType + "=" + locatorValue);
        try {
            WebElement element = waitForElement(locatorType, locatorValue);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Verifies element is enabled
     */
    public boolean isElementEnabled(String locatorType, String locatorValue) {
        logger.info("Checking if element is enabled: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        return element.isEnabled();
    }
    
    /**
     * Verifies element is selected
     */
    public boolean isElementSelected(String locatorType, String locatorValue) {
        logger.info("Checking if element is selected: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        return element.isSelected();
    }
    
    /**
     * Verifies text is present on page
     */
    public boolean verifyTextPresent(String text) {
        logger.info("Verifying text is present: " + text);
        return driver.getPageSource().contains(text);
    }
    
    /**
     * Verifies page title
     */
    public boolean verifyTitle(String expectedTitle) {
        logger.info("Verifying page title: " + expectedTitle);
        return driver.getTitle().equals(expectedTitle);
    }
    
    /**
     * Verifies page title contains text
     */
    public boolean verifyTitleContains(String text) {
        logger.info("Verifying page title contains: " + text);
        return driver.getTitle().contains(text);
    }
    
    /**
     * Gets page title
     */
    public String getTitle() {
        logger.info("Getting page title");
        return driver.getTitle();
    }
    
    /**
     * Gets current URL
     */
    public String getCurrentUrl() {
        logger.info("Getting current URL");
        return driver.getCurrentUrl();
    }
    
    /**
     * Scrolls to element
     */
    public void scrollToElement(String locatorType, String locatorValue) {
        logger.info("Scrolling to element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Scrolls by pixels
     */
    public void scrollByPixels(int x, int y) {
        logger.info("Scrolling by pixels: x=" + x + ", y=" + y);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")");
    }
    
    /**
     * Scrolls to bottom of page
     */
    public void scrollToBottom() {
        logger.info("Scrolling to bottom of page");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    /**
     * Scrolls to top of page
     */
    public void scrollToTop() {
        logger.info("Scrolling to top of page");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }
    
    /**
     * Clicks element using JavaScript
     */
    public void jsClick(String locatorType, String locatorValue) {
        logger.info("JS clicking on element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    
    /**
     * Enters text using JavaScript
     */
    public void jsEnterText(String locatorType, String locatorValue, String text) {
        logger.info("JS entering text '" + text + "' into element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
    }
    
    /**
     * Executes JavaScript
     */
    public Object executeJavaScript(String script) {
        logger.info("Executing JavaScript: " + script);
        return ((JavascriptExecutor) driver).executeScript(script);
    }
    
    /**
     * Takes screenshot (returns base64 string)
     */
    public String takeScreenshot() {
        logger.info("Taking screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    
    /**
     * Presses keyboard key
     */
    public void pressKey(String locatorType, String locatorValue, Keys key) {
        logger.info("Pressing key " + key + " on element: " + locatorType + "=" + locatorValue);
        WebElement element = waitForElement(locatorType, locatorValue);
        element.sendKeys(key);
    }
    
    /**
     * Presses Enter key
     */
    public void pressEnter(String locatorType, String locatorValue) {
        pressKey(locatorType, locatorValue, Keys.ENTER);
    }
    
    /**
     * Presses Tab key
     */
    public void pressTab(String locatorType, String locatorValue) {
        pressKey(locatorType, locatorValue, Keys.TAB);
    }
    
    /**
     * Refreshes the page
     */
    public void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
    }
    
    /**
     * Navigates back
     */
    public void navigateBack() {
        logger.info("Navigating back");
        driver.navigate().back();
    }
    
    /**
     * Navigates forward
     */
    public void navigateForward() {
        logger.info("Navigating forward");
        driver.navigate().forward();
    }
    
    /**
     * Waits for specified seconds
     */
    public void waitForSeconds(int seconds) {
        logger.info("Waiting for " + seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Waits for element to be visible
     */
    public void waitForElementVisible(String locatorType, String locatorValue) {
        logger.info("Waiting for element to be visible: " + locatorType + "=" + locatorValue);
        By locator = getLocator(locatorType, locatorValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Waits for element to be clickable
     */
    public void waitForElementClickable(String locatorType, String locatorValue) {
        logger.info("Waiting for element to be clickable: " + locatorType + "=" + locatorValue);
        By locator = getLocator(locatorType, locatorValue);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Waits for element to disappear
     */
    public void waitForElementInvisible(String locatorType, String locatorValue) {
        logger.info("Waiting for element to disappear: " + locatorType + "=" + locatorValue);
        By locator = getLocator(locatorType, locatorValue);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Waits for page to load completely
     */
    public void waitForPageLoad() {
        logger.info("Waiting for page to load");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
     * Closes the browser
     */
    public void closeBrowser() {
        logger.info("Closing browser");
        driver.quit();
    }
    
    /**
     * Helper method to get locator based on type
     */
    private By getLocator(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "classname":
            case "class":
                return By.className(locatorValue);
            case "tagname":
            case "tag":
                return By.tagName(locatorValue);
            case "linktext":
            case "link":
                return By.linkText(locatorValue);
            case "partiallinktext":
            case "partiallink":
                return By.partialLinkText(locatorValue);
            case "css":
            case "cssselector":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new RuntimeException("Locator type not supported: " + locatorType);
        }
    }
    
    /**
     * Helper method to wait for element and return it
     */
    private WebElement waitForElement(String locatorType, String locatorValue) {
        By locator = getLocator(locatorType, locatorValue);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}

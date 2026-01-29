# Keyword Driven Framework

A comprehensive keyword-driven test automation framework built with Java, Selenium WebDriver, TestNG, and Maven.

## Project Structure

```
keywordDriven/
├── pom.xml                                    # Maven configuration
├── testng.xml                                 # TestNG suite configuration
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java          # Configuration reader
│   │   │   ├── driver/
│   │   │   │   └── DriverManager.java         # WebDriver management
│   │   │   ├── engine/
│   │   │   │   └── KeywordEngine.java         # Keyword execution engine
│   │   │   ├── keywords/
│   │   │   │   └── Keywords.java              # All keyword implementations
│   │   │   ├── reports/
│   │   │   │   └── ReportManager.java         # Extent Reports manager
│   │   │   └── utils/
│   │   │       ├── ExcelReader.java           # Excel data reader
│   │   │       └── ScreenshotUtil.java        # Screenshot utility
│   │   └── resources/
│   │       ├── config.properties              # Configuration file
│   │       └── log4j2.xml                     # Logging configuration
│   └── test/
│       ├── java/com/automation/
│       │   ├── base/
│       │   │   └── BaseTest.java              # Base test class
│       │   ├── listeners/
│       │   │   └── TestListener.java          # TestNG listener
│       │   └── tests/
│       │       ├── KeywordDrivenTest.java     # Main keyword test class
│       │       └── SampleTest.java            # Sample test examples
│       └── resources/testdata/
│           └── TestData.xlsx                  # Test data Excel file
└── target/
    ├── reports/                               # Extent HTML reports
    ├── screenshots/                           # Test screenshots
    └── logs/                                  # Log files
```

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser

## Setup

1. Clone or download the project
2. Open terminal in project directory
3. Run `mvn clean install` to download dependencies

## Excel Test Data Format

Create an Excel file at `src/test/resources/testdata/TestData.xlsx` with a sheet named "TestSteps":

| TestCaseID | StepNo | StepDescription | Keyword | LocatorType | LocatorValue | TestData | TestData2 | Execute |
|------------|--------|-----------------|---------|-------------|--------------|----------|-----------|---------|
| TC001 | 1 | Open Google | openBrowser | | | https://www.google.com | | Yes |
| TC001 | 2 | Enter search text | enterText | name | q | Selenium | | Yes |
| TC001 | 3 | Press Enter | pressEnter | name | q | | | Yes |
| TC002 | 1 | Open Login Page | openBrowser | | | https://example.com/login | | Yes |
| TC002 | 2 | Enter username | enterText | id | username | testuser | | Yes |

## Available Keywords

### Browser Actions
- `openBrowser` - Opens browser and navigates to URL
- `navigate` - Navigates to a URL
- `closeBrowser` - Closes the browser
- `refreshPage` - Refreshes current page
- `navigateBack` - Goes back in browser history
- `navigateForward` - Goes forward in browser history

### Click Actions
- `click` - Clicks on an element
- `doubleClick` - Double clicks on an element
- `rightClick` - Right clicks on an element
- `jsClick` - Clicks using JavaScript

### Input Actions
- `enterText` - Enters text into an input field
- `clearText` - Clears text from an input field
- `jsEnterText` - Enters text using JavaScript

### Dropdown Actions
- `selectByVisibleText` - Selects by visible text
- `selectByValue` - Selects by value attribute
- `selectByIndex` - Selects by index

### Checkbox/Radio
- `checkCheckbox` - Checks a checkbox
- `uncheckCheckbox` - Unchecks a checkbox
- `selectRadioButton` - Selects a radio button

### Mouse Actions
- `mouseHover` - Hovers over an element
- `dragAndDrop` - Drags and drops element

### Frame Actions
- `switchToFrame` - Switches to iframe
- `switchToFrameByNameOrId` - Switches by name/ID
- `switchToFrameByIndex` - Switches by index
- `switchToDefaultContent` - Switches to default content
- `switchToParentFrame` - Switches to parent frame

### Alert Actions
- `acceptAlert` - Accepts alert
- `dismissAlert` - Dismisses alert
- `enterTextInAlert` - Enters text in prompt

### Window Actions
- `switchToNewWindow` - Switches to new window
- `switchToWindowByTitle` - Switches by title
- `closeCurrentWindow` - Closes current window

### Scroll Actions
- `scrollToElement` - Scrolls to element
- `scrollByPixels` - Scrolls by x,y pixels
- `scrollToBottom` - Scrolls to page bottom
- `scrollToTop` - Scrolls to page top

### Keyboard Actions
- `pressEnter` - Presses Enter key
- `pressTab` - Presses Tab key

### Wait Actions
- `waitForSeconds` - Waits for specified seconds
- `waitForElementVisible` - Waits for element visibility
- `waitForElementClickable` - Waits for element to be clickable
- `waitForElementInvisible` - Waits for element to disappear
- `waitForPageLoad` - Waits for page load

### Verification Actions
- `verifyElementDisplayed` - Verifies element is displayed
- `verifyElementEnabled` - Verifies element is enabled
- `verifyElementSelected` - Verifies element is selected
- `verifyTextPresent` - Verifies text on page
- `verifyTitle` - Verifies page title
- `verifyTitleContains` - Verifies title contains text
- `getText` - Gets element text (compares if TestData provided)
- `getTextContains` - Verifies text contains value
- `getAttribute` - Gets attribute value
- `getTitle` - Gets page title
- `getCurrentUrl` - Gets current URL

### Screenshot
- `takeScreenshot` - Takes a screenshot

## Locator Types

- `id` - By ID
- `name` - By Name
- `className` / `class` - By Class Name
- `tagName` / `tag` - By Tag Name
- `linkText` / `link` - By Link Text
- `partialLinkText` / `partialLink` - By Partial Link Text
- `css` / `cssSelector` - By CSS Selector
- `xpath` - By XPath

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn clean test -Dtest=SampleTest
```

### Run with specific browser
```bash
mvn clean test -Dbrowser=firefox
```

### Run in headless mode
```bash
mvn clean test -Dheadless=true
```

## Configuration

Edit `src/main/resources/config.properties`:

```properties
browser=chrome          # chrome, firefox, edge
headless=false          # true for headless mode
implicitWait=10         # implicit wait in seconds
explicitWait=20         # explicit wait in seconds
testDataPath=src/test/resources/testdata/TestData.xlsx
```

## Reports

After test execution:
- **Extent Report**: `target/reports/TestReport_*.html`
- **Screenshots**: `target/screenshots/`
- **Logs**: `target/logs/automation.log`

## Adding New Keywords

1. Add the keyword method in `Keywords.java`
2. Add the case statement in `KeywordEngine.java`
3. Use the keyword in Excel or directly in test code

## Best Practices

1. Use meaningful test case IDs and step descriptions
2. Keep locators updated in the Excel file
3. Use explicit waits over implicit waits where possible
4. Group related test cases together
5. Use the Execute column to skip steps temporarily
6. Review logs and reports after each run

## Troubleshooting

1. **Browser not launching**: Ensure WebDriverManager can download drivers
2. **Element not found**: Check locator values and wait times
3. **Excel read error**: Verify Excel file format and sheet name
4. **Test failures**: Check screenshots and logs for details

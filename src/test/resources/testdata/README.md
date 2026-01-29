# Test Data Excel File Template

Create an Excel file named `TestData.xlsx` in this folder with the following structure:

## Sheet: TestSteps

| Column Name | Description | Example |
|-------------|-------------|---------|
| TestCaseID | Unique test case identifier | TC001 |
| StepNo | Step number | 1 |
| StepDescription | Description of the step | Open Google homepage |
| Keyword | Keyword to execute | openBrowser |
| LocatorType | Type of locator (id, name, xpath, css, etc.) | xpath |
| LocatorValue | Value of the locator | //input[@name='q'] |
| TestData | Primary test data | https://www.google.com |
| TestData2 | Secondary test data (optional) | |
| Execute | Yes/No to execute step | Yes |

## Sample Test Data:

### TC001 - Google Search Test
| TestCaseID | StepNo | StepDescription | Keyword | LocatorType | LocatorValue | TestData | TestData2 | Execute |
|------------|--------|-----------------|---------|-------------|--------------|----------|-----------|---------|
| TC001 | 1 | Open Google | openBrowser | | | https://www.google.com | | Yes |
| TC001 | 2 | Wait for page load | waitForPageLoad | | | | | Yes |
| TC001 | 3 | Enter search text | enterText | name | q | Selenium WebDriver | | Yes |
| TC001 | 4 | Press Enter | pressEnter | name | q | | | Yes |
| TC001 | 5 | Wait for results | waitForSeconds | | | 3 | | Yes |
| TC001 | 6 | Verify results | verifyTitleContains | | | Selenium | | Yes |

### TC002 - Login Test
| TestCaseID | StepNo | StepDescription | Keyword | LocatorType | LocatorValue | TestData | TestData2 | Execute |
|------------|--------|-----------------|---------|-------------|--------------|----------|-----------|---------|
| TC002 | 1 | Open Login Page | openBrowser | | | https://the-internet.herokuapp.com/login | | Yes |
| TC002 | 2 | Wait for page | waitForPageLoad | | | | | Yes |
| TC002 | 3 | Enter username | enterText | id | username | tomsmith | | Yes |
| TC002 | 4 | Enter password | enterText | id | password | SuperSecretPassword! | | Yes |
| TC002 | 5 | Click login | click | css | button[type='submit'] | | | Yes |
| TC002 | 6 | Wait for login | waitForSeconds | | | 2 | | Yes |
| TC002 | 7 | Verify success | verifyTextPresent | | | You logged into a secure area! | | Yes |

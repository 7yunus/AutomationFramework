# Cucumber automation framework for UI and API

**Prerequisites**

1. Java 11 or above should be present and environment variable should be set in the local system.
2. Maven should be present and environment variable should be set in the local system.
3. Browsers(Chrome or Firefox) should be present in the local system.

### Selenium-Maven-Cucumber-TestNG framework.

* Used maven as it can easily handle the entire lifecycle of a project.
* Implemented page object model using page factory.
* Added Log4j Api to generate log files.
* Used Allure, Extent and Cucumber reports.
* Used properties file to read the application url and to select browser.
* Supports chrome and firefox browser for this test
* Added Failed runner for retrying failed scenarios

### Packages:

#### UI Tests

* Feature file: `src/test/resources/features`
* Step Definitions: `src/test/java/org/example/sedin/stepDefinition`
* Page classes: `src/main/java/org/example/sedin/pages`
* Web driver setup: `src/main/java/org/example/sedin/configuration`
* Utils: `src/main/java/org/example/sedin/utilities`

#### API Tests

* Tests: `src/test/java/org/example/sedin/apiTests`

#### Cucumber Runners

* Cucumber runner: `src/test/java/org/example/sedin/runner`
* Failed runner: `src/test/java/org/example/sedin/runner/FailedTestRunner.java`

#### Hooks

* Hooks: `src/test/java/org/example/sedin/hooks`

### Maven Project Execution using following command:

To execute test sequentially, comment DataProvider method in CucumberRunner: `mvn clean test`  
To execute test in parallel, uncomment DataProvider method in CucumberRunner
class: `mvn clean test`   
To execute tests on cross browsers in parallel: `testng_cross_browser_parallel.xml`

* Remote or local single browser setup can be controlled using config.properties
* Remote or local cross browser setup can be controlled using testng_cross_browser_parallel.xml

### Jenkins file:

JenkinsFile

### Report generation:

Run Command after test execution-> `allure serve target/allure-results
`  
Allure report path-> target/allure-results

### UI Test cases covered:

> src/test/resources/features/CheckoutProduct.feature
> src/test/resources/features/LoginFailure.feature
> src/test/resources/features/PriceValidation.feature  
> src/test/resources/features/ProductList.feature

### API Test cases covered:

> src/test/java/org/example/sedin/apiTests/AuthTokenPassingExampleTest.java
> src/test/java/org/example/sedin/apiTests/CheckGETResponseWithConfigTest.java
> src/test/java/org/example/sedin/apiTests/JsonSchemaValidateExampleTest.java
> src/test/java/org/example/sedin/apiTests/ParseRequestBodyTest.java

### Reporting:

> Allure, Cucumber and Extent reports

### Author: Mohammed Yunus F


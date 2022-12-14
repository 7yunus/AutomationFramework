# Sedin Assessment

Created by - Mohammed Yunus F

**Prerequisites**

1. Java 8/11 should be present and environment variable should be set in the local system.
2. Maven should be present and environment variable should be set in the local system.
3. Browsers(Chrome or Firefox) should be present in the local system.

### Selenium-Maven-Cucumber-TestNG framework.

* Used maven as it can easily handle the entire lifecycle of a project.
* Implemented page object model using page factory.
* Added Log4j Api to generate log files.
* Used Allure and cucumber reports.
* Used properties file to read the application url and to select browser.
* Supports chrome and firefox browser for this test

### Packages:

#### UI Tests

* Feature file: `src/test/resources/features`
* Step Definitions: `src/test/java/org/example/sedin/stepDefinition`
* Page classes: `src/main/java/org/example/sedin/pages`
* Cucumber runner: `src/test/java/org/example/sedin/runner`
* Hooks: `src/test/java/org/example/sedin/hooks`
* Web driver setup: `src/main/java/org/example/sedin/configuration`
* Utils: `src/main/java/org/example/sedin/utilities`

#### API Tests

* Tests: `src/test/java/org/example/sedin/apiTests`

### Maven Project Execution using following command:

To execute->`mvn clean test`

### Report generation:

Run Command after test execution->`allure serve target/allure-results
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

**Allure Report Screenshots**

![allureReport1](https://user-images.githubusercontent.com/67258606/204088922-8909048c-f71a-4698-be45-0c81e1d31384.png)
![allureReport1](https://user-images.githubusercontent.com/67258606/204088865-d4c4c5d8-f0e3-4bbf-8e53-a21a6fdcf879.png)


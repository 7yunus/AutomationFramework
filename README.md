# Cermati Assessment
Created by - Mohammed Yunus F  

**Prerequisites**
1. Java 8/11 should be present and environment variable should be set in the local system.
2. Maven should be present and environment variable should be set in the local system.
3. Browsers(Chrome or Firefox) mentioned above should be present in the local system.

###Selenium-Maven-Cucumber-TestNG framework.  
* Used maven as it can easily handle the entire lifecycle of a project.
* Implemented page object model using page factory.
* Added Log4j Api to generate log files.
* Used Allure and cucumber reports.
* Used properties file to read the application url and to select browser.
* Supports chrome and firefox browser for this test 

###Packages:  
* Feature file - `src/test/resources/features`
* Step Definitions - `src/test/java/org/example/cermati/stepDefinition`
* Cucumber runner - `src/test/java/org/example/cermati/runner`
* Hooks = `src/test/java/org/example/cermati/hooks`
* Web driver setup - `src/main/java/org/example/cermati/configuration`
* Page classes - `src/main/java/org/example/cermati/pages`
* Utils - `src/main/java/org/example/cermati/utilities`

###Maven Project Execution using following command:

To execute->`mvn clean test`  

###Report generation:
Run Command after test execution->`allure serve target/allure-results
`  
Allure report path-> target/allure-results

###Test cases covered
>src/test/resources/features/ProductAccess.feature

**Allure Report Screenshots**

![allureReport1](https://user-images.githubusercontent.com/67258606/204088922-8909048c-f71a-4698-be45-0c81e1d31384.png)
![allureReport1](https://user-images.githubusercontent.com/67258606/204088865-d4c4c5d8-f0e3-4bbf-8e53-a21a6fdcf879.png)


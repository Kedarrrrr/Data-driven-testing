Data Driven Testing Framework – Login Module

Project Overview



This project demonstrates Data Driven Testing for a web application’s Login functionality.

The main goal is to validate the login behavior using multiple sets of input data without changing the test logic.



Instead of hardcoding values, test data is maintained separately and fed into the test execution dynamically.



Objective



To verify login functionality with valid and invalid credentials



To ensure the application behaves correctly for different user inputs



To reduce duplication of test scripts by separating test data from test logic



Scope of Testing



Valid login



Invalid username



Invalid password



Blank fields



Boundary cases (minimum / maximum input length)



Tools \& Technologies Used



Programming Language: Java



Automation Tool: Selenium WebDriver



Testing Framework: TestNG



Build Tool: Maven



Data Source: Excel (Apache POI)



Browser: Chrome



IDE: Eclipse / IntelliJ



Project Structure

DataDrivenTestingProject

│

├── src/test/java

│   ├── testcases

│   │   └── LoginTest.java

│   ├── utilities

│   │   └── ExcelUtils.java

│

├── testdata

│   └── LoginData.xlsx

│

├── pom.xml

└── README.md



Data Driven Approach



Test data is stored in an Excel file



Each row represents a different test scenario



Columns include:



Username



Password



Expected Result (Pass/Fail)



TestNG’s @DataProvider is used to read data from Excel and supply it to the test method.



Test Execution Flow



Launch browser



Navigate to login page



Read test data from Excel



Enter username and password



Click Login



Validate application response



Capture result (Pass/Fail)



Close browser



Key Scenarios Covered



Login with valid credentials → Successful login



Login with invalid credentials → Error message displayed



Login with blank fields → Validation message shown



Multiple test cases executed in a single run using data provider



Advantages of Data Driven Testing



Same test logic reused for multiple test cases



Easy to add new test scenarios by updating Excel



Better test coverage with less code duplication



Improves maintainability and scalability



Limitations



Only login functionality covered



No database validation



No reporting tool integrated (Extent Report can be added)



Future Enhancements



Add Extent Reports



Integrate with CI/CD (Jenkins)



Extend framework to cover other modules



Add negative and edge-case scenarios



Conclusion



This project demonstrates how Data Driven Testing improves test coverage and flexibility by separating test data from test scripts, making the automation framework easier to maintain and extend.


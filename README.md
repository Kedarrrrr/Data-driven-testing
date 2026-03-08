Data Driven Testing – Selenium Java
Overview

Data Driven Testing (DDT) is a test automation technique where test data is separated from test scripts. The same test script is executed multiple times with different input values.

In this project, Selenium WebDriver with Java is used to perform automated UI testing, while test data is stored externally in files such as Excel, CSV, or JSON.

This approach improves test coverage, maintainability, and reusability of automation scripts.

Technologies Used

Java

Selenium WebDriver

TestNG

Apache POI (for Excel handling)

Maven

Project Structure
selenium-data-driven-testing
│
├── src
│   ├── main
│   │   └── java
│   │       └── utils
│   │           └── ExcelUtils.java
│   │
│   └── test
│       └── java
│           └── tests
│               └── LoginTest.java
│
├── testdata
│   └── LoginData.xlsx
│
├── pom.xml
└── README.md
Test Data

Test data is stored in an Excel file.

Example: LoginData.xlsx

Username	Password	ExpectedResult
user1	pass123	Success
user2	wrongpass	Failure
admin	admin123	Success

Each row represents a separate test execution.

Excel Utility Class

ExcelUtils.java

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    public static Object[][] getTestData(String filePath, String sheetName) throws Exception {

        FileInputStream fis = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for(int i = 1; i < rowCount; i++) {
            for(int j = 0; j < colCount; j++) {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        return data;
    }
}
Test Script Example

LoginTest.java

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {
        return ExcelUtils.getTestData("testdata/LoginData.xlsx", "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com/login");

        // locate elements and perform login
        // driver.findElement(...)

        // validation logic here

        driver.quit();
    }
}
How Data Driven Testing Works

Test data is stored in an external file (Excel).

The DataProvider in TestNG reads the data.

Selenium test executes once for every row of data.

Results are validated based on expected outcomes.

How to Run Tests

Using Maven:

mvn clean test

Or run the TestNG test class directly from the IDE.

Advantages

Reusable test scripts

Easy maintenance

Improved test coverage

Separation of test logic and data

Limitations

Large datasets may slow test execution

Managing test data files can become complex

Debugging failures may require checking multiple data rows

Best Practices

Keep test data separate from test logic

Use meaningful column names in datasets

Validate data before running tests

Maintain smaller, manageable datasets

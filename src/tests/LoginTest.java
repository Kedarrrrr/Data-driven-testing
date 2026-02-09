package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import utils.ExcelUtil;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com/login"); // dummy URL
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {
        return ExcelUtil.getTestData(
                "testdata/logindata.xlsx",
                "Sheet1"
        );
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();

        if (expectedResult.equals("success")) {
            Assert.assertTrue(driver.getPageSource().contains("Welcome"));
        } else {
            Assert.assertTrue(driver.getPageSource().contains("Invalid"));
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

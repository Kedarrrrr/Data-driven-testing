package compund_Interest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class Calculate {

    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                + "//src//test//resources//configure.properties");

        prop.load(file);

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(prop.getProperty("URL"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement popup = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("wzrk-confirm")));
            popup.click();
        } catch (Exception e) {
        }

        String File = System.getProperty("user.dir") + "//Test_Data//Compound.xlsx";
        String Sheet = "Sheet1";

        int rows = Utilities.getRowCount(File, Sheet);

        WebElement principle = driver.findElement(By.id("principal"));
        WebElement interest = driver.findElement(By.id("interest"));
        WebElement period = driver.findElement(By.id("tenure"));

        WebElement calculate = driver.findElement(By.xpath("//img[contains(@src,'btn_calcutate')]"));
        WebElement clear = driver.findElement(By.xpath("//img[@class='PL5']"));

        Select tenureType = new Select(driver.findElement(By.id("tenurePeriod")));
        tenureType.selectByVisibleText("year(s)");

        Select interestType = new Select(driver.findElement(By.id("frequency")));
        interestType.selectByVisibleText("Simple Interest");

        for (int r = 1; r <= rows; r++) {

            String p = Utilities.getCellData(File, Sheet, r, 0);
            String roi = Utilities.getCellData(File, Sheet, r, 1);
            String per = Utilities.getCellData(File, Sheet, r, 2);
            String exp = Utilities.getCellData(File, Sheet, r, 3);

            principle.sendKeys(p);
            interest.sendKeys(roi);
            period.sendKeys(per);

            calculate.click();

            try {

                Alert alert = driver.switchTo().alert();

                if (exp.equalsIgnoreCase("Alert")) {

                    Utilities.setCellData(File, Sheet, r, 4, "PASS");
                    Utilities.fillGreenColor(File, Sheet, r, 4);

                } else {

                    Utilities.setCellData(File, Sheet, r, 4, "FAIL");
                    Utilities.fillRedColor(File, Sheet, r, 4);
                }

                alert.accept();

            }

            catch (Exception e) {

                String actual = driver.findElement(By.xpath("//span[@id='resp_matval']//strong")).getText();

                if (actual.equals(exp)) {

                    Utilities.setCellData(File, Sheet, r, 4, "PASS");
                    Utilities.fillGreenColor(File, Sheet, r, 4);

                } else {

                    Utilities.setCellData(File, Sheet, r, 4, "FAIL");
                    Utilities.fillRedColor(File, Sheet, r, 4);
                }

            }

            clear.click();
        }

        driver.quit();
    }
}
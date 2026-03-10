package compund_Interest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Simple_interest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html?classic=true");
		
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement pop =mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='wzrk-confirm']")))
		pop.click();
		
		String sheet = "Sheet1";
		String File = System.getProperty("user.dir")+"//Test_Data//Compound.xlsx";
		int nr=Utilities.getRowNo(File, sheet);
		
		//web elements from page
		WebElement principle = driver.findElement(By.xpath("//input[@id='principal']"));
		WebElement interest = driver.findElement(By.xpath("//input[@id='interest']"));
		WebElement period = driver.findElement(By.xpath("//input[@id='tenure']"));
		WebElement dpd=driver.findElement(By.xpath("//select[@id='tenurePeriod']"));

		Select dropdown =new Select(dpd);
		dropdown.selectByContainsVisibleText("year(s)");
		
		WebElement i=driver.findElement(By.xpath("//select[@id='frequency']"));
		Select type=new Select(i);
		type.selectByContainsVisibleText("Simple Interest");
		
	
	}

}

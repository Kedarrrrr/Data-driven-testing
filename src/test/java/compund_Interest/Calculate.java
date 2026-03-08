package compund_Interest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Calculate {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Properties propertiesobj=new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//configure.properties");
		propertiesobj.load(file);
		
		WebDriver driver = new ChromeDriver();
		driver.get(propertiesobj.getProperty("URL"));
		
		String Sheet= "Sheet1";
		String File = System.getProperty("user.dir")+"//Test_Data//Compound.xlsx";
		
		int nr=Utilities.getRowNo(File, Sheet);
		
		int nc=Utilities.getColNo(File, Sheet, nr);
		
		WebElement principle = driver.findElement(By.xpath("//input[@id='principal']"));
		WebElement interest = driver.findElement(By.xpath("//input[@id='interest']"));
		WebElement period = driver.findElement(By.xpath("//input[@id='tenure']"));
		//WebElement days=driver.findElement(By.xpath("//select[@id='tenurePeriod']")).click().findElement(By.xpath("//option[text()='year(s)']"));
		WebElement calculate = driver.findElement(By.xpath("//img[@src='https://images.moneycontrol.com/images/mf_revamp/btn_calcutate.gif']"));
		WebElement clear =driver.findElement(By.xpath("//img[@class='PL5']"));
		WebElement dpd=driver.findElement(By.xpath("//select[@id='tenurePeriod']"));
		
		Select dropdown =new Select(dpd);
		dropdown.selectByContainsVisibleText("year(s)");
		
		WebElement i=driver.findElement(By.xpath("//select[@id='frequency']"));
		Select type=new Select(i);
		type.selectByContainsVisibleText("Simple Interest");
	
		for(int r=2; r<=nr;r++) {
			
				XSSFCell p = Utilities.getCell(File, Sheet, r, 1);
				XSSFCell ce=Utilities.getCell(File, Sheet, r, 2);
				XSSFCell t= Utilities.getCell(File, Sheet, r, 3);
				XSSFCell exp=Utilities.getCell(File, Sheet, r, 4);
				
				principle.sendKeys(p.toString());
				interest.sendKeys(ce.toString());
				period.sendKeys(t.toString());
				
				calculate.click();
				
				String ev=driver.findElement(By.xpath("//span[@id='resp_matval']//strong")).getText();
				
				if(ev==exp.toString()) {
					System.out.println("Test Passed");
					Utilities.setCell(File, Sheet, r, 6, "Pass");
					Utilities.fillGreenColor(File, Sheet, r, 6);
					
				}else {
					System.out.println("Test Failed");
					Utilities.setCell(File, Sheet, r, 6, "Fail");
					Utilities.fillGreenColor(File, Sheet, r, 6);
				}
				
		}
		
	}

}

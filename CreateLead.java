package week5.day2.dataparameterization;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends BaseClass {
	
	@DataProvider(name = "Testdata")
	public Object[][] fetchCreateLeadData() throws InvalidFormatException, IOException{
		String data[][] = ReadExcelData.readExcel("Create Lead");
		return data;
		
	}

	@Test(dataProvider = "Testdata")
	public void NewLead(String companyName,String firstName, String lastName) throws InterruptedException {
				//CLick Create Lead
				driver.findElement(By.linkText("Create Lead")).click();
				
				//Enter company name, first name, last name
				driver.findElement(By.id("createLeadForm_companyName")).sendKeys("CTS");
				driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Pavi");
				driver.findElement(By.id("createLeadForm_lastName")).sendKeys("N");
								
				//Click Create Lead button
				driver.findElement(By.name("submitButton")).click();
				
				//Verify company name
				String text2 = driver.findElement(By.id("viewLead_companyName_sp")).getText();
				String comp = text2.replaceAll("[^a-zA-Z]", "");
				
				if(comp.equals("CTS"))
				{
					System.out.println("New Lead is created");
				}
				else
				{
					System.out.println("New Lead is not created");
				}
		 
	}

}

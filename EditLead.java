package week5.day2.dataparameterization;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditLead extends BaseClass{
	
	@DataProvider(name = "Testdata")
	public Object[][] fetchEditLeadData() throws InvalidFormatException, IOException{
		String data[][] = ReadExcelData.readExcel("Edit Lead");
		return data;
	}
	
	@Test(dataProvider = "Testdata")
	public void EditExistingLead(String name, String companyName) throws InterruptedException {
							
		//6	Click Leads link
		driver.findElement(By.linkText("Leads")).click();
					
		//7	Click Find leads
		driver.findElement(By.linkText("Find Leads")).click();
		Thread.sleep(5000);
		
		// 8	Enter first name
		driver.findElement(By.xpath("(//input[@name='firstName'])[3]")).sendKeys(name);
		
		//10	Click find leads button
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		Thread.sleep(4000);
				
		//12	Click First Resulting lead
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		
		// 11 Verify title of the page
		String title = driver.getTitle();
		if(title.contains("View Lead"))
			System.out.println("Lead is viewed");
		else
			System.out.println("Lead is not viewed");
		String oldCompanyName = driver.findElement(By.id("viewLead_companyName_sp")).getText();
		
		System.out.println("Company Name-"+oldCompanyName);
		
		// 12 Click Edit
		driver.findElement(By.xpath("//a[text() = 'Edit']")).click();
		
		// 13 Change the company name
		driver.findElement(By.id("updateLeadForm_companyName")).clear();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys(companyName);
		
		// 14 Click Update
		driver.findElement(By.xpath("//input[@value = 'Update']")).click();
		
		// 15 Confirm the changed name appears
		String newCompanyName = driver.findElement(By.id("viewLead_companyName_sp")).getText();
		
		System.out.println("Updated Company Name-"+newCompanyName);
		
		if(oldCompanyName.equals(newCompanyName))
			System.out.println("Lead edit is not done");
		else
			System.out.println("Lead Edit is done");
		}

}

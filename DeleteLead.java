package week5.day2.dataparameterization;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteLead extends BaseClass{
	@DataProvider(name = "Testdata")
	public Object[][] fetchDeleteLeadData() throws InvalidFormatException, IOException{
		String[][] data = ReadExcelData.readExcel("Delete Lead");
		return data;
		
	}

	@Test(dataProvider = "Testdata")
	public void DelLead(String phoneNum) throws InterruptedException {
		
		//Delete Lead:
		//6	Click Leads link
		driver.findElement(By.linkText("Leads")).click();
		
		//7	Click Find leads
		driver.findElement(By.linkText("Find Leads")).click();
		
		//8	Click on Phone
		driver.findElement(By.xpath("//span[text() = 'Phone']")).click();
		
		//9	Enter phone number
		driver.findElement(By.name("phoneNumber")).sendKeys(phoneNum);
		
		//10	Click find leads button
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		Thread.sleep(3000);
		
		//11	Capture lead ID of First Resulting lead
		String LeadID = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]")).getText();
		
		//12	Click First Resulting lead
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		Thread.sleep(4000);
		
		//13	Click Delete
		driver.findElement(By.xpath("//a[text() = 'Delete']")).click();
		
		//14	Click Find leads
		driver.findElement(By.linkText("Find Leads")).click();
		
	    //15	Enter captured lead ID
		driver.findElement(By.xpath("//input[@name = 'id']")).sendKeys(LeadID);
		Thread.sleep(4000);
		
		//16	Click find leads button
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		Thread.sleep(4000);
		
		//17	Verify message "No records to display" in the Lead List. This message confirms the successful deletion
		boolean isElementDisplayed = driver.findElement(By.xpath("//div[text() = 'No records to display']")).isDisplayed();
		if(isElementDisplayed)
			System.out.println("Lead is deleted");
		else
			System.out.println("Lead is not deleted");

	}

}

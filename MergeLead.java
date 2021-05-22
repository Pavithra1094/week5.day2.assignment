package week5.day2.dataparameterization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.java.it.Data;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeLead extends BaseClass {
	@DataProvider(name = "Testdata")
	public Object[][] fetchMergeLeadData() throws InvalidFormatException, IOException{
		String data[][] = ReadExcelData.readExcel("Merge Lead");
		return data;
	}
	
	@Test(dataProvider = "Testdata")
	public void MergeExisitngLead(String fromLead,String toLead) throws InterruptedException {
		
		 // Click on contacts Button
		 driver.findElement(By.xpath("//a[text() = 'Leads']")).click();
		 
		 // Click on Merge Contacts using Xpath Locator
		 driver.findElement(By.xpath("//a[text() = 'Merge Leads']")).click();
		 
		 // Click on Widget of From Contact
		 driver.findElement(By.xpath("//span[text() = 'From Lead']/following::img")).click();
		 
		 Set<String> windowHandles = driver.getWindowHandles();
		 List<String> listWindowHandles = new ArrayList<String>(windowHandles);
		 driver.switchTo().window(listWindowHandles.get(1));
		 
		 //Search for name
		 driver.findElement(By.name("firstName")).sendKeys(fromLead);
		 
		 //click search
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		 
		 Thread.sleep(5000);
		 // Click on First Resulting Contact
		 driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[1]")).click();
		  
		 driver.switchTo().window(listWindowHandles.get(0));
		 // Click on Widget of To Contact
		 driver.findElement(By.xpath("(//span[text() = 'From Lead']/following::img)[2]")).click();
		  
		 Set<String> windowHandles1 = driver.getWindowHandles();
		 List<String> listWindowHandles1 = new ArrayList<String>(windowHandles1);
		 driver.switchTo().window(listWindowHandles1.get(1));
		 
		 //Search for name
		 driver.findElement(By.name("firstName")).sendKeys(toLead);
		 
		 //click search
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();
		
		 
		 Thread.sleep(5000);
		 // Click on Second Resulting Contact
		 driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[2]")).click();
		 
		 driver.switchTo().window(listWindowHandles.get(0));
		 // Click on Merge button using Xpath Locator
		 driver.findElement(By.xpath("//a[text() = 'Merge']")).click();
		  
		 // Accept the Alert
		 Alert alert = driver.switchTo().alert();
		 alert.accept();
		  
		 // Verify the title of the page
		 Thread.sleep(5000);
		 String title = driver.getTitle();
		 System.out.println(title);
	}

}

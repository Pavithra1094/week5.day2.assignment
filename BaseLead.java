package week5.day2.invocationcount;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseLead {


	ChromeDriver driver;
	@Parameters({"url","username","password"})
	@BeforeMethod
	public void login(String url,String username, String password) {
    
     WebDriverManager.chromedriver().setup();
		
		// Launch the chrome browser
		driver=new ChromeDriver();
		
		// Load the url
		driver.get(url); 
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Maximize the screen
		driver.manage().window().maximize();	
		
		//Find the user name and type the value
		driver.findElement(By.id("username")).sendKeys(username);
		
		//Find the password and type the value
		driver.findElement(By.id("password")).sendKeys(password);
		
		//Find the login button and click
		driver.findElementByClassName("decorativeSubmit").click();
		
		//Click link
		driver.findElement(By.linkText("CRM/SFA")).click();
		//Click leads
		driver.findElement(By.linkText("Leads")).click();
	}
	//@AfterMethod(groups="common")
	@AfterMethod
	public void teardown()
	{
		driver.close();
	}

}
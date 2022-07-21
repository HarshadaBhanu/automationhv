package tests;

import org.apache.commons.exec.OS;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/*
 BaseClass is a parent class 
 It initializes new chromedriver for each new test 
 */
public class BaseClass {
	
	
	public WebDriver driver; // declaration of object , creating a ref variable d
    
	@BeforeMethod //before each test case -- launch the browser and get url
	public void setupApplication() {
		/*
		 to set the path of the driver for the respective browser
		 */
		if (OS.isFamilyWindows()) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		}

		driver = new ChromeDriver(); //initilization of object - create new instance of chrome driver by creating object 
		driver.get("https://www.sogeti.com/"); //url to test - opens browser and url is hit
        driver.findElement(By.xpath("//button[contains(text(),\"Allow all cookies\")]")).click();
        driver.manage().window().maximize(); //open window and maximize
        System.out.println("URL for Sogeti launched");

	}
	
	@AfterMethod //after each test case -- quit the browser
   public void closeApplication() {
		driver.quit(); //quit the whole browser session
	}
	
	
	
	

}

package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AutomationPage;
import pages.HomePage;

public class UITestCases extends BaseClass{
	
	@Test(priority=1)
	public void verifyServicesAutomationLink() {
		
		HomePage home = new HomePage(driver); // creates obj, in HomePage as the services and Automation are on home page itself, 
		//passing the parameter as driver which was initialized in base class
		home.hoverServicesAndClickAutomationLink();
		AutomationPage automation = new AutomationPage(driver);
		Assert.assertEquals(automation.getAutomationText(), "AUTOMATION", "The Automation page is not loaded");
		System.out.println("Verified Automation Page is loaded");
		Assert.assertTrue(automation.verifyServicesAndAutomationLink(), "Services and Automation both are not selected");
		System.out.println("Verified both Services and Automation are selected");
				
	}
	
	@Test(priority=2)
	public void verifyContactUs()  {
		
		HomePage home = new HomePage(driver);
		home.hoverServicesAndClickAutomationLink();
		AutomationPage automation = new AutomationPage(driver);
		automation.addContactUs();
	}
	
	@Test(priority=3)
	public void verifyWorldwideDropdownCounties() {
		
		HomePage home = new HomePage(driver);
		home.clickWorldwide();
		home.validateCountry();
	}
	
	
	
	

}

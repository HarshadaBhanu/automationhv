package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class HomePage {

	WebDriver driver;
	Actions action;
	
	//Using FindBy for locating elements
	@FindBy(xpath = "//*[@id=\"main-menu\"]//div[@class=\"wrapper\"]//span[contains(text(),'Services')]")
	WebElement servicesLink;
	
	@FindBy(xpath = "//div[@class=\"mega-navbar refreshed level2\"]//a[@href=\"https://www.sogeti.com/services/automation/\"]")
	WebElement automationLink;
	
	@FindBy(xpath = "//span[@aria-label='Worldwide']")
	WebElement worldwideDropdown;
	
	@FindBy(xpath = "//div[@id='country-list-id']//li")
	WebElement countryList;
	
	public HomePage(WebDriver driver) {	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void hoverServicesAndClickAutomationLink() {
		
		action = new Actions(this.driver);
		action.moveToElement(servicesLink);
		action.moveToElement(automationLink);
		action.click().build().perform();
		System.out.println("Hover Services Link and clicked Automation Link");
		//action.build().perform();
	}
	

	
	public void clickWorldwide() {
		worldwideDropdown.click();
	}
	
	public void validateCountry() {
		List<WebElement> countries = driver.findElements(By.xpath("//div[@id='country-list-id']//li"));
		
		List<String> expectedTitleList = new ArrayList<String>(); 
        expectedTitleList.add("Sogeti Belgium");
        expectedTitleList.add("Sogeti Finland");
        expectedTitleList.add("Sogeti France | Gérez la transformation numérique de votre entreprise avec Sogeti");
        expectedTitleList.add("Sogeti Deutschland GmbH – Beratungsdienstleistungen für Softwaretest und Qualitätssicherung");
        expectedTitleList.add("Sogeti Ireland");
        expectedTitleList.add("Sogeti Luxembourg");
        expectedTitleList.add("We Make Technology Work | Sogeti");
        expectedTitleList.add("Sogeti Norge");
        expectedTitleList.add("Sogeti España");
        expectedTitleList.add("Sogeti Sverige");
        expectedTitleList.add("Sogeti UK | Software Testing Services, Digital Services, DevOps Services, DevOps Consultancy, Testing Consultancy");
        expectedTitleList.add("Sogeti USA");

		
		String originalWindow = driver.getWindowHandle();

		for (WebElement country : countries) {
		    System.out.println("Country name text:" + country.getText());
		    country.click();
		   
		}
		
		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator(); 
		
		List<String> actualTitleList = new ArrayList<String>();

		while(I1.hasNext())
		{

			String child_window=I1.next(); 
	
	
			if(!originalWindow.equals(child_window)) // apart from original window iterate to rest windows
				{
					driver.switchTo().window(child_window);
			
					System.out.println(driver.switchTo().window(child_window).getTitle());
					
					actualTitleList.add(getTitle());
			
					driver.close();
				}

		}
		//switch to the parent window
		driver.switchTo().window(originalWindow);
		
		Assert.assertTrue(expectedTitleList.size() == actualTitleList.size() && expectedTitleList.containsAll(actualTitleList) && actualTitleList.containsAll(expectedTitleList), "Country link not working");
		System.out.println("All the countries in the list under worldwide are working");
	}
}

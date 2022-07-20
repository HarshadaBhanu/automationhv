package pages;

import java.time.Duration;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.utility.RandomString;

public class AutomationPage {

WebDriver driver;
Actions action;
	
	//Using FindBy for locating elements
	@FindBy(xpath = "//div[@class=\"case-item-box\"]")
	WebElement automationText;
	
	@FindBy(xpath = "//li[@class=\"selected has-children  expanded level2 focus-style\"]//div[@class=\"wrapper\"]//span[text()='Services']")
	WebElement servicesLink;
	
	@FindBy(xpath = "//li[@class=\"selected has-children  expanded level2 focus-style hover\"]//div[@class=\"wrapper\"]//span[text()='Services']")
	WebElement servicesLinkSelected;
	
	@FindBy(xpath = "//li[@class=\"selected  current expanded\"]//a[@class=\"subMenuLink\" ][contains(text(),\"Automation\")]")
	WebElement automationLinkSelected;
	
	@FindBy(id = "4ff2ed4d-4861-4914-86eb-87dfa65876d8")
	WebElement firstName;
	
	@FindBy(id = "11ce8b49-5298-491a-aebe-d0900d6f49a7")
	WebElement lastName;
	
	@FindBy(id = "056d8435-4d06-44f3-896a-d7b0bf4d37b2")
	WebElement email;
	
	@FindBy(id = "755aa064-7be2-432b-b8a2-805b5f4f9384")
	WebElement phone;
	
	@FindBy(id = "703dedb1-a413-4e71-9785-586d609def60")
	WebElement company;
	
	@FindBy(id = "e74d82fb-949d-40e5-8fd2-4a876319c45a")
	WebElement country;
	
	@FindBy(id = "88459d00-b812-459a-99e4-5dc6eff2aa19")
	WebElement message;
	
	@FindBy(xpath = "//label[normalize-space()='I agree']")
	WebElement agree;
	
	@FindBy(xpath = "//div[@class=\"rc-anchor-center-item rc-anchor-checkbox-holder\"]")
	WebElement captcha;
	
	@FindBy(name="submit")
	WebElement submit;
	
	public AutomationPage(WebDriver driver) {	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getAutomationText() {
		return automationText.getText();
		
	}
	
	public boolean verifyServicesAndAutomationLink() {
		
		action = new Actions(this.driver);
		action.moveToElement(servicesLink);
		action.build().perform();
		
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(servicesLinkSelected));
		
		if( servicesLinkSelected.isDisplayed() && automationLinkSelected.isDisplayed() )
			return true;
		else 
			return false;
	}	
	
	public void addContactUs()  {
		
		//Random rand = new Random();
		
		this.firstName.sendKeys(RandomStringUtils.randomAlphabetic(6));
		this.lastName.sendKeys(RandomStringUtils.randomAlphabetic(6));
		this.email.sendKeys(RandomStringUtils.randomAlphabetic(6)+"@"+RandomStringUtils.randomAlphabetic(4)+".com");
		this.phone.sendKeys(RandomStringUtils.random(10, "1234567890".toCharArray()));
		this.company.sendKeys(RandomStringUtils.randomAlphabetic(6));
		Select cntry = new Select(country);
		cntry.selectByVisibleText("India");
		this.message.sendKeys(RandomStringUtils.randomAlphabetic(20));
		this.agree.click();
		
		
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
		wait.until(ExpectedConditions.elementToBeClickable(captcha)).click();
		driver.switchTo().defaultContent();
		
		//new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]"))); 

		//new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark"))).click();
		//this.captcha.click();
		this.submit.click();
		
		//Thread.sleep(10000);
	}
	
	
}

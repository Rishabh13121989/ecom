package automationproject.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationproject.AbstractComponents.AbstractComponent;

public class Checkout extends AbstractComponent {

	WebDriver driver;
	WebDriverWait w;

	public Checkout(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		w = new WebDriverWait (driver, Duration.ofSeconds(10));
	}

	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;

	By countryBy=By.xpath("(//button[contains(@class,'ta-item')])");

	@FindBy(xpath="(//button[contains(@class,'ta-item')])")
	WebElement typedCountry;

	@FindBy(css=".action__submit")
	WebElement placeOrder;

	By successVerificationBy= By.cssSelector(".hero-primary");

	@FindBy(css="li:nth-of-type(5)")
	WebElement signoff;

	public void orderCompletion()
	{
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry,"canada").build().perform();
		w.until(ExpectedConditions.elementToBeClickable(countryBy));
		typedCountry.click();
		placeOrder.click();
	}
	
	public String confirmationMessage()
	{
		w.until(ExpectedConditions.visibilityOfElementLocated(successVerificationBy));
		WebElement element= driver.findElement(successVerificationBy);
		 String successMessage = element.getText().trim();
		 System.out.println("Order success message: " + successMessage);
		signoff.click();
		return successMessage;
	}
	





}

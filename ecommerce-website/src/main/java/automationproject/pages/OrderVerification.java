package automationproject.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationproject.AbstractComponents.AbstractComponent;

public class OrderVerification extends AbstractComponent {

	WebDriver driver;


	public OrderVerification(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}



	By cartProducBy=By.cssSelector(".cart h3");

	@FindBy(css=".cart h3")
	List<WebElement> productNames;

	By checkoutBy=By.cssSelector(".totalRow .btn");

	@FindBy(css=".totalRow .btn")
	WebElement checkoutButton;

	

	public  Checkout  verifyOrder(String productName) 
	{	

		waitForVisibility(cartProducBy);
		Boolean productMatch= productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		System.out.println(productMatch);
		
		if(productMatch) {
			System.out.println("Product found in cart: " + productName);
		}
		else {
			System.out.println("Product not found in cart: " + productName);
		}
		waitForVisibility(checkoutBy);
		checkoutButton.click();
		Checkout checkout= new Checkout(driver);
		return checkout;
		
	}
	
	
	
	





}

package automationproject.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationproject.AbstractComponents.AbstractComponent;

public class VerifyPlacedOrder extends AbstractComponent{
	WebDriver driver;
	
	public VerifyPlacedOrder(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> totalOrder;
	
	
	
	public VerifyPlacedOrder verifyThePlacedOrder(String productName)
	{
		Boolean OrderMatch= totalOrder.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		System.out.println(OrderMatch);
		
		if(OrderMatch) {
			System.out.println("Order found in cart: " + productName);
		}
		else {
			System.out.println("Order not found in cart: " + productName);
		}
		VerifyPlacedOrder verifyPlacedOrder = new VerifyPlacedOrder(driver);
		return verifyPlacedOrder;
	}

	
	
	
	
	
}

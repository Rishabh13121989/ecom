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

public class AddingProductToCart extends AbstractComponent {

	WebDriver driver;
	
	
	public AddingProductToCart(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	By productsBy = By.cssSelector(".mb-3");
	
	@FindBy(css=".mb-3")
	WebElement productSection;
	
	By addtoCartBy = By.cssSelector(".card-body button:last-of-type");
	
	By productNameBy = By.cssSelector("b");
	
	By toastBy = By.cssSelector("#toast-container");
	
	By animationBy = By.cssSelector(".ng-animating");
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(xpath="//i[@class='fa fa-handshake-o']")
	WebElement placedOrder;
	
	public void addToCart(String productName)
	{
		waitForVisibility(productsBy);
		List<WebElement> products= driver.findElements(productsBy);
		WebElement prod=products.stream().filter(product ->product.findElement(productNameBy).getText().equalsIgnoreCase(productName))
				.findFirst().orElseThrow(() -> new RuntimeException("Product not found: " + productName));
		prod.findElement(addtoCartBy).click();
		waitForVisibility(toastBy);
		waitForVisibility(animationBy);
		
	}
	
	public OrderVerification gotoCart()
	{
		cartButton.click();
		OrderVerification orderVerification = new OrderVerification(driver);
		return orderVerification;
	}
	
	
	public VerifyPlacedOrder gotoOrderHistory() {
	    // Code to navigate to order history page
	    placedOrder.click(); // Assuming clicking this navigates to order history
	    return new VerifyPlacedOrder(driver);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

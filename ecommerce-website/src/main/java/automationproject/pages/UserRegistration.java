package automationproject.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationproject.AbstractComponents.AbstractComponent;

public class UserRegistration extends AbstractComponent{
	
	WebDriver driver;
	
	
	public UserRegistration(WebDriver driver)
	{
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".text-reset")
	WebElement resetButton;
	
	@FindBy(id = "firstName")
	WebElement firstName;
		
	@FindBy(id = "lastName")
	WebElement lastName;
	
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userMobile")
	WebElement userMobile;
	
	@FindBy(className="custom-select")
	WebElement occupationButton;
	
	@FindBy(css="input[value='Male']")
	WebElement genderButton;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(css="input[formcontrolname='required']")
	WebElement checkBox;
	
	@FindBy(id = "login")
	WebElement signUpButton;
	
	By loginButtonBy = By.xpath("//button[text()='Login']");
	
	@FindBy(xpath="//button[text()='Login']")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement error;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public void registration(String firstname, String lastname, String email, String mobile,String occupation, String password, String confirmpassword)
	{
		resetButton.click();
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		userEmail.sendKeys(email);
		userMobile.sendKeys(mobile);
		Select dropdown = new Select(occupationButton);
		dropdown.selectByVisibleText(occupation);
		genderButton.click();
		userPassword.sendKeys(password);
		confirmPassword.sendKeys(confirmpassword);
		checkBox.click();
		signUpButton.click();
		waitForVisibility(loginButtonBy);
		loginButton.click();
		
		
	}
	
	public AddingProductToCart login(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		signUpButton.click();
		AddingProductToCart addingProductToCart = new AddingProductToCart(driver);
		return addingProductToCart;
	}
	
	public String errorMessage()
	{
		waitForerrorMessage(error);
		return error.getText();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

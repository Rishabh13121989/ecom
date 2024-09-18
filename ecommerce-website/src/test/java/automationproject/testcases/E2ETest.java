package automationproject.testcases;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class E2ETest {

	WebDriver driver;
	WebDriverWait w;
	String firstname="kfgwsffpka";
	String lastname="gaesdfopsh";
	String email="sivlhw5n@gmail.com";
	String password = "Qaz@1020";
	String productName= "ZARA COAT 3";

	@BeforeMethod
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		w = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void purchasing()
	{

		driver.findElement(By.cssSelector(".text-reset")).click();
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userMobile")).sendKeys("1234567890");
		Select dropdown = new Select(driver.findElement(By.className("custom-select")));
		dropdown.selectByVisibleText("Engineer");
		driver.findElement(By.cssSelector("input[value='Male']")).click();
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("confirmPassword")).sendKeys(password);
		driver.findElement(By.cssSelector("input[formcontrolname='required']")).click();
		driver.findElement(By.id("login")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Login']")));
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod=products.stream().filter(product ->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("zara coat 3"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cart h3"));
		Boolean productMatch= cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		System.out.println(productMatch);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow .btn")));
		driver.findElement(By.cssSelector(".totalRow .btn")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"canada").build().perform();
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class,'ta-item')])")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
		WebElement element= driver.findElement(By.cssSelector("h1"));
		Assert.assertEquals(element.getText(), "THANKYOU FOR THE ORDER.");
		driver.findElement(By.cssSelector("li:nth-of-type(5)")).click();

	}

}

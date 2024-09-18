package automationproject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automationproject.pages.UserRegistration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public UserRegistration userRegistration;



	public WebDriver setup() throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\automationproject\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName= prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}else if(browserName.equalsIgnoreCase("safari"))
		{
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=  (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+ testcaseName+".png");
		FileUtils.copyFile(source,file);
			return System.getProperty("user.dir")+"//reports//"+ testcaseName+".png";
		
	}

	@BeforeMethod(alwaysRun=true)
	public UserRegistration launchApp() throws IOException
	{

		driver = setup();
		userRegistration = new UserRegistration(driver);
		userRegistration.goTo();
		return userRegistration;

	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		if (driver != null) {
			driver.quit();
			driver=null;
		}}
	
	
}

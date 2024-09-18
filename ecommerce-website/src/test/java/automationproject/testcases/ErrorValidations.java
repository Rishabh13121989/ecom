package automationproject.testcases;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationproject.TestComponents.BaseTest;


public class ErrorValidations extends BaseTest {


	@Test(dataProvider="getData")
	public void loginErrorValidation(HashMap<String,String> input) throws InterruptedException, IOException
	{
		userRegistration.login(input.get("email"), input.get("password"));
		Assert.assertEquals("Incorrect email or password.", userRegistration.errorMessage());

	}

	@Test(dataProvider="getData")
	public void failedTestValidation(HashMap<String,String> input) throws InterruptedException, IOException
	{
		userRegistration.login(input.get("email"), input.get("password"));
		Assert.assertEquals("Incorrect email or passwor", userRegistration.errorMessage());

	}



	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("email","saravaakumar@gmail.com");
		map.put("password", "Qaz@1020");


		HashMap<String, String> map1 = new HashMap<>();
		map1.put("email", "john.ddoe@example.com");
		map1.put("password", "Pwd@1234");


		return new Object[][] { { map }, { map1 } };
	}


}

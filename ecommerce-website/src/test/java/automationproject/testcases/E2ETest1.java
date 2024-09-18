package automationproject.testcases;
import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import automationproject.TestComponents.BaseTest;
import automationproject.pages.AddingProductToCart;
import automationproject.pages.Checkout;
import automationproject.pages.OrderVerification;
import automationproject.pages.VerifyPlacedOrder;

public class E2ETest1 extends BaseTest{

	@Test(dataProvider="getData")
	public void purchasingProduct(HashMap<String, String> input) throws InterruptedException, IOException
	{
		//userRegistration.registration(input.get("firstname"), input.get("lastname"), input.get("email"), input.get("mobile"), input.get("occupation"), input.get("password"), input.get("confirmpassword"));
		AddingProductToCart addingProductToCart=userRegistration.login(input.get("email"), input.get("password"));
		addingProductToCart.addToCart(input.get("product"));
		Thread.sleep(2000);
		OrderVerification orderVerification=addingProductToCart.gotoCart();
		Checkout checkout = orderVerification.verifyOrder(input.get("product"));
		checkout.orderCompletion();
		String successMessageElement= checkout.confirmationMessage();
		Assert.assertEquals(successMessageElement,"THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods="purchasingProduct", dataProvider="getData")
	public void orderHistory(HashMap<String, String> input)
	{
		AddingProductToCart addingProductToCart=userRegistration.login(input.get("email"), input.get("password"));
		VerifyPlacedOrder verifyPlacedOrder=addingProductToCart.gotoOrderHistory();
		Boolean orderFound = verifyPlacedOrder.verifyThePlacedOrder(input.get("product")) != null;
		Assert.assertTrue(orderFound, "Order not found in the order history.");

	}


	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("firstname", "saravana");
		map.put("lastname", "kumar");
		map.put("email","saravanakumar@gmail.com");
		map.put("mobile", "1213456780");
		map.put("occupation", "Engineer");
		map.put("password", "Qaz@1020");
		map.put("confirmpassword", "Qaz@1020");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<>();
		map1.put("firstname", "john");
		map1.put("lastname", "doe");
		map1.put("email", "john.doe@example.com");
		map1.put("mobile", "9876543210");
		map1.put("occupation", "Doctor");
		map1.put("password", "Pwd@1234");
		map1.put("confirmpassword", "Pwd@1234");
		map1.put("product", "ADIDAS ORIGINAL");


		return new Object[][] { { map }, { map1 } };
	}

}

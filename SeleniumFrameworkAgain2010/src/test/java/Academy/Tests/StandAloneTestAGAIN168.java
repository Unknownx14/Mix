package Academy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.pageObjects.LandingPagePOAGAIN;
import Academy.pageObjects.MyCartPOAGAIN;
import Academy.pageObjects.PlaceOrderPOAGAIN;
import Academy.pageObjects.ProductCatalogPOAGAIN;
import Academy.pageObjects.ThankYouPOAGAIN;
import Academy.pageObjects.YourOrdersPOAGAIN;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestAGAIN168 extends BaseTest  {
	
	String wantedProduct ="ZARA COAT 3";
	
	/*
	public static String
    removeFirstandLast(String str)
    {
 
        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);
 
        // Return the modified string
        return str;
    }*/

	@Test(groups= {"JK"})
	public void submitOrderTestCaseJK() throws IOException, InterruptedException
	{
		
		//LandingPagePOAGAIN lpPO = launchApplication(); //This is not needed since we have @BeforeMethod in the BaseTest
		
		//LandingPage PO
		
		lpPO.loginApplication("unknownxjk@gmail.com", "kojikurac123");
		
		
		//Product Catalog PO
		ProductCatalogPOAGAIN pcPO = new ProductCatalogPOAGAIN(driver);
		Assert.assertEquals(pcPO.getToastTitleMethod(), "Login Successfully");
			
		//String wantedProduct ="ZARA COAT 3"; //It is now on the Class level
		
		pcPO.addProductToCart(wantedProduct);

		//My Cart PO
		MyCartPOAGAIN mcPO = new MyCartPOAGAIN(driver);
	
		Assert.assertTrue(mcPO.isPresentInCart(wantedProduct));
		System.out.println(mcPO.isPresentInCart(wantedProduct));
		
		System.out.println(mcPO.getItemNumber(wantedProduct));
		
		mcPO.clickCheckoutButton();
		
		
		//Place Order
		PlaceOrderPOAGAIN poPO = new PlaceOrderPOAGAIN(driver);
		
		String wantedCountryPartial = "yug";
		String wantedCountryFull = "yugoslavia";
		
		poPO.enterCountry(wantedCountryPartial);
		
		poPO.selectCountry(wantedCountryFull);
		
		poPO.clickPlaceOrderButton();
		
		//Thank you
		ThankYouPOAGAIN tyPO = new ThankYouPOAGAIN(driver);

		Assert.assertEquals(tyPO.getThankYouMessage(), "THANKYOU FOR THE ORDER.");
		
		//To extract order ID
		System.out.println(tyPO.getOrderId());
		
		//To get this order ID into readable format
		String orderIDremoveFirstAndLast = removeFirstandLastJK(tyPO.getOrderId());
		String orderIDReadableFormat = orderIDremoveFirstAndLast.trim();
		System.out.println(orderIDReadableFormat);
		
	/*
//Go to Your Orders page and verify that the product is listed
		driver.findElement(By.xpath("//button[contains(@routerlink, 'myorders')]")).click();
		Thread.sleep(1000);
		
		List<WebElement> allOrderIds = driver.findElements(By.xpath("//tbody"));
		List<WebElement> allOrderNames = driver.findElements(By.xpath("//tbody//td[2]"));
		
		boolean orderIdInMyOrders = allOrderIds.stream().anyMatch(myOrder -> myOrder.findElement(By.xpath("//tbody//th")).getText().equals(orderIDReadableFormat));
		System.out.println(orderIdInMyOrders);
		Assert.assertTrue(orderIdInMyOrders);
		*/
		//Go to Your Orders page and verify that the product is listed
		YourOrdersPOAGAIN yoPO =  new YourOrdersPOAGAIN(driver);
		
		yoPO.goToOrdersPage();
		System.out.println(yoPO.getOrderIDInMyOrders(orderIDReadableFormat));
		Assert.assertTrue(yoPO.getOrderIDInMyOrders(orderIDReadableFormat));
		
		
	}
	
	
	
	@Test(dataProvider="getData", groups= {"Purchase"}) //Using @DataProvider, submitOrderTest(String emailDataProv, String passwordDataProv, String wantedProductDataProv)
	public void submitOrderTestCase(HashMap<String, String> input) throws IOException, InterruptedException
	{
		
		//LandingPagePOAGAIN lpPO = launchApplication(); //This is not needed since we have @BeforeMethod in the BaseTest
		
		//LandingPage PO
		
		lpPO.loginApplication(input.get("emailDataProv"), input.get("passwordDataProv")); //"unknownxjk@gmail.com", "kojikurac123"
		
		
		//Product Catalog PO
		ProductCatalogPOAGAIN pcPO = new ProductCatalogPOAGAIN(driver);
		Assert.assertEquals(pcPO.getToastTitleMethod(), "Login Successfully");
			
		//String wantedProduct ="ZARA COAT 3"; //It is now on the Class level
		
		pcPO.addProductToCart(input.get("wantedProductDataProv"));

		//My Cart PO
		MyCartPOAGAIN mcPO = new MyCartPOAGAIN(driver);
	
		Assert.assertTrue(mcPO.isPresentInCart(input.get("wantedProductDataProv")));
		System.out.println(mcPO.isPresentInCart(input.get("wantedProductDataProv")));
		
		System.out.println(mcPO.getItemNumber(input.get("wantedProductDataProv")));
		
		mcPO.clickCheckoutButton();
		
		
		//Place Order
		PlaceOrderPOAGAIN poPO = new PlaceOrderPOAGAIN(driver);
		
		String wantedCountryPartial = "yug";
		String wantedCountryFull = "yugoslavia";
		
		poPO.enterCountry(wantedCountryPartial);
		
		poPO.selectCountry(wantedCountryFull);
		
		poPO.clickPlaceOrderButton();
		
		//Thank you
		ThankYouPOAGAIN tyPO = new ThankYouPOAGAIN(driver);

		Assert.assertEquals(tyPO.getThankYouMessage(), "THANKYOU FOR THE ORDER.");
		
		
	}
	
	
	@Test(dependsOnMethods = {"submitOrderTestCase"})
	public void OrderHistoryTestCase() throws InterruptedException
	{
		
		//LandingPage PO
		lpPO.loginApplication("unknownxjk@gmail.com", "kojikurac123");
		
		//Go to Your Orders page and verify that the product is listed
		YourOrdersPOAGAIN yoPO = new YourOrdersPOAGAIN(driver);
		yoPO.goToOrdersPage();
		
		Assert.assertTrue(yoPO.getOrderNameInMyOrders(wantedProduct));
		
	}
	
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
	/*	HashMap<String, String> map = new HashMap<String, String>();
		map.put("emailDataProv","unknownxjk@gmail.com");
		map.put("passwordDataProv","kojikurac123");
		map.put("wantedProductDataProv","ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("emailDataProv","shetty@gmail.com");
		map1.put("passwordDataProv","Iamking@000");
		map1.put("wantedProductDataProv","ADIDAS ORIGINAL");
		*/
		
		 //return new Object[][] {{"unknownxjk@gmail.com", "kojikurac123", "ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
		List<HashMap<String, String>> data =  getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\Academy\\data\\PurchaseOrder.json");
		//return new Object[][] {{map}, {map1}};
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	

}

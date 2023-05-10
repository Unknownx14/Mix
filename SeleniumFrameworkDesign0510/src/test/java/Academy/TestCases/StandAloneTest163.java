package Academy.TestCases;

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
import Academy.pageObject.LandingPagePO;
import Academy.pageObject.MyCartPO;
import Academy.pageObject.OrdersPagePO;
import Academy.pageObject.PlaceOrderPO;
import Academy.pageObject.ProductCataloguePO;
import Academy.pageObject.ThankYouPO;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest163 extends BaseTest {

	
@Test(dataProvider="getData", groups= {"Purchase"})
public void submitOrderE2E(HashMap<String, String> input) throws IOException //String emailLogin, String passwordLogin, String productName
		{
		
		//LandingPagePO lpPO =launchApplication();//This is a method from BaseTest.java, and it is not needed if we have @BeforeMethod annotation
		 
		
		//Login to the application
		lpPO.LoginApplicationAction(input.get("emailLogin"), input.get("passwordLogin")); //("unknownxjk@gmail.com", "kojikurac123")
		
		//Get all products from ProductCatalogue page
		ProductCataloguePO pcPO = new ProductCataloguePO(driver);
		List<WebElement> allProducts = pcPO.getProductList();
		
		
		String wantedProductName = "ZARA COAT 3";
		
		//Add a product to a cart
		//pcPO.addProductToCart(wantedProductName); //This is from the original coding
			
		//This is just for me
		Assert.assertEquals(pcPO.addProductToCart(input.get("productName")), "Product Added To Cart"); //wantedProductName
		
		pcPO.clickCartButtonAbstract(); //This is by Rahul in the Abstract, because it is common for all pages
		//pcPO.clickCartButton(); // I did it in the ProductCataloguePO
				
		//On this page, find the selected product under the Cart
		
		MyCartPO mcPO = new MyCartPO(driver);
		
		mcPO.getProductInCartByName(input.get("productName")); //wantedProductName
		mcPO.verifyH3text(input.get("productName"));//wantedProductName
		Assert.assertTrue(mcPO.verifyH3text(input.get("productName"))); //wantedProductName
		mcPO.clickCheckoutButton();
			
		//We need the Actions class for this dropdown
		PlaceOrderPO poPO = new PlaceOrderPO(driver);
		
		String partialTypeInCountry = "indi"; //yug
		String yourCountry = "india"; //yugoslavia
		poPO.chooseCountry(partialTypeInCountry);
		poPO.selectYourCountry(yourCountry);
		poPO.clickPlaceOrderButton();
		
		//Validate the Thankyou message
		ThankYouPO tyPO = new ThankYouPO(driver);
		//tyPO.validateThankYouMessage(); //Since there is Assert below, I do not need this line
		Assert.assertEquals(tyPO.validateThankYouMessage(), "THANKYOU FOR THE ORDER.");

				
		//driver.close(); //This is now in @AfterMethod
				
				

	}

//To verify ZARA COAT 3 is displaying in the Orders page

@Test(dependsOnMethods = {"submitOrderE2E"}) //This means "submitOrderE2E" TC will be the first to run, then "OrderHistoryTest" will be the next
public void OrderHistoryTest() throws IOException
{
	
	String wantedProductName = "ZARA COAT 3";
	
	//Login to the application
			lpPO.LoginApplicationAction("unknownxjk@gmail.com", "kojikurac123");
			
	OrdersPagePO opPO = new OrdersPagePO(driver);
	
	opPO.verifyOrderDisplay();
	//opPO.matchYourOrder(wantedProductName);
	System.out.println(opPO.matchYourOrder(wantedProductName));
	Assert.assertTrue(opPO.matchYourOrder(wantedProductName));
}


@DataProvider
public Object[][] getData() throws IOException
{
	/*HashMap<String, String> hmap = new HashMap<String, String>();
	hmap.put("emailLogin", "unknownxjk@gmail.com");
	hmap.put("passwordLogin", "kojikurac123");
	hmap.put("productName", "ZARA COAT 3");
	
	
	HashMap<String, String> hmap1 = new HashMap<String, String>();
	hmap1.put("emailLogin", "shetty@gmail.com");
	hmap1.put("passwordLogin", "Iamking@000");
	hmap1.put("productName", "ADIDAS ORIGINAL");*/
	
	List<HashMap<String, String>> data =getJsonDataToHashmap(System.getProperty("user.dir") + "\\src\\test\\java\\Academy\\Data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0)}, {data.get(1)}}; //Now we only use this line and the one above
	//return new Object[][] {{"unknownxjk@gmail.com","kojikurac123","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}






}

package Academy.TestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Academy.pageObject.LandingPagePO;
import Academy.pageObject.MyCartPO;
import Academy.pageObject.PlaceOrderPO;
import Academy.pageObject.ProductCataloguePO;
import Academy.pageObject.ThankYouPO;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest154 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		LandingPagePO lpPO = new LandingPagePO(driver); //This is to instantiate driver in our PO class
		
		//Get this url
		lpPO.goTo();
		//Login to the application
		lpPO.LoginApplicationAction("unknownxjk@gmail.com", "kojikurac123");
		
		//Get all products from ProductCatalogue page
		ProductCataloguePO pcPO = new ProductCataloguePO(driver);
		List<WebElement> allProducts = pcPO.getProductList();
		
		
		String wantedProductName = "ZARA COAT 3";
		
		//Add a product to a cart
		//pcPO.addProductToCart(wantedProductName); //This is from the original coding
			
		//This is just for me
		Assert.assertEquals(pcPO.addProductToCart(wantedProductName), "Product Added To Cart");
		
		pcPO.clickCartButtonAbstract(); //This is by Rahul in the Abstract, because it is common for all pages
		//pcPO.clickCartButton(); // I did it in the ProductCataloguePO
				
		//On this page, find the selected product under the Cart
		
		MyCartPO mcPO = new MyCartPO(driver);
		
		mcPO.getProductInCartByName(wantedProductName);
		mcPO.verifyH3text(wantedProductName);
		Assert.assertTrue(mcPO.verifyH3text(wantedProductName));//???
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

				
		//driver.close();
				
				

	}

}

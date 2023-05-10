package Academy.TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.pageObject.MyCartPO;
import Academy.pageObject.PlaceOrderPO;
import Academy.pageObject.ProductCataloguePO;
import Academy.pageObject.ThankYouPO;

public class StandAloneTest163ErrorValidations extends BaseTest {


	
@Test(groups= {"ErrorValidationGroup"}, retryAnalyzer=Academy.TestComponents.Retry.class)
public void wrongEmailLogin() throws IOException
		{
		
		//LandingPagePO lpPO =launchApplication();//This is a method from BaseTest.java, and it is not needed if we have @BeforeMethod annotation
		 
		
		//Login to the application
		lpPO.LoginApplicationAction("unknownxwrong@gmail.com", "kojikurac123");
		System.out.println(lpPO.getErrorMessage());
		Assert.assertEquals(lpPO.getErrorMessage(), "Incorrect email or password JK.");
	
	}



@Test
public void productNotExisting() throws IOException
		{
		
		//LandingPagePO lpPO =launchApplication();//This is a method from BaseTest.java, and it is not needed if we have @BeforeMethod annotation
		 
		
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
		Assert.assertFalse(mcPO.verifyH3text("ZARA COAT 333"));//???
		
		
	}


@Test(groups= {"ErrorValidationGroup"})
public void wrongEmailLoginTestRetryAllFailedTestcases() throws IOException
		{
		
		//LandingPagePO lpPO =launchApplication();//This is a method from BaseTest.java, and it is not needed if we have @BeforeMethod annotation
		 
		
		//Login to the application
		lpPO.LoginApplicationAction("unknownxwrong01@gmail.com", "kojikurac123");
		System.out.println(lpPO.getErrorMessage());
		Assert.assertEquals(lpPO.getErrorMessage(), "Incorrect email or password JK01.");
	
	}

}

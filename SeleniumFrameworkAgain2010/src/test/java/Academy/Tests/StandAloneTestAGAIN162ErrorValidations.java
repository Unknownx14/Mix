package Academy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.pageObjects.MyCartPOAGAIN;
import Academy.pageObjects.PlaceOrderPOAGAIN;
import Academy.pageObjects.ProductCatalogPOAGAIN;
import Academy.pageObjects.ThankYouPOAGAIN;

public class StandAloneTestAGAIN162ErrorValidations extends BaseTest {
	
	
	public static String
    removeFirstandLast(String str)
    {
 
        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);
 
        // Return the modified string
        return str;
    }

	@Test(groups = {"ErrorHandling"}/*, retryAnalyzer=Academy.TestComponents.Retry.class*/)
	public void loginErrorTestCase() throws IOException, InterruptedException
	{
		
		//LandingPagePOAGAIN lpPO = launchApplication(); //This is not needed since we have @BeforeMethod in the BaseTest
		
		//LandingPage PO
		
		lpPO.loginApplication("abcdeeee@gmail.com", "kojikurac123");
		
		
		System.out.println(lpPO.incorrectEmailPassword());
		Assert.assertEquals(lpPO.incorrectEmailPassword(), "Incorrect email or password.JK");
		
	}
	
	
	@Test
	public void productErrorTestCase() throws IOException, InterruptedException
	{
		
		//LandingPagePOAGAIN lpPO = launchApplication(); //This is not needed since we have @BeforeMethod in the BaseTest
		
		//LandingPage PO
		
		lpPO.loginApplication("unknownxjk@gmail.com", "kojikurac123");
		
		
		//Product Catalog PO
		ProductCatalogPOAGAIN pcPO = new ProductCatalogPOAGAIN(driver);
		Assert.assertEquals(pcPO.getToastTitleMethod(), "Login Successfully");
			
		String wantedProduct ="ZARA COAT 3";
		
		pcPO.addProductToCart(wantedProduct);

		//My Cart PO
		MyCartPOAGAIN mcPO = new MyCartPOAGAIN(driver);
	
		Assert.assertFalse(mcPO.isPresentInCart("ZARA COAT 33"));
		System.out.println(mcPO.isPresentInCart(wantedProduct));
		
		
	}
	

}

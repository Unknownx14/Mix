package Academy.Tests;

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

import Academy.pageObjects.LandingPagePOAGAIN;
import Academy.pageObjects.MyCartPOAGAIN;
import Academy.pageObjects.PlaceOrderPOAGAIN;
import Academy.pageObjects.ProductCatalogPOAGAIN;
import Academy.pageObjects.ThankYouPOAGAIN;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestAGAIN153 {
	
	
	public static String
    removeFirstandLast(String str)
    {
 
        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);
 
        // Return the modified string
        return str;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("https://www.rahulshettyacademy.com/client");
		
		//LandingPage PO
		LandingPagePOAGAIN lpPO = new LandingPagePOAGAIN(driver);
		lpPO.goTo();
		lpPO.loginApplication("unknownxjk@gmail.com", "kojikurac123");
		
		
		//Product Catalog PO
		ProductCatalogPOAGAIN pcPO = new ProductCatalogPOAGAIN(driver);
		Assert.assertEquals(pcPO.getToastTitleMethod(), "Login Successfully");
			
		String wantedProduct ="ZARA COAT 3";
		
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
		String orderIDremoveFirstAndLast = removeFirstandLast(tyPO.getOrderId());
		String orderIDReadableFormat = orderIDremoveFirstAndLast.trim();
		System.out.println(orderIDReadableFormat);
		
		
//Go to Your Orders page and verify that the product is listed
		driver.findElement(By.xpath("//button[contains(@routerlink, 'myorders')]")).click();
		
		List<WebElement> allOrderIds = driver.findElements(By.xpath("//tbody"));
		List<WebElement> allOrderNames = driver.findElements(By.xpath("//tbody//td[2]"));
		
		boolean orderIdInMyOrders = allOrderIds.stream().anyMatch(myOrder -> myOrder.findElement(By.xpath("//tbody//th")).getText().equals(orderIDReadableFormat));
		
		Assert.assertTrue(orderIdInMyOrders);
		
		
	}

}

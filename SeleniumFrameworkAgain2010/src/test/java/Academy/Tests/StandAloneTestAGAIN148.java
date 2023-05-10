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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestAGAIN148 {
	
	
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
		driver.get("https://www.rahulshettyacademy.com/client");
		
		
		driver.findElement(By.id("userEmail")).sendKeys("unknownxjk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("kojikurac123");
		driver.findElement(By.id("login")).click();
		
		
		//Explicit wait - define the object of the class
				WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
				w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-title")));
		
		String loginMessage = driver.findElement(By.cssSelector(".toast-title")).getText();
		System.out.println(loginMessage);
		Assert.assertEquals(loginMessage, "Login Successfully");
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//List of all elements
		List<WebElement> allProducts = driver.findElements(By.cssSelector(".mb-3"));
		
		String wantedProduct ="ZARA COAT 3";
		
		WebElement singleProduct = allProducts.stream().filter(oneProduct -> oneProduct.findElement(By.cssSelector("b")).getText().equals(wantedProduct))
		.findFirst().orElse(null);
		
		singleProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //This way is more quick
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//In Cart
		List<WebElement> allProductsInCart = driver.findElements(By.cssSelector(".cartWrap"));
		WebElement singleProductInCart = allProductsInCart.stream().filter(oneProductInCart -> oneProductInCart.findElement(By.cssSelector("h3")).getText().equals(wantedProduct))
		.findFirst().orElse(null);
		
		Boolean presentInCart = allProductsInCart.stream().anyMatch(oneProductInCart -> oneProductInCart.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(wantedProduct));
		Assert.assertTrue(presentInCart);
		System.out.println(presentInCart);
		
		/*WebElement itemNumber = allProductsInCart.stream().filter(oneItemNumber -> oneItemNumber.findElement(By.cssSelector("p")).getText().contains("#"))
		.findFirst().orElse(null);*/
		String itemNumber = singleProductInCart.findElement(By.cssSelector(".itemNumber")).getText();
		System.out.println(itemNumber);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		
		//Place Order
		//We need the Actions class for this dropdown
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "yug").build().perform();
		
		//driver.findElement(By.cssSelector(".form-group input")).sendKeys("yug"); //This is my way
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> allCountries = driver.findElements(By.cssSelector(".ta-item"));
		WebElement singleCountry = allCountries.stream().filter(oneCountry -> oneCountry.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("yugoslavia"))
		.findFirst().orElse(null);
		singleCountry.click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//Thank you
		String thakYouMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(thakYouMessage, "THANKYOU FOR THE ORDER.");
		
		//To extract order ID
		String orderID = driver.findElement(By.cssSelector("label[class*='inserted']")).getText();
		System.out.println(orderID);
		
		//To get this order ID into readable format
		String orderIDremoveFirstAndLast = removeFirstandLast(orderID);
		String orderIDReadableFormat = orderIDremoveFirstAndLast.trim();
		System.out.println(orderIDReadableFormat);
		
		

	}

}

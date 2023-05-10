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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest148 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

/*		System.setProperty("webdriver.chrome.driver", "C:\\JK\\chromedriver_win32\\chromedriver.exe");
		//webdriver.chrome.driver is a key value, a property
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.rahulshettyacademy.com/client");
		*/
		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.rahulshettyacademy.com/client");
		
		//Login to the application
		driver.findElement(By.id("userEmail")).sendKeys("unknownxjk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("kojikurac123");
		driver.findElement(By.id("login")).click();
		
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//List of all products
		List<WebElement> allProducts = driver.findElements(By.cssSelector(".mb-3"));
		
		String wantedProductName = "ZARA COAT 3";

		//Stream instead of FOR and IF loops
		//allProducts.stream().filter(singleProduct-> singleProduct.getText().equals("ZARA COAT 3"));
		WebElement prod = allProducts.stream().filter(singleProduct->singleProduct.findElement(By.cssSelector("b")).getText().equals(wantedProductName))
		.findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
				w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				//w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
				w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				
				
				
				String toastText = driver.findElement(By.cssSelector(".toast-message")).getText();
				System.out.println(toastText);
				Assert.assertEquals(toastText, "Product Added To Cart");
				
				driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
				
		//On this page, find the selected product under the Cart
				List<WebElement> allInCart = driver.findElements(By.cssSelector(".cartWrap"));
				WebElement prodInCart = allInCart.stream().filter(singleInCart->singleInCart.findElement(By.cssSelector("h3")).getText().equals(wantedProductName))
				.findFirst().orElse(null);
				
				//This is for matching if an item is displayed - anyMatch instead of filter
				Boolean matchJK = allInCart.stream().anyMatch(singleInCart->singleInCart.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(wantedProductName));
				System.out.println(matchJK);
				Assert.assertTrue(matchJK);
				
				//Click on a button
				//prodInCart.findElement(By.cssSelector(".btn-primary")).click();
				
				//Click on the Checkout button
				driver.findElement(By.xpath("//button[text()='Checkout']")).click();
				//driver.findElement(By.cssSelector(".totalRow button")).click();
				
				
				//We need the Actions class for this dropdown
				Actions a = new Actions(driver);
				a.sendKeys(driver.findElement(By.xpath("//*[@placeholder='Select Country']")), "indi").build().perform(); //css [placeholder='Select Country']
		
				w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
				
				List<WebElement> listedCountries = driver.findElements(By.cssSelector(".ta-item"));
				WebElement myCountry = listedCountries.stream().filter(wantedCountry->wantedCountry.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("india"))
				.findFirst().orElse(null);
				
				myCountry.click();
				
				//Place the order
				driver.findElement(By.cssSelector(".action__submit")).click();
				
				//Validate the Thankyou message
				String thankyouMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
				System.out.println(thankyouMessage);
				Assert.assertEquals(thankyouMessage, "THANKYOU FOR THE ORDER.");
				
				driver.close();
				
				
				
	}

}

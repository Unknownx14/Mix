package Academy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Opodo01Test2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.opodo.com/");
		
		
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".didomi-popup-view")));
		driver.findElement(By.cssSelector("#didomi-notice-agree-button")).click();
		
		
		//Select Departure
		driver.findElement(By.cssSelector(".odf-input-inline-first")).click();
		driver.findElement(By.cssSelector(".odf-input-inline-first")).sendKeys("Athens");
		
		String airportCodeDeparture = "ATH";

		List<WebElement> allDepartures = driver.findElements(By.cssSelector(".odf-dropdown-col.lg.odf-text-nowrap"));
		WebElement wantedAirport =  allDepartures.stream().filter(oneAirport->oneAirport.findElement(By.tagName("span")).getText().equals(airportCodeDeparture))
		.findFirst().orElse(null);
		wantedAirport.findElement(By.cssSelector(".odf-space-outer-left-xs.odf-space-outer-right-xs")).click();
		
		//Select Arrival
		driver.findElement(By.cssSelector("input[placeholder='Where to?']")).sendKeys("Sydney");
		Thread.sleep(1000);
		
		String airportCodeArrival = "SYD";

		List<WebElement> allArrivals = driver.findElements(By.cssSelector(".odf-dropdown-col.lg.odf-text-nowrap"));
		
		WebElement wantedAirportArrival =  allArrivals.stream().filter(oneAirport01->oneAirport01.findElement(By.tagName("span")).getText().equals(airportCodeArrival))
		.findFirst().orElse(null);
		wantedAirportArrival.findElement(By.cssSelector(".odf-space-outer-left-xs.odf-space-outer-right-xs")).click();
		
		
		//Select Departure date
		String wantedDepartureDate = "24";
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".odf-box-content.odf-box-content-lg")));
		
		//*****
	/*	String abc =driver.findElement(By.xpath("//*[@id=\"react-app\"]/div/div/div[1]/div/div[2]/div[1]/div[2]/div/div/div[3]/div/div[2]/div/div[1]/div/div[2]/div/div[2]/div[5]/div[5]"))
				.getText();
		System.out.println("Ajde"+ " " +abc);
		Assert.assertEquals(wantedDepartureDate, abc);*/
		
		//Limit the scope of the driver
		WebElement firstCalendar = driver.findElement(By.xpath("(//div[@class='odf-calendar-month']) [1]"));
		
		List<WebElement> allDepartureRows = firstCalendar.findElements(By.cssSelector(".odf-calendar-row"));
		System.out.println(allDepartureRows.size());
		
		List<WebElement> allDepartureDates = firstCalendar.findElements(By.cssSelector(".odf-calendar-day"));
		System.out.println(allDepartureDates.size());
		
		WebElement wantedDate = allDepartureDates.stream().filter(oneDate->oneDate.getText().toString().equals(wantedDepartureDate))
		.findFirst().orElse(null);
		
		Thread.sleep(2000);
		System.out.println(wantedDate.getText());
		wantedDate.click();
		
		
		//Select Return date
				String wantedReturnDate = "29";
				
				//Limit the scope of the driver
				WebElement secondCalendar = driver.findElement(By.xpath("(//div[@class='odf-calendar-month']) [2]"));
				
				List<WebElement> allReturnRows = secondCalendar.findElements(By.cssSelector(".odf-calendar-row"));
				System.out.println(allReturnRows.size());
				
				List<WebElement> allReturnDates = secondCalendar.findElements(By.cssSelector(".odf-calendar-day"));
				System.out.println(allReturnDates.size());
				
				WebElement wantedDateReturn = allReturnDates.stream().filter(oneDate->oneDate.getText().toString().equals(wantedReturnDate))
				.findFirst().orElse(null);
				
				Thread.sleep(2000);
				System.out.println(wantedDateReturn.getText());
				wantedDateReturn.click();	
				
				
				w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Done']")));
				driver.findElement(By.xpath("//button[text()='Done']")).click();
				
				driver.findElement(By.xpath("//button[text()='Search Flights']")).click();
		
		
	}

}

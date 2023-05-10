package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	
	public WebDriver initializeDriver() throws IOException
	{
		 prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\joko2909\\SeleniumTraining\\Toma\\src\\main\\java\\resources\\data.properties");
		
		prop.load(fis);
		String wantedBrowser = prop.getProperty("browser");
		
		if(wantedBrowser.equals("Chrome"))
		{
			/*System.setProperty("webdriver.chrome.driver", "C:\\JK\\chromedriver_win32\\chromedriver.exe");
			//webdriver.chrome.driver is a key value, a property
			 driver = new ChromeDriver();*/
			WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
			 driver = new ChromeDriver();
		}
		else if(wantedBrowser.equals("FireFox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\JK\\geckodriver-v0.31.0-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}
		else if(wantedBrowser.equals("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\JK\\edgedriver_win64\\msedgedriver.exe");
			 driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	
	
	public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" +testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile)); //This is where this printscreen will be stored in our system
		return destinationFile;
	}
	
}

package Academy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Academy.pageObject.LandingPagePO;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPagePO lpPO;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		//Properties class
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Academy\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		String wantedBrowser = System.getProperty("browser") !=null ? System.getProperty("browser") :prop.getProperty("browser") ; //This is for 
		//using maven commnads in the Command prompt
		
		//prop.getProperty("browser");
		
		if(wantedBrowser.contains("chrome")) //It was equalsIgnoreCase("chrome"), now it is contains
		{
			ChromeOptions options = new ChromeOptions();//We need this for the Headless mode
			
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		if(wantedBrowser.contains("headless"))
		{
		options.addArguments("headless");
		}
		 driver = new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));//This is for headless mode to be like maximize, in full screen
		
		}
		else if (wantedBrowser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\JK\\geckodriver-v0.31.0-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
			}
		else if (wantedBrowser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\JK\\edgedriver_win64\\msedgedriver.exe");
			 driver = new EdgeDriver();
			}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPagePO launchApplication() throws IOException
	{
		driver = initializeDriver();
		 lpPO = new LandingPagePO(driver); //This is to instantiate driver in our PO class
		//Get this url
		lpPO.goTo();
		return lpPO;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	//This is from Jackson Databind lession 169
	public List<HashMap<String, String>> getJsonDataToHashmap(String filePath) throws IOException
	{
		//Read a json file to String
		String jsonContent = FileUtils.readFileToString(new File(filePath)
				, StandardCharsets.UTF_8);
		
		//Convert this String to a HashMap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
		
	}
	
	
	//This is a method for taking Screenshots
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	
	
}

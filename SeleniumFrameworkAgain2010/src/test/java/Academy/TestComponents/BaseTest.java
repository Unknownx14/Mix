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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Academy.pageObjects.LandingPagePOAGAIN;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPagePOAGAIN lpPO;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		//Properties class is for using GlobalData.properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Academy\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); //This line of code
		//we now use to select on which browser TCs will run from the Command prompt
		
		//String browserName = prop.getProperty("browser"); //This is now not needed because of the line above
		
		
		if(browserName.contains("chrome")) //equalsIgnoreCase is for the exact match
		{
			//This line below is for running a TC in the headless mode
			ChromeOptions options = new ChromeOptions();
			
		WebDriverManager.chromedriver().setup(); // WebDriver manager for invoking the chromedriver that is not on our local machine
		if(browserName.contains("headless"))
		{
		options.addArguments("headless"); //the headless mode
		}
		 driver = new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440, 900)); //For the headless mode to run in a full-screen window
		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\JK\\geckodriver-v0.31.0-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
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
	public LandingPagePOAGAIN launchApplication() throws IOException
	{
		 driver = initializeDriver();
		  lpPO = new LandingPagePOAGAIN(driver);
		 lpPO.goTo();
		 return lpPO;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		//Read json file as a String
		String jsonContent = FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8); //new File(System.getProperty("user.dir") + "\\src\\test\\java\\Academy\\data\\PurchaseOrder.json" is now sent
										//from our TC so we do not have a hardcoded json file
		
		//Convert this String to a HashMap with the Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		//As a result we get these HashMaps {map}, {map1} as a List
		return data;
		
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		//This method returns a path where a screenshot is stored
	}
	
	
	public String
    removeFirstandLastJK(String str)
    {
 
        // Removing first and last character
        // of a string using substring() method
        str = str.substring(1, str.length() - 1);
 
        // Return the modified string
        return str;
    }
	
}

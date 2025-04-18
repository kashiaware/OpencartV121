package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver; 
	public Logger logger;
	public Properties p;
	
	@Parameters({"os","browser"})
	@BeforeClass(groups={"Sanity","Regression","Master","datadriven"})
	public void setUp(String os,String br) throws IOException
	{

		//loading config.properties file

		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);

		logger=LogManager.getLogger(this.getClass());   //log4j2

		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();

			//OS

			if(os.equalsIgnoreCase("window"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);	
			}
			else
			{
				System.out.println("No matching os");
				return;
			}

			//Browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("Microsoftedge");break;
			default:System.out.println("No matching browser");
			return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}


		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
				{
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver();break;
			case "edge"   : driver=new EdgeDriver();break;
			case "firefox": driver=new FirefoxDriver();break;
			default:System.out.println("Invalid browser");return;//return exit from entire method and rest of test not executed
			}
				} 
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL")); //reading url from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups={"Sanity","Regression","Master","datadriven"})
	public void tearDown()
	{
		if(driver != null)
		driver.quit();
	}

	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	public String randomeNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomeAlphaNumber()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return( generatedstring+"@"+generatednumber);
	}
}

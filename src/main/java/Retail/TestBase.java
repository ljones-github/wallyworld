package Retail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class TestBase {
	
	static WebDriver driver;
	static String myBrowser;
	
	
	public static WebDriver initializeDriver()
	{
		myBrowser = System.getProperty("browser");
		
		if(myBrowser == null)
		{
			myBrowser = "firefox";
		}
		
		if(myBrowser.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\drivers\\chromedriver.exe");
			
			if(myBrowser.contains("headless"))
			{
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.acceptInsecureCerts();
				ChromeOptions co = new ChromeOptions();
				co.addArguments("headless");
				co.merge(dc);
				driver = new ChromeDriver(co);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			}
			
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.acceptInsecureCerts();
			ChromeOptions co = new ChromeOptions();
			co.setBinary(System.getProperty("user.dir") + "\\Resources\\drivers\\chromedriver.exe");
			co.merge(dc);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		}
		else if(myBrowser.contains("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Resources\\drivers\\geckodriver.exe");
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.acceptInsecureCerts();
			FirefoxOptions ffo = new FirefoxOptions();
			ffo.merge(dc);
			driver = new FirefoxDriver(ffo);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		}
		else
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\Resources\\drivers\\\\IEDriverServer.exe");
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.acceptInsecureCerts();
			InternetExplorerOptions ieo = new InternetExplorerOptions();
			ieo.merge(dc);
			driver = new InternetExplorerDriver(ieo);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		}
		
		return driver;
	}
	
	public void takeScreenshot(String methodName, WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\Resources\\Screenshots\\" + methodName + ".png";
		FileHandler.copy(src, new File(dest));
	}

	public ArrayList<String> dropDownOptions(WebElement dropdown)
	{
		Select s = new Select(dropdown);
		List<WebElement> myOptions = s.getOptions();
		ArrayList<String>textOptions = new ArrayList<String>();
		
		for(WebElement e : myOptions)
		{
			textOptions.add(e.getText());
		}
		return textOptions;
	}
}

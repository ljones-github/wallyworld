package Retail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

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
	
	public void takeScreenshotAdditional(String methodName, String additional, WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\Resources\\Screenshots\\" + methodName + "_" + additional + ".png";
		FileHandler.copy(src, new File(dest));
	}
	
	public String TakeAFULLScreenshot(String methodName, WebDriver driver) throws IOException
	{
		String dest = System.getProperty("user.dir") + "\\Resources\\Screenshots\\" + methodName + ".png";
		
		 Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);             
		 try
		 {
		 	Path fileToDelete = Paths.get(dest);
			
			if(fileToDelete.toFile().exists())
			{
				Files.delete(fileToDelete);
			}
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println("Error desc: " + e);
		 }
		 
		  try 
		  {                 
			  ImageIO.write(screenshot.getImage(),"PNG",new File(dest));             
		  } 
		  catch (IOException e) 
		  { 
			  System.out.println("Error desc: " + e);
		  }
		return dest;
	}

	
	public String TakeAFULLScreenshotAdditional(String methodName, String additional, WebDriver driver) throws IOException
	{
		String dest = System.getProperty("user.dir") + "\\Resources\\Screenshots\\" + methodName + "_" + additional + ".png";
		
		 Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);             
		 try
		 {
		 	Path fileToDelete = Paths.get(dest);
			
			if(fileToDelete.toFile().exists())
			{
				Files.delete(fileToDelete);
			}
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println("Error desc: " + e);
		 }
		 
		  try 
		  {                 
			  ImageIO.write(screenshot.getImage(),"PNG",new File(dest));             
		  } 
		  catch (IOException e) 
		  { 
			  System.out.println("Error desc: " + e);
		  }
		return dest;
	}

	public List<String> dropDownOptions(WebElement dropdown)
	{
		Select s = new Select(dropdown);
		List<WebElement> myOptions = s.getOptions();
		List<String>textOptions = new ArrayList<String>();
		
		//Using streams
		textOptions = myOptions.stream().map(option -> option.getText()).collect(Collectors.toList());
		
		return textOptions;
	}
	
	public void scrollElementIntoView(WebElement ele, WebDriver driver)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}
	
	public void scrollByAmount(WebDriver driver, int pxAmount)
	{ 
		String pxStringAmount = String.valueOf(pxAmount) + ")";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String myScript = "window.scrollBy(0, " + pxStringAmount;
		js.executeScript(myScript);
	}
	
	public void scrollToEndOfPage(WebDriver driver)
	{ 
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollToEndOfDynamicPage(WebDriver driver)
	{
		try {
		    long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

		    while (true) {
		        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		        Thread.sleep(2000);

		        long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
		        if (newHeight == lastHeight) {
		            break;
		        }
		        lastHeight = newHeight;
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	public void openLinkInNewtab(WebElement webEleLink, WebDriver driver)
	{
		Actions s = new Actions(driver);
		s.keyDown(Keys.CONTROL).click(webEleLink).build().perform();
	}
	
}

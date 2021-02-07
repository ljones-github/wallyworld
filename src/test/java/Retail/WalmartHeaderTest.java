package Retail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import jdk.internal.jline.internal.Log;

public class WalmartHeaderTest extends TestBase{

	String methodName;
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		this.driver = TestBase.initializeDriver();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		driver.get(myProps.getProperty("url"));
	}
	
	@Test
	public void wallyTrackOrder() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.trackOrders();
	}

	@Test
	public void wallyReorderItems() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.reorderItems();
	}
	
	@Test
	public void wallyLists() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.lists();
	}
	
	@Test
	public void wallyPlus() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.walmartPlus();
		WalmartHeaderTest wht = new WalmartHeaderTest();
		wht.TakeAFULLScreenshot(methodName, driver);
	}
	
	@Test
	public void wallyLocation() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.walmartLocation();
	}
	
	@Test
	public void wallyLocalStore() throws IOException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.localStore();
		WalmartHeaderTest wht = new WalmartHeaderTest();
		wht.TakeAFULLScreenshot(methodName, driver);
	}
	
	@Test
	public void wallyAllDepartments() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.seeAllDepartments();
		WalmartHeaderTest wht = new WalmartHeaderTest();
		wht.TakeAFULLScreenshot(methodName, driver);
	}
	
	@Test
	public void wallyDealsMenu() throws IOException, InterruptedException 
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.dealsStaticMenuLinks();
		WalmartHeaderTest wht = new WalmartHeaderTest();
		wht.takeScreenshot(methodName, driver);	
	}
	
	@Test
	public void wallyThirdParty() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		WalmartHomeHeader wally = new WalmartHomeHeader(driver);
		wally.thirdPartyLinks();
	}
	
	@AfterMethod
	public void tearDown()
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		this.driver.quit();
	}
}

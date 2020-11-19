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
		FileInputStream fis = new FileInputStream("C:\\Users\\ljone\\Walmart\\Resources\\data.properties");
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
	
	@AfterMethod
	public void tearDown()
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		this.driver.quit();
	}
}

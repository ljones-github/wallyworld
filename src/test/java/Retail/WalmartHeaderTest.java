package Retail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class WalmartHeaderTest extends TestBase{

	String MethodName;
	WebDriver driver;
	
	@BeforeTest
	public void setUp() throws IOException
	{
		this.driver = TestBase.initializeDriver();
		FileInputStream fis = new FileInputStream("C:\\Users\\ljone\\Walmart\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		driver.get(myProps.getProperty("url"));
	}
}

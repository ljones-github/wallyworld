package Retail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class WalmartHomeHeader{
	
	WebDriver driver;
	String methodName;
	
	private static Logger log = LogManager.getLogger(WalmartHomeHeader.class.getName());
	
	@FindBy(css="#header-Header-sparkButton")
	private WebElement hamburgerButton;
	
	@FindBy(css=".z_a")
	private WebElement walmartHomeButton;
	
	@FindBy(css=".g_a.an_c.an_e")
	private WebElement walmartDotCom;
	
	@FindBy(xpath="//a[@title='Pickup & delivery']")
	private WebElement pickUpDelivery;
	
	@FindBy(css="#global-search-input")
	private WebElement searchBox;
	
	@FindBy(css="#global-search-submit")
	private WebElement searchSubmit;
	
	@FindBy(css="#hf-account-flyout")
	private WebElement accountButton;
	
	@FindBy(css="#hf-easyreorder")
	private WebElement reorderButton;
	
	@FindBy(css="#hf-cart")
	private WebElement cart;

	public WalmartHomeHeader(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void trackOrders() throws IOException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = new Actions(driver);
		FileInputStream fis = new FileInputStream("C:\\Users\\ljone\\Walmart\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		
		log.debug("Attempting to click on element");
		s.click(hamburgerButton).build().perform();
		log.info("Element successfully clicked");
		
		WebElement ele = driver.findElement(By.cssSelector(".b_a.o_a.o_az.o_o.o_f.bm_c.b_f"));
		WebElement trackOrders = driver.findElement(By.xpath("//a[@data-index='0']"));
		log.debug("Attempting to click on element");
		s.click(trackOrders).build().perform();
		log.info("Element successfully clicked");
		
		Assert.assertTrue(driver.getCurrentUrl().contains("trackorder"));
		
		WebElement email = driver.findElement(By.id("email"));
		WebElement orderId = driver.findElement(By.id("fullOrderId"));
		WebElement orderStatusButton = driver.findElement(By.cssSelector(".button-wrapper"));
		try 
		{
			log.debug("Attempting to send info to textbox(es)");
			s.sendKeys(email, myProps.getProperty("email")).build().perform();
			s.sendKeys(orderId, myProps.getProperty("orderNum")).build().perform();
			log.info("Data successfully sent to textbox(es)");
			log.debug("Attempting to click on element");
			s.click(orderStatusButton).build().perform();
			log.info("Element successfully clicked");
		} 
		catch (Exception e) 
		{
			log.error("Class: " + WalmartHomeHeader.class.getName() + " || Method: " + methodName + " Error: " + e);
		}
		
		finally
		{
			//Do-something if neccessary
		}
	
		
	}
}

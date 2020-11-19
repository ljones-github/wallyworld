package Retail;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.Assert;

public class WalmartHomeHeader{
	
	WebDriver driver;
	WebDriverWait myWait;
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
		myWait = new WebDriverWait(driver, 5000);
	}
	
	public void trackOrders() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = new Actions(driver);
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		
		log.debug("Attempting to click on element");
		s.click(hamburgerButton).build().perform();
		log.info("Element successfully clicked");
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement trackOrders = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='0']"));
		log.debug("Attempting to click on element");
		s.click(trackOrders).build().perform();
		log.info("Element successfully clicked");
		
		Thread.sleep(5000);
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
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			log.error("Class: " + WalmartHomeHeader.class.getName() + " || Method: " + methodName + " Error: " + e);
		}
		
		finally
		{
			//Do-something if necessary
		}
	
		WebElement correctErrors = driver.findElement(By.xpath(("//span[@data-automation-id='track-order-form-alert']")));
		String errorAlert = correctErrors.getText();
		Assert.assertTrue(errorAlert.contains("correct"));
		
	}
	
	public void reorderItems() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = new Actions(driver);
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		
		log.debug("Attempting to click on element");
		s.click(hamburgerButton).build().perform();
		log.info("Element successfully clicked");
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement reorderItems = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='1']"));
		s.click(reorderItems).build().perform();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().contains("easyreorder"));
		
	}
	
	public void lists() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = new Actions(driver);
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		
		log.debug("Attempting to click on element");
		s.click(hamburgerButton).build().perform();
		log.info("Element successfully clicked");
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement lists = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='2']"));
		s.click(lists).build().perform();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().contains("lists"));
	}
	
	public void walmartPlus() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = new Actions(driver);
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		
		log.debug("Attempting to click on element");
		s.click(hamburgerButton).build().perform();
		log.info("Element successfully clicked");
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement wallyPlus = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='3']"));
		s.click(wallyPlus).build().perform();
		Thread.sleep(5000);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("plus"));
	}
	
	public void walmartCredit() throws IOException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = new Actions(driver);
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		Properties myProps = new Properties();
		myProps.load(fis);
		
		log.debug("Attempting to click on element");
		s.click(hamburgerButton).build().perform();
		log.info("Element successfully clicked");
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement wallyPlus = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='3']"));
		s.click(wallyPlus).pause(Duration.ofMillis(5000)).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("credit-card"));
	}
}

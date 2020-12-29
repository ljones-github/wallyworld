package Retail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jdk.jfr.Timespan;
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
		Actions s = hamburgerClick();
		
		Properties myProps = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		myProps.load(fis);
		
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
	
		//WebElement correctErrors = driver.findElement(By.xpath(("//span[@data-automation-id='track-order-form-alert']")));
		//String errorAlert = correctErrors.getText();
		//Assert.assertTrue(errorAlert.contains("correct"));
		
	}
	
	public void reorderItems() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = hamburgerClick();
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement reorderItems = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='1']"));
		s.click(reorderItems).build().perform();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().contains("easyreorder"));
		
	}
	
	public void lists() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = hamburgerClick();
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement lists = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='2']"));
		s.click(lists).build().perform();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().contains("lists"));
	}
	
	public void walmartPlus() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = hamburgerClick();
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement wallyPlus = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='3']"));
		s.click(wallyPlus).build().perform();
		Thread.sleep(5000);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("plus"));
	}
	
	public void walmartCredit() throws IOException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = hamburgerClick();
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']"))));
		WebElement wallyPlus = driver.findElement(By.xpath("//div[@data-tl-id='header-top-links']//a[@data-index='3']"));
		s.click(wallyPlus).pause(Duration.ofMillis(5000)).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("credit-card"));
	}
	
	public void walmartLocation() throws IOException, InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Properties myProps = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		myProps.load(fis);
		Actions s = hamburgerClick();
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#vh-location-button")));
		Thread.sleep(1000);
		WebElement locale = driver.findElement(By.cssSelector("#vh-location-button"));
		try
		{	log.debug("Attempting to click on element");
			s.click(locale).pause(Duration.ofMillis(2000)).build().perform();
			log.info("Element successfully clicked");
			log.debug("Attempting to locate element");
			WebElement zipCode = driver.findElement(By.cssSelector("#zipcode-location-form-input"));
			log.info("Element successfully located");
			log.debug("Attempting to click on element and send keys");
			s.click(zipCode).sendKeys(zipCode, myProps.getProperty("zipCode")).build().perform();
			log.info("Element successfully clicked and keys successfully sent");
			WebElement updateLocale = driver.findElement(By.xpath("//label[@data-tl-id='field-label-lhn-location']/following-sibling::div/button"));
			
			log.debug("Attempting to click on element");
			s.click(updateLocale).pause(Duration.ofMillis(2000)).build().perform();
			log.info("Successfully clicked on element");
			
			
			
		}
		catch(Exception e)
		{
			log.error("Class: " + WalmartHomeHeader.class.getName() + " || Method: " + methodName + " || Error: " + e);
		}
		
		
		WebElement reHamburger = driver.findElement(By.cssSelector("#header-Header-sparkButton"));
		s.pause(Duration.ofMillis(3000)).click(reHamburger).pause(Duration.ofMillis(2000)).build().perform();
		
		String myZip = driver.findElement(By.xpath("//span[@id='vh-location-button-label']/span[2]")).getText();
		Assert.assertTrue(myProps.getProperty("zipCode").equals(myZip));
		
	}
	
	public void localStore() throws IOException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = hamburgerClick();
		
		myWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#vh-store-button"))));
		WebElement store = driver.findElement(By.cssSelector("#vh-store-button"));
		s.moveToElement(store).pause(Duration.ofMillis(3000)).build().perform();
		Properties myProps = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\data.properties");
		myProps.load(fis);
		int option = Integer.parseInt(myProps.getProperty("option"));
		if(option > 4)
		{
			option = 1;
		}
		//Local Store
		WebElement 	ls = driver.findElement(By.xpath("//a[@data-uid='80hj4J6C']"));
		//Store Finder
		WebElement sf = driver.findElement(By.xpath("//a[@data-uid='UL8yFFCm']"));
		//Weekly Ad
		WebElement wa = driver.findElement(By.xpath("//a[@data-uid='YjNj1C8S']"));
		//Pickup Today
		WebElement pt = driver.findElement(By.xpath("//a[@data-uid='nzK_u_UN']"));
		try
		{
			log.info("Attempting to click on element");
		switch(option) {
			case 1: 
			{
				ls.click();
				log.debug("Successfully clicked on element");
				break;
			}
			case 2:
			{
				sf.click();
				log.debug("Successfully clicked on element");
				break;
			}
			case 3:
			{
				wa.click();
				log.debug("Successfully clicked on element");
				break;
			}
			case 4:
			{
				pt.click();
				log.debug("Successfully clicked on element");
				break;
			}
			default:
			{
				log.error("Option " + option + "not available");
				break;
			}
		}
		}
		catch(Exception e)
		{
			log.error("Class: " + WalmartHomeHeader.class.getName() + " || Method: " + methodName + " || Error: " + e);
		}
		
	}
	
	public void seeAllDepartments() throws InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = hamburgerClick();
		Thread.sleep(2000);
		WebElement seeAll = driver.findElement(By.xpath("//a[@data-tl-id='GlobalHeaderDepartmentsMenu-allLink']"));
		try 
		{
			log.debug("Attempting to click on element");
			s.click(seeAll).pause(Duration.ofMillis(2500)).build().perform();
			log.info("Successfully clicked on element");
		}
		catch (Exception e)
		{
			log.error("Class: " + WalmartHomeHeader.class.getName() + "|| Method: " + methodName + " Error: " + e);
		}
		
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().contains("departments"));
	}
	
	public void dealsStaticMenuLinks() throws InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		dealsStaticMenu();
		Thread.sleep(1000);
		Actions s = new Actions(driver);
		List<WebElement>dealsLinks = driver.findElements(By.xpath("//div[@id='dept-level1-item-0-children']/div/div/div/a"));
		//for(WebElement e : dealsLinks) 
		//{
			//s.click(dealsLinks.get(0)).pause(Duration.ofMillis(1000)).build().perform(); 
			//s.click(dealsLinks.get(0)).build().perform();
			//System.out.println(dealsLinks.get(0).getAttribute("href"));
			
		//}
		
		/*ArrayList<String>websiteTitles = new ArrayList<String>();
		Set<String>windowsIds = driver.getWindowHandles();
		Iterator<String>windowIt = windowsIds.iterator();
		
		while(windowIt.hasNext())
		{
			driver.switchTo().window(windowIt.next());
			websiteTitles.add(driver.getTitle());
		}
		
		
		System.out.println(websiteTitles);*/
	}
	
	public void dealsStaticMenu() throws InterruptedException
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = hamburgerClick();
		Thread.sleep(2000);
		WebElement dealsMenu = driver.findElement(By.xpath("//button[@data-uid='LHN-0']"));
		s.moveToElement(dealsMenu).pause(Duration.ofMillis(2000)).build().perform();
	
	}
	//Used for test(s) that require the user to click on the hamburger button before clicking on another item
	public Actions hamburgerClick()
	{
		methodName = new Throwable().getStackTrace()[0].getMethodName();
		Actions s = new Actions(driver);
		log.debug("Attempting to click on element");
		s.moveToElement(hamburgerButton).click(hamburgerButton).build().perform();
		log.info("Element successfully clicked");
		return s;
	}
}

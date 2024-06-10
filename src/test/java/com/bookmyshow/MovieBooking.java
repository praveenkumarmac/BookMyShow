package com.bookmyshow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MovieBooking {
	public static WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("beforeSuite");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("beforeTest");
	}	
	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass");
	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("beforeMethod");
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("afterMethod");
	}
	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}
	@AfterTest
	public void afterTest() {
		System.out.println("afterTeste");
	}
	@AfterSuite
	public void afterSuite() {
		System.out.println("afterSuite");
	}


	@DataProvider(name="TestData")
	public Object[][] movieLocation() {
		return new Object[][] {{"Chennai"}};
	}
	@DataProvider(name="TestData1")
	public Object[][] movieName() {
		return new Object[][] {{"Garudan"}};
	}	

	@Test(priority = -1)
	public void applicationLaunch() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		options.addArguments("disable-popups");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);

		String url = "https://www.bookmyshow.com";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		Thread.sleep(3000);

	}
	@Test(priority = 0, dataProvider = "TestData")
	public void theatreLocation(String place) throws InterruptedException {
		WebElement location = driver.findElement(By.xpath("//span[text()='"+place+"']"));
		location.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		Thread.sleep(3000);
	}
	@Test(priority = 1, dataProvider = "TestData1")
	public void movieToWatch(String movie) throws InterruptedException {
		//img[@alt='"+movie+"']

		WebElement movieName = driver.findElement(By.xpath("//div[text()='"+movie+"' and @class='sc-7o7nez-0 daKrZU']"));
		movieName.click();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals("Robo", movie);
		soft.assertEquals(false, "Value mismatched");	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		Thread.sleep(3000);
	}
	@Test(priority = 2)
	public void bookMovie() throws InterruptedException {
		Thread.sleep(18000);
		WebElement bookTicket = driver.findElement(By.xpath("//span[text()='Book tickets']/preceding::div[@class='sc-1vmod7e-2 hhYlrx']"));
		bookTicket.click();
		Thread.sleep(3000);
	}			
}

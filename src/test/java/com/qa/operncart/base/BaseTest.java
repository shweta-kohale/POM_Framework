package com.qa.operncart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opercart.factory.DriverFactory;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory df;
	public LoginPage loginObject;
	public Properties prop;
	public AccountsPage accPage;
	public SearchResultsPage searchResultsPage;
	public ProductInfoPage productInfo;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.intitProp();
		driver = df.initDriver(prop);
		loginObject = new LoginPage(driver);
		
			
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

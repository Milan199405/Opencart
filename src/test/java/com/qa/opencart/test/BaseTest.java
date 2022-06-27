package com.qa.opencart.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.ProductResultsPage;
import com.qa.opencart.pages.RegisterAccountPage;

public class BaseTest {
	
    private WebDriver driver;
	private DriverFactory driverFactory;
	public Properties props;
	
	public LoginPage loginPage;
	public AccountPage accountPage;
	public ProductResultsPage productsResultPage;
	public ProductInfoPage productInfoPage;
	public RegisterAccountPage registerAccountPage;
	
	public SoftAssert softAssert;
    
	@BeforeTest
	public void setup() {
		driverFactory = new DriverFactory();
		props = driverFactory.initializeProperties();
		driver = driverFactory.initializeDriver(props);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;

public class BaseTest {
	
	public BasePage basePg;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPg;
	public AccountPage accountPg;
	public ProductInfoPage productInfoPg;
	public RegistrationPage registrationPg;
	
	
	
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName){
		
		basePg = new BasePage();
		prop = basePg.init_prop();
		String browser = prop.getProperty("broswer");
		
		if(browserName!=null){
			browser=browserName;
		}
		
		driver = basePg.init_driver(browser);
		loginPg = new LoginPage(driver);
		accountPg = new AccountPage(driver);
		productInfoPg = new ProductInfoPage(driver);
		driver.get(prop.getProperty("url"));
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}

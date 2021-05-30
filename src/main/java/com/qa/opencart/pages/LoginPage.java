package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;
import com.qa.opentcart.util.Constants;
import com.qa.opentcart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1.Private locators
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login' and @type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	
	//2.Constructor of the class
	public LoginPage(WebDriver driver){
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	
	//3.Page Actions : Nothing but the feature(behavior) of the page
	
	@Step("Getting login page title")
	public String getLoginPageTitle(){
		String loginPageTitle=elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 5, 1);
		return loginPageTitle;
	}
	
	@Step("Checking forgot password lik in exsits")
	public boolean verifyForgotPwdLinkExist(){
		return elementUtil.doIsDisplayed(forgotPwdLink);
		
	}
	
	@Step("login with username: {0} and password: {1}")
	public AccountPage doLogin(String un, String pwd){
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
//		
		return new AccountPage(driver);
	}
	
	@Step("Navigate to the registation page")
	public RegistrationPage navigateToRegistrationPage(){
		elementUtil.doClick(registerLink);
		return new RegistrationPage(this.driver);
		
	}

}

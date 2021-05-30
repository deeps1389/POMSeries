package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;
import com.qa.opentcart.util.Constants;
import com.qa.opentcart.util.ElementUtil;

public class RegistrationPage extends BasePage {
	
	private ElementUtil elementUtil;
	
	//1.Private By locators
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYesRadioBtn = By.xpath("(//label[@class= 'radio-inline']/input)[1]");
	private By subscribeNoRadioBtn = By.xpath("(//label[@class= 'radio-inline']/input)[2]");
	private By agreeCheckBox = By.xpath("//input[@type = 'checkbox' and @name = 'agree']");
	private By continueBtn = By.xpath("//input[@type = 'submit' and @value = 'Continue']");
	private By accountSuccess = By.cssSelector("#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	//2.Constructor of the class
	
	public RegistrationPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	//3.Class Actions or methods
	
	public boolean accountRegistration(String firstname, String lastname, String emailId, String telephone,
			String password, String subscribe){
		
		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
		elementUtil.doSendKeys(this.emailId, emailId);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")){
			elementUtil.doClick(subscribeYesRadioBtn);
		}
		else{
			elementUtil.doClick(subscribeNoRadioBtn);
		}
		
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueBtn);
		
		String text = elementUtil.doGetText(accountSuccess);
		
		if(text.equals(Constants.ACCOUNT_SUCCESS_MESSAGE)){
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		else 
			return false;
		
		
		
		
		
		
	}
	
	
	

}

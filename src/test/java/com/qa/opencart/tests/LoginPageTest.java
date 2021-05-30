package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.testlisteners.ExtentReportListener;
import com.qa.opentcart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners(ExtentReportListener.class)
@Epic("Epic 100: Design the login page features")
@Story("User Story 100: Design the login features with the title, link exists and by logging to account ")
public class LoginPageTest extends BaseTest {
	
	@Description("Verfiy login page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verifyLoginPageTitleTest(){
		String title = loginPg.getLoginPageTitle();
		System.out.println("Login Page title is: "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
			
	}
	
	@Description("Verify Forgot password link exists")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2)
	public void verifyForgotPwdLinkExistTest(){
		Assert.assertTrue(loginPg.verifyForgotPwdLinkExist());
	}
	
	@Description("Login to application with username and password")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void verifydoLoginTest(){
		loginPg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

}

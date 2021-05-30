package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opentcart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic-200: Design accounts page")
@Story("User Story 201 : Designing the  accounts page features with title, header, account section and product results")
public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accountPageSetUp(){
		accountPg = loginPg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Account page title  test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verifyAccountPageTitleTest(){
		String accountPageTitle = accountPg.getMyAccountPageTitle();
		System.out.println("Account Page Title is: "+accountPageTitle);
		Assert.assertEquals(accountPageTitle, Constants.ACCOUNT_PAGE_TITLE);
		
	}
	
	@Description("Verfiy account page Your Stor link exists")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void verifyYorStorLinkTest(){
		Assert.assertTrue(accountPg.verifyYourStoreLinkExist());
	}
	
	@Test(priority=3)
	public void verifyMyAccountLinkTest(){
		Assert.assertTrue(accountPg.verifyMyAccountLinkExist());
	}
	
	@Test(priority=4)
	public void veriftHomeIconLinkTest(){
		Assert.assertTrue(accountPg.verfiyHomeIconLinkExist());
	}
	
	@Test(priority=5)
	public void verfiyMyAccountHeaderTextTest(){
		String headerText = accountPg.getMyAccountHeader();
		System.out.println("Account Header Text is:"+headerText);
		Assert.assertEquals(headerText, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test(priority=6)
	public void verfiyMyAccountHeaderSectionsCountTest(){
		Assert.assertTrue(accountPg.getMyAccountHeaderSectionsCount() == Constants.ACCOUNT_HEADER_SECTION_COUNT);
	}
	
	@Test(priority=7)
	public void verifyMyAccountHeaderSectionsTest(){
		List<String> accountSectionsList = accountPg.getMyAccountHeaderSectionsList();
		Assert.assertEquals(accountSectionsList, Constants.accountHeaderSections());
	}
	
	@Test(priority=8)
	public void verifySearchResultTest(){
		Assert.assertTrue(accountPg.doSearchProduct("mac"));
	}
	
	

}

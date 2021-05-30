package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BasePage;
import com.qa.opentcart.util.Constants;
import com.qa.opentcart.util.ElementUtil;

import io.qameta.allure.Step;

public class AccountPage extends BasePage {
	
	private WebDriver driver;
	private ElementUtil elemetUtil;
	
	//1.Private By locators
	By yourStorLink = By.linkText("Your Store");
	By myAccountLink = By.xpath("//a[@title='My Account' and @class='dropdown-toggle']");
	By homeIconLink = By.xpath("//i[@class='fa fa-home']");
	By header = By.cssSelector("div#logo a");
	By headerSectionsList = By.cssSelector("div#content h2");
	By searchText = By.cssSelector("div#search input");
	By serachButton = By.cssSelector(".btn.btn-default.btn-lg");
	By searchResult = By.xpath("//div[@class='product-thumb']");
	By prouductSearchResultHeader = By.cssSelector(".product-thumb h4 a");
	
	
	//2.Constructor of the class
	public AccountPage(WebDriver driver){
		this.driver = driver;
		elemetUtil = new ElementUtil(driver);
		
	}
		
	//3.Page Actions
	@Step("getting accounts page title")
	public String getMyAccountPageTitle(){
		return elemetUtil.waitForTitlePresent(Constants.ACCOUNT_PAGE_TITLE, 6, 1);
	}
	
	public boolean verifyYourStoreLinkExist(){
		return elemetUtil.doIsDisplayed(yourStorLink);
	}
	
	public boolean verifyMyAccountLinkExist(){
		return elemetUtil.doIsDisplayed(myAccountLink);
	}
	
	
	public boolean verfiyHomeIconLinkExist(){
		return elemetUtil.doIsDisplayed(homeIconLink);
	}
	
	@Step("getting header value")
	public String getMyAccountHeader(){
		return driver.findElement(header).getText();
	}
	
	@Step("getting total number of account section count")
	public int getMyAccountHeaderSectionsCount(){
		return  elemetUtil.getElementsCount(headerSectionsList);
			
	}
		
	@Step("getting account header section list")
	public List<String> getMyAccountHeaderSectionsList(){
		
		List<String> accountSectionList = new ArrayList<String>();
		
		List<WebElement> accountHeaderSections = elemetUtil.getElementList(headerSectionsList);
		
		for(WebElement ele : accountHeaderSections){
			System.out.println(ele.getText());
			accountSectionList.add(ele.getText());
				
		}
		return accountSectionList;
		
	}
	
	@Step("Searching the product with name: {0}")
	public boolean doSearchProduct(String productName) {
		elemetUtil.doSendKeys(searchText, productName);
		elemetUtil.doClick(serachButton);
		
		if (elemetUtil.getElementsCount(searchResult)>0)
			return true;
		else 
			return false;
		
	}
	
	@Step("selecting product from the search result with product name :{0}")
	public ProductInfoPage selectProductFromSearchResult(String productName){
		
		List<WebElement> productSearchList = elemetUtil.getElementList(prouductSearchResultHeader);
		for(WebElement ele : productSearchList){
			if(ele.getText().equals(productName)){
				ele.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
		
	}
	

}

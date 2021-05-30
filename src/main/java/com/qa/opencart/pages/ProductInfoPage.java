package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opentcart.util.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	

	By productHeader = By.cssSelector("#content h1");
	By productThumbnails = By.cssSelector("#content .thumbnails li img");
	By productMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	By productPrice = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	By quantityTextBox = By.id("input-quantity");
	By addToCartButton = By.id("button-cart");
	
	public ProductInfoPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	public Map<String, String> getProductInformation(){
		Map<String, String> productInfoMap = new HashMap<>();
		productInfoMap.put("ProductName", elementUtil.doGetText(productHeader).trim());
		
		List<WebElement> productMetaDataList = elementUtil.getElementList(productMetaData);
		for(WebElement ele : productMetaDataList){
			productInfoMap.put(ele.getText().split(":")[0].trim(), ele.getText().split(":")[1].trim());
			
		}
		
		List<WebElement> productPriceList = elementUtil.getElementList(productPrice);
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTax", productPriceList.get(1).getText().split(":")[1].trim());
		
		return productInfoMap;
	}
	
	public void selectQuantity(String qty){
		
		elementUtil.doSendKeys(quantityTextBox, qty);
	}
	
	public void addToCart(){
		elementUtil.doClick(addToCartButton);
	}
	
	public int getProductImages(){
		int totalImageCount =  elementUtil.getElementList(productThumbnails).size();
		System.out.println("Total number of product images: "+4);
		
		return totalImageCount;
	}

	public String getProductInfoPageTitle(String productName) {
		return elementUtil.waitForTitlePresent(productName, 10, 5);
		
	}
	
	
}

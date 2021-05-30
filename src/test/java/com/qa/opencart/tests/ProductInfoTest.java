package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opentcart.util.Constants;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetUp(){
		accountPg = loginPg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void verifyProductPageTitleTest(){
		 Assert.assertTrue(accountPg.doSearchProduct("iMac"));
		accountPg.selectProductFromSearchResult("iMac");
		Assert.assertEquals(productInfoPg.getProductInfoPageTitle("iMac"),"iMac");
		
	}
	
	@Test
	public void verifyProductInfoPageTest_MacBook(){
		String productName = "MacBook";
		
		Assert.assertTrue(accountPg.doSearchProduct(productName));
		
		accountPg.selectProductFromSearchResult("MacBook Pro");
		
		Assert.assertEquals(productInfoPg.getProductImages(),4);
		
		Map<String, String> productDataMap = productInfoPg.getProductInformation();
		
		System.out.println(productDataMap);
		
		//Has map data
		//{Brand=Apple, Availability=Out Of Stock, 
		//price=$2,000.00, Product Name=MacBook Pro, Product Code=Product 18, 
		//Reward Points=800, exTax=Ex Tax: $2,000.00}
		
		Assert.assertEquals(productDataMap.get("ProductName"), "MacBook Pro");
		Assert.assertEquals(productDataMap.get("Brand"), "Apple");
		Assert.assertEquals(productDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productDataMap.get("price"), "$2,000.00");
		Assert.assertEquals(productDataMap.get("exTax"), "$2,000.00");
		
		
	}
	
	
	@Test
	public void verfiFyProductInfoPageTest_iMac(){
		String productName = "iMac";
		Assert.assertTrue(accountPg.doSearchProduct(productName));
		
		productInfoPg = accountPg.selectProductFromSearchResult("iMac");
		
		Assert.assertTrue(productInfoPg.getProductImages()==3);
		
		Map<String,String> productInfoMap = productInfoPg.getProductInformation();
		System.out.println(productInfoMap);
		
		Assert.assertEquals(productInfoMap.get("ProductName"), "iMac");
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
		
		
	}

}

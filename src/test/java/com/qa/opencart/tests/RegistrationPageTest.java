package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opentcart.util.Constants;
import com.qa.opentcart.util.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void registrationPageSetUp(){
		
		registrationPg = loginPg.navigateToRegistrationPage();
		
	}
	
	@DataProvider
	public Object [][]  getRegistrationData(){
		Object data [][] = ExcelUtil.getSheetData(Constants.REGISTRATION_SHEET_NAME);
		return data;
		
	}
	
	
	@Test(dataProvider="getRegistrationData")
	public void userRegistrationTest(String firstname, String lastname, String email, String telephone, String password,String subscribe){
		
		registrationPg.accountRegistration(firstname, lastname, email, telephone, password, subscribe);
	}
	

}

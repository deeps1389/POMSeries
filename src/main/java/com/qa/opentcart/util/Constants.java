package com.qa.opentcart.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_HEADER = "Your Store";
	
	public static final int ACCOUNT_HEADER_SECTION_COUNT = 4;
	
	public static final String ACCOUNT_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	public static final String REGISTRATION_SHEET_NAME = "Registration";
	
	
	
	public static List<String> accountHeaderSections(){
		List<String> accountSectionsText = new ArrayList<String>();
		accountSectionsText.add("My Account");
		accountSectionsText.add("My Orders");
		accountSectionsText.add("My Affiliate Account");
		accountSectionsText.add("Newsletter");
		
		return accountSectionsText;
		
	}

}

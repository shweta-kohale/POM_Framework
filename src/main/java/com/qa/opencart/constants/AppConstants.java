package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	
	public static final String ACC_PAGE_TITLE = "My Account";
	
	public static final String LOGIN_PAGE_URL_PARAM = "route=account/login"; 
	
	public static final String ACC_PAGE_URL_PARAM = "route=account/account"; 
	
	public static final List<String> ACC_PAGE_SEC_HEADERS = Arrays.asList("My Account", "My Affiliate Account", 
			"My Orders", "Newsletter");

	public static final int DEFAULT_TIME_OUT = 5;
	
	public static final int DEFAULT_LARGE_TIME_OUT = 10;
	
	public static final int MACBOOK_PRO_CONSTANT = 4;
	
	public static final int MACBOOK_AIR_CONSTANT = 4;
	
	public static final int IMAC_CONSTANT = 3;
}

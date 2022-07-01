package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Loginn";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Accountt";
	public static final String ERROR_MESSAGE = "No match for E-Mail Address and/or Password.";
	public static final String ACCOUNT_CREATION_SUCCESS_MSG = "Your Account Has Been Created!";
	public static final long DEFAULT_WAIT = 5;
	public static final String REGISTER_PAGE_SHEET_NAME = "Registration";

	public static List<String> expectedAccountPageContentHeader() {
		List<String> ACCOUNT_PAGE_CONTENT_HEADER = new ArrayList<String>();
		ACCOUNT_PAGE_CONTENT_HEADER.add("My Account");
		ACCOUNT_PAGE_CONTENT_HEADER.add("My Orders");
		ACCOUNT_PAGE_CONTENT_HEADER.add("My Affiliate Account");
		ACCOUNT_PAGE_CONTENT_HEADER.add("Newsletterr");
		return ACCOUNT_PAGE_CONTENT_HEADER;
	}

	public static String PRODUCT_HEADER[] = { "MacBook Pro", "Apple Cinema 30\"", "Samsung SyncMaster 941BW" };

}

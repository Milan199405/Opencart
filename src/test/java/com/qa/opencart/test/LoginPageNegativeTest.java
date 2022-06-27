package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] negativeData() {
		return new Object[][] { 
			{"abcgmail.com", "qwqjwwh" },
			{"abc@gmailcom", "Selenium@12345"},
			{"naveenanimation20@gmail.com", "Selenium@1234"}
		};
	}

	@Test(dataProvider = "negativeData")
	public void loginNegativeTest(String un, String pwd) {
		Boolean flag = loginPage.doLoginWithWrongCredentials(un, pwd);
		Assert.assertFalse(flag);
	}
}

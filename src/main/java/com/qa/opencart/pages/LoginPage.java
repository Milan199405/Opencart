package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private By emailLocator = By.id("input-email");
	private By passwordLocator = By.id("input-password");
	private By login = By.xpath("//input[@value = 'Login']");
	private By warningMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private By register = By.xpath("(//aside[@id = 'column-right']//a)[2]");

	private WebDriver driver;
	private ElementUtil elUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		return elUtil.doGetTitle();
	}

	public String getLoginPageUrl() {
		return elUtil.doGetUrl();
	}

	public Boolean isRegisterLinkExist() {
		return elUtil.isElementExist(register);
	}

	@Step("Do login with username: {0} and password: {1}")
	public AccountPage doLogin(String email, String password) {
		elUtil.doSendKeys(emailLocator, email);
		elUtil.doSendKeys(passwordLocator, password);
		elUtil.doClick(login);
		return new AccountPage(driver);
	}

	public Boolean doLoginWithWrongCredentials(String email, String password) {
		elUtil.doSendKeys(emailLocator, email);
		elUtil.doSendKeys(passwordLocator, password);
		elUtil.doClick(login);
		String warningtext = elUtil.doGetText(warningMsg);
		System.out.println(warningtext);
		if (warningtext.contains(Constants.ERROR_MESSAGE))
			return false;
		return true;
	}

	public RegisterAccountPage goToRegisterPage() {
		elUtil.doClick(register);
		return new RegisterAccountPage(driver);
	}
}

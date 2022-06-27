package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method is used for getting the By locator (When user uses String locator
	 * strategy)
	 * 
	 * @param locatorType
	 * @param value
	 * @return This method returns By locator
	 */
	public By getByLocator(String locatorType, String value) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(value);
			break;

		case "classname":
			locator = By.className(value);
			break;

		case "name":
			locator = By.name(value);
			break;

		case "tagname":
			locator = By.tagName(value);
			break;

		case "linktext":
			locator = By.linkText(value);
			break;

		case "partiallinktext":
			locator = By.partialLinkText(value);
			break;

		case "cssselector":
			locator = By.cssSelector(value);
			break;

		case "xpath":
			locator = By.xpath(value);
			break;

		default:
			System.out.println("Please provide a proper locator type.");
			break;
		}
		return locator;
	}

	/************************************
	 * WebElement Utility
	 *************************************/

	/**
	 * This method is used for, getting the WebElement this method - follows "By
	 * Locator" strategy.
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.flash)) {
			jsUtil.flash(element);
			return element;
		}
		return element;
	}

	public WebElement getElementWithWait(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<WebElement> getElementsWithWait(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * This method is used for, getting the WebElement this method - follows "String
	 * Locator" strategy.
	 * 
	 * @param locatorType
	 * @param value
	 * @return WebElement
	 */
	public WebElement getElement(String locatorType, String value) {
		return driver.findElement(getByLocator(locatorType, value));
	}

	/************************************
	 * SendKeys Utility
	 *************************************/

	/**
	 * This method is used for sending keys or values to WebPage, this method -
	 * follows "By Locator" strategy.
	 * 
	 * @param locator
	 * @param keys
	 */
	
	public void doClear(By locator) {
		getElement(locator).clear();
	}
	
	public void doSendKeys(By locator, String keys) {
		doClear(locator);
		getElement(locator).sendKeys(keys);
	}

	public void doSendKeys(String locatorType, String locatorValue, String keys) {
		getElement(locatorType, locatorValue).sendKeys(keys);
	}

	/*
	 * public void doSendKeys(By locator, long timeout, String val) { WebDriverWait
	 * wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	 * wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(val
	 * );; }
	 */

	public void doSendKeysWithWait(By locator, long timeout, String val) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(val);
		;
	}

	public void doSendKeysWithWait(By locator, long timeout, String val, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(val);
		;
	}

	
	public String doGetTitle() {
		return driver.getTitle();
	}
	

	public String doGetUrl() {
		return driver.getCurrentUrl();
	}
	
	/************************************
	 * Get Text Utility
	 *************************************/

	/**
	 * This method is used for getting text from a HTML tag in the DOM.
	 * 
	 * @param locator
	 * @return Text of the element
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	/************************************
	 * Click Utility
	 *************************************/

	/**
	 * This method is used for clicking purpose.
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClickWithWait(By locator, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}

	/**
	 * This method is used for check, whether the element is displayed or not.
	 * 
	 * @param locator
	 * @return Boolean
	 */
	public Boolean isElementExist(By locator) {
		return getElement(locator).isDisplayed();
	}

	/************************************
	 * Elements Count,List, Print Utility
	 *************************************/

	/**
	 * This method is used for getting total number of elements present in a
	 * WebPage.
	 * 
	 * @param locator
	 * @return Total elements count
	 */
	public int getElementsCount(By locator) {
		return driver.findElements(locator).size();
	}

	/**
	 * This method is used for getting text of an element.
	 * 
	 * @param locator
	 * @return List of String
	 */
	public List<String> getElementsTextList(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(locator);
		for (WebElement e : elementList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				textList.add(e.getText());
			}

		}
		return textList;
	}

	/**
	 * This method is used for print, a List of String in console
	 * 
	 * @param list
	 */
	public void printElementsText(List<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
	}

	/************************************
	 * Get Attribute Utility
	 *************************************/

	/**
	 * This method is used for getting the attribute value
	 * 
	 * @param locator
	 * @param attrName
	 * @return the attribute name of WebElement
	 */

	public String getAttributeValue(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	/**
	 * This method is used for getting a list of given attributes from an element.
	 * 
	 * @param locator
	 * @param attrName
	 * @return List of given attributes of an element
	 */
	public List<String> getAttributeValues(By locator, String attrName) {
		List<String> actualAttrList = new ArrayList<String>();
		List<WebElement> elements = getElements(locator);
		for (WebElement e : elements) {
			actualAttrList.add(e.getAttribute(attrName));
		}
		return actualAttrList;
	}

	/************************************
	 * DropDown Utility
	 *************************************/

	public void doSelectByIndex(By locator, int index) {
		WebElement element = getElement(locator);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void doSelectByVisibleText(By locator, String visibleText) {
		WebElement element = getElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	public void doSelectByValue(By locator, String attrValue) {
		WebElement element = getElement(locator);
		Select select = new Select(element);
		select.selectByValue(attrValue);
	}

	public List<String> getAllDropDownValuesWithSelectTag(By locator) {
		List<String> actualList = new ArrayList<String>();
		WebElement elemant = getElement(locator);
		Select select = new Select(elemant);
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			actualList.add(text);
		}

		return actualList;
	}

	/************************************
	 * User Action Utility
	 *************************************/

	public void contextClick(By locator) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.contextClick(element).build().perform();
		
	}

	public void dragAndDrop(By sourceLocator, By targetLocator) {
		Actions action = new Actions(driver);
		WebElement sourceElement = getElement(sourceLocator);
		WebElement targetElement = getElement(targetLocator);

		// Case - 1
//       action.dragAndDrop(sourceElement, targetElement).build().perform();

		// Case - 2
		action.clickAndHold(sourceElement).moveToElement(targetElement).release().build().perform();
	}

	public void moveToElement(By locator) {
		Actions action = new Actions(driver);
		WebElement targetElement = getElement(locator);
		action.moveToElement(targetElement).build().perform();
	}

	public void dosendKeysByActionClass(By locator, String value) {
		Actions actions = new Actions(driver);
		WebElement targetElement = getElement(locator);

		// Case - 1
//        actions.sendKeys(targetElement, value).build().perform();

		// Case - 2
		actions.click(targetElement).sendKeys(value).build().perform();
	}

	public void doClickByActionClass(By locator) {
		Actions action = new Actions(driver);
		WebElement targElement = getElement(locator);

		// Case - 1
//        action.click(targElement).perform();

		// Case - 2
		action.moveToElement(targElement).click().build().perform();
	}

	/************************************
	 * Wait For Non WebElement Utility
	 *************************************/

	/**
	 * Wait For URL
	 **/

	public Boolean checkUrlFractionWithWait(int timeout, String urlContains) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(urlContains));
	}

	public Boolean checkUrlToBeWithWait(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlToBe(url));
	}

	/**
	 * Wait For Title
	 **/

	public Boolean checkTitleContainsWithWait(int timeout, String titleContains) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.titleContains(titleContains));
	}

	public Boolean checkTitleIsWithWait(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.titleIs(title));
	}

	/**
	 * Wait For Alert
	 **/

	public Alert switchToAlertWithWait(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Wait For Frames
	 **/

	public WebDriver switchToFrameWithWaitByNameOrId(long timeout, String nameOrId) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}
}

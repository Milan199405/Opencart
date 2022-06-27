package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {

	private Properties props;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionManager(Properties props) {
		this.props = props;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(props.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(props.getProperty("incognito"))) co.addArguments("--incognito");
		return co;

	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(props.getProperty("headless"))) fo.addArguments("--headless");
		if (Boolean.parseBoolean(props.getProperty("incognito"))) fo.addArguments("--incognito");
		return fo;

	}
}

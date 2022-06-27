package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
//	private WebDriver driver;
	private Properties props;
	public static String flash;
	private OptionManager om;

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initializeDriver(Properties props) {

		flash = props.getProperty("highlight");
		String browser = props.getProperty("browser");
		om = new OptionManager(props);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(om.getChromeOptions()));
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(om.getFirefoxOptions()));
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("Please provide a valid browser name");
		}

		getThreadLocalDriver().manage().window().maximize();
		getThreadLocalDriver().manage().deleteAllCookies();
//		getThreadLocalDriver().get(props.getProperty("url"));
//		openUrl(props.getProperty("url"));
		
		URL url;
		try {
			url = new URL(props.getProperty("url"));
			openUrl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
			
		return getThreadLocalDriver();
	}

	public static synchronized WebDriver getThreadLocalDriver() {
		return tlDriver.get();
	}


	public Properties initializeProperties() {

		FileInputStream ip = null;
		props = new Properties();
		String env = System.getProperty("env");
		if (env == null) {

			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {

			try {
				switch (env.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;

				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				default:
					System.out.println("Please provide valid environment name...");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			props.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getThreadLocalDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public void openUrl(String url) {
		try {
		if(url == null) throw new Exception("url is null");
		} catch(Exception e) {
			
		}
		
		getThreadLocalDriver().get(url);
	}
	
	public void openUrl(URL url) {
		try {
		if(url == null) throw new Exception("url is null");
		} catch(Exception e) {
			
		}
		
		getThreadLocalDriver().navigate().to(url);
	}
	
	public void openUrl(String baseUrl, String path) {
		try {
		if(baseUrl == null) throw new Exception("baseUrl is null");
		} catch(Exception e) {
			
		}
		
		getThreadLocalDriver().get(baseUrl+"/"+path);
	}
	
	public void openUrl(String baseUrl, String path, String queryParam) {
		try {
		if(baseUrl == null) throw new Exception("baseUrl is null");
		} catch(Exception e) {
			
		}
		
		getThreadLocalDriver().get(baseUrl+"/"+path+"?"+queryParam);
	}

}

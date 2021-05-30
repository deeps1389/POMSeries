package com.qa.opencart.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opentcart.util.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Deepali gade
 *
 */
public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the browser on the basis of given
	 * browser...
	 * 
	 * @param browser
	 * @return This return webdriver driver
	 */
	public WebDriver init_driver(String browser) {
		System.out.println("browser value is : " + browser);
		
			if(browser.equalsIgnoreCase("chrome")){
				WebDriverManager.chromedriver().setup();
				//driver = new ChromeDriver();
				highlight = prop.getProperty("highlight");
				optionsManager = new OptionsManager(prop);
				if(Boolean.parseBoolean((prop.getProperty("remote"))))
				{
					init_remoteDriver("chrome");
				}
				else
				{
					tlDriver.set(new ChromeDriver());
				}
			}
			else if(browser.equalsIgnoreCase("firefox")){
				WebDriverManager.firefoxdriver().setup();
				//driver = new FirefoxDriver();
				if(Boolean.parseBoolean((prop.getProperty("remote")))){
					init_remoteDriver("firefox");
				}
				else
				{
					tlDriver.set(new FirefoxDriver());
				}
				
			}
			else if(browser.equalsIgnoreCase("safari")){
				//driver = new SafariDriver();
				tlDriver.set(new SafariDriver());
			}
			else{
				System.out.println("Please pass the correct browser value");
			}
			
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			
			return getDriver();

	}

	private void init_remoteDriver(String browser) {
		System.out.println("Running test on remote Grid:" + browser);
		
		if(browser.equals("chrome"))
		{
			//DesiredCapabilities cap = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--no-sandbox");
			//options.addArguments("--headless");
	        //options.setExperimentalOption("useAutomationExtension", false);
	        //options.addArguments("start-maximized"); // open Browser in maximized mode
	        //options.addArguments("disable-infobars"); // disabling infobars
	       // options.addArguments("--disable-extensions"); // disabling extensions
	       // options.addArguments("--disable-gpu"); // applicable to windows os only
	        options.addArguments("--disable-dev-shm-usage"); 
			//options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			//cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), options));
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}
		}
		
		else if(browser.equals("firefox")){
			//DesiredCapabilities cap = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			//cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions()); 
			// ***With new version of selenium no need to use desiredcapabilities for firefox ***
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), options));
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}

		/**
	 * getDriver using ThreadLocal
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to load the properties from config.properties file
	 * 
	 * @return it return Properties prop reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\Deepali\\workspace\\Apr2021NewPOMSeries\\src\\main\\java\\com\\qa\\opencart\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	
	}

	/**
	 * This method is used to take the screenshot It will return the path of
	 * screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

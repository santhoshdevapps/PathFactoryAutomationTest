package com.qa.base;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testcases.LoginPageTest;
import com.qa.testcases.SignupPageTest;
import com.qa.utils.Constants;
import com.qa.utils.Log;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class TestBase {

	public static WebDriver driver;
	WebDriverWait wait;
	SignupPageTest signupPageTest;
	LoginPageTest loginPageTest;

	public void initialization(String browser) throws Exception {
		 //,String deviceName, String osVersion, String port
		if (browser.equalsIgnoreCase("chrome")) {
			// create chrome instance
			System.setProperty(Constants.DRIVER_CHROME, Constants.DRIVER_LAUNCHER_PATH_CHROME);
			driver = new ChromeDriver();
			Log.info("Chrome driver is created");
		}
		// Check if parameter passed as 'firefox'
		else if (browser.equalsIgnoreCase("firefox")) {
			// set path to chromedriver.exe
			System.setProperty(Constants.DRIVER_FIREFOX, Constants.DRIVER_LAUNCHER_PATH_FIREFOX);
			// create chrome instance
			driver = new FirefoxDriver();
			Log.info("Firefox driver is created");
		}
		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {
			// set path to Edge.exe
			System.setProperty(Constants.DRIVER_EDGE, Constants.DRIVER_LAUNCHER_PATH_EDGE);
			// create Edge instance
			driver = new EdgeDriver();
			Log.info("Edge driver is created");
		} 
		else if(browser.equalsIgnoreCase("ios")){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "emulator-5554");
			capabilities.setCapability("platformVerison", "7.0");
			capabilities.setCapability("appPackage", "com.android.chrome");
			capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

			driver  = new AndroidDriver<WebElement>(new URL(Constants.APPIUM_URL), capabilities);
			Log.info("Ios driver is created");
		}
		else if(browser.equalsIgnoreCase("Android")){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "iphone");
			capabilities.setCapability("platformVerison", "11.1");
			capabilities.setCapability("automationName", "XCUITest");
			//capabilities.setCapability("app", "/Users/santhoshdamodharan/Library/Developer/Xcode/DerivedData/Calculator-ctkoofyohfgoikczsqrozanbhpkq/Build/Products/Debug-iphonesimulator/Calculator.app");
			//capabilities.setCapability("autoAcceptAlerts", true);
			// capabilities.setCapability("waitForAppScript", true);
			// capabilities.setCapability("app", "NBC DEV Bay");
			//capabilities.setCapability("bundleId", bundleId);
			//capabilities.setCapability("udid",Constants.UDID);

			driver  = new IOSDriver<WebElement>(new URL(Constants.APPIUM_URL), capabilities);
			Log.info("Android driver is created");
		}
		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		Log.info("website is launched");
		wait = new WebDriverWait(driver, 30);
		HashMap<WebDriver, WebDriverWait> driverInstance = new HashMap<WebDriver, WebDriverWait>();
		driverInstance.put(driver, wait);

	}


	public WebDriver getDriver() {
		Log.info("returns the driver");
		return TestBase.driver;
		

	}

	public WebDriverWait getWait() {
		Log.info("returns the wait time");
		return this.wait;

	}
}

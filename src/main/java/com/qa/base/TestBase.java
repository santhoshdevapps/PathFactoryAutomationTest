package com.qa.base;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testcases.LoginPageTest;
import com.qa.testcases.SignupPageTest;
import com.qa.utils.Constants;
import com.qa.utils.Log;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class TestBase {

	public static WebDriver driver;
	public DesiredCapabilities capabilities = null;
	WebDriverWait wait;
	SignupPageTest signupPageTest;
	LoginPageTest loginPageTest;

	public void initialization(String browser, String port) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {

			// create chrome instance
			ChromeOptions options = new ChromeOptions();
			System.setProperty(Constants.DRIVER_CHROME,
					System.getProperty("user.dir") + Constants.DRIVER_LAUNCHER_PATH_CHROME);
			driver = new ChromeDriver(options);
			Log.info("Chrome driver is created");

			/** Using Selenium Grid - to invoke Chrome **/

			// capabilities = new DesiredCapabilities();
			// capabilities.setBrowserName(BrowserType.CHROME);
			// capabilities.setPlatform(Platform.MAC);
			// options.merge(capabilities);
			// driver = new RemoteWebDriver(new
			// URL("http://localhost:"+port+"/wd/hub"), options);
		}

		// Check if parameter passed as 'firefox'
		else if (browser.equalsIgnoreCase("firefox")) {

			// set path to firfox.exe
			System.setProperty(Constants.DRIVER_FIREFOX,
					System.getProperty(Constants.USER_DIRECTORY) + Constants.DRIVER_LAUNCHER_PATH_FIREFOX);

			// create firefox instance
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
			Log.info("Firefox driver is created");

			/** Using Selenium Grid - to invoke Firefox **/

			// capabilities = DesiredCapabilities.firefox();
			// capabilities.setBrowserName(BrowserType.FIREFOX);
			// capabilities.setPlatform(Platform.MAC);
			// FirefoxOptions options = new FirefoxOptions();
			// options.merge(capabilities);
			// driver = new RemoteWebDriver(new
			// URL("http://192.168.0.25:"+port+"/wd/hub"), options);

		}

		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {

			// set path to Edge.exe
			System.setProperty(Constants.DRIVER_EDGE, Constants.DRIVER_LAUNCHER_PATH_EDGE);
			// create Edge instance
			driver = new EdgeDriver();
			Log.info("Edge driver is created");

			/** Using Selenium Grid - to invoke Edge **/

			// capabilities = DesiredCapabilities.edge();
			// capabilities.setBrowserName(BrowserType.EDGE);
			// capabilities.setPlatform(Platform.MAC);
			// EdgeOptions options = new EdgeOptions();
			// options.merge(capabilities);
			// driver = new RemoteWebDriver(new
			// URL("http://192.168.0.25:"+port+"/wd/hub"), options);

		} else if (browser.equalsIgnoreCase("Android")) {

			/** Using capabilities - to invoke Android **/
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "emulator-5554");
			capabilities.setCapability("platformVerison", "7.0");
			capabilities.setCapability("browserName", "chrome");

			driver = new RemoteWebDriver(new URL("http://192.168.0.25:" + port + "/wd/hub"), capabilities);
			Log.info("Android driver is created");
		} else if (browser.equalsIgnoreCase("ios")) {

			/** Using capabilities - to invoke Android **/
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "iphone");
			capabilities.setCapability("browserName", "safari");
			capabilities.setCapability("platformVerison", "11.1");
			capabilities.setCapability("automationName", "XCUITest");
			// capabilities.setCapability("app", "");
			// capabilities.setCapability("autoAcceptAlerts", true);
			// capabilities.setCapability("waitForAppScript", true);
			// capabilities.setCapability("app", "app-path");
			// capabilities.setCapability("bundleId", bundleId);
			// capabilities.setCapability("udid",Constants.UDID);

			driver = new RemoteWebDriver(new URL("http://192.168.0.25:" + port + "/wd/hub"), capabilities);
			Log.info("ios driver is created");
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		if (!browser.equalsIgnoreCase("ios") & !browser.equalsIgnoreCase("ios")) {
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		}
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
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

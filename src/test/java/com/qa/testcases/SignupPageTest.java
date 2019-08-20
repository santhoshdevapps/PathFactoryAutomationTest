package com.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.page.HomePage;
import com.qa.page.LoginPage;
import com.qa.page.SignUpPage;
import com.qa.utils.Constants;
import com.qa.utils.ExcelUtils;
import com.qa.utils.Log;

public class SignupPageTest extends TestBase {

	WebDriver driver;
	WebDriverWait wait;
	LoginPage loginPage;
	HomePage homePage;
	SignUpPage signupPage;
	ExcelUtils excelUtils;
	String registeredEmail = null;
	String password = null;

	@BeforeMethod
	@Parameters({ "browser" }) // , "deviceName", "osVersion", "port"
	public void setUp(String browser) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("New Test Case Started");
		// ,String deviceName, String osVersion, String port
		// initialization(browser, deviceName, osVersion, port);
		initialization(browser);
		this.driver = getDriver();
		this.wait = getWait();
		loginPage = new LoginPage(driver, wait);
		homePage = new HomePage(driver, wait);
		signupPage = new SignUpPage(driver, wait);
		excelUtils = new ExcelUtils();
		homePage.clickSignIn();
	}

	@Test(priority = 1)
	public void signUpScreenTestcase() throws Exception {

		XSSFSheet excelWSheet = excelUtils.setExcel(Constants.FILE_PATH + Constants.FILE_NAME, Constants.SHEETNAME);
		int rowCount = excelWSheet.getLastRowNum() - excelWSheet.getFirstRowNum();

		for (int i = 1; i <= rowCount; i++) {

			String username = excelUtils.readExcel(i, 3);
			loginPage.createAccount(username);
			registeredEmail = excelUtils.readExcel(i, 3);
			password = excelUtils.readExcel(i, 4);

			signupPage.signup(excelUtils.readExcel(i, 0), excelUtils.readExcel(i, 1), excelUtils.readExcel(i, 2),
					excelUtils.readExcel(i, 3), excelUtils.readExcel(i, 4), excelUtils.readExcel(i, 5),
					excelUtils.readExcel(i, 6), excelUtils.readExcel(i, 7), excelUtils.readExcel(i, 8),
					excelUtils.readExcel(i, 9), excelUtils.readExcel(i, 10), excelUtils.readExcel(i, 11),
					excelUtils.readExcel(i, 12), excelUtils.readExcel(i, 13), excelUtils.readExcel(i, 14),
					excelUtils.readExcel(i, 15));

		}
		Log.info("Account created");
	}

	@AfterMethod
	public void tearDown() {
		Log.startTestCase("Test Case Ended");
		driver.quit();
	}
}

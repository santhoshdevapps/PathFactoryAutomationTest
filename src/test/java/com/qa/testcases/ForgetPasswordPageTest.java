package com.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.page.ForgetPasswordPage;
import com.qa.page.HomePage;
import com.qa.page.LoginPage;
import com.qa.page.SignUpPage;
import com.qa.utils.ExcelUtils;
import com.qa.utils.Log;

public class ForgetPasswordPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	SignUpPage signupPage;
	ExcelUtils excelUtils;
	ForgetPasswordPage forgotPasswordPage;
	WebDriver driver;
	WebDriverWait wait;
		
	@Parameters({"browser","port"})
	@BeforeMethod
	public void setUp(String browser, String port) throws Exception{
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("New Test Case Started");
		initialization(browser,port);
		this.driver = getDriver();
		this.wait = getWait();	
		homePage = new HomePage(driver, wait);
		loginPage = new LoginPage(driver, wait);
		forgotPasswordPage = new ForgetPasswordPage(driver, wait);
		homePage.clickSignIn();
		loginPage.clickForgetPasswordLink();
	}

	/**
	 * Verify that user is redirected to Forgot password page when clicking on Forgot Password link
	*/
	@Test(priority=1)
	public void redirectedToForgotPasswordPage(){
		driver.navigate().back();
		loginPage.clickForgetPasswordLink();
		String expectedUrl = driver.getCurrentUrl();
		AssertJUnit.assertEquals(expectedUrl, "http://automationpractice.com/index.php?controller=password");
	}

	/**
	* this method checks for valid username(email) format
	* unregistered username
	 * @throws IOException 
	*/
	@Test(priority=2)
	public void unregisteredUsername() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		forgotPasswordPage.getForgetPassword(testcaseName);
	    String errorText = forgotPasswordPage.getErrorText();
	    AssertJUnit.assertEquals(errorText, "There is no account registered for this email address.");
	}

	/**
	* this method checks for valid username(email) format
	* invalid username (ie incorrect email format)
	 * @throws IOException 
	*/
	@Test(priority=3)
	public void invalidUsername() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		forgotPasswordPage.getForgetPassword(testcaseName);
	    String errorText = forgotPasswordPage.getErrorText();
	    AssertJUnit.assertEquals(errorText, "Invalid email address.");
	}

	/**
	* this method checks for valid username(email) format
	*  valid username
	 * @throws IOException 
	*/
	@Test(priority=4)
	public void validUsername() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		forgotPasswordPage.getForgetPassword(testcaseName);
		String username = forgotPasswordPage.getUsername(testcaseName);
	    String successText = forgotPasswordPage.getSuccessText();
	    AssertJUnit.assertEquals(successText, "A confirmation email has been sent to your address: "+username);
	}

	/**
	* this method checks for empty username
	*  empty username
	 * @throws IOException 
	*/
	@Test(priority=5)
	public void emptyUsername() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		forgotPasswordPage.getForgetPassword(testcaseName);
	    String errorText = forgotPasswordPage.getErrorText();
	    AssertJUnit.assertEquals(errorText, "Invalid email address.");
	}

	// Below testcase will be implemented in future  
	
	/**
	* After changing the password, this method checks for login with old password
	* valid username
	* old password
	*/
	@Test(priority=6,enabled=false)
	public void oldPasswordLogin(){

	}


	/**
	* After changing the password, this method tchecks for login with new password
	* valid username
	* new valid password
	*/
	@Test(priority=7,enabled=false)
	public void newPasswordLogin(){

	}
	
	@AfterMethod
	public void tearDown(){
		Log.startTestCase("Test Case Ended");
		driver.quit();
	}
}

package com.qa.page;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utils.Constants;
import com.qa.utils.ExcelUtils;
import com.qa.utils.Log;

/**
 * This class contains elements in Home Page.
 */
public class ForgetPasswordPage {
    By email = By.cssSelector("input[id='email']");
    By retrievePassword = By.cssSelector("#form_forgotpassword .button");
    By errorAlert = By.cssSelector(".alert.alert-danger ol li");
    By successAlert = By.cssSelector(".alert.alert-success");

	WebDriver driver = null;
	WebDriverWait wait = null;
    ExcelUtils excelUtils;

	// Initializing web drivers
	public ForgetPasswordPage(WebDriver driver, WebDriverWait wait) {
		Log.info("Initializing web drivers");
		this.driver = driver;
		this.wait = wait;
		excelUtils = new ExcelUtils();
	}

	 // Set the email in the email inputbox
    public void setEmail(String email) {
    	Log.info("Set the email in the input box");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.email))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.email))).sendKeys(email);

    }
	// Click Retrieve password button
	public void clickRetrievePassword() {
		Log.info("Clicking on the retrieve passowrd button");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.retrievePassword))).click();

	}
	
	// this method Retrieve password 
		public void retrievePassword(String email) {
			Log.info("Retrieve password method is called");
			setEmail(email);
			clickRetrievePassword();
		}
	

    /**
        * to get the error message displayed
        * @return string
    */
    public String getErrorText() {
    	Log.info("Fetching the error message");
        String ActualData = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.errorAlert))).getText();
        return ActualData;

    }
    
    /**
     * to get the error message displayed
     * @return string
 */
	public String getSuccessText() {
		Log.info("Fetching the success message");
		String ActualData = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.successAlert)))
				.getText();
		return ActualData;

	}
    
    /**
     * Check all possible tests for the forget password process
     * testcaseName - pass all possible values of testcaseName from the excel sheets
     */
	public void getForgetPassword(String testcaseName) throws IOException {
		Log.info("Forget password method: " + testcaseName + " is invoked");
		String username;

		HashMap<String, String> credential = excelUtils.getdata(testcaseName, Constants.SHEETNAME_FORGET);
		username = credential.entrySet().iterator().next().getKey();
		retrievePassword(username);

	}

     /**
      * this method takes testcase name and return the username
      * testcaseName - pass all possible values of testcaseName from the excel sheets
      */
	public String getUsername(String testcaseName) throws IOException {
		Log.info("Returning the username");
		String username;

		HashMap<String, String> credential = excelUtils.getdata(testcaseName, Constants.SHEETNAME_FORGET);
		username = credential.entrySet().iterator().next().getKey();
		return username;
	}

}

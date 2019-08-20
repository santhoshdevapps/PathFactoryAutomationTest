package com.qa.page;
import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.utils.Constants;
import com.qa.utils.ExcelUtils;
import com.qa.utils.Log;

/**
 * This class contains elements in Login Page.
 */

public class LoginPage {

    By email = By.id("email_create");
    By createAccount = By.id("SubmitCreate");
    By registeredEmail = By.id("email");
    By password = By.id("passwd");
    By signInBtn = By.id("SubmitLogin");
    By productLogo = By.className("logo");
    By errorAlert = By.cssSelector(".alert.alert-danger ol li");
    By pageHeader = By.className("page-subheading");
    By emailLabel = By.className("page-subheading");
    By passwordLabel = By.className("page-subheading");
    By forgetPwdLink = By.cssSelector(".lost_password a");
    WebDriver driver = null;
    WebDriverWait wait = null;
    ExcelUtils excelUtils;

        // Initializing web drivers
    public LoginPage(WebDriver driver, WebDriverWait wait) {
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

    // Click on the create account button
    public void clickCreateAccount() {
		Log.info("Clicking the create account button");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.createAccount))).click();

    }

    // Set registered email in the inputbox
    public void setRegisteredEmail(String email) {
		Log.info("Setting the registered email" + email);

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.registeredEmail))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.registeredEmail))).sendKeys(email);

    }

    // Set the password in the password inputbox
    public void setPassword(String password) {
		Log.info("Setting the password");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).sendKeys(password);

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
     * to get the message displayed
     * @return string
     */
	public String getText() {
		
		String ActualData = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.pageHeader)))
				.getText();
		return ActualData;

	}

    // Click SignIn button to navigate to the dashboard page
    public void clickSignIn() {
    	
		Log.info("Clicking the sign in button");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.signInBtn))).click();

    }
    
    // Click forget password link to navigate to the forget password page
    public void clickForgetPasswordLink() {
    	
		Log.info("Clicking the Forgot password link");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.forgetPwdLink))).click();

    }
    
    // SignIn using Enter key to navigate to the dashboard page
    public void signInUsingEnterKey() {
    	
		Log.info("Enter key submit form");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).sendKeys(Keys.ENTER);

    }

    // Verify that the right logo is displayed
    public boolean verifyLogo() {
    	
		Log.info("Verifying the logo");
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.productLogo))).isDisplayed();

    }

    // Verify that the right logo is displayed
    public boolean verifyUIElements() {
    	
		Log.info("Verifying the UI Elements");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.pageHeader))).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.emailLabel))).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.passwordLabel))).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.forgetPwdLink))).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.signInBtn))).isDisplayed();
        
        return true;

    }
    
    
    // To check for the type of password (ie to verify that the password is displayed in bullet or asterisk)
    public String verifyPasswordVisibility() {
    	
		Log.info("Check for password visibility (bullet or asterisk)");
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).getAttribute("type");

    }

    // To check for the title of the displayed in the browser
    public String validateLoginPageTitle(){
    	
		Log.info("Validating the page title");
        return driver.getTitle();

    }
    
    /**
    * Check the process of login
    * setRegisteredEmail - to verify that the email is registered and valid
    * setPassword - to verify that the correct password is entered
    * PressEnter - Press enter key to login
     * @throws IOException 
    */
    public void loginUsingEnterKey(String testcaseName) throws IOException {
    	
		Log.info("Login process after enter key is pressed");

    	 String username;
         String password;

         HashMap<String, String> credential = excelUtils.getdata(testcaseName,Constants.SHEETNAME_LOGIN);
         username = credential.entrySet().iterator().next().getKey();                
         password = credential.entrySet().iterator().next().getValue();              
        setRegisteredEmail(username);
        setPassword(password);
        signInUsingEnterKey();

    }
    
    /**
    * Check all possible tests for the login process
    * username - pass all possible values of username from the excel sheets
    * password - pass all possible values of password from the excel sheets
    * login - login flow
    */
    public void login(String testcaseName) throws IOException{
    	
		Log.info("Login method");
        String username;
        String password;

        HashMap<String, String> credential = excelUtils.getdata(testcaseName,Constants.SHEETNAME_LOGIN);
        username = credential.entrySet().iterator().next().getKey();                
        password = credential.entrySet().iterator().next().getValue();              
        login(username, password);

    }

    /**
    * Check the process of create account
    * setEmail - to verify that the email is valid
    * clickCreateAccount - click on the create account button after setting the email
    */
    public void createAccount(String email) {
    	
		Log.info("Create account");
        setEmail(email);
        clickCreateAccount();

    }

    /**
    * Check the process of login
    * setRegisteredEmail - to verify that the email is registered and valid
    * setPassword - to verify that the correct password is entered
    * clickSignIn - click on the sign in button after validating the above two functions
    */
    public void login(String email, String password) {
    	
        setRegisteredEmail(email);
        setPassword(password);
        clickSignIn();

    }

}
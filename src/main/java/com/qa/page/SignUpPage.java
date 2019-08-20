package com.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utils.Log;

/**
 * This class contains elements in Signin Page.
 */

public class SignUpPage {

	WebDriver driver;
	WebDriverWait wait;
	By genderMale = By.id("uniform-id_gender1");
	By genderFemale = By.id("uniform-id_gender2");
	By fname = By.id("customer_firstname");
	By lname = By.id("customer_lastname");
	By email = By.id("email");
	By password = By.id("passwd");
	By date = By.id("passwd");
	By company = By.id("company");
	By address = By.id("address1");
	By city = By.id("city");
	By state = By.id("id_state");
	By postal = By.cssSelector("#postcode");
	By country = By.cssSelector("#id_country");
	By additionalInfo = By.id("other");
	By homePhone = By.id("phone");
	By mobilePhone = By.id("phone_mobile");
	By addressAlias = By.id("alias");
	By registerBtn = By.id("submitAccount");

	// Initializing the driver,wait:
	public SignUpPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	// Set the title radio button
	public void setTitle(String title) {
		
		Log.info("Check for salutation (Mr or Mrs)");
		if (title.equalsIgnoreCase("Mr")) {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.genderMale))).click();
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.genderFemale))).click();
		}

	}

	// Set the first name in input box
	public void setFirstName(String firstName) throws InterruptedException {
		
		Log.info("Set the first name in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.fname))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.fname))).sendKeys(firstName);
	}

	// Set the last name in input box
	public void setLasttName(String lastName) throws InterruptedException {
		
		Log.info("Set the last name in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.lname))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.lname))).sendKeys(lastName);
	}

	// Set the email in email input box
	public void setEmail(String email) {
		
		Log.info("Set the email in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.email))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.email))).sendKeys(email);
	}

	// Set the password in password input box
	public void setPasword(String password) {
		
		Log.info("Set the password in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).sendKeys(password);

	}

	/**
	 * Set the dob from the dropdown Get the date dd/mm/yyyy format and split it
	 * using "/" to get the days, month and years seperately
	 * 
	 * @param date
	 */
	public void setDob(String date) {

		Log.info("Set the dob");
		String[] dob = date.split("/");
		Select selectDay = new Select(driver.findElement(By.id("days")));
		Select selectMonth = new Select(driver.findElement(By.id("months")));
		Select selectYear = new Select(driver.findElement(By.id("years")));

		selectDay.selectByValue(dob[0]);
		selectMonth.selectByValue(dob[1]);
		selectYear.selectByValue(dob[2]);

	}

	// Set the company in input box
	public void setCompany(String companyName) throws InterruptedException {
		
		Log.info("Set the company in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.company))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.company))).sendKeys(companyName);
	}

	// Set the address in input box
	public void setAddress(String address) throws InterruptedException {
		
		Log.info("Set the address in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.address))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.address))).sendKeys(address);
	}

	// Set the city in input box
	public void setCity(String city) throws InterruptedException {
		
		Log.info("Set the city in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.city))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.city))).sendKeys(city);
	}

	// Set the state from the dropdown
	public void setState(String state) {

		Log.info("Set the state in the input box");
		Select selectState = new Select(driver.findElement(this.state));
		selectState.selectByVisibleText(state);

	}

	// Set the Postal code in input box
	public void setPostalCode(String postalCode) throws InterruptedException {
		
		Log.info("Set the postal code in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.postal))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.postal))).sendKeys(postalCode);
	}

	// Set the country from the dropdown
	public void setCountry(String country) {

		Log.info("Set the country");
		Select selectState = new Select(driver.findElement(this.country));
		selectState.selectByVisibleText(country);

	}

	// Set the additional information in the text box if needed (Additional Info
	// - optional)
	public void setAdditionalInfo(String additionalInfo) throws InterruptedException {
		
		Log.info("Set the additional information in the text box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.additionalInfo))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.additionalInfo)))
				.sendKeys(additionalInfo);
	}

	// Set the home phone number in input box
	public void setHomePhone(String homePhone) throws InterruptedException {
		
		Log.info("Set the phone number in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.homePhone))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.homePhone))).sendKeys(homePhone);
	}

	// Set the mobile phone number in input box
	public void setMobilePhone(String mobilePhone) throws InterruptedException {
		
		Log.info("Set the mobile number in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.mobilePhone))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.mobilePhone))).sendKeys(mobilePhone);
	}

	// Set the address in the input box
	public void setAddressAlias(String addressAlias) throws InterruptedException {
		
		Log.info("Set the address alias in the input box");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.addressAlias))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.addressAlias)))
				.sendKeys(addressAlias);
	}

	// Click the register button after filling the form details
	public void clickRegister() {
		
		Log.info("Clicking the register button");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.registerBtn))).click();
	}

	/**
	 * this method takes in all the below mentioned param to create an account
	 * successfully
	 * 
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param date
	 * @param companyName
	 * @param address
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param country
	 * @param additionalInfo
	 * @param homePhone
	 * @param mobilePhone
	 * @param addressAlias
	 * @throws InterruptedException
	 */
	public void signup(String title, String firstName, String lastName, String email, String password, String date,
			String companyName, String address, String city, String state, String postalCode, String country,
			String additionalInfo, String homePhone, String mobilePhone, String addressAlias)
			throws InterruptedException {
		
		Log.info("Sign up method");
		this.setTitle(title);
		this.setFirstName(firstName);
		this.setLasttName(lastName);
		this.setEmail(email);
		this.setPasword(password);
		this.setDob(date);
		this.setCompany(companyName);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setPostalCode(postalCode);
		this.setCountry(country);
		this.setAdditionalInfo(additionalInfo);
		this.setHomePhone(homePhone);
		this.setMobilePhone(mobilePhone);
		this.setAddressAlias(addressAlias);
		this.clickRegister();
	}

}

package com.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utils.Log;

/**
 * This class contains elements in Home Page.
 */

public class HomePage {
	By signIn = By.xpath("//a[@class='login']");
	WebDriver driver = null;
	WebDriverWait wait = null;

	// Initializing web drivers
	public HomePage(WebDriver driver, WebDriverWait wait) {
		Log.info("Initializing web drivers");
		this.driver = driver;
		this.wait = wait;
	}

	// Click SignIn button to navigate to the login page
	public void clickSignIn() {
		Log.info("Clicking the sign in button");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.signIn))).click();

	}

}

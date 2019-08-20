package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import com.qa.base.TestBase;


public class TestListener implements ITestListener {

	public void onFinish(ITestContext status) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext status) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult status) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult status) {
		Log.info("The name of the testcase failed is :"+status.getName());
		captureScreenShot(status, "FAIL");
		
	}

	public void onTestSkipped(ITestResult status) {
		Log.info("The name of the testcase skipped is :"+status.getName());
		captureScreenShot(status, "SKIP");
		
	}

	public void onTestStart(ITestResult status) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult status) {
		Log.info("The name of the testcase passed is :"+status.getName());
		captureScreenShot(status, "PASS");
		
	}
	
	private void captureScreenShot(ITestResult status, String isTestPassed) {	
		
		Log.info("Capturing the screenshot");
		String destDir ="";
		String passfailMethod = status.getMethod().getRealClass().getSimpleName() + "."
				+ status.getMethod().getMethodName();
		File scrfile =((TakesScreenshot)TestBase.driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateformat = new SimpleDateFormat(Constants.DATE_FORMAT_TYPE);
		if (isTestPassed.equals("PASS")) {
			destDir = System.getProperty(Constants.USER_DIRECTORY)+Constants.SUCCESS_SCREENSHOT_PATH;
		} else if (isTestPassed.equals("FAIL")) {
			destDir = System.getProperty(Constants.USER_DIRECTORY)+Constants.FAILURE_SCREENSHOT_PATH;
		} else if (isTestPassed.equals("SKIP")) {
			destDir = System.getProperty(Constants.USER_DIRECTORY)+Constants.SKIP_SCREENSHOT_PATH;
		}
		new File(destDir).mkdirs();
		String destFile = passfailMethod + " - " + dateformat.format(new Date()) + Constants.IMAGE_FORMAT_TYPE;
		try {
			FileUtils.copyFile(scrfile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
}

}

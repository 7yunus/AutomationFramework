package org.example.sedin.runner;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import org.apache.commons.io.FileUtils;
import org.example.sedin.configuration.DriverSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerClass extends DriverSetup implements ITestListener {
	private static String getTestMethodName(ITestResult iTest) {
		return iTest.getMethod().getConstructorOrMethod().getName();
	}


	@Attachment (value = "screenshot", type = "image/png")
	public static File screenshot (WebDriver driver) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		Date date = new Date();
		String fileName = sdf.format(date);
		File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File(System.getProperty("user.dir")+"/target/allure-results/"+fileName+".png"));
		Allure.addAttachment("Screenshot attached ", FileUtils.openInputStream(SrcFile));
		return SrcFile;

	}

	@Attachment (value = "attachment", type = "text/plain")
	public static File seleniumLog (WebDriver driver) throws IOException  {
		File seleniumLog = new File("C://Eclipse//PageObjectModel//log//selenium.log");
		FileUtils.copyFile(seleniumLog, new File(System.getProperty("user.dir")+"//allure-results//selenium"+".log"));
		Allure.addAttachment("Selenium Log attached ", FileUtils.openInputStream(seleniumLog));
		return seleniumLog;
	}


	@Attachment (value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		StringBuilder str=getConsoleLogs();
		Allure.addAttachment("Text Captured from Console ",(message+str));
		return (message+str);
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println(iTestContext.getName() + "on start");
		iTestContext.setAttribute("WebDriver", driver);
	}
//	@Override
//	public void onFinish(ITestContext iTestContext) {
//		System.out.println(iTestContext.getName() + "on Finish");
//	}
//	@Override
//	public void onTestStart(ITestResult iTest) {
//		System.out.println(getTestMethodName(iTest) +"on Test Start");
//	}
//	@Override
//	public void onTestSuccess(ITestResult iTest) {
//		System.out.println(getTestMethodName(iTest) +"on Test Success");
//		if (driver != null) {
//			try {
//				screenshot(driver);
//				seleniumLog(driver);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			saveTextLog("Passed Screenshot captured");
//		}
//	}
	//Logger logger ;
	@Override
	public void onTestFailure(ITestResult iTest) {
		System.out.println(getTestMethodName(iTest) +"on Test Failure");
		if (driver != null) {
			try {
				screenshot(driver);
				seleniumLog(driver);
			} catch (IOException e) {
				e.printStackTrace();
			}
			saveTextLog("Failed Screenshot captured");

		}
	}
	@Override
	public void onTestSkipped(ITestResult iTest) {
		System.out.println(getTestMethodName(iTest));
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTest) {
		System.out.println(getTestMethodName(iTest));
	}

	public static StringBuilder getConsoleLogs() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		StringBuilder consolelogs = new StringBuilder();

		for (org.openqa.selenium.logging.LogEntry entry : logEntries) {
			consolelogs.append(new Date(entry.getTimestamp()) + " "
					+ entry.getLevel() + " " + entry.getMessage());
			consolelogs.append(System.lineSeparator());

		}
		return consolelogs;
	}

}


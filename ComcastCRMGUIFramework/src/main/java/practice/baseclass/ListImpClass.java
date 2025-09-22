package practice.baseclass;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener {
	ExtentReports report;
	ExtentTest test;
	String testName;
	String time = new Date().toString().replace(" ", "_").replace(":", "_");

	public void onStart(ISuite suite) {
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancePracticeReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM");
		spark.config().setReportName("Organization module");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "window 11");
		report.setSystemInfo("browser", "chrome");
	}

	public void onFinish(ISuite suite) {
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		testName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, testName+" get started");
	}
	
	public void OnTestFailure(ITestResult result) {
		TakesScreenshot tks = (TakesScreenshot)UtilityClassObject.getDriver();
		String filePath = tks.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filePath+"_"+time);
	}
}

package Listeners_Utility;

import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseclassUtility.Baseclass;
import GenericUtilities.ClassObject_Utility;

public class Listeners implements ITestListener, ISuiteListener {

	public ExtentReports report = null;
	public static ExtentTest test;

	// Onstart we generate report
	@Override
	public void onStart(ISuite suite) {

		// To see the Output in report and console
		Reporter.log("Report confguration", true);
		// To see the Output in Advance report
		//test.log(Status.INFO, "On Suite Execution Started");

		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		// Configuration the report
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReoprt/Adv_report" + time + ".html");
		spark.config().setDocumentTitle("VTiger Suite Execution Report");
		spark.config().setReportName("VTiger Report");
		spark.config().setTheme(Theme.DARK);

		// Set Environment configurations
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS version", "Windows-10");
		report.setSystemInfo("Browser version", "Chrome-135");
		test = report.createTest("VTiger Runtime Events");
		ClassObject_Utility.setTest(test);
	}

	// Onfinish we print the report
	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report Backup", true);
		report.flush();
		test.log(Status.INFO, "Suite Execution Finished");

	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "--Started--", true);
		test.log(Status.INFO, result.getMethod().getMethodName() + "--Started--");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "--Success--", true);
		test.log(Status.INFO, result.getMethod().getMethodName() + "--Success--");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "--Failed--", true);
		test.log(Status.FAIL, testname + "--Failed--");

		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot ts = (TakesScreenshot) Baseclass.sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath, testname + time + ".png");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "--Skipped--", true);
		test.log(Status.INFO, result.getMethod().getMethodName() + "--Skipped--");
	}

}

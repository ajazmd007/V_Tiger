package BaseclassUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;

import GenericUtilities.ClassObject_Utility;
import GenericUtilities.DataBase_Utility;
import GenericUtilities.Property_Utility;
import GenericUtilities.WebDriver_Utility;
import POMPages.HomePomPage;
import POMPages.LoginPomPage;

public class Baseclass {
	// Declaring Globally
	//Changes done in git 

	Property_Utility pro = new Property_Utility();
	DataBase_Utility db = new DataBase_Utility();
	WebDriver_Utility w_util = new WebDriver_Utility();
	public WebDriver driver = null;

	public static WebDriver sdriver = null;

	// @Parameters
	@BeforeSuite(groups = { "Smoke", "Regression" })
	public void beforeSuite() throws IOException {
		// Print the message in report and console
		Reporter.log("Configure the DB:Connect", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Configure the DB:Connect");
		db.getDataBaseConnection();

	}

	@BeforeTest(groups = { "Smoke", "Regression" })
	public void beforeTest() {
		Reporter.log("BT:Parallel Exe", true);
		ClassObject_Utility.getTest().log(Status.INFO, "BT:Parallel Exe");

	}

	@BeforeClass(groups = { "Smoke", "Regression" })
	public void beforeClass() throws IOException {
		Reporter.log("Launch the Browser", true);
		String Browser = System.getProperty("browser",pro.FetchDataFromPropFile("browser"));
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		sdriver = driver;
		ClassObject_Utility.setDriver(driver);

	}

	@BeforeMethod(groups = { "Smoke", "Regression" })
	public void beforeMethod() throws IOException {
		Reporter.log("Login to the appln", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Login to the appln");
		// String URL = pro.FetchDataFromPropFile("URL");
		// String Username = pro.FetchDataFromPropFile("Username");
		// String Password = pro.FetchDataFromPropFile("Password");

		String URL = System.getProperty("URL", pro.FetchDataFromPropFile("URL"));
		String Username = System.getProperty("Username", pro.FetchDataFromPropFile("Username"));
		String Password = System.getProperty("Password", pro.FetchDataFromPropFile("Password"));

		LoginPomPage l = new LoginPomPage(driver);
		w_util.navigateToAnAppln(driver, URL);
		w_util.maximizeTheWindow(driver);
		l.login(Username, Password);
		String Timeouts = pro.FetchDataFromPropFile("timeouts");
		w_util.waitTillElementFounds(Timeouts, driver);

	}

	@AfterMethod(groups = { "Smoke", "Regression" })
	public void afterMethod() {
		Reporter.log("Logout of the appln", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Logout to the appln");
		HomePomPage home = new HomePomPage(driver);
		home.logout(driver);

	}

	@AfterClass(groups = { "Smoke", "Regression" })
	public void afterClass() {
		Reporter.log("Close the browser", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Close the browser");
		WebDriver_Utility wb = new WebDriver_Utility();
		wb.QuitTheBrowser(driver);
	}

	@AfterTest(groups = { "Smoke", "Regression" })
	public void afterTest() {
		Reporter.log("AT:Parallel Distributed Exe", true);
		ClassObject_Utility.getTest().log(Status.INFO, "AT:Parallel Distributed Exe");

	}

	@AfterSuite(groups = { "Smoke", "Regression" })
	public void afterSuite() {
		Reporter.log("Close DB connection", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Close DB connection");
		db.closeDataBaseConnection();
	}

}

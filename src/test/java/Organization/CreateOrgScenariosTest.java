package Organization;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ClassObject_Utility;
import GenericUtilities.Excel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.WebDriver_Utility;
import POMPages.CreateNewOrgPompage;
import POMPages.HomePomPage;
import POMPages.OrgDetailPomPage;
import POMPages.OrganizationPomPage;
import junit.framework.Assert;

@Listeners(Listeners_Utility.Listeners.class)
public class CreateOrgScenariosTest extends Baseclass {

	@Test(groups = "smoke", retryAnalyzer = Listeners_Utility.RetryAnalyzer_utility.class)

	public void createOrgTest() throws Exception {

		// Fetch data from Property Utility file we are using from "Baseclass"

		WebDriver_Utility w_util = new WebDriver_Utility();
		// Fetch data from Excel Utility
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch Data From Excel File");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organization", 1, 2) + random;

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		ClassObject_Utility.getTest().log(Status.INFO, "Home page verification");
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Organization Tab");
		home.getOrganization();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Create New Organization page");
		OrganizationPomPage Org = new OrganizationPomPage(driver);
		Org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating New Organization");
		CreateNewOrgPompage neworg = new CreateNewOrgPompage(driver);
		neworg.getOrgname_TF(orgname);
		neworg.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying Organization");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean expected_results_org = orgdetail.getHeader().contains(orgname);
		Assert.assertEquals(expected_results_org, true);

		// click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Organization tab and delete Organization");
		home.getOrganization();
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// handle the pop up
		ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application and quiting the browser using from "Baseclass"

	}

	@Test(groups = "regression", retryAnalyzer = Listeners_Utility.RetryAnalyzer_utility.class)

	public void CreateOrgWithIndTypeTest() throws IOException, InterruptedException {

		// Fetch data from Property Utility file using from "Baseclass

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from Excel Utility
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch Data From Excel File");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organization", 9, 2) + random;
		String industry = ex_util.FetchDataFromExcelFile("Organization", 9, 3);
		String type = ex_util.FetchDataFromExcelFile("Organization", 9, 4);

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		ClassObject_Utility.getTest().log(Status.INFO, "Home page verification");
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Organization Tab");
		home.getOrganization();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Create New Organization page");
		OrganizationPomPage Org = new OrganizationPomPage(driver);
		Org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating New Organization with Industry and Type");
		CreateNewOrgPompage neworg = new CreateNewOrgPompage(driver);
		neworg.getOrgname_TF(orgname);
		WebElement IndustryDropDown = neworg.getOrgIndustryDD();
		WebElement TypeDropDown = neworg.getOrgTypeDD();
		w_util.HandleDropdownUsingValue(IndustryDropDown, industry);
		w_util.HandleDropdownUsingValue(TypeDropDown, type);
		neworg.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying Organization");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean expected_results_org = orgdetail.getHeader().contains(orgname);
		Assert.assertEquals(expected_results_org, true);

//		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
//		String header = orgdetail.getHeader();
//		if (header.contains(orgname)) {
//			Reporter.log(orgname + "Test Pass", true);
//		} else {
//			Reporter.log("org not created", true);
//		}

		// Verify actual Industry with expected industry
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying Industry");
		boolean actind = orgdetail.getVerifyIndustry().contains(industry);
		Assert.assertEquals(actind, true);

//		if (actind.equals(industry)) {
//			Reporter.log(industry + " : selected proper industry", true);
//		} else {
//			Reporter.log(industry + " : not proper industry", true);
//		}

		// Verify actual type with expected
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying Type");
		boolean acttype = orgdetail.getVerifyType().contains(type);
		Assert.assertEquals(acttype, true);

//		if (acttype.equals(type)) {
//			Reporter.log(type + " : selected proper type", true);
//		} else {
//			Reporter.log(type + " : not proper type", true);
//
//		}

		// click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Organization tab and delete Organization");
		home.getOrganization();
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// handle the pop up
		ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application using from "Baseclass"

		// close the browser using from "Baseclass"

	}

	@Test(groups = "regression", retryAnalyzer = Listeners_Utility.RetryAnalyzer_utility.class)

	public void CreateOrgWithPNTest() throws IOException, InterruptedException {

		// Fetch data from property utility file using from "Baseclass"

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from excel utility file
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch Data From Excel File");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organization", 5, 2) + random;
		String number = ex_util.FetchDataFromExcelFile("Organization", 5, 3) + random;

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		ClassObject_Utility.getTest().log(Status.INFO, "Home page verification");
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Organization Tab");
		home.getOrganization();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Create New Organization page");
		OrganizationPomPage Org = new OrganizationPomPage(driver);
		Org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating New Organization with Phone number");
		CreateNewOrgPompage newcon = new CreateNewOrgPompage(driver);
		newcon.getOrgname_TF(orgname);
		newcon.getOrgphno_TF(number);
		newcon.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying Organization");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean expected_results_org = orgdetail.getHeader().contains(orgname);
		Assert.assertEquals(expected_results_org, true);

		// Verify actual phno with expected phn no
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying Phone Number");
		boolean phtf = orgdetail.getVerifyOrgPhno().contains(number);
		Assert.assertEquals(phtf, true);

		// click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Organization tab and delete Organization");
		home.getOrganization();
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// handle the pop up
		ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application and quite the browser using from "Baseclass"

	}
}

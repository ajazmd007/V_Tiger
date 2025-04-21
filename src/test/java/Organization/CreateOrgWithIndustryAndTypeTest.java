package Organization;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseclassUtility.Baseclass;
import GenericUtilities.Excel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.WebDriver_Utility;
import POMPages.CreateNewOrgPompage;
import POMPages.HomePomPage;
import POMPages.OrgDetailPomPage;
import POMPages.OrganizationPomPage;
import junit.framework.Assert;

public class CreateOrgWithIndustryAndTypeTest extends Baseclass {

	// @Parameters("BROWSERS") using from "Baseclass"
	@Test(groups = "regression")

	public void CreateOrgWithIndTypeTest() throws IOException, InterruptedException {

		// Fetch data from Property Utility file using from "Baseclass

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from Excel Utility
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organization", 9, 2) + random;
		String industry = ex_util.FetchDataFromExcelFile("Organization", 9, 3);
		String type = ex_util.FetchDataFromExcelFile("Organization", 9, 4);

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify org tab in home page and click
		home.getOrganization();

		// Identify plus button and click
		OrganizationPomPage Org = new OrganizationPomPage(driver);
		Org.getPlusicon();

		// Enter org name in create new org page and save
		CreateNewOrgPompage neworg = new CreateNewOrgPompage(driver);
		neworg.getOrgname_TF(orgname);
		WebElement IndustryDropDown = neworg.getOrgIndustryDD();
		WebElement TypeDropDown = neworg.getOrgTypeDD();
		w_util.HandleDropdownUsingValue(IndustryDropDown, industry);
		w_util.HandleDropdownUsingValue(TypeDropDown, type);
		neworg.getSaveBtn();

		// Verify actual org name with expected org name
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
		boolean actind = orgdetail.getVerifyIndustry().contains(industry);
		Assert.assertEquals(actind, true);

//		if (actind.equals(industry)) {
//			Reporter.log(industry + " : selected proper industry", true);
//		} else {
//			Reporter.log(industry + " : not proper industry", true);
//		}

		// Verify actual type with expected
		boolean acttype = orgdetail.getVerifyType().contains(type);
		Assert.assertEquals(acttype, true);

//		if (acttype.equals(type)) {
//			Reporter.log(type + " : selected proper type", true);
//		} else {
//			Reporter.log(type + " : not proper type", true);
//
//		}

		// click on org tab and delete the created org
		home.getOrganization();
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// handle the pop up
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application using from "Baseclass"

		// close the browser using from "Baseclass"

	}
}
package Organization;

import java.io.IOException;
import org.openqa.selenium.By;
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

public class CreateOrgWithPhoneNumberTest extends Baseclass {

	// @Parameters("BROWSERS") using from "Baseclass"
	@Test(groups = "regression")

	public void CreateOrgWithPNTest() throws IOException, InterruptedException {

		// Fetch data from property utility file using from "Baseclass"

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from excel utility file
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organization", 5, 2) + random;
		String number = ex_util.FetchDataFromExcelFile("Organization", 5, 3) + random;

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
		CreateNewOrgPompage newcon = new CreateNewOrgPompage(driver);
		newcon.getOrgname_TF(orgname);
		newcon.getOrgphno_TF(number);
		newcon.getSaveBtn();

		// Verify actual org name with expected org name
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean expected_results_org = orgdetail.getHeader().contains(orgname);
		Assert.assertEquals(expected_results_org, true);

		// Verify actual phno with expected phn no
		boolean phtf = orgdetail.getVerifyOrgPhno().contains(number);
		Assert.assertEquals(phtf, true);

		// click on org tab and delete the created org
		home.getOrganization();
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// handle the pop up
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application and quite the browser using from "Baseclass"

	}
}

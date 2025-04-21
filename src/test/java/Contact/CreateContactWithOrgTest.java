package Contact;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseclassUtility.Baseclass;
import GenericUtilities.Excel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.WebDriver_Utility;
import POMPages.ContactDetailPomPage;
import POMPages.ContactPomPage;
import POMPages.CreateNewContactPomPage;
import POMPages.CreateNewOrgPompage;
import POMPages.HomePomPage;
import POMPages.OrgDetailPomPage;
import POMPages.OrganizationPomPage;
import junit.framework.Assert;

public class CreateContactWithOrgTest extends Baseclass {

	// @Parameters("BROWSERS") using from "Baseclass"
	@Test(groups = "regression")

	public void createContactOrgNameTest() throws IOException, InterruptedException {

		// Fetch data from Property Utility file using from "Baseclass"

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from Excel Utility
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String Contactname = ex_util.FetchDataFromExcelFile("Contacts", 7, 2) + random;
		String Orgname = ex_util.FetchDataFromExcelFile("Contacts", 7, 3) + random;

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
		neworg.getOrgname_TF(Orgname);
		neworg.getSaveBtn();

		// Verify actual org name with expected org name
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean expected_results_org = orgdetail.getHeader().contains(Orgname);
		Assert.assertEquals(expected_results_org, true);

		// Identify contact tab in home page and click
		home.getContacts();

		// Identify plus button and click
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// Enter contact name in create new org page and save
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(Contactname);

		// Select Org name
		String pwid = driver.getWindowHandle();
		newcon.getOrgplusicon();

		w_util.switchToTabUsingUrl(driver, "module=Accounts&action");

		newcon.getOrgsearchTF(Orgname);
		newcon.getOrgsearchBtn();
		driver.findElement(By.xpath("//a[text()='" + Orgname + "']")).click();
		w_util.switchBackToParentWindow(driver, pwid);
		newcon.getSaveBtn();

		// Verify actual contact name with expected contact name
		ContactDetailPomPage condetail = new ContactDetailPomPage(driver);
		boolean expected_contact_results = condetail.getHeader().contains(Contactname);
		Assert.assertEquals(expected_contact_results, true);


		// Click on contact tab and delete the created org
		home.getContacts();
		driver.findElement(By
				.xpath("//a[text()='" + Contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		w_util.HandleAlertAndAccept(driver);

		// click on org tab and delete the created org
		home.getOrganization();
		driver.findElement(
				By.xpath("//a[text()='" + Orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application and quiting from using from "Baseclass"

	}

}
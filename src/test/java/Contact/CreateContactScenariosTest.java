package Contact;

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
import POMPages.ContactDetailPomPage;
import POMPages.ContactPomPage;
import POMPages.CreateNewContactPomPage;
import POMPages.CreateNewOrgPompage;
import POMPages.HomePomPage;
import POMPages.OrgDetailPomPage;
import POMPages.OrganizationPomPage;
import junit.framework.Assert;

//Check with the branch
//Changes made in 3.0
@Listeners(Listeners_Utility.Listeners.class)
public class CreateContactScenariosTest extends Baseclass {

	@Test(groups = "smoke", retryAnalyzer = Listeners_Utility.RetryAnalyzer_utility.class)

	public void CreateContactNameTest() throws IOException, InterruptedException {

		// Fetch data from Property Utility file using from "Baseclass"

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from Excel Utility
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch Data From Excel File");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String Contactname = ex_util.FetchDataFromExcelFile("Contacts", 1, 2) + random;

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		ClassObject_Utility.getTest().log(Status.INFO, "Home page verification");
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify contact tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Contacts Tab");
		home.getContacts();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Create New Contact page");
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// Enter contact name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating New Contact");
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(Contactname);
		newcon.getSaveBtn();

		// Verify actual contact name with expected contact name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying the Contact");
		ContactDetailPomPage condetail = new ContactDetailPomPage(driver);
		boolean expected_results_contact = condetail.getHeader().contains(Contactname);
		Assert.assertEquals(expected_results_contact, true);

		// Click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to contact tab and delete contact");
		home.getContacts();
		driver.findElement(By
				.xpath("//a[text()='" + Contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application and quiting the browser from Baseclass

	}

	@Test(groups = "regression", retryAnalyzer = Listeners_Utility.RetryAnalyzer_utility.class)
	public void createContactOrgNameTest() throws IOException, InterruptedException {

		// Fetch data from Property Utility file using from "Baseclass"

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from Excel Utility
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch Data From Excel File");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String Contactname = ex_util.FetchDataFromExcelFile("Contacts", 7, 2) + random;
		String Orgname = ex_util.FetchDataFromExcelFile("Contacts", 7, 3) + random;

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying home page");
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Organization tab");
		home.getOrganization();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to create new Organization");
		OrganizationPomPage Org = new OrganizationPomPage(driver);
		Org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Create new Organization");
		CreateNewOrgPompage neworg = new CreateNewOrgPompage(driver);
		neworg.getOrgname_TF(Orgname);
		neworg.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying Organization name");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean expected_results_org = orgdetail.getHeader().contains(Orgname);
		Assert.assertEquals(expected_results_org, true);

		// Identify contact tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Contact tab");
		home.getContacts();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to create new Contact page");
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// Enter contact name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Create new Contact with org name");
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(Contactname);

		// Select Org name
		ClassObject_Utility.getTest().log(Status.INFO, "Select organization name from Child window");
		String pwid = driver.getWindowHandle();
		newcon.getOrgplusicon();

		w_util.switchToTabUsingUrl(driver, "module=Accounts&action");

		newcon.getOrgsearchTF(Orgname);
		newcon.getOrgsearchBtn();
		driver.findElement(By.xpath("//a[text()='" + Orgname + "']")).click();
		w_util.switchBackToParentWindow(driver, pwid);
		newcon.getSaveBtn();

		// Verify actual contact name with expected contact name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying contact name with orgname");
		ContactDetailPomPage condetail = new ContactDetailPomPage(driver);
		boolean expected_contact_results = condetail.getHeader().contains(Contactname);
		Assert.assertEquals(expected_contact_results, true);

		// Click on contact tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to contact tab and delete");
		home.getContacts();
		driver.findElement(By
				.xpath("//a[text()='" + Contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
		w_util.HandleAlertAndAccept(driver);

		// click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to org tab and delete");
		home.getOrganization();
		driver.findElement(
				By.xpath("//a[text()='" + Orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application and quiting from using from "Baseclass"

	}

	@Test(groups = "regression", retryAnalyzer = Listeners_Utility.RetryAnalyzer_utility.class)

	public void CreateContactWthSuppoDateTest() throws IOException, InterruptedException {

		// Fetch data from Property Utility file using from "Baseclass

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Fetch data from Excel Utility
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch Data From Excel File");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String Contactname = ex_util.FetchDataFromExcelFile("Contacts", 4, 2) + random;

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying home page");
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify contact tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Contacts tab");
		home.getContacts();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Create New Contact page");
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// Enter contact name in create new contact page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating New Contact");
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(Contactname);

		// Specify start and end Support date
		ClassObject_Utility.getTest().log(Status.INFO, "Enter Start date");
		WebElement start_dateTF = newcon.getConStartDate_TF();
		start_dateTF.clear();
		String startdate = j_util.getCurrentDate();
		start_dateTF.sendKeys(startdate);

		ClassObject_Utility.getTest().log(Status.INFO, "Enter End date");
		WebElement end_dateTF = newcon.getConEndDate_TF();
		end_dateTF.clear();
		String enddate = j_util.getDateAftergivendays();
		end_dateTF.sendKeys(enddate);
		newcon.getSaveBtn();

		// Verify actual contact name with expected contact name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying contact name with expected contact name");
		ContactDetailPomPage condetail = new ContactDetailPomPage(driver);
		boolean expected_contact_results = condetail.getHeader().contains(Contactname);
		Assert.assertEquals(expected_contact_results, true);

		// Verify start supp date end support date using Assert
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying start date with actual start date");
		boolean actstrtdate = condetail.getVerifyStartdate().contains(startdate);
		Assert.assertEquals(actstrtdate, true);

		// Verify end supp date end support date using Assert
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying end date with actual end date");
		boolean actenddate = condetail.getVerifyEnddate().contains(enddate);
		Assert.assertEquals(actenddate, true);

		// Click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to org tab and delete");
		home.getContacts();
		driver.findElement(By
				.xpath("//a[text()='" + Contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application and quite the browser using from "Baseclass

	}

}

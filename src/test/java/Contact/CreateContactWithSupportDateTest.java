package Contact;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseclassUtility.Baseclass;
import GenericUtilities.Excel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.WebDriver_Utility;
import POMPages.ContactDetailPomPage;
import POMPages.ContactPomPage;
import POMPages.CreateNewContactPomPage;
import POMPages.HomePomPage;
import junit.framework.Assert;

public class CreateContactWithSupportDateTest extends Baseclass {

	// @Parameters("BROWSERS")using from "Baseclass
	@Test(groups = "regression")

	public void CreateContactWthSuppoDateTest() throws IOException, InterruptedException {

		// Fetch data from Property Utility file using from "Baseclass
		WebDriver_Utility w_util = new WebDriver_Utility();
		// Fetch data from Excel Utility
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String Contactname = ex_util.FetchDataFromExcelFile("Contacts", 4, 2) + random;

		// Launch the browser,maximize, implicitly wait, vaigate to ulr and login all of
		// them are used from "Baseclass"

		// Using Soft assert we are validating homepage
		HomePomPage home = new HomePomPage(driver);
		boolean expected_result_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(expected_result_home, true);

		// Identify contact tab in home page and click
		home.getContacts();

		// Identify plus button and click
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// Enter contact name in create new org page and save
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(Contactname);

		// Specify start and end Support date
		WebElement start_dateTF = newcon.getConStartDate_TF();
		start_dateTF.clear();
		String startdate = j_util.getCurrentDate();
		start_dateTF.sendKeys(startdate);

		WebElement end_dateTF = newcon.getConEndDate_TF();
		end_dateTF.clear();
		String enddate = j_util.getDateAftergivendays();
		end_dateTF.sendKeys(enddate);
		newcon.getSaveBtn();

		// Verify actual contact name with expected contact name
		ContactDetailPomPage condetail = new ContactDetailPomPage(driver);
		boolean expected_contact_results = condetail.getHeader().contains(Contactname);
		Assert.assertEquals(expected_contact_results, true);


		// Verify start supp date end support date using Assert
		boolean actstrtdate = condetail.getVerifyStartdate().contains(startdate);
		Assert.assertEquals(actstrtdate, true);

		// Verify end supp date end support date using Assert
		boolean actenddate = condetail.getVerifyEnddate().contains(enddate);
		Assert.assertEquals(actenddate, true);

		// Click on org tab and delete the created org
		home.getContacts();
		driver.findElement(By
				.xpath("//a[text()='" + Contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

		// logout of the application  and quite the browser using from "Baseclass

	}
}
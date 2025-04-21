package POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactDetailPomPage {

	// Declaration
	@FindBy(xpath = "//span[contains(text(),'Contact Information')]")
	private WebElement header;

	@FindBy(id = "mouseArea_Last Name")
	private WebElement verifyLastname;

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement verifyOrgname;

	@FindBy(id = "mouseArea_Support Start Date")
	private WebElement verifyStartdate;

	@FindBy(id = "mouseArea_Support End Date")
	private WebElement verifyEnddate;

	// Initialization
	public ContactDetailPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public String getVerifyLastname() {
		return verifyLastname.getText();
	}

	public String getVerifyOrgname() {
		return verifyOrgname.getText();
	}

	public String getVerifyStartdate() {
		return verifyStartdate.getText();
	}

	public String getVerifyEnddate() {
		return verifyEnddate.getText();
	}

}

package POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriver_Utility;

public class HomePomPage {

	// Declaration
	@FindBy(partialLinkText = "Home")
	private WebElement header;

	@FindBy(linkText = "Organizations")
	private WebElement Organization_Tab;

	@FindBy(linkText = "Contacts")
	private WebElement Contacts_Tab;

	@FindBy(xpath = "//span[text()=' Administrator']/../following-sibling::td/img")
	private WebElement admin_icon;

	@FindBy(linkText = "Sign Out")
	private WebElement signout;

	// Initialize
	public HomePomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilize
	public String getHeader() {
		return header.getText();
	}

	public void getOrganization() {
		Organization_Tab.click();
	}

	public void getContacts() {
		Contacts_Tab.click();
	}

	public WebElement getAdmin_icon() {
		return admin_icon;
	}

	public void getSignout() {
		signout.click();
	}
	
	public void logout(WebDriver driver) {
		WebDriver_Utility w_util = new WebDriver_Utility();
		w_util.Action_MouseHovering(driver, admin_icon);
		signout.click();
	}

}

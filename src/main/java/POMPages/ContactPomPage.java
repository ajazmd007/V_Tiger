package POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPomPage {

	// Declaration
	@FindBy(linkText = "Contacts")
	private WebElement header;

	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement plusicon;

	// Initialization
	public ContactPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization

	public String getHeader() {
		return header.getText();
	}

	public void getPlusicon() {
		plusicon.click();
	}

}

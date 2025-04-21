package POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPomPage {

	// Declaration
	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement header;

	@FindBy(name = "lastname")
	private WebElement Lastname_TF;

	@FindBy(xpath = "//img[contains(@onclick,'module=Accounts&action')]")
	private WebElement Orgplusicon;

	@FindBy(id = "search_txt")
	private WebElement OrgsearchTF;

	@FindBy(xpath = "//input[@type= 'button']")
	private WebElement OrgsearchBtn;

	@FindBy(name = "support_start_date")
	private WebElement ConStartDate_TF;

	@FindBy(name = "support_end_date")
	private WebElement ConEndDate_TF;

	@FindBy(xpath = "//input[@title= 'Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialization
	public CreateNewContactPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getLastname_TF(String Lastname) {
		Lastname_TF.sendKeys(Lastname);
	}

	public void getOrgplusicon() {
		Orgplusicon.click();
	}

	public void getOrgsearchTF(String Orgname) {
		OrgsearchTF.sendKeys(Orgname);
	}

	public void getOrgsearchBtn() {
		OrgsearchBtn.click();
	}

	public WebElement getConStartDate_TF() {
		return ConStartDate_TF;
	}

	public WebElement getConEndDate_TF() {
		return ConEndDate_TF;
	}

	public void getSaveBtn() {
		saveBtn.click();
	}

}

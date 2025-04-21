package POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrgPompage {

	// Declaration
	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement header;

	@FindBy(name = "accountname")
	private WebElement Orgname_TF;

	@FindBy(name = "phone")
	private WebElement Orgphno_TF;

	@FindBy(name = "industry")
	private WebElement OrgIndustryDD;

	@FindBy(name = "accounttype")
	private WebElement OrgTypeDD;

	@FindBy(xpath = "//input[@title= 'Save [Alt+S]']")
	private WebElement saveBtn;

	// Initialization
	public CreateNewOrgPompage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getOrgname_TF(String orgname) {
		Orgname_TF.sendKeys(orgname);
	}

	public void getOrgphno_TF(String orgphno) {
		Orgphno_TF.sendKeys(orgphno);
	}

	public WebElement getOrgIndustryDD() {
		return OrgIndustryDD;
	}

	public WebElement getOrgTypeDD() {
		return OrgTypeDD;
	}

	public void getSaveBtn() {
		saveBtn.click();
	}

}

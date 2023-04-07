package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class AccountTabPage extends ElementUtil {
	private WebDriver driver;
	private Logger logg;
	
	public AccountTabPage(WebDriver driver,Logger logg) {
		super(driver, logg);
		this.logg=logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='AccountBtn']")
	WebElement accountActMainTab;
	
	@FindBy(css = "#contentMsg > div > ul")
	WebElement accountVerificationMessage;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement accountCustomerDetailsSubhdr;
	
	@FindBy(xpath = "//span[text()='Account No.']")
	WebElement accountAccountNbrLbl;
	
	@FindBy(xpath = "//input[@id='AccountNo']")
	WebElement accountAccountNbrTxt;
	
	@FindBy(xpath = "//label[@for='MCECustomerID']")
	WebElement accountMCECustomerIDLbl;
	
	@FindBy(xpath = "//input[@id='MCECustomerID']")
	WebElement accountMCECustomerIDTxt;
	
	@FindBy(xpath = "//label[@for='RegistrantType']")
	WebElement accountRegistrantTypeLbl;
	
	@FindBy(xpath = "//input[@id='RegistrantType']")
	WebElement accountRegistrantTypeTxt;
	
	@FindBy(xpath = "//label[@for='CarrierType']")
	WebElement accountCarrierTypeLbl;
	
	@FindBy(xpath = "//input[@id='CarrierType']")
	WebElement accountCarrierTypeTxt;
	
	@FindBy(xpath = "//label[@for='IFTAAccountNo']")
	WebElement accountIFTAAccountNoLbl;
	
	@FindBy(xpath = "//input[@id='IFTAAccountNo']")
	WebElement accountIFTAAccountNoTxt;
	
	@FindBy(xpath = "//label[@for='ClientStatus']")
	WebElement accountCustomerStatusLbl;
	
	@FindBy(xpath = "//input[@id='ClientStatus']")
	WebElement accountCustomerStatusTxt;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[2]")
	WebElement accountAddressDetailsSubhdr;
	
	@FindBy(xpath = "//li[@aria-controls='tabs-1']")
	WebElement accountTab1;

	@FindBy(xpath = "//label[@for='Addressess_0__AddrLine1']")
	WebElement accountStreet0Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_0__AddrLine1']")
	WebElement accountStreet0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__Zip']")
	WebElement accountZip0Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_0__Zip']")
	WebElement accountZip0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__Jur']")
	WebElement accountJur0Lbl;
	
	@FindBy(xpath = "//select[@id='Addressess_0__Jur']")
	WebElement accountJur0dd;

	@FindBy(xpath = "//label[@for='Addressess_0__City']")
	WebElement accountCity0Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_0__City']")
	WebElement accountCity0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__County']")
	WebElement accountCounty0Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_0__County']")
	WebElement accountCounty0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__Country']")
	WebElement accountCountry0Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_0__Country']")
	WebElement accountCountry0Txt;

	@FindBy(xpath = "//li[@aria-controls='tabs-2']")
	WebElement accountTab2;
	
	@FindBy(xpath = "//label[@for='Addressess_1__AddrLine1']")
	WebElement accountStreet1Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_1__AddrLine1']")
	WebElement accountStreet1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__Zip']")
	WebElement accountZip1Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_1__Zip']")
	WebElement accountZip1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__Jur']")
	WebElement accountJur1Lbl;
	
	@FindBy(xpath = "//select[@id='Addressess_1__Jur']")
	WebElement accountJur1dd;

	@FindBy(xpath = "//label[@for='Addressess_1__City']")
	WebElement accountCity1Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_1__City']")
	WebElement accountCity1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__County']")
	WebElement accountCounty1Lbl;
	
	@FindBy(xpath = "//input[@id='Addressess_1__County']")
	WebElement accountCounty1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__Country']")
	WebElement accountCountryLbl;
	
	@FindBy(xpath = "//input[@id='Addressess_1__Country']")
	WebElement accountCountry1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__AttnTo']")
	WebElement accountAttentionToLbl;
	
	@FindBy(xpath = "//input[@id='Addressess_1__AttnTo']")
	WebElement accountAttentionToTxt;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[3]")
	WebElement accountBusinesscustomerDetailsSubhdr;

	@FindBy(xpath = "//label[@for='USDOTNo']")
	WebElement accountUSDOTNoLbl;
	
	@FindBy(xpath = "//input[@id='USDOTNo']")
	WebElement accountUSDOTNoTxt;

	@FindBy(xpath = "//label[@for='TIN']")
	WebElement accountTPIDLbl;
	
	@FindBy(xpath = "//input[@id='TIN']")
	WebElement accountTPIDTxt;

	@FindBy(xpath = "//label[@for='ConatactName']")
	WebElement accountContactNameLbl;
	
	@FindBy(xpath = "//input[@id='ConatactName']")
	WebElement accountContactNameTxt;

	@FindBy(xpath = "//label[@for='EmailAddress']")
	WebElement accountEmailLbl;
	
	@FindBy(xpath = "//input[@id='EmailAddress']")
	WebElement accountEmailTxt;

	@FindBy(xpath = "//label[@for='PrimaryPhone']")
	WebElement accountPrimaryPhoneLbl;
	
	@FindBy(xpath = "//input[@id='PrimaryPhone']")
	WebElement accountPrimaryPhoneTxt;

	@FindBy(xpath = "//label[@for='AlternatePhone']")
	WebElement accountAlternatePhoneLbl;
	
	@FindBy(xpath = "//input[@id='AlternatePhone']")
	WebElement accountAlternatePhoneTxt;

	@FindBy(xpath = "//label[@for='FaxNo']")
	WebElement accountFaxNoLbl;
	
	@FindBy(xpath = "//input[@id='FaxNo']")
	WebElement accountFaxNoTxt;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[4]")
	WebElement accountAccountDetailsSubhdr;
	
	@FindBy(xpath = "//label[@for='EmailNotification']")
	WebElement accountEmailNotificationLbl;
	
	@FindBy(xpath = "//input[@id='EmailNotification']")
	WebElement accountEmailNotification;
	
	@FindBy(xpath = "//label[@for='FaxNotification']")
	WebElement accountFaxNotificationLbl;
	
	@FindBy(xpath = "//input[@id='FaxNotification']")
	WebElement accountFaxNotification;

	public void validateAccountstabPresence() {
		isPresentAndDisplayed(accountActMainTab);
		highlightElement(driver, accountActMainTab);
	}

	public void checkEmailNotification() {
		webCheckON(accountEmailNotification);
	}

	public void checkFaxNotification() {
		webCheckON(accountFaxNotification);
	}

	public String fetchMCECustomernoLbl() {
		return FetchTextBoxValuewithText(accountMCECustomerIDLbl);
	}

	public String fetchMCECustomerNo() {
		return FetchTextBoxValuewithattribute(accountMCECustomerIDTxt, "value");
	}

	public String fetchRegistrationTypeLbl() {
		return FetchTextBoxValuewithText(accountRegistrantTypeLbl);
	}

	public String fetchRegistrationType() {
		return FetchTextBoxValuewithattribute(accountRegistrantTypeTxt, "value");
	}

	public String fetchAccountCarrierTypeLbl() {
		return FetchTextBoxValuewithText(accountCarrierTypeLbl);
	}

	public String fetchAccountCarrierType() {
		return FetchTextBoxValuewithattribute(accountCarrierTypeTxt, "value");
	}

	public String fetchIFTAAccountNbrlbl() {
		return FetchTextBoxValuewithText(accountIFTAAccountNoLbl);
	}

	public String fetchIFTAAccountNbr() {
		return FetchTextBoxValuewithattributedisabled(accountIFTAAccountNoTxt, "value");
	}

	public String fetchAccountCustomerStatusLbl() {
		return FetchTextBoxValuewithText(accountCustomerStatusLbl);
	}

	public String fetchAccountCustomerStatus() {
		return FetchTextBoxValuewithattribute(accountCustomerStatusTxt, "value");
	}

	public String fetchAccountsTab1() {
		return FetchTextBoxValuewithText(accountTab1);
	}

	public String fetchAccountStreet0Lbl() {
		return FetchTextBoxValuewithText(accountStreet0Lbl);
	}

	public String fetchAccountStreet0() {
		return FetchTextBoxValuewithattribute(accountStreet0Txt, "value");
	}

	public String fetchAccountZip0lbl() {
		return FetchTextBoxValuewithText(accountZip0Lbl);
	}

	public String fetchAccountZip0() {
		return FetchTextBoxValuewithattribute(accountZip0Txt, "value");
	}

	public String fetchAccountJur0lbl() {
		return FetchTextBoxValuewithText(accountJur0Lbl);
	}

	public String fetchAccountJur0() {
		return FetchDropdownSelectedValue(accountJur0dd);
	}

	public String fetchAccountCity0lbl() {
		return FetchTextBoxValuewithText(accountCity0Lbl);
	}

	public String fetchAccountCity0() {
		return FetchTextBoxValuewithattribute(accountCity0Txt, "value");
	}

	public String fetchAccountCounty0Lbl() {
		return FetchTextBoxValuewithText(accountCounty0Lbl);
	}

	public String fetchAccountCounty0() {
		return FetchTextBoxValuewithattribute(accountCounty0Txt, "value");
	}

	public String fetchAccountCountry0Lbl() {
		return FetchTextBoxValuewithText(accountCountry0Lbl);
	}

	public String fetchAccountCountry0() {
		return FetchTextBoxValuewithattribute(accountCountry0Txt, "value");
	}

	public void clickMailingAddress() {
		clickElement(accountTab2);
	}

	public String fetchAccountsTab2() {
		return FetchTextBoxValuewithText(accountTab2);
	}

	public String fetchAccountStreet1lbl() {
		return FetchTextBoxValuewithText(accountStreet1Lbl);
	}

	public String fetchAccountStreet1() {
		return FetchTextBoxValuewithattribute(accountStreet1Txt, "value");
	}

	public String fetchAccountZip1Lbl() {
		return FetchTextBoxValuewithText(accountZip1Lbl);
	}

	public String fetchAccountZip1() {
		return FetchTextBoxValuewithattribute(accountZip1Txt, "value");
	}

	public String fetchAccountJur1lbl() {
		return FetchTextBoxValuewithText(accountJur1Lbl);
	}

	public String fetchAccountJur1() {
		return FetchDropdownSelectedValue(accountJur1dd);
	}

	public String fetchAccountCity1Lbl() {
		return FetchTextBoxValuewithText(accountCity1Lbl);
	}

	public String fetchAccountCity1() {
		return FetchTextBoxValuewithattribute(accountCity1Txt, "value");
	}

	public String fetchAccountCounty1Lbl() {
		return FetchTextBoxValuewithText(accountCounty1Lbl);
	}

	public String fetchAccountCounty1() {
		return FetchTextBoxValuewithattribute(accountCounty1Txt, "value");
	}

	public String fetchAccountCountry1Lbl() {
		return FetchTextBoxValuewithText(accountCountryLbl);
	}

	public String fetchAccountCountry1() {
		return FetchTextBoxValuewithattribute(accountCountry1Txt, "value");
	}

	public String fetchAccountAttentionToLbl() {
		return FetchTextBoxValuewithText(accountAttentionToLbl);
	}

	public String fetchAccountAttentionTo() {
		return FetchTextBoxValuewithattribute(accountAttentionToTxt, "value");
	}

	public String fetchAccountUSDOTNoLbl() {
		return FetchTextBoxValuewithText(accountUSDOTNoLbl);
	}

	public String fetchAccountUSDOTNo() {
		return FetchTextBoxValuewithattribute(accountUSDOTNoTxt, "value");
	}

	public String fetchAccountTPIDLbl() {
		return FetchTextBoxValuewithText(accountTPIDLbl);
	}

	public String fetchAccountTPID() {
		return FetchTextBoxValuewithattribute(accountTPIDTxt, "value");
	}

	public String fetchAccountContactNameLbl() {
		return FetchTextBoxValuewithText(accountContactNameLbl);
	}

	public String fetchAccountContactName() {
		return FetchTextBoxValuewithattribute(accountContactNameTxt, "value");
	}

	public String fetchAccountEmailLbl() {
		return FetchTextBoxValuewithText(accountEmailLbl);
	}

	public String fetchAccountEmail() {
		return FetchTextBoxValuewithattribute(accountEmailTxt, "value");
	}

	public String fetchAccountPrimaryPhonelbl() {
		return FetchTextBoxValuewithText(accountPrimaryPhoneLbl);
	}

	public String fetchAccountPrimaryPhone() {
		return FetchTextBoxValuewithattribute(accountPrimaryPhoneTxt, "value");
	}

	public String fetchAccountAlternatePhoneLbl() {
		return FetchTextBoxValuewithText(accountAlternatePhoneLbl);
	}

	public String fetchAccountAlternatePhone() {
		return FetchTextBoxValuewithattribute(accountAlternatePhoneTxt, "value");
	}

	public String fetchAccountFaxNoLbl() {
		return FetchTextBoxValuewithText(accountFaxNoLbl);
	}

	public String fetchAccountFaxNo() {
		return FetchTextBoxValuewithattribute(accountFaxNoTxt, "value");
	}

	public String fetchAccountEmailNotificationLbl() {
		return FetchTextBoxValuewithText(accountEmailNotificationLbl);
	}

	public String fetchAccountEmailNotification() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(accountEmailNotification, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fetchAccountFaxNotificationLbl() {
		return FetchTextBoxValuewithText(accountFaxNotificationLbl);
	}

	public String fetchAccountFaxNotification() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(accountFaxNotification, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}


	public String validateMessage() {
		highlightElement(driver, accountVerificationMessage);
		return FetchTextBoxValuewithText(accountVerificationMessage);
	}

}

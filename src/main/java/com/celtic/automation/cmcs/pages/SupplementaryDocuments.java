package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class SupplementaryDocuments extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public SupplementaryDocuments(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[@for='AccountNo']")
	WebElement supplementDocumentsAccountNoLbl;

	@FindBy(xpath = "//input[@id='AccountNo']")
	WebElement supplementDocumentsAccountNo;

	@FindBy(xpath = "//label[@for='LegalName']")
	WebElement supplementDocumentsLegalNameLbl;

	@FindBy(xpath = "//input[@id='LegalName']")
	WebElement supplementDocumentsLegalName;

	@FindBy(xpath = "//label[@for='DBA']")
	WebElement supplementDocumentsDBALbl;

	@FindBy(xpath = "//input[@id='DBA']")
	WebElement supplementDocumentsDba;

	@FindBy(xpath = "//label[@for='FleetNo']")
	WebElement supplementDocumentsFleetNoLbl;

	@FindBy(xpath = "//input[@id='FleetNo']")
	WebElement supplementDocumentsFleetNo;

	@FindBy(xpath = "//label[@for='FltExpMonthYear']")
	WebElement supplementDocumentsFltExpMonthYearLbl;

	@FindBy(xpath = "//input[@id='FltExpMonthYear']")
	WebElement supplementDocumentsFltExpMonthYear;

	@FindBy(xpath = "//label[@for='SupplementNo']")
	WebElement supplementDocumentsSupplementNoLbl;

	@FindBy(xpath = "//input[@id='SupplementNo']")
	WebElement supplementDocumentsSupplementNo;

	@FindBy(xpath = "//label[@for='FleetType']")
	WebElement supplementDocumentsFleetTypeLbl;

	@FindBy(xpath = "//input[@id='FleetType']")
	WebElement supplementDocumentsFleetType;

	@FindBy(xpath = "//label[@for='SuppType']")
	WebElement supplementDocumentsTransactionTypeLbl;

	@FindBy(xpath = "//input[@id='SuppType']")
	WebElement supplementDocumentsTransactionType;

	@FindBy(xpath = "//label[@for='USDot']")
	WebElement supplementDocumentsUSDotLbl;

	@FindBy(xpath = "//input[@id='USDot']")
	WebElement supplementDocumentsUSDot;

	@FindBy(xpath = "//label[@for='IRPRequirementsForm_DocStatInd']")
	WebElement supplementDocumentsIRPRequirementLbl;

	@FindBy(xpath = "//select[@id='IRPRequirementsForm_DocStatInd']")
	WebElement supplementDocumentsIRPRequirement;

	@FindBy(xpath = "//label[@for='StatementOfUnderstanding_DocStatInd']")
	WebElement supplementDocumentsStatementofUnderstandingLbl;;

	@FindBy(xpath = "//select[@id='StatementOfUnderstanding_DocStatInd']")
	WebElement supplementDocumentsStatementOfUnderstanding;

	@FindBy(xpath = "//label[@for='InstallmentAgreement_DocStatInd']")
	WebElement supplementDocumentsInstallmentAgreementLbl;

	@FindBy(xpath = "//select[@id='InstallmentAgreement_DocStatInd']")
	WebElement supplementDocumentsInstallmentAgreement;

	@FindBy(xpath = "//label[@for='PowerOfAttony_DocStatInd']")
	WebElement supplementDocumentsPowerOfAttonyLbl;

	@FindBy(xpath = "//select[@id='PowerOfAttony_DocStatInd']")
	WebElement supplementDocumentsPowerOfAttony;

	public void enterAccountNo(String accountNo) {
		webEditTxtChange(supplementDocumentsAccountNo, accountNo);
	}

	public void selectIRPRequirements(String irp) {
		selectFromDropdownByVisibleText(supplementDocumentsIRPRequirement, irp);
	}

	public void selectStatmentDoccs(String statementunderstanding) {
		selectFromDropdownByVisibleText(supplementDocumentsStatementOfUnderstanding, statementunderstanding);
	}

	public void selectInstallmentDocs(String installment) {
		selectFromDropdownByVisibleText(supplementDocumentsInstallmentAgreement, installment);
	}

	public void selectPowerOfAttony(String PowerofAttony) {
		selectFromDropdownByVisibleText(supplementDocumentsPowerOfAttony, PowerofAttony);
	}

	public String fetchAccountNoLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsAccountNoLbl);
	}

	public String fetchAccountNo() {
		return FetchDropdownSelectedValue(supplementDocumentsAccountNo);
	}

	public String fetchLegalNameLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsLegalNameLbl);
	}

	public String fetchLegalName() {
		return FetchDropdownSelectedValue(supplementDocumentsLegalName);
	}

	public String fetchDBALbl() {
		return FetchTextBoxValuewithText(supplementDocumentsDBALbl);
	}

	public String fetchDBA() {
		return FetchTextBoxValuewithattribute(supplementDocumentsDba, "value");
	}

	public String fetchFleetNoLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsFleetNoLbl);
	}

	public String fetchFleetNo() {
		return FetchTextBoxValuewithattribute(supplementDocumentsFleetNo, "value");
	}

	public String fetchExpMonthYearLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsFltExpMonthYearLbl);
	}

	public String fetchExpMonthYear() {
		return FetchTextBoxValuewithattribute(supplementDocumentsFltExpMonthYearLbl, "value");
	}

	public String fetchSupplementNoLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsSupplementNoLbl);
	}

	public String fetchSupplementNo() {
		return FetchTextBoxValuewithattribute(supplementDocumentsSupplementNoLbl, "value");
	}

	public String fetchFleetTypeLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsFleetTypeLbl);
	}

	public String fetchFleetType() {
		return FetchTextBoxValuewithattribute(supplementDocumentsFleetType, "value");
	}

	public String fetchTransactionTypeLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsTransactionTypeLbl);
	}

	public String fetchTransactionType() {
		return FetchTextBoxValuewithattribute(supplementDocumentsTransactionTypeLbl, "value");
	}

	public String fetchUSDOTLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsUSDotLbl);
	}

	public String fetchUSDOT() {
		return FetchTextBoxValuewithattribute(supplementDocumentsUSDot, "value");
	}

	public String supplementDocumentsStatementOfUnderstandingLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsStatementofUnderstandingLbl);
	}

	public String supplementDocumentsStatementOfUnderstanding() {
		return FetchDropdownSelectedValue(supplementDocumentsStatementOfUnderstanding);
	}

	public String supplementDocumentsInstallmentAgreementLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsInstallmentAgreementLbl);
	}

	public String supplementDocumentsInstallmentAgreement() {
		return FetchDropdownSelectedValue(supplementDocumentsInstallmentAgreement);
	}

	public String supplementDocumentsPowerOfAttorneyLbl() {
		return FetchTextBoxValuewithText(supplementDocumentsPowerOfAttonyLbl);
	}

	public String supplementDocumentsPowerOfAttorney() {
		return FetchDropdownSelectedValue(supplementDocumentsPowerOfAttony);
	}

}
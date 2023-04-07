package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class FleetTabPage extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public FleetTabPage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(1) > span")
	WebElement fleetVerificationMsg1;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(2) > span")
	WebElement fleetVerificationMsg2;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(3) > span")
	WebElement fleetVerificationMsg3;

	@FindBy(xpath = "//a[@id='FleetBtn']")
	WebElement fleetFltMainTab;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement fleetFltSummarySubHdr;

	@FindBy(xpath = "//label[@for='CustomerType']")
	WebElement fleetRegistrationTypeLbl;

	@FindBy(xpath = "//select[@id='CustomerType']")
	WebElement fleetRegistrationTypedd;

	@FindBy(xpath = "//label[@for='FltStatus']")
	WebElement fleetFltStatusLbl;

	@FindBy(xpath = "//select[@id='FltStatus']")
	WebElement fleetFltStatusdd;

	@FindBy(xpath = "//label[@for='CarrierType']")
	WebElement fleetCarrierTypeLbl;

	@FindBy(xpath = "//input[@id='CarrierType']")
	WebElement fleetCarrierTypeTxt;

	@FindBy(xpath = "//label[@for='DBA']")
	WebElement fleetDBANameLbl;

	@FindBy(xpath = "//input[@id='DBA']")
	WebElement fleetDBANameTxt;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[2]")
	WebElement fleetAddressDetailsSubHdr;

	@FindBy(xpath = "//input[@id='btnAddrOverride']")
	WebElement fleetAddressOverrideSubHdr;

	@FindBy(xpath = "//li[@aria-controls='tabs-1']")
	WebElement fleetTab1;

	@FindBy(xpath = "//label[@for='Addressess_0__AddrLine1']")
	WebElement fleetStreet0Lbl;

	@FindBy(xpath = "//input[@id='Addressess_0__AddrLine1']")
	WebElement fleetStreet0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__Zip']")
	WebElement fleetZip0Lbl;

	@FindBy(xpath = "//input[@id='Addressess_0__Zip']")
	WebElement fleetZip0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__Jur']")
	WebElement fleetJur0Lbl;

	@FindBy(xpath = "//select[@id='Addressess_0__Jur']")
	WebElement fleetJur0dd;

	@FindBy(xpath = "//label[@for='Addressess_0__City']")
	WebElement fleetCity0Lbl;

	@FindBy(xpath = "//input[@id='Addressess_0__City']")
	WebElement fleetCity0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__County']")
	WebElement fleetCounty0Lbl;

	@FindBy(xpath = "//input[@id='Addressess_0__County']")
	WebElement fleetCounty0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__Country']")
	WebElement fleetCountry0Lbl;

	@FindBy(xpath = "//input[@id='Addressess_0__Country']")
	WebElement fleetCountry0Txt;

	@FindBy(xpath = "//label[@for='Addressess_0__IsNonDeliverableAddr']")
	WebElement fleetNonDeliverable0Lbl;

	@FindBy(xpath = "//input[@id='Addressess_0__IsNonDeliverableAddr']")
	WebElement fleetNonDeliverable0Chk;

	@FindBy(xpath = "//li[@aria-controls='tabs-2']")
	WebElement fleetTab2;

	@FindBy(xpath = "//label[@for='Addressess_1__AddrLine1']")
	WebElement fleetStreet1Lbl;

	@FindBy(xpath = "//input[@id='Addressess_1__AddrLine1']")
	WebElement fleetStreet1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__Zip']")
	WebElement fleetZip1Lbl;

	@FindBy(xpath = "//input[@id='Addressess_1__Zip']")
	WebElement fleetZip1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__Jur']")
	WebElement fleetJur1Lbl;

	@FindBy(xpath = "//select[@id='Addressess_1__Jur']")
	WebElement fleetJur1dd;

	@FindBy(xpath = "//label[@for='Addressess_1__City']")
	WebElement fleetCity1Lbl;

	@FindBy(xpath = "//input[@id='Addressess_1__City']")
	WebElement fleetCity1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__County']")
	WebElement fleetCounty1Lbl;

	@FindBy(xpath = "//input[@id='Addressess_1__County']")
	WebElement fleetCounty1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__Country']")
	WebElement fleetCountry1Lbl;

	@FindBy(xpath = "//input[@id='Addressess_1__Country']")
	WebElement fleetCountry1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__IsNonDeliverableAddr']")
	WebElement fleetNonDeliverable1Lbl;

	@FindBy(xpath = "//input[@id='Addressess_1__IsNonDeliverableAddr']")
	WebElement fleetNonDeliverable1Chk;

	@FindBy(xpath = "//input[@id='Addressess_1__AttnTo']")
	WebElement fleetAttentionTO1Txt;

	@FindBy(xpath = "//label[@for='Addressess_1__AttnTo']")
	WebElement fleetAttentionToLbl;

	@FindBy(xpath = "//li[@aria-controls='tabs-3']")
	WebElement fleetTab3;

	@FindBy(xpath = "//label[@for='reportingServices_ServiceProviderID']")
	WebElement fleetServiceProviderLbl;

	@FindBy(xpath = "//select[@id='reportingServices_ServiceProviderID']")
	WebElement fleetServiceProviderdd;

	@FindBy(xpath = "//label[@for='reportingServices_LegalName']")
	WebElement fleetLegalNameLbl;

	@FindBy(xpath = "//input[@id='reportingServices_LegalName']")
	WebElement fleetLegalNameTxt;

	@FindBy(xpath = "//label[@for='reportingServices_DBAName']")
	WebElement fleetServiceDBANameLbl;

	@FindBy(xpath = "//input[@id='reportingServices_DBAName']")
	WebElement fleetServiceDBANameTxt;

	@FindBy(xpath = "//label[@for='reportingServices_checkedPwrattoony']")
	WebElement fleetServicePowerOfAttroneyLbl;

	@FindBy(xpath = "//input[@id='reportingServices_checkedPwrattoony']")
	WebElement fleetServicePowerOfAttroneyChk;

	@FindBy(xpath = "//label[@for='reportingServices_PowerOfAttEffDate']")
	WebElement fleetServicePowerOfAttroneyEffDateLbl;

	@FindBy(xpath = "//input[@id='reportingServices_PowerOfAttEffDate']")
	WebElement fleetServicePowerOfAttroneyEffDateTxt;

	@FindBy(xpath = "//label[@for='reportingServices_PowerOfAttExpDate']")
	WebElement fleetServicePowerOfAttroneyExpDateLbl;

	@FindBy(xpath = "//input[@id='reportingServices_PowerOfAttExpDate']")
	WebElement fleetServicePowerOfAttroneyExpDateTxt;

	@FindBy(xpath = "//label[@for='reportingServices_EmailID']")
	WebElement fleetServiceEmailIdLbl;

	@FindBy(xpath = "//input[@id='reportingServices_EmailID']")
	WebElement fleetServiceEmailIdTxt;

	@FindBy(xpath = "//label[@for='reportingServices_PhoneNo']")
	WebElement fleetServicePhoneNoLbl;

	@FindBy(xpath = "//input[@id='reportingServices_PhoneNo']")
	WebElement fleetServicePhoneNoTxt;

	@FindBy(xpath = "//label[@for='reportingServices_Fax']")
	WebElement fleetServiceFaxNoLbl;

	@FindBy(xpath = "//input[@id='reportingServices_Fax']")
	WebElement fleetServiceFaxNoTxt;

	@FindBy(xpath = "//label[@for='reportingServices_Street1']")
	WebElement fleetServiceStreetLbl;

	@FindBy(xpath = "//input[@id='reportingServices_Street1']")
	WebElement fleetServiceStreetTxt;

	@FindBy(xpath = "//label[@for='reportingServices_City']")
	WebElement fleetServiceCityLbl;

	@FindBy(xpath = "//input[@id='reportingServices_City']")
	WebElement fleetServiceCityTxt;

	@FindBy(xpath = "//label[@for='reportingServices_Jur']")
	WebElement fleetService_JurLbl;

	@FindBy(xpath = "//input[@id='reportingServices_Jur']")
	WebElement fleetServiceJurTxt;

	@FindBy(xpath = "//label[@for='reportingServices_ZipCode']")
	WebElement fleetServiceZipCodeLbl;

	@FindBy(xpath = "//input[@id='reportingServices_ZipCode']")
	WebElement fleetServiceZipCodeTxt;

	@FindBy(xpath = "//label[@for='reportingServices_Country']")
	WebElement fleetServiceCountryLbl;

	@FindBy(xpath = "//input[@id='reportingServices_Country']")
	WebElement fleetServiceCountryTxt;

	@FindBy(xpath = "//li[@aria-controls='tabs-4']")
	WebElement fleetTab4;

	@FindBy(xpath = "//label[@for='Addressess_2__AddrLine1']")
	WebElement fleetServiceMailingStreetLbl;

	@FindBy(xpath = "//input[@id='Addressess_2__AddrLine1']")
	WebElement fleetServiceMailingStreetTxt;

	@FindBy(xpath = "//label[@for='Addressess_2__Zip']")
	WebElement fleetServiceMailingZipCodeLbl;

	@FindBy(xpath = "//input[@id='Addressess_2__Zip']")
	WebElement fleetServiceMailingZipCodeTxt;

	@FindBy(xpath = "//label[@for='Addressess_2__Jur']")
	WebElement fleetServiceMailingJurLbl;

	@FindBy(xpath = "//select[@id='Addressess_2__Jur']")
	WebElement fleetServiceMailingJurTxt;

	@FindBy(xpath = "//label[@for='Addressess_2__City']")
	WebElement fleetServiceMailingCityLbl;

	@FindBy(xpath = "//input[@id='Addressess_2__City']")
	WebElement fleetServiceMailingCityTxt;

	@FindBy(xpath = "//label[@for='Addressess_2__County']")
	WebElement fleetServiceMailingCountyLbl;

	@FindBy(xpath = "//input[@id='Addressess_2__County']")
	WebElement fleetServiceMailingCountyTxt;

	@FindBy(xpath = "//label[@for='Addressess_2__Country']")
	WebElement fleetServiceMailingCountryLbl;

	@FindBy(xpath = "//input[@id='Addressess_2__Country']")
	WebElement fleetServiceMailingCountrytxt;

	@FindBy(xpath = "//input[@id='Addressess_2__AttnTo']")
	WebElement fleetServiceMailingAttentionToTxt;

	@FindBy(xpath = "//label[@for='Addressess_2__AttnTo']")
	WebElement fleetServiceMailingAttentionToLbl;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[3]")
	WebElement fleetfltDetailsSubHdr;

	@FindBy(xpath = "//label[@for='ContactName']")
	WebElement fleetDetailsContactNameLbl;

	@FindBy(xpath = "//input[@id='ContactName']")
	WebElement fleetDetailsContactNameTxt;

	@FindBy(xpath = "//label[@for='Email']")
	WebElement fleetDetailsEmailIdLbl;

	@FindBy(xpath = "//input[@id='Email']")
	WebElement fleetDetailsEmailIdTxt;

	@FindBy(xpath = "//label[@for='PhoneNumber']")
	WebElement fleetDetailsPrimaryCellNbrLbl;

	@FindBy(xpath = "//input[@id='PhoneNumber']")
	WebElement fleetDetailsPrimaryCellNbrTxt;

	@FindBy(xpath = "//label[@for='FlCellNbr']")
	WebElement fleetDetailsAlternateCellNbrLbl;

	@FindBy(xpath = "//input[@id='FlCellNbr']")
	WebElement fleetDetailsAlternateCellNbrTxt;

	@FindBy(xpath = "//label[@for='FaxNo']")
	WebElement fleetDetailsFaxNoLbl;

	@FindBy(xpath = "//input[@id='FaxNo']")
	WebElement fleetDetailsFaxNoTxt;

	@FindBy(xpath = "//label[@for='TinNbr']")
	WebElement fleetDetailsTpIdLbl;

	@FindBy(xpath = "//input[@id='TinNbr']")
	WebElement fleetDetailsTPIdTxt;

	@FindBy(xpath = "//label[@for='UsdotNbr']")
	WebElement fleetDetailsUsdotNbrLbl;

	@FindBy(xpath = "//input[@id='UsdotNbr']")
	WebElement fleetDetailsUsdotNbrTxt;

	@FindBy(xpath = "//label[@for='ChangeVehUsdotTin']")
	WebElement fleetDetailsChangeVehUsdotTinLbl;

	@FindBy(xpath = "//input[@id='ChangeVehUsdotTin']")
	WebElement fleetDetailsChangeVehUsdotTInChk;

	@FindBy(xpath = "//label[@for='FltType']")
	WebElement fleetDetailsFltTypeLbl;

	@FindBy(xpath = "//select[@id='FltType']")
	WebElement fleetDetailsFltTypedd;

	@FindBy(xpath = "//label[@for='CommodityClass']")
	WebElement fleetDetailsCommodityClassLbl;

	@FindBy(xpath = "//select[@id='CommodityClass']")
	WebElement fleetDetailsCommodityClassdd;

	@FindBy(xpath = "//label[@for='FltEffDate']")
	WebElement fleetDetailsFltEffDateLbl;

	@FindBy(xpath = "//input[@id='FltEffDate']")
	WebElement fleetDetailsFltEffDatedtPicker;

	@FindBy(xpath = "//label[@for='FltExpDate']")
	WebElement fleetDetailsFltExpDateLbl;

	@FindBy(xpath = "//input[@id='FltExpDate']")
	WebElement fleetDetailsFltExpDatedtPicker;

	@FindBy(xpath = "//label[@for='changeAddrOnUsdot']")
	WebElement fleetDetailsChangeAddrOnUsdotLbl;

	@FindBy(xpath = "//input[@id='changeAddrOnUsdot']")
	WebElement fleetDetailschangeAddrOnUsdotChk;

	@FindBy(xpath = "//label[@for='FirstOperationDate']")
	WebElement fleetDetailsFirstOperatedDateLbl;

	@FindBy(xpath = "//input[@id='FirstOperationDate']")
	WebElement fleetDetailsFirstOperatedDatedtPicker;

	@FindBy(xpath = "//label[@for='WyIntraStF']")
	WebElement fleetDetailsWyomingIndicatorLbl;

	@FindBy(xpath = "//input[@id='WyIntraStF']")
	WebElement fleetDetailsWyomingIndicatorChk;

	@FindBy(xpath = "//label[@for='IftaMileageChk']")
	WebElement fleetDetailsIFTADistanceLbl;

	@FindBy(xpath = "//input[@id='IftaMileageChk']")
	WebElement fleetDetailsIFTADistanceChk;

	@FindBy(xpath = "//label[@for='MobileIND']")
	WebElement fleetDetailsMobileNotificationLbl;

	@FindBy(xpath = "//input[@id='MobileIND']")
	WebElement fleetDetailsMobileNotificationChk;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[5]")
	WebElement fleetDocumentationCollectionSubHdr;

	@FindBy(xpath = "//label[@for='IRPRequirementsForm']")
	WebElement fleetDocCollectionIRPRequirementsFormLbl;

	@FindBy(xpath = "//select[@id='IRPRequirementsForm']")
	WebElement fleetDocCollectionIRPRequirementsFormdd;

	@FindBy(xpath = "//label[@for='StatementOfUnderstanding']")
	WebElement fleetDocCollectionStatementOfUnderstandingLbl;

	@FindBy(xpath = "//select[@id='StatementOfUnderstanding']")
	WebElement fleetDocCollectionStatementOfUnderstandingdd;

	@FindBy(xpath = "//label[@for='InstallmentAgreement']")
	WebElement fleetDocCollectionInstallmentAgreementLbl;

	@FindBy(xpath = "//select[@id='InstallmentAgreement']")
	WebElement fleetDocCollectionInstallmentAgreementdd;

	@FindBy(xpath = "//label[@for='PowerOfAttorney']")
	WebElement fleetDocCollectionPowerOfAttorneyLbl;

	@FindBy(xpath = "//select[@id='PowerOfAttorney']")
	WebElement fleetDocCollectionPowerOfAttorneydd;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[6]")
	WebElement fleetVehicleDocumentSubHdr;

	@FindBy(xpath = "//label[@for='HVUTForm2290']")
	WebElement fleetVehDocumentHVUTFormLbl;

	@FindBy(xpath = "//select[@id='HVUTForm2290']")
	WebElement fleetVehDocumentHVUTFormdd;

	@FindBy(xpath = "//label[@for='PropertyTaxVehDocument']")
	WebElement fleetVehDocumentPropertyTaxLbl;

	@FindBy(xpath = "//select[@id='PropertyTaxVehDocument']")
	WebElement fleetVehDocumentPropertyTaxdd;

	public void navigateToServiceProvider() throws Exception {
		clickElement(fleetTab3);
	}

	public void clickPowerOfAttroney() {
		if (isPresentAndDisplayed(fleetServicePowerOfAttroneyChk)) {
			webCheckON(fleetServicePowerOfAttroneyChk);
		}
	}

	public void enterEmailID(String emailIdValue) {
		Boolean valueexists = validateTextbox(fleetDetailsEmailIdTxt);
		if (valueexists == true) {
			webEditTxtChange(fleetDetailsEmailIdTxt, emailIdValue);
		}
	}

	public void selectIRPRequirementForm(String selectValue) {
		selectFromDropdownByVisibleText(fleetDocCollectionIRPRequirementsFormdd, selectValue); // C - COLLECTED
	}

	public void selectStatementofUnderstanding(String selectValue) {
		selectFromDropdownByVisibleText(fleetDocCollectionStatementOfUnderstandingdd, selectValue); // C - COLLECTED
	}

	public void selectInstallmentAgreement(String selectValue) {
		selectFromDropdownByVisibleText(fleetDocCollectionInstallmentAgreementdd, selectValue); // C - COLLECTED
	}

	public void selectPowerOfAttroney(String selectValue) {
		selectFromDropdownByVisibleText(fleetDocCollectionPowerOfAttorneydd, selectValue); // C - COLLECTED
	}

	public void selectHVUTForm(String selectValue) {
		selectFromDropdownByVisibleText(fleetVehDocumentHVUTFormdd, selectValue); // C - COLLECTED
	}

	public void selectPropertyTax(String selectValue) {
		selectFromDropdownByVisibleText(fleetVehDocumentPropertyTaxdd, selectValue); // C - COLLECTED
	}

	public void selectEffectiveDate(String effDate) {
		Boolean valueexists = validateTextbox(fleetDetailsFltEffDatedtPicker);
		if (valueexists == true) {
			highlightElement(driver, fleetDetailsFltEffDatedtPicker);
			clickElement(fleetDetailsFltEffDatedtPicker);
			webEditTxt(fleetDetailsFltEffDatedtPicker, effDate);
		}
	}

	public void selectExpirationDate(String expDate) {
		clearElement(fleetDetailsFltExpDatedtPicker);
		clickElement(fleetDetailsFltExpDatedtPicker);
		webEditTxt(fleetDetailsFltExpDatedtPicker, expDate);
	}

	public void selectFirstOperatedDate(String firstOperatedDate) {
		Boolean valueexists = validateTextbox(fleetDetailsFirstOperatedDatedtPicker);
		if (valueexists == true) {
			clickElement(fleetDetailsFirstOperatedDatedtPicker);
			webEditTxt(fleetDetailsFirstOperatedDatedtPicker, firstOperatedDate);
		}
	}

	public void selectFleetType(String fleetTypeValue) {
		Boolean selectedornot = validateDropdownSelected(fleetDetailsFltTypedd);
		if (selectedornot == true) {
			selectFromDropdownByVisibleText(fleetDetailsFltTypedd, fleetTypeValue);
		}
	}

	public void selectCommodityClass(String commodityClassValue) {
		Boolean selectedornot = validateDropdownSelected(fleetDetailsCommodityClassdd);
		if (selectedornot == true) {
			selectFromDropdownByVisibleText(fleetDetailsCommodityClassdd, commodityClassValue);
		}
	}

	public void enterContactName(String contactNameValue) {
		Boolean valueexists = validateTextbox(fleetDetailsContactNameTxt);
		if (valueexists == true) {
			webEditTxtChange(fleetDetailsContactNameTxt, contactNameValue);
		}
	}

	public void enterprimaryPhone(String primaryPhoneValue) {
		Boolean valueexists = validateTextbox(fleetDetailsPrimaryCellNbrTxt);
		if (valueexists == true) {
			webEditTxtChange(fleetDetailsPrimaryCellNbrTxt, primaryPhoneValue);
		}
	}

	public void clickMailingAddress() throws InterruptedException {
		clickElement(fleetTab2);
	}

	public void clickOnTimeMailingAddress() throws InterruptedException {
		clickElement(fleetTab4);
	}

	public String fetchFleetTabBusinessAddress() {
		return FetchTextBoxValuewithText(fleetTab1);
	}

	public String fetchFleetTabMailingAddress() {
		return FetchTextBoxValuewithText(fleetTab2);
	}

	public String fetchFleetTabServiceProviderAddress() {
		return FetchTextBoxValuewithText(fleetTab3);
	}

	public String fetchFleetTabOneTimeMailingAddress() {
		return FetchTextBoxValuewithText(fleetTab4);
	}

	public String fetchRegistrationTypeLbl() {
		return FetchTextBoxValuewithText(fleetRegistrationTypeLbl);
	}

	public String fetchRegistrationType() {
		return FetchDropdownSelectedValue(fleetRegistrationTypedd);
	}

	public String fetchFltStatusLbl() {
		return FetchTextBoxValuewithText(fleetFltStatusLbl);
	}

	public String fetchFltStatus() {
		return FetchDropdownSelectedValue(fleetFltStatusdd);
	}

	public String fetchCarrierTypeLbl() {
		return FetchTextBoxValuewithText(fleetCarrierTypeLbl);
	}

	public String fetchCarrierType() {
		return FetchTextBoxValuewithattribute(fleetCarrierTypeTxt, "value");
	}

	public String fetchDBANameLbl() {
		return FetchTextBoxValuewithText(fleetDBANameLbl);
	}

	public String fetchDBAName() {
		return FetchTextBoxValuewithattribute(fleetDBANameTxt, "value");
	}

	public String fleetStreet0Lbl() {
		return FetchTextBoxValuewithText(fleetStreet0Lbl);
	}

	public String fleetStreet0() {
		return FetchTextBoxValuewithattribute(fleetStreet0Txt, "value");
	}

	public String fleetZip0Lbl() {
		return FetchTextBoxValuewithText(fleetZip0Lbl);
	}

	public String fleetZip0() {
		return FetchTextBoxValuewithattribute(fleetZip0Txt, "value");
	}

	public String fleetJur0Lbl() {
		return FetchTextBoxValuewithText(fleetJur0Lbl);
	}

	public String fleetJur0() {
		return FetchDropdownSelectedValue(fleetJur0dd);
	}

	public String fleetCity0Lbl() {
		return FetchTextBoxValuewithText(fleetCity0Lbl);
	}

	public String fleetCity0() {
		return FetchTextBoxValuewithattribute(fleetCity0Txt, "value");
	}

	public String fleetCounty0Lbl() {
		return FetchTextBoxValuewithText(fleetCounty0Lbl);
	}

	public String fleetCounty0() {
		return FetchTextBoxValuewithattribute(fleetCounty0Txt, "value");
	}

	public String fleetCountry0Lbl() {
		return FetchTextBoxValuewithText(fleetCountry0Lbl);
	}

	public String fleetCountry0() {
		return FetchTextBoxValuewithattribute(fleetCountry0Txt, "value");
	}

	public String fleetNonDeliverable0Lbl() {
		return FetchTextBoxValuewithText(fleetNonDeliverable0Lbl);
	}

	public String fleetNonDeliverable0() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetNonDeliverable0Chk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetStreet1Lbl() {
		return FetchTextBoxValuewithText(fleetStreet1Lbl);
	}

	public String fleetStreet1() {
		return FetchTextBoxValuewithattribute(fleetStreet1Txt, "value");
	}

	public String fleetZip1Lbl() {
		return FetchTextBoxValuewithText(fleetZip1Lbl);
	}

	public String fleetZip1() {
		return FetchTextBoxValuewithattribute(fleetZip1Txt, "value");
	}

	public String fleetJur1Lbl() {
		return FetchTextBoxValuewithText(fleetJur1Lbl);
	}

	public String fleetJur1() {
		return FetchDropdownSelectedValue(fleetJur1dd);
	}

	public String fleetCity1Lbl() {
		return FetchTextBoxValuewithText(fleetCity1Lbl);
	}

	public String fleetCity1() {
		return FetchTextBoxValuewithattribute(fleetCity1Txt, "value");
	}

	public String fleetCounty1Lbl() {
		return FetchTextBoxValuewithText(fleetCounty1Lbl);
	}

	public String fleetCounty1() {
		return FetchTextBoxValuewithattribute(fleetCounty1Txt, "value");
	}

	public String fleetCountry1Lbl() {
		return FetchTextBoxValuewithText(fleetCountry1Lbl);
	}

	public String fleetCountry1() {
		return FetchTextBoxValuewithattribute(fleetCountry1Txt, "value");
	}

	public String fleetNonDeliverable1Lbl() {
		return FetchTextBoxValuewithText(fleetNonDeliverable1Lbl);
	}

	public String fleetNonDeliverable1() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetNonDeliverable1Chk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetAttentionToLbl() {
		return FetchTextBoxValuewithText(fleetAttentionToLbl);
	}

	public String fleetAttentionTO() {
		return FetchTextBoxValuewithattribute(fleetAttentionTO1Txt, "value");
	}

	public String fleetServiceProviderLbl() {
		return FetchTextBoxValuewithText(fleetServiceProviderLbl);
	}

	public String fleetServiceProvider() {
		return FetchDropdownSelectedValue(fleetServiceProviderdd);
	}

	public String fleetServiceLegalNameLbl() {
		return FetchTextBoxValuewithText(fleetLegalNameLbl);
	}

	public String fleetServiceLegalName() {
		return FetchTextBoxValuewithattribute(fleetLegalNameTxt, "value");
	}

	public String fleetServiceDBANameLbl() {
		return FetchTextBoxValuewithText(fleetServiceDBANameLbl);
	}

	public String fleetServiceDBAName() {
		return FetchTextBoxValuewithattribute(fleetServiceDBANameTxt, "value");
	}

	public String fleetServicePowerOfAttroneyLbl() {
		return FetchTextBoxValuewithText(fleetServicePowerOfAttroneyLbl);
	}

	public boolean fleetServicePowerOfAttroneyLblpresence() {
		return isPresentAndDisplayed(fleetServicePowerOfAttroneyLbl);
	}

	public String fleetServicePowerOfAttroney() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetServicePowerOfAttroneyChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetServicePowerOfAttroneyEffDateLbl() {
		return FetchTextBoxValuewithText(fleetServicePowerOfAttroneyEffDateLbl);
	}

	public String fleetServicePowerOfAttroneyEffDate() {
		return FetchTextBoxValuewithattribute(fleetServicePowerOfAttroneyEffDateTxt, "value");
	}

	public String fleetServicePowerOfAttroneyExpDateLbl() {
		return FetchTextBoxValuewithText(fleetServicePowerOfAttroneyExpDateLbl);
	}

	public String fleetServicePowerOfAttroneyExpDate() {
		return FetchTextBoxValuewithattribute(fleetServicePowerOfAttroneyExpDateTxt, "value");
	}

	public String fleetServiceEmailIdLbl() {
		return FetchTextBoxValuewithText(fleetServiceEmailIdLbl);
	}

	public String fleetServiceEmailId() {
		return FetchTextBoxValuewithattribute(fleetServiceEmailIdTxt, "value");
	}

	public String fleetServicePhoneNoLbl() {
		return FetchTextBoxValuewithText(fleetServicePhoneNoLbl);
	}

	public String fleetServicePhoneNo() {
		return FetchTextBoxValuewithattribute(fleetServicePhoneNoTxt, "value");
	}

	public String fleetServiceFaxNoLbl() {
		return FetchTextBoxValuewithText(fleetServiceFaxNoLbl);
	}

	public String fleetServiceFaxNo() {
		return FetchTextBoxValuewithattribute(fleetServiceFaxNoTxt, "value");
	}

	public String fleetServiceStreetLbl() {
		return FetchTextBoxValuewithText(fleetServiceStreetLbl);
	}

	public String fleetServiceStreet() {
		return FetchTextBoxValuewithattribute(fleetServiceStreetTxt, "value");
	}

	public String fleetServiceCityLbl() {
		return FetchTextBoxValuewithText(fleetServiceCityLbl);
	}

	public String fleetServiceCity() {
		return FetchTextBoxValuewithattribute(fleetServiceCityTxt, "value");
	}

	public String fleetServiceJurLbl() {
		return FetchTextBoxValuewithText(fleetService_JurLbl);
	}

	public String fleetServiceJur() {
		return FetchTextBoxValuewithattribute(fleetServiceJurTxt, "value");
	}

	public String fleetServiceZipCodeLbl() {
		return FetchTextBoxValuewithText(fleetServiceZipCodeLbl);
	}

	public String fleetServiceZipCode() {
		return FetchTextBoxValuewithattribute(fleetServiceZipCodeTxt, "value");
	}

	public String fleetServiceCountryLbl() {
		return FetchTextBoxValuewithText(fleetServiceCountryLbl);
	}

	public String fleetServiceCountry() {
		return FetchTextBoxValuewithattribute(fleetServiceCountryTxt, "value");
	}

	public String fleetServiceMailingStreetLbl() {
		return FetchTextBoxValuewithText(fleetServiceMailingStreetLbl);
	}

	public String fleetServiceMailingStreet() {
		return FetchTextBoxValuewithattribute(fleetServiceMailingStreetTxt, "value");
	}

	public String fleetServiceMailingZipCodeLbl() {
		return FetchTextBoxValuewithText(fleetServiceMailingZipCodeLbl);
	}

	public String fleetServiceMailingZipCode() {
		return FetchTextBoxValuewithattribute(fleetServiceMailingZipCodeTxt, "value");
	}

	public String fleetServiceMailingJurLbl() {
		return FetchTextBoxValuewithText(fleetServiceMailingJurLbl);
	}

	public String fleetServiceMailingJur() {
		return FetchDropdownSelectedValue(fleetServiceMailingJurTxt);
	}

	public String fleetServiceMailingCityLbl() {
		return FetchTextBoxValuewithText(fleetServiceMailingCityLbl);
	}

	public String fleetServiceMailingCity() {
		return FetchTextBoxValuewithattribute(fleetServiceMailingCityTxt, "value");
	}

	public String fleetServiceMailingCountyLbl() {
		return FetchTextBoxValuewithText(fleetServiceMailingCountyLbl);
	}

	public String fleetServiceMailingCounty() {
		return FetchTextBoxValuewithattribute(fleetServiceMailingCountyTxt, "value");
	}

	public String fleetServiceMailingCountryLbl() {
		return FetchTextBoxValuewithText(fleetServiceMailingCountryLbl);
	}

	public String fleetServiceMailingCountry() {
		return FetchTextBoxValuewithattribute(fleetServiceMailingCountrytxt, "value");
	}

	public String fleetServiceMailingAttentionToLbl() {
		return FetchTextBoxValuewithText(fleetServiceMailingAttentionToLbl);
	}

	public String fleetServiceMailingAttentionTo() {
		return FetchTextBoxValuewithattribute(fleetServiceMailingAttentionToTxt, "value");
	}

	public String fleetDetailsContactNameLbl() {
		return FetchTextBoxValuewithText(fleetDetailsContactNameLbl);
	}

	public String fleetDetailsContactName() {
		return FetchTextBoxValuewithattribute(fleetDetailsContactNameTxt, "value");
	}

	public String fleetDetailsEmailIdLbl() {
		return FetchTextBoxValuewithText(fleetDetailsEmailIdLbl);
	}

	public String fleetDetailsEmailId() {
		return FetchTextBoxValuewithattribute(fleetDetailsEmailIdTxt, "value");
	}

	public String fleetDetailsPrimaryCellNbrLbl() {
		return FetchTextBoxValuewithText(fleetDetailsPrimaryCellNbrLbl);
	}

	public String fleetDetailsPrimaryCellNbr() {
		return FetchTextBoxValuewithattribute(fleetDetailsPrimaryCellNbrTxt, "value");
	}

	public String fleetDetailsAlternateCellNbrLbl() {
		return FetchTextBoxValuewithText(fleetDetailsAlternateCellNbrLbl);
	}

	public String fleetDetailsAlternateCellNbr() {
		return FetchTextBoxValuewithattribute(fleetDetailsAlternateCellNbrTxt, "value");
	}

	public void scrollToFax() {
		scrollIntoView(fleetDetailsFaxNoLbl);
	}

	public String fleetDetailsFaxNoLbl() {
		return FetchTextBoxValuewithText(fleetDetailsFaxNoLbl);
	}

	public String fleetDetailsFaxNo() {
		return FetchTextBoxValuewithattribute(fleetDetailsFaxNoTxt, "value");
	}

	public String fleetDetailsTpIdLbl() {
		return FetchTextBoxValuewithText(fleetDetailsTpIdLbl);
	}

	public String fleetDetailsTPID() {
		return FetchTextBoxValuewithattribute(fleetDetailsTPIdTxt, "value");
	}

	public String fleetDetailsUsdotNbrLbl() {
		return FetchTextBoxValuewithText(fleetDetailsUsdotNbrLbl);
	}

	public String fleetDetailsUsdotNbr() {
		return FetchTextBoxValuewithattribute(fleetDetailsUsdotNbrTxt, "value");
	}

	public String fleetDetailsChangeVehUsdotTinLbl() {
		return FetchTextBoxValuewithText(fleetDetailsChangeVehUsdotTinLbl);
	}

	public String fleetDetailsChangeVehUsdotTin() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetDetailsChangeVehUsdotTInChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetDetailsFltTypeLbl() {
		return FetchTextBoxValuewithText(fleetDetailsFltTypeLbl);
	}

	public String fleetDetailsFltType() {
		return FetchDropdownSelectedValue(fleetDetailsFltTypedd);
	}

	public String fleetDetailsCommodityClassLbl() {
		return FetchTextBoxValuewithText(fleetDetailsCommodityClassLbl);
	}

	public String fleetDetailsCommodityClass() {
		return FetchDropdownSelectedValue(fleetDetailsCommodityClassdd);
	}

	public String fleetDetailsFltEffDateLbl() {
		return FetchTextBoxValuewithText(fleetDetailsFltEffDateLbl);
	}

	public String fleetDetailsFltEffDatedtPicker() {
		return FetchTextBoxValuewithattribute(fleetDetailsFltEffDatedtPicker, "value");
	}

	public String fleetDetailsFltExpDateLbl() {
		return FetchTextBoxValuewithText(fleetDetailsFltExpDateLbl);
	}

	public String fleetDetailsFltExpDate() {
		return FetchTextBoxValuewithattribute(fleetDetailsFltExpDatedtPicker, "value");
	}

	public void scrollToAddrOnUsdot() {
		scrollIntoView(fleetDetailsChangeAddrOnUsdotLbl);
	}

	public String fleetDetailsChangeAddrOnUsdotLbl() {
		return FetchTextBoxValuewithText(fleetDetailsChangeAddrOnUsdotLbl);
	}

	public String fleetDetailsChangeAddrOnUsdot() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetDetailschangeAddrOnUsdotChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetDetailsFirstOperatedDateLbl() {
		return FetchTextBoxValuewithText(fleetDetailsFirstOperatedDateLbl);
	}

	public String fleetDetailsFirstOperatedDate() {
		return FetchTextBoxValuewithattribute(fleetDetailsFirstOperatedDatedtPicker, "value");
	}

	public String fleetDetailsWyomingIndicatorLbl() {
		return FetchTextBoxValuewithText(fleetDetailsWyomingIndicatorLbl);
	}

	public String fleetDetailsWyomingIndicator() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetDetailsWyomingIndicatorChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetDetailsIFTADistanceLbl() {
		return FetchTextBoxValuewithText(fleetDetailsIFTADistanceLbl);
	}

	public String fleetDetailsIFTADistance() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetDetailsIFTADistanceChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetDetailsMobileNotificationLbl() {
		return FetchTextBoxValuewithText(fleetDetailsMobileNotificationLbl);
	}

	public String fleetDetailsMobileNotification() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(fleetDetailsMobileNotificationChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fleetDetailsIRPRequirementsLbl() {
		return FetchTextBoxValuewithText(fleetDocCollectionIRPRequirementsFormLbl);
	}

	public String fleetDetailsIRPRequirements() {
		return FetchDropdownSelectedValue(fleetDocCollectionIRPRequirementsFormdd);
	}

	public String fleetDetailsStatementOfUnderstandingLbl() {
		return FetchTextBoxValuewithText(fleetDocCollectionStatementOfUnderstandingLbl);
	}

	public String fleetDetailsStatementOfUnderstanding() {
		return FetchDropdownSelectedValue(fleetDocCollectionStatementOfUnderstandingdd);
	}

	public String fleetDetailsInstallmentAgreementLbl() {
		return FetchTextBoxValuewithText(fleetDocCollectionInstallmentAgreementLbl);
	}

	public String fleetDetailsInstallmentAgreement() {
		return FetchDropdownSelectedValue(fleetDocCollectionInstallmentAgreementdd);
	}

	public String fleetDetailsPowerOfAttorneyLbl() {
		return FetchTextBoxValuewithText(fleetDocCollectionPowerOfAttorneyLbl);
	}

	public String fleetDetailsPowerOfAttorney() {
		return FetchDropdownSelectedValue(fleetDocCollectionPowerOfAttorneydd);
	}

	public boolean fleetHVUTFormLblpresence() {
		return isPresentAndDisplayed(fleetVehDocumentHVUTFormLbl);
	}

	public String fleetDetailsHVUTFormLbl() {
		return FetchTextBoxValuewithText(fleetVehDocumentHVUTFormLbl);
	}

	public String fleetDetailsHVUTForm() {
		return FetchDropdownSelectedValue(fleetVehDocumentHVUTFormdd);
	}

	public boolean fleetPropertyTaxLblpresence() {
		return isPresentAndDisplayed(fleetVehDocumentPropertyTaxLbl);
	}

	public String fleetDetailsPropertyTaxLbl() {
		return FetchTextBoxValuewithText(fleetVehDocumentPropertyTaxLbl);
	}

	public String fleetDetailsPropertyTax() {
		return FetchDropdownSelectedValue(fleetVehDocumentPropertyTaxdd);
	}

	public String fleetValidateMessage1() {
		highlightElement(driver, fleetVerificationMsg1);
		return FetchTextBoxValuewithText(fleetVerificationMsg1);
	}

	public String fleetValidateMessage2() {
		highlightElement(driver, fleetVerificationMsg2);
		return FetchTextBoxValuewithText(fleetVerificationMsg2);
	}

	public String fleetValidateMessage3() {
		highlightElement(driver, fleetVerificationMsg3);
		return FetchTextBoxValuewithText(fleetVerificationMsg3);
	}

}

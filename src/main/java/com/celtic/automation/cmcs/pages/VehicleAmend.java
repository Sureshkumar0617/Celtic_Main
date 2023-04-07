package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class VehicleAmend extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public VehicleAmend(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement amendVehicleChangeVehicleDetailsSubHdr;

	@FindBy(xpath = "//label[@for='ChangeVehUnitNo']")
	WebElement amendVehicleChangeVehUnitNoLbl;

	@FindBy(xpath = "//input[@id='ChangeVehUnitNo']")
	WebElement amendVehicleChangeVehUnitNoTxt;

	@FindBy(xpath = "//label[@for='ChangeVehVIN']")
	WebElement amendVehicleVINLbl;

	@FindBy(xpath = "//input[@id='ChangeVehVIN']")
	WebElement amendVehicleVINTxt;

	@FindBy(xpath = "//label[@for='ChangeVehPlateNo']")
	WebElement amendVehiclePLateNoLbl;

	@FindBy(xpath = "//input[@id='ChangeVehPlateNo']")
	WebElement amendVehiclePlateNoTxt;

	@FindBy(xpath = "//input[@id='btnFind']")
	WebElement amendVehicleSearchBtn;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[2]")
	WebElement amendVehicleSupplementDetailsSubHdr;

	@FindBy(xpath = "//label[@for='NoOfVehicle']")
	WebElement amendVehicleNoOfVehiclesNoLbl;

	@FindBy(xpath = "//input[@id='NoOfVehicle']")
	WebElement amendVehicleNoOfVehiclesTxt;

	@FindBy(xpath = "//label[@for='RegistrantType']")
	WebElement amendVehicleRegistrationTypeLbl;

	@FindBy(xpath = "//select[@id='RegistrantType']")
	WebElement amendVehicleRegistrationTypeTxt;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[3]")
	WebElement amendVehicleVehiclesDetailsSubHdr;

	@FindBy(xpath = "//label[@for='VehDtlVin']")
	WebElement amendVehicleVinLbl;

	@FindBy(xpath = "//input[@id='VehDtlVin']")
	WebElement amendVehicleVinTxt;

	@FindBy(xpath = "//label[@for='VehDtlUnitNo']")
	WebElement amendVehicleUnitNoLbl;

	@FindBy(xpath = "//input[@id='VehDtlUnitNo']")
	WebElement amendVehicleUnitNoTxt;

	@FindBy(xpath = "//label[@for='VehDtlWeightGroupNo']")
	WebElement amendVehicleWeightGroupNoLbl;

	@FindBy(xpath = "//select[@id='VehDtlWeightGroupNo']")
	WebElement amendVehicleWeightGroupNoDd;

	@FindBy(xpath = "//label[@for='VehDtlYear']")
	WebElement amendVehicleYearLbl;

	@FindBy(xpath = "//input[@id='VehDtlYear']")
	WebElement amendVehicleYearTxt;

	@FindBy(xpath = "//label[@for='VehDtlBodyStyle']")
	WebElement amendVehicleBodyTypeLbl;

	@FindBy(xpath = "//select[@id='VehDtlBodyStyle']")
	WebElement amendVehicleBodyTypeDd;

	@FindBy(xpath = "//label[@for='VehDtlMake']")
	WebElement amendVehicleMakeDateLbl;

	@FindBy(xpath = "//select[@id='VehDtlMake']")
	WebElement amendVehicleMakeDateDd;

	@FindBy(xpath = "//input[@id='VehDtlMakeOther']")
	WebElement amendVehicleMakeDateOtherTxt;

	@FindBy(xpath = "//label[@for='VehDtlCombinedAxles']")
	WebElement amendVehicleCombinedAxleLbl;

	@FindBy(xpath = "//input[@id='VehDtlCombinedAxles']")
	WebElement amendVehicleCombinedAxleTxt;

	@FindBy(xpath = "//label[@for='VehDtlAxles']")
	WebElement amendVehicleAxleLbl;

	@FindBy(xpath = "//input[@id='VehDtlAxles']")
	WebElement amendVehicleAxleTxt;

	@FindBy(xpath = "//label[@for='VehDtlFuleType']")
	WebElement amendVehicleFueltypeLbl;

	@FindBy(xpath = "//select[@id='VehDtlFuleType']")
	WebElement amendVehicleFuelTypeDd;

	@FindBy(xpath = "//label[@for='VehDtlSeats']")
	WebElement amendVehicleSeatLbl;

	@FindBy(xpath = "//input[@id='VehDtlSeats']")
	WebElement amendVehicleSeatTxt;

	@FindBy(xpath = "//label[@for='VehDtlColor']")
	WebElement amendVehicleVehicleColourLbl;

	@FindBy(xpath = "//select[@id='VehDtlColor']")
	WebElement amendVehicleVehicleColourDd;

	@FindBy(xpath = "//label[@for='VehDtlUnladenWeight']")
	WebElement amendVehicleUnladenWeightLbl;

	@FindBy(xpath = "//input[@id='VehDtlUnladenWeight']")
	WebElement amendVehicleUnladenWeightTxt;

	@FindBy(xpath = "//label[@for='VehDtlMaxDesiredWeight']")
	WebElement amendVehicleGrossWeightLbl;

	@FindBy(xpath = "//input[@id='VehDtlMaxDesiredWeight']")
	WebElement amendVehicleGrossWeightTxt;

	@FindBy(xpath = "//label[@for='PurchaseDate']")
	WebElement amendVehiclePurchaseDateLbl;

	@FindBy(xpath = "//input[@id='PurchaseDate']")
	WebElement amendVehiclePurchaseDatedtPicker;

	@FindBy(xpath = "//label[@for='PurchasePrice']")
	WebElement amendVehiclePurchasePriceLbl;

	@FindBy(xpath = "//input[@id='PurchasePrice']")
	WebElement amendVehiclePurchasePriceTxt;

	@FindBy(xpath = "//label[@for='FactoryPrice']")
	WebElement amendVehicleFactoryPriceLbl;

	@FindBy(xpath = "//input[@id='FactoryPrice']")
	WebElement amendVehicleFactoryPriceTxt;

	@FindBy(xpath = "//label[@for='IsTearIndicator']")
	WebElement amendVehicleTVRLbl;

	@FindBy(xpath = "//input[@id='IsTearIndicator']")
	WebElement amendVehicleTVRChk;

	@FindBy(xpath = "//label[@for='NoOfDays']")
	WebElement amendVehicleTVRNoOfDaysLbl;

	@FindBy(xpath = "//input[@id='NoOfDays']")
	WebElement amendVehicleTVRNoOfDaysTxt;

	@FindBy(xpath = "//label[@for='VehDtlTitleJurisdiction']")
	WebElement amendVehicleTitleJurisdictionLbl;

	@FindBy(xpath = "//select[@id='VehDtlTitleJurisdiction']")
	WebElement amendVehicleTitleJurisdictionDd;

	@FindBy(xpath = "//label[@for='VehDtlTitleNo']")
	WebElement amendVehicleTitleNoLbl;

	@FindBy(xpath = "//input[@id='VehDtlTitleNo']")
	WebElement amendVehicleTitleNoTxt;

	@FindBy(xpath = "//label[@for='VehDtlCountyPlateType']")
	WebElement amendVehicleInStatePlateTypeLbl;

	@FindBy(xpath = "//select[@id='VehDtlCountyPlateType']")
	WebElement amendVehicleInStatePlateTypeDd;

	@FindBy(xpath = "//label[@for='VehDtlCountyWeight']")
	WebElement amendVehicleInStatePlateWeightLbl;

	@FindBy(xpath = "//input[@id='VehDtlCountyWeight']")
	WebElement amendVehicleInStatePlateWeightTxt;

	@FindBy(xpath = "//label[@for='CountyPlateExpiration']")
	WebElement amendVehicleInStateExpirationDateLbl;

	@FindBy(xpath = "//input[@id='CountyPlateExpiration']")
	WebElement amendVehicleInStateExpirationDatedtPicker;

	@FindBy(xpath = "//label[@for='VehDtlCountyPlate']")
	WebElement amendVehicleInStatePlateLbl;

	@FindBy(xpath = "//input[@id='VehDtlCountyPlate']")
	WebElement amendVehicleInStatePlateTxt;

	@FindBy(xpath = "//label[@for='CountyFee']")
	WebElement amendVehicleInStatePlateFeeLbl;

	@FindBy(xpath = "//input[@id='CountyFee']")
	WebElement amendVehicleInStatePlateFeeTxt;

	@FindBy(xpath = "//label[@for='OwnerDtlName']")
	WebElement amendVehicleOwnerNameLbl;

	@FindBy(xpath = "//input[@id='OwnerDtlName']")
	WebElement amendVehicleOwnerNameTxt;

	@FindBy(xpath = "//label[@for='OwnerPhoneNo']")
	WebElement amendVehicleOwnerPhoneNoLbl;

	@FindBy(xpath = "//input[@id='OwnerPhoneNo']")
	WebElement amendVehicleOwnerPhoneNoTxt;

	@FindBy(xpath = "//label[@for='LeaseOrOwner']")
	WebElement amendVehicleSafetyResponsibilityLbl;

	@FindBy(xpath = "//select[@id='LeaseOrOwner']")
	WebElement amendVehicleSafetyResponsibilityDd;

	@FindBy(xpath = "//label[@for='IsLease']")
	WebElement amendVehicleLeaseLbl;

	@FindBy(xpath = "//input[@id='IsLease']")
	WebElement amendVehicleLeaseChk;

	@FindBy(xpath = "//label[@for='VehDtlSafetyUsdot']")
	WebElement amendVehicleSafetyUSDOTLbl;

	@FindBy(xpath = "//input[@id='VehDtlSafetyUsdot']")
	WebElement amendVehicleSafetyUSDOTTxt;

	@FindBy(xpath = "//label[@for='VehDtlSafetyTin']")
	WebElement amendVehicleSafetyTPIDLbl;

	@FindBy(xpath = "//input[@id='VehDtlSafetyTin']")
	WebElement amendVehicleSafetyTPIDTxt;

	@FindBy(xpath = "//label[@for='SaftyChangeDuringYear']")
	WebElement amendVehicleSafetyChangeLbl;

	@FindBy(xpath = "//select[@id='SaftyChangeDuringYear']")
	WebElement amendVehicleSafetyChangeDd;

	@FindBy(xpath = "//label[@for='IsUseExistingPlate']")
	WebElement amendVehicleUseExistingPlateLbl;

	@FindBy(xpath = "//input[@id='IsUseExistingPlate']")
	WebElement amendVehicleUseExistingPlateChk;

	@FindBy(xpath = "//label[@for='ExistingPlate']")
	WebElement amendVehicleExistingPlateLbl;

	@FindBy(xpath = "//input[@id='ExistingPlate']")
	WebElement amendVehicleExistingPlateTxt;

	@FindBy(xpath = "//label[@for='VehDtlIsChangeUsdotAddress']")
	WebElement amendVehicleChangeNameAddLbl;

	@FindBy(xpath = "//input[@id='VehDtlIsChangeUsdotAddress']")
	WebElement amendVehicleChangeNameAddChk;

	@FindBy(xpath = "//label[@for='CabCardFeeOverride']")
	WebElement amendVehicleOverrideGradeCrossingFeeLbl;

	@FindBy(xpath = "//input[@id='CabCardFeeOverride']")
	WebElement amendVehicleOverrideGradeCrossingFeeChk;

	@FindBy(xpath = "//label[@for='TAFeeOverride']")
	WebElement amendVehicleOverrideBicentennialFeeLbl;

	@FindBy(xpath = "//input[@id='TAFeeOverride']")
	WebElement amendVehicleOverrideBicentennialFeeChk;

	@FindBy(xpath = "//label[@for='IsNewPlateRequired']")
	WebElement amendVehicleNewPlateRequiredLbl;

	@FindBy(xpath = "//input[@id='IsNewPlateRequired']")
	WebElement amendVehicleNewPlateRequiredChk;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[5]")
	WebElement amendVehicleFeeCalculationSubHdr;

	@FindBy(xpath = "//label[@for='ColoradoDistanceFlag']")
	WebElement amendVehicleColorado10kIndicatorLbl;

	@FindBy(xpath = "//select[@id='ColoradoDistanceFlag']")
	WebElement amendVehicleColorado10kIndicatorDd;

	@FindBy(xpath = "//label[@for='ColoradoTrailerFlag']")
	WebElement amendVehicleColoradoTrailerFlagLbl;

	@FindBy(xpath = "//select[@id='ColoradoTrailerFlag']")
	WebElement amendVehicleColoradoTrailerFlagDd;

	@FindBy(xpath = "//label[@for='UTSpecialTruckFlag']")
	WebElement amendVehicleUtahSpecialTruckLbl;

	@FindBy(xpath = "//select[@id='UTSpecialTruckFlag']")
	WebElement amendVehicleUtahSpecialTruckDd;

	@FindBy(xpath = "//label[@for='NewUsedF']")
	WebElement amendVehicleNewVehicleLbl;

	@FindBy(xpath = "//select[@id='NewUsedF']")
	WebElement amendVehicleNewVehicleDd;

	@FindBy(xpath = "//label[@for='CoSpecialTruck']")
	WebElement amendVehicleCOSpecialTruckLbl;

	@FindBy(xpath = "//select[@id='CoSpecialTruck']")
	WebElement amendVehicleCOSpecialTruckDd;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[6]")
	WebElement amendVehicleTrackingSubHdr;

	@FindBy(xpath = "//label[@for='HVUTForm2290']")
	WebElement amendVehicleHVUTForm2290Lbl;

	@FindBy(xpath = "//select[@id='HVUTForm2290']")
	WebElement amendVehicleHVUTForm2290Dd;

	@FindBy(xpath = "//label[@for='LeaseContract']")
	WebElement amendVehicleLeaseContractLbl;

	@FindBy(xpath = "//select[@id='LeaseContract']")
	WebElement amendVehicleLeaseContractDd;

	@FindBy(xpath = "//label[@for='TitleDocument']")
	WebElement amendVehicleaTitleDocumentLbl;

	@FindBy(xpath = "//select[@id='TitleDocument']")
	WebElement amendVehicleTitleDocumentDd;

	@FindBy(xpath = "//label[@for='PlateReturndoc']")
	WebElement amendVehiclePlateReturnDocLbl;

	@FindBy(xpath = "//select[@id='PlateReturndoc']")
	WebElement amendVehiclePlateReturnDocDd;

	@FindBy(xpath = "//label[@for='AffidavitDoc']")
	WebElement amendVehicleAffidavitDocLbl;

	@FindBy(xpath = "//select[@id='AffidavitDoc']")
	WebElement amendVehicleAffidavitDocDd;

	@FindBy(xpath = "//label[@for='PropertyTaxVehDocument']")
	WebElement amendVehiclePropertyTaxLbl;

	@FindBy(xpath = "//select[@id='PropertyTaxVehDocument']")
	WebElement amendVehiclePropertyTaxDd;

	public void clickVIN() {
		clickElement(amendVehicleVINTxt);
	}

	public void clickUnitNo() {
		clickElement(amendVehicleChangeVehUnitNoTxt);
	}

	public void selectUnitNoFromSuggestions() {
		waitUntilElementClickable(amendVehicleChangeVehUnitNoTxt);
		clickElement(amendVehicleChangeVehUnitNoTxt);
		webEditTxtkeys(amendVehicleChangeVehUnitNoTxt, Keys.ARROW_DOWN);
		webEditTxtkeys(amendVehicleChangeVehUnitNoTxt, Keys.ENTER);
	}

	public void selectUnitNoFromExcel(String changeVehicleUnitNoValue) {
		String text;
		do {
			waitUntilElementClickable(amendVehicleChangeVehUnitNoTxt);
			clickElement(amendVehicleChangeVehUnitNoTxt);
			webEditTxtkeys(amendVehicleChangeVehUnitNoTxt, Keys.ARROW_DOWN);
			webEditTxtkeys(amendVehicleChangeVehUnitNoTxt, Keys.ENTER);
			text = amendVehicleChangeVehUnitNoTxt.getAttribute("value").trim();
			if (text == changeVehicleUnitNoValue) {
				webEditTxtkeys(amendVehicleChangeVehUnitNoTxt, Keys.ENTER);
				break;
			}

		} while (!text.isEmpty());
	}

	public void enterUnitNo(String changeVehicleUnitNoValue) {
		webEditTxtChange(amendVehicleChangeVehUnitNoTxt, changeVehicleUnitNoValue);
	}

	public void clickSearch() throws InterruptedException {
		clickElement(amendVehicleSearchBtn);

	}

	public void selectBodyType(String amendVehicleBodyTypeDdValue) {
		selectFromDropdownByVisibleText(amendVehicleBodyTypeDd, amendVehicleBodyTypeDdValue);
	}

	public void clickTVR() {
		clickElement(amendVehicleTVRChk);
	}

	public void enterUnladenWeight(String unladenWeightValue) {
		if (FetchTextBoxValuewithattribute(amendVehicleUnladenWeightTxt, "value") == null) {
			webEditTxtChange(amendVehicleUnladenWeightTxt, unladenWeightValue);
		}
	}

	public void selectWeightGroupNo(String amendVehicleWeightGroupNoDdValue) {
		selectFromDropdownByVisibleText(amendVehicleWeightGroupNoDd, amendVehicleWeightGroupNoDdValue);
	}

	public void validatePresenceOfMandatoryFeildValues() {
		isElementcontainsValue(amendVehicleUnitNoTxt, "value");
		isElementcontainsValue(amendVehicleWeightGroupNoDd, "value");
		isElementcontainsValue(amendVehicleYearTxt, "value");
		isElementcontainsValue(amendVehicleBodyTypeDd, "value");
		isElementcontainsValue(amendVehicleMakeDateDd, "value");
		isElementcontainsValue(amendVehicleAxleTxt, "value");
		isElementcontainsValue(amendVehicleUnladenWeightTxt, "value");
		isElementcontainsValue(amendVehiclePurchaseDatedtPicker, "value");
		isElementcontainsValue(amendVehiclePurchasePriceTxt, "value");
		isElementcontainsValue(amendVehicleTitleJurisdictionDd, "value");
		isElementcontainsValue(amendVehicleTitleNoTxt, "value");
		isElementcontainsValue(amendVehicleOwnerNameTxt, "value");
		isElementcontainsValue(amendVehicleSafetyUSDOTTxt, "value");
		isElementcontainsValue(amendVehicleSafetyTPIDTxt, "value");
		isElementcontainsValue(amendVehicleSafetyChangeDd, "value");
	}

	public void selectColorado10kIndicator(String colorado10kIndicatorddValue) {
		selectFromDropdownByVisibleText(amendVehicleColorado10kIndicatorDd, colorado10kIndicatorddValue);
	}

	public void selectColoradoTrailer(String coloradoTrailerddValue) {
		selectFromDropdownByVisibleText(amendVehicleColoradoTrailerFlagDd, coloradoTrailerddValue);
	}

	public void selectUtahSpecialTruck(String utahSpecialTruckddValue) {
		selectFromDropdownByVisibleText(amendVehicleUtahSpecialTruckDd, utahSpecialTruckddValue);
	}

	public void selectCNewVehicle(String newVehicleddValue) {
		selectFromDropdownByVisibleText(amendVehicleNewVehicleDd, newVehicleddValue);
	}

	public void selectCOSpecialTruck(String coSpecialTruckddValue) {
		selectFromDropdownByVisibleText(amendVehicleCOSpecialTruckDd, coSpecialTruckddValue);
	}

	public void selectHVUTForm2290(String hvutForm2290ddValue) {
		selectFromDropdownByVisibleText(amendVehicleHVUTForm2290Dd, hvutForm2290ddValue);
	}

	public void selectLeaseContract(String leaseContractddValue) {
		selectFromDropdownByVisibleText(amendVehicleLeaseContractDd, leaseContractddValue);
	}

	public void selectTitleDocument(String titleDocumentddValue) {
		selectFromDropdownByVisibleText(amendVehicleTitleDocumentDd, titleDocumentddValue);
	}

	public void selectPlateReturndoc(String plateReturndocddValue) {
		selectFromDropdownByVisibleText(amendVehiclePlateReturnDocDd, plateReturndocddValue);
	}

	public void selectAffidavitDoc(String affidavitDocddValue) {
		selectFromDropdownByVisibleText(amendVehicleAffidavitDocDd, affidavitDocddValue);
	}

	public void selectPropertyTax(String propertyTaxddValue) {
		selectFromDropdownByVisibleText(amendVehiclePropertyTaxDd, propertyTaxddValue);
	}

	public void selectSafetyChangedd(String amendVehicleSafetyChangeValue) {
		selectFromDropdownByVisibleText(amendVehicleSafetyChangeDd, amendVehicleSafetyChangeValue);
	}

	public String fetchAmendVehicleBodyType() {
		return FetchDropdownSelectedValue(amendVehicleBodyTypeDd);
	}

}

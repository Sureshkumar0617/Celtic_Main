package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class VehicleAdd extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public VehicleAdd(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[@for='CopyVIN']")
	WebElement addVinLbl;

	@FindBy(xpath = "//input[@id='CopyVIN']")
	WebElement addVinTxt;

	@FindBy(xpath = "//label[@for='CopyUnitNo']")
	WebElement addUnitNoLbl;

	@FindBy(xpath = "//input[@id='CopyUnitNo']")
	WebElement addUnitNoTxt;

	@FindBy(xpath = "//label[@for='CopyPlateNo']")
	WebElement addPlateNoLbl;

	@FindBy(xpath = "//input[@id='CopyPlateNo']")
	WebElement addPlateNoTxt;

	@FindBy(xpath = "//input[@id='btnSearch']")
	WebElement addSearchBtn;

	@FindBy(xpath = "//label[@for='NoOfVehicle']")
	WebElement addNoOfVehiclesLbl;

	@FindBy(xpath = "//input[@id='NoOfVehicle']")
	WebElement addNoOfVehiclesTxt;

	@FindBy(xpath = "//label[@for='RegistrantType']")
	WebElement addRegistrationTypeLbl;

	@FindBy(xpath = "//select[@id='RegistrantType']")
	WebElement addRegistrationTypeDd;

	@FindBy(xpath = "//label[@for='VehDtlUnitNo']")
	WebElement addDetailsUnitNoLbl;

	@FindBy(xpath = "//input[@id='VehDtlUnitNo']")
	WebElement addDetailsUnitNoTxt;

	@FindBy(xpath = "//label[@for='VehDtlWeightGroupNo']")
	WebElement addDetailsWeightGroupNoLbl;

	@FindBy(xpath = "//select[@id='VehDtlWeightGroupNo']")
	WebElement addDetailsWeightGroupNoDd;

	@FindBy(xpath = "//label[@for='VehDtlYear']")
	WebElement addDetailsYearLbl;

	@FindBy(xpath = "//input[@id='VehDtlYear']")
	WebElement addDetailsYearTxt;

	@FindBy(xpath = "//label[@for='VehDtlBodyStyle']")
	WebElement addDetailsBodyTypeLbl;

	@FindBy(xpath = "//select[@id='VehDtlBodyStyle']")
	WebElement addDetailsBodyTypeDd;

	@FindBy(xpath = "//label[@for='VehDtlMake']")
	WebElement addDetailsMakeLbl;

	@FindBy(xpath = "//select[@id='VehDtlMake']")
	WebElement addDetailsMakeDd;

	@FindBy(xpath = "//label[@for='VehDtlAxles']")
	WebElement addDetailsAxleLbl;

	@FindBy(xpath = "//input[@id='VehDtlAxles']")
	WebElement addDetailsAxleTxt;

	@FindBy(xpath = "//label[@for='VehDtlCombinedAxles']")
	WebElement addDetailsCombinedAxleLbl;

	@FindBy(xpath = "//input[@id='VehDtlCombinedAxles']")
	WebElement addDetailsCombinedAxleTxt;

	@FindBy(xpath = "//label[@for='VehDtlFuleType']")
	WebElement addDetailsFuelTypeLbl;

	@FindBy(xpath = "//select[@id='VehDtlFuleType']")
	WebElement addDetailsFuelTypeDd;

	@FindBy(xpath = "//label[@for='VehDtlSeats']")
	WebElement addDetailsSeatsLbl;

	@FindBy(xpath = "//input[@id='VehDtlSeats']")
	WebElement addDetailsSeatsTxt;

	@FindBy(xpath = "//label[@for='VehDtlColor']")
	WebElement addDetailsColorLbl;

	@FindBy(xpath = "//select[@id='VehDtlColor']")
	WebElement addDetailsColorDd;

	@FindBy(xpath = "//label[@for='VehDtlUnladenWeight']")
	WebElement addDetailsUnladenWeightLbl;

	@FindBy(xpath = "//input[@id='VehDtlUnladenWeight']")
	WebElement addDetailsUnladenWeightTxt;

	@FindBy(xpath = "//label[@for='VehDtlMaxDesiredWeight']")
	WebElement addDetailsGrossWeightLbl;

	@FindBy(xpath = "//input[@id='VehDtlMaxDesiredWeight']")
	WebElement addDetailsGrossWeightTxt;

	@FindBy(xpath = "//label[@for='PurchaseDate']")
	WebElement addDetailsPurchaseDateLbl;

	@FindBy(xpath = "//input[@id='PurchaseDate']")
	WebElement addDetailsPurchaseDatedtPicker;

	@FindBy(xpath = "//label[@for='PurchasePrice']")
	WebElement addDetailsPurchasePriceLbl;

	@FindBy(xpath = "//input[@id='PurchasePrice']")
	WebElement addDetailsPurchasePriceTxt;

	@FindBy(xpath = "//label[@for='FactoryPrice']")
	WebElement addDetailsFactoryPriceLbl;

	@FindBy(xpath = "//input[@id='FactoryPrice']")
	WebElement addDetailsFactoryPriceTxt;

	@FindBy(xpath = "//label[@for='IsTearIndicator']")
	WebElement addDetailsTVRLbl;

	@FindBy(xpath = "//input[@id='IsTearIndicator']")
	WebElement addDetailsTVRChk;

	@FindBy(xpath = "//label[@for='NoOfDays']")
	WebElement addDetailsTVRDaysLbl;

	@FindBy(xpath = "//input[@id='NoOfDays']")
	WebElement addDetailsTVRDaysTxt;

	@FindBy(xpath = "//label[@for='VehDtlTitleJurisdiction']")
	WebElement addDetailsTitleJurisdictionLbl;

	@FindBy(xpath = "//select[@id='VehDtlTitleJurisdiction']")
	WebElement addDetailsTitleJurisdictionDd;

	@FindBy(xpath = "//label[@for='VehDtlTitleNo']")
	WebElement addDetailsTitleNoLbl;

	@FindBy(xpath = "//input[@id='VehDtlTitleNo']")
	WebElement addDetailsTitleNoTxt;

	@FindBy(xpath = "//label[@for='VehDtlCountyPlateType']")
	WebElement addDetailsInStatePlateTypeLbl;

	@FindBy(xpath = "//select[@id='VehDtlCountyPlateType']")
	WebElement addDetailsInStatePlateTypeDd;

	@FindBy(xpath = "//label[@for='VehDtlCountyWeight']")
	WebElement addDetailsInStateWeightLbl;

	@FindBy(xpath = "//input[@id='VehDtlCountyWeight']")
	WebElement addDetailsInStateWeightTxt;

	@FindBy(xpath = "//label[@for='CountyPlateExpiration']")
	WebElement addDetailsInstateExpirationDateLbl;

	@FindBy(xpath = "//input[@id='CountyPlateExpiration']")
	WebElement addDetailsInstateExpirationDatedtPicker;

	@FindBy(xpath = "//label[@for='VehDtlCountyPlate']")
	WebElement addDetailsInStatePlateLbl;

	@FindBy(xpath = "//input[@id='VehDtlCountyPlate']")
	WebElement addDetailsInStatePlateTxt;;

	@FindBy(xpath = "//label[@for='CountyFee']")
	WebElement addDetailsInStateFeeLbl;

	@FindBy(xpath = "//input[@id='CountyFee']")
	WebElement addDetailsInStateFeeTxt;

	@FindBy(xpath = "//label[@for='OwnerDtlName']")
	WebElement addDetailsOwnerNameLbl;

	@FindBy(xpath = "//input[@id='OwnerDtlName']")
	WebElement addDetailsOwnerNameTxt;

	@FindBy(xpath = "//label[@for='OwnerPhoneNo']")
	WebElement addDetailsOwnerPhoneNoLbl;

	@FindBy(xpath = "//input[@id='OwnerPhoneNo']")
	WebElement addDetailsOwnerPhoneNoTxt;

	@FindBy(xpath = "//label[@for='LeaseOrOwner']")
	WebElement addDetailsSafetyResponsibilityLbl;

	@FindBy(xpath = "//select[@id='LeaseOrOwner']")
	WebElement addDetailsSafetyResponsibilitydd;

	@FindBy(xpath = "//label[@for='IsLease']")
	WebElement addDetailsIsLeaseLbl;

	@FindBy(xpath = "//input[@id='IsLease']")
	WebElement addDetailsIsLeaseChk;

	@FindBy(xpath = "//label[@for='VehDtlSafetyUsdot']")
	WebElement addDetailsSafetyUSDOTLbl;

	@FindBy(xpath = "//input[@id='VehDtlSafetyUsdot']")
	WebElement addDetailsSafetyUSDOTTxt;

	@FindBy(xpath = "//label[@for='VehDtlSafetyTin']")
	WebElement addDetailsSafetyTPIDLbl;

	@FindBy(xpath = "//input[@id='VehDtlSafetyTin']")
	WebElement addDetailsSafetyTPIDTxt;

	@FindBy(xpath = "//label[@for='SaftyChangeDuringYear']")
	WebElement addDetailsSafetyChangeLbl;

	@FindBy(xpath = "//select[@id='SaftyChangeDuringYear']")
	WebElement addDetailsSafetyChangeDd;

	@FindBy(xpath = "//label[@for='IsUseExistingPlate']")
	WebElement addDetailsUseExistingPlateLbl;

	@FindBy(xpath = "//input[@id='IsUseExistingPlate']")
	WebElement addDetailsUseExistingPlateChk;

	@FindBy(xpath = "//label[@for='ExistingPlate']")
	WebElement addDetailsExistingPlateLbl;

	@FindBy(xpath = "//input[@id='ExistingPlate']")
	WebElement addDetailsExistingPlateTxt;

	@FindBy(xpath = "//label[@for='VehDtlIsChangeUsdotAddress']")
	WebElement addDetailsChangeUSDOTAddressLbl;

	@FindBy(xpath = "//input[@id='VehDtlIsChangeUsdotAddress']")
	WebElement addDetailsChangeUSDOTAddressChk;

	@FindBy(xpath = "//label[@for='CabCardFeeOverride']")
	WebElement addDetailsGradeCrossingFeeLbl;

	@FindBy(xpath = "//input[@id='CabCardFeeOverride']")
	WebElement addDetailsGradeCrossingFeeChk;

	@FindBy(xpath = "//label[@for='TAFeeOverride']")
	WebElement addDetailsBicenetennialLbl;

	@FindBy(xpath = "//input[@id='TAFeeOverride']")
	WebElement addDetailsBicenetenniallChk;

	@FindBy(xpath = "//label[@for='SecondPlateF']")
	WebElement addDetailsSecondPlateLbl;

	@FindBy(xpath = "//input[@id='SecondPlateF']")
	WebElement addDetailsSecondPlateChk;

	@FindBy(xpath = "//label[@for='SecondPlateFeeOverrideF']")
	WebElement addDetailsSecondPlateFeeLbl;

	@FindBy(xpath = "//input[@id='SecondPlateFeeOverrideF']")
	WebElement addDetailsSecondPlateFeeChk;

	@FindBy(xpath = "//label[@for='ColoradoDistanceFlag']")
	WebElement addVehicleColorado10KIndicatorLbl;

	@FindBy(xpath = "//select[@id='ColoradoDistanceFlag']")
	WebElement addVehicleColorado10KIndicatordd;

	@FindBy(xpath = "//label[@for='ColoradoTrailerFlag']")
	WebElement addVehicleColoradoTrailerLbl;

	@FindBy(xpath = "//select[@id='ColoradoTrailerFlag']")
	WebElement addVehicleColoradoTrailerdd;

	@FindBy(xpath = "//label[@for='UTSpecialTruckFlag']")
	WebElement addVehicleUtahSpecialTruckLbl;

	@FindBy(xpath = "//select[@id='UTSpecialTruckFlag']")
	WebElement addVehicleUtahSpecialTruckdd;

	@FindBy(xpath = "//label[@for='NewUsedF']")
	WebElement addVehicleNewVehicleLbl;

	@FindBy(xpath = "//select[@id='NewUsedF']")
	WebElement addVehicleNewVehicledd;

	@FindBy(xpath = "//label[@for='CoSpecialTruck']")
	WebElement addVehicleCOSpecialTruckLbl;

	@FindBy(xpath = "//select[@id='CoSpecialTruck']")
	WebElement addVehicleCOSpecialTruckdd;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[6]")
	WebElement addVehicleTrackingSubHdr;

	@FindBy(xpath = "//label[@for='HVUTForm2290']")
	WebElement addVehicleHVUTForm2290Lbl;

	@FindBy(xpath = "//select[@id='HVUTForm2290']")
	WebElement addVehicleHVUTForm2290dd;

	@FindBy(xpath = "//label[@for='LeaseContract']")
	WebElement addVehicleLeaseContractLbl;

	@FindBy(xpath = "//select[@id='LeaseContract']")
	WebElement addVehicleLeaseContractdd;

	@FindBy(xpath = "//label[@for='TitleDocument']")
	WebElement addVehicleTitleDocumentLbl;

	@FindBy(xpath = "//select[@id='TitleDocument']")
	WebElement addVehicleTitleDocumentdd;

	@FindBy(xpath = "//label[@for='PlateReturndoc']")
	WebElement addVehiclePlateReturndocbl;

	@FindBy(xpath = "//select[@id='PlateReturndoc']")
	WebElement addVehiclePlateReturndocdd;

	@FindBy(xpath = "//label[@for='AffidavitDoc']")
	WebElement addVehicleAffidavitDocLbl;

	@FindBy(xpath = "//select[@id='AffidavitDoc']")
	WebElement addVehicleAffidavitDocdd;

	@FindBy(xpath = "//input[@id='CopyUnitNo']")
	WebElement vehicleListEnterUnitno;

	@FindBy(xpath = "//input[@id='btnFind']")
	WebElement vehicleListVehicleSearch;

	@FindBy(xpath = "//a[@id='lnkGridSelectgvVehicleList']")
	WebElement vehicleListVehicleunit1;

	@FindBy(xpath = "//input[@id='btnCancel']")
	WebElement vehicleListCancelVehicle;

	public void enterVINNumber(String vinNumberValue) {
		webEditTxtChange(addVinTxt, vinNumberValue);
	}

	public void clickSearch() throws InterruptedException {
		clickElement(addSearchBtn);
	}

	public void enterUnitNumber(String unitNumber) {
		webEditTxtChange(addUnitNoTxt, unitNumber);
	}

	public void enterDetailsUnitNumber(String unitNumberValue) {
		webEditTxtChange(addDetailsUnitNoTxt, unitNumberValue);
	}

	public void selectBodyType(String bodyTypeValue) throws InterruptedException {
		selectFromDropdownByVisibleText(addDetailsBodyTypeDd, bodyTypeValue);
	}

	public void selectWeightGroupNumber(String bodyTypeValue) {
		selectFromDropdownByVisibleText(addDetailsWeightGroupNoDd, bodyTypeValue);
	}

	public void enterseats(String seatValue) {
		try{
		webEditTxtChange(addDetailsSeatsTxt, seatValue);
		}
		catch (Exception e) {
			logg.info("Seats are not entered");
		}
	}

	public void enterYear(String yearValue) {
		webEditTxtChange(addDetailsYearTxt, yearValue);
	}

	public void selectMake(String makeValue) {
		selectFromDropdownByVisibleText(addDetailsMakeDd, makeValue);
	}

	public void enterAxle(String axleValue) {
		webEditTxtChange(addDetailsAxleTxt, axleValue);
	}

	public void enterCombinedAxleEmpty(String combinedAxleValue) {
		if (FetchTextBoxValuewithattribute(addDetailsCombinedAxleTxt, "value").isEmpty()) {
			webEditTxtChange(addDetailsCombinedAxleTxt, combinedAxleValue);
		}
	}

	public void enterCombinedAxle(String combinedAxleValue) {
		webEditTxtChange(addDetailsCombinedAxleTxt, combinedAxleValue);
	}

	public void selectFuelType(String fuelTypeValue) {
		selectFromDropdownByVisibleText(addDetailsFuelTypeDd, fuelTypeValue);
	}

	public void selectColor(String colorValue) {
		selectFromDropdownByVisibleText(addDetailsColorDd, colorValue);
	}

	public void enterUnladenWeight(String unladeWeightValue) {
		webEditTxtChange(addDetailsUnladenWeightTxt, unladeWeightValue);
	}

	public void enterPurchaseDate(String purchaseDate) {
		clickElement(addDetailsPurchaseDatedtPicker);
		webEditTxt(addDetailsPurchaseDatedtPicker, purchaseDate);
	}

	public void enterPurchaseprice(String purchasePriceValue) {
		webEditTxtChange(addDetailsPurchasePriceTxt, purchasePriceValue);
	}

	public void enterFactoryPrice(String factoryPriceValue) {
		webEditTxtChange(addDetailsFactoryPriceTxt, factoryPriceValue);
	}

	public void checkTVR() {
		if (isPresentAndDisplayed(addDetailsTVRChk)) {
			webCheckON(addDetailsTVRChk);
		}
	}

	public void checkUseexistingplate() {
		webCheckON(addDetailsUseExistingPlateChk);
	}

	public void enterTVRDays(String TVRDaysValue) {
		webEditTxtChange(addDetailsTVRDaysTxt, TVRDaysValue);
	}

	public void selectTitleJurisdiction(String titleJurisdictionValue) {
		selectFromDropdownByVisibleText(addDetailsTitleJurisdictionDd, titleJurisdictionValue);
	}

	public void enterTitleNo(String titleNumberValue) {
		webEditTxtChange(addDetailsTitleNoTxt, titleNumberValue);
	}

	public void selectInStatePlateType(String inStatePlateTypeValue) {
		selectFromDropdownByVisibleText(addDetailsInStatePlateTypeDd, inStatePlateTypeValue);
	}

	public void enterInStatePlateWeight(String inStatePlateWeightValue) {
		webEditTxtChange(addDetailsInStateWeightTxt, inStatePlateWeightValue);
	}

	public void emptyenterInStateExpirationDate(String inStateExpirationDateValue) {
		if (FetchTextBoxValuewithattribute(addDetailsInstateExpirationDatedtPicker, "value").isEmpty()) {
			clickElement(addDetailsInstateExpirationDatedtPicker);
			webEditTxt(addDetailsInstateExpirationDatedtPicker, inStateExpirationDateValue);
		}
	}

	public void enterInStatePlate(String inStatePlateValue) {
		webEditTxtChange(addDetailsInStatePlateTxt, inStatePlateValue);
	}

	public void enterInStateFee(String inStateFeeValue) {
		webEditTxtChange(addDetailsInStateFeeTxt, inStateFeeValue);
	}

	public String fetchOwnerName() {
		return FetchTextBoxValuewithattribute(addDetailsOwnerNameTxt, "value");
	}

	public void enterOwnerName(String ownerNameValue) {
		if (FetchTextBoxValuewithattribute(addDetailsOwnerNameTxt, "value").isEmpty()) {
			webEditTxtChange(addDetailsOwnerNameTxt, ownerNameValue);
		}
	}

	public void enterOwnerPhoneNumber(String inSlateFeeValue) {
		webEditTxtChange(addDetailsOwnerPhoneNoTxt, inSlateFeeValue);
	}

	public void selectSafetyResponsibility(String safetyResponsibilityValue) {
		selectFromDropdownByVisibleText(addDetailsSafetyResponsibilitydd, safetyResponsibilityValue);
	}

	public void checkLease() {
		webCheckON(addDetailsIsLeaseChk);
	}

	public void enterSafetyUSDOT(String safetyUSDOTValue) {
		webEditTxtChange(addDetailsSafetyUSDOTTxt, safetyUSDOTValue);
	}

	public void enterSafetyTPID(String safetyTPIDValue) {
		webEditTxtChange(addDetailsSafetyTPIDTxt, safetyTPIDValue);
	}

	public void selectSafetyChange(String safetyChangeValue) {
		selectFromDropdownByVisibleText(addDetailsSafetyChangeDd, safetyChangeValue);
	}

	public void checkExistingPlate() {
		if (isPresentAndDisplayed(addDetailsUseExistingPlateChk)) {
			webCheckON(addDetailsUseExistingPlateChk);
		}
	}

	public void enterExistingPlate(String existingPlateValue) {
		webEditTxtChange(addDetailsExistingPlateTxt, existingPlateValue);
	}

	public void checkChangeUSDOTAddress() {
		webCheckON(addDetailsChangeUSDOTAddressChk);
	}

	public void checkGradeCrossingFee() {
		webCheckON(addDetailsGradeCrossingFeeChk);
	}

	public void checkBicenetenniall() {
		webCheckON(addDetailsBicenetenniallChk);
	}

	public void checkSecondPlate() {
		webCheckON(addDetailsSecondPlateChk);
	}

	public void checkSecondPlateFee() {
		webCheckON(addDetailsSecondPlateFeeChk);
	}

	public void selectColorado10kIndicator(String colorado10kIndicatorddValue) {
		selectFromDropdownByVisibleText(addVehicleColorado10KIndicatordd, colorado10kIndicatorddValue);
	}

	public void selectColoradoTrailer(String coloradoTrailerddValue) {
		selectFromDropdownByVisibleText(addVehicleColoradoTrailerdd, coloradoTrailerddValue);
	}

	public void selectUtahSpecialTruck(String utahSpecialTruckddValue) {
		selectFromDropdownByVisibleText(addVehicleUtahSpecialTruckdd, utahSpecialTruckddValue);
	}

	public void selectCNewVehicle(String newVehicleddValue) {
		selectFromDropdownByVisibleText(addVehicleNewVehicledd, newVehicleddValue);
	}

	public void selectCOSpecialTruck(String cOSpecialTruckddValue) {
		selectFromDropdownByVisibleText(addVehicleCOSpecialTruckdd, cOSpecialTruckddValue);
	}

	public void selectHVUTForm2290(String hvutForm2290ddValue) {
		selectFromDropdownByVisibleText(addVehicleHVUTForm2290dd, hvutForm2290ddValue);
	}

	public void selectLeaseContract(String leaseContractddValue) {
		selectFromDropdownByVisibleText(addVehicleLeaseContractdd, leaseContractddValue);
	}

	public void selectTitleDocument(String titleDocumentddValue) {
		selectFromDropdownByVisibleText(addVehicleTitleDocumentdd, titleDocumentddValue);
	}

	public void selectPlateReturndoc(String plateReturndocddValue) {
		selectFromDropdownByVisibleText(addVehiclePlateReturndocdd, plateReturndocddValue);
	}

	public void selectAffidavitDoc(String affidavitDocddValue) {
		selectFromDropdownByVisibleText(addVehicleAffidavitDocdd, affidavitDocddValue);
	}

	public void updatevehiclelistUnit(String unitNumber) {
		webEditTxtChange(vehicleListEnterUnitno, unitNumber);
	}

	public void updatevehiclelistsearch() throws InterruptedException {
		clickElement(vehicleListVehicleSearch);
	}

	public void selectFirstHandIcon() {
		clickElement(vehicleListVehicleunit1);
	}

	public void UpdateFactoryPrice(String factoryPrice) {
		webEditTxtChange(addDetailsFactoryPriceTxt, factoryPrice);
	}

	public void CancelVehicle() {
		clickElement(vehicleListCancelVehicle);
	}
}

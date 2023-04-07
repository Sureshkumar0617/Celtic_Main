package com.celtic.automation.cmcs.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class Reinstatement extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public Reinstatement(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='PageHeader row']//h3[contains(@class,'-left')]")
	WebElement pageHeaderRow;

	@FindBy(xpath = "//span[text()='Account No.']")
	WebElement accountNoLbl;

	@FindBy(xpath = "//input[@id='AccountNo']")
	WebElement accountNo;

	@FindBy(xpath = "//span[text()='Fleet No.']")
	WebElement fleetNoLbl;

	@FindBy(xpath = "//input[@id='FleetNo']")
	WebElement fleetNo;

	@FindBy(xpath = "//span[text()='Supplement No.']")
	WebElement supplementNoLbl;

	@FindBy(xpath = "//input[@id='SupplementNo']")
	WebElement supplementNo;

	@FindBy(xpath = "//span[text()='Legal Name']")
	WebElement legalNameLbl;

	@FindBy(xpath = "//input[@id='RegistrantName']")
	WebElement legalName;

	@FindBy(xpath = "//span[text()='DBA Name']")
	WebElement dbaNameLbl;

	@FindBy(xpath = "//input[@id='DBAName']")
	WebElement dbaName;

	@FindBy(xpath = "//span[text()='Fleet Type']")
	WebElement fleetTypeLbl;

	@FindBy(xpath = "//input[@id='CMVFleetType']")
	WebElement fleetType;

	@FindBy(xpath = "//span[text()='Fleet Effective Date']")
	WebElement fleetEffectiveDateLbl;

	@FindBy(xpath = "//input[@id='FleetEffDate']")
	WebElement fleetEffectiveDate;

	@FindBy(xpath = "//span[text()='Fleet Expiration Month / Year']")
	WebElement fleetExpirationMonthyearLbl;

	@FindBy(xpath = "//input[@id='FltExpMonth']")
	WebElement fleetExpirationMonth;

	@FindBy(xpath = "//input[@id='FltExpYear']")
	WebElement fleetExpirationyear;

	@FindBy(xpath = "//span[text()='Supplement Desc.']")
	WebElement supplementDescLbl;

	@FindBy(xpath = "//input[@id='SupplementDesc']")
	WebElement supplementDesc;

	@FindBy(xpath = "//span[text()='Reporting Period From']")
	WebElement reportingPeriodFromLbl;

	@FindBy(xpath = "//input[@id='ReportingPeriodFrom']")
	WebElement reportingPeriodFrom;

	@FindBy(xpath = "//span[text()='Reporting Period To']")
	WebElement reportingPeriodToLbl;

	@FindBy(xpath = "//input[@id='ReportingPeriodTo']")
	WebElement reportingPeriodTo;

	@FindBy(xpath = "//span[text()='USDOT No.']")
	WebElement usdotNbrLbl;

	@FindBy(xpath = "//input[@id='UsdotNbr']")
	WebElement usdotNbr;

	@FindBy(xpath = "//span[text()='Avg. Per Vehicle Distance Chart']")
	WebElement estimatedDistanceChartLbl;

	@FindBy(xpath = "//input[@id='EstimatedDistanceChart']")
	WebElement estimatedDistanceChart;

	@FindBy(xpath = "//span[text()='Override Contiguous Jur.']")
	WebElement overrideContJurLbl;

	@FindBy(xpath = "//input[@id='OverrideContJur']")
	WebElement overrideContJur;

	@FindBy(xpath = "//span[text()='Actual Distance']")
	WebElement actualDistanceGenLbl;

	@FindBy(xpath = "//input[@id='ActualDistanceGen']")
	WebElement actualDistanceGen;

	@FindBy(xpath = "//span[text()='Estimated Distance']")
	WebElement firstYearEstimatedMileageLbl;

	@FindBy(xpath = "//input[@id='FirstYearEstimatedMileage']")
	WebElement firstYearEstimatedMileage;

	@FindBy(xpath = "//span[text()='Total Fleet Distance']")
	WebElement totalFleetDistanceGenLbl;

	@FindBy(xpath = "//input[@id='TotalFleetDistanceGen']")
	WebElement totalFleetDistanceGen;

	@FindBy(xpath = "//span[text()='Do you have actual distance for the reporting period?']")
	WebElement frpMlgQuetionLbl;

	@FindBy(xpath = "//input[@id='FRPMlgQuetion']")
	WebElement frpMlgQuetion;

	@FindBy(xpath = "//span[text()='Distance Type']")
	WebElement distanceTypeLbl;

	@FindBy(xpath = "//input[@id='DistanceType']")
	WebElement distanceType;

	@FindBy(xpath = "//span[text()='We certify that the actual distance submitted is accurate']")
	WebElement actualDistConfirmationLbl;

	@FindBy(xpath = "//input[@id='ActualDistConfirmation']")
	WebElement actualDistConfirmation;

	@FindBy(xpath = "//label[@for='RegMonth']")
	WebElement billingRegisterMonthLbl;

	@FindBy(xpath = "//input[@id='RegMonth']")
	WebElement billingRegisterMonthTxt;

	@FindBy(xpath = "//label[@for='NoOfPowerVehicle']")
	WebElement billingNoOfVehiclesinSuppLbl;

	@FindBy(xpath = "//input[@id='NoOfPowerVehicle']")
	WebElement billingNoOfVehiclesInSuppTxt;

	@FindBy(xpath = "//label[@for='SupplementStatus']")
	WebElement billingSupplementStatusLbl;

	@FindBy(xpath = "//input[@id='SupplementStatus']")
	WebElement billingSupplementStatusTxt;

	@FindBy(xpath = "//label[@for='EnterpriseEscrow']")
	WebElement billingEnterpriseSystemCreditLbl;

	@FindBy(xpath = "//input[@id='EnterpriseEscrow']")
	WebElement billingEnterpriseSystemCreditTxt;

	@FindBy(xpath = "//label[@for='IRPEscrow']")
	WebElement billingIRPSystemCreditLbl;

	@FindBy(xpath = "//input[@id='IRPEscrow']")
	WebElement billingIRPSystemCreditTxt;

	@FindBy(xpath = "//label[@for='InvoiceDate']")
	WebElement billingInvoiceDateLbl;

	@FindBy(xpath = "//input[@id='InvoiceDate']")
	WebElement billingInvoiceDatedtPicker;

	@FindBy(xpath = "//label[@for='ReceiptDate']")
	WebElement billingApplicationReceiptDateLbl;

	@FindBy(xpath = "//input[@id='ReceiptDate']")
	WebElement billingApplicationReceiptDatedtPicker;

	@FindBy(xpath = "//label[@for='PaymentDate']")
	WebElement billingPaymentDateLbl;

	@FindBy(xpath = "//input[@id='PaymentDate']")
	WebElement billingPaymentDatedtPicker;

	@FindBy(xpath = "//label[@for='ExchangeRate']")
	WebElement billingExchangeRateLbl;

	@FindBy(xpath = "//input[@id='ExchangeRate']")
	WebElement billingExchangeRateTxt;

	@FindBy(xpath = "//label[@for='ManualAdjBaseJur']")
	WebElement billingManualAdjBaseJurLbl;

	@FindBy(xpath = "//input[@id='ManualAdjBaseJur']")
	WebElement billingManualAdjBaseJurTxt;

	@FindBy(xpath = "//label[@for='BatchBilling']")
	WebElement billingBatchBillingLbl;

	@FindBy(xpath = "//input[@id='BatchBilling']")
	WebElement billingBatchBillingChk;

	@FindBy(xpath = "//label[@for='IsUseOneTimeMailing']")
	WebElement billingIsUseOneTimeMailingLbl;

	@FindBy(xpath = "//input[@id='IsUseOneTimeMailing']")
	WebElement billingIsUseOneTimeMailingChk;

	@FindBy(xpath = "//a[@id='BillingBtn']")
	WebElement billingBillingTab;

	@FindBy(xpath = "//input[@id='ReceiptDate']")
	WebElement billingAppReceiptDatedPicker;

	@FindBy(xpath = "//h3[contains(@class,'pull-left')]")
	WebElement billingSubhdr;

	public void enterreceiptdate(String previousDate) {
		if (billingAppReceiptDatedPicker.getAttribute("value").isEmpty()) {
			clickElement(billingAppReceiptDatedPicker);
			webEditTxt(billingAppReceiptDatedPicker, previousDate);
		}
	}

	public String distanceAccountNumberLbl() {
		return FetchTextBoxValuewithText(accountNoLbl);
	}

	public String distanceAccountNumber() {
		return FetchTextBoxValuewithattributedisabled(accountNo, "value");
	}

	public String distanceFleetNumberLbl() {
		return FetchTextBoxValuewithText(fleetNoLbl);
	}

	public String distanceFleetNumber() {
		return FetchTextBoxValuewithattributedisabled(fleetNo, "value");
	}

	public String distanceSupplementNumberLbl() {
		return FetchTextBoxValuewithText(supplementNoLbl);
	}

	public String distanceSupplementNumber() {
		return FetchTextBoxValuewithattributedisabled(supplementNo, "value");
	}

	public String distanceLegalNameLbl() {
		return FetchTextBoxValuewithText(legalNameLbl);
	}

	public String distanceLegalName() {
		return FetchTextBoxValuewithattributedisabled(legalName, "value");
	}

	public String distanceDbaNameLbl() {
		return FetchTextBoxValuewithText(dbaNameLbl);
	}

	public String distanceDbaName() {
		return FetchTextBoxValuewithattributedisabled(dbaName, "value");
	}

	public String distanceFleetTypeLbl() {
		return FetchTextBoxValuewithText(fleetTypeLbl);
	}

	public String distanceFleetType() {
		return FetchTextBoxValuewithattributedisabled(fleetType, "value");
	}

	public String distanceEffectiveDateLbl() {
		return FetchTextBoxValuewithText(fleetEffectiveDateLbl);
	}

	public String distanceEffectiveDate() {
		return FetchTextBoxValuewithattributedisabled(fleetEffectiveDate, "value");
	}

	public String distanceExpirateMonthYearLbl() {
		return FetchTextBoxValuewithText(fleetExpirationMonthyearLbl);
	}

	public String distanceExpirateMonth() {
		return FetchTextBoxValuewithattributedisabled(fleetExpirationMonth, "value");
	}

	public String distanceExpirateYear() {
		return FetchTextBoxValuewithattributedisabled(fleetExpirationyear, "value");
	}

	public String distanceSupplementDescLbl() {
		return FetchTextBoxValuewithText(supplementDescLbl);
	}

	public String distanceSupplementDesc() {
		return FetchTextBoxValuewithattributedisabled(supplementDesc, "value");
	}

	public String distancereportingPeriodFromLbl() {
		return FetchTextBoxValuewithText(reportingPeriodFromLbl);
	}

	public String distanceReportingPeriodFrom() {
		return FetchTextBoxValuewithattributedisabled(reportingPeriodFrom, "value");
	}

	public String distancereportingPeriodToLbl() {
		return FetchTextBoxValuewithText(reportingPeriodToLbl);
	}

	public String distanceReportingPeriodTo() {
		return FetchTextBoxValuewithattributedisabled(reportingPeriodTo, "value");
	}

	public String distanceusdotNbrLbl() {
		return FetchTextBoxValuewithText(usdotNbrLbl);
	}

	public String distanceusdotNbr() {
		return FetchTextBoxValuewithattributedisabled(usdotNbr, "value");
	}

	public String distanceEstimatedDistanceChartLbl() {
		return FetchTextBoxValuewithText(estimatedDistanceChartLbl);
	}

	public String distanceEstimatedDistanceChart() {
		return FetchTextBoxValuewithattributedisabled(estimatedDistanceChart, "value");
	}

	public String distanceOverrideContJurLbl() {
		return FetchTextBoxValuewithText(overrideContJurLbl);
	}

	public String distanceOverrideContJur() {
		return FetchTextBoxValuewithattributedisabled(overrideContJur, "value");
	}

	public String distanceActualDistanceLbl() {
		return FetchTextBoxValuewithText(actualDistanceGenLbl);
	}

	public String distanceActualDistance() {
		return FetchTextBoxValuewithattributedisabled(actualDistanceGen, "value");
	}

	public String distanceEstimatedDistanceLbl() {
		return FetchTextBoxValuewithText(firstYearEstimatedMileageLbl);
	}

	public String distanceEstimatedDistance() {
		return FetchTextBoxValuewithattributedisabled(firstYearEstimatedMileage, "value");
	}

	public String distanceTotalFleetDistanceLbl() {
		return FetchTextBoxValuewithText(totalFleetDistanceGenLbl);
	}

	public String distanceTotalFleetDistance() {
		return FetchTextBoxValuewithattributedisabled(totalFleetDistanceGen, "value");
	}

	public String distanceFRPMlgQuetionLbl() {
		return FetchTextBoxValuewithText(frpMlgQuetionLbl);
	}

	public String distanceFRPMlgQuetion() {
		return FetchTextBoxValuewithattributedisabled(frpMlgQuetion, "value");
	}

	public String distanceDistanceTypeLbl() {
		return FetchTextBoxValuewithText(distanceTypeLbl);
	}

	public String distanceDistanceType() {
		return FetchTextBoxValuewithattributedisabled(distanceType, "value");
	}

	public String distanceActualDistConfirmationLbl() {
		return FetchTextBoxValuewithText(actualDistConfirmationLbl);
	}

	public String distanceActualDistConfirmation() {
		return FetchTextBoxValuewithattributedisabled(actualDistConfirmation, "value");
	}

	public String fetchRegisterMonthLbl() {
		return FetchTextBoxValuewithText(billingRegisterMonthLbl);
	}

	public String fetchRegisterMonth() {
		return FetchTextBoxValuewithattribute(billingRegisterMonthTxt, "value");
	}

	public String fetchNoOfVehiclesinSuppLbl() {
		return FetchTextBoxValuewithText(billingNoOfVehiclesinSuppLbl);
	}

	public String fetchNoOfVehiclesinSupp() {
		return FetchTextBoxValuewithattribute(billingNoOfVehiclesInSuppTxt, "value");
	}

	public String fetchSupplementStatusLbl() {
		return FetchTextBoxValuewithText(billingSupplementStatusLbl);
	}

	public String fetchSupplementStatus() {
		return FetchTextBoxValuewithattribute(billingSupplementStatusTxt, "value");
	}

	public String fetchEnterpriseSystemCreditLbl() {
		return FetchTextBoxValuewithText(billingEnterpriseSystemCreditLbl);
	}

	public String fetchEnterpriseSystemCredit() {
		return FetchTextBoxValuewithattribute(billingEnterpriseSystemCreditTxt, "value");
	}

	public String fetchIRPSystemCreditLbl() {
		return FetchTextBoxValuewithText(billingIRPSystemCreditLbl);
	}

	public String fetchIRPSystemCredit() {
		return FetchTextBoxValuewithattribute(billingIRPSystemCreditTxt, "value");
	}

	public String fetchInvoiceDateLbl() {
		return FetchTextBoxValuewithText(billingInvoiceDateLbl);
	}

	public String fetchInvoiceDate() {
		return FetchTextBoxValuewithattribute(billingInvoiceDatedtPicker, "value");
	}

	public String fetchApplicationReceiptDateLbl() {
		return FetchTextBoxValuewithText(billingApplicationReceiptDateLbl);
	}

	public String fetchApplicationReceiptDate() {
		return FetchTextBoxValuewithattribute(billingApplicationReceiptDatedtPicker, "value");
	}

	public String fetchPaymentDateLbl() {
		return FetchTextBoxValuewithText(billingPaymentDateLbl);
	}

	public String fetchPaymentDate() {
		return FetchTextBoxValuewithattribute(billingPaymentDatedtPicker, "value");
	}

	public String fetchExchangeRateLbl() {
		return FetchTextBoxValuewithText(billingExchangeRateLbl);
	}

	public String fetchExchangeRate() {
		return FetchTextBoxValuewithattribute(billingExchangeRateTxt, "value");
	}

	public String fetchManualAdjBaseJurLbl() {
		return FetchTextBoxValuewithText(billingManualAdjBaseJurLbl);
	}

	public String fetchManualAdjBaseJur() {
		return FetchTextBoxValuewithattribute(billingManualAdjBaseJurTxt, "value");
	}

	public String fetchBillingBatchBillingLbl() {
		return FetchTextBoxValuewithText(billingBatchBillingLbl);
	}

	public String fetchBillingBatchBilling() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(billingBatchBillingChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fetchBillingIsUseOneTimeMailingLbl() {
		return FetchTextBoxValuewithText(billingIsUseOneTimeMailingLbl);
	}

	public String fetchBillingIsUseOneTimeMailing() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(billingIsUseOneTimeMailingChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fetchHeaderRowRin() {
		return FetchTextBoxValuewithText(pageHeaderRow);
	}
}

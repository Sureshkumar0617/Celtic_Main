package com.celtic.automation.cmcs.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class BillingTab extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public BillingTab(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#contentMsg > div > ul > li > span")
	WebElement billingValidationMessage;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(1) > span")
	WebElement billingValidationMessage2;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(2) > span")
	WebElement billingValidationMessage3;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement billingSupplementDetailsSubhdr;

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

	@FindBy(xpath = "//label[@for='RenewalFeeEffectiveDate']")
	WebElement billingRenewalFeeEffectiveDateLbl;

	@FindBy(xpath = "//input[@id='RenewalFeeEffectiveDate']")
	WebElement billingRenewalFeeEffectiveDatedtPicker;

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

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[2]")
	WebElement billingFeeSubhdr;

	@FindBy(xpath = "//label[@for='ManualAdjBaseJur']")
	WebElement billingManualAdjBaseJurLbl;

	@FindBy(xpath = "//input[@id='ManualAdjBaseJur']")
	WebElement billingManualAdjBaseJurTxt;

	@FindBy(xpath = "//label[@for='BatchBilling']")
	WebElement billingBatchBillingLbl;

	@FindBy(xpath = "//input[@id='BatchBilling']")
	WebElement billingBatchBillingChk;

	@FindBy(xpath = "//label[@for='Chk30daysTear']")
	WebElement billingTVRLbl;

	@FindBy(xpath = "//input[@id='Chk30daysTear']")
	WebElement billingTVRChk;

	@FindBy(xpath = "//label[@for='QuaterPayment']")
	WebElement billingInstallmentPlanLbl;

	@FindBy(xpath = "//input[@id='QuaterPayment']")
	WebElement billingInstallmentPlanChk;

	@FindBy(xpath = "//label[@for='IsUseOneTimeMailing']")
	WebElement billingIsUseOneTimeMailingLbl;

	@FindBy(xpath = "//input[@id='IsUseOneTimeMailing']")
	WebElement billingIsUseOneTimeMailingChk;

	@FindBy(xpath = "//label[@for='NoOfdaysTear']")
	WebElement billingTVRNoOfDaysLbl;

	@FindBy(xpath = "//input[@id='NoOfdaysTear']")
	WebElement billingTVRNoOfDaysTxt;

	@FindBy(xpath = "//th[contains(@class,'sorting_disabled')and not( contains(@class,'hidden'))]")
	List<WebElement> billingGridHeaders;

	@FindBy(xpath = "//input[contains(@id,'FeeDetailRows_')and contains(@id,'_FeeDescription')]")
	List<WebElement> billingFee;

	@FindBy(xpath = "//input[contains(@id,'FeeDetailRows_')and contains(@id,'_FeeAmount')]")
	List<WebElement> billingFeeAmount;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[3]")
	WebElement billingDeliveryDetailsSubhdr;

	@FindBy(xpath = "//label[@for='selectEDC']")
	WebElement billingElectronicDeliveryTypeLbl;

	@FindBy(xpath = "//select[@id='selectEDC']")
	WebElement billingElectronicDeliveryTypedd;

	@FindBy(xpath = "//label[@for='selectedInvReport']")
	WebElement billingInvoiceReportTypeLbl;

	@FindBy(xpath = "//select[@id='selectedInvReport']")
	WebElement billingInvoiceReportTypedd;

	@FindBy(xpath = "//input[contains(@id,'FeeDetailRows_')and contains(@id,'_FeeDescription')]")
	List<WebElement> billingFeeType;

	@FindBy(xpath = "//input[@id='btnRecalculate']")
	WebElement billingReCalculateBtn;

	@FindBy(xpath = "(//select[contains(@id,'ReasonsSelect')])")
	List<WebElement> WaiveList;

	@FindBy(xpath = "//th[contains(@class,'sorting_disabled')and not( contains(@class,'hidden')) and contains(text(),'Type')]")
	WebElement billingGridFeeType;

	@FindBy(xpath = "//th[contains(@class,'sorting_disabled')and not( contains(@class,'hidden')) and contains(text(),'Amt')]")
	WebElement billingGridFeeAmount;

	@FindBy(xpath = "//th[contains(@class,'sorting_disabled')and not( contains(@class,'hidden')) and contains(text(),'Waive')]")
	WebElement billingGridWaive;

	@FindBy(xpath = "//label[@for='selectEDC30daysTear']")
	WebElement billingTVRElectronicDeliveryTypeLbl;

	@FindBy(xpath = "//select[@id='selectEDC30daysTear']")
	WebElement billingTVRElectronicDeliveryTypedd;

	@FindBy(xpath = "//input[@id='btnCancelBill']")
	WebElement billingCancelBillBtn;

	@FindBy(xpath = "//input[@id='Email']")
	WebElement billingEmailIDTxt;

	@FindBy(xpath = "//a[@role='button' and contains(text(),'Manual')]")
	WebElement billingManualAdjReasonCollapse;

	@FindBy(xpath = "//textarea[@id='editComment_CommentTxtcommentsVM2']")
	WebElement billingManualAdjReasonCommentsTxt;

	@FindBy(xpath = "//input[@id='editComment_DelAllowedcommentsVM2']")
	WebElement billingManualAdjReasonDeleteAllowedChk;

	@FindBy(xpath = "//input[@id='addUpdateCommentBtncommentsVM2']")
	WebElement billingManualAdjReasonAddorUpdateCommentBtn;

	@FindBy(xpath = "//input[@id='refreshCommentBtncommentsVM2']")
	WebElement billingManualAdjReasonClearCommentBtn;

	@FindBy(xpath = "//select[@id='FeeDetailRows_8__WaiverReasonsSelect']")
	WebElement billingBicentennialFeedd;

	@FindBy(xpath = "//select[@id='FeeDetailRows_9__WaiverReasonsSelect']")
	WebElement billingGradeCrossingFeedd;

	@FindBy(xpath = "//select[@id='FeeDetailRows_10__WaiverReasonsSelect']")
	WebElement billingReplacementPlateFeedd;

	@FindBy(xpath = "//select[@id='FeeDetailRows_11__WaiverReasonsSelect']")
	WebElement billingSecondPlateFeedd;

	@FindBy(xpath = "//select[@id='FeeDetailRows_12__WaiverReasonsSelect']")
	WebElement billingLateFilingPenaltydd;

	@FindBy(xpath = "//select[@id='FeeDetailRows_13__WaiverReasonsSelect']")
	WebElement billingLatePayPenaltydd;

	@FindBy(xpath = "//select[@id='FeeDetailRows_14__WaiverReasonsSelect']")
	WebElement billingTransferFeedd;

	@FindBy(xpath = "//select[@id='FeeDetailRows_15__WaiverReasonsSelect']")
	WebElement billingTransferRevenueFeedd;

	@FindBy(xpath = "//a[@role='button' and contains(text(),'Fee')]")
	WebElement billingFeeOverrideReasonCollapse;

	@FindBy(xpath = "//textarea[@id='editComment_CommentTxtcommentsVM3']")
	WebElement billingFeeOverrideReasonCommentsTxt;

	@FindBy(xpath = "//input[@id='editComment_DelAllowedcommentsVM3']")
	WebElement billingFeeOverrideReasonDeleteAllowedChk;

	@FindBy(xpath = "//input[@id='addUpdateCommentBtncommentsVM3']")
	WebElement billingFeeOverrideReasonAddorUpdateCommentBtn;

	@FindBy(xpath = "//input[@id='refreshCommentBtncommentsVM3']")
	WebElement billingFeeOverrideReasonClearCommentBtn;

	@FindBy(xpath = "//a[@id='BillingBtn']")
	WebElement billingBillingTab;

	@FindBy(xpath = "//input[@id='ReceiptDate']")
	WebElement billingAppReceiptDatedPicker;

	@FindBy(xpath = "//h3[contains(@class,'pull-left')]")
	WebElement billingSubhdr;

	public void renewalFeeEffectiveDatedtPicker(String effDate) {
		clearElement(billingRenewalFeeEffectiveDatedtPicker);
		clickElement(billingRenewalFeeEffectiveDatedtPicker);
		webEditTxt(billingRenewalFeeEffectiveDatedtPicker, effDate);
	}

	public void enterManualAdjBaseJur(String manualAdjBaseJurValue) {
		webEditTxtChange(billingManualAdjBaseJurTxt, manualAdjBaseJurValue);
	}

	public void clickBatchBilling() {
		webCheckON(billingBatchBillingChk);
	}

	public void clickTVR() {
		if (isPresentAndDisplayed(billingTVRChk)) {
			webCheckON(billingTVRChk);
		}
	}

	public void clickInstallmentPlan() {
		webCheckON(billingInstallmentPlanChk);
	}

	public void clickIsUseOneTimeMailingChk() {
		webCheckON(billingIsUseOneTimeMailingChk);
	}

	public void enterTVRNofDays(String tVRNoOfDaysValue) {
		webEditTxtChange(billingTVRNoOfDaysTxt, tVRNoOfDaysValue);
	}

	public void selectElectronicDeliveryType(String electronicDeliveryTypeValue) {
		selectFromDropdownByVisibleText(billingElectronicDeliveryTypedd, electronicDeliveryTypeValue);
	}

	public void selectInvoiceReportType(String invoiceReportTypeValue) {
		selectFromDropdownByVisibleText(billingInvoiceReportTypedd, invoiceReportTypeValue);
	}

	public void clickReCalculate() throws InterruptedException {
		clickElement(billingReCalculateBtn);
	}

	public void selectTVRElectronicDeliveryType(String tVRElectronicDeliveryTypeValue) {
		selectFromDropdownByVisibleText(billingTVRElectronicDeliveryTypedd, tVRElectronicDeliveryTypeValue);
	}

	public void clickCancelBill() {
		clickElement(billingCancelBillBtn);
	}

	public void enterEmailIDTxt(String emailIDTxtValue) {
		webEditTxtChange(billingEmailIDTxt, emailIDTxtValue);
	}

	public void expandManualAdjReason() throws Exception {
		if (FetchTextBoxValuewithattribute(billingManualAdjReasonCollapse, "aria-expanded").equalsIgnoreCase("false")) {
			clickElement(billingManualAdjReasonCollapse);
		}
	}

	public void enterManualAdjReasonComments(String manualAdjReasonCommentsValue) {
		webEditTxtChange(billingManualAdjReasonCommentsTxt, manualAdjReasonCommentsValue);
	}

	public void clickManualAdjReasonDeleteAllowed() {
		webCheckON(billingManualAdjReasonDeleteAllowedChk);
	}

	public void clickManualAdjReasonAddorUpdateComments() {
		clickElement(billingManualAdjReasonAddorUpdateCommentBtn);
	}

	public void clickManualAdjReasonclearComments() {
		clickElement(billingManualAdjReasonClearCommentBtn);
	}

	public void selectBicentennialFee(String bicentennialFeeValue) {
		selectFromDropdownByVisibleText(billingBicentennialFeedd, bicentennialFeeValue);
	}

	public void selectGradeCrossingFee(String gradeCrossingFeeValue) {
		selectFromDropdownByVisibleText(billingGradeCrossingFeedd, gradeCrossingFeeValue);
	}

	public void selectReplacementPlateFee(String replacementPlateFeeValue) {
		selectFromDropdownByVisibleText(billingReplacementPlateFeedd, replacementPlateFeeValue);
	}

	public void selectSecondPlateFee(String secondPlateFeeValue) {
		selectFromDropdownByVisibleText(billingSecondPlateFeedd, secondPlateFeeValue);
	}

	public void selectLateFilingPenalty(String lateFilingPenaltyValue) {
		selectFromDropdownByVisibleText(billingLateFilingPenaltydd, lateFilingPenaltyValue);
	}

	public void selectLatePayPenalty(String latePayPenaltyValue) {
		selectFromDropdownByVisibleText(billingLatePayPenaltydd, latePayPenaltyValue);
	}

	public void selectTransferFee(String transferFeeValue) {
		selectFromDropdownByVisibleText(billingTransferFeedd, transferFeeValue);
	}

	public void selectTransferRevenueFee(String transferRevenueFeeValue) {
		selectFromDropdownByVisibleText(billingTransferRevenueFeedd, transferRevenueFeeValue);
	}

	public void expandFeeOverrideReason() {
		clickElement(billingFeeOverrideReasonCollapse);
	}

	public void enterFeeOverrideReasonComments(String manualAdjReasonCommentsValue) {
		webEditTxtChange(billingFeeOverrideReasonCommentsTxt, manualAdjReasonCommentsValue);
	}

	public void clickFeeOverrideReasonDeleteAllowed() {
		webCheckON(billingFeeOverrideReasonDeleteAllowedChk);
	}

	public void clickFeeOverrideReasonAddorUpdateComments() {
		clickElement(billingFeeOverrideReasonAddorUpdateCommentBtn);
	}

	public void clickFeeOverrideReasonclearComments() {
		clickElement(billingFeeOverrideReasonClearCommentBtn);
	}

	public void validateBillingtab(String textmsg) {
		Boolean a = FetchTextBoxValuewithText(billingBillingTab).contains(textmsg);
		if (a == true) {
			assert true;
		} else {
			assert false;
		}
	}

	public String validateMessage() {
		clickElement(billingValidationMessage);
		highlightElement(driver, billingValidationMessage);
		return FetchTextBoxValuewithText(billingValidationMessage);
	}

	public String validateMessage2() {
		highlightElement(driver, billingValidationMessage2);
		return FetchTextBoxValuewithText(billingValidationMessage2);
	}

	public String validateMessage3() {
		highlightElement(driver, billingValidationMessage3);
		return FetchTextBoxValuewithText(billingValidationMessage3);
	}

	public void enterReceiptDate(String previousDate) {
		if (billingAppReceiptDatedPicker.getAttribute("value").isEmpty()) {
			clickElement(billingAppReceiptDatedPicker);
			webEditTxt(billingAppReceiptDatedPicker, previousDate);
		}
	}

	public void validateSubHdr(String hdrValue) {
		if (billingSubhdr.getText().contains(hdrValue))
			assert true;
	}

	public String fetchRegisterMonthLbl() {
		return FetchTextBoxValuewithText(billingRegisterMonthLbl);
	}

	public String fetchRegisterMonth() {
		return FetchTextBoxValuewithattribute(billingRegisterMonthTxt, "value");
	}

	public String fetchNoOfVehiclesInSuppLbl() {
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

	public String fetchRenewalFeeEffectiveDatelbl() {
		return FetchTextBoxValuewithText(billingRenewalFeeEffectiveDateLbl);
	}

	public String fetchRenewalFeeEffectiveDate() {
		return FetchTextBoxValuewithattribute(billingRenewalFeeEffectiveDatedtPicker, "value");
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

	public String fetchBillingTVRLbl() {
		return FetchTextBoxValuewithText(billingTVRLbl);
	}

	public String fetchBillingTVR() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattributedisabled(billingTVRChk, "checked") == null) {
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
		if (FetchTextBoxValuewithattributedisabled(billingIsUseOneTimeMailingChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fetchBillingInstallmentPlanLbl() {
		return FetchTextBoxValuewithText(billingInstallmentPlanLbl);
	}

	public String fetchBillingInstallmentPlan() {
		boolean boolstatus = false;
		boolstatus = ((isElementSelected(billingInstallmentPlanChk)));
		return Boolean.toString(boolstatus);
	}

	public String fetchBillingTVRNoOfDaysLbl() {
		return FetchTextBoxValuewithText(billingTVRNoOfDaysLbl);
	}

	public String fetchBillingTVRNoOfDays() {
		return FetchTextBoxValuewithattribute(billingTVRNoOfDaysTxt, "value");
	}

	public ArrayList<String> fetchTableHeaders() {
		ArrayList<String> headersArray = new ArrayList<String>();
		for (int i = 0; i < billingGridHeaders.size(); i++) {
			headersArray.add(FetchTextBoxValuewithText(billingGridHeaders.get(i)));
		}
		return headersArray;
	}

	public ArrayList<String> fetchTableFeeType() {
		ArrayList<String> feeTypeArray = new ArrayList<String>();
		for (int i = 0; i < billingFeeType.size(); i++) {
			feeTypeArray.add(FetchTextBoxValuewithattributedisabled(billingFeeType.get(i), "value"));
		}
		return feeTypeArray;
	}

	public ArrayList<String> fetchTableFeeAmount() {
		ArrayList<String> feeAmountArray = new ArrayList<String>();
		for (int i = 0; i < billingFeeAmount.size(); i++) {
			feeAmountArray.add(FetchTextBoxValuewithattributedisabled(billingFeeAmount.get(i), "value"));
		}
		return feeAmountArray;
	}

	public String fetchElectronicDeliveryTypelbl() {
		return FetchTextBoxValuewithText(billingElectronicDeliveryTypeLbl);
	}

	public String fetchElectronicDeliveryType() {
		return FetchDropdownSelectedValue(billingElectronicDeliveryTypedd);
	}

	public String fetchBillingEmail() {
		return FetchTextBoxValuewithattribute(billingEmailIDTxt, "value");
	}

	public String fetchInvoiceReportTypelbl() {
		return FetchTextBoxValuewithText(billingInvoiceReportTypeLbl);
	}

	public String fetchBillingInvoiceReportType() {
		return FetchDropdownSelectedValue(billingInvoiceReportTypedd);
	}

	@SuppressWarnings("unused")
	public void selectWaiveOff(String feeTypeExcel, String feeWaiveExcel) {
		for (int i = 0; i < billingFeeType.size(); i++) {
			if (FetchTextBoxValuewithattributedisabled(billingFeeType.get(i), "value").trim()
					.equalsIgnoreCase(feeTypeExcel))
				;
			{
				selectFromDropdownByVisibleText(WaiveList.get(i), feeWaiveExcel);
				break;
			}
		}
	}

	/*
	 * Returns True only is the Specified Fee Type contains amount more than zero
	 * Bicentennial Fee,Grade Crossing Fee,Replacement Plate Fee,Second Plate
	 * Fee,Late Filing Penalty,Late Pay Penalty,Transfer Fee,Transfer Revenue
	 * Fee,Wire Transfer Fee
	 */
	public boolean getAmount(String FeeTypeExcel) {
		boolean Amountbool = false;
		for (int i = 0; i < billingFeeType.size(); i++) {
			if (FetchTextBoxValuewithattributedisabled(billingFeeType.get(i), "value").trim()
					.equalsIgnoreCase(FeeTypeExcel))
				;
			{
				String FeeAmountValue = FetchTextBoxValuewithattributedisabled(billingFeeAmount.get(i), "value").trim();
				if (Double.valueOf(String.format("%.2f", (Double.valueOf(FeeAmountValue)))) > 0) {
					Amountbool = true;
					break;
				}
			}
		}
		return Amountbool;
	}

	public String fetchbillingTVRNoOfDaysLbl() {
		return FetchTextBoxValuewithText(billingTVRNoOfDaysLbl);
	}

	public String fetchBillingTVRNoOfDay() {
		return FetchTextBoxValuewithattribute(billingTVRNoOfDaysTxt, "value");
	}

	public ArrayList<String> fetchTableHeader() {
		ArrayList<String> headersArray = new ArrayList<String>();
		for (int i = 0; i < billingGridHeaders.size(); i++) {
			headersArray.add(FetchTextBoxValuewithText(billingGridHeaders.get(i)));
		}
		return headersArray;
	}

	public String fetchBillingGridFeeType() {
		return FetchTextBoxValuewithText(billingGridFeeType);
	}

	public String fetchBillingGridFeeAmount() {
		return FetchTextBoxValuewithText(billingGridFeeAmount);
	}

	public String fetchbillingGridWaive() {
		return FetchTextBoxValuewithText(billingGridWaive);
	}
}
package com.celtic.automation.cmcs.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.celtic.automation.cmcs.util.ElementUtil;

public class PaymentTab extends ElementUtil {
	private WebDriver driver;
	private Logger logg;

	public PaymentTab(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(1) > span")
	WebElement paymentValidationMessage1;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(2) > span")
	WebElement paymentValidationMessage2;

	@FindBy(css = "#contentMsg > div > ul > li:nth-child(3) > span")
	WebElement paymentValidationMessage3;

	@FindBy(css = "#contentMsg > div > ul > li > span")
	WebElement paymentValidationMessage4;

	@FindBy(css = "#contentMsg > div > ul > li > span")
	WebElement supplementValidationMessage5;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[1]")
	WebElement paymentSupplementDetailsSubHdr;

	@FindBy(xpath = "//label[@for='IRPEscrow']")
	WebElement paymentIRPSystemCreditLbl;;

	@FindBy(xpath = "//input[@id='IRPEscrow']")
	WebElement paymentIRPSystemCredittxt;

	@FindBy(xpath = "//label[@for='EnterpriseEscrow']")
	WebElement paymentEnterpriseSystemCreditLbl;;

	@FindBy(xpath = "//input[@id='EnterpriseEscrow']")
	WebElement paymentEnterpriseSystemCreditTxt;

	@FindBy(xpath = "//label[@for='InvoiceDate']")
	WebElement paymentInvoiceDateLbl;;

	@FindBy(xpath = "//input[@id='InvoiceDate']")
	WebElement paymentInvoiceDatedtPicker;

	@FindBy(xpath = "//label[@for='invoiceNbr']")
	WebElement paymentInvoiceNumberLbl;;

	@FindBy(xpath = "//input[@id='invoiceNbr']")
	WebElement paymentInvoiceNumberTxt;

	@FindBy(xpath = "//label[@for='ReceiptDate']")
	WebElement paymentPaymentReciptDateLbl;;

	@FindBy(xpath = "//input[@id='ReceiptDate']")
	WebElement paymentPaymentReciptDatedtPicker;

	@FindBy(xpath = "//label[@for='ManualAdjBaseJur']")
	WebElement paymentManualAdjBaseJurLbl;

	@FindBy(xpath = "//input[@id='ManualAdjBaseJur']")
	WebElement paymentManualAdjBaseJurTxt;

	@FindBy(xpath = "//label[@for='BatchCredential']")
	WebElement paymentBatchCredentialLbl;

	@FindBy(xpath = "//input[@id='BatchCredential']")
	WebElement paymentBatchCredentialChk;;

	@FindBy(xpath = "//label[@for='WireTransferFee']")
	WebElement paymentWireTransferFeeLbl;

	@FindBy(xpath = "//input[@id='WireTransferFee']")
	WebElement paymentWireTransferFeeChk;

	@FindBy(xpath = "(//div[contains(@class,'box-title')]/h4)[3]")
	WebElement paymentCabCardDeliveryTypeSubHdr;

	@FindBy(xpath = "//label[@for='ElectronicDeliveryType']")
	WebElement paymentElectronicDeliveryTypeLbl;

	@FindBy(xpath = "//select[@id='ElectronicDeliveryType']")
	WebElement paymentElectronicDeliveryTypedd;

	@FindBy(xpath = "//a[contains(text(),'Show')]")
	WebElement paymentShowHandLoglnk;

	@FindBy(xpath = "//a[contains(text(),'Invoice')]")
	WebElement paymentViewInvoiceReportlnk;

	@FindBy(xpath = "//a[contains(text(),'TVR')]")
	WebElement paymentViewTVRLnk;

	@FindBy(xpath = "//input[@id='btnPayNow']")
	WebElement paymentPayNowBtn;

	@FindBy(xpath = "//input[@id='btnAddToCart']")
	WebElement paymentAddToCartBtn;

	@FindBy(xpath = "//select[@id='paymentVos_0__PayType']")
	WebElement paymentMethod;

	@FindBy(xpath = "//a[@title='Supplement Continuance']")
	WebElement supplementCont;

	@FindBy(xpath = "//div[@id='contentMsg']//span")
	WebElement addToCartMsg;

	@FindBy(xpath = "//a[@id='cartRef']")
	WebElement verifyAddToCart;

	@FindBy(xpath = "//select[contains(@id,'PayType')]")
	WebElement paymentType;

	@FindBy(xpath = "//input[contains(@id,'paymentAmount')and contains(@id,'paymentVos')]")
	WebElement paymentAmount;

	@FindBy(xpath = "//input[@id='Add']")
	WebElement paymentAdd;

	@FindBy(xpath = "//select[@id='paymentVos_1__PayType']")
	WebElement paymentAmountchecktype;

	@FindBy(xpath = "//input[@id='paymentVos_1__paymentAmount']")
	WebElement paymentAmountCheque;

	@FindBy(xpath = "//input[@id='paymentVos_2__DeleteChk']")
	WebElement checkboxDelete;

	@FindBy(xpath = "//input[@id='Delete']")
	WebElement deleteCheckBox;

	@FindBy(xpath = "//select[@id='outPutModelSelect']")
	WebElement paymentReceipt;

	@FindBy(xpath = "//input[@id='paymentVos_1__PaymentNo']")
	WebElement paymentCheckNo;

	@FindBy(xpath = "//input[@id='paymentVos_0__paymentAmount']")
	WebElement paymentAmount1;

	@FindBy(xpath = "//th[contains(@class,'sorting_disabled')and not( contains(@class,'hidden'))]")
	List<WebElement> billingGridHeaders;

	@FindBy(xpath = "//th[contains(@class,'sorting_disabled')and contains(text(),'Type')]")
	WebElement billingGridFeeType;

	@FindBy(xpath = "//input[contains(@id,'FeeDetailRows_')and contains(@id,'_FeeDescription')]")
	List<WebElement> billingFeeType;

	@FindBy(xpath = "//th[contains(@class,'sorting_disabled')and contains(text(),'Amt')]")
	WebElement billingGridFeeAmount;

	@FindBy(xpath = "//input[contains(@id,'FeeDetailRows_')and contains(@id,'_FeeAmount')]")
	List<WebElement> billingFeeAmount;

	public void clickPayNow() throws InterruptedException {
		scrollIntoView(paymentPayNowBtn);
		clickElement(paymentPayNowBtn);
	}

	public void clickAddtoCart() {
		clickElement(paymentAddToCartBtn);
	}

	public void clickPaymenToAdd() {
		clickElement(paymentAdd);
	}

	public void clicksupplcont() {
		clickElement(supplementCont);
	}

	public void validatemessage(String TransactionexistMeesage) {
		highlightElement(driver, addToCartMsg);
		Assert.assertEquals(addToCartMsg.getText(), TransactionexistMeesage);
	}

	public void clickVerifyAddToCart() {
		clickElement(verifyAddToCart);
	}

	public void selectPaymentMethod(String Methodtype) {
		webEditTxtChange(paymentMethod, Methodtype);
	}

	public void enterPaymentAmount(String paymentAmount1) {
		webEditTxtChange(paymentAmount, paymentAmount1);
	}

	public String fetchEnterpriseSystemCreditLbl() {
		return FetchTextBoxValuewithText(paymentEnterpriseSystemCreditLbl);
	}

	public String fetchEnterpriseSystemCredit() {
		return FetchTextBoxValuewithattribute(paymentEnterpriseSystemCreditTxt, "value");
	}

	public String fetchIRPSystemCreditLbl() {
		return FetchTextBoxValuewithText(paymentIRPSystemCreditLbl);
	}

	public String fetchIRPSystemCredit() {
		return FetchTextBoxValuewithattribute(paymentIRPSystemCredittxt, "value");
	}

	public String fetchInvoiceDateLbl() {
		return FetchTextBoxValuewithText(paymentInvoiceDateLbl);
	}

	public String fetchInvoiceDate() {
		return FetchTextBoxValuewithattribute(paymentInvoiceDatedtPicker, "value");
	}

	public String fetchInvoiceNumberLbl() {
		return FetchTextBoxValuewithText(paymentInvoiceNumberLbl);
	}

	public String fetchInvoiceNumber() {
		return FetchTextBoxValuewithattribute(paymentInvoiceNumberTxt, "value");
	}

	public String fetchPaymentReciptDateLbl() {
		return FetchTextBoxValuewithText(paymentPaymentReciptDateLbl);
	}

	public String fetchPaymentReciptDate() {
		return FetchTextBoxValuewithattribute(paymentPaymentReciptDatedtPicker, "value");
	}

	public String fetchManualAdjBaseJurLbl() {
		return FetchTextBoxValuewithText(paymentManualAdjBaseJurLbl);
	}

	public String fetchManualAdjBaseJur() {
		return FetchTextBoxValuewithattribute(paymentManualAdjBaseJurTxt, "value");
	}

	public String fetchBatchCredentialLbl() {
		return FetchTextBoxValuewithText(paymentBatchCredentialLbl);
	}

	public String fetchBatchCredential() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(paymentBatchCredentialChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public String fetchWireTransferFeeLbl() {
		return FetchTextBoxValuewithText(paymentWireTransferFeeLbl);
	}

	public String fetchWireTransferFee() {
		boolean boolstatus = false;
		if (FetchTextBoxValuewithattribute(paymentWireTransferFeeChk, "checked") == null) {
			boolstatus = false;
		} else {
			boolstatus = true;
		}
		return Boolean.toString(boolstatus);
	}

	public ArrayList<String> fetchTableHeaders() {
		ArrayList<String> headersArray = new ArrayList<String>();
		for (int i = 0; i < billingGridHeaders.size(); i++) {
			headersArray.add(FetchTextBoxValuewithText(billingGridHeaders.get(i)));
		}
		return headersArray;
	}

	public String fetchHeaderFeeType() {
		return FetchTextBoxValuewithText(billingGridFeeType);
	}

	public ArrayList<String> fetchTableFeeType() {
		ArrayList<String> feeTypeArray = new ArrayList<String>();
		for (int i = 0; i < billingFeeType.size(); i++) {
			feeTypeArray.add(FetchTextBoxValuewithattributedisabled(billingFeeType.get(i), "value"));
		}
		return feeTypeArray;
	}

	public String fetchHeaderFeeAmount() {
		return FetchTextBoxValuewithText(billingGridFeeAmount);
	}

	public ArrayList<String> fetchTableFeeAmount() {
		ArrayList<String> feeAmountArray = new ArrayList<String>();
		for (int i = 0; i < billingFeeAmount.size(); i++) {
			feeAmountArray.add(FetchTextBoxValuewithattributedisabled(billingFeeAmount.get(i), "value"));
		}
		return feeAmountArray;
	}

	public String fetchElectronicDeliveryTypeLbl() {
		return FetchTextBoxValuewithText(paymentElectronicDeliveryTypeLbl);
	}

	public String fetchElectronicDeliveryType() {
		return FetchDropdownSelectedValue(paymentElectronicDeliveryTypedd);
	}

}

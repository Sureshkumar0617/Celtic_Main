package com.celtic.automation.cmcs.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class Payment extends ElementUtil{
	private WebDriver driver;
	private Logger logg;
	
	public Payment(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#contentMsg > div.alert.alert-danger.notification > ul > li > span") WebElement paymentValidationMessage1;
	
	@FindBy(css="#contentMsg > div.alert.alert-info.notification > ul > li:nth-child(1) > span") WebElement paymentValidationMessage2;
	
	@FindBy(css="#contentMsg > div.alert.alert-info.notification > ul > li:nth-child(2) > span") WebElement paymentValidationMessage3;
	
	@FindBy(css="#contentMsg > div.alert.alert-info.notification > ul > li:nth-child(3) > span") WebElement paymentValidationMessage4;
	
	@FindBy(xpath="//div[@class='alert alert-danger notification']") List<WebElement> validationMessage;
	
	@FindBy(xpath="//input[@id='AccountNo']") WebElement paymentMCECustomerIdTxt;
	
	@FindBy(xpath="//label[@for='AccountNo']") WebElement paymentMCECustomerIdLbl;
	
	@FindBy(xpath="//input[@id='legalName']") WebElement paymentLegalNameTxt;
	
	@FindBy(xpath="//label[@for='legalName']") WebElement paymentLegalNameLbl;
	
	@FindBy(xpath="//input[@id='DBAName']") WebElement paymentDBANameTxt;
	
	@FindBy(xpath="//label[@for='DBAName']") WebElement paymentDBANameLbl;
	
	@FindBy(xpath="//input[@id='EntEsc']") WebElement paymentEnterpriseSystemCreditTxt;
	
	@FindBy(xpath="//label[@for='EntEsc']") WebElement paymentEnterpriseSystemCreditLbl;
	
	@FindBy(xpath="//input[@id='IrpEsc']") WebElement paymentIRPSystemCreditTxt;
	
	@FindBy(xpath="//label[@for='IrpEsc']") WebElement paymentIRPSystemCreditLbl;
	
	@FindBy(xpath="//input[@id='IftaEsc']") WebElement paymentIftaSystemCreditTxt;
	
	@FindBy(xpath="//label[@for='IftaEsc']") WebElement paymentIftaSystemCreditLbl;
	
	@FindBy(xpath="//input[@id='OPAEsc']") WebElement paymentOpaSystemCreditTxt;
	
	@FindBy(xpath="//label[@for='OPAEsc']") WebElement paymentOpaSystemCreditLbl;
	
	@FindBy(xpath="//table[@id='gvTransactionDetail']//tr[1]/th") List<WebElement> paymentTableHeaderRow;
    
	@FindBy(xpath="//table[@id='gvTransactionDetail']/tbody/tr[1]/td/span") List<WebElement> paymentInvoiceRow;
    
	@FindBy(xpath="//table[@id='gvPaymentTypeUS']/tbody/tr") List<WebElement> paymentTableBodyRows;
	
	@FindBy(xpath="//strong[contains(text(),'Total')]") WebElement paymentTotalAmountDueLbl;
    
	@FindBy(xpath="//input[@name='totalInvoiceAmountUSD']") WebElement paymentTotalInvoiceAmount;
    
    @FindBy(xpath="//input[@id='invoiceNbr']") WebElement paymentInvoiceNbrTxt;
	
    @FindBy(xpath="//label[@for='invoiceNbr']") WebElement paymentInvoiceNbrLbl;
	
    @FindBy(xpath="//input[@id='invDateFrom']") WebElement paymentInvoiceDateFromIdTxt;
	
    @FindBy(xpath="//label[@for='invDateFrom']") WebElement paymentInvoiceDateFromIdLbl;
	
    @FindBy(xpath="//input[@id='invDateTo']") WebElement paymentInvoiceDateToTxt;
	
    @FindBy(xpath="//label[@for='invDateTo']") WebElement paymentInvoiceDateToLbl;

	@FindBy(xpath="//input[@id='cartId']") WebElement paymentCartIdTxt;
	
	@FindBy(xpath="//label[@for='cartId']") WebElement paymentCartIdLbl;
	
	@FindBy(xpath="//input[@id='allTransaction']") WebElement paymentAllTransactionTxt;
	
	@FindBy(xpath="//label[@for='allTransaction']") WebElement paymentAllTransactionLbl;
	
	@FindBy(xpath="//input[@id='btnbtnSrch']") WebElement paymentbtnSearch;
	
	@FindBy(xpath="(//div[contains(@class,'box-title')]/h4)[1]") WebElement paymentSearchForTransactionSubHdr;  
	
	@FindBy(xpath="(//div[contains(@class,'box-title')]/h4)[2]") WebElement paymentSelectedTransactionSubHdr;
	
	@FindBy(xpath="(//div[contains(@class,'box-title')]/h4)[3]") WebElement paymentElectronicDeliveryTypeSubHdr;
	
	@FindBy(xpath="//select[@id='ElectronicDeliveryType']") WebElement paymentElectronicDeliveryTypedd;
	
	@FindBy(xpath="//input[@id='payerName']") WebElement paymentPayerNameTxt;
	
	@FindBy(xpath="//label[@for='payerName']") WebElement paymentPayerNameLbl;
	
	@FindBy(xpath="//input[@id='payerAccountNo']") WebElement paymentPayerAccountNoTxt;
	
	@FindBy(xpath="//label[@for='payerAccountNo']") WebElement paymentPayerAccountNoLbl;
	
	@FindBy(xpath="//input[@id='recieptDate']") WebElement paymentRecieptDateTxt;
	
	@FindBy(xpath="//label[@for='recieptDate']") WebElement paymentRecieptDateLbl;
	
	@FindBy(xpath="//input[@id='btnRemove']") WebElement paymentRemoveBtn;
	
	@FindBy(xpath="//input[@id='btnPay']") WebElement paymentPayBtn;
	
	@FindBy(xpath="//input[@id='btnSaveQuit']") WebElement paymentSaveQuitBtn;

	@FindBy(xpath="//th[contains(@aria-label,'Remove')]") WebElement paymentRemoveTbl;
	
	@FindBy(xpath="//th[contains(@aria-label,'MCE')]") WebElement paymentMCECustomerIdTbl;
	
	@FindBy(xpath="//th[contains(@aria-label,'Legal')]") WebElement paymentLegalNameTbl;
	
	@FindBy(xpath="//th[contains(@aria-label,'Invoice No')]") WebElement paymentInvoiceNoTbl;
	
	@FindBy(xpath="//th[contains(@aria-label,'Date')]") WebElement paymentInvoiceDateTbl;
	
	@FindBy(xpath="//th[contains(@aria-label,'Transaction')]") WebElement paymentTransactionTypeTbl;
	
	@FindBy(xpath="//th[contains(@aria-label,'Priority')]") WebElement paymentPriorityTbl;
	
	@FindBy(xpath="//th[contains(@aria-label,'Amount')]") WebElement paymentAmountDuesTbl;
	
	@FindBy(xpath="//input[contains(@id,'totalAmountUSD')]") WebElement paymentTotalAmountUSDTbl;

	@FindBy(xpath="//input[@id='payerCustAccountNo']") WebElement paymentPayerMCECustomerIdTxt;
	
	@FindBy(xpath="//label[@for='payerCustAccountNo']") WebElement paymentPayerMCECustomerIdLbl;
	
	@FindBy(xpath="//input[@name='totalInvoiceAmountUSD']") WebElement paymentTotalInvoiceAmountUSDValue;	
	
	@FindBy(xpath="//input[@id='Delete']") WebElement deleteBtn;
	
	@FindBy(xpath="//input[@id='Add']") WebElement addBtn;
	
	@FindBy(xpath="//input[@id='btnCashDrawerCCUSD']") WebElement creditCardBtn;
	
	@FindBy(xpath="//input[@id='btnCashDrawerECheckUSD']") WebElement eCheckBtn;
	
	@FindBy(xpath="//h3[@class='red']") WebElement RedNote;
	
	@FindBy(xpath="//input[@id='forOverPayment']") List<WebElement> forOverPaymentrdList;
	
	@FindBy(xpath="//input[@id='totalAmount']") WebElement paymentTotalAmountTxt;
	
	@FindBy(xpath="//label[@for='totalAmount']") WebElement paymentTotalAmountLbl;
	
	@FindBy(xpath="//input[@id='remainingBalance']") WebElement paymentRemainingBalanceTxt;
	
	@FindBy(xpath="//label[@for='remainingBalance']") WebElement paymentRemainingBalanceLbl;
	
	@FindBy(xpath="//input[@id='changeAmount']") WebElement paymentChangeAmountTxt;
	
	@FindBy(xpath="//label[@for='changeAmount']") WebElement paymentChangeAmountLbl;
	
	@FindBy(xpath="//input[@id='overPaymentAmount']") WebElement paymentOverPaymentAmountTxt;
	
	@FindBy(xpath="//label[@for='overPaymentAmount']") WebElement paymentOverPaymentAmountLbl;
	
	@FindBy(xpath="//input[@id='netAmountPaid']") WebElement paymentNetAmountPaidTxt;
	
	@FindBy(xpath="//label[@for='netAmountPaid']") WebElement paymentNetAmountPaidLbl;
	
	@FindBy(xpath="//label[@for='outPutModelSelect']") WebElement paymentPaymentReceiptLbl;
	
	@FindBy(xpath="//select[@id='outPutModelSelect']") WebElement paymentPaymentReceiptdd;
	
	@FindBy(xpath="//input[@id='remainingBalance']") WebElement paymentRemainingBalancetxt;
	
	@FindBy(xpath="//input[@id='btnbtnTransactionSection']")WebElement transactionSelectionBtn;
	
	public void selectElectronicDeliverytype(String electronicdeliverytypevalue) {
		selectFromDropdownByVisibleText(paymentElectronicDeliveryTypedd, electronicdeliverytypevalue);
	}
	
	public String fetchTotalAmount() {
		return FetchTextBoxValuewithattributedisabled(paymentTotalInvoiceAmountUSDValue,"value");
	}
	
	public String fetchRemainingBalance() {
		waitUntilElementVisible(paymentRemainingBalanceTxt);
		return FetchTextBoxValuewithattribute(paymentRemainingBalanceTxt,"value");
	}
	
	public void clickPayNow() {
	clickElement(paymentPayBtn);
}

	public void selectPaymentType(int i,String paymentTypeValue) {
	WebElement paymentPaymentType=driver.findElement(By.xpath("//select[contains(@id,'PayType') and contains(@id,'"+i+"')]"));
	selectFromDropdownByVisibleText(paymentPaymentType, paymentTypeValue); 
}

public void enterpaymentNoBasedonType(int i, String paymentNumberValue) {
	WebElement paymentPaymentNumbertxt = driver.findElement(
			By.xpath("//input[contains(@id,'paymentVos') and contains(@id,'PaymentNo')and contains(@id,'" + i + "')]"));
	WebElement paymentPaymentType = driver
			.findElement(By.xpath("//select[contains(@id,'PayType') and contains(@id,'" + i + "')]"));
	if (FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Check")) {
		webEditTxt(paymentPaymentNumbertxt, paymentNumberValue);
	} else if (FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Certified Check")) {
		webEditTxt(paymentPaymentNumbertxt, paymentNumberValue);
	} else if (FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Wire Transfer")) {
		webEditTxt(paymentPaymentNumbertxt, paymentNumberValue);
	} else if (FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("EFT")) {
		webEditTxt(paymentPaymentNumbertxt, paymentNumberValue);
	}
}

public void enterPaymentAmountBasedonType(int i,String paymentAmountValue) {
	WebElement paymentPaymentAmount=driver.findElement(By.xpath("//input[contains(@id,'paymentVos') and contains(@id,'PaymentAmount') and contains(@id,'"+i+"')]"));
	WebElement paymentPaymentType=driver.findElement(By.xpath("//select[contains(@id,'PayType') and contains(@id,'"+i+"')]"));
	if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Cash")) {
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Check")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Certified Check")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Wire Transfer")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("EFT")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("Enterprise System Credit")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("IRP System Credit")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("IFTA System Credit")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("OPA System Credit")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
	else if(FetchDropdownSelectedValue(paymentPaymentType).trim().equalsIgnoreCase("IFTA Bond")){
		webEditTxt(paymentPaymentAmount, paymentAmountValue);
	}
}

public void validatemsg(String msgValue) {
	for(WebElement eachmsg:validationMessage) {
		if(FetchTextBoxValuewithText(eachmsg).contains(msgValue))
			assert true;
	}
}

public void clickDelete() {
	clickElement(deleteBtn);
}

public void clickAdd() {
	clickElement(addBtn);
}

public void clickCreditCard() {
	clickElement(creditCardBtn);
}

public void clickECheck() {
	clickElement(eCheckBtn);
}

public void selectOverPaymentRadioButton(String paymentRdValue) {
	for(WebElement rd:forOverPaymentrdList) {
		if(FetchTextBoxValuewithattribute(rd, "value").equalsIgnoreCase(paymentRdValue)) {
			clickElement(rd);
		}
	}
}

public void selectPaymentReceipt(String paymentReceiptddValue) {
	selectFromDropdownByVisibleText(paymentPaymentReceiptdd, paymentReceiptddValue);
}

public String fetchMCECustomerIdLbl() {
	return FetchTextBoxValuewithText(paymentPayerMCECustomerIdLbl);
}

public String fetchMCECustomerId() {
	return FetchTextBoxValuewithattribute(paymentPayerMCECustomerIdTxt,"value");
}

public String fetchLegalNameLbl() {
	return FetchTextBoxValuewithText(paymentLegalNameLbl);
}

public String fetchLegalName() {
	return FetchTextBoxValuewithattribute(paymentLegalNameTxt,"value");
}

public String fetchInvoiceDateLbl() {
	return FetchTextBoxValuewithText(paymentDBANameLbl);
}

public String fetchInvoiceDate() {
	return FetchTextBoxValuewithattribute(paymentDBANameTxt,"value");
}

public String fetchEnterpriseSystemCreditLbl() {
	return FetchTextBoxValuewithText(paymentEnterpriseSystemCreditLbl);
}

public String fetchEnterpriseSystemCredit() {
	return FetchTextBoxValuewithattribute(paymentEnterpriseSystemCreditTxt,"value");
}

public String fetchIRPSystemCreditLbl() {
	return FetchTextBoxValuewithText(paymentIRPSystemCreditLbl);
}

public String fetchIRPSystemCredit() {
	return FetchTextBoxValuewithattribute(paymentIRPSystemCreditTxt,"value");
}

public String fetchIftaSystemCreditLbl() {
	return FetchTextBoxValuewithText(paymentIftaSystemCreditLbl);
}

public String fetchIftaSystemCredit() {
	return FetchTextBoxValuewithattribute(paymentIftaSystemCreditTxt,"value");
}

public String fetchOpaSystemCreditLbl() {
	return FetchTextBoxValuewithText(paymentOpaSystemCreditLbl);
}

public String fetchOpaSystemCredit() {
	return FetchTextBoxValuewithattribute(paymentOpaSystemCreditTxt,"value");
}

public ArrayList<String> fetchTableHeaders() {
	ArrayList<String> headersArray = new ArrayList<String>();
	for(int i=0;i<paymentTableHeaderRow.size();i++) {
		headersArray.add(FetchTextBoxValuewithText(paymentTableHeaderRow.get(i)));
	}
	return headersArray;
}

public ArrayList<String> fetchTableInvoiceDetails() {
	ArrayList<String> invoiceArray = new ArrayList<String>();
	for(int i=0;i<paymentInvoiceRow.size();i++) {
		invoiceArray.add(FetchTextBoxValuewithText(paymentInvoiceRow.get(i)));
	}
	return invoiceArray;
}
public int fetchTableRowsize() {
	int rowSize=paymentTableBodyRows.size();
	return rowSize;
}

public String fetchTotalAmountDueLbl() {
	return FetchTextBoxValuewithText(paymentTotalAmountDueLbl);
}

public String fetchTotalAmountDue() {
	return FetchTextBoxValuewithattributedisabled(paymentTotalInvoiceAmount,"value");
}






}






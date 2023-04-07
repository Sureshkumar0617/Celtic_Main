package com.celtic.automation.cmcs.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class Financepage extends ElementUtil {

	private WebDriver driver;
	private Logger logg;

	public Financepage(WebDriver driver, Logger logg) {
		super(driver, logg);
		this.logg = logg;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[@for='AccountNo']")
	WebElement postPaymentAccountNoLbl;

	@FindBy(xpath = "//input[@id='AccountNo']")
	WebElement postPaymentAccountNo;

	@FindBy(xpath = "//label[@for='FleetNbr']")
	WebElement installmentFleetNoLbl;

	@FindBy(xpath = "//input[@id='FleetNbr']")
	WebElement installmentFleetNo;

	@FindBy(xpath = "//label[@for='LegalName']")
	WebElement installmentLegalNameLbl;

	@FindBy(xpath = "//input[@id='LegalName']")
	WebElement installmentLegalName;

	@FindBy(xpath = "//label[@for='FltMonthYear']")
	WebElement postPaymentFleerMonthYearLbl;

	@FindBy(xpath = "//input[@id='FltMonthYear']")
	WebElement postPaymentFleerMonthYear;

	@FindBy(xpath = "//label[@for='SupplementNo']")
	WebElement installmentSupplementNoLbl;

	@FindBy(xpath = "//input[@id='SupplementNo']")
	WebElement installmentSupplementNo;

	@FindBy(xpath = "//label[@for='DBAName']")
	WebElement installmentDBANameLbl;

	@FindBy(xpath = "//input[@id='DBAName']")
	WebElement installmentDBAName;

	@FindBy(xpath = "//label[@for='CMVFleetType']")
	WebElement postPaymentFleetTypeLbl;

	@FindBy(xpath = "//input[@id='CMVFleetType']")
	WebElement postPaymentFleetType;

	@FindBy(xpath = "//label[@for='SupplementDesc']")
	WebElement installmentSupplementDescLbl;

	@FindBy(xpath = "//input[@id='SupplementDesc']")
	WebElement installmentSupplementDesc;

	@FindBy(xpath = "//label[@for='UsdotNbr']")
	WebElement installmentUSDOTLbl;

	@FindBy(xpath = "//input[@id='UsdotNbr']")
	WebElement installmentUSDOT;

	@FindBy(xpath = "//label[@for='SupplementEffectiveDate']")
	WebElement postPaymentSupplementEffectiveDateLbl;

	@FindBy(xpath = "//input[@id='SupplementEffectiveDate']")
	WebElement postPaymentSupplementEffectiveDate;

	@FindBy(xpath = "//label[@for='SupplementStatus']")
	WebElement installmentSupplementStatusLbl;

	@FindBy(xpath = "//input[@id='SupplementStatus']")
	WebElement installmentSupplementStatus;

	@FindBy(xpath = "//label[@for='InvoiceDate']")
	WebElement installmentInvoiceDateLbl;

	@FindBy(xpath = "//input[@id='InvoiceDate']")
	WebElement installmentInvoiceDate;

	@FindBy(xpath = "//label[@for='invoiceNbr']")
	WebElement installmentinvoiceNbrLbl;

	@FindBy(xpath = "//input[@id='invoiceNbr']")
	WebElement installmentInvoiceNbr;

	@FindBy(xpath = "//label[@for='EnterpriseEscrow']")
	WebElement postPaymentEnterpriseSystemCreditLbl;

	@FindBy(xpath = "//input[@id='EnterpriseEscrow']")
	WebElement postPaymentEnterpriseSystemCredit;

	@FindBy(xpath = "//label[@for='ReceiptDate']")
	WebElement installmentReceiptDateLbl;

	@FindBy(xpath = "//input[@id='ReceiptDate']")
	WebElement installmentReceiptDate;

	@FindBy(xpath = "//label[@for='InstallmentPlanNumber']")
	WebElement installmentInstallmentPlanNumberLbl;

	@FindBy(xpath = "//input[@id='InstallmentPlanNumber']")
	WebElement installmentInstallmentPlanNumber;

	@FindBy(xpath = "//th[@class='sorting_disabled']")
	List<WebElement> FeeHeaders;

	@FindBy(xpath = "//tr[@role='row']//th[not(contains(@class,'hidden')) and (contains(@class,'Align'))]")
	List<WebElement> feeRow;

	@FindBy(xpath = "//a[@title='Finance']")
	WebElement dashboardFinanceTab;

	@FindBy(xpath = "//a[@title='Post Payment']")
	WebElement financePostPayment;

	@FindBy(xpath = "//input[@id='btnSearch']")
	WebElement postPaymentSearch;

	@FindBy(xpath = "//a[@id='lnkGridSelectgvPostPayment']")
	WebElement postPaymentCartId;

	@FindBy(xpath = "//input[@id='AccountNo']")
	WebElement postPaymentMceId;

	@FindBy(xpath = "//a[@title='Services']")
	WebElement installmentService;

	@FindBy(xpath = "//a[@title='IRP']")
	WebElement installmentIRP;

	@FindBy(xpath = "//input[@id='AccountNo']")
	WebElement installmentAccountNo;

	@FindBy(xpath = "//input[@id='FltExpYear']")
	WebElement installmentFleetYear;

	@FindBy(xpath = "//a[@id='lnkGridSelectCustomerGrid']")
	WebElement installmentGrid;

	@FindBy(xpath = "//select[@id='paymentVos_0__PayType']")
	WebElement installmentPayType;

	@FindBy(xpath = "//input[@id='paymentVos_0__PaymentAmount']")
	WebElement installmentPaymentCash;

	@FindBy(xpath = "//select[@id='outPutModelSelect']")
	WebElement intallmentPdf;

	public void clickfinance() {
		clickElement(dashboardFinanceTab);
	}

	public void clickpostpayment() {
		clickElementUsingActions(financePostPayment);
	}

	public void enterMCEid(String MCEIDvalue) {
		clickElement(postPaymentMceId);
		webEditTxtChange(postPaymentMceId, MCEIDvalue);
	}

	public void clicksearch() {
		clickElement(postPaymentSearch);
	}

	public void clickoncartid() {
		clickElement(postPaymentCartId);
	}

	public void clickservice() {
		clickElement(installmentService);
	}

	public void clickIRP() {
		clickElement(installmentIRP);
	}

	public void selectpaymenttype(String selectValue) {
		selectFromDropdownByVisibleText(installmentPayType, selectValue);
	}

	public void entercashamount(String installmenttypevalue) {
		webEditTxtChange(installmentPaymentCash, installmenttypevalue);
	}

	public void clickreceipt(String selectValue) {
		selectFromDropdownByValue(intallmentPdf, selectValue);
	}

	public void clickandenterAccountNo(String accountnovalue) {
		webEditTxtChange(installmentAccountNo, accountnovalue);
	}

	public void clickandenterfleet(String fleetnovalue) {
		webEditTxtChange(installmentFleetNo, fleetnovalue);
	}

	public void clickandenterfleetyear(String yearvalue) {
		webEditTxtChange(installmentFleetYear, yearvalue);
	}

	public void clickgrid() throws InterruptedException {
		clickElement(installmentGrid);
	}

	public ArrayList<String> fetchTableHeader() {
		waitUntilElementsVisible(FeeHeaders);
		ArrayList<String> headersArray = new ArrayList<String>();
		for (int i = 0; i < FeeHeaders.size(); i++) {
			headersArray.add(FetchTextBoxValuewithText(FeeHeaders.get(i)));
		}
		return headersArray;
	}

	public ArrayList<String> fetchTableRowValues() {
		waitUntilElementsVisible(feeRow);
		ArrayList<String> jurisArray = new ArrayList<String>();
		for (int i = 0; i < feeRow.size(); i++) {
			jurisArray.add(FetchTextBoxValuewithText(feeRow.get(i)));
		}
		return jurisArray;
	}

	public String fetchPostPaymentAccountNoLbl() {
		return FetchTextBoxValuewithText(postPaymentAccountNoLbl);
	}

	public String fetchPostPaymentAccountNo() {
		return FetchTextBoxValuewithattribute(postPaymentAccountNo, "value");
	}

	public String fetchFleetNoLbl() {
		return FetchTextBoxValuewithText(installmentFleetNoLbl);
	}

	public String fetchFleetNo() {
		return FetchTextBoxValuewithattribute(installmentFleetNo, "value");
	}

	public String fetchLegalNameLbl() {
		return FetchTextBoxValuewithText(installmentLegalNameLbl);
	}

	public String fetchLegalName() {
		return FetchTextBoxValuewithattribute(installmentLegalName, "value");
	}

	public String fetchFleetMonthYearLbl() {
		return FetchTextBoxValuewithText(postPaymentFleerMonthYearLbl);
	}

	public String fetchFleetMonthYear() {
		return FetchTextBoxValuewithattribute(postPaymentFleerMonthYear, "value");
	}

	public String fetchSupplementNoLbl() {
		return FetchTextBoxValuewithText(installmentSupplementNoLbl);
	}

	public String fetchSupplementNo() {
		return FetchTextBoxValuewithattribute(installmentSupplementNo, "value");
	}

	public String fetchDBANameLbl() {
		return FetchTextBoxValuewithText(installmentDBANameLbl);
	}

	public String fetchDBAName() {
		return FetchTextBoxValuewithattribute(installmentDBAName, "value");
	}

	public String fetchFleetTypeLbl() {
		return FetchTextBoxValuewithText(postPaymentFleetTypeLbl);
	}

	public String fetchFleetType() {
		return FetchTextBoxValuewithattribute(postPaymentFleetType, "value");
	}

	public String fetchSupplementDescLbl() {
		return FetchTextBoxValuewithText(installmentSupplementDescLbl);
	}

	public String fetchSupplementDesc() {
		return FetchTextBoxValuewithattribute(installmentSupplementDesc, "value");
	}

	public String fetchUSDOTLbl() {
		return FetchTextBoxValuewithText(installmentUSDOTLbl);
	}

	public String fetchUSDOT() {
		return FetchTextBoxValuewithattribute(installmentUSDOT, "value");
	}

	public String fetchSupplementEffectiveDateLbl() {
		return FetchTextBoxValuewithText(postPaymentSupplementEffectiveDateLbl);
	}

	public String fetchSupplementEffectiveDate() {
		return FetchTextBoxValuewithattribute(postPaymentSupplementEffectiveDate, "value");
	}

	public String fetchSupplementStatusLbl() {
		return FetchTextBoxValuewithText(installmentSupplementStatusLbl);
	}

	public String fetchSupplementStatus() {
		return FetchTextBoxValuewithattribute(installmentSupplementStatus, "value");
	}

	public String fetchInvoiceDateLbl() {
		return FetchTextBoxValuewithText(installmentInvoiceDateLbl);
	}

	public String fetchInvoiceDate() {
		return FetchTextBoxValuewithattribute(installmentInvoiceDate, "value");
	}

	public String fetchInvoicenoLbl() {
		return FetchTextBoxValuewithText(installmentinvoiceNbrLbl);
	}

	public String fetchInvoiceNo() {
		return FetchTextBoxValuewithattribute(installmentInvoiceNbr, "value");
	}

	public String fetchEnterpriseSystemCreditLbl() {
		return FetchTextBoxValuewithText(postPaymentEnterpriseSystemCreditLbl);
	}

	public String fetchEnterpriseSystemCredit() {
		return FetchTextBoxValuewithattribute(postPaymentEnterpriseSystemCredit, "value");
	}

	public String fetchInstallmentReceiptDateLbl() {
		return FetchTextBoxValuewithText(installmentReceiptDateLbl);
	}

	public String fetchInstallmentReceiptDate() {
		return FetchTextBoxValuewithattribute(installmentReceiptDate, "value");
	}

	public String fetchInstallmentPlanNumberlbl() {
		return FetchTextBoxValuewithText(installmentInstallmentPlanNumberLbl);
	}

	public String fetchInstallmentPlanNumber() {
		return FetchTextBoxValuewithattribute(installmentInstallmentPlanNumber, "value");
	}

}
package com.celtic.automation.cmcs.parallel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.celtic.automation.cmcs.factory.DriverFactory;
import com.celtic.automation.cmcs.pages.AccountTabPage;
import com.celtic.automation.cmcs.pages.BillingTab;
import com.celtic.automation.cmcs.pages.CommonObjects;
import com.celtic.automation.cmcs.pages.DashBoardPage;
import com.celtic.automation.cmcs.pages.DistanceTabPage;
import com.celtic.automation.cmcs.pages.Enquiry;
import com.celtic.automation.cmcs.pages.Financepage;
import com.celtic.automation.cmcs.pages.FleetPage;
import com.celtic.automation.cmcs.pages.FleetTabPage;
import com.celtic.automation.cmcs.pages.LoginPage;
import com.celtic.automation.cmcs.pages.Payment;
import com.celtic.automation.cmcs.pages.PaymentTab;
import com.celtic.automation.cmcs.pages.Reinstatement;
import com.celtic.automation.cmcs.pages.VehicleAdd;
import com.celtic.automation.cmcs.pages.VehicleAmend;
import com.celtic.automation.cmcs.pages.VehicleDocuments;
import com.celtic.automation.cmcs.pages.VehicleTabPage;
import com.celtic.automation.cmcs.pages.WgtGroup;
import com.celtic.automation.cmcs.pages.WgtGroupAdd;
import com.celtic.automation.cmcs.util.ConfigReader;
import com.celtic.automation.cmcs.util.ElementUtil;
import com.celtic.automation.cmcs.util.ExcelUtil;
import com.celtic.automation.cmcs.util.GenericFunctions;
import com.celtic.automation.cmcs.util.Loggers;
import com.celtic.automation.cmcs.util.ScreenshotUtility;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RWC002 extends DriverFactory {
	private LoginPage loginpage;
	private DashBoardPage dashboardpage;
	private AccountTabPage accounttabpage;
	private FleetTabPage fleettabpage;
	private FleetPage fleetpage;
	private CommonObjects commonobjects;
	private DistanceTabPage distancetabpage;
	private WgtGroup wgtgroup;
	private WgtGroupAdd wgtgroupadd;
	private VehicleTabPage Vehicletabpage;
	private VehicleAmend vehicleAmend;
	private BillingTab billingtab;
	private Payment pay;
	private PaymentTab paymenttab;
	private VehicleAdd vehicleadd;
	private Financepage financepage;
	private VehicleDocuments vehicledocs;
	private Reinstatement reinstate;
	private Enquiry enquiry;
	private ConfigReader config;
	private ElementUtil eleutil;
	private ExcelUtil excelutilRin = null;
	private ExcelUtil excelutilWriteRin = null;
	private ExcelUtil excelutilRWC = null;
	private ExcelUtil excelutilWriteRWC = null;
	private ScreenshotUtility screenshotUtil;
	private ErrorCollector error = new ErrorCollector();
	private Loggers logger;
	private int readRowNo = 3;
	private int writeRowNo;
	private String ParentWindow = null;
	private String childWindow = null;
	private String fileLocation = null;
	private String DesiredPath = null;
	private String installmentPlanCheckStatus = null;
	private String[] fullClassName = this.getClass().getName().split("[.]");
	private String className = this.getClass().getName().split("[.]")[fullClassName.length - 1];

	public WebDriver driver;
	public String browser;
	Properties prop;

	@Before(value="@RWC002")
	public void launchBrowser(Scenario scenario) throws Exception {
		if (scenario.getName().toLowerCase().contains("chrome")) {
			browser = "chrome";
		} else if (scenario.getName().toLowerCase().contains("chromeincognito")) {
			browser = "chrome-incognito";
		} else if (scenario.getName().toLowerCase().contains("edge")) {
			browser = "edge";
		} else if (scenario.getName().toLowerCase().contains("firefox")) {
			browser = "firefox";
		}
		initdriver(browser);
	}

	@Given("Login as Internal user")
	public void login() throws Exception {
		logger = new Loggers();
		logger.configureLoggerSystem(new Throwable().getStackTrace()[0].getClassName());
		config = new ConfigReader(logger.loggingInstance());
		screenshotUtil = new ScreenshotUtility(logger.loggingInstance());
		eleutil = new ElementUtil(getDriver(),logger.loggingInstance());
		loginpage = new LoginPage(getDriver(),logger.loggingInstance());
		dashboardpage = new DashBoardPage(getDriver(),logger.loggingInstance());
		accounttabpage = new AccountTabPage(getDriver(),logger.loggingInstance());
		fleettabpage = new FleetTabPage(getDriver(),logger.loggingInstance());
		fleetpage = new FleetPage(getDriver(),logger.loggingInstance());
		commonobjects = new CommonObjects(getDriver(),logger.loggingInstance());
		distancetabpage = new DistanceTabPage(getDriver(),logger.loggingInstance());
		wgtgroup = new WgtGroup(getDriver(),logger.loggingInstance());
		wgtgroupadd = new WgtGroupAdd(getDriver(),logger.loggingInstance());
		Vehicletabpage = new VehicleTabPage(getDriver(),logger.loggingInstance());
		vehicleAmend = new VehicleAmend(getDriver(),logger.loggingInstance());
		billingtab = new BillingTab(getDriver(),logger.loggingInstance());
		pay = new Payment(getDriver(),logger.loggingInstance());
		paymenttab = new PaymentTab(getDriver(),logger.loggingInstance());
		vehicleadd = new VehicleAdd(getDriver(),logger.loggingInstance());
		financepage = new Financepage(getDriver(),logger.loggingInstance());
		vehicledocs = new VehicleDocuments(getDriver(),logger.loggingInstance());
		reinstate = new Reinstatement(getDriver(),logger.loggingInstance());
		enquiry = new Enquiry(getDriver(),logger.loggingInstance());
		try {
			logger.setLoggerInfo("*** Test Execution is about to begin ***");
		config.initprop();
		excelutilRin = new ExcelUtil(config.readRINexcel(),logger.loggingInstance());
		excelutilWriteRin = new ExcelUtil(config.writeRinExcel(),logger.loggingInstance());
		excelutilRWC = new ExcelUtil(config.readRwcExcel(),logger.loggingInstance());
		excelutilWriteRWC = new ExcelUtil(config.writeRwcExcel(),logger.loggingInstance());
		getDriver().get(config.readLoginURL());
		logger.setLoggerInfo(
				"****************************** Login to the application  *****************************************");
		screenshotUtil.captureScreenshot(className, "ApplicationLogin");
	writeRowNo = excelutilWriteRWC.getEmptyRowNumber(config.writeRwcExcel(), "Account");
		logger.setLoggerInfo("**RWC002-Launch the application using URL");
		
		loginpage.enterUsername(config.readLoginInternalUser());
		logger.setLoggerInfo("*** Enter Username ***");
		screenshotUtil.captureScreenshot(className, "Username");
		logger.setLoggerInfo("**RWC002-Enter Username");
		
		loginpage.enterPassword(config.readPswrd());
		logger.setLoggerInfo("*** Enter Password ***");
		screenshotUtil.captureScreenshot(className, "Password");
		logger.setLoggerInfo("**RWC002-Enter password");
		
		loginpage.clickLoginBtn();
		logger.setLoggerInfo("*** Click Login ***");
		screenshotUtil.captureScreenshot(className, "Login");
		logger.setLoggerInfo("**RWC002-click on Login");
		}
		catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@When("User will navigate to IRP & Reinstate Fleet")
	public void navigateReinstateFleet() throws Exception {
		try{
		CommonStep.scenario.log("Expand the Services header on the left column of the screen and select IRP");
		CommonStep.scenario.log("Click on Fleet Reinstatement  from Fleet card menu.");

		dashboardpage.clickIRPLink();
		logger.setLoggerInfo("*** Click IRP ***");
		screenshotUtil.captureScreenshot(className, "IRP");

		dashboardpage.clickFleetMore();
		logger.setLoggerInfo("*** Click Fleet More ***");
		screenshotUtil.captureScreenshot(className, "FleetMore");

		dashboardpage.clickFleetReinstatement();
		logger.setLoggerInfo("*** Click Fleet Reinstatement ***");
		screenshotUtil.captureScreenshot(className, "FleetReinstatement");

		CommonStep.scenario.log("Enter valid search data and click to proceed");

		fleetpage.enterAccountNo(excelutilRin.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("*** Enter Account Number ***");
		screenshotUtil.captureScreenshot(className, "Entering AccountNumber");

		/*
		 * Last inactive date should be previous month last date [now we are in Nov so
		 * slect oct-31]
		 */
		fleetpage.enterLastInactiveDays(excelutilRin.getCellData("PreSetup", "InactiveDate", readRowNo));
		logger.setLoggerInfo("*** Enter Last Inactive Days ***");
		screenshotUtil.captureScreenshot(className, "Entering Last Inactive Days");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		fleetpage.clickFirstHandimg();
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will move to Distance page & validate the message {string}")
	public void navigateDistanceIRP(String expSucces) throws Exception {
		try {
			if(reinstate.fetchHeaderRowRin().trim().equalsIgnoreCase("Distance Details")) {
		CommonStep.scenario.log("click on proceed button from Reinstatement-Distance Screen");

		logger.setLoggerInfo("*** Validating the information Message ***");
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen" + actualmessage);

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		screenshotUtil.captureScreenshot(className, "Validating Information Message");
	

		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceAccountNumberLbl(),
				writeRowNo, reinstate.distanceAccountNumber());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceFleetNumberLbl(),
				writeRowNo, reinstate.distanceFleetNumber());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceSupplementNumberLbl(),
				writeRowNo, reinstate.distanceSupplementNumber());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceLegalNameLbl(), writeRowNo,
				reinstate.distanceLegalName());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceDbaNameLbl(), writeRowNo,
				reinstate.distanceDbaName());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceFleetTypeLbl(), writeRowNo,
				reinstate.distanceFleetType());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceEffectiveDateLbl(),
				writeRowNo, reinstate.distanceEffectiveDate());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceExpirateMonthYearLbl(),
				writeRowNo, reinstate.distanceExpirateMonth() + "/" + reinstate.distanceExpirateYear());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceSupplementDescLbl(),
				writeRowNo, reinstate.distanceSupplementDesc());

		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distancereportingPeriodFromLbl(),
				writeRowNo, reinstate.distanceReportingPeriodFrom());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distancereportingPeriodToLbl(),
				writeRowNo, reinstate.distanceReportingPeriodTo());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceusdotNbrLbl(), writeRowNo,
				reinstate.distanceusdotNbr());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceEstimatedDistanceChartLbl(),
				writeRowNo, reinstate.distanceEstimatedDistanceChart());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceOverrideContJurLbl(),
				writeRowNo, reinstate.distanceOverrideContJur());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceEstimatedDistanceLbl(),
				writeRowNo, reinstate.distanceEstimatedDistance());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceActualDistanceLbl(),
				writeRowNo, reinstate.distanceActualDistance());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceTotalFleetDistanceLbl(),
				writeRowNo, reinstate.distanceTotalFleetDistance());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceFRPMlgQuetionLbl(),
				writeRowNo, reinstate.distanceFRPMlgQuetion());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceDistanceTypeLbl(),
				writeRowNo, reinstate.distanceDistanceType());
		excelutilWriteRin.setCellData(config.writeRinExcel(), "Distance", reinstate.distanceActualDistConfirmationLbl(),
				writeRowNo, reinstate.distanceActualDistConfirmation());

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
			}
		else {
			logger.setLoggerInfo("Screen is not in Distance Tab");
		}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to billing Page & verify the information")
	public void navigateBillingIRP() throws Exception {
		try {
			if(reinstate.fetchHeaderRowRin().trim().equalsIgnoreCase("Billing Details")) {
		CommonStep.scenario.log("click on proceed button from Reinstatement-Billing Screen");

		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchRegisterMonthLbl(), writeRowNo,
				reinstate.fetchRegisterMonth());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchNoOfVehiclesinSuppLbl(),
				writeRowNo, reinstate.fetchNoOfVehiclesinSupp());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchSupplementStatusLbl(),
				writeRowNo, reinstate.fetchSupplementStatus());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchEnterpriseSystemCreditLbl(),
				writeRowNo, reinstate.fetchEnterpriseSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchIRPSystemCreditLbl(),
				writeRowNo, reinstate.fetchIRPSystemCredit());

		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchInvoiceDateLbl(), writeRowNo,
				reinstate.fetchInvoiceDate());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchApplicationReceiptDateLbl(),
				writeRowNo, reinstate.fetchApplicationReceiptDate());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchPaymentDateLbl(), writeRowNo,
				reinstate.fetchPaymentDate());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchExchangeRateLbl(), writeRowNo,
				reinstate.fetchExchangeRate());

		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchManualAdjBaseJurLbl(),
				writeRowNo, reinstate.fetchManualAdjBaseJur());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchBillingBatchBillingLbl(),
				writeRowNo, reinstate.fetchBillingBatchBilling());
		excelutilWriteRWC.setCellData(config.writeRinExcel(), "Billing", reinstate.fetchBillingIsUseOneTimeMailingLbl(),
				writeRowNo, reinstate.fetchBillingIsUseOneTimeMailing());

		/* RIN- Billing->Application Receipt Date can be -current date */
		reinstate.enterreceiptdate(excelutilRin.getCellData("Billing", "LastReceiptDate", readRowNo));
		logger.setLoggerInfo("*** Enter  Receipt  Date ***");
		screenshotUtil.captureScreenshot(className, "Entering Receipt Date");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		}
		else {
			logger.setLoggerInfo("Screen is not in Billing Tab");
		}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@And("User will validate the Success Information Message {string}")
	public void successMessage(String expSucces) throws Exception {
		try {

		logger.setLoggerInfo("*** Validating the information Message ***");
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen" + actualmessage);

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		screenshotUtil.captureScreenshot(className, "Validating Information Message");
		}
		catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@When("User will navigate to the Renewal Screen")
	public void navigateRenewal() throws Exception {
		try {
		CommonStep.scenario.log("Expand the Services header on the left column of the screen and select IRP");
		CommonStep.scenario.log("Click on Renew fleet from Fleet card menu.");

		dashboardpage.clickIRPLink();
		logger.setLoggerInfo("*** Click IRP ***");
		screenshotUtil.captureScreenshot(className, "IRP");

		dashboardpage.clickRenewFleetLink();
		logger.setLoggerInfo("*** Click RenewFleet ***");
		screenshotUtil.captureScreenshot(className, "RenewFleet");

		CommonStep.scenario.log("Enter valid search data and click to proceed");

		fleetpage.enterAccountNo(excelutilRWC.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("*** Enter Account Number ***");
		screenshotUtil.captureScreenshot(className, "Entering AccountNumber");

		fleetpage.enterFleetNo(excelutilRWC.getCellData("PreSetup", "FleetNumber", readRowNo)); 
		logger.setLoggerInfo("*** Enter FleetNo ***");
		screenshotUtil.captureScreenshot(className, "Entering FleetNumber");

		fleetpage.enterFleetyear(excelutilRWC.getCellData("PreSetup", "Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("*** Click FleetYear ***");
		screenshotUtil.captureScreenshot(className, "Entering FleetYear");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to Account screen under IRP and provides all the required input to proceed")
	public void navigateAccountRenew() throws Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Customer Details")) {
		CommonStep.scenario.log("Enter valid all detail in account page with comments and click on proceed button");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchMCECustomernoLbl(),
				writeRowNo, accounttabpage.fetchMCECustomerNo());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchRegistrationTypeLbl(),
				writeRowNo, accounttabpage.fetchRegistrationType());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountCarrierTypeLbl(),
				writeRowNo, accounttabpage.fetchAccountCarrierType());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchIFTAAccountNbrlbl(),
				writeRowNo, accounttabpage.fetchIFTAAccountNbr());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountCustomerStatusLbl(),
				writeRowNo, accounttabpage.fetchAccountCustomerStatus());

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountStreet0Lbl(), writeRowNo,
				accounttabpage.fetchAccountStreet0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountZip0lbl(), writeRowNo,
				accounttabpage.fetchAccountZip0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountJur0lbl(), writeRowNo,
				accounttabpage.fetchAccountJur0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountCity0lbl(), writeRowNo,
				accounttabpage.fetchAccountCity0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountCounty0Lbl(), writeRowNo,
				accounttabpage.fetchAccountCounty0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountCountry0Lbl(), writeRowNo,
				accounttabpage.fetchAccountCountry0());

		accounttabpage.clickMailingAddress();
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountStreet1lbl(), writeRowNo,
				accounttabpage.fetchAccountStreet1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountZip1Lbl(), writeRowNo,
				accounttabpage.fetchAccountZip1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountJur1lbl(), writeRowNo,
				accounttabpage.fetchAccountJur1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountCity1Lbl(), writeRowNo,
				accounttabpage.fetchAccountCity1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountCounty1Lbl(), writeRowNo,
				accounttabpage.fetchAccountCounty1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountCountry1Lbl(), writeRowNo,
				accounttabpage.fetchAccountCountry1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountAttentionToLbl(), writeRowNo,
				accounttabpage.fetchAccountAttentionTo());

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountUSDOTNoLbl(),
				writeRowNo, accounttabpage.fetchAccountUSDOTNo());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountTPIDLbl(),
				writeRowNo, accounttabpage.fetchAccountTPID());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountContactNameLbl(),
				writeRowNo, accounttabpage.fetchAccountContactName());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountEmailLbl(),
				writeRowNo, accounttabpage.fetchAccountEmail());

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountPrimaryPhonelbl(),
				writeRowNo, accounttabpage.fetchAccountPrimaryPhone());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountAlternatePhoneLbl(),
				writeRowNo, accounttabpage.fetchAccountAlternatePhone());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountFaxNoLbl(),
				writeRowNo, accounttabpage.fetchAccountFaxNo());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountEmailNotificationLbl(), writeRowNo,
				accounttabpage.fetchAccountEmailNotification());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountFaxNotificationLbl(), writeRowNo,
				accounttabpage.fetchAccountFaxNotification());

		accounttabpage.checkEmailNotification();
		logger.setLoggerInfo("*** Check Email Notification ***");
		screenshotUtil.captureScreenshot(className, "Check EmailNotification");

		commonobjects.provideComments(excelutilRWC.getCellData("AccountTab", "Comments", readRowNo));
		logger.setLoggerInfo("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className, "Enter Comments in Account Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();


		CommonStep.scenario.log("Click on proceed from the verification page");
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
			}
			else {
				logger.setLoggerInfo("Screen is not in Account Tab");
			}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("user will navigate to Fleet to input the data and validate the message {string}")
	public void navigateFleetRenew(String expSucces) throws Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Fleet Details")) {
		String actualtext = commonobjects.fetchErrorMessage(expSucces);

		try {
			Assert.assertEquals(actualtext, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Fleet Screen" + expSucces);

		CommonStep.scenario.log(
				"Enter update all the mandatory and valid details in fleet page. Also update Fleet Expiration Date & Fleet Type & Commodity Class and click on proceed button after entering comments");

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchRegistrationTypeLbl(),
				writeRowNo, fleettabpage.fetchRegistrationType());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchFltStatusLbl(), writeRowNo,
				fleettabpage.fetchFltStatus());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchCarrierTypeLbl(), writeRowNo,
				fleettabpage.fetchCarrierType());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchDBANameLbl(), writeRowNo,
				fleettabpage.fetchDBAName());

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetStreet0Lbl(), writeRowNo,
				fleettabpage.fleetStreet0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetZip0Lbl(), writeRowNo,
				fleettabpage.fleetZip0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetJur0Lbl(), writeRowNo,
				fleettabpage.fleetJur0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetCity0Lbl(), writeRowNo,
				fleettabpage.fleetCity0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetCounty0Lbl(), writeRowNo,
				fleettabpage.fleetCounty0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetCountry0Lbl(), writeRowNo,
				fleettabpage.fleetCountry0());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetNonDeliverable0Lbl(), writeRowNo,
				fleettabpage.fleetNonDeliverable0());

		fleettabpage.clickMailingAddress();
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetStreet1Lbl(), writeRowNo,
				fleettabpage.fleetStreet1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetZip1Lbl(), writeRowNo,
				fleettabpage.fleetZip1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetJur1Lbl(), writeRowNo,
				fleettabpage.fleetJur1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetCity1Lbl(), writeRowNo,
				fleettabpage.fleetCity1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetCounty1Lbl(), writeRowNo,
				fleettabpage.fleetCounty1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetCountry1Lbl(), writeRowNo,
				fleettabpage.fleetCountry1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetNonDeliverable1Lbl(), writeRowNo,
				fleettabpage.fleetNonDeliverable1());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetAttentionToLbl(), writeRowNo,
				fleettabpage.fleetAttentionTO());

		fleettabpage.navigateToServiceProvider();
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceProviderLbl(), writeRowNo,
				fleettabpage.fleetServiceProvider());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceLegalNameLbl(),
				writeRowNo, fleettabpage.fleetServiceLegalName());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceDBANameLbl(), writeRowNo,
				fleettabpage.fleetServiceDBAName());

		if (fleettabpage.fleetServicePowerOfAttroneyLblpresence() == true) {
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
					fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServicePowerOfAttroneyLbl(),
					writeRowNo, fleettabpage.fleetServicePowerOfAttroney());
		}
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress()
						+ fleettabpage.fleetServicePowerOfAttroneyEffDateLbl(),
				writeRowNo, fleettabpage.fleetServicePowerOfAttroneyEffDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress()
						+ fleettabpage.fleetServicePowerOfAttroneyExpDateLbl(),
				writeRowNo, fleettabpage.fleetServicePowerOfAttroneyExpDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceEmailIdLbl(), writeRowNo,
				fleettabpage.fleetServiceEmailId());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServicePhoneNoLbl(), writeRowNo,
				fleettabpage.fleetServicePhoneNo());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceFaxNoLbl(), writeRowNo,
				fleettabpage.fleetServiceFaxNo());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceStreetLbl(), writeRowNo,
				fleettabpage.fleetServiceStreet());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceCityLbl(), writeRowNo,
				fleettabpage.fleetServiceCity());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceJurLbl(), writeRowNo,
				fleettabpage.fleetServiceJur());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceZipCodeLbl(), writeRowNo,
				fleettabpage.fleetServiceZipCode());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceCountryLbl(), writeRowNo,
				fleettabpage.fleetServiceCountry());

		fleettabpage.clickOnTimeMailingAddress();
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingStreetLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingStreet());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingZipCodeLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingZipCode());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingJurLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingJur());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingCityLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingCity());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingCountyLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingCounty());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingCountryLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingCountry());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingAttentionToLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingAttentionTo());

		eleutil.scrollToBottom();
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsContactNameLbl(),
				writeRowNo, fleettabpage.fleetDetailsContactName());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsEmailIdLbl(),
				writeRowNo, fleettabpage.fleetDetailsEmailId());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsPrimaryCellNbrLbl(),
				writeRowNo, fleettabpage.fleetDetailsPrimaryCellNbr());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsAlternateCellNbrLbl(),
				writeRowNo, fleettabpage.fleetDetailsAlternateCellNbr());

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFaxNoLbl(), writeRowNo,
				fleettabpage.fleetDetailsFaxNo());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsTpIdLbl(), writeRowNo,
				fleettabpage.fleetDetailsTPID());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsUsdotNbrLbl(),
				writeRowNo, fleettabpage.fleetDetailsUsdotNbr());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsChangeVehUsdotTinLbl(),
				writeRowNo, fleettabpage.fleetDetailsChangeVehUsdotTin());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFltTypeLbl(),
				writeRowNo, fleettabpage.fleetDetailsFltType());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsCommodityClassLbl(),
				writeRowNo, fleettabpage.fleetDetailsCommodityClass());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFltEffDateLbl(),
				writeRowNo, fleettabpage.fleetDetailsFltEffDatedtPicker());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFltExpDateLbl(),
				writeRowNo, fleettabpage.fleetDetailsFltExpDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsChangeAddrOnUsdotLbl(),
				writeRowNo, fleettabpage.fleetDetailsChangeAddrOnUsdot());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFirstOperatedDateLbl(),
				writeRowNo, fleettabpage.fleetDetailsFirstOperatedDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsWyomingIndicatorLbl(),
				writeRowNo, fleettabpage.fleetDetailsWyomingIndicator());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsIFTADistanceLbl(),
				writeRowNo, fleettabpage.fleetDetailsIFTADistance());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsMobileNotificationLbl(),
				writeRowNo, fleettabpage.fleetDetailsMobileNotification());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsIRPRequirementsLbl(),
				writeRowNo, fleettabpage.fleetDetailsIRPRequirements());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fleetDetailsStatementOfUnderstandingLbl(), writeRowNo,
				fleettabpage.fleetDetailsStatementOfUnderstanding());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fleetDetailsInstallmentAgreementLbl(), writeRowNo,
				fleettabpage.fleetDetailsInstallmentAgreement());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsPowerOfAttorneyLbl(),
				writeRowNo, fleettabpage.fleetDetailsPowerOfAttorney());

		if (fleettabpage.fleetHVUTFormLblpresence() == true) {
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsHVUTFormLbl(),
					writeRowNo, fleettabpage.fleetDetailsHVUTForm());
		}
		if (fleettabpage.fleetPropertyTaxLblpresence() == true) {
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsPropertyTaxLbl(),
					writeRowNo, fleettabpage.fleetDetailsPropertyTax());
		}

		eleutil.scrollToTop();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		logger.setLoggerInfo("Message in Fleet Screen " + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Fleet Screen 1");

		fleettabpage.navigateToServiceProvider();
		logger.setLoggerInfo("*** navigateToServiceProvider ***");
		screenshotUtil.captureScreenshot(className, "Navigate to Service provider");

		fleettabpage.clickPowerOfAttroney();
		logger.setLoggerInfo("*** Click PowerofAttroney ***");
		screenshotUtil.captureScreenshot(className, "Check Power of Attroney");

		fleettabpage.enterEmailID(excelutilRWC.getCellData("FleetTab", "Email iD", readRowNo));
		logger.setLoggerInfo("*** Entering the Emailid ***");
		screenshotUtil.captureScreenshot(className, "Entering the Emailid");

		fleettabpage.enterContactName(excelutilRWC.getCellData("FleetTab", "Contact Name", readRowNo));
		logger.setLoggerInfo("*** Entering the Contact Name ***");
		screenshotUtil.captureScreenshot(className, "Entering the Contact name");

		fleettabpage.enterprimaryPhone(excelutilRWC.getCellData("FleetTab", "Primary Phone", readRowNo));
		logger.setLoggerInfo("*** Entering the Primary Phone Number ***");
		screenshotUtil.captureScreenshot(className, "Entering the primary Phone Number");

		fleettabpage.selectFleetType(excelutilRWC.getCellData("FleetTab", "Fleet type", readRowNo));
		logger.setLoggerInfo("*** Selecting  the Fleet Type ***");
		screenshotUtil.captureScreenshot(className, "Selecting the Fleet Type");

		fleettabpage.selectCommodityClass(excelutilRWC.getCellData("FleetTab", "Commodity Class", readRowNo));
		logger.setLoggerInfo("*** Selectng  the Commondity Class ***");
		screenshotUtil.captureScreenshot(className, "Entering the Commodity Class");

		fleettabpage.selectEffectiveDate(excelutilRWC.getCellData("FleetTab", "Effective Date", readRowNo));
		logger.setLoggerInfo("*** Selecting the Effective Date ***");
		screenshotUtil.captureScreenshot(className, "Selecting the Effective Date");

		/*
		 *  RWC- Expiration date will not be blank in app- if value is present in
		 * excel  then only we have to pass in the application else we can skip that
		 */		if (excelutilRWC.getCellData("FleetTab", "Expiration Date", readRowNo) != null) {
			fleettabpage.selectExpirationDate(excelutilRWC.getCellData("FleetTab", "Expiration Date", readRowNo));
			logger.setLoggerInfo("*** Selecting the Expiration Date ***");
			screenshotUtil.captureScreenshot(className, "Selecting the Expiration Date");
		}

		fleettabpage.selectFirstOperatedDate(excelutilRWC.getCellData("FleetTab", "First Operated Date", readRowNo));
		logger.setLoggerInfo("*** Selecting the First Operated Date ***");
		screenshotUtil.captureScreenshot(className, "Selecting the First Operated Date");

		fleettabpage.selectPowerOfAttroney(excelutilRWC.getCellData("FleetTab", "Power of Attorney", readRowNo));
		logger.setLoggerInfo("*** Selecting PowerOfAttroney ***");
		screenshotUtil.captureScreenshot(className, "Selecting PowerOfAttroney");

		commonobjects.provideComments(excelutilRWC.getCellData("FleetTab", "Comments", readRowNo));
		logger.setLoggerInfo("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className, "Enter Comments in Fleet Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		CommonStep.scenario.log("Click on proceed button from the verification page");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
			}
			else {
				logger.setLoggerInfo("Screen is not in Fleet Details Tab");
			}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("user will navigate to Distance to input the data and validate message {string} {string}")
	public void navigateDistanceRenew(String expSucces1, String expSucces2) throws Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Distance Details")) {
		CommonStep.scenario.log(
				"Select Actual Distance radio button & Enter all the mandatory and valid details in distance page and click on proceed button after entering comments");
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceReportingPeriodFromLbl(), writeRowNo,
				distancetabpage.distanceReportingPeriodFrom());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceReportingPeriodToLbl(), writeRowNo,
				distancetabpage.distanceReportingPeriodTo());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceUsdotNbrLbl(),
				writeRowNo, distancetabpage.distanceUsdotNbr());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceEstimatedDistanceChartLbl(), writeRowNo,
				distancetabpage.distanceEstimatedDistanceChart());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceOverrideContJurLbl(),
				writeRowNo, distancetabpage.distanceOverrideContJur());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceEstimatedDistanceLbl(), writeRowNo,
				distancetabpage.distanceEstimatedDistance());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceActualDistanceLbl(),
				writeRowNo, distancetabpage.distanceActualDistance());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceTotalFleetDistanceLbl(), writeRowNo,
				distancetabpage.distanceTotalFleetDistance());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceFRPMlgQuetionLbl(),
				writeRowNo, distancetabpage.distanceFRPMlgQuetion());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceDistanceTypeLbl(),
				writeRowNo, distancetabpage.distanceDistanceType());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceActualDistConfirmationLbl(), writeRowNo,
				distancetabpage.distanceActualDistConfirmation());

		ArrayList<String> jurisValues = distancetabpage.fetchTableJuris();
		ArrayList<String> distanceValues = distancetabpage.fetchTableDistanceMiles();
		ArrayList<String> percentValues = distancetabpage.fetchTablePercent();

		for (int i = 0; i < jurisValues.size(); i++) {
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "DistanceJuris",
					distancetabpage.distanceJurisTableHeaderJuri() + i, writeRowNo, jurisValues.get(i));
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "DistanceJuris",
					distancetabpage.distanceJurisTableHeaderDistance() + i, writeRowNo, distanceValues.get(i));
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "DistanceJuris",
					distancetabpage.distanceJurisTableHeaderPercent() + i, writeRowNo, percentValues.get(i));
		}

		String actualmessage = commonobjects.fetchErrorMessage(expSucces1);
		try {
			Assert.assertEquals(actualmessage, expSucces1);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen" + actualmessage);

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		logger.setLoggerInfo("Message in Distance Screen" + commonobjects.fetchErrorMessage(expSucces1));

		screenshotUtil.captureScreenshot(className, "Message in Distance Screen 1");

		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen" + actualmessage2);

		logger.setLoggerInfo("Message in Distance Screen" + commonobjects.fetchErrorMessage(expSucces1));

		screenshotUtil.captureScreenshot(className, "Message in Distance Screen 1");

		distancetabpage
				.selectYesOrNo(excelutilRWC.getCellData("DistanceTab", "Actual Distance Reporting Period", readRowNo));
		logger.setLoggerInfo("*** Selecting yes or No of Reporting period Question***");

		commonobjects.provideComments(excelutilRWC.getCellData("DistanceTab", "Comments", readRowNo));
		logger.setLoggerInfo("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className, "Enter Comments in Distance Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		CommonStep.scenario.log("Click on proceed button from the verification page");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
			}
			else {
				logger.setLoggerInfo("Screen is not in Distance Details Tab");
			}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("user will navigate to Weight Group to add weight group {string}")
	public void addWeightGrpRenew(String expSucces) throws Exception {
	try {
		if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Weight Group Selection Details")) {
		CommonStep.scenario.log(
				"Click on Add weight group button Add new Weight Group(s) & enter all the mandatory and valid details in the weight group page and click on proceed button");
		CommonStep.scenario.log("Update an existing Weight Group & enter comments");

		ArrayList<String> headervalues = wgtgroup.fetchTableHeader();
		ArrayList<String> RowDatavalues = wgtgroup.fetchTableRowData();
		int j, k = 0;
		for (int i = 0; i < RowDatavalues.size(); i++) {
			if (i > 5) {
				j = i % 6;
				if (j == 0) {
					k++;
				}
				excelutilWriteRWC.setCellData(config.writeRwcExcel(), "WeightGroup", headervalues.get(j) + k,
						writeRowNo, RowDatavalues.get(i));
				logger.setLoggerInfo("Weight Group headervalues" + headervalues.get(j));
				logger.setLoggerInfo("Weight Group RowDatavalues" + RowDatavalues.get(i));
			} else {
				excelutilWriteRWC.setCellData(config.writeRwcExcel(), "WeightGroup", headervalues.get(i) + k,
						writeRowNo, RowDatavalues.get(i));
				logger.setLoggerInfo("Weight Group headervalues" + headervalues.get(i));
				logger.setLoggerInfo("Weight Group RowDatavalues" + RowDatavalues.get(i));
			}

		}

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Weight Group Screen" + actualmessage);

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		logger.setLoggerInfo("Message in Weight Group Screen" + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Weight Group Screen 1");

		String weightGroupCountExcel = excelutilRWC.getCellData("WeightGrouptab", "TotalWeightGroups", readRowNo);

		for (int weightcount = 0; weightcount < Integer.valueOf(weightGroupCountExcel); weightcount++) {
			if (weightcount < Integer.valueOf(weightGroupCountExcel)) {
				eleutil.sleepTime(2000);	 // for jenkins execution			
				wgtgroup.clickAddWeightGroup();
				logger.setLoggerInfo("*** Click AddweightGroup ***");
				screenshotUtil.captureScreenshot(className, "Click AddweightGroup");
			}

			wgtgroupadd.selectWeightGroupType(excelutilRWC.getCellData("WeightGrouptab",
					"WeightGroup Type" + String.valueOf(weightcount), readRowNo));
			logger.setLoggerInfo("*** Select WeightGroupType ***");
			screenshotUtil.captureScreenshot(className, "Select WeightGroupType");

			wgtgroupadd.enterWeightGroupNo(excelutilRWC.getCellData("WeightGrouptab",
					"Weight Group No." + String.valueOf(weightcount), readRowNo));
			logger.setLoggerInfo("*** Enter WeightGroup No ***");
			screenshotUtil.captureScreenshot(className, "Enter WeightGroup No");

			wgtgroupadd.selectMaxGrossWeight(excelutilRWC.getCellData("WeightGrouptab",
					"Max Gross Weight" + String.valueOf(weightcount), readRowNo));
			logger.setLoggerInfo("*** Select MaxGross Weight ***");
			screenshotUtil.captureScreenshot(className, "Select MaxGross Weight");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			CommonStep.scenario.log("Click on proceed button from the verification page");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
		}
		}
		else {
			logger.setLoggerInfo("Screen is not in Weight Group Selection Details Tab");
		}
		
	}catch(Exception e) {
		logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
		logger.closeTheHandler();
		throw new Exception(e);
	}

	}

	@And("user will amend the existing weight group")
	public void amendWeightGroup() throws Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Weight Group Selection Details")) {

		wgtgroup.clickHandImg();

		String juriExcelCount = excelutilRWC.getCellData("WeightJuris", "Juris Count", readRowNo);
		for (int i = 0; i < Integer.valueOf(juriExcelCount); i++) {
			String juriExcel = excelutilRWC.getCellData("WeightJuris", "Juri" + String.valueOf(i), readRowNo);
			wgtgroupadd.enterWeightJuriValue(juriExcel);
		}

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());


		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());


		String[] weightlist = wgtgroup.validateJurisWeightsedited(); 
		for (int i = 0; i < Integer.valueOf(juriExcelCount); i++) {
			String juriExcel = excelutilRWC.getCellData("WeightJuris", "Juri" + String.valueOf(i), readRowNo);
			if (weightlist[i].equalsIgnoreCase(juriExcel)) {
				assert true;
			}
		}

		CommonStep.scenario.log("Click Done");
		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
			}
			else {
				logger.setLoggerInfo("Screen is not in Weight Group Tab");
			}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("user will navigate to Vehicle screen and add the vehicles that was previously deleted in expired year and validate the message {string} {string}")
	public void navigateAndAddVehiclesRenew(String expSucces, String expSucces2) throws Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Renewal Vehicle Processing")) {
		CommonStep.scenario.log(
				"Complete Amend vehicle  and edit Ownership Details Safety USDOT and TIN and change Weight Group such that New Plate is requested Also request TVR and enter comments Delete Vehicle(s) such that all Documents are collected and Comments entered");
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchAmendVehicleLbl(),
				writeRowNo, Vehicletabpage.fetchAmendVehicle());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchAddVehiclesLbl(),
				writeRowNo, Vehicletabpage.fetchAddVehicles());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchDeleteVehicleLbl(),
				writeRowNo, Vehicletabpage.fetchDeleteVehicle());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchRenewVehicleLbl(),
				writeRowNo, Vehicletabpage.fetchRenewVehicle());

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		int NoofVehiclestoAdd = Integer
				.valueOf(excelutilRWC.getCellData("VehicleAddTab", "NoofVehiclestoAdd", readRowNo));
		if (NoofVehiclestoAdd > 0) {
			logger.setLoggerInfo(
					"Number of Vehicles to be Add is greater than zero Hence about to click the Add Vehicle Radio Button");

			Vehicletabpage.clickAddVehicleRadioButton();
			logger.setLoggerInfo("*** Click Add Vehicle RadioButton ***");
			screenshotUtil.captureScreenshot(className, "Click Add Vehicle RadioButton");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

			String actualmessage = commonobjects.fetchErrorMessage(expSucces);
			try {
				Assert.assertEquals(actualmessage, expSucces);
			} catch (Throwable e) {
				error.addError(e);
			}
			CommonStep.scenario.log("Message in Add Vehicle Screen" + actualmessage);

			logger.setLoggerInfo("Message in Add Vehicle Screen" + commonobjects.fetchErrorMessage(expSucces));
			screenshotUtil.captureScreenshot(className, "Message in Add Vehicle Screen");

			for (int addVehicle = 0; addVehicle < NoofVehiclestoAdd; addVehicle++) {

				vehicleadd.enterVINNumber(
						excelutilRWC.getCellData("VehicleAddTab", "VIN" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Enter VIN Before Add ***");
				screenshotUtil.captureScreenshot(className, "Enter VIN Before Add");

				vehicleadd.clickSearch();

				vehicleadd.selectBodyType(
						excelutilRWC.getCellData("VehicleAddTab", "BodyType" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter Body Type ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Body Type");

				vehicleadd.enterDetailsUnitNumber(excelutilRWC.getCellData("VehicleAddTab",
						"Unit Number" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter Unit Number ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Unit Number");

				vehicleadd.selectWeightGroupNumber(excelutilRWC.getCellData("VehicleAddTab",
						"WeightGroup Number" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter WeightGroupNo ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter WeightGroupNo");

				vehicleadd.enterYear(
						excelutilRWC.getCellData("VehicleAddTab", "year" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter Year ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Year");

				vehicleadd.selectMake(
						excelutilRWC.getCellData("VehicleAddTab", "Make" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter Make ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Make");

				vehicleadd.enterAxle(
						excelutilRWC.getCellData("VehicleAddTab", "Axle" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter Axle ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Axle");

				vehicleadd.enterUnladenWeight(excelutilRWC.getCellData("VehicleAddTab",
						"unladen Weight" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter UnladenWeight ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter UnladenWeight");
			
				vehicleadd.enterseats(
						excelutilRWC.getCellData("VehicleAddTab", "Seats" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle-Enter Seats ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Seats");

				vehicleadd.enterCombinedAxleEmpty(excelutilRWC.getCellData("VehicleAddTab",
						"CombinedAxle" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter Combined Axle ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Combined Axle");

				vehicleadd.selectColor(excelutilRWC.getCellData("VehicleAddTab",
						"VehicleColor" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter Vehicle Color ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Vehicle Color");

				/*
				 *  Purchase date should be past date
				 */				vehicleadd.enterPurchaseDate(excelutilRWC.getCellData("VehicleAddTab",
						"PurchaseDate" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter Purchase Date ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Purchase Date");

				vehicleadd.enterPurchaseprice(excelutilRWC.getCellData("VehicleAddTab",
						"Purchase Price" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter Purchase price ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Purchase price");

				vehicleadd.enterFactoryPrice(excelutilRWC.getCellData("VehicleAddTab",
						"Factory Price" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter Factory price ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Factory price");

				vehicleadd.checkTVR();
				logger.setLoggerInfo("***Add Vehicle-  Click TVR ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle- Click TVR");

				vehicleadd.enterTVRDays(
						excelutilRWC.getCellData("VehicleAddTab", "TVRDays" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter Number of TVR Days ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Number of TVR Days ");

				vehicleadd.selectTitleJurisdiction(excelutilRWC.getCellData("VehicleAddTab",
						"Type Juris" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Select Title Juri ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle- Select Title Juri");

				vehicleadd.enterTitleNo(
						excelutilRWC.getCellData("VehicleAddTab", "Title No" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter Title Number ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Title Number");

				if (excelutilRWC.getCellData("VehicleAddTab", "Existing Plate" + String.valueOf(addVehicle),
						readRowNo) != null) {
					vehicleadd.checkExistingPlate();
					logger.setLoggerInfo("***Add Vehicle- Check Existing plate ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle- Check Existing plate");

					vehicleadd.enterExistingPlate(excelutilRWC.getCellData("VehicleAddTab",
							"Existing Plate" + String.valueOf(addVehicle), readRowNo));
					logger.setLoggerInfo("***Add Vehicle- Enter Existing plate ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter Existing plate");
				}
				/*
				 *  In State Expiration date should be current date
				 */				
				vehicleadd.emptyenterInStateExpirationDate(eleutil.getCurrentDateInSpecifiedFormat("MMddYYYY"));
				logger.setLoggerInfo("***Add Vehicle- Enter InState ExpirationDate ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter InState ExpirationDate");

				vehicleadd.enterInStatePlate(excelutilRWC.getCellData("VehicleAddTab",
						"In-State Plate" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter InState Plate ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-Enter InState Plate");

				vehicleadd.selectInStatePlateType(excelutilRWC.getCellData("VehicleAddTab",
						"In-State Plate type" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Select InState Plate Type ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle- Select InState Plate Type");

				vehicleadd.enterInStatePlateWeight(excelutilRWC.getCellData("VehicleAddTab",
						"In-State Plate Weight" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter In State Plate Weight ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle- Enter In State Plate Weight");

				vehicleadd.enterInStateFee(excelutilRWC.getCellData("VehicleAddTab",
						"In-State Fee" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Enter In State Fee ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle- Enter In State Fee");

				if (vehicleadd.fetchOwnerName().isEmpty()) {
					vehicleadd.enterOwnerName(excelutilRWC.getCellData("VehicleAddTab", "OwnerName", readRowNo));
					logger.setLoggerInfo("***Add Vehicle- Enter Owner Name ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle- Enter Owner Name");
				}

				vehicleadd.selectSafetyChange(excelutilRWC.getCellData("VehicleAddTab",
						"Safety Change" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- Select Safety Change ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle- Select Safety Change");

				if (excelutilRWC.getCellData("VehicleAddTab",
						"Override Grade Crossing Fee" + String.valueOf(addVehicle), readRowNo) != null) {
					vehicleadd.checkGradeCrossingFee();
					logger.setLoggerInfo("***Add Vehicle- check Grade Crossing Fee ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle-check Grade Crossing Fee");
				}

				if (excelutilRWC.getCellData("VehicleAddTab", "Override Bicentennial Fee" + String.valueOf(addVehicle),
						readRowNo) != null) {
					vehicleadd.checkBicenetenniall();
					logger.setLoggerInfo("***Add Vehicle- check Bicenetenniall ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle-check Bicenetenniall");
				}
				if (excelutilRWC.getCellData("VehicleAddTab", "Override Second Plate Fee" + String.valueOf(addVehicle),
						readRowNo) != null) {
					vehicleadd.checkSecondPlateFee();
					logger.setLoggerInfo("***Add Vehicle- checkSecondPlateFee***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle-checkSecondPlateFee");
				}

				vehicleadd.selectAffidavitDoc(excelutilRWC.getCellData("VehicleAddTab",
						"Affidavit document" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle- select Affidavit Doc ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle-select Affidavit Doc");

				commonobjects.provideComments(excelutilRWC.getCellData("VehicleTab", "AddComments", readRowNo));

				logger.setLoggerInfo("*** Enter Comments ***");
				screenshotUtil.captureScreenshot(className, "Enter Comments in Add Vehicle Section");

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();

				String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
				try {
					Assert.assertEquals(actualmessage2, expSucces2);
				} catch (Throwable e) {
					error.addError(e);
				}
				CommonStep.scenario.log("Message in Add Vehicle Screen " + actualmessage2);

				logger.setLoggerInfo("Message in Amend Vehicle Screen " + commonobjects.fetchErrorMessage(expSucces2));
				screenshotUtil.captureScreenshot(className, "Message in Add Vehicle Screen");

			}

			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
			logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		}
			}
			else {
				logger.setLoggerInfo("Screen is not in Vehicle Renewal Entry Tab");
			}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@And("user will navigate to vehicle list to update the added vehicle details and validate message {string}")

	public void vehicleListUpdateRenew(String expSucces) throws Exception {
try {
	if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Renewal Vehicle Processing")) {
		CommonStep.scenario.log("Cancel the Added Vehicle ");

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		int NoofVehiclestoDelete = Integer
				.valueOf(excelutilRWC.getCellData("VehicleTab", "NoOfVehiclesToDelete", readRowNo));

		if (NoofVehiclestoDelete > 0) {
			CommonStep.scenario.log("Go to Vehicle List and update/cancel  Vehicle details");

			logger.setLoggerInfo(
					"Number of Vehicles to be deleted is greater than zero Hence about to click the Vehicle List");

			for (int addVehicle = 0; addVehicle < NoofVehiclestoDelete; addVehicle++) {

				Vehicletabpage.clickVehicleList();
				logger.setLoggerInfo("*** Click VehicleList ***");
				screenshotUtil.captureScreenshot(className, "Click VehicleList");

				vehicleadd.enterVINNumber(
						excelutilRWC.getCellData("VehicleUpdate", "VIN" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Cancel- Enter VIN No ***");
				screenshotUtil.captureScreenshot(className, "Cancel-Enter VIN No");

				vehicleAmend.clickSearch();
				logger.setLoggerInfo("***Cancel- click on Search ***");
				screenshotUtil.captureScreenshot(className, "Cancel-click on Search");

				vehicleadd.selectFirstHandIcon();

				vehicleadd.CancelVehicle();

				eleutil.handleAlert();

				logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

				String actualmessage = commonobjects.fetchErrorMessage(expSucces);
				try {
					Assert.assertEquals(actualmessage, expSucces);
				} catch (Throwable e) {
					error.addError(e);
				}

				CommonStep.scenario.log("Message in Vehicle Cancel Screen" + actualmessage);

				logger.setLoggerInfo("Message in Vehicle Cancel Screen" + commonobjects.fetchErrorMessage(expSucces));
				screenshotUtil.captureScreenshot(className, "Message in Vehicle Cancel Screen 1");

				Vehicletabpage.clickAddVehicleRadioButton();
				logger.setLoggerInfo("***Add Again-Click Add Vehicle RadioButton ***");
				screenshotUtil.captureScreenshot(className, "Add Again-Click Add Vehicle RadioButton");

				commonobjects.clickProceed();

				vehicleadd.enterVINNumber(
						excelutilRWC.getCellData("VehicleUpdate", "VIN" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again - Enter VIN Before Add ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again - Enter VIN Before Add");

				vehicleadd.clickSearch();

				vehicleadd.selectBodyType(
						excelutilRWC.getCellData("VehicleUpdate", "BodyType" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter Body Type ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Body Type");

				vehicleadd.enterDetailsUnitNumber(excelutilRWC.getCellData("VehicleUpdate",
						"Unit Number" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter Unit Number ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Unit Number");

				vehicleadd.selectWeightGroupNumber(excelutilRWC.getCellData("VehicleUpdate",
						"WeightGroup Number" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter WeightGroupNo ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter WeightGroupNo");

				vehicleadd.enterYear(
						excelutilRWC.getCellData("VehicleUpdate", "year" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter Year ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Year");

				vehicleadd.selectMake(
						excelutilRWC.getCellData("VehicleUpdate", "Make" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter Make ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Make");

				vehicleadd.enterAxle(
						excelutilRWC.getCellData("VehicleUpdate", "Axle" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter Axle ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Axle");

				vehicleadd.enterUnladenWeight(excelutilRWC.getCellData("VehicleUpdate",
						"unladen Weight" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter UnladenWeight ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter UnladenWeight");

				vehicleadd.enterseats(
						excelutilRWC.getCellData("VehicleUpdate", "Seats" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("*** Add Vehicle again -Enter Seats ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Seats");

				vehicleadd.enterCombinedAxleEmpty(excelutilRWC.getCellData("VehicleUpdate",
						"CombinedAxle" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter Combined Axle ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Combined Axle");

				vehicleadd.selectColor(excelutilRWC.getCellData("VehicleUpdate",
						"VehicleColor" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter Vehicle Color ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Vehicle Color");

				/*
				 *  Purchase date should be past date
				 */				
				vehicleadd.enterPurchaseDate(excelutilRWC.getCellData("VehicleUpdate",
						"PurchaseDate" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter Purchase Date ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Purchase Date");

				vehicleadd.enterPurchaseprice(excelutilRWC.getCellData("VehicleUpdate",
						"Purchase Price" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter Purchase price ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again Enter Purchase price");

				vehicleadd.enterFactoryPrice(excelutilRWC.getCellData("VehicleUpdate",
						"Factory Price" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter Factory price ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Factory price");

				vehicleadd.checkTVR();
				logger.setLoggerInfo("***Add Vehicle again -  Click TVR ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again - Click TVR");

				vehicleadd.enterTVRDays(
						excelutilRWC.getCellData("VehicleUpdate", "TVRDays" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter Number of TVR Days ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Number of TVR Days ");

				vehicleadd.selectTitleJurisdiction(excelutilRWC.getCellData("VehicleUpdate",
						"Type Juris" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Select Title Juri ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again - Select Title Juri");

				vehicleadd.enterTitleNo(
						excelutilRWC.getCellData("VehicleUpdate", "Title No" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter Title Number ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Title Number");

				if (excelutilRWC.getCellData("VehicleUpdate", "Existing Plate" + String.valueOf(addVehicle),
						readRowNo) != null) {

					vehicleadd.checkExistingPlate();
					logger.setLoggerInfo("***Add Vehicle again - Check Existing plate ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle again - Check Existing plate");

					vehicleadd.enterExistingPlate(excelutilRWC.getCellData("VehicleUpdate",
							"Existing Plate" + String.valueOf(addVehicle), readRowNo));
					logger.setLoggerInfo("***Add Vehicle again - Enter Existing plate ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Existing plate");
				}

				/*
				 *  In state Feilds -In State Expiration date should be current date
				 */
				vehicleadd.emptyenterInStateExpirationDate(eleutil.getCurrentDateInSpecifiedFormat("MMddYYYY"));
				logger.setLoggerInfo("***Add Vehicle again - Enter InState ExpirationDate ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter InState ExpirationDate");

				vehicleadd.enterInStatePlate(excelutilRWC.getCellData("VehicleUpdate",
						"In-State Plate" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter InState Plate ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter InState Plate");

				vehicleadd.selectInStatePlateType(excelutilRWC.getCellData("VehicleUpdate",
						"In-State Plate type" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Select InState Plate Type ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again - Select InState Plate Type");

				vehicleadd.enterInStatePlateWeight(excelutilRWC.getCellData("VehicleUpdate",
						"In-State Plate Weight" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter In State Plate Weight ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again - Enter In State Plate Weight");

				vehicleadd.enterInStateFee(excelutilRWC.getCellData("VehicleUpdate",
						"In-State Fee" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Enter In State Fee ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again - Enter In State Fee");

				if (vehicleadd.fetchOwnerName().isEmpty()) {
					vehicleadd.enterOwnerName(excelutilRWC.getCellData("VehicleUpdate", "OwnerName", readRowNo));
					logger.setLoggerInfo("***Add Vehicle again - Enter Owner Name ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle again - Enter Owner Name");
				}

				vehicleadd.selectSafetyChange(excelutilRWC.getCellData("VehicleUpdate",
						"Safety Change" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - Select Safety Change ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again - Select Safety Change");

				if (excelutilRWC.getCellData("VehicleUpdate",
						"Override Grade Crossing Fee" + String.valueOf(addVehicle), readRowNo) != null) {
					vehicleadd.checkGradeCrossingFee();
					logger.setLoggerInfo("***Add Vehicle again - check Grade Crossing Fee ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle again -check Grade Crossing Fee");
				}

				if (excelutilRWC.getCellData("VehicleUpdate", "Override Bicentennial Fee" + String.valueOf(addVehicle),
						readRowNo) != null) {
					vehicleadd.checkBicenetenniall();
					logger.setLoggerInfo("***Add Vehicle again - check Bicenetenniall ***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle again -check Bicenetenniall");
				}

				if (excelutilRWC.getCellData("VehicleUpdate", "Override Second Plate Fee" + String.valueOf(addVehicle),
						readRowNo) != null) {
					vehicleadd.checkSecondPlateFee();
					logger.setLoggerInfo("***Add Vehicle again - checkSecondPlateFee***");
					screenshotUtil.captureScreenshot(className, "Add Vehicle again -checkSecondPlateFee");
				}

				vehicleadd.selectAffidavitDoc(excelutilRWC.getCellData("VehicleUpdate",
						"Affidavit document" + String.valueOf(addVehicle), readRowNo));
				logger.setLoggerInfo("***Add Vehicle again - select Affidavit Doc ***");
				screenshotUtil.captureScreenshot(className, "Add Vehicle again -select Affidavit Doc");

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();

				commonobjects.clickDoneBtn();
			}
		}
			else {
				logger.setLoggerInfo("Screen is not in Vehicle Renewal Entry Tab");
			}

		}
}catch(Exception e) {
	logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
	logger.closeTheHandler();
	throw new Exception(e);
}

	}

	@And("user will navigate to vehicle list to modify the added vehicle details")
	public void vehicleListModifyRecentlyAddedVehicle() throws Exception {
try {
	if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Renewal Vehicle Processing")) {
		int NoofVehiclestoAmend = Integer
				.valueOf(excelutilRWC.getCellData("VehicleModify", "NoofVehiclestoModify", readRowNo));
		if (NoofVehiclestoAmend > 0) {
			logger.setLoggerInfo(
					"Number of Vehicles to be Modified is greater than zero Hence about to click the Amend Vehicle Radio Button");
			for (int addVehicle = 0; addVehicle < NoofVehiclestoAmend; addVehicle++) {

				Vehicletabpage.clickVehicleList();

				logger.setLoggerInfo("*** Click VehicleList ***");

				screenshotUtil.captureScreenshot(className, "Click VehicleList");
				vehicleadd.enterVINNumber(
						excelutilRWC.getCellData("VehicleModify", "Vin" + String.valueOf(addVehicle), readRowNo));

				logger.setLoggerInfo("*** Cancel- Enter VIN No ***");

				screenshotUtil.captureScreenshot(className, "Cancel-Enter VIN No");

				vehicleAmend.clickSearch();

				logger.setLoggerInfo("***Cancel- click on Search ***");

				screenshotUtil.captureScreenshot(className, "Cancel-click on Search");

				vehicleadd.selectFirstHandIcon();
				vehicleadd.selectColor(excelutilRWC.getCellData("VehicleModify",
						"VehicleColor" + String.valueOf(addVehicle), readRowNo));

				logger.setLoggerInfo("***Add Vehicle again - Enter Vehicle Color ***");

				screenshotUtil.captureScreenshot(className, "Add Vehicle again -Enter Vehicle Color");

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();
				commonobjects.clickDoneBtn();
				logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
			}
		}
	}
	else {
		logger.setLoggerInfo("Screen is not in Vehicle Renewal Entry Tab");
	}
}catch(Exception e) {
	logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
	logger.closeTheHandler();
	throw new Exception(e);
}
	}

	@Then("User will navigate to billing to verify as well to waive fees and adjust the cost fees {string}")

	public void navigateBillingRenew(String expSucces) throws Exception {
		try {
		if (eleutil.getTitle().trim().equalsIgnoreCase("Vehicle Renewal Entry - IRP")) {
			logger.setLoggerInfo("Screen is in Vehicle Renewal Entry Screen");
			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
		}
		CommonStep.scenario.log("Click Done from the supplement selection page");
		
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchRegisterMonthLbl(), writeRowNo,
				billingtab.fetchRegisterMonth());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchNoOfVehiclesInSuppLbl(),
				writeRowNo, billingtab.fetchNoOfVehiclesinSupp());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchSupplementStatusLbl(),
				writeRowNo, billingtab.fetchSupplementStatus());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchEnterpriseSystemCreditLbl(),
				writeRowNo, billingtab.fetchEnterpriseSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchIRPSystemCreditLbl(),
				writeRowNo, billingtab.fetchIRPSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchRenewalFeeEffectiveDatelbl(),
				writeRowNo, billingtab.fetchRenewalFeeEffectiveDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchInvoiceDateLbl(), writeRowNo,
				billingtab.fetchInvoiceDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchApplicationReceiptDateLbl(),
				writeRowNo, billingtab.fetchApplicationReceiptDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchPaymentDateLbl(), writeRowNo,
				billingtab.fetchPaymentDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchExchangeRateLbl(), writeRowNo,
				billingtab.fetchExchangeRate());

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchManualAdjBaseJurLbl(),
				writeRowNo, billingtab.fetchManualAdjBaseJur());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingBatchBillingLbl(),
				writeRowNo, billingtab.fetchBillingBatchBilling());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingTVRLbl(), writeRowNo,
				billingtab.fetchBillingTVR());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingInstallmentPlanLbl(),
				writeRowNo, billingtab.fetchBillingInstallmentPlan());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing",
				billingtab.fetchBillingIsUseOneTimeMailingLbl(), writeRowNo,
				billingtab.fetchBillingIsUseOneTimeMailing());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchElectronicDeliveryTypelbl(),
				writeRowNo, billingtab.fetchElectronicDeliveryType());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", "Email", writeRowNo,
				billingtab.fetchBillingEmail());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchInvoiceReportTypelbl(),
				writeRowNo, billingtab.fetchBillingInvoiceReportType());

		ArrayList<String> TableFeeType = billingtab.fetchTableFeeType();
		ArrayList<String> TableFeeAmount = billingtab.fetchTableFeeAmount();

		for (int i = 0; i < TableFeeType.size(); i++) {
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "BillingGrid",
					billingtab.fetchBillingGridFeeType() + i, writeRowNo, TableFeeType.get(i));
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "BillingGrid",
					billingtab.fetchBillingGridFeeAmount() + i, writeRowNo, TableFeeAmount.get(i));
		}

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		billingtab.clickTVR();
		logger.setLoggerInfo("*** Billing-Click TVR ***");
		screenshotUtil.captureScreenshot(className, "Billing-Click TVR");
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingTVRNoOfDaysLbl(),
				writeRowNo, billingtab.fetchBillingTVRNoOfDays());

		installmentPlanCheckStatus = billingtab.fetchBillingInstallmentPlan();
		logger.setLoggerInfo("*** Billing-Installment Plan Check Box Status ***" + installmentPlanCheckStatus); 
		CommonStep.scenario.log("Check Installment Payment checkbox");

		billingtab.clickInstallmentPlan();
		logger.setLoggerInfo("*** Billing-Click Installement Plan ***");
		screenshotUtil.captureScreenshot(className, "Billing-Click Installement Plan");

		billingtab.selectElectronicDeliveryType(
				excelutilRWC.getCellData("BillingTab", "Electronic Delivery Type", readRowNo));
		logger.setLoggerInfo("*** Billing-Select Electronic DeliveryType ***");
		screenshotUtil.captureScreenshot(className, "Billing-Select Electronic DeliveryType");

		commonobjects.provideComments(excelutilRWC.getCellData("BillingTab", "BillingComments", readRowNo));
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		billingtab.enterManualAdjBaseJur(excelutilRWC.getCellData("BillingTab", "Manual Adj. Base Jur.", readRowNo));
		logger.setLoggerInfo("*** Billing-Enter ManualAdjBaseJur ***");
		screenshotUtil.captureScreenshot(className, "Billing-Enter ManualAdjBaseJur");

		billingtab.clickReCalculate();
		logger.setLoggerInfo("*** Billing-Click Recalculate ***");
		screenshotUtil.captureScreenshot(className, "Billing-Click Recalculate");

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Biling Screen" + actualmessage);

		logger.setLoggerInfo("Message in Biling Screen" + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Biling Screen 1");

		billingtab.expandManualAdjReason();
		billingtab.enterManualAdjReasonComments(excelutilRWC.getCellData("BillingTab", "ManualReason", readRowNo));
		logger.setLoggerInfo("*** Enter ManualAdjReason Comments ***");
		screenshotUtil.captureScreenshot(className, "Enter ManualAdjReason Comments");

		billingtab.clickManualAdjReasonDeleteAllowed();
		billingtab.clickManualAdjReasonAddorUpdateComments();
		CommonStep.scenario.log("Click on proceed button");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		billingtab.clickReCalculate();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		ParentWindow = eleutil.GetParentWindow();

		installmentPlanCheckStatus = billingtab.fetchBillingInstallmentPlan();
		logger.setLoggerInfo("*** Billing-Installment Plan Check Box Status after proceed ***" + installmentPlanCheckStatus);
		CommonStep.scenario.log("Manually Adjust & Waive Fees with comments & set Delivery Types as PDF");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		eleutil.waitForTwoWindow(2);
		childWindow = eleutil.SwitchtoFirstChildWindow();
	/*	eleutil.sleepTime(4000); // to wait for swith to 2nd window
		eleutil.saveAsFile();
		fileLocation = System.getProperty("user.dir") + "\\" + config.readDownloadFolder() + className + "\\";
		logger.setLoggerInfo("fileLocation" + fileLocation);
		DesiredPath = eleutil.checkFileExistence(fileLocation, "Billing", "pdf");
		eleutil.sleepTime(4000); // to wait for the PDF Load completely
		eleutil.uploadFile(DesiredPath);
		eleutil.sleepTime(4000); // to display the file upload is completed
		*/
		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to Payment Tab to input the fields and validate message {string} {string} {string}")
	public void navigatePaymentRenew(String expSucces, String expSucces2, String expSucces3) throws Exception {
		try {

		CommonStep.scenario.log("Verify the page & set Delivery Types as PDF Click on proceed button");

		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchEnterpriseSystemCreditLbl(),
				writeRowNo, paymenttab.fetchEnterpriseSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchIRPSystemCreditLbl(),
				writeRowNo, paymenttab.fetchIRPSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchInvoiceDateLbl(),
				writeRowNo, paymenttab.fetchInvoiceDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchInvoiceNumberLbl(),
				writeRowNo, paymenttab.fetchInvoiceNumber());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchPaymentReciptDateLbl(),
				writeRowNo, paymenttab.fetchPaymentReciptDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchManualAdjBaseJurLbl(),
				writeRowNo, paymenttab.fetchManualAdjBaseJur());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchBatchCredentialLbl(),
				writeRowNo, paymenttab.fetchBatchCredential());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchWireTransferFeeLbl(),
				writeRowNo, paymenttab.fetchWireTransferFee());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchElectronicDeliveryTypeLbl(),
				writeRowNo, paymenttab.fetchElectronicDeliveryType());

		ArrayList<String> paymentTableFeeType = paymenttab.fetchTableFeeType();
		ArrayList<String> paymentTableFeeAmount = paymenttab.fetchTableFeeAmount();
		for (int i = 0; i < paymentTableFeeType.size(); i++) {
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchHeaderFeeType() + i,
					writeRowNo, paymentTableFeeType.get(i));
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchHeaderFeeAmount() + i,
					writeRowNo, paymentTableFeeAmount.get(i));
		}

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		String actualmessage1 = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage1, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen" + expSucces);

		logger.setLoggerInfo("Message in Payment Screen" + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Payment Screen 1");

		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		} catch (Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen" + expSucces2);

		logger.setLoggerInfo("Message in Payment Screen" + commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className, "Message in Payment Screen 2");
		String actualmessage3 = commonobjects.fetchErrorMessage(expSucces3);
		try {
			Assert.assertEquals(actualmessage3, expSucces3);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen" + expSucces3);

		logger.setLoggerInfo("Message in Payment Screen" + commonobjects.fetchErrorMessage(expSucces3));
		screenshotUtil.captureScreenshot(className, "Message in Payment Screen 3");

		pay.selectElectronicDeliverytype(excelutilRWC.getCellData("Payment", "ElectronicDeliveryType", readRowNo));
		logger.setLoggerInfo("***Payment Screen-Select Delivery type***");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());


		CommonStep.scenario.log("Payment Screen-CClick pay Now");

		paymenttab.clickPayNow();
		logger.setLoggerInfo("***Payment Screen-Click Pay Now**");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		commonobjects.validateInfoMsgs();


		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchMCECustomerIdLbl(), writeRowNo,
				pay.fetchMCECustomerId());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchLegalNameLbl(), writeRowNo,
				pay.fetchLegalName());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchInvoiceDateLbl(), writeRowNo,
				pay.fetchInvoiceDate());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchEnterpriseSystemCreditLbl(),
				writeRowNo, pay.fetchEnterpriseSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchIRPSystemCreditLbl(),
				writeRowNo, pay.fetchIRPSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchIftaSystemCreditLbl(),
				writeRowNo, pay.fetchIftaSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchOpaSystemCreditLbl(),
				writeRowNo, pay.fetchOpaSystemCredit());
		excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchTotalAmountDueLbl(), writeRowNo,
				pay.fetchTotalAmountDue());

		ArrayList<String> tableHeaderValues = pay.fetchTableHeaders();
		ArrayList<String> tableInvoice = pay.fetchTableInvoiceDetails();
		for (int i = 0; i < tableInvoice.size(); i++) {
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "PaymentScreen", tableHeaderValues.get(i), writeRowNo,
					tableInvoice.get(i));
		}

		String paymentExcelCount = excelutilRWC.getCellData("Payment", "payment Count", readRowNo);

		CommonStep.scenario
				.log("Select multiple payment types and select PDF delivery type and click on proceed button");
		for (int i = 0; i < Integer.valueOf(paymentExcelCount); i++) {

			logger.setLoggerInfo("no.of rows present is:" + pay.fetchTableRowsize());
			if (pay.fetchTableRowsize() < Integer.valueOf(paymentExcelCount)) {
				paymenttab.clickPaymenToAdd();
			}
			String PaymentType = excelutilRWC.getCellData("Payment", "PaymentType" + i, readRowNo);
			String PaymentNumberValue = excelutilRWC.getCellData("Payment", "PaymentChequeNo", readRowNo);
			pay.selectPaymentType(i, PaymentType);
			logger.setLoggerInfo("***Payment Screen-Payment Type***");
			screenshotUtil.captureScreenshot(className, "Payment Screen-Payment Type" + i);

			pay.enterpaymentNoBasedonType(i, PaymentNumberValue);
			logger.setLoggerInfo("***Payment Screen-Payment Number based on Payment Type***");
			screenshotUtil.captureScreenshot(className, "Payment Screen-Payment Number based on  Payment Type" + i);

			String totalAmount = pay.fetchTotalAmount();
			logger.setLoggerInfo("Payment Screen-totalAmount is " + totalAmount);
			String cashAmount = String.format("%.2f",
					(Double.valueOf(totalAmount) / Integer.valueOf(paymentExcelCount)));
			if (i == (Integer.valueOf(paymentExcelCount) - 1)) {
				String RemainingAmount = pay.fetchRemainingBalance();
				logger.setLoggerInfo("Payment Screen-Remaining balance is:" + RemainingAmount);
				pay.enterPaymentAmountBasedonType(i, RemainingAmount);
			} else {
				pay.enterPaymentAmountBasedonType(i, cashAmount);
			}

			logger.setLoggerInfo("***Payment Screen-Payment Amount based on Payment Type***");
			screenshotUtil.captureScreenshot(className, "Payment Screen-Payment Amount based on  Payment Type" + i);
		}

		pay.selectPaymentReceipt(excelutilRWC.getCellData("Payment", "Payment receipt", readRowNo));
		logger.setLoggerInfo("***Payment Screen-Enter Payment type and amount***");
		ParentWindow = eleutil.GetParentWindow();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		eleutil.waitForTwoWindow(2);
		childWindow = eleutil.SwitchtoFirstChildWindow();
		eleutil.sleepTime(4000); // to wait for switch to 2nd window
	/*	eleutil.saveAsFile();
		fileLocation = System.getProperty("user.dir") + "\\" + config.readDownloadFolder() + className + "\\";
		logger.setLoggerInfo("fileLocation" + fileLocation);
		DesiredPath = eleutil.checkFileExistence(fileLocation, "Payment", "pdf");
		eleutil.sleepTime(4000); // to wait for the PDF Load completely
		eleutil.uploadFile(DesiredPath);
		eleutil.sleepTime(4000); // to display the file upload is completed
*/
		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate will collect all vehicle level documents from Vehicle Documents {string}")
	public void vehicleDocumentCredentials(String expSucces) throws Exception {
		try {
		CommonStep.scenario.log("Collect Vehicle Level Documents");

		dashboardpage.clickEnterpriseLink();
		commonobjects.waitForSpinner();

		dashboardpage.clickIRPLink();
		commonobjects.waitForSpinner();

		vehicledocs.clickVehicleDocuments();
		logger.setLoggerInfo("***Vehicle Document Screen-Click on Vehicle Document ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Click on Vehicle Document");

		fleetpage.enterAccountNo(excelutilRWC.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("***Vehicle Document Screen-Enter Account Number ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Enter Account Number");

		fleetpage.enterFleetNoSupplement(excelutilRWC.getCellData("PreSetup", "FleetNumber", readRowNo));
		logger.setLoggerInfo("***Vehicle Document Screen-Enter Fleet Number ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Enter Fleet Number");

		fleetpage.enterFleetyear(
				excelutilRWC.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("***Vehicle Document Screen-Enter Fleet Year ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Enter Fleet Year");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		vehicledocs.selectHVUT(excelutilRWC.getCellData("VehicleDocuments", "HVUT", readRowNo));
		logger.setLoggerInfo("*** Vehicle Document Screen-Select HVUT ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Select HVUT");

		vehicledocs.selectLeaseContract(excelutilRWC.getCellData("VehicleDocuments", "Lease contract", readRowNo));
		logger.setLoggerInfo("*** Vehicle Document Screen-Select Lease Contract ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Select Lease Contract");

		vehicledocs.selectTitleDocument(excelutilRWC.getCellData("VehicleDocuments", "Title Documents", readRowNo));
		logger.setLoggerInfo("*** Vehicle Document Screen-Select Title Document ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Select Title Document");

		vehicledocs.selectPlateReturn(excelutilRWC.getCellData("VehicleDocuments", "Plate Returned", readRowNo));
		logger.setLoggerInfo("*** Vehicle Document Screen-Select Plate Return ***");
		screenshotUtil.captureScreenshot(className, "Vehicle Document Screen-Select Plate Return");

		ParentWindow = eleutil.GetParentWindow();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		eleutil.waitForTwoWindow(2);
		childWindow = eleutil.SwitchtoFirstChildWindow();
		eleutil.sleepTime(4000); // to wait for switch to 2nd window
	/*	eleutil.saveAsFile();
		fileLocation = System.getProperty("user.dir") + "\\" + config.readDownloadFolder() + className + "\\";
		logger.setLoggerInfo("fileLocation" + fileLocation);
		DesiredPath = eleutil.checkFileExistence(fileLocation, "Vehicle Documents", "pdf");
		eleutil.sleepTime(4000); // to wait for the PDF Load completely
		eleutil.uploadFile(DesiredPath);
		eleutil.sleepTime(4000); // to display the file upload is completed
*/
		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("user will navigate to Payment page for 2nd installment and validate message {string} {string}")
	public void secondInstallmentPayment(String expSucces, String expSucces2) throws Exception {
	try {
		if (installmentPlanCheckStatus.equalsIgnoreCase("true")) {
			logger.setLoggerInfo("Installment Payment" + installmentPlanCheckStatus);
			CommonStep.scenario.log(
					"Go to Payment - Installment Payment & serach above record & complete 2nd Installment payment");

			dashboardpage.clickEnterpriseLink();
			commonobjects.waitForSpinner();

			dashboardpage.clickIRPLink();
			commonobjects.waitForSpinner();

			dashboardpage.clickInstallmentPayment();
			logger.setLoggerInfo("*** Installment Payment Screen-Click on Installment Payment ***");
			screenshotUtil.captureScreenshot(className, "Installment Payment Screen-Click on Installment Payment");

			commonobjects.waitForSpinner();

			financepage.clickandenterAccountNo(excelutilRWC.getCellData("PreSetup", "AccountNumber", readRowNo));
			logger.setLoggerInfo("*** Installment Payment Screen-Enter Account Number ***");
			screenshotUtil.captureScreenshot(className, "Installment Payment Screen-Enter Account Number");

			financepage.clickandenterfleet(excelutilRWC.getCellData("PreSetup", "FleetNumber", readRowNo));
			logger.setLoggerInfo("*** Installment Payment Screen-Enter Fleet Number ***");
			screenshotUtil.captureScreenshot(className, "Installment Payment Screen-Enter Fleet Number");

			financepage.clickandenterfleetyear(
					excelutilRWC.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
			logger.setLoggerInfo("*** Installment Payment Screen-Enter Fleet Year ***");
			screenshotUtil.captureScreenshot(className, "Installment Payment Screen-Enter Fleet Year");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			financepage.clickgrid();

			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchPostPaymentAccountNoLbl(), writeRowNo, financepage.fetchPostPaymentAccountNo());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchFleetNoLbl(), writeRowNo, financepage.fetchFleetNo());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchLegalNameLbl(), writeRowNo, financepage.fetchLegalName());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchFleetMonthYearLbl(), writeRowNo, financepage.fetchFleetMonthYear());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementNoLbl(), writeRowNo, financepage.fetchSupplementNo());

			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchDBANameLbl(), writeRowNo, financepage.fetchDBAName());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchFleetTypeLbl(), writeRowNo, financepage.fetchFleetType());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementDescLbl(), writeRowNo, financepage.fetchSupplementDesc());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchUSDOTLbl(), writeRowNo, financepage.fetchUSDOT());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementEffectiveDateLbl(), writeRowNo,
					financepage.fetchSupplementEffectiveDate());

			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementStatusLbl(), writeRowNo, financepage.fetchSupplementStatus());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInvoiceDateLbl(), writeRowNo, financepage.fetchInvoiceDate());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInvoicenoLbl(), writeRowNo, financepage.fetchInvoiceNo());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchEnterpriseSystemCreditLbl(), writeRowNo,
					financepage.fetchEnterpriseSystemCredit());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInstallmentReceiptDateLbl(), writeRowNo,
					financepage.fetchInstallmentReceiptDate());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInstallmentPlanNumberlbl(), writeRowNo, financepage.fetchInstallmentPlanNumber());

			ArrayList<String> TableFeeType = billingtab.fetchTableFeeType();
			ArrayList<String> TableFeeAmount = billingtab.fetchTableFeeAmount();

			for (int i = 0; i < TableFeeType.size(); i++) {
				excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
						billingtab.fetchBillingGridFeeType() + i, writeRowNo, TableFeeType.get(i));
				excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
						billingtab.fetchBillingGridFeeAmount() + i, writeRowNo, TableFeeAmount.get(i));
			}

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchMCECustomerIdLbl(), writeRowNo, pay.fetchMCECustomerId());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen", pay.fetchLegalNameLbl(),
					writeRowNo, pay.fetchLegalName());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					financepage.fetchDBANameLbl(), writeRowNo, financepage.fetchDBAName());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchEnterpriseSystemCreditLbl(), writeRowNo, pay.fetchEnterpriseSystemCredit());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchIRPSystemCreditLbl(), writeRowNo, pay.fetchIRPSystemCredit());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchIftaSystemCreditLbl(), writeRowNo, pay.fetchIftaSystemCredit());
			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchOpaSystemCreditLbl(), writeRowNo, pay.fetchOpaSystemCredit());

			excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchTotalAmountDueLbl(), writeRowNo, pay.fetchTotalAmountDue());

			ArrayList<String> tableHeaderValues1 = pay.fetchTableHeaders();
			ArrayList<String> tableInvoice = pay.fetchTableInvoiceDetails();
			for (int i = 0; i < tableInvoice.size(); i++) {
				excelutilWriteRWC.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
						tableHeaderValues1.get(i), writeRowNo, tableInvoice.get(i));
			}

			String paymentExcelCount = excelutilRWC.getCellData("Installment Payment", "payment Count", readRowNo);
			for (int i = 0; i < Integer.valueOf(paymentExcelCount); i++) {
				logger.setLoggerInfo("Installment Payment- no.of rows present is:" + pay.fetchTableRowsize());
				if (pay.fetchTableRowsize() < Integer.valueOf(paymentExcelCount)) {
					paymenttab.clickPaymenToAdd();
				}
				String PaymentType = excelutilRWC.getCellData("Payment", "PaymentType" + i, readRowNo);
				String PaymentNumberValue = excelutilRWC.getCellData("Payment", "PaymentChequeNo", readRowNo);
				pay.selectPaymentType(i, PaymentType);
				logger.setLoggerInfo("***Installment Payment-Payment Type***");
				screenshotUtil.captureScreenshot(className, "Installment Payment-Payment Type" + i);

				pay.enterpaymentNoBasedonType(i, PaymentNumberValue);
				logger.setLoggerInfo("***Installment Payment-Payment Number based on Payment Type***");
				screenshotUtil.captureScreenshot(className,
						"Installment Payment-Payment Number based on  Payment Type" + i);

				String totalAmount = pay.fetchTotalAmount();
				logger.setLoggerInfo("Installment Payment-totalAmount is " + totalAmount);
				String cashAmount = String.format("%.2f",
						(Double.valueOf(totalAmount) / Integer.valueOf(paymentExcelCount)));
				if (i == (Integer.valueOf(paymentExcelCount) - 1)) {
					String RemainingAmount = pay.fetchRemainingBalance();
					logger.setLoggerInfo("Installment Payment-Remaining balance is:" + RemainingAmount);
					pay.enterPaymentAmountBasedonType(i, RemainingAmount);
				} else {
					pay.enterPaymentAmountBasedonType(i, cashAmount);
				}

				logger.setLoggerInfo("***Installment Payment-Payment Amount based on Payment Type***");
				screenshotUtil.captureScreenshot(className,
						"Installment Payment-Payment Amount based on  Payment Type" + i);
			}

			pay.selectPaymentReceipt(excelutilRWC.getCellData("Payment", "Payment receipt", readRowNo));
			logger.setLoggerInfo("***Installment Payment-Enter Payment type and amount***");

			ParentWindow = eleutil.GetParentWindow();
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			String actualmessage = commonobjects.fetchErrorMessage(expSucces);
			try {
				Assert.assertEquals(actualmessage, expSucces);
			} catch (Throwable e) {
				error.addError(e);
			}

			CommonStep.scenario.log("Message in Installment Payment Screen " + expSucces);

			logger.setLoggerInfo("Message in Installment Payment Screen " + commonobjects.fetchErrorMessage(expSucces));
			screenshotUtil.captureScreenshot(className, "Message in Installment Payment Screen 1");

			String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
			try {
				Assert.assertEquals(actualmessage1, expSucces2);
			} catch (Throwable e) {
				error.addError(e);
			}
			CommonStep.scenario.log("Message in Installment Payment Screen " + expSucces2);
			logger.setLoggerInfo("Message in Installment Payment Screen " + commonobjects.fetchErrorMessage(expSucces2));
			screenshotUtil.captureScreenshot(className, "Message in Installment Payment Screen 2");

			eleutil.waitForTwoWindow(2);
			childWindow = eleutil.SwitchtoFirstChildWindow();
			/*	eleutil.sleepTime(4000); // to wait for swith to 2nd window
			eleutil.saveAsFile();
			fileLocation = System.getProperty("user.dir") + "\\" + config.readDownloadFolder() + className + "\\";
			logger.setLoggerInfo("fileLocation" + fileLocation);
			DesiredPath = eleutil.checkFileExistence(fileLocation, "InstallmentPayment", "pdf");
			eleutil.sleepTime(4000); // to wait for the PDF Load completely
			eleutil.uploadFile(DesiredPath);
			eleutil.sleepTime(4000); // to display the file upload is completed
*/
			eleutil.closeSpecificWindow(childWindow);
			eleutil.SwitchSpecificWindow(ParentWindow);
		}
	}catch(Exception e) {
		logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
		logger.closeTheHandler();
		throw new Exception(e);
	}
	}

	@Then("User will navigate to all the inquiries and report with respect to the flow for validation.")
	public void inquiriesAndReportsValidation() throws Exception {
		try {
		String childWindow = null;
		Boolean flag = false;
		String expiryYearExcel = null;
		ParentWindow = eleutil.GetParentWindow();

		dashboardpage.clickServiceLink();
		commonobjects.waitForSpinner();
		dashboardpage.clickIRPLink();
		commonobjects.waitForSpinner();
		dashboardpage.clickFleetEnquiry();
		logger.setLoggerInfo("FleetEnquiry-Click on Fleet Enquiry");

		childWindow = eleutil.SwitchtoFirstChildWindow();

		fleetpage.enterAccountNo(excelutilRWC.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("FleetEnquiry-Enter Account Number");

		fleetpage.enterFleetyear(
				excelutilRWC.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("FleetEnquiry-Enter Account Number");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "FleetEnquiry-Click on proceed");

		flag = enquiry.fleetenquiryvaluevalidation(excelutilRWC.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo));

		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);

		try {
			Assert.assertEquals(flag, true);
		} catch (Throwable e) {
			error.addError(e);
		}
		dashboardpage.clickSupplementEnquiry();
		logger.setLoggerInfo("SupplementEnquiry-Click on Supplement Enquiry");

		childWindow = eleutil.SwitchtoFirstChildWindow();

		enquiry.enterSupplementEnquiryAccountNo(excelutilRWC.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("SupplementEnquiry-Enter Account Number");

		enquiry.enterSupplementEnquiryExpYear(
				excelutilRWC.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("SupplementEnquiry-Enter Account Number");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "SupplementEnquiry-Click on proceed");

		flag = enquiry
				.supplementEnquiryvaluevalidation(excelutilRWC.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo));

		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);

		try {
			Assert.assertEquals(flag, true);
		} catch (Throwable e) {
			error.addError(e);
		}

		expiryYearExcel = excelutilRWC.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo);

		dashboardpage.clickVehicleEnquiry();
		logger.setLoggerInfo("Vehicle Enquiry-Click on Vehicle Enquiry");

		childWindow = eleutil.SwitchtoFirstChildWindow();

		fleetpage.enterAccountNo(excelutilRWC.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("Vehicle Enquiry-Enter Account Number");

		enquiry.enterVehicleEnquiryExpYear(
				excelutilRWC.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("Vehicle-Enter Expiry Year");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "Vehicle Enquiry-Click on proceed");

		for (int i = 1; i <= enquiry.vehicleEnquiryTableRowCount(); i++) {
			String ActualVIN = enquiry.fetchVehicleEnquiryVIN(String.valueOf(i), expiryYearExcel);
			String expectedVIN = excelutilRWC.getCellData("VehicleAddTab", "VIN" + String.valueOf(i), readRowNo);
			if (ActualVIN != null) {
				try {
					Assert.assertEquals(ActualVIN, expectedVIN);
				} catch (Throwable e) {
					error.addError(e);
				}
			}
		}

		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);

		expiryYearExcel = excelutilRWC.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo);

		dashboardpage.clickVehicleSupplementEnquiry();
		logger.setLoggerInfo("Vehicle Supplement Enquiry-Click on Vehicle Enquiry");

		childWindow = eleutil.SwitchtoFirstChildWindow();

		fleetpage.enterAccountNo(excelutilRWC.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("Vehicle Supplement Enquiry-Enter Account Number");

		enquiry.enterVehicleSupplementEnquiryExpYear(
				excelutilRWC.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("Vehicle-Enter Expiry Year");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "Vehicle Enquiry-Click on proceed");


		for (int i = 1; i <= enquiry.vehicleSupplementEnquiryTableRowCount(); i++) {
			String ActualVIN = enquiry.FetchVehicleSupplementEnquiryVIN(String.valueOf(i), expiryYearExcel);
			String expectedVIN = excelutilRWC.getCellData("VehicleAddTab", "VIN" + String.valueOf(i), readRowNo);
			if (ActualVIN != null) {
				try {
					Assert.assertEquals(ActualVIN, expectedVIN);
				} catch (Throwable e) {
					error.addError(e);
				}
			}
		}
		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);
		logger.setLoggerInfo("******************************************************************TEst Execution is Passed******************************************************************************");
		logger.closeTheHandler();
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}
	@AfterStep(value="@RWC002")
	public void as(Scenario scenario) throws Exception {
		scenario.attach(new GenericFunctions().getByteScreenshot(), "image/png", "anyname");
	}

	@After(value="@RWC002")
	public void tearDown(Scenario scenario) throws Exception {
		String exeTime = new SimpleDateFormat("ddMMYYYYHH").format(new Date());
		try {
			if (scenario.isFailed()) {
				String screenshotName = scenario.getName().replaceAll(" ", "_");
				byte[] sourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(sourcePath, "image/png", screenshotName);

				TakesScreenshot ts = (TakesScreenshot) getDriver();
				File source = ts.getScreenshotAs(OutputType.FILE);
				String fileLocation = System.getProperty("user.dir") + "\\" + "test-output\\";

				String recentCreatedFile = ElementUtil.getfolder(fileLocation);
				File f = new File(recentCreatedFile);

				if (f.exists()) {
					FileUtils.copyFile(source,
							new File(recentCreatedFile + "\\" + "Screenshot_Failed", screenshotName + ".png"));
				} else {
					f.mkdir();
					FileUtils.copyFile(source, new File(fileLocation + "\\" + "Screenshot_Failed" + "\\" + exeTime,
							screenshotName + ".png"));
				}
			}
		} catch (Exception e) {

		}
		finally {
			getDriver().quit();
		}
	}

}

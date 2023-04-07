package com.celtic.automation.cmcs.parallel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
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
import com.celtic.automation.cmcs.pages.InventoryPage;
import com.celtic.automation.cmcs.pages.LoginPage;
import com.celtic.automation.cmcs.pages.Payment;
import com.celtic.automation.cmcs.pages.PaymentTab;
import com.celtic.automation.cmcs.pages.VehicleAdd;
import com.celtic.automation.cmcs.pages.VehicleAmend;
import com.celtic.automation.cmcs.pages.VehicleDelete;
import com.celtic.automation.cmcs.pages.VehicleList;
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
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class RWC001 extends DriverFactory {

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
	private VehicleDelete vehicleDelete;
	private BillingTab billingtab;
	private Payment pay;
	private PaymentTab paymenttab;
	private InventoryPage inventorypage;
	private VehicleAdd vehicleadd;
	private Financepage financepage;
	private VehicleList vehiclelist;
	private Enquiry enquiry;
	private ScreenshotUtility screenshotUtil;
	private ConfigReader config;
	private ElementUtil eleutil;
	private Loggers logger;
	private ExcelUtil excelutil = null;
	private ExcelUtil excelutilWrite = null;
	private ErrorCollector error = new ErrorCollector();
	private int readRowNo = 1;
	private int writeRowNo;
	private String cancelAmendedVehicle = null;
	private String ParentWindow = null;
	private String childWindow = null;
	private String fileLocation = null;
	private String DesiredPath = null;
	private String cancelDeletedVehicle = null;
	private String installmentPlanCheckStatus = null;
	private String[] fullClassName = this.getClass().getName().split("[.]");
	private String className = this.getClass().getName().split("[.]")[fullClassName.length - 1];
	
	public WebDriver driver;
	public String browser;
	Properties prop;

	@Before(value="@RWC001")
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

	

	@Given("User login as a Internal user")
	public void internalUserLogin() throws Exception {
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
		vehicleDelete = new VehicleDelete(getDriver(),logger.loggingInstance());
		billingtab = new BillingTab(getDriver(),logger.loggingInstance());
		pay = new Payment(getDriver(),logger.loggingInstance());
		paymenttab = new PaymentTab(getDriver(),logger.loggingInstance());
		inventorypage = new InventoryPage(getDriver(),logger.loggingInstance());
		vehicleadd = new VehicleAdd(getDriver(),logger.loggingInstance());
		financepage = new Financepage(getDriver(),logger.loggingInstance());
		vehiclelist = new VehicleList(getDriver(),logger.loggingInstance());
		enquiry = new Enquiry(getDriver(),logger.loggingInstance());
		
		try {
		logger.setLoggerInfo(" Test Execution is about to begin for "+className+" ");
		config.initprop();

		excelutil = new ExcelUtil(config.readRwcExcel(),logger.loggingInstance());
		excelutilWrite = new ExcelUtil(config.writeRwcExcel(),logger.loggingInstance());
		writeRowNo = excelutilWrite.getEmptyRowNumber(config.writeRwcExcel(),"Account");
		
		getDriver().get(config.readLoginURL());
		CommonStep.scenario.log("Launch the application using URL and login with valid credentials");
		logger.setLoggerInfo(className+"_"+"URL is Loading");
		screenshotUtil.captureScreenshot(className,"ApplicationLogin");
		
		loginpage.enterUsername(config.readLoginInternalUser());
		logger.setLoggerInfo(className+"_"+config.readLoginInternalUser()+"  has been entered sucssesfully ");
		screenshotUtil.captureScreenshot(className, "Username");
		
		loginpage.enterPassword(config.readPswrd());
		logger.setLoggerInfo(className+"_"+config.readPswrd()+"  has been entered sucssesfully ");
		screenshotUtil.captureScreenshot(className, "Password");
		
		loginpage.clickLoginBtn();
		logger.setLoggerInfo(className+"*Click Login *");
		screenshotUtil.captureScreenshot(className, "Login");		
	}
		catch(Exception e) {
		logger.setLoggerInfo(className+ExceptionUtils.getStackTrace(e));
		logger.closeTheHandler();
		throw new Exception(e);
	}
	}

	@When("User will navigate to the IRPLink")
	public void irpLink() throws Exception {
		try {
		CommonStep.scenario.log("Expand the Services header on the left column of the screen and select IRP");
		CommonStep.scenario.log("Click on Renew fleet from Fleet card menu.");

		dashboardpage.clickIRPLink();
		logger.setLoggerInfo(className+"* Click IRP *");
		screenshotUtil.captureScreenshot(className, "IRP");

		dashboardpage.clickRenewFleetLink();
		logger.setLoggerInfo(className+"* Click RenewFleet *");
		screenshotUtil.captureScreenshot(className, "RenewFleet");
		}
		catch(Exception e) {
			logger.setLoggerInfo(className+ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will provide all the Account Number Details to start with IRP Transaction")
	public void accountNumberDetails() throws IOException, Exception {
		try {
		CommonStep.scenario.log("Enter valid search data and click to proceed");

		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo(className+"* Enter Account Number *");
		screenshotUtil.captureScreenshot(className, "Entering AccountNumber");

		fleetpage.enterFleetNo(excelutil.getCellData("PreSetup", "FleetNumber", readRowNo));
		logger.setLoggerInfo("* Enter FleetNo *");
		screenshotUtil.captureScreenshot(className, "Entering FleetNumber");

		fleetpage.enterFleetyear(excelutil.getCellData("PreSetup", "Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("* Click FleetYear *");
		screenshotUtil.captureScreenshot(className, "Entering FleetYear");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
			
		}
		catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}

	}
	

	@Then("User will navigate to account section and fill the data")
	public void accountSectionNavigation() throws Exception, Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Customer Details")) {
		CommonStep.scenario.log("Enter valid all detail in account page with comments and click on proceed button");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		writeRowNo = excelutilWrite.getEmptyRowNumber(config.writeRwcExcel(), "Account");

		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchMCECustomernoLbl(),
				writeRowNo, accounttabpage.fetchMCECustomerNo());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchRegistrationTypeLbl(),
				writeRowNo, accounttabpage.fetchRegistrationType());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountCarrierTypeLbl(),
				writeRowNo, accounttabpage.fetchAccountCarrierType());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchIFTAAccountNbrlbl(),
				writeRowNo, accounttabpage.fetchIFTAAccountNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountCustomerStatusLbl(),
				writeRowNo, accounttabpage.fetchAccountCustomerStatus());

		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountStreet0Lbl(), writeRowNo,
				accounttabpage.fetchAccountStreet0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountZip0lbl(), writeRowNo,
				accounttabpage.fetchAccountZip0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountJur0lbl(), writeRowNo,
				accounttabpage.fetchAccountJur0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountCity0lbl(), writeRowNo,
				accounttabpage.fetchAccountCity0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountCounty0Lbl(), writeRowNo,
				accounttabpage.fetchAccountCounty0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab1() + accounttabpage.fetchAccountCountry0Lbl(), writeRowNo,
				accounttabpage.fetchAccountCountry0());

		accounttabpage.clickMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountStreet1lbl(), writeRowNo,
				accounttabpage.fetchAccountStreet1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountZip1Lbl(), writeRowNo,
				accounttabpage.fetchAccountZip1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountJur1lbl(), writeRowNo,
				accounttabpage.fetchAccountJur1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountCity1Lbl(), writeRowNo,
				accounttabpage.fetchAccountCity1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountCounty1Lbl(), writeRowNo,
				accounttabpage.fetchAccountCounty1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountCountry1Lbl(), writeRowNo,
				accounttabpage.fetchAccountCountry1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account",
				accounttabpage.fetchAccountsTab2() + accounttabpage.fetchAccountAttentionToLbl(), writeRowNo,
				accounttabpage.fetchAccountAttentionTo());

		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountUSDOTNoLbl(),
				writeRowNo, accounttabpage.fetchAccountUSDOTNo());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountTPIDLbl(), writeRowNo,
				accounttabpage.fetchAccountTPID());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountContactNameLbl(),
				writeRowNo, accounttabpage.fetchAccountContactName());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountEmailLbl(), writeRowNo,
				accounttabpage.fetchAccountEmail());

		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountPrimaryPhonelbl(),
				writeRowNo, accounttabpage.fetchAccountPrimaryPhone());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountAlternatePhoneLbl(),
				writeRowNo, accounttabpage.fetchAccountAlternatePhone());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountFaxNoLbl(), writeRowNo,
				accounttabpage.fetchAccountFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountEmailNotificationLbl(),
				writeRowNo, accounttabpage.fetchAccountEmailNotification());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Account", accounttabpage.fetchAccountFaxNotificationLbl(),
				writeRowNo, accounttabpage.fetchAccountFaxNotification());

		accounttabpage.checkEmailNotification();
		logger.setLoggerInfo("* Check Email Notification *");
		screenshotUtil.captureScreenshot(className, "Check EmailNotification");

		commonobjects.provideComments(excelutil.getCellData("AccountTab", "Comments", readRowNo));
		logger.setLoggerInfo(className+"* Enter Comments *");
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
		}
		catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to Fleet section and fill the data and validate message  {string}")
	public void fleetSectionNavigation(String expSucces) throws Exception {
	try {
		if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Fleet Details")) {
		String actualtext = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualtext, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Fleet Screen" + expSucces);
		CommonStep.scenario.log("Enter update all the mandatory and valid details in fleet page. Also update Fleet Expiration Date & Fleet Type & Commodity Class and click on proceed button after entering comments");

		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchRegistrationTypeLbl(), writeRowNo,
				fleettabpage.fetchRegistrationType());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchFltStatusLbl(), writeRowNo,
				fleettabpage.fetchFltStatus());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchCarrierTypeLbl(), writeRowNo,
				fleettabpage.fetchCarrierType());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fetchDBANameLbl(), writeRowNo,
				fleettabpage.fetchDBAName());

		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetStreet0Lbl(), writeRowNo,
				fleettabpage.fleetStreet0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetZip0Lbl(), writeRowNo,
				fleettabpage.fleetZip0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetJur0Lbl(), writeRowNo,
				fleettabpage.fleetJur0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetCity0Lbl(), writeRowNo,
				fleettabpage.fleetCity0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetCounty0Lbl(), writeRowNo,
				fleettabpage.fleetCounty0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetCountry0Lbl(), writeRowNo,
				fleettabpage.fleetCountry0());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabBusinessAddress() + fleettabpage.fleetNonDeliverable0Lbl(), writeRowNo,
				fleettabpage.fleetNonDeliverable0());

		fleettabpage.clickMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetStreet1Lbl(), writeRowNo,
				fleettabpage.fleetStreet1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetZip1Lbl(), writeRowNo,
				fleettabpage.fleetZip1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetJur1Lbl(), writeRowNo,
				fleettabpage.fleetJur1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetCity1Lbl(), writeRowNo,
				fleettabpage.fleetCity1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetCounty1Lbl(), writeRowNo,
				fleettabpage.fleetCounty1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetCountry1Lbl(), writeRowNo,
				fleettabpage.fleetCountry1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetNonDeliverable1Lbl(), writeRowNo,
				fleettabpage.fleetNonDeliverable1());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabMailingAddress() + fleettabpage.fleetAttentionToLbl(), writeRowNo,
				fleettabpage.fleetAttentionTO());

		fleettabpage.navigateToServiceProvider();
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceProviderLbl(), writeRowNo,
				fleettabpage.fleetServiceProvider());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceLegalNameLbl(),
				writeRowNo, fleettabpage.fleetServiceLegalName());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceDBANameLbl(), writeRowNo,
				fleettabpage.fleetServiceDBAName());

		if (fleettabpage.fleetServicePowerOfAttroneyLblpresence() == true) {
			excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
					fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServicePowerOfAttroneyLbl(),
					writeRowNo, fleettabpage.fleetServicePowerOfAttroney());
		}
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress()
						+ fleettabpage.fleetServicePowerOfAttroneyEffDateLbl(),
				writeRowNo, fleettabpage.fleetServicePowerOfAttroneyEffDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress()
						+ fleettabpage.fleetServicePowerOfAttroneyExpDateLbl(),
				writeRowNo, fleettabpage.fleetServicePowerOfAttroneyExpDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceEmailIdLbl(), writeRowNo,
				fleettabpage.fleetServiceEmailId());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServicePhoneNoLbl(), writeRowNo,
				fleettabpage.fleetServicePhoneNo());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceFaxNoLbl(), writeRowNo,
				fleettabpage.fleetServiceFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceStreetLbl(), writeRowNo,
				fleettabpage.fleetServiceStreet());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceCityLbl(), writeRowNo,
				fleettabpage.fleetServiceCity());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceJurLbl(), writeRowNo,
				fleettabpage.fleetServiceJur());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceZipCodeLbl(), writeRowNo,
				fleettabpage.fleetServiceZipCode());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabServiceProviderAddress() + fleettabpage.fleetServiceCountryLbl(), writeRowNo,
				fleettabpage.fleetServiceCountry());

		fleettabpage.clickOnTimeMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingStreetLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingStreet());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingZipCodeLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingZipCode());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingJurLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingJur());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingCityLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingCity());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingCountyLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingCounty());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingCountryLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingCountry());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fetchFleetTabOneTimeMailingAddress() + fleettabpage.fleetServiceMailingAttentionToLbl(),
				writeRowNo, fleettabpage.fleetServiceMailingAttentionTo());
		eleutil.scrollToBottom();
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsContactNameLbl(),
				writeRowNo, fleettabpage.fleetDetailsContactName());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsEmailIdLbl(), writeRowNo,
				fleettabpage.fleetDetailsEmailId());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsPrimaryCellNbrLbl(),
				writeRowNo, fleettabpage.fleetDetailsPrimaryCellNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsAlternateCellNbrLbl(),
				writeRowNo, fleettabpage.fleetDetailsAlternateCellNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFaxNoLbl(), writeRowNo,
				fleettabpage.fleetDetailsFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsTpIdLbl(), writeRowNo,
				fleettabpage.fleetDetailsTPID());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsUsdotNbrLbl(), writeRowNo,
				fleettabpage.fleetDetailsUsdotNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsChangeVehUsdotTinLbl(),
				writeRowNo, fleettabpage.fleetDetailsChangeVehUsdotTin());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFltTypeLbl(), writeRowNo,
				fleettabpage.fleetDetailsFltType());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsCommodityClassLbl(),
				writeRowNo, fleettabpage.fleetDetailsCommodityClass());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFltEffDateLbl(),
				writeRowNo, fleettabpage.fleetDetailsFltEffDatedtPicker());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFltExpDateLbl(),
				writeRowNo, fleettabpage.fleetDetailsFltExpDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsChangeAddrOnUsdotLbl(),
				writeRowNo, fleettabpage.fleetDetailsChangeAddrOnUsdot());

		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsFirstOperatedDateLbl(),
				writeRowNo, fleettabpage.fleetDetailsFirstOperatedDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsWyomingIndicatorLbl(),
				writeRowNo, fleettabpage.fleetDetailsWyomingIndicator());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsIFTADistanceLbl(),
				writeRowNo, fleettabpage.fleetDetailsIFTADistance());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsMobileNotificationLbl(),
				writeRowNo, fleettabpage.fleetDetailsMobileNotification());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsIRPRequirementsLbl(),
				writeRowNo, fleettabpage.fleetDetailsIRPRequirements());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet",
				fleettabpage.fleetDetailsStatementOfUnderstandingLbl(), writeRowNo,
				fleettabpage.fleetDetailsStatementOfUnderstanding());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsInstallmentAgreementLbl(),
				writeRowNo, fleettabpage.fleetDetailsInstallmentAgreement());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsPowerOfAttorneyLbl(),
				writeRowNo, fleettabpage.fleetDetailsPowerOfAttorney());
		if (fleettabpage.fleetHVUTFormLblpresence() == true) {
			excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsHVUTFormLbl(),
					writeRowNo, fleettabpage.fleetDetailsHVUTForm());
		}
		if (fleettabpage.fleetPropertyTaxLblpresence() == true) {
			excelutilWrite.setCellData(config.writeRwcExcel(), "Fleet", fleettabpage.fleetDetailsPropertyTaxLbl(),
					writeRowNo, fleettabpage.fleetDetailsPropertyTax());
		}
		eleutil.scrollToTop();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		logger.setLoggerInfo(className+"Message in Fleet Screen " + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Fleet Screen 1");

		fleettabpage.navigateToServiceProvider();
		logger.setLoggerInfo("* navigateToServiceProvider *");
		screenshotUtil.captureScreenshot(className, "Navigate to Service provider");

		fleettabpage.clickPowerOfAttroney();
		logger.setLoggerInfo("* Click PowerofAttroney *");
		screenshotUtil.captureScreenshot(className, "Check Power of Attroney");

		fleettabpage.enterEmailID(excelutil.getCellData("FleetTab", "Email iD", readRowNo));
		logger.setLoggerInfo("* Entering the Emailid *");
		screenshotUtil.captureScreenshot(className, "Entering the Emailid");

		/*
		 * RWC- Expiration date will not be blank in app- if value is present in
		 * excel  then only we have to pass in the application else we can skip that
		 */
		if ((excelutil.getCellData("FleetTab", "Expiration Date", readRowNo) != null)
				&& (excelutil.getCellData("FleetTab", "Expiration Date", readRowNo) != "")) {
			fleettabpage.selectExpirationDate(excelutil.getCellData("FleetTab", "Expiration Date", readRowNo));
			logger.setLoggerInfo("* Selecting the Expiration Date *");
			screenshotUtil.captureScreenshot(className, "Selecting the Expiration Date");
		}

		fleettabpage.selectIRPRequirementForm(excelutil.getCellData("FleetTab", "IRP Requirements Form", readRowNo));
		logger.setLoggerInfo("* Selecting the IRPRequirementForm *");
		screenshotUtil.captureScreenshot(className, "Selecting the IRPRequirementForm");

		fleettabpage.selectStatementofUnderstanding(
				excelutil.getCellData("FleetTab", "Statement of Understanding", readRowNo));
		logger.setLoggerInfo("* Selecting StatementofUnderstanding *");
		screenshotUtil.captureScreenshot(className, "Selecting StatementofUnderstanding");

		fleettabpage.selectInstallmentAgreement(excelutil.getCellData("FleetTab", "Installment Agreement", readRowNo));
		logger.setLoggerInfo("* Selecting InstallmentAgreement *");
		screenshotUtil.captureScreenshot(className, "Selecting InstallmentAgreement");

		fleettabpage.selectPowerOfAttroney(excelutil.getCellData("FleetTab", "Power of Attorney", readRowNo));
		logger.setLoggerInfo("* Selecting PowerOfAttroney *");
		screenshotUtil.captureScreenshot(className, "Selecting PowerOfAttroney");

		fleettabpage.selectHVUTForm(excelutil.getCellData("FleetTab", "HVUTForm 2290", readRowNo));
		logger.setLoggerInfo("* Selecting HVUTForm *");
		screenshotUtil.captureScreenshot(className, "Selecting HVUTForm");

		fleettabpage.selectPropertyTax(excelutil.getCellData("FleetTab", "Property Tax", readRowNo));
		logger.setLoggerInfo("* Selecting PropertyTax *");
		screenshotUtil.captureScreenshot(className, "Selecting PropertyTax");

		commonobjects.provideComments(excelutil.getCellData("FleetTab", "Comments", readRowNo));
		logger.setLoggerInfo("* Enter Comments *");
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
		logger.closeTheHandler();
	}catch(Exception e) {
		logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
		logger.closeTheHandler();
		throw new Exception(e);
	}
	}
	

	@Then("User will navigate to Distance section and fill the data and validate message {string} {string}")
	public void distanceSectionNavigation(String expSucces1, String expSucces2) throws Exception, Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Distance Details")) {
		CommonStep.scenario.log("Select Actual Distance radio button & Enter all the mandatory and valid details in distance page and click on proceed button after entering comments");
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceReportingPeriodFromLbl(),
				writeRowNo, distancetabpage.distanceReportingPeriodFrom());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceReportingPeriodToLbl(),
				writeRowNo, distancetabpage.distanceReportingPeriodTo());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceUsdotNbrLbl(),
				writeRowNo, distancetabpage.distanceUsdotNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceEstimatedDistanceChartLbl(), writeRowNo,
				distancetabpage.distanceEstimatedDistanceChart());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceOverrideContJurLbl(),
				writeRowNo, distancetabpage.distanceOverrideContJur());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceEstimatedDistanceLbl(),
				writeRowNo, distancetabpage.distanceEstimatedDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceActualDistanceLbl(),
				writeRowNo, distancetabpage.distanceActualDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceTotalFleetDistanceLbl(),
				writeRowNo, distancetabpage.distanceTotalFleetDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceFRPMlgQuetionLbl(),
				writeRowNo, distancetabpage.distanceFRPMlgQuetion());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance", distancetabpage.distanceDistanceTypeLbl(),
				writeRowNo, distancetabpage.distanceDistanceType());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Distance",
				distancetabpage.distanceActualDistConfirmationLbl(), writeRowNo,
				distancetabpage.distanceActualDistConfirmation());
		
		ArrayList<String> jurisValues = distancetabpage.fetchTableJuris();
		ArrayList<String> distanceValues = distancetabpage.fetchTableDistanceMiles();
		ArrayList<String> percentValues = distancetabpage.fetchTablePercent();

		for (int i = 0; i < jurisValues.size(); i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(), "DistanceJuris",
					distancetabpage.distanceJurisTableHeaderJuri() + i, writeRowNo, jurisValues.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(), "DistanceJuris",
					distancetabpage.distanceJurisTableHeaderDistance() + i, writeRowNo, distanceValues.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(), "DistanceJuris",
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
		logger.setLoggerInfo(className+"Message in Distance Screen" + commonobjects.fetchErrorMessage(expSucces1));

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

		distancetabpage.enterAllDistanceValue(excelutil.getCellData("DistanceTab", "Distance", readRowNo));
		logger.setLoggerInfo("* Enter MODistanceValue *");
		screenshotUtil.captureScreenshot(className, "Enter MODistanceValue");

		commonobjects.provideComments(excelutil.getCellData("DistanceTab", "Comments", readRowNo));
		logger.setLoggerInfo("* Enter Comments *");
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
			}
			catch(Exception e) {
				logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to Weight group section and fill the data ans validate message {string}")
	public void weightGroupSectionNavigation(String expSucces) throws Exception {
		try{
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Weight Group Selection Details")) {
		CommonStep.scenario.log("Click on Add weight group button Add new Weight Group(s) & enter all the mandatory and valid details in the weight group page and click on proceed button");
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
				excelutilWrite.setCellData(config.writeRwcExcel(), "WeightGroup", headervalues.get(j) + k, writeRowNo,
						RowDatavalues.get(i));
				logger.setLoggerInfo("Weight Group headervalues" + headervalues.get(j));
				logger.setLoggerInfo("Weight Group RowDatavalues" + RowDatavalues.get(i));
			} else if (i > 0 && i < 5) {

				excelutilWrite.setCellData(config.writeRwcExcel(), "WeightGroup", headervalues.get(i) + k, writeRowNo,
						RowDatavalues.get(i));
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
		logger.setLoggerInfo(className+"Message in Weight Group Screen" + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Weight Group Screen 1");

		String weightGroupCountExcel = excelutil.getCellData("WeightGrouptab", "TotalWeightGroups", readRowNo);

		for (int weightcount = 0; weightcount < Integer.valueOf(weightGroupCountExcel); weightcount++) {
			if (weightcount < Integer.valueOf(weightGroupCountExcel)) {
				wgtgroup.clickAddWeightGroup();
				logger.setLoggerInfo("* Click AddweightGroup *");
				screenshotUtil.captureScreenshot(className, "Click AddweightGroup");
			}

			wgtgroupadd.selectWeightGroupType(excelutil.getCellData("WeightGrouptab",
					"WeightGroup Type" + String.valueOf(weightcount), readRowNo));
			logger.setLoggerInfo("* Select WeightGroupType *");
			screenshotUtil.captureScreenshot(className, "Select WeightGroupType");

			wgtgroupadd.enterWeightGroupNo(excelutil.getCellData("WeightGrouptab",
					"Weight Group No." + String.valueOf(weightcount), readRowNo));
			logger.setLoggerInfo("* Enter WeightGroup No *");
			screenshotUtil.captureScreenshot(className, "Enter WeightGroup No");

			wgtgroupadd.selectMaxGrossWeight(excelutil.getCellData("WeightGrouptab",
					"Max Gross Weight" + String.valueOf(weightcount), readRowNo));
			logger.setLoggerInfo("* Select MaxGross Weight *");
			screenshotUtil.captureScreenshot(className, "Select MaxGross Weight");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			CommonStep.scenario.log("Click on proceed button from the verification page");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
		}

		wgtgroup.clickHandImg();

		String juriExcelCount = excelutil.getCellData("WeightJuris", "Juris Count", readRowNo);
		for (int i = 0; i < Integer.valueOf(juriExcelCount); i++) {
			String juriExcel = excelutil.getCellData("WeightJuris", "Juri" + String.valueOf(i), readRowNo);
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
			String juriExcel = excelutil.getCellData("WeightJuris", "Juri" + String.valueOf(i), readRowNo);
			if (weightlist[i].equalsIgnoreCase(juriExcel)) {
				assert true;
			}
		}


		CommonStep.scenario.log("Click Done");
		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
			}
			else {
				logger.setLoggerInfo("Screen is not in Weight Group Selection Details Tab");
			}
			}
		catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to Ammend vehicle and validate the message {string} {string} {string}")
	public void amendVehicleNavigation(String expSucces, String expSucces2, String expSucces3) throws Exception {
	try {
		if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Renewal Vehicle Processing")) {
		CommonStep.scenario.log("Complete Amend vehicle  and edit Ownership Details Safety USDOT and TIN and change Weight Group such that New Plate is requested Also request TVR and enter comments Delete Vehicle(s) such that all Documents are collected and Comments entered");

		excelutilWrite.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchAmendVehicleLbl(),
				writeRowNo, Vehicletabpage.fetchAmendVehicle());
		excelutilWrite.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchAddVehiclesLbl(),
				writeRowNo, Vehicletabpage.fetchAddVehicles());
		excelutilWrite.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchDeleteVehicleLbl(),
				writeRowNo, Vehicletabpage.fetchDeleteVehicle());
		excelutilWrite.setCellData(config.writeRwcExcel(), "VehicleTab", Vehicletabpage.fetchRenewVehicleLbl(),
				writeRowNo, Vehicletabpage.fetchRenewVehicle());

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		int NoofVehiclestoAmend = Integer
				.valueOf(excelutil.getCellData("VehicleTab", "NoofVehiclestoAmend", readRowNo));
		if (NoofVehiclestoAmend > 0) {
			logger.setLoggerInfo(
					"Number of Vehicles to be Amended is greater than zero Hence about to click the Amend Vehicle Radio Button");

			Vehicletabpage.clickAmendVehicleRadioButton();
			logger.setLoggerInfo(className+"* Click AmendVehicle RadioButton *");
			screenshotUtil.captureScreenshot(className, "Click AmendVehicle RadioButton");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

			String actualmessage = commonobjects.fetchErrorMessage(expSucces);
			try {
				Assert.assertEquals(actualmessage, expSucces);
			} catch (Throwable e) {
				error.addError(e);
			}
			CommonStep.scenario.log("Message in Amend Vehicle Screen" + actualmessage);

			logger.setLoggerInfo("Message in Amend Vehicle Screen" + commonobjects.fetchErrorMessage(expSucces));
			screenshotUtil.captureScreenshot(className, "Message in Amend Vehicle Screen 1");

			for (int i = 0; i < NoofVehiclestoAmend; i++) {
				if (NoofVehiclestoAmend > Integer.valueOf(config.readAmendVehicleCondition())) {
					vehicleAmend.selectUnitNoFromSuggestions();
					logger.setLoggerInfo("* Select Unit number *");
					screenshotUtil.captureScreenshot(className, "Select Unit number");
				} else {
					vehicleAmend.enterUnitNo(
							excelutil.getCellData("VehicleAmendTab", "Unit" + String.valueOf(i), readRowNo));
					logger.setLoggerInfo("* Enter Unit number Before Amend  *");
					screenshotUtil.captureScreenshot(className, "Enter Unit number Before Amend ");
				}
				vehicleAmend.clickSearch();
				String vehicleBodyType = vehicleAmend.fetchAmendVehicleBodyType();

				if (vehicleBodyType.equalsIgnoreCase("CG - Converter Gear")
						|| vehicleBodyType.equalsIgnoreCase("MT - Motor Totor")
						|| vehicleBodyType.equalsIgnoreCase("RT - Road Truck")
						|| vehicleBodyType.equalsIgnoreCase("TK - Straight Truck")
						|| vehicleBodyType.equalsIgnoreCase("TR - Tractor")
						|| vehicleBodyType.equalsIgnoreCase("TT - Truck Tractor")
						|| vehicleBodyType.equalsIgnoreCase("WR - Wrecker")) {
					vehicleAmend.selectWeightGroupNo(
							excelutil.getCellData("WeightGrouptab", "VehicleWeightGroupNo", readRowNo));

					logger.setLoggerInfo("* Enter WeightGroupNo *");
					screenshotUtil.captureScreenshot(className, "Enter WeightGroupNo");
					String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
					try {
						Assert.assertEquals(actualmessage1, expSucces2);
					} catch (Throwable e) {
						error.addError(e);
					}
					CommonStep.scenario.log("Message in Amend Vehicle Screen" + actualmessage1);

					logger.setLoggerInfo("Message in Amend Vehicle Screen" + commonobjects.fetchErrorMessage(expSucces2));
					screenshotUtil.captureScreenshot(className, "Message in Amend Vehicle Screen 2");
				}
				vehicleAmend.enterUnladenWeight(excelutil.getCellData("VehicleTab", "unladenWeight", readRowNo));
				logger.setLoggerInfo("* Enter UnladenWeight *");
				screenshotUtil.captureScreenshot(className, "Enter UnladenWeight");

				vehicleAmend.clickTVR();
				logger.setLoggerInfo("* Click TVR *");
				screenshotUtil.captureScreenshot(className, "Click TVR");

				vehicleAmend.selectSafetyChangedd(excelutil.getCellData("VehicleTab", "Safety Change", readRowNo));
				logger.setLoggerInfo("* Select Safety Changedd *");
				screenshotUtil.captureScreenshot(className, "Select Safety Changedd");

				vehicleAmend.selectHVUTForm2290(excelutil.getCellData("VehicleTab", "HVUT - Form", readRowNo));
				logger.setLoggerInfo("* Select HVUTForm2290 *");
				screenshotUtil.captureScreenshot(className, "Select HVUTForm2290");

				vehicleAmend.selectLeaseContract(excelutil.getCellData("VehicleTab", "Lease Contract", readRowNo));
				logger.setLoggerInfo("* Select LeaseContract *");
				screenshotUtil.captureScreenshot(className, "Select LeaseContract");

				vehicleAmend.selectTitleDocument(excelutil.getCellData("VehicleTab", "Title Document", readRowNo));
				logger.setLoggerInfo("* Select TitleDocument *");
				screenshotUtil.captureScreenshot(className, "Select TitleDocument");

				vehicleAmend.selectPlateReturndoc(
						excelutil.getCellData("VehicleTab", "Plate Returned Document", readRowNo));
				logger.setLoggerInfo("* Select PlateReturndoc *");
				screenshotUtil.captureScreenshot(className, "Select PlateReturndoc");

				vehicleAmend.selectAffidavitDoc(excelutil.getCellData("VehicleTab", "Affidavit document", readRowNo));
				logger.setLoggerInfo("* Select AffidavitDoc *");
				screenshotUtil.captureScreenshot(className, "Select AffidavitDoc");

				vehicleAmend.selectPropertyTax(excelutil.getCellData("VehicleTab", "Property Tax", readRowNo));
				logger.setLoggerInfo("* Select PropertyTax *");
				screenshotUtil.captureScreenshot(className, "Select PropertyTax");

				commonobjects.provideComments(excelutil.getCellData("VehicleTab", "AmmendComments", readRowNo));

				logger.setLoggerInfo("* Enter Comments *");
				screenshotUtil.captureScreenshot(className, "Enter Comments in Amend Vehicle Section");

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();

				String actualmessage2 = commonobjects.fetchErrorMessage(expSucces3);
				try {
					Assert.assertEquals(actualmessage2, expSucces3);
				} catch (Throwable e) {
					error.addError(e);
				}
				CommonStep.scenario.log("Message in Amend Vehicle Screen " + actualmessage2);

				logger.setLoggerInfo("Message in Amend Vehicle Screen " + commonobjects.fetchErrorMessage(expSucces3));
				screenshotUtil.captureScreenshot(className, "Message in Amend Vehicle Screen");

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

	@Then("User will Delete vehicle as per the requiremnet and validate the message {string}")
	public void deleteVehicle(String expSucces) throws Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Renewal Vehicle Processing")) {
			CommonStep.scenario.log("Cancel the  Delete Vehicle ");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		int NoofVehiclestoDelete = Integer
				.valueOf(excelutil.getCellData("VehicleTab", "NoOfVehiclesToDelete", readRowNo));
		if (NoofVehiclestoDelete > 0) {
			logger.setLoggerInfo(
					"Number of Vehicles to be deleted is greater than zero Hence about to click the Delete Vehicle Radio Button");
			Vehicletabpage.clickDeleteVehicleRadioButton();
			logger.setLoggerInfo(className+" Click DeleteVehicle ");
			screenshotUtil.captureScreenshot(className, "Click Delete Vehicle");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
			
			/* 
			 * below lines of code for deleting the vehicles from suggestion box 
			 */
			String Vehiclescount = excelutil.getCellData("VehicleTab", "NoOfVehiclesToDelete", readRowNo);
			String PlateStatus = excelutil.getCellData("VehicleTab", "DeletePlateStatus", readRowNo);
			String PlateReturnedDocument = excelutil.getCellData("VehicleTab", "DeletePlateReturnedDocument",
					readRowNo);
			String AffidavitDocument = excelutil.getCellData("VehicleTab", "DeleteAffidavitDocument", readRowNo);
			String Comments = excelutil.getCellData("VehicleTab", "DeleteComments", readRowNo);

			if (NoofVehiclestoDelete > Integer.valueOf(config.readDeleteVehicleCondition())) {
				vehicleDelete.deleteFewVehicles(Vehiclescount, PlateStatus, PlateReturnedDocument, AffidavitDocument,
						Comments);
				commonobjects.clickProceed();
				commonobjects.waitForSpinner();
				commonobjects.clickProceed();
				commonobjects.waitForSpinner();
			} else {

				for (int j = 0; j < NoofVehiclestoDelete; j++) {

					vehicleDelete.enterUnitNo(
							excelutil.getCellData("VehicleDeleteTab", "Unit" + String.valueOf(j), readRowNo));
					logger.setLoggerInfo("* Delete vehicle Enter Unit number *");
					screenshotUtil.captureScreenshot(className, "Delete vehicle Enter Unit number");

					vehicleDelete.clickonSearch();
					vehicleDelete.clickCheckBoxFromGrid();

					vehicleDelete.selectPlateStatus(PlateStatus);
					logger.setLoggerInfo("*DeleteVehicle Select PlateStatus *");
					screenshotUtil.captureScreenshot(className, "DeleteVehicle Select PlateStatus");

					vehicleDelete.selectPlateReturnedDocument(PlateReturnedDocument);
					logger.setLoggerInfo("*DeleteVehicle Select PlateReturndoc *");
					screenshotUtil.captureScreenshot(className, "DeleteVehicle Select PlateReturndoc");

					vehicleDelete.selectAffidavitDocument(AffidavitDocument);
					logger.setLoggerInfo("*DeleteVehicle Select AffidavitDoc *");
					screenshotUtil.captureScreenshot(className, "DeleteVehicle Select AffidavitDoc");

					vehicleDelete.entercomments(Comments);
					logger.setLoggerInfo("* DeleteVehicle Comment *");
					screenshotUtil.captureScreenshot(className, "DeleteVehicle Comment");

					commonobjects.clickProceed();
					commonobjects.waitForSpinner();
					commonobjects.clickProceed();
					commonobjects.waitForSpinner();
				} 
			} 
			String actualmessage = commonobjects.fetchErrorMessage(expSucces);
			try {
				Assert.assertEquals(actualmessage, expSucces);
			} catch (Throwable e) {
				error.addError(e);
			}
			CommonStep.scenario.log("Message in Delete Vehicle Screen" + actualmessage);

			logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
			logger.setLoggerInfo("Message in Delete Vehicle Screen" + commonobjects.fetchErrorMessage(expSucces));
			screenshotUtil.captureScreenshot(className, "Message in Delete Vehicle Screen 1");

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

	@Then("User will navigate to vehicle list to cancel the existing amended vehicle and amend it again {string}")
	public void vehicleListNavigation(String expSucces) throws Exception {
try {
	if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Renewal Vehicle Processing")) {
		int NoofVehiclestoAmend = Integer
				.valueOf(excelutil.getCellData("VehicleTab", "NoofVehiclestoAmend", readRowNo));
		if (NoofVehiclestoAmend > 0) {
			CommonStep.scenario.log("Go to Vehicle List and update Amended  Vehicle details");
			logger.setLoggerInfo(
					"Number of Vehicles to be Amended is greater than zero Hence about to click the Vehicle List");
			Vehicletabpage.clickVehicleList();
			logger.setLoggerInfo(className+"* Click VehicleList *");
			screenshotUtil.captureScreenshot(className, "Click VehicleList");

			cancelAmendedVehicle = vehiclelist.fetchRequiredUnitNumber("AMEND");

			logger.setLoggerInfo("Cancelling the recently Amended Vehicle" + cancelAmendedVehicle);

			if (NoofVehiclestoAmend > Integer.valueOf(config.readAmendVehicleCondition())) {
				
				vehicleadd.enterUnitNumber(cancelAmendedVehicle);
			} else {
				vehicleadd.enterUnitNumber(excelutil.getCellData("VehicleAmendTab", "Unit0", readRowNo));
			}
			logger.setLoggerInfo("* Select Unit No *");
			screenshotUtil.captureScreenshot(className, "Select Unit No");

			vehicleAmend.clickSearch();
			logger.setLoggerInfo("* Search Unit No *");

			vehicleadd.selectFirstHandIcon();

			vehicleadd.selectWeightGroupNumber(
					excelutil.getCellData("WeightGrouptab", "VehicleWeightGroupNo", readRowNo));
			logger.setLoggerInfo("* Select Weight Group *");
			screenshotUtil.captureScreenshot(className, "Select Weight Group");

			vehicleadd.selectColor(excelutil.getCellData("VehicleTab", "VehicleColor", readRowNo));
			logger.setLoggerInfo("* Select Color *");
			screenshotUtil.captureScreenshot(className, "Select Color");

			vehicleAmend.selectSafetyChangedd(excelutil.getCellData("VehicleTab", "Safety Change", readRowNo));
			logger.setLoggerInfo("* Select Safety Changedd *");
			screenshotUtil.captureScreenshot(className, "Select Safety Changedd");

			vehicleAmend.selectHVUTForm2290(excelutil.getCellData("VehicleTab", "HVUT - Form", readRowNo));
			logger.setLoggerInfo("* Select HVUTForm2290 *");
			screenshotUtil.captureScreenshot(className, "Select HVUTForm2290");

			vehicleAmend.selectLeaseContract(excelutil.getCellData("VehicleTab", "Lease Contract", readRowNo));
			logger.setLoggerInfo("* Select LeaseContract *");
			screenshotUtil.captureScreenshot(className, "Select LeaseContract");

			vehicleAmend.selectTitleDocument(excelutil.getCellData("VehicleTab", "Title Document", readRowNo));
			logger.setLoggerInfo("* Select TitleDocument *");
			screenshotUtil.captureScreenshot(className, "Select TitleDocument");

			vehicleAmend
					.selectPlateReturndoc(excelutil.getCellData("VehicleTab", "Plate Returned Document", readRowNo));
			logger.setLoggerInfo("* Select PlateReturndoc *");
			screenshotUtil.captureScreenshot(className, "Select PlateReturndoc");

			vehicleAmend.selectAffidavitDoc(excelutil.getCellData("VehicleTab", "Affidavit document", readRowNo));
			logger.setLoggerInfo("* Select AffidavitDoc *");
			screenshotUtil.captureScreenshot(className, "Select AffidavitDoc");

			vehicleAmend.selectPropertyTax(excelutil.getCellData("VehicleTab", "Property Tax", readRowNo));
			logger.setLoggerInfo("* Select PropertyTax *");
			screenshotUtil.captureScreenshot(className, "Select PropertyTax");
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
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

	@Then("User will navigate to vehicle list to cancel the existing deleted vehicle and delete it again {string}")
	public void cancelExistingVehicle(String expSucces) throws Exception {
		try {
			if(commonobjects.fetchHeaderRow().trim().equalsIgnoreCase("Renewal Vehicle Processing")) {
		int NoofVehiclestoDelete = Integer
				.valueOf(excelutil.getCellData("VehicleTab", "NoOfVehiclesToDelete", readRowNo));
		if (NoofVehiclestoDelete > 0) {
			CommonStep.scenario.log("Go to Vehicle List and update/cancel  Deleted Vehicle details");
			logger.setLoggerInfo(className+
					"Number of Vehicles to be deleted is greater than zero Hence about to click the Vehicle List");

			Vehicletabpage.clickVehicleList();
			logger.setLoggerInfo("* Click VehicleList *");
			screenshotUtil.captureScreenshot(className, "Click VehicleList");

			cancelDeletedVehicle = vehiclelist.fetchRequiredUnitNumber("DELETE");
			if (NoofVehiclestoDelete > Integer.valueOf(config.readDeleteVehicleCondition())) {
				logger.setLoggerInfo("Cancelling the recently Deleted Vehicle" + cancelDeletedVehicle);
				vehicleadd.enterUnitNumber(cancelDeletedVehicle);
			} else {
				vehicleadd.enterUnitNumber(excelutil.getCellData("VehicleDeleteTab", "Unit0", readRowNo));
			}
			logger.setLoggerInfo("* Select Unit No *");
			screenshotUtil.captureScreenshot(className, "Select Unit No");

			vehicleAmend.clickSearch();
			logger.setLoggerInfo("* Search Unit No *");
			screenshotUtil.captureScreenshot(className, "Search Unit No");
			
			vehicleadd.selectFirstHandIcon();
			commonobjects.clickConfirmCancel();
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

			commonobjects.clickBack();
			logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

			Vehicletabpage.clickDeleteVehicleRadioButton();
			logger.setLoggerInfo(" Click DeleteVehicle ");
			screenshotUtil.captureScreenshot(className, "Click Delete Vehicle");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
			/*
			 *  below lines of code for deleting the vehicles from suggestion box
			 */			
			String plateStatus = excelutil.getCellData("VehicleTab", "DeletePlateStatus", readRowNo);
			String plateReturnedDocument = excelutil.getCellData("VehicleTab", "DeletePlateReturnedDocument",readRowNo);
			String affidavitDocument = excelutil.getCellData("VehicleTab", "DeleteAffidavitDocument", readRowNo);
			String Comments = excelutil.getCellData("VehicleTab", "DeleteComments", readRowNo);
		
			logger.setLoggerInfo("Deleting the recently Cancelled Vehicle" + cancelDeletedVehicle);
			
			vehicleDelete.enterUnitNo(cancelDeletedVehicle);
			logger.setLoggerInfo("* Delete vehicle Enter Unit number *");
			screenshotUtil.captureScreenshot(className, "Delete vehicle Enter Unit number");

			vehicleDelete.clickonSearch();
			vehicleDelete.clickCheckBoxFromGrid();

			vehicleDelete.selectPlateStatus(plateStatus);
			logger.setLoggerInfo("*DeleteVehicle Select PlateStatus *");
			screenshotUtil.captureScreenshot(className, "DeleteVehicle Select PlateStatus");

			vehicleDelete.selectPlateReturnedDocument(plateReturnedDocument);
			logger.setLoggerInfo("*DeleteVehicle Select PlateReturndoc *");
			screenshotUtil.captureScreenshot(className, "DeleteVehicle Select PlateReturndoc");

			vehicleDelete.selectAffidavitDocument(affidavitDocument);
			logger.setLoggerInfo("*DeleteVehicle Select AffidavitDoc *");
			screenshotUtil.captureScreenshot(className, "DeleteVehicle Select AffidavitDoc");

			vehicleDelete.entercomments(Comments);
			logger.setLoggerInfo("* DeleteVehicle Comment *");
			screenshotUtil.captureScreenshot(className, "DeleteVehicle Comment");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();

			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
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

	@Then("User will navigate to billing to verify as well to adjust the cost & Waive Fees {string}")
	public void billingNavigation(String expSucces) throws Exception {
try {
		if (eleutil.getTitle().trim().equalsIgnoreCase("Vehicle Renewal Entry - IRP")) {
			logger.setLoggerInfo("Screen is in Vehicle Renewal Entry Screen");
			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
		}
		CommonStep.scenario.log("Click Done from the supplement selection page");

		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchRegisterMonthLbl(), writeRowNo,
				billingtab.fetchRegisterMonth());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchNoOfVehiclesInSuppLbl(),
				writeRowNo, billingtab.fetchNoOfVehiclesinSupp());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchSupplementStatusLbl(), writeRowNo,
				billingtab.fetchSupplementStatus());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchEnterpriseSystemCreditLbl(),
				writeRowNo, billingtab.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchIRPSystemCreditLbl(), writeRowNo,
				billingtab.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchRenewalFeeEffectiveDatelbl(),
				writeRowNo, billingtab.fetchRenewalFeeEffectiveDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchInvoiceDateLbl(), writeRowNo,
				billingtab.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchApplicationReceiptDateLbl(),
				writeRowNo, billingtab.fetchApplicationReceiptDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchPaymentDateLbl(), writeRowNo,
				billingtab.fetchPaymentDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchExchangeRateLbl(), writeRowNo,
				billingtab.fetchExchangeRate());

		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchManualAdjBaseJurLbl(), writeRowNo,
				billingtab.fetchManualAdjBaseJur());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingBatchBillingLbl(),
				writeRowNo, billingtab.fetchBillingBatchBilling());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingTVRLbl(), writeRowNo,
				billingtab.fetchBillingTVR());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingInstallmentPlanLbl(),
				writeRowNo, billingtab.fetchBillingInstallmentPlan());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingIsUseOneTimeMailingLbl(),
				writeRowNo, billingtab.fetchBillingIsUseOneTimeMailing());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchElectronicDeliveryTypelbl(),
				writeRowNo, billingtab.fetchElectronicDeliveryType());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", "Email", writeRowNo,
				billingtab.fetchBillingEmail());
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchInvoiceReportTypelbl(),
				writeRowNo, billingtab.fetchBillingInvoiceReportType());

		ArrayList<String> TableFeeType = billingtab.fetchTableFeeType();
		ArrayList<String> TableFeeAmount = billingtab.fetchTableFeeAmount();

		for (int i = 0; i < TableFeeType.size(); i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(), "BillingGrid", billingtab.fetchBillingGridFeeType() + i,
					writeRowNo, TableFeeType.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(), "BillingGrid",
					billingtab.fetchBillingGridFeeAmount() + i, writeRowNo, TableFeeAmount.get(i));
		}

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		billingtab.clickTVR();
		logger.setLoggerInfo(className+"*Billing-Click TVR *");
		screenshotUtil.captureScreenshot(className, "Billing-Click TVR");
		excelutilWrite.setCellData(config.writeRwcExcel(), "Billing", billingtab.fetchBillingTVRNoOfDaysLbl(),
				writeRowNo, billingtab.fetchBillingTVRNoOfDays());

		installmentPlanCheckStatus = billingtab.fetchBillingInstallmentPlan();
		CommonStep.scenario.log("Check Installment Payment checkbox");
		logger.setLoggerInfo("*Billing-Installment Plan Check Box Status *" + installmentPlanCheckStatus);
		
		billingtab.clickInstallmentPlan();
		logger.setLoggerInfo("* Click Installement Plan *");
		screenshotUtil.captureScreenshot(className, "Click Installement Plan");

		billingtab.selectElectronicDeliveryType(
				excelutil.getCellData("BillingTab", "Electronic Delivery Type", readRowNo));
		logger.setLoggerInfo("* Select Electronic DeliveryType *");
		screenshotUtil.captureScreenshot(className, "Select Electronic DeliveryType");

		commonobjects.provideComments(excelutil.getCellData("BillingTab", "BillingComments", readRowNo));
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		logger.setLoggerInfo("* Messages after Proceeding for first time in Billing Screen *"
				+ commonobjects.validateInfoMsgs());

		billingtab.enterManualAdjBaseJur(excelutil.getCellData("BillingTab", "Manual Adj. Base Jur.", readRowNo));
		logger.setLoggerInfo("* Enter ManualAdjBaseJur *");
		screenshotUtil.captureScreenshot(className, "Enter ManualAdjBaseJur");

		billingtab.clickReCalculate();
		logger.setLoggerInfo("* Click Recalculate *");
		screenshotUtil.captureScreenshot(className, "Click Recalculate");

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
		billingtab.enterManualAdjReasonComments(excelutil.getCellData("BillingTab", "ManualReason", readRowNo));
		logger.setLoggerInfo("* Enter ManualAdjReason Comments *");
		screenshotUtil.captureScreenshot(className, "Enter ManualAdjReason Comments");

		billingtab.clickManualAdjReasonDeleteAllowed();
		billingtab.clickManualAdjReasonAddorUpdateComments();
		CommonStep.scenario.log("Click on proceed button");
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		logger.setLoggerInfo("* Messages after Proceeding for Second time in Billing Screen *"
				+ commonobjects.validateInfoMsgs());

		/*
		 * Check whether Late Filing Penalty,Late Pay Penalty are greater than zero
		 * Bicentennial Fee,Grade Crossing Fee,Replacement Plate Fee,Second Plate
		 * Fee,Late Filing Penalty,Late Pay Penalty,Transfer Fee,Transfer Revenue
		 * Fee,Wire Transfer Fee S - System Error,M - MCE Agent Error,D - Natural
		 * Disaster,O - Other
		 */
		boolean LateFilingPenaltyAmount = billingtab
				.getAmount(excelutil.getCellData("BillingTab", "Late Filing Penalty", readRowNo));
		logger.setLoggerInfo("Late Filing Penalty Amount is Greater than zero - " + LateFilingPenaltyAmount);

		boolean LatePayPenaltyAmount = billingtab
				.getAmount(excelutil.getCellData("BillingTab", "Late Pay Penaltys", readRowNo));
		logger.setLoggerInfo("Late pay Penalty Amount is Greater than zero - " + LatePayPenaltyAmount);

		if (LateFilingPenaltyAmount == true && LatePayPenaltyAmount == true) {
			String FeeDescriptiontoWaiveOff = excelutil.getCellData("BillingTab", "Grade Crossing Fee", readRowNo);
			String FeeWaiveOffReason = excelutil.getCellData("BillingTab", "Grade Crossing Fee Waive Off", readRowNo);
			billingtab.selectWaiveOff(FeeDescriptiontoWaiveOff, FeeWaiveOffReason);
			logger.setLoggerInfo("* Waived off for " + FeeDescriptiontoWaiveOff + "  " + " with Reason "
					+ FeeWaiveOffReason + " *");
			screenshotUtil.captureScreenshot(className, "Waived off Fee");
		}
		billingtab.clickReCalculate();
		logger.setLoggerInfo("* Click Recalculate *");
		screenshotUtil.captureScreenshot(className, "Click Recalculate");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		billingtab.expandFeeOverrideReason();

		billingtab.enterFeeOverrideReasonComments(
				excelutil.getCellData("BillingTab", "FeeOverrideReasonComments", readRowNo));

		logger.setLoggerInfo("* Enter FeeOverrideReason Comments *");
		screenshotUtil.captureScreenshot(className, "Enter FeeOverrideReason Comments");

		billingtab.clickFeeOverrideReasonDeleteAllowed();
		billingtab.clickFeeOverrideReasonAddorUpdateComments();

		billingtab.clickReCalculate();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		ParentWindow = eleutil.GetParentWindow();

	
		installmentPlanCheckStatus = billingtab.fetchBillingInstallmentPlan();
		logger.setLoggerInfo("* Installment Plan Check Box Status *" + installmentPlanCheckStatus); 
		CommonStep.scenario.log("Manually Adjust & Waive Fees with comments & set Delivery Types as PDF");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		eleutil.waitForTwoWindow(2);
		String childWindow = eleutil.SwitchtoFirstChildWindow();
	/*	eleutil.saveAsFile();
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

	@Then("User will navigate to Payment Tab to input the data and validate message {string} {string} {string}")
	public void paymentTabNavigation(String expSucces, String expSucces2, String expSucces3) throws Exception {
		try {
		CommonStep.scenario.log("Verify the page & set Delivery Types as PDF Click on proceed button");

		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchEnterpriseSystemCreditLbl(),
				writeRowNo, paymenttab.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchIRPSystemCreditLbl(),
				writeRowNo, paymenttab.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchInvoiceDateLbl(), writeRowNo,
				paymenttab.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchInvoiceNumberLbl(), writeRowNo,
				paymenttab.fetchInvoiceNumber());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchPaymentReciptDateLbl(),
				writeRowNo, paymenttab.fetchPaymentReciptDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchManualAdjBaseJurLbl(),
				writeRowNo, paymenttab.fetchManualAdjBaseJur());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchBatchCredentialLbl(),
				writeRowNo, paymenttab.fetchBatchCredential());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchWireTransferFeeLbl(),
				writeRowNo, paymenttab.fetchWireTransferFee());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchElectronicDeliveryTypeLbl(),
				writeRowNo, paymenttab.fetchElectronicDeliveryType());

		ArrayList<String> paymentTableFeeType = paymenttab.fetchTableFeeType();
		ArrayList<String> paymentTableFeeAmount = paymenttab.fetchTableFeeAmount();
		for (int i = 0; i < paymentTableFeeType.size(); i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchHeaderFeeType() + i,
					writeRowNo, paymentTableFeeType.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentTab", paymenttab.fetchHeaderFeeAmount() + i,
					writeRowNo, paymentTableFeeAmount.get(i));
		}

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
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

		pay.selectElectronicDeliverytype(excelutil.getCellData("Payment", "ElectronicDeliveryType", readRowNo));
		logger.setLoggerInfo("*Select Delivery type*");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());


		CommonStep.scenario.log("Verify the page & Click Add to Cart button");

		paymenttab.clickAddtoCart();
		logger.setLoggerInfo("*Click Add to Cart");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		commonobjects.validateInfoMsgs();
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to supplement continuance and validate the meesage {string}")
	public void supplementContinuanceNavigation(String expSucces) throws Exception, Exception {
		try {
		CommonStep.scenario.log("Go to Supplment Continuance & try to continue above record");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		paymenttab.clicksupplcont();
		logger.setLoggerInfo("*Click Supplement continue*");
		screenshotUtil.captureScreenshot(className, "Click Supplement continue");

		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("*Enter Account Number*");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Fleet Screen" + expSucces);
		logger.setLoggerInfo("Message in Fleet Screen" + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Fleet Screen");
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User will navigate to payment tab and fill the requirement")
	public void paymentTabNavigation() throws Exception, Exception {
		try {
		CommonStep.scenario.log("Click Cart icon");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		paymenttab.clickVerifyAddToCart();
		logger.setLoggerInfo("*Verify Cart*");
		screenshotUtil.captureScreenshot(className, "Verify Cart");

		CommonStep.scenario.log("Select record & proceed");
		CommonStep.scenario.log("Click on pay now button");

		pay.clickPayNow();
		logger.setLoggerInfo("*Click Paynow*");
		screenshotUtil.captureScreenshot(className, "Click Paynow");

		commonobjects.clickProceed();
		CommonStep.scenario.log("Click Proceed");
		commonobjects.waitForSpinner();
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchMCECustomerIdLbl(), writeRowNo,
				pay.fetchMCECustomerId());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchLegalNameLbl(), writeRowNo,
				pay.fetchLegalName());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchInvoiceDateLbl(), writeRowNo,
				pay.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchEnterpriseSystemCreditLbl(),
				writeRowNo, pay.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchIRPSystemCreditLbl(), writeRowNo,
				pay.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchIftaSystemCreditLbl(), writeRowNo,
				pay.fetchIftaSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchOpaSystemCreditLbl(), writeRowNo,
				pay.fetchOpaSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", pay.fetchTotalAmountDueLbl(), writeRowNo,
				pay.fetchTotalAmountDue());

		ArrayList<String> tableHeaderValues = pay.fetchTableHeaders();
		ArrayList<String> tableInvoice = pay.fetchTableInvoiceDetails();
		for (int i = 0; i < tableInvoice.size(); i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(), "PaymentScreen", tableHeaderValues.get(i), writeRowNo,
					tableInvoice.get(i));
		}

		String paymentExcelCount = excelutil.getCellData("Payment", "payment Count", readRowNo);

		CommonStep.scenario
				.log("Select multiple payment types and select PDF delivery type and click on proceed button");
		for (int i = 0; i < Integer.valueOf(paymentExcelCount); i++) {

			logger.setLoggerInfo("no.of rows present is:" + pay.fetchTableRowsize());
			if (pay.fetchTableRowsize() < Integer.valueOf(paymentExcelCount)) {
				paymenttab.clickPaymenToAdd();
			}
			String PaymentType = excelutil.getCellData("Payment", "PaymentType" + i, readRowNo);
			String PaymentNumberValue = excelutil.getCellData("Payment", "PaymentChequeNo", readRowNo);
			pay.selectPaymentType(i, PaymentType);
			logger.setLoggerInfo("*Payment Type*");
			screenshotUtil.captureScreenshot(className, "Payment Type" + i);

			pay.enterpaymentNoBasedonType(i, PaymentNumberValue);
			logger.setLoggerInfo("*Payment Number based on Payment Type*");
			screenshotUtil.captureScreenshot(className, "Payment Number based on  Payment Type" + i);

			String totalAmount = pay.fetchTotalAmount();
			logger.setLoggerInfo("totalAmount is " + totalAmount);
			String cashAmount = String.format("%.2f",
					(Double.valueOf(totalAmount) / Integer.valueOf(paymentExcelCount)));
			if (i == (Integer.valueOf(paymentExcelCount) - 1)) {
				String RemainingAmount = pay.fetchRemainingBalance();
				logger.setLoggerInfo("Remaining balance is:" + RemainingAmount);
				pay.enterPaymentAmountBasedonType(i, RemainingAmount);
			} else {
				pay.enterPaymentAmountBasedonType(i, cashAmount);
			}

			logger.setLoggerInfo("*Payment Amount based on Payment Type*");
			screenshotUtil.captureScreenshot(className, "Payment Amount based on  Payment Type" + i);
		}

		pay.selectPaymentReceipt(excelutil.getCellData("Payment", "Payment receipt", readRowNo));
		logger.setLoggerInfo("*Enter Payment type and amount*");
		ParentWindow = eleutil.GetParentWindow();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		eleutil.waitForTwoWindow(2);
		childWindow = eleutil.SwitchtoFirstChildWindow();
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

	@Then("user will validate message {string} {string}")
	public void messageValidation(String expSucces, String expSucces2) throws Exception {
		try {
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen " + expSucces);

		logger.setLoggerInfo("Message in Payment Screen " + commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className, "Message in Payment Screen 1");

		String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage1, expSucces2);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen " + expSucces2);
		logger.setLoggerInfo("Message in Payment Screen " + commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className, "Message in Payment Screen 2");
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("User navigate to inventory tab to input the data and validate the message {string} {string}")
	public void inventoryTabNavigation(String expSucces, String expSucces2) throws Exception {
		try {
		CommonStep.scenario.log("Check Inventory ");
		dashboardpage.clickOperation();
		dashboardpage.clickOnInventory();

		dashboardpage.clickNewInventory();
		logger.setLoggerInfo("*Click Inventory*");
		screenshotUtil.captureScreenshot(className, "Click Inventory");

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		inventorypage.selectApplicationType(excelutil.getCellData("InventoryTab", "ApplicationType", readRowNo));
		logger.setLoggerInfo("*Select Application Type*");

		inventorypage.selectNewInventoryType(excelutil.getCellData("InventoryTab", "inventoryType", readRowNo));
		logger.setLoggerInfo("*Select New Inventory Type*");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Inventory Screen" + expSucces);

		logger.setLoggerInfo("Message in Inventory Screen" + commonobjects.fetchErrorMessage(expSucces));

		screenshotUtil.captureScreenshot(className, "Message in Inventory Screen");

		inventorypage.selectNewSubInventoryType(excelutil.getCellData("InventoryTab", "Subtype", readRowNo));
		logger.setLoggerInfo("*Select New Sub Inventory Type*");

		inventorypage.selectInventoryStatus(excelutil.getCellData("InventoryTab", "InventoryStatus", readRowNo));
		logger.setLoggerInfo("*Select Inventory Status*");

		inventorypage.enterPrefix(excelutil.getCellData("InventoryTab", "Prefix", readRowNo));
		logger.setLoggerInfo("*Enter Prefix *");

		inventorypage.enterFromNo(excelutil.getCellData("InventoryTab", "FromNo", readRowNo));
		logger.setLoggerInfo("*Enter Form No*");

		inventorypage.enterToNo(excelutil.getCellData("InventoryTab", "ToNo", readRowNo));
		logger.setLoggerInfo("*Enter To No*");

		inventorypage.enterQuantity(excelutil.getCellData("InventoryTab", "Quantity", readRowNo));
		logger.setLoggerInfo("*Enter Quantity*");

		inventorypage.enterYear(excelutil.getCellData("InventoryTab", "Year", readRowNo));
		logger.setLoggerInfo("*Enter Year*");

		screenshotUtil.captureScreenshot(className, "Enter details for new inventory");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);

		} catch (Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen" + expSucces2);

		logger.setLoggerInfo("Message in Payment Screen" + commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className, "Message in Inventory Screen 2");

		commonobjects.clickQuit();
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("Assign the inventory to proceed further {string} {string}")
	public void assignInventory(String expSucces, String expSucces2) throws Exception, Exception {
		try {
		CommonStep.scenario.log("Go to Inventory - Assign Invetory & add required Inventory");

		dashboardpage.clickAssignInventory();
		logger.setLoggerInfo("*Click Assign Inventory*");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());

		inventorypage.selectNewInventoryType(excelutil.getCellData("InventoryTab", "Subtype", readRowNo));

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Inventory Screen " + expSucces);

		logger.setLoggerInfo("Message in Inventory Screen " + commonobjects.fetchErrorMessage(expSucces));

		screenshotUtil.captureScreenshot(className, "Message in Inventory Screen 1");

		inventorypage.selectNewSubInventoryType(excelutil.getCellData("InventoryTab", "InventorySubtype", readRowNo));
		logger.setLoggerInfo("*Select new Sub Inventory Type*");

		inventorypage.enterFromNo(excelutil.getCellData("InventoryTab", "FromNo", readRowNo)); 
		logger.setLoggerInfo("*Enter Form No*");

		inventorypage.enterQuantity(excelutil.getCellData("InventoryTab", "Quantity", readRowNo));
		logger.setLoggerInfo("*Enter Quantity*");

		inventorypage.enterYear(excelutil.getCellData("InventoryTab", "Year", readRowNo));
		logger.setLoggerInfo("*Enter Year*");

		screenshotUtil.captureScreenshot(className, "Enter details in Assign Inventory");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Inventory Screen" + expSucces2);

		logger.setLoggerInfo("Message in Inventory Screen" + commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className, "Message in Inventory Screen 2");

		commonobjects.clickQuit();
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("user navigate to post payment for 2nd installment payment and fill the data and validate the message {string}")
	public void secondPayment(String expSucces) throws Exception {
		try {
		CommonStep.scenario.log("Go to Finance -Do Post Payment");

		dashboardpage.clickOperation();
		commonobjects.waitForSpinner();
		logger.setLoggerInfo("*Click on Operations*");

		financepage.clickfinance();
		commonobjects.waitForSpinner();
		logger.setLoggerInfo("*Click on Finance*");

		financepage.clickpostpayment();
		commonobjects.waitForSpinner();
		logger.setLoggerInfo("*Click on Post Payment*");

		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		ParentWindow = eleutil.GetParentWindow();

		financepage.enterMCEid(excelutil.getCellData("PreSetup", "MCENumber", readRowNo));
		logger.setLoggerInfo("*Enter MCEId*");
		financepage.clicksearch();

		financepage.clickoncartid();
		logger.setLoggerInfo("*Click on Cart id*");
		logger.setLoggerInfoArray(commonobjects.validateInfoMsgs());
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		} catch (Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message for Post Payment Screen" + expSucces);
		}catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}

	@Then("user should land on the installement payment and verify the amount and then proceed {string} {string}")
	public void amountVerification(String expSucces, String expSucces2) throws Exception {
		try {
		CommonStep.scenario
				.log("Go to Payment - Installment Payment & serach above record & complete 2nd Installment payment");
		if (installmentPlanCheckStatus.equalsIgnoreCase("true")) {
			logger.setLoggerInfo("Installment Payment is required to do");
			CommonStep.scenario.log("Go to Payment - Installment Payment & serach above record & complete 2nd Installment payment");

			dashboardpage.clickEnterpriseLink();
			commonobjects.waitForSpinner();

			dashboardpage.clickIRPLink();
			commonobjects.waitForSpinner();

			dashboardpage.clickInstallmentPayment();
			commonobjects.waitForSpinner();

			financepage.clickandenterAccountNo(excelutil.getCellData("PreSetup", "AccountNumber", readRowNo));
			logger.setLoggerInfo("* Installment Payment Screen-Enter Account Number *");
			screenshotUtil.captureScreenshot(className, "Installment Payment Screen-Enter Account Number");

			financepage.clickandenterfleet(excelutil.getCellData("PreSetup", "FleetNumber", readRowNo));
			logger.setLoggerInfo("* Installment Payment Screen-Enter Fleet Number *");
			screenshotUtil.captureScreenshot(className, "Installment Payment Screen-Enter Fleet Number");

			financepage.clickandenterfleetyear(
					excelutil.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
			logger.setLoggerInfo("* Installment Payment Screen-Enter Fleet Year *");
			screenshotUtil.captureScreenshot(className, "Installment Payment Screen-Enter Fleet Year");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			financepage.clickgrid();

			
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchPostPaymentAccountNoLbl(), writeRowNo, financepage.fetchPostPaymentAccountNo());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchFleetNoLbl(), writeRowNo, financepage.fetchFleetNo());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchLegalNameLbl(), writeRowNo, financepage.fetchLegalName());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchFleetMonthYearLbl(), writeRowNo, financepage.fetchFleetMonthYear());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementNoLbl(), writeRowNo, financepage.fetchSupplementNo());

			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchDBANameLbl(), writeRowNo, financepage.fetchDBAName());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchFleetTypeLbl(), writeRowNo, financepage.fetchFleetType());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementDescLbl(), writeRowNo, financepage.fetchSupplementDesc());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails", financepage.fetchUSDOTLbl(),
					writeRowNo, financepage.fetchUSDOT());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementEffectiveDateLbl(), writeRowNo,
					financepage.fetchSupplementEffectiveDate());

			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchSupplementStatusLbl(), writeRowNo, financepage.fetchSupplementStatus());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInvoiceDateLbl(), writeRowNo, financepage.fetchInvoiceDate());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInvoicenoLbl(), writeRowNo, financepage.fetchInvoiceNo());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchEnterpriseSystemCreditLbl(), writeRowNo,
					financepage.fetchEnterpriseSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInstallmentReceiptDateLbl(), writeRowNo,
					financepage.fetchInstallmentReceiptDate());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
					financepage.fetchInstallmentPlanNumberlbl(), writeRowNo, financepage.fetchInstallmentPlanNumber());

			ArrayList<String> TableFeeType = billingtab.fetchTableFeeType();
			ArrayList<String> TableFeeAmount = billingtab.fetchTableFeeAmount();

			for (int i = 0; i < TableFeeType.size(); i++) {
				excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
						billingtab.fetchBillingGridFeeType() + i, writeRowNo, TableFeeType.get(i));
				excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentDetails",
						billingtab.fetchBillingGridFeeAmount() + i, writeRowNo, TableFeeAmount.get(i));
			}

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();

			
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen", pay.fetchMCECustomerIdLbl(),
					writeRowNo, pay.fetchMCECustomerId());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen", pay.fetchLegalNameLbl(),
					writeRowNo, pay.fetchLegalName());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					financepage.fetchDBANameLbl(), writeRowNo, financepage.fetchDBAName());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchEnterpriseSystemCreditLbl(), writeRowNo, pay.fetchEnterpriseSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchIRPSystemCreditLbl(), writeRowNo, pay.fetchIRPSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchIftaSystemCreditLbl(), writeRowNo, pay.fetchIftaSystemCredit());
			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen",
					pay.fetchOpaSystemCreditLbl(), writeRowNo, pay.fetchOpaSystemCredit());

			excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen", pay.fetchTotalAmountDueLbl(),
					writeRowNo, pay.fetchTotalAmountDue());

			ArrayList<String> tableHeaderValues = pay.fetchTableHeaders();
			ArrayList<String> tableInvoice = pay.fetchTableInvoiceDetails();
			for (int i = 0; i < tableInvoice.size(); i++) {
				excelutilWrite.setCellData(config.writeRwcExcel(), "InstallmentPaymentScreen", tableHeaderValues.get(i),
						writeRowNo, tableInvoice.get(i));
			}

			String paymentExcelCount = excelutil.getCellData("Installment Payment", "payment Count", readRowNo);
			for (int i = 0; i < Integer.valueOf(paymentExcelCount); i++) {
				logger.setLoggerInfo("no.of rows present is:" + pay.fetchTableRowsize());
				if (pay.fetchTableRowsize() < Integer.valueOf(paymentExcelCount)) {
					paymenttab.clickPaymenToAdd();
				}
				String PaymentType = excelutil.getCellData("Payment", "PaymentType" + i, readRowNo);
				String PaymentNumberValue = excelutil.getCellData("Payment", "PaymentChequeNo", readRowNo);
				pay.selectPaymentType(i, PaymentType);
				logger.setLoggerInfo("*Payment Type*");
				screenshotUtil.captureScreenshot(className, "Payment Type" + i);

				pay.enterpaymentNoBasedonType(i, PaymentNumberValue);
				logger.setLoggerInfo("*Payment Number based on Payment Type*");
				screenshotUtil.captureScreenshot(className, "Payment Number based on  Payment Type" + i);

				String totalAmount = pay.fetchTotalAmount();
				logger.setLoggerInfo("totalAmount is " + totalAmount);
				String cashAmount = String.format("%.2f",
						(Double.valueOf(totalAmount) / Integer.valueOf(paymentExcelCount)));
				if (i == (Integer.valueOf(paymentExcelCount) - 1)) {
					String RemainingAmount = pay.fetchRemainingBalance();
					logger.setLoggerInfo("Remaining balance is:" + RemainingAmount);
					pay.enterPaymentAmountBasedonType(i, RemainingAmount);
				} else {
					pay.enterPaymentAmountBasedonType(i, cashAmount);
				}

				logger.setLoggerInfo("*Payment Amount based on Payment Type*");
				screenshotUtil.captureScreenshot(className, "Payment Amount based on  Payment Type" + i);
			}

			pay.selectPaymentReceipt(excelutil.getCellData("Payment", "Payment receipt", readRowNo));
			logger.setLoggerInfo("*Enter Payment type and amount*");

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

	@Then("User will navigate to all the inquiries and report with respect to the flow for verification.")
	public void inquiriesAndReportsVerification() throws Exception {
		try {
		Boolean flag = false;
		String expiryYearExcel = null;
		ParentWindow = eleutil.GetParentWindow();

		dashboardpage.clickServiceLink();
		commonobjects.waitForSpinner();
		logger.setLoggerInfo("FleetEnquiry-Click on Services ");

		dashboardpage.clickIRPLink();
		commonobjects.waitForSpinner();
		logger.setLoggerInfo("FleetEnquiry-Click on IRP");

		dashboardpage.clickFleetEnquiry();
		logger.setLoggerInfo("FleetEnquiry-Click on Fleet Enquiry");

		childWindow = eleutil.SwitchtoFirstChildWindow();

		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("FleetEnquiry-Enter Account Number");

		fleetpage.enterFleetyear(
				excelutil.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("FleetEnquiry-Enter Expiry Year");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "FleetEnquiry-Click on proceed");
		flag = enquiry.fleetenquiryvaluevalidation(excelutil.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo));

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

		enquiry.enterSupplementEnquiryAccountNo(excelutil.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("SupplementEnquiry-Enter Account Number");

		enquiry.enterSupplementEnquiryExpYear(
				excelutil.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("SupplementEnquiry-Enter Expiry Year ");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "SupplementEnquiry-Click on proceed");

		flag = enquiry
				.supplementEnquiryvaluevalidation(excelutil.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo));

		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);

		try {
			Assert.assertEquals(flag, true);
		} catch (Throwable e) {
			error.addError(e);
		}

		expiryYearExcel = excelutil.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo);

		dashboardpage.clickVehicleEnquiry();
		logger.setLoggerInfo("Vehicle Enquiry-Click on Vehicle Enquiry");

		childWindow = eleutil.SwitchtoFirstChildWindow();

		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("Vehicle Enquiry-Enter Account Number");

		enquiry.enterVehicleEnquiryExpYear(
				excelutil.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("Vehicle-Enter Expiry Year");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "Vehicle Enquiry-Click on proceed");

		for (int i = 1; i <= enquiry.vehicleEnquiryTableRowCount(); i++) {
			String actualUnit = enquiry.fetchVehicleEnquiryUnit(String.valueOf(i), expiryYearExcel);
			String expectedUnit = excelutil.getCellData("VehicleAmendTab", "Unit" + String.valueOf(i - 1), readRowNo);
			if (actualUnit != null) {
				try {
					Assert.assertEquals(actualUnit, expectedUnit);
				} catch (Throwable e) {
					error.addError(e);
				}
			}
		}
		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);

		expiryYearExcel = excelutil.getCellData("Enquiry", "Fleet EXP.MM/YYYY", readRowNo);

		dashboardpage.clickVehicleSupplementEnquiry();
		logger.setLoggerInfo("Vehicle Supplement Enquiry-Click on Vehicle Enquiry");

		childWindow = eleutil.SwitchtoFirstChildWindow();

		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup", "AccountNumber", readRowNo));
		logger.setLoggerInfo("Vehicle Supplement Enquiry-Enter Account Number");

		enquiry.enterVehicleSupplementEnquiryExpYear(
				excelutil.getCellData("PreSetup", "Vehicle and Installment Fleet Expiration Year", readRowNo));
		logger.setLoggerInfo("Vehicle-Enter Expiry Year");

		commonobjects.clickProceed();
		screenshotUtil.captureScreenshot(className, "Vehicle Enquiry-Click on proceed");

		for (int i = 1; i < enquiry.vehicleSupplementEnquiryTableRowCount(); i++) {
			String actualdeleteUnit = enquiry.fetchVehicleSupplementEnquiryUnit(String.valueOf(i), expiryYearExcel,
					"Delete");
			logger.setLoggerInfo("actual delete unit:" + actualdeleteUnit);
			String expecteddeleteUnit = excelutil.getCellData("VehicleDeleteTab", "Unit" + String.valueOf(i - 1),
					readRowNo);
			if (actualdeleteUnit != null) {
				try {
					Assert.assertEquals(actualdeleteUnit, expecteddeleteUnit);
				} catch (Throwable e) {
					error.addError(e);
				}
			}
			String actualAmendedUnit = enquiry.fetchVehicleSupplementEnquiryUnit(String.valueOf(i), expiryYearExcel,
					"Amend");
			logger.setLoggerInfo("actual Amend unit:" + actualAmendedUnit);
			String expectedAmendedUnit = excelutil.getCellData("VehicleAmendTab", "Unit" + String.valueOf(i - 1),
					readRowNo);

			if (actualAmendedUnit != null) {
				try {
					Assert.assertEquals(actualAmendedUnit, expectedAmendedUnit);
				} catch (Throwable e) {
					error.addError(e);
				}
			}
		}

		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(ParentWindow);
		logger.setLoggerInfo("TEst Execution is Passed");
		logger.closeTheHandler();
		}
		catch(Exception e) {
			logger.setLoggerInfo(ExceptionUtils.getStackTrace(e));
			logger.closeTheHandler();
			throw new Exception(e);
		}
	}
	
	@AfterStep(value="@RWC001")
	public void as(Scenario scenario) throws Exception {
		scenario.attach(new GenericFunctions().getByteScreenshot(), "image/png", "anyname");
	}

	@After(value="@RWC001")
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

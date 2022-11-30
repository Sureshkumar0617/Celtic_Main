package com.celtic.automation.cmcs.parallel;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import com.celtic.automation.cmcs.factory.DriverFactory;
import com.celtic.automation.cmcs.pages.AccountTabPage;
import com.celtic.automation.cmcs.pages.BillingTab;
import com.celtic.automation.cmcs.pages.CommonObjects;
import com.celtic.automation.cmcs.pages.DashBoardPage;
import com.celtic.automation.cmcs.pages.DistanceTabPage;
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
import com.celtic.automation.cmcs.util.ReadExcelUtil;
import com.celtic.automation.cmcs.util.ScreenshotUtility;
import com.celtic.automation.cmcs.util.WriteExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.junit.rules.ErrorCollector;

public class RWC001 {

	private LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
	private DashBoardPage dashboardpage = new DashBoardPage(DriverFactory.getDriver());
	private AccountTabPage accounttabpage = new AccountTabPage(DriverFactory.getDriver());
	private FleetTabPage fleettabpage = new FleetTabPage(DriverFactory.getDriver());
	private FleetPage fleetpage = new FleetPage(DriverFactory.getDriver());
	private CommonObjects commonobjects = new CommonObjects(DriverFactory.getDriver());
	private DistanceTabPage distancetabpage = new DistanceTabPage(DriverFactory.getDriver());
	private WgtGroup wgtgroup = new WgtGroup(DriverFactory.getDriver());
	private WgtGroupAdd wgtgroupadd = new WgtGroupAdd(DriverFactory.getDriver());
	private VehicleTabPage Vehicletabpage = new VehicleTabPage(DriverFactory.getDriver());
	private VehicleAmend vehicleAmend =new VehicleAmend(DriverFactory.getDriver());
	private VehicleDelete vehicleDelete =new VehicleDelete(DriverFactory.getDriver());
	private BillingTab billingtab = new BillingTab(DriverFactory.getDriver());
	private Payment pay =new Payment(DriverFactory.getDriver());
	private PaymentTab paymenttab = new PaymentTab(DriverFactory.getDriver());
	private InventoryPage inventorypage = new InventoryPage(DriverFactory.getDriver());
	private VehicleAdd vehicleadd= new VehicleAdd(DriverFactory.getDriver());
	private Financepage financepage =new Financepage(DriverFactory.getDriver());
	private VehicleList vehiclelist =new VehicleList(DriverFactory.getDriver());

	private int readRowNo=1;
	private int writeRowNo=2;
	private String cancelDeletedVehicle=null;
	
	private String cancelAmendedVehicle=null;
	private ElementUtil eleutil;
	private ScreenshotUtility screenshotUtil =new ScreenshotUtility();
	public ConfigReader config = new ConfigReader();
	public Logger log ;
	private ReadExcelUtil excelutil=null;
	private WriteExcelUtil excelutilWrite=null;
	private ErrorCollector error = new ErrorCollector();
	private String[] fullClassName=this.getClass().getName().split("[.]");
	private String className = this.getClass().getName().split("[.]")[fullClassName.length-1];
	private String installmentPlanCheckStatus=null;

	@Given("User login as a Internal user")
	public void user_login_as_a_internal_user() throws Exception {
		
		
				
			
		
			/*
			 * String test = new Throwable().getStackTrace()[0].getClassName(); String path
			 * =System.getProperty("user.dir")+"\\log"+test;
			 * System.setProperty("file.path.can",path); log = LogManager.getLogger(test);
			 * ConfigReader.getLogInfo("Opened Browser");
			 */
		config.loggerConfigurator(new Throwable().getStackTrace()[0].getClassName());
		config.initprop();
		excelutil = new ReadExcelUtil(config.readRwcExcel(),ConfigReader.log);
		excelutilWrite=new WriteExcelUtil(ConfigReader.log);
		eleutil =new ElementUtil(ConfigReader.log);
		
				
		DriverFactory.getDriver().get(config.readLoginURL());
		CommonStep.scenario.log("Launch the application using URL and login with valid credentials");
		ConfigReader.getLogInfo("****************************** Login to the application  *****************************************");
		screenshotUtil.captureScreenshot(className,"ApplicationLogin");
		loginpage.enterUsername(config.readLoginInternalUser());
		ConfigReader.getLogInfo("*** Enter Username ***");
		screenshotUtil.captureScreenshot(className,"Username");
		loginpage.enterPassword(config.readPswrd());
		ConfigReader.getLogInfo("*** Enter Password ***");
		screenshotUtil.captureScreenshot(className,"Password");
		loginpage.clickLoginBtn();
		ConfigReader.getLogInfo("*** Click Login ***");
		screenshotUtil.captureScreenshot(className,"Login");
	}
		
	
	@When("User will navigate to the IRPLink")
	public void user_will_navigate_to_the_irp_link() throws Exception {
		CommonStep.scenario.log("Expand the Services header on the left column of the screen and select IRP");
		CommonStep.scenario.log("Click on Renew fleet from Fleet card menu.");
		dashboardpage.clickIRPLink();
		ConfigReader.getLogInfo("*** Click IRP ***");
		screenshotUtil.captureScreenshot(className,"IRP");
		dashboardpage.clickRenewFleetLink();
		ConfigReader.getLogInfo("*** Click RenewFleet ***");
		screenshotUtil.captureScreenshot(className,"RenewFleet");
	}

	@Then("User will provide all the Account Number Details to start with IRP Transaction")
	public void user_will_provide_all_the_account_number_details_to_start_with_irp_transaction() throws IOException, Exception {
		CommonStep.scenario.log("Enter valid search data and click to proceed");
		
		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
		ConfigReader.getLogInfo("*** Enter Account Number ***");
		screenshotUtil.captureScreenshot(className,"Entering AccountNumber");
		fleetpage.enterFleetNo(excelutil.getCellData("PreSetup","FleetNumber",readRowNo));
		ConfigReader.getLogInfo("*** Enter FleetNo ***");
		screenshotUtil.captureScreenshot(className,"Entering FleetNumber");
		fleetpage.enterFleetyear(excelutil.getCellData("PreSetup","Fleet Expiration Year",readRowNo));
		ConfigReader.getLogInfo("*** Click FleetYear ***");
		screenshotUtil.captureScreenshot(className,"Entering FleetYear");
		commonobjects.clickProceed();	
commonobjects.waitForSpinner();
	}

	@Then("User will navigate to account section and fill the data")
	public void user_will_navigate_to_account_section_and_fill_the_data_and_validate_message() throws Exception, Exception {
		//Fetch Values
		
		CommonStep.scenario.log("Enter valid all detail in account page with comments and click on proceed button");
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchMCECustomernolbl(),writeRowNo,accounttabpage.fetchMCECustomerno());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchRegistrationTypelbl(), writeRowNo,accounttabpage.fetchRegistrationType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountCarrierTypeLbl(), writeRowNo,accounttabpage.fetchAccountCarrierType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchIFTAAccountNbrlbl(),writeRowNo,accounttabpage.fetchIFTAAccountNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountCustomerStatusLbl(),writeRowNo,accounttabpage.fetchAccountCustomerStatus());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountStreet0Lbl(),writeRowNo,accounttabpage.fetchAccountStreet0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountZip0lbl(),writeRowNo,accounttabpage.fetchAccountZip0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountJur0lbl(), writeRowNo,accounttabpage.fetchAccountJur0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountCity0lbl(),writeRowNo,accounttabpage.fetchAccountCity0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountCounty0lbl(), writeRowNo,accounttabpage.fetchAccountCounty0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab1()+accounttabpage.fetchAccountCountry0lbl(),writeRowNo,accounttabpage.fetchAccountCountry0());

		accounttabpage.clickMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountStreet1lbl(),writeRowNo,accounttabpage.fetchAccountStreet1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountZip1lbl(), writeRowNo,accounttabpage.fetchAccountZip1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountJur1lbl(), writeRowNo,accounttabpage.fetchAccountJur1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountCity1lbl(),writeRowNo,accounttabpage.fetchAccountCity1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountCounty1lbl(),writeRowNo,accounttabpage.fetchAccountCounty1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountCountry1lbl(),writeRowNo,accounttabpage.fetchAccountCountry1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountsTab2()+accounttabpage.fetchAccountAttentionTolbl(),writeRowNo,accounttabpage.fetchAccountAttentionTo());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountUSDOTNolbl(), writeRowNo,accounttabpage.fetchAccountUSDOTNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountTPIDlbl(),writeRowNo,accounttabpage.fetchAccountTPID());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountContactnamelbl(), writeRowNo,accounttabpage.fetchAccountContactname());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountEmaillbl(),writeRowNo,accounttabpage.fetchAccountEmail());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountPrimaryPhonelbl(),writeRowNo,accounttabpage.fetchAccountPrimaryPhone());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountAlternatePhonelbl(), writeRowNo,accounttabpage.fetchAccountAlternatePhone());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountFaxNolbl(),writeRowNo,accounttabpage.fetchAccountFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountEmailnotificationlbl(), writeRowNo,accounttabpage.fetchAccountEmailnotification());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Account",accounttabpage.fetchAccountFaxnotificationlbl(),writeRowNo,accounttabpage.fetchAccountFaxnotification());

		
		accounttabpage.checkEmailNotification();
		ConfigReader.getLogInfo("*** Check Email Notification ***");
		screenshotUtil.captureScreenshot(className,"Check EmailNotification");
		commonobjects.provideComments(excelutil.getCellData("AccountTab","Comments",readRowNo));
		ConfigReader.getLogInfo("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter Comments in Account Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		//Account Verification Screen
		CommonStep.scenario.log("Click on proceed from the verification page");
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to Fleet section and fill the data and validate message  {string}")
	public void user_will_navigate_to_fleet_section_and_fill_the_data_and_validate_message(String expSucces) throws Exception {
		String actualtext = commonobjects.fetchErrorMessage(expSucces);

		try {
		Assert.assertEquals(actualtext, expSucces);
		}
		catch(Throwable e) {
		error.addError(e);
		}

		CommonStep.scenario.log("Message in Fleet Screen"+expSucces);
		
		CommonStep.scenario.log("Enter update all the mandatory and valid details in fleet page. Also update Fleet Expiration Date & Fleet Type & Commodity Class and click on proceed button after entering comments");
		//Fetch Fleet Details screen 
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchRegistrationtypelbl(), writeRowNo,fleettabpage.fetchRegistrationtype());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchfltstatuslbl(),writeRowNo,fleettabpage.fetchfltstatus());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchcarriertypelbl(), writeRowNo,fleettabpage.fetchcarriertype());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchDBANamelbl(),writeRowNo, fleettabpage.fetchDBAName());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetStreet0Lbl(),writeRowNo,fleettabpage.fleetStreet0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetZip0Lbl(),writeRowNo,fleettabpage.fleetZip0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetJur0Lbl(), writeRowNo,fleettabpage.fleetJur0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetCity0Lbl(),writeRowNo, fleettabpage.fleetCity0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetCounty0Lbl(), writeRowNo,fleettabpage.fleetCounty0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetCountry0Lbl(),writeRowNo,fleettabpage.FleetCountry0());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabBusinessAddress()+fleettabpage.fleetNonDeliverable0Lbl(), writeRowNo,fleettabpage.fleetNonDeliverable0());

		fleettabpage.clickMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetStreet1Lbl(),writeRowNo,fleettabpage.fleetStreet1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetZip1Lbl(),writeRowNo,fleettabpage.fleetZip1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetJur1Lbl(), writeRowNo,fleettabpage.fleetJur1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetCity1Lbl(),writeRowNo, fleettabpage.FleetCity1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetCounty1Lbl(), writeRowNo,fleettabpage.fleetCounty1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetCountry1Lbl(),writeRowNo,fleettabpage.fleetCountry1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetNonDeliverable1Lbl(), writeRowNo,fleettabpage.fleetNonDeliverable1());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabMailingAddress()+fleettabpage.fleetAttentionToLbl(), writeRowNo,fleettabpage.fleetAttentionTO());

		fleettabpage.navigateToServiceProvider();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceProviderLbl(), writeRowNo,fleettabpage.fleetServiceProvider());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceLegalNameLbl(),writeRowNo,fleettabpage.fleetServiceLegalName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceDBANameLbl(), writeRowNo,fleettabpage.fleetServiceDBAName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePowerOfAttroneyLbl(),writeRowNo, fleettabpage.fleetServicePowerOfAttroney());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePowerOfAttroneyEffDateLbl(), writeRowNo,fleettabpage.fleetServicePowerOfAttroneyEffDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePowerOfAttroneyExpDateLbl(),writeRowNo,fleettabpage.fleetServicePowerOfAttroneyExpDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceEmailIdLbl(), writeRowNo,fleettabpage.fleetServiceEmailId());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServicePhoneNoLbl(),writeRowNo, fleettabpage.fleetServicePhoneNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceFaxNoLbl(), writeRowNo,fleettabpage.fleetServiceFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceStreetLbl(),writeRowNo,fleettabpage.fleetServiceStreet());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceCityLbl(), writeRowNo,fleettabpage.fleetServiceCity());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceJurLbl(),writeRowNo, fleettabpage.fleetServiceJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceZipCodeLbl(), writeRowNo,fleettabpage.fleetServiceZipCode());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabServiceProviderAddress()+fleettabpage.fleetServiceCountryLbl(),writeRowNo,fleettabpage.fleetServiceCountry());

		fleettabpage.clickOnTimeMailingAddress();
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingStreetLbl(), writeRowNo,fleettabpage.fleetServiceMailingStreet());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingZipCodeLbl(),writeRowNo, fleettabpage.fleetServiceMailingZipCode());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingJurLbl(), writeRowNo,fleettabpage.fleetServiceMailingJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingCityLbl(),writeRowNo,fleettabpage.fleetServiceMailingCity());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingCountyLbl(), writeRowNo,fleettabpage.fleetServiceMailingCounty());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingCountryLbl(),writeRowNo, fleettabpage.fleetServiceMailingCountry());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fetchFleetTabOneTimeMailingAddress()+fleettabpage.fleetServiceMailingAttentionToLbl(), writeRowNo,fleettabpage.fleetServiceMailingAttentionTo());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsContactNameLbl(),writeRowNo,fleettabpage.fleetDetailsContactName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsEmailIdLbl(), writeRowNo,fleettabpage.fleetDetailsEmailId());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsPrimaryCellNbrLbl(),writeRowNo, fleettabpage.fleetDetailsPrimaryCellNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsAlternateCellNbrLbl(), writeRowNo,fleettabpage.fleetDetailsAlternateCellNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFaxNoLbl(),writeRowNo, fleettabpage.fleetDetailsFaxNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsTpIdLbl(), writeRowNo,fleettabpage.fleetDetailsTPID());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsUsdotNbrLbl(),writeRowNo,fleettabpage.fleetDetailsUsdotNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsChangeVehUsdotTinLbl(), writeRowNo,fleettabpage.fleetDetailsChangeVehUsdotTin());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFltTypeLbl(),writeRowNo, fleettabpage.fleetDetailsFltType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsCommodityClassLbl(), writeRowNo,fleettabpage.fleetDetailsCommodityClass());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFltEffDateLbl(),writeRowNo,fleettabpage.fleetDetailsFltEffDatedtPicker());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFltExpDateLbl(), writeRowNo,fleettabpage.fleetDetailsFltExpDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsChangeAddrOnUsdotLbl(),writeRowNo, fleettabpage.fleetDetailsChangeAddrOnUsdot());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsFirstOperatedDateLbl(),writeRowNo,fleettabpage.fleetDetailsFirstOperatedDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsWyomingIndicatorLbl(), writeRowNo,fleettabpage.fleetDetailsWyomingIndicator());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsIFTADistanceLbl(),writeRowNo, fleettabpage.fleetDetailsIFTADistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsMobileNotificationLbl(), writeRowNo,fleettabpage.fleetDetailsMobileNotification());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsIRPRequirementsLbl(),writeRowNo, fleettabpage.fleetDetailsIRPRequirements());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsStatementOfUnderstandingLbl(), writeRowNo,fleettabpage.fleetDetailsStatementOfUnderstanding());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsInstallmentAgreementLbl(),writeRowNo,fleettabpage.fleetDetailsInstallmentAgreement());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsPowerOfAttorneyLbl(), writeRowNo,fleettabpage.fleetDetailsPowerOfAttorney());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsHVUTFormLbl(),writeRowNo, fleettabpage.fleetDetailsHVUTForm());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Fleet",fleettabpage.fleetDetailsPropertyTaxLbl(), writeRowNo,fleettabpage.fleetDetailsPropertyTax());


		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		ConfigReader.getLogInfo("Message in Fleet Screen "+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Fleet Screen 1");



		fleettabpage.navigateToServiceProvider();
		ConfigReader.getLogInfo("*** navigateToServiceProvider ***");
		screenshotUtil.captureScreenshot(className,"Navigate to Service provider");

		fleettabpage.clickPowerOfAttroney();
		ConfigReader.getLogInfo("*** Click PowerofAttroney ***");
		screenshotUtil.captureScreenshot(className,"Check Power of Attroney");

		fleettabpage.enterEmailID(excelutil.getCellData("FleetTab","Email iD",readRowNo));
		ConfigReader.getLogInfo("*** Entering the Emailid ***");
		screenshotUtil.captureScreenshot(className,"Entering the Emailid");
		//Expiration Date through Excel
		//	fleettabpage.selectExpirationDate(ExcelReader.FetchDataFromSheet(config.readRWCexcel(), "FleetTab", 2, 13));
		//Expiration Date through System
		fleettabpage.selectExpirationDate(eleutil.getAddedDateInSpecifiedFormat("MMddYYYY",2));
		ConfigReader.getLogInfo("*** Selecting the Expiration Date ***");
		screenshotUtil.captureScreenshot(className,"Selecting the Expiration Date");

		fleettabpage.selectIRPRequirementForm(excelutil.getCellData("FleetTab","IRP Requirements Form",readRowNo));
		ConfigReader.getLogInfo("*** Selecting the IRPRequirementForm ***");
		screenshotUtil.captureScreenshot(className,"Selecting the IRPRequirementForm");

		fleettabpage.selectStatementofUnderstanding(excelutil.getCellData("FleetTab","Statement of Understanding",readRowNo));		
		ConfigReader.getLogInfo("*** Selecting StatementofUnderstanding ***");
		screenshotUtil.captureScreenshot(className,"Selecting StatementofUnderstanding");

		fleettabpage.selectInstallmentAgreement(excelutil.getCellData("FleetTab","Installment Agreement",readRowNo));
		ConfigReader.getLogInfo("*** Selecting InstallmentAgreement ***");
		screenshotUtil.captureScreenshot(className,"Selecting InstallmentAgreement");

		fleettabpage.selectPowerOfAttroney(excelutil.getCellData("FleetTab","Power of Attorney",readRowNo));
		ConfigReader.getLogInfo("*** Selecting PowerOfAttroney ***");
		screenshotUtil.captureScreenshot(className,"Selecting PowerOfAttroney");

		fleettabpage.selectHVUTForm(excelutil.getCellData("FleetTab","HVUT - Form 2290",readRowNo));
		ConfigReader.getLogInfo("*** Selecting HVUTForm ***");
		screenshotUtil.captureScreenshot(className,"Selecting HVUTForm");		

		fleettabpage.selectPropertyTax(excelutil.getCellData("FleetTab","Property Tax",readRowNo));
		ConfigReader.getLogInfo("*** Selecting PropertyTax ***");
		screenshotUtil.captureScreenshot(className,"Selecting PropertyTax");		

		commonobjects.provideComments(excelutil.getCellData("FleetTab","Comments",readRowNo));
		ConfigReader.getLogInfo("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter Comments in Fleet Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		CommonStep.scenario.log("Click on proceed button from the verification page");
		//Fleet Verification Screen
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to Distance section and fill the data and validate message {string} {string}")
	public void user_will_navigate_to_distance_section_and_fill_the_data_and_validate_message(String expSucces1, String expSucces2) throws Exception, Exception {
		CommonStep.scenario.log("Select Actual Distance radio button & Enter all the mandatory and valid details in distance page and click on proceed button after entering comments");
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceReportingPeriodFromlbl(), writeRowNo,distancetabpage.distanceReportingPeriodFrom());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceReportingPeriodTolbl(), writeRowNo,distancetabpage.distanceReportingPeriodTo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceUsdotNbrlbl(), writeRowNo,distancetabpage.distanceUsdotNbr());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceEstimatedDistanceChartlbl(), writeRowNo,distancetabpage.distanceEstimatedDistanceChart());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceOverrideContJurlbl(), writeRowNo,distancetabpage.distanceOverrideContJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceEstimatedDistancelbl(), writeRowNo,distancetabpage.distanceEstimatedDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceActualDistancelbl(), writeRowNo,distancetabpage.distanceActualDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceTotalFleetDistancelbl(), writeRowNo,distancetabpage.distanceTotalFleetDistance());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceFRPMlgQuetionlbl(), writeRowNo,distancetabpage.distanceFRPMlgQuetion());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceDistanceTypelbl(), writeRowNo,distancetabpage.distanceDistanceType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Distance",distancetabpage.distanceActualDistConfirmationlbl(), writeRowNo,distancetabpage.distanceActualDistConfirmation());


		//Juris Table Verification
		//Juris Table header

		ArrayList<String>  Juris_values=distancetabpage.FetchTable_Juris();
		ArrayList<String>  Distance_values=distancetabpage.FetchTable_DistanceMiles();
		ArrayList<String>  Percent_values=distancetabpage.FetchTable_Percent();

		for(int i=0;i<Juris_values.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"Distance_Juris",distancetabpage.distanceJurisTableHeaderJuri()+i, writeRowNo,Juris_values.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"Distance_Juris",distancetabpage.distanceJurisTableHeaderDistance()+i, writeRowNo,Distance_values.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"Distance_Juris",distancetabpage.distanceJurisTableHeaderPercent()+i, writeRowNo,Percent_values.get(i));
		}

		//Juris list
		String actualmessage = commonobjects.fetchErrorMessage(expSucces1);
		try {
			Assert.assertEquals(actualmessage, expSucces1);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen"+ actualmessage);

		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		ConfigReader.getLogInfo("Message in Distance Screen"+commonobjects.fetchErrorMessage(expSucces1));

		screenshotUtil.captureScreenshot(className,"Message in Distance Screen 1");

		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Distance Screen"+ actualmessage2);

		ConfigReader.getLogInfo("Message in Distance Screen"+commonobjects.fetchErrorMessage(expSucces1));

		screenshotUtil.captureScreenshot(className,"Message in Distance Screen 1");

	/*	distancetabpage.enterAllDistanceValue(excelutil.getCellData("DistanceTab","Distance",readRowNo));   	
		//distancetabpage.enterMODistanceValue(excelutil.getCellData("DistanceTab","Juri",RowNo), excelutil.getCellData("DistanceTab","Distance",RowNo));
		ConfigReader.getLogInfo("*** Enter MODistanceValue ***");
		screenshotUtil.captureScreenshot(className,"Enter MODistanceValue");
		*/
		distancetabpage.selectYesOrNo(excelutil.getCellData("DistanceTab","Actual Distance Reporting Period",readRowNo));
		commonobjects.provideComments(excelutil.getCellData("DistanceTab","Comments",readRowNo));
		ConfigReader.getLogInfo("*** Enter Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter Comments in Distance Section");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		CommonStep.scenario.log("Click on proceed button from the verification page");
		//Distance Verification Screen
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to Weight group section and fill the data ans validate message {string}")
	public void user_will_navigate_to_weight_group_section_and_fill_the_data_ans_validate_message(String expSucces) throws Exception {
		CommonStep.scenario.log("Click on Add weight group button Add new Weight Group(s) & enter all the mandatory and valid details in the weight group page and click on proceed button");
		CommonStep.scenario.log("Update an existing Weight Group & enter comments");
		
		//Fetch Values into Excel
		ArrayList<String>  headervalues=wgtgroup.fetchTableHeader();
		ArrayList<String>  RowDatavalues=wgtgroup.fetchTableRowData();
		int j,k=0;
		for(int i=0;i<RowDatavalues.size();i++) {
			if(i>5){
				j=i%6;
				if(j==0){
					k++;
				}
				excelutilWrite.setCellData(config.writeRwcExcel(),"WeightGroup",headervalues.get(j)+k, writeRowNo,RowDatavalues.get(i));
				ConfigReader.getLogInfo("Weight Group headervalues"+headervalues.get(j));
				ConfigReader.getLogInfo("Weight Group RowDatavalues"+RowDatavalues.get(i));
			}
			else if(i>0 && i<5){
			
				excelutilWrite.setCellData(config.writeRwcExcel(),"WeightGroup",headervalues.get(i)+k, writeRowNo,RowDatavalues.get(i)); 
				ConfigReader.getLogInfo("Weight Group headervalues"+headervalues.get(i));
				ConfigReader.getLogInfo("Weight Group RowDatavalues"+RowDatavalues.get(i));
			}

		}

		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Weight Group Screen"+actualmessage);


		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		ConfigReader.getLogInfo("Message in Weight Group Screen"+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Weight Group Screen 1");

		//Add Weight Group Screen
		String WeightGroupCount_Excel=excelutil.getCellData("WeightGrouptab","TotalWeightGroups",readRowNo);

		for(int weightcount=0;weightcount<Integer.valueOf(WeightGroupCount_Excel);weightcount++) {
			if(weightcount<Integer.valueOf(WeightGroupCount_Excel)) {
				wgtgroup.clickAddWeightGroup();
				ConfigReader.getLogInfo("*** Click AddweightGroup ***");
				screenshotUtil.captureScreenshot(className,"Click AddweightGroup");
			}
			wgtgroupadd.selectWeightGroupType(excelutil.getCellData("WeightGrouptab","WeightGroup Type"+String.valueOf(weightcount),readRowNo));

			ConfigReader.getLogInfo("*** Select WeightGroupType ***");
			screenshotUtil.captureScreenshot(className,"Select WeightGroupType");
			wgtgroupadd.enterWeightGroupNo(excelutil.getCellData("WeightGrouptab","Weight Group No."+String.valueOf(weightcount),readRowNo));
			ConfigReader.getLogInfo("*** Enter WeightGroup No ***");
			screenshotUtil.captureScreenshot(className,"Enter WeightGroup No");
			wgtgroupadd.selectMaxGrossWeight(excelutil.getCellData("WeightGrouptab","Max Gross Weight"+String.valueOf(weightcount),readRowNo));
			ConfigReader.getLogInfo("*** Select MaxGross Weight ***");
			screenshotUtil.captureScreenshot(className,"Select MaxGross Weight");
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			CommonStep.scenario.log("Click on proceed button from the verification page");
//Add Weight Group Verification
			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
		}

		//Weight Group Screen
	//Edit Existing Weight Group
		wgtgroup.clickHandImg();

		String juriExcelCount=excelutil.getCellData("WeightJuris","Juris Count",readRowNo);
		for(int i=0;i<Integer.valueOf(juriExcelCount);i++) {
			String juriExcel=excelutil.getCellData("WeightJuris","Juri"+String.valueOf(i),readRowNo);
			wgtgroupadd.enterWeightJuriValue(juriExcel);
		}
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		//ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		// Weight Group Verification Screen
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
//		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs());
		//Validating JUR WITH DIFFERENT WEIGHTS
		String[] weightlist=wgtgroup.validateJurisWeightsedited(); //[AL, AR, AZ]
		for(int i=0;i<Integer.valueOf(juriExcelCount);i++) {
			String Juri_Excel=excelutil.getCellData("WeightJuris","Juri"+String.valueOf(i),readRowNo);
			if(weightlist[i].equalsIgnoreCase(Juri_Excel)){
				assert true;
			}
		}

		//In Weight Group Screen
		CommonStep.scenario.log("Click Done");
		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
	}

	@Then("User will navigate to Ammend vehicle and validate the message {string} {string} {string}")
	public void user_will_navigate_to_Ammend_vehicle_and_validate_the_message(String expSucces, String expSucces2, String expSucces3) throws Exception {
		CommonStep.scenario.log("Complete Amend vehicle  and edit Ownership Details Safety USDOT and TIN and change Weight Group such that New Plate is requested Also request TVR and enter comments Delete Vehicle(s) such that all Documents are collected and Comments entered");
		//Fetch Values
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchAmendVehicleLbl(), writeRowNo,Vehicletabpage.fetchAmendVehicle());
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchAddVehiclesLbl(), writeRowNo,Vehicletabpage.fetchAddVehicles());
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchDeleteVehicleLbl(), writeRowNo,Vehicletabpage.fetchDeleteVehicle());
		excelutilWrite.setCellData(config.writeRwcExcel(),"VehicleTab",Vehicletabpage.fetchRenewVehicleLbl(), writeRowNo,Vehicletabpage.fetchRenewVehicle());


		int NoofVehiclestoAmend=Integer.valueOf(excelutil.getCellData("VehicleTab","NoofVehiclestoAmend",readRowNo));
		if(NoofVehiclestoAmend>0) {
			ConfigReader.getLogInfo("Number of Vehicles to be Amended is greater than zero Hence about to click the Amend Vehicle Radio Button");
			Vehicletabpage.clickAmendVehicleRadioButton();
			ConfigReader.getLogInfo("*** Click AmendVehicle RadioButton ***");
			screenshotUtil.captureScreenshot(className,"Click AmendVehicle RadioButton");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));

			String actualmessage = commonobjects.fetchErrorMessage(expSucces);
			try {
				Assert.assertEquals(actualmessage, expSucces);
			}
			catch(Throwable e) {
				error.addError(e);
			}
			CommonStep.scenario.log("Message in Amend Vehicle Screen"+actualmessage);

			ConfigReader.getLogInfo("Message in Amend Vehicle Screen"+commonobjects.fetchErrorMessage(expSucces));
			screenshotUtil.captureScreenshot(className,"Message in Amend Vehicle Screen 1");


			for(int i=0;i<NoofVehiclestoAmend;i++) {
				if(NoofVehiclestoAmend>Integer.valueOf(config.readAmendVehicleCondition())) {
					vehicleAmend.selectUnitNoFromSuggestions();
					ConfigReader.getLogInfo("*** Select Unit number ***");
					screenshotUtil.captureScreenshot(className,"Select Unit number");
				}
				else {
					vehicleAmend.enterUnitNo(excelutil.getCellData("VehicleAmendTab","Unit"+String.valueOf(i),readRowNo));
					ConfigReader.getLogInfo("*** Enter Unit number ***");
					screenshotUtil.captureScreenshot(className,"Enter Unit number");
				}
				vehicleAmend.clickSearch();
String vehicleBodyType=vehicleAmend.fetchAmendVehicleBodyType();

				if(vehicleBodyType.equalsIgnoreCase("CG - Converter Gear") || vehicleBodyType.equalsIgnoreCase("MT - Motor Totor")||
				   vehicleBodyType.equalsIgnoreCase("RT - Road Truck")|| vehicleBodyType.equalsIgnoreCase("TK - Straight Truck")||
				   vehicleBodyType.equalsIgnoreCase("TR - Tractor")|| vehicleBodyType.equalsIgnoreCase("TT - Truck Tractor") ||
				 vehicleBodyType.equalsIgnoreCase("WR - Wrecker")) {
					vehicleAmend.selectWeightGroupNo(excelutil.getCellData("WeightGrouptab","Vehicle_WeightGroupNo",readRowNo));

					ConfigReader.getLogInfo("*** Enter WeightGroupNo ***");
					screenshotUtil.captureScreenshot(className,"Enter WeightGroupNo");
					String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
					try {
						Assert.assertEquals(actualmessage1, expSucces2);
					}
					catch(Throwable e) {
						error.addError(e);
					}
					CommonStep.scenario.log("Message in Amend Vehicle Screen"+actualmessage1);

					ConfigReader.getLogInfo("Message in Amend Vehicle Screen"+commonobjects.fetchErrorMessage(expSucces2));
					screenshotUtil.captureScreenshot(className,"Message in Amend Vehicle Screen 2");
				}
				vehicleAmend.enterUnladenWeight(excelutil.getCellData("VehicleTab","unladenWeight",readRowNo));
				ConfigReader.getLogInfo("*** Enter UnladenWeight ***");
				screenshotUtil.captureScreenshot(className,"Enter UnladenWeight");


				vehicleAmend.clickTVR();
				ConfigReader.getLogInfo("*** Click TVR ***");
				screenshotUtil.captureScreenshot(className,"Click TVR");

				vehicleAmend.selectSafetyChangedd(excelutil.getCellData("VehicleTab","Safety Change",readRowNo));
				ConfigReader.getLogInfo("*** Select Safety Changedd ***");
				screenshotUtil.captureScreenshot(className,"Select Safety Changedd");

				vehicleAmend.selectHVUTForm2290(excelutil.getCellData("VehicleTab","HVUT - Form",readRowNo));
				ConfigReader.getLogInfo("*** Select HVUTForm2290 ***");
				screenshotUtil.captureScreenshot(className,"Select HVUTForm2290");

				vehicleAmend.selectLeaseContract(excelutil.getCellData("VehicleTab","Lease Contract",readRowNo));
				ConfigReader.getLogInfo("*** Select LeaseContract ***");
				screenshotUtil.captureScreenshot(className,"Select LeaseContract");

				vehicleAmend.selectTitleDocument(excelutil.getCellData("VehicleTab","Title Document",readRowNo));
				ConfigReader.getLogInfo("*** Select TitleDocument ***");
				screenshotUtil.captureScreenshot(className,"Select TitleDocument");

				vehicleAmend.selectPlateReturndoc(excelutil.getCellData("VehicleTab","Plate Returned Document",readRowNo));
				ConfigReader.getLogInfo("*** Select PlateReturndoc ***");
				screenshotUtil.captureScreenshot(className,"Select PlateReturndoc");

				vehicleAmend.selectAffidavitDoc(excelutil.getCellData("VehicleTab","Affidavit document",readRowNo));
				ConfigReader.getLogInfo("*** Select AffidavitDoc ***");
				screenshotUtil.captureScreenshot(className,"Select AffidavitDoc");

				vehicleAmend.selectPropertyTax(excelutil.getCellData("VehicleTab","Property Tax",readRowNo));
				ConfigReader.getLogInfo("*** Select PropertyTax ***");
				screenshotUtil.captureScreenshot(className,"Select PropertyTax");

				commonobjects.provideComments(excelutil.getCellData("VehicleTab","Ammend_Comments",readRowNo));

				ConfigReader.getLogInfo("*** Enter Comments ***");
				screenshotUtil.captureScreenshot(className,"Enter Comments in Distance Section");

				commonobjects.clickProceed();
				commonobjects.waitForSpinner();
				//Verification Screen
				commonobjects.clickProceed();
				commonobjects.waitForSpinner();
				String actualmessage2 = commonobjects.fetchErrorMessage(expSucces3);
				try {
					Assert.assertEquals(actualmessage2, expSucces3);
				}
				catch(Throwable e) {
					error.addError(e);
				}
				CommonStep.scenario.log("Message in Amend Vehicle Screen "+actualmessage2);


				ConfigReader.getLogInfo("Message in Amend Vehicle Screen "+commonobjects.fetchErrorMessage(expSucces3));
				screenshotUtil.captureScreenshot(className,"Message in Amend Vehicle Screen");

			}// End of for Loop
			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
			ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		} //End of vehicle ammend if loop
	}


	@Then("User will Delete vehicle as per the requiremnet and validate the message {string}")
	public void user_will_delete_vehicle_as_per_the_requiremnet_and_validate_the_message(String expSucces) throws Exception {
		CommonStep.scenario.log("Completed Delete Vehicle ");
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		int NoofVehiclestoDelete=Integer.valueOf(excelutil.getCellData("VehicleTab","NoOfVehiclesToDelete",readRowNo));	
		if(NoofVehiclestoDelete>0) {
			ConfigReader.getLogInfo("Number of Vehicles to be deleted is greater than zero Hence about to click the Delete Vehicle Radio Button");
			Vehicletabpage.clickDeleteVehicleRadioButton();
			ConfigReader.getLogInfo("**** Click DeleteVehicle ****");
			screenshotUtil.captureScreenshot(className,"Click Delete Vehicle");

			commonobjects.clickProceed();
			commonobjects.waitForSpinner();
			ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
			//below lines of code for deleting the vehicles from suggestion box
			String Vehiclescount=excelutil.getCellData("VehicleTab","NoOfVehiclesToDelete",readRowNo);
			String PlateStatus=excelutil.getCellData("VehicleTab","Delete_PlateStatus",readRowNo);
			String PlateReturnedDocument=excelutil.getCellData("VehicleTab","Delete_PlateReturnedDocument",readRowNo);
			String AffidavitDocument=excelutil.getCellData("VehicleTab","Delete_AffidavitDocument",readRowNo);
			String Comments=excelutil.getCellData("VehicleTab","Delete_Comments",readRowNo);

			if(NoofVehiclestoDelete>Integer.valueOf(config.readDeleteVehicleCondition())) {
				vehicleDelete.deleteFewVehicles(Vehiclescount, PlateStatus, PlateReturnedDocument, AffidavitDocument, Comments);
				commonobjects.clickProceed();
				commonobjects.waitForSpinner();
				commonobjects.clickProceed();
				commonobjects.waitForSpinner();
			}
			else {

				for(int j=0;j<NoofVehiclestoDelete;j++) {
					//Enter unit # to filter & delete
					vehicleDelete.enterUnitNo(excelutil.getCellData("VehicleDeleteTab","Unit"+String.valueOf(j),readRowNo));
					ConfigReader.getLogInfo("*** Delete vehicle Enter Unit number ***");
					screenshotUtil.captureScreenshot(className,"Delete vehicle Enter Unit number");

					vehicleDelete.clickonSearch();
					vehicleDelete.ClickCheckBoxFromGrid();

					vehicleDelete.selectPlateStatus(PlateStatus);
					ConfigReader.getLogInfo("***DeleteVehicle Select PlateStatus ***");
					screenshotUtil.captureScreenshot(className,"DeleteVehicle Select PlateStatus");

					vehicleDelete.selectPlateReturnedDocument(PlateReturnedDocument);
					ConfigReader.getLogInfo("***DeleteVehicle Select PlateReturndoc ***");
					screenshotUtil.captureScreenshot(className,"DeleteVehicle Select PlateReturndoc");

					vehicleDelete.selectAffidavitDocument(AffidavitDocument);
					ConfigReader.getLogInfo("***DeleteVehicle Select AffidavitDoc ***");
					screenshotUtil.captureScreenshot(className,"DeleteVehicle Select AffidavitDoc");


					vehicleDelete.entercomments(Comments);
					ConfigReader.getLogInfo("*** DeleteVehicle Comment ***");
					screenshotUtil.captureScreenshot(className,"DeleteVehicle Comment");

					commonobjects.clickProceed();
					commonobjects.waitForSpinner();
					commonobjects.clickProceed();
					commonobjects.waitForSpinner();
				}//End of For Loop
			} //End of if loop
			String actualmessage = commonobjects.fetchErrorMessage(expSucces);
			try {
				Assert.assertEquals(actualmessage, expSucces);
			}
			catch(Throwable e) {
				error.addError(e);
			}
			CommonStep.scenario.log("Message in Delete Vehicle Screen"+actualmessage);

			ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
			ConfigReader.getLogInfo("Message in Delete Vehicle Screen"+commonobjects.fetchErrorMessage(expSucces));
			screenshotUtil.captureScreenshot(className,"Message in Delete Vehicle Screen 1");
			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
			ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		}//End of vehicle Delete if loop
	}
	@Then("User will navigate to vehicle list to cancel the existing amended vehicle and amend it again {string}")
	public void user_will_navigate_to_vehicle_list_to_cancel_the_existing_amended_vehicle_and_amend_it_again(String expSucces) throws Exception {
	//verification screen before going to billing screen -Vehicles cancel from Vehicle List
	
		int NoofVehiclestoAmend=Integer.valueOf(excelutil.getCellData("VehicleTab","NoofVehiclestoAmend",readRowNo));
		if(NoofVehiclestoAmend>0) {
			CommonStep.scenario.log("Go to Vehicle List and update Amended  Vehicle details");
			ConfigReader.getLogInfo("Number of Vehicles to be Amended is greater than zero Hence about to click the Vehicle List");
		Vehicletabpage.clickVehicleList();
		ConfigReader.getLogInfo("*** Click VehicleList ***");
		screenshotUtil.captureScreenshot(className,"Click VehicleList");
	
		cancelAmendedVehicle=vehiclelist.fetchRequiredUnitNumber("AMEND");
		ConfigReader.getLogInfo("Cancelling the recently Amended Vehicle"+cancelAmendedVehicle);
		
		if(NoofVehiclestoAmend>Integer.valueOf(config.readAmendVehicleCondition())) {
			//Canceling the Amended Vehicle
			//	vehicleadd.enterUnitNumber(excelutil.getCellData("VehicleAmendTab","Unit0",RowNo));
			vehicleadd.enterUnitNumber(cancelAmendedVehicle);
		}
		else {
			vehicleadd.enterUnitNumber(excelutil.getCellData("VehicleAmendTab","Unit3",readRowNo));
		}
		ConfigReader.getLogInfo("*** Select Unit No ***");
		screenshotUtil.captureScreenshot(className,"Select Unit No");

		vehicleAmend.clickSearch();
		ConfigReader.getLogInfo("*** Search Unit No ***");
	Thread.sleep(2000);	
		vehicleadd.selectFirstHandIcon();
		vehicleadd.selectWeightGroupNumber(excelutil.getCellData("VehicleUpdate","WeightGroup Number",readRowNo));
		vehicleadd.selectColor(excelutil.getCellData("VehicleTab","Vehicle_Color",readRowNo));
		vehicleAmend.selectSafetyChangedd(excelutil.getCellData("VehicleUpdate","Safety Change",readRowNo));

		vehicleAmend.selectHVUTForm2290(excelutil.getCellData("VehicleUpdate","HVUT - Form 2290",readRowNo));

		vehicleAmend.selectLeaseContract(excelutil.getCellData("VehicleUpdate","Lease Contract",readRowNo));

		vehicleAmend.selectTitleDocument(excelutil.getCellData("VehicleUpdate","Title Document",readRowNo));

		vehicleAmend.selectPlateReturndoc(excelutil.getCellData("VehicleUpdate","Plate Returned Document",readRowNo));

		vehicleAmend.selectAffidavitDoc(excelutil.getCellData("VehicleUpdate","Affidavit document",readRowNo));

		vehicleAmend.selectPropertyTax(excelutil.getCellData("VehicleUpdate","Property Tax",readRowNo));

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		//AMend Verification Screen
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		//To move from Amend Screen to Renewal Vehicle Processing
		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
		}
	}
		@Then("User will navigate to vehicle list to cancel the existing deleted vehicle and delete it again {string}")
		public void user_will_navigate_to_vehicle_list_to_cancel_the_existing_deleted_vehicle_and_delete_it_again(String expSucces) throws Exception {
		//Canceling the Deleted Vehicle
			int NoofVehiclestoDelete=Integer.valueOf(excelutil.getCellData("VehicleTab","NoOfVehiclesToDelete",readRowNo));
			if(NoofVehiclestoDelete>0) {
				CommonStep.scenario.log("Go to Vehicle List and update/cancel  Deleted Vehicle details");
				ConfigReader.getLogInfo("Number of Vehicles to be deleted is greater than zero Hence about to click the Vehicle List");
			Vehicletabpage.clickVehicleList();
				ConfigReader.getLogInfo("*** Click VehicleList ***");
				screenshotUtil.captureScreenshot(className,"Click VehicleList");
			
				cancelDeletedVehicle=vehiclelist.fetchRequiredUnitNumber("DELETE");
				if(NoofVehiclestoDelete>Integer.valueOf(config.readDeleteVehicleCondition())) {
					ConfigReader.getLogInfo("Cancelling the recently Deleted Vehicle"+cancelDeletedVehicle);
					vehicleadd.enterUnitNumber(cancelDeletedVehicle);
				}
				else {
					vehicleadd.enterUnitNumber(excelutil.getCellData("VehicleDeleteTab","Unit0",readRowNo));	
				}
				ConfigReader.getLogInfo("*** Select Unit No ***");
				screenshotUtil.captureScreenshot(className,"Select Unit No");

				vehicleAmend.clickSearch();
				ConfigReader.getLogInfo("*** Search Unit No ***");
				screenshotUtil.captureScreenshot(className,"Search Unit No");
				//	}
				vehicleadd.selectFirstHandIcon();
				commonobjects.ClickConfirmCancel();
				eleutil.handleAlert();
				ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));

				String actualmessage = commonobjects.fetchErrorMessage(expSucces);
				try {
					Assert.assertEquals(actualmessage, expSucces);

				}
				catch(Throwable e) {
					error.addError(e);
				}
				CommonStep.scenario.log("Message in Vehicle Cancel Screen"+ actualmessage);


				ConfigReader.getLogInfo("Message in Vehicle Cancel Screen"+commonobjects.fetchErrorMessage(expSucces)); 
				screenshotUtil.captureScreenshot(className,"Message in Vehicle Cancel Screen 1");

				commonobjects.clickBack();
				ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));

		//Delete the recently cancelled vehicle
		Vehicletabpage.clickDeleteVehicleRadioButton();
		ConfigReader.getLogInfo("**** Click DeleteVehicle ****");
		screenshotUtil.captureScreenshot(className,"Click Delete Vehicle");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		//below lines of code for deleting the vehicles from suggestion box
		String plateStatus=excelutil.getCellData("VehicleTab","Delete_PlateStatus",readRowNo);
		String plateReturnedDocument=excelutil.getCellData("VehicleTab","Delete_PlateReturnedDocument",readRowNo);
		String affidavitDocument=excelutil.getCellData("VehicleTab","Delete_AffidavitDocument",readRowNo);
		String Comments=excelutil.getCellData("VehicleTab","Delete_Comments",readRowNo);
		ConfigReader.getLogInfo("Deleting the recently Cancelled Vehicle"+cancelDeletedVehicle);
		vehicleDelete.enterUnitNo(cancelDeletedVehicle);

		//vehicleDelete.enterUnitNo(excelutil.getCellData("VehicleDeleteTab","Unit0",RowNo));
		ConfigReader.getLogInfo("*** Delete vehicle Enter Unit number ***");
		screenshotUtil.captureScreenshot(className,"Delete vehicle Enter Unit number");

		vehicleDelete.clickonSearch();
		vehicleDelete.ClickCheckBoxFromGrid();

		vehicleDelete.selectPlateStatus(plateStatus);
		ConfigReader.getLogInfo("***DeleteVehicle Select PlateStatus ***");
		screenshotUtil.captureScreenshot(className,"DeleteVehicle Select PlateStatus");

		vehicleDelete.selectPlateReturnedDocument(plateReturnedDocument);
		ConfigReader.getLogInfo("***DeleteVehicle Select PlateReturndoc ***");
		screenshotUtil.captureScreenshot(className,"DeleteVehicle Select PlateReturndoc");

		vehicleDelete.selectAffidavitDocument(affidavitDocument);
		ConfigReader.getLogInfo("***DeleteVehicle Select AffidavitDoc ***");
		screenshotUtil.captureScreenshot(className,"DeleteVehicle Select AffidavitDoc");


		vehicleDelete.entercomments(Comments);
		ConfigReader.getLogInfo("*** DeleteVehicle Comment ***");
		screenshotUtil.captureScreenshot(className,"DeleteVehicle Comment");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
		//TO move from Renewal Vehicle Processing to Billing Screen						

		commonobjects.clickDoneBtn();
		commonobjects.waitForSpinner();
			}

	}
	@Then("User will navigate to billing to verify as well to adjust the cost & Waive Fees {string}")
	public void user_will_navigate_to_billing_to_verify_as_well_to_adjust_the_cost_waive_fees(String expSucces) throws Exception {
	
		if(eleutil.getTitle().trim().equalsIgnoreCase("Vehicle Renewal Entry - IRP")) {
			ConfigReader.getLogInfo("Screen is in Vehicle Renewal Entry Screen");
			commonobjects.clickDoneBtn();
			commonobjects.waitForSpinner();
		}
		CommonStep.scenario.log("Click Done from the supplement selection page");
		//Fetch values from Biling Screen
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchRegisterMonthlbl(),writeRowNo,billingtab.fetchRegisterMonth());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchNoOfVehiclesinSupplbl(),writeRowNo,billingtab.fetchNoOfVehiclesinSupp());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchSupplementStatuslbl(),writeRowNo,billingtab.fetchSupplementStatus());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchEnterpriseSystemCreditlbl(),writeRowNo,billingtab.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchIRPSystemCreditlbl(),writeRowNo,billingtab.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchRenewalFeeEffectiveDatelbl(),writeRowNo,billingtab.fetchRenewalFeeEffectiveDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchInvoiceDatelbl(),writeRowNo,billingtab.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchApplicationReceiptDatelbl(),writeRowNo,billingtab.fetchApplicationReceiptDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchPaymentDatelbl(),writeRowNo,billingtab.fetchPaymentDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchExchangeRatelbl(),writeRowNo,billingtab.fetchExchangeRate());

		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchManualAdjBaseJurlbl(),writeRowNo,billingtab.fetchManualAdjBaseJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBilling_BatchBillinglbl(),writeRowNo,billingtab.fetchBilling_BatchBilling());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBilling_TVRlbl(),writeRowNo,billingtab.fetchBilling_TVR());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBilling_InstallmentPlanlbl(),writeRowNo,billingtab.fetchBilling_InstallmentPlan());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBilling_IsUseOneTimeMailinglbl(),writeRowNo,billingtab.fetchBilling_IsUseOneTimeMailing());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchElectronicDeliveryTypelbl(),writeRowNo,billingtab.fetchElectronicDeliveryType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing","Email",writeRowNo,billingtab.fetchBilling_Email());
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchInvoiceReportTypelbl(),writeRowNo,billingtab.fetchBilling_InvoiceReportType());

		ArrayList<String> TableFeeType=billingtab.FetchTable_FeeType();
		ArrayList<String> TableFeeAmount=billingtab.FetchTable_FeeAmount();

		for(int i=0;i<TableFeeType.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"BillingGrid",billingtab.fetchBillingGridFeeType()+i,writeRowNo,TableFeeType.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"BillingGrid",billingtab.fetchBillingGridFeeAmount()+i,writeRowNo,TableFeeAmount.get(i));
		}	

		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));

		billingtab.clickTVR();
		ConfigReader.getLogInfo("*** Click TVR ***");
		screenshotUtil.captureScreenshot(className,"Click TVR");
		
		excelutilWrite.setCellData(config.writeRwcExcel(),"Billing",billingtab.fetchBillingTVRNoOfDayslbl(),writeRowNo,billingtab.fetchBilling_TVRNoOfDays());
		 installmentPlanCheckStatus=billingtab.fetchBilling_InstallmentPlan();
		 CommonStep.scenario.log("Check Installment Payment checkbox");
		 ConfigReader.getLogInfo("*** Installment Plan Check Box Status ***"+installmentPlanCheckStatus); //Before clciking should returns false
		 billingtab.clickInstallmentPlan();
		ConfigReader.getLogInfo("*** Click Installement Plan ***");
		screenshotUtil.captureScreenshot(className,"Click Installement Plan");

		billingtab.selectElectronicDeliveryType(excelutil.getCellData("BillingTab","Electronic Delivery Type",readRowNo));
		ConfigReader.getLogInfo("*** Select Electronic DeliveryType ***");
		screenshotUtil.captureScreenshot(className,"Select Electronic DeliveryType");

		commonobjects.provideComments(excelutil.getCellData("BillingTab","Billing_Comments",readRowNo));
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
//check the Installment Plan is checked or not
		 installmentPlanCheckStatus=billingtab.fetchBilling_InstallmentPlan();
		ConfigReader.getLogInfo("*** Installment Plan Check Box Status ***"+installmentPlanCheckStatus); //After clicking should returns true
		CommonStep.scenario.log("Manually Adjust & Waive Fees with comments & set Delivery Types as PDF");
		billingtab.enterManualAdjBaseJur(excelutil.getCellData("BillingTab","Manual Adj. Base Jur.",readRowNo));
		ConfigReader.getLogInfo("*** Enter ManualAdjBaseJur ***");
		screenshotUtil.captureScreenshot(className,"Enter ManualAdjBaseJur");

		billingtab.clickReCalculate();
		ConfigReader.getLogInfo("*** Click Recalculate ***");
		screenshotUtil.captureScreenshot(className,"Click Recalculate");
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Biling Screen"+ actualmessage);

		ConfigReader.getLogInfo("Message in Biling Screen"+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Biling Screen 1");

		billingtab.expandManualAdjReason();
		billingtab.enterManualAdjReasonComments(excelutil.getCellData("BillingTab","ManualReason",readRowNo));
		ConfigReader.getLogInfo("*** Enter ManualAdjReason Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter ManualAdjReason Comments");
		billingtab.clickManualAdjReasonDeleteAllowed();
		billingtab.clickManualAdjReasonAddorUpdateComments();
		CommonStep.scenario.log("Click on proceed button");
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		//Check whether Late Filing Penalty,Late Pay Penalty are greater than zero
		//Bicentennial Fee,Grade Crossing Fee,Replacement Plate Fee,Second Plate Fee,Late Filing Penalty,Late Pay Penalty,Transfer Fee,Transfer Revenue Fee,Wire Transfer Fee
		//S - System Error,M - MCE Agent Error,D - Natural Disaster,O - Other
		boolean LateFilingPenaltyAmount=billingtab.getAmount(excelutil.getCellData("BillingTab","Late Filing Penalty",readRowNo));
		ConfigReader.getLogInfo("Late Filing Penalty Amount is Greater than zero - "+LateFilingPenaltyAmount);
		boolean LatePayPenaltyAmount=billingtab.getAmount(excelutil.getCellData("BillingTab","Late Pay Penaltys",readRowNo));
		ConfigReader.getLogInfo("Late pay Penalty Amount is Greater than zero - "+LatePayPenaltyAmount);
		if(LateFilingPenaltyAmount==true &&LatePayPenaltyAmount==true) {
			String FeeDescriptiontoWaiveOff=excelutil.getCellData("BillingTab","Grade Crossing Fee",readRowNo);
			String FeeWaiveOffReason=excelutil.getCellData("BillingTab","Grade Crossing Fee Waive Off",readRowNo);
			billingtab.selectWaiveOff(FeeDescriptiontoWaiveOff,FeeWaiveOffReason);
			ConfigReader.getLogInfo("*** Waived off for "+FeeDescriptiontoWaiveOff +"  "+" with Reason "+ FeeWaiveOffReason + " ***");
			screenshotUtil.captureScreenshot(className,"Waived off Fee");
		}
		billingtab.clickReCalculate();
		ConfigReader.getLogInfo("*** Click Recalculate ***");
		screenshotUtil.captureScreenshot(className,"Click Recalculate");
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		billingtab.expandFeeOverrideReason();

		billingtab.enterFeeOverrideReasonComments(excelutil.getCellData("BillingTab","FeeOverrideReasonComments",readRowNo));

		ConfigReader.getLogInfo("*** Enter FeeOverrideReason Comments ***");
		screenshotUtil.captureScreenshot(className,"Enter FeeOverrideReason Comments");

		billingtab.clickFeeOverrideReasonDeleteAllowed();
		billingtab.clickFeeOverrideReasonAddorUpdateComments();
Thread.sleep(2000);
		billingtab.clickReCalculate();
		//ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));

		String mainwidow=eleutil.GetParentWindow();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		eleutil.waitForTwoWindow(2);
		String childWindow=eleutil.SwitchtoFirstChildWindow();
		eleutil.saveAsFile();
		String fileLocation=System.getProperty("user.dir")+"\\"+config.readDownloadFolder()+className+"\\";
		ConfigReader.getLogInfo("fileLocation"+fileLocation);
		String DesiredPath=eleutil.checkFileExistence(fileLocation,"Billing","pdf");
		eleutil.sleepTime(4000); //to wait for the PDF Load completely
		eleutil.uploadFile(DesiredPath);
		eleutil.sleepTime(4000); //to display the file upload is completed
		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(mainwidow);
	}

	@Then("User will navigate to Payment Tab to input the data and validate message {string} {string} {string}")
	public void user_will_navigate_to_payment_tab_to_input_the_data_and_validate_message(String expSucces, String expSucces2, String expSucces3) throws Exception {
		CommonStep.scenario.log("Verify the page & set Delivery Types as PDF Click on proceed button");
		//Fetch Values from Cart payment
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchEnterpriseSystemCreditlbl(),writeRowNo,paymenttab.FetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchIRPSystemCreditlbl(),writeRowNo,paymenttab.FetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchInvoiceDatelbl(),writeRowNo,paymenttab.FetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchInvoiceNumberlbl(),writeRowNo,paymenttab.FetchInvoiceNumber());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchPaymentReciptDatelbl(),writeRowNo,paymenttab.FetchPaymentReciptDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchManualAdjBaseJurlbl(),writeRowNo,paymenttab.FetchManualAdjBaseJur());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchBatchCredentiallbl(),writeRowNo,paymenttab.FetchBatchCredential());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchWireTransferFeelbl(),writeRowNo,paymenttab.FetchWireTransferFee());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchElectronicDeliveryTypelbl(),writeRowNo,paymenttab.FetchElectronicDeliveryType());
	
		ArrayList<String> Payment_TableFeeType=paymenttab.FetchTable_FeeType();
		ArrayList<String> Payment_TableFeeAmount=paymenttab.FetchTable_FeeAmount();
		for(int i=0;i<Payment_TableFeeType.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchHeaderFeeType()+i,writeRowNo,Payment_TableFeeType.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentTab",paymenttab.FetchHeaderFeeAmount()+i,writeRowNo,Payment_TableFeeAmount.get(i));
		}

		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen"+ expSucces);

		ConfigReader.getLogInfo("Message in Payment Screen"+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 1");


		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen"+ expSucces2);

		ConfigReader.getLogInfo("Message in Payment Screen"+ commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 2");
		String actualmessage3 = commonobjects.fetchErrorMessage(expSucces3);
		try {
			Assert.assertEquals(actualmessage3, expSucces3);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen"+ expSucces3);

		ConfigReader.getLogInfo("Message in Payment Screen"+commonobjects.fetchErrorMessage(expSucces3));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 3");
		pay.selectElectronicDeliverytype(excelutil.getCellData("Payment","ElectronicDeliveryType",readRowNo));
		ConfigReader.getLogInfo("***Select Delivery type***");
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		//ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));

		//Navigates to Payment Verification Screen
		CommonStep.scenario.log("Verify the page & Click Add to Cart button");
		paymenttab.clickAddtoCart();
		ConfigReader.getLogInfo("***Click Add to Cart**");
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		commonobjects.validateInfoMsgs();
	}

	@Then("User will navigate to supplement continuance and validate the meesage {string}")
	public void user_will_navigate_to_supplement_continuance_and_validate_the_meesage(String expSucces) throws Exception, Exception {
		CommonStep.scenario.log("Go to Supplment Continuance & try to continue above record");
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		paymenttab.clicksupplcont();
		ConfigReader.getLogInfo("***Click Supplement continue***");
		screenshotUtil.captureScreenshot(className,"Click Supplement continue");
		fleetpage.enterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
		commonobjects.clickProceed(); 
		commonobjects.waitForSpinner();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Fleet Screen"+ expSucces);
		ConfigReader.getLogInfo("Message in Fleet Screen"+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Fleet Screen");
	}

	@Then("User will navigate to payment tab and fill the requirement")
	public void user_will_navigate_to_payment_tab_and_fill_the_requirement() throws Exception, Exception {
		CommonStep.scenario.log("Click Cart icon");
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		paymenttab.clickVerifyAddToCart();

		ConfigReader.getLogInfo("***Verify Cart***");
		screenshotUtil.captureScreenshot(className,"Verify Cart");
		CommonStep.scenario.log("Select record & proceed");
		CommonStep.scenario.log("Click on pay now button");
		pay.clickPayNow();  

		ConfigReader.getLogInfo("***Click Paynow***");
		screenshotUtil.captureScreenshot(className,"Click Paynow");
		commonobjects.clickProceed();
		CommonStep.scenario.log("Click Proceed");
		commonobjects.waitForSpinner();
	//	ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		//Fetch Values from 
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchMCECustomerIdLbl(),writeRowNo,pay.fetchMCECustomerId());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchLegalNameLbl(),writeRowNo,pay.fetchLegalName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchInvoiceDateLbl(),writeRowNo,pay.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchEnterpriseSystemCreditLbl(),writeRowNo,pay.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchIRPSystemCreditLbl(),writeRowNo,pay.fetchIRPSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchIftaSystemCreditLbl(),writeRowNo,pay.fetchIftaSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchOpaSystemCreditLbl(),writeRowNo,pay.fetchOpaSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",pay.fetchTotalAmountDueLbl(),writeRowNo,pay.fetchTotalAmountDue());

		ArrayList<String> TableHeadervalues=pay.fetchTableHeaders();
		ArrayList<String> Table_Invoice=pay.fetchTableInvoiceDetails();
		for(int i=0;i<Table_Invoice.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"PaymentScreen",TableHeadervalues.get(i),writeRowNo,Table_Invoice.get(i));
		}

		String paymentExcelCount=excelutil.getCellData("Payment","payment Count",readRowNo);
	//	paymenttab.clickPaymenToAdd();
		CommonStep.scenario.log("Select multiple payment types and select PDF delivery type and click on proceed button");
		for(int i=0; i<Integer.valueOf(paymentExcelCount);i++) {
			
			ConfigReader.getLogInfo("no.of rows present is:"+pay.fetchTableRowsize());
			if(pay.fetchTableRowsize()<Integer.valueOf(paymentExcelCount)) {
				paymenttab.clickPaymenToAdd();
				}
			String PaymentType=excelutil.getCellData("Payment","PaymentType"+i,readRowNo);
			String PaymentNumberValue=excelutil.getCellData("Payment","PaymentChequeNo",readRowNo);
			pay.selectPaymentType(i,PaymentType);	
			ConfigReader.getLogInfo("***Payment Type***");
			screenshotUtil.captureScreenshot(className,"Payment Type"+i);


			pay.enterpaymentNoBasedonType(i,PaymentNumberValue);
			ConfigReader.getLogInfo("***Payment Number based on Payment Type***");
			screenshotUtil.captureScreenshot(className,"Payment Number based on  Payment Type"+i);

			String totalAmount=pay.FetchTotalAmount();
			ConfigReader.getLogInfo("totalAmount is "+totalAmount);
			String cashAmount=String.format("%.2f",(Double.valueOf(totalAmount)/Integer.valueOf(paymentExcelCount)));
			if(i==(Integer.valueOf(paymentExcelCount)-1)) {
				String RemainingAmount=pay.fetchRemainingBalance();
				ConfigReader.getLogInfo("Remaining balance is:"+RemainingAmount);
				pay.enterPaymentAmountBasedonType(i,RemainingAmount);
			}
			else {
				pay.enterPaymentAmountBasedonType(i,cashAmount);
			}

			ConfigReader.getLogInfo("***Payment Amount based on Payment Type***");
			screenshotUtil.captureScreenshot(className,"Payment Amount based on  Payment Type"+i);
		}

		pay.selectPaymentReceipt(excelutil.getCellData("Payment","Payment receipt",readRowNo));
		ConfigReader.getLogInfo("***Enter Payment type and amount***");
		String mainwidow=eleutil.GetParentWindow();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();

		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		eleutil.waitForTwoWindow(2);
		String childWindow=eleutil.SwitchtoFirstChildWindow();
		eleutil.saveAsFile();
		String fileLocation=System.getProperty("user.dir")+"\\"+config.readDownloadFolder()+className+"\\";
		ConfigReader.getLogInfo("fileLocation"+fileLocation);
		String DesiredPath=eleutil.checkFileExistence(fileLocation,"Payment","pdf");
		eleutil.sleepTime(4000);  //to wait for the PDF Load completely
		eleutil.uploadFile(DesiredPath);
		eleutil.sleepTime(4000); //to display the file upload is completed

		eleutil.closeSpecificWindow(childWindow);
		eleutil.SwitchSpecificWindow(mainwidow);
	}
	@Then("user will validate message {string} {string}")
	public void user_will_validate_message(String expSucces, String expSucces2) throws Exception {
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen "+ expSucces);

		ConfigReader.getLogInfo("Message in Payment Screen "+commonobjects.fetchErrorMessage(expSucces));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 1");

		String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage1, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Payment Screen "+ expSucces2);
		ConfigReader.getLogInfo("Message in Payment Screen "+commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className,"Message in Payment Screen 2");
	}

	@Then("User navigate to inventory tab to input the data and validate the message {string} {string}")
	public void user_navigate_to_inventory_tab_to_input_the_data_and_validate_the_message(String expSucces, String expSucces2) throws Exception {
		CommonStep.scenario.log("Check Inventory ");
		inventorypage.clickOperation();
		inventorypage.clickOnInventory();
		inventorypage.clickNewInventory();
		ConfigReader.getLogInfo("***Click Inventory***");
		screenshotUtil.captureScreenshot(className,"Click Inventory");
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		inventorypage.selectNewInventoryType(excelutil.getCellData("InventoryTab","Inventory_Newintype",readRowNo));
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Inventory Screen"+ expSucces);


		ConfigReader.getLogInfo("Message in Inventory Screen"+commonobjects.fetchErrorMessage(expSucces));

		screenshotUtil.captureScreenshot(className,"Message in Inventory Screen");

		inventorypage.selectNewSubInventoryType(excelutil.getCellData("InventoryTab","Inventory_Subtype",readRowNo));

		inventorypage.enterFromNo(excelutil.getCellData("InventoryTab","FromNo",readRowNo)); //modify

		inventorypage.enterQuantity(excelutil.getCellData("InventoryTab","Quantity",readRowNo));

		inventorypage.enterYear(excelutil.getCellData("InventoryTab","Year",readRowNo));


		ConfigReader.getLogInfo("***Enter details for new inventory***");
		screenshotUtil.captureScreenshot(className,"Enter details for new inventory");
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);

		}
		catch(Throwable e) {
			error.addError(e);
		}

		CommonStep.scenario.log("Message in Payment Screen"+ expSucces2);

		ConfigReader.getLogInfo("Message in Payment Screen"+commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className,"Message in Inventory Screen 2");
		commonobjects.clickQuit();
	}
	@Then("Assign the inventory to proceed further {string} {string}")
	public void assign_the_inventory_to_proceed_further(String expSucces, String expSucces2) throws Exception, Exception {
		CommonStep.scenario.log("Go to Inventory - Assign Invetory & add required Inventory");
		inventorypage.clickAssignInventory();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		inventorypage.selectNewInventoryType(excelutil.getCellData("InventoryTab","Inventory_Newintype",readRowNo));
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Inventory Screen "+ expSucces);

		ConfigReader.getLogInfo("Message in Inventory Screen "+commonobjects.fetchErrorMessage(expSucces));

		screenshotUtil.captureScreenshot(className,"Message in Inventory Screen 1");

		inventorypage.selectNewSubInventoryType(excelutil.getCellData("InventoryTab","Inventory_Subtype",readRowNo));

		inventorypage.enterFromNo(excelutil.getCellData("InventoryTab","FromNo",readRowNo)); //modify

		inventorypage.enterQuantity(excelutil.getCellData("InventoryTab","Quantity",readRowNo));

		inventorypage.enterYear(excelutil.getCellData("InventoryTab","Year",readRowNo));

		ConfigReader.getLogInfo("***Enter details in Assign Inventory***");
		screenshotUtil.captureScreenshot(className,"Enter details in Assign Inventory");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		String actualmessage2 = commonobjects.fetchErrorMessage(expSucces2);
		try {
			Assert.assertEquals(actualmessage2, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Inventory Screen"+ expSucces2);

		ConfigReader.getLogInfo("Message in Inventory Screen"+commonobjects.fetchErrorMessage(expSucces2));
		screenshotUtil.captureScreenshot(className,"Message in Inventory Screen 2");


		commonobjects.clickQuit();
	}

	@Then("user navigate to post payment for 2nd installment payment and fill the data and validate the message {string} {string}")
	public void user_navigate_to_post_payment_for_2nd_installment_payment_and_fill_the_data_and_validate_the_message(String expSucces, String expSucces2) throws Exception {
		CommonStep.scenario.log("Go to Inventory - Assign Invetory & add required Inventory");

		financepage.clickfinance();

		financepage.clickpostpayment();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		financepage.enterMCEid(excelutil.getCellData("PreSetup","MCENumber",readRowNo));

		financepage.clicksearch();

		financepage.clickoncartid();

		financepage.clickservice();

		financepage.clickIRP();

		dashboardpage.clickInstallmentPayment();

		financepage.clickandenterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));

		financepage.clickandenterfleet(excelutil.getCellData("PreSetup","FleetNumber",readRowNo));

		financepage.clickandenterfleetyear(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		financepage.clickgrid();

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		financepage.selectpaymenttype(excelutil.getCellData("Payment","PaymentType0",readRowNo));

		String totalAmount=pay.FetchTotalAmount();

		financepage.entercashamount(totalAmount);

		pay.selectPaymentReceipt(excelutil.getCellData("Payment","Payment receipt",readRowNo));

		ConfigReader.getLogInfo("Enter the details in installement payment and proceed");
		screenshotUtil.captureScreenshot(className,"Enter the details in installement payment and proceed");

		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		ConfigReader.getLogInfo(commonobjects.validateInfoMsgs().get(0));
		String actualmessage = commonobjects.fetchErrorMessage(expSucces);
		try {
			Assert.assertEquals(actualmessage, expSucces);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in post Payment Screen"+ expSucces);

		ConfigReader.getLogInfo("Message in post Payment Screen"+commonobjects.fetchErrorMessage(expSucces));

		screenshotUtil.captureScreenshot(className,"Message in post Payment Screen 1");

		String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);

		try {
			Assert.assertEquals(actualmessage1, expSucces2);
		}
		catch(Throwable e) {
			error.addError(e);
		}
		CommonStep.scenario.log("Message in Post Payment Screen "+ expSucces2);

		ConfigReader.getLogInfo("Message in Post Payment Screen "+commonobjects.fetchErrorMessage(expSucces2));

		screenshotUtil.captureScreenshot(className,"Message in post Payment Screen 2");

	}

	@Then("user should land on the installement payment and verify the amount and then proceed {string} {string}")
	public void user_should_land_on_the_installement_payment_and_verify_the_amount_and_then_proceed(String expSucces, String expSucces2) throws Exception {
		CommonStep.scenario.log("Go to Payment - Installment Payment & serach above record & complete 2nd Installment payment");
		if(installmentPlanCheckStatus.equalsIgnoreCase("true")) {
ConfigReader.getLogInfo("Installment Payment is required to do");

		dashboardpage.clickEnterpriseLink();
		commonobjects.waitForSpinner();
		dashboardpage.clickIRPLink();
		commonobjects.waitForSpinner();
		dashboardpage.clickInstallmentPayment();
		commonobjects.waitForSpinner();
		financepage.clickandenterAccountNo(excelutil.getCellData("PreSetup","AccountNumber",readRowNo));
		financepage.clickandenterfleet(excelutil.getCellData("PreSetup","FleetNumber",readRowNo));
		financepage.clickandenterfleetyear(excelutil.getCellData("PreSetup","Vehicle and Installment Fleet Expiration Year",readRowNo));
		
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		financepage.clickgrid();
		
		//Fetch Vallues from 
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchPostPaymentAccountNoLbl(),writeRowNo,financepage.fetchPostPaymentAccountNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchFleetNolbl(),writeRowNo,financepage.fetchFleetNo());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchLegalNamelbl(),writeRowNo,financepage.fetchLegalName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchFleetMonthYearlbl(),writeRowNo,financepage.fetchFleetMonthYear());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementNolbl(),writeRowNo,financepage.fetchSupplementNo());
		
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchDBANamelbl(),writeRowNo,financepage.fetchDBAName());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchFleetTypelbl(),writeRowNo,financepage.fetchFleetType());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementDesclbl(),writeRowNo,financepage.fetchSupplementDesc());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchUSDOTlbl(),writeRowNo,financepage.fetchUSDOT());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementEffectiveDatelbl(),writeRowNo,financepage.fetchSupplementEffectiveDate());
		
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchSupplementStatuslbl(),writeRowNo,financepage.fetchSupplementStatus());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInvoiceDatelbl(),writeRowNo,financepage.fetchInvoiceDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInvoicenolbl(),writeRowNo,financepage.fetchInvoiceno());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchEnterpriseSystemCreditlbl(),writeRowNo,financepage.fetchEnterpriseSystemCredit());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInstallment_ReceiptDatelbl(),writeRowNo,financepage.fetchInstallment_ReceiptDate());
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",financepage.fetchInstallmentPlanNumberlbl(),writeRowNo,financepage.fetchInstallmentPlanNumber());
		
	
	 
    	ArrayList<String> TableFeeType=billingtab.FetchTable_FeeType();
		ArrayList<String> TableFeeAmount=billingtab.FetchTable_FeeAmount();

		for(int i=0;i<TableFeeType.size();i++) {
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",billingtab.fetchBillingGridFeeType()+i,writeRowNo,TableFeeType.get(i));
			excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentDetails",billingtab.fetchBillingGridFeeAmount()+i,writeRowNo,TableFeeAmount.get(i));
		}	


		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		//payment Verification
		commonobjects.clickProceed();
		commonobjects.waitForSpinner();
		
//Fetch Values from 
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchMCECustomerIdLbl(),writeRowNo,pay.fetchMCECustomerId());
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchLegalNameLbl(),writeRowNo,pay.fetchLegalName());
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",financepage.fetchDBANamelbl(),writeRowNo,financepage.fetchDBAName());
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchEnterpriseSystemCreditLbl(),writeRowNo,pay.fetchEnterpriseSystemCredit());
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchIRPSystemCreditLbl(),writeRowNo,pay.fetchIRPSystemCredit());
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchIftaSystemCreditLbl(),writeRowNo,pay.fetchIftaSystemCredit());
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchOpaSystemCreditLbl(),writeRowNo,pay.fetchOpaSystemCredit());
	
	excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",pay.fetchTotalAmountDueLbl(),writeRowNo,pay.fetchTotalAmountDue());

	ArrayList<String> TableHeadervalues=pay.fetchTableHeaders();
	ArrayList<String> Table_Invoice=pay.fetchTableInvoiceDetails();
	for(int i=0;i<Table_Invoice.size();i++) {
		excelutilWrite.setCellData(config.writeRwcExcel(),"InstallmentPaymentScreen",TableHeadervalues.get(i),writeRowNo,Table_Invoice.get(i));
	}

	String paymentExcelCount=excelutil.getCellData("Installment Payment","payment Count",readRowNo);
	for(int i=0; i<Integer.valueOf(paymentExcelCount);i++) {
		ConfigReader.getLogInfo("no.of rows present is:"+pay.fetchTableRowsize());
		if(pay.fetchTableRowsize()<Integer.valueOf(paymentExcelCount)) {
			paymenttab.clickPaymenToAdd();
		}
		String PaymentType=excelutil.getCellData("Payment","PaymentType"+i,readRowNo);
		String PaymentNumberValue=excelutil.getCellData("Payment","PaymentChequeNo",readRowNo);
		pay.selectPaymentType(i,PaymentType);	
		ConfigReader.getLogInfo("***Payment Type***");
		screenshotUtil.captureScreenshot(className,"Payment Type"+i);


		pay.enterpaymentNoBasedonType(i,PaymentNumberValue);
		ConfigReader.getLogInfo("***Payment Number based on Payment Type***");
		screenshotUtil.captureScreenshot(className,"Payment Number based on  Payment Type"+i);

		String totalAmount=pay.FetchTotalAmount();
		ConfigReader.getLogInfo("totalAmount is "+totalAmount);
		String cashAmount=String.format("%.2f",(Double.valueOf(totalAmount)/Integer.valueOf(paymentExcelCount)));
		if(i==(Integer.valueOf(paymentExcelCount)-1)) {
			String RemainingAmount=pay.fetchRemainingBalance();
			ConfigReader.getLogInfo("Remaining balance is:"+RemainingAmount);
			pay.enterPaymentAmountBasedonType(i,RemainingAmount);
		}
		else {
			pay.enterPaymentAmountBasedonType(i,cashAmount);
		}

		ConfigReader.getLogInfo("***Payment Amount based on Payment Type***");
		screenshotUtil.captureScreenshot(className,"Payment Amount based on  Payment Type"+i);
	}

	pay.selectPaymentReceipt(excelutil.getCellData("Payment","Payment receipt",readRowNo));
	ConfigReader.getLogInfo("***Enter Payment type and amount***");
	String mainwidow=eleutil.GetParentWindow();
commonobjects.clickProceed();
	commonobjects.waitForSpinner();
	//Payment 
	commonobjects.clickProceed();
	commonobjects.waitForSpinner();

	String actualmessage = commonobjects.fetchErrorMessage(expSucces);
	try {
		Assert.assertEquals(actualmessage, expSucces);
	}
	catch(Throwable e) {
		error.addError(e);
	}

	CommonStep.scenario.log("Message in Installment Payment Screen "+ expSucces);

	ConfigReader.getLogInfo("Message in Installment Payment Screen "+commonobjects.fetchErrorMessage(expSucces));
	screenshotUtil.captureScreenshot(className,"Message in Installment Payment Screen 1");

	String actualmessage1 = commonobjects.fetchErrorMessage(expSucces2);
	try {
		Assert.assertEquals(actualmessage1, expSucces2);
	}
	catch(Throwable e) {
		error.addError(e);
	}
	CommonStep.scenario.log("Message in Installment Payment Screen "+ expSucces2);
	ConfigReader.getLogInfo("Message in Installment Payment Screen "+commonobjects.fetchErrorMessage(expSucces2));
	screenshotUtil.captureScreenshot(className,"Message in Installment Payment Screen 2");

eleutil.waitForTwoWindow(2);
	String childWindow=eleutil.SwitchtoFirstChildWindow();
	eleutil.saveAsFile();
	String fileLocation=System.getProperty("user.dir")+"\\"+config.readDownloadFolder()+className+"\\";
	ConfigReader.getLogInfo("fileLocation"+fileLocation);
	String DesiredPath=eleutil.checkFileExistence(fileLocation,"InstallmentPayment","pdf");
	eleutil.sleepTime(4000);  //to wait for the PDF Load completely
	eleutil.uploadFile(DesiredPath);
	eleutil.sleepTime(4000); //to display the file upload is completed

	eleutil.closeSpecificWindow(childWindow);
	eleutil.SwitchSpecificWindow(mainwidow);

}
	
	
	
	
	}
	
}


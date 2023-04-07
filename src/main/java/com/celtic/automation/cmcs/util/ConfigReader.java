package com.celtic.automation.cmcs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import com.celtic.automation.cmcs.factory.DriverFactory;

public class ConfigReader extends DriverFactory{

	public static String URL;
	String fileName="./src/test/resources/config/config.properties";
	HashMap<String,String> hs= new HashMap<String,String>();
	public  Properties prop;

	private Logger logg;
	//	 public static Logger log ;
//	Loggers logger;
	
	public ConfigReader(Logger logg) {
		this.logg=logg;
	}  
	
	/*	public void loggerConfigurator(String classname) {
			String test = classname;
			String path =System.getProperty("user.dir")+"/log/"+test;
			System.setProperty("file.path",path);
			log = LogManager.getLogger(classname);
		}*/

	public  Properties initprop() throws Exception {
		prop = new Properties();
		FileInputStream ip = null ;
	//	logger=new Loggers();
		try {
//			logger.configureLoggerSystem(new Throwable().getStackTrace()[0].getClassName());
//			logg=logger.loggingInstance();
			ip	= new FileInputStream(fileName);
			prop.load(ip);
			logg.info("Log information from config reader file "+ip);
		} catch (FileNotFoundException e) {
		//	//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
		} catch (IOException e) {
		//	//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
		}
		finally {
			ip.close();
		}
		return prop;
	}
	

	public   String readJson() throws IOException {
		try
		{
		return prop.getProperty("readjson");
		}
		catch (Exception e){
		//	//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public   String readBrowser() throws IOException {
		try
		{
			return prop.getProperty("browser");
		}
		catch (Exception e){
		//	//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public   String readLoginURL() throws IOException {
		try{
			return prop.getProperty("loginURL");         
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public   String readUserId() throws IOException {
		try{
			return prop.getProperty("userid");         
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readPswrd() throws IOException {
		try{
			return prop.getProperty("pswrd");         
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readTestSuiteDirectory() throws Exception {
		try{
			return prop.getProperty("testSuiteDirectory");         
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginEmpId() throws Exception {
		try
		{
			return prop.getProperty("LoginEmpId");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readAmendVehicleCondition() throws Exception {
		try
		{
			return prop.getProperty("AmendVehicleCondition");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readDeleteVehicleCondition() throws Exception {
		try
		{
			return prop.getProperty("DeleteVehicleCondition");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String getApplicationName() throws Exception {
		try
		{
			return prop.getProperty("ApplicationName");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readJdbcDriver() throws Exception {
		try
		{
			return prop.getProperty("ApplicationName");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String getOS() throws Exception {
		try
		{
			return prop.getProperty("OS");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public  String getEnvironment() throws Exception {
		try
		{
			return prop.getProperty("Environment");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public  String writeRwcExcel() throws Exception {
		try
		{
			return prop.getProperty("writeRWCExcel");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public   String writeRinExcel() throws Exception {
		try
		{
			return prop.getProperty("writeRINExcel");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readRwcExcel() throws Exception {
		try
		{
			return prop.getProperty("RWCexcel");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readRINexcel() throws Exception {
		try
		{
			return prop.getProperty("RINexcel");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readLoginInternalUser() throws Exception {
		try
		{
			return prop.getProperty("LoginInternalUser");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readLoginServiceProvider1() throws Exception {
		try
		{
			return prop.getProperty("LoginServiceProvider1");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginServiceProvider2() throws Exception {
		try
		{
			return prop.getProperty("LoginServiceProvider2");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginExternaluser1() throws Exception {
		try
		{
			return prop.getProperty("LoginExternaluser1");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginExternaluser2() throws Exception {
		try
		{
			return prop.getProperty("LoginExternaluser2");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}
//	public  String readPassedScreenshotFile() throws Exception {
	//ScreenshotPass=test-output\\
//		try
//		{
//			return prop.getProperty("ScreenshotPass");
//		}
//		catch (Exception e){
//			//setLoggerError("Error in init_prop"+e);
//			logg.info("Error in init_prop"+e);
//			throw e;
//		}
//	}
	public  String readDownloadFolder() throws Exception {
		try
		{
			return prop.getProperty("DownloadFolder");
		}
		catch (Exception e){
			//setLoggerError("Error in init_prop"+e);
			logg.info("Error in init_prop"+e);
			throw e;
		}
	}

}

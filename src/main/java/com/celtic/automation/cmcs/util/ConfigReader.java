package com.celtic.automation.cmcs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

	public static String URL;
	public  Properties prop;
	
 public static Logger log ;
	HashMap<String,String> hs= new HashMap<String,String>();
	String fileName="./src/test/resources/config/config.properties";
	
	public void loggerConfigurator(String classname) {
		String test = classname;
		String path =System.getProperty("user.dir")+"\\log\\"+test;
		System.setProperty("file.path.can",path);
		log = LogManager.getLogger(classname);
		log.info("Opened Browser");
	}

	public  Properties initprop() throws IOException {
		
		//String test = new Throwable().getStackTrace()[0].getClassName();
		
		

		
		prop = new Properties();
		FileInputStream ip = null ;
		try {
			ip	= new FileInputStream(fileName);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			log.error("Error in init_prop"+e);
		} catch (IOException e) {
			log.error("Error in init_prop"+e);
		}
		finally {
			ip.close();
		}
		return prop;
	}
	public static String getLogInfo(String logvalue) {
		log.info(logvalue);
		return logvalue;
	}
/*	public  String readJsonF() throws IOException {
		InputStream is =null;
		try {
			try {
				 is =Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
				prop.load(is);
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prop.getProperty("readjson");
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		finally {
			is.close();
		}
	}*/
	public void readproperty() throws IOException {
		prop=initprop();
	}
	public   String readJson() throws IOException {
		return prop.getProperty("readjson");
	}

	public   String readBrowser() throws IOException {
		try
		{
			return initprop().getProperty("browser");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public   String readLoginURL() throws IOException {
		return initprop().getProperty("loginURL");
	}

	public   String readUserId() throws IOException {
		return prop.getProperty("userid");
	}
	public   String readPswrd() throws IOException {
		return prop.getProperty("pswrd");
	}
	public  String readTestSuiteDirectory() throws Exception {
		try{
			return initprop().getProperty("testSuiteDirectory");         
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginEmpId() throws Exception {
		try
		{
			return initprop().getProperty("LoginEmpId");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readAmendVehicleCondition() throws Exception {
		try
		{
			return initprop().getProperty("AmendVehicleCondition");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readDeleteVehicleCondition() throws Exception {
		try
		{
			return initprop().getProperty("DeleteVehicleCondition");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String getApplicationName() throws Exception {
		try
		{
			return initprop().getProperty("ApplicationName");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readJdbcDriver() throws Exception {
		try
		{
			return initprop().getProperty("ApplicationName");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String getOS() throws Exception {
		try
		{
			return initprop().getProperty("OS");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String getEnvironment() throws Exception {
		try
		{
			return initprop().getProperty("Environment");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String writeRwcExcel() throws Exception {
		try
		{
			return initprop().getProperty("writeRWCExcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public   String writeRinExcel() throws Exception {
		try
		{
			return initprop().getProperty("writeRINExcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public   String readRwcExcel() throws Exception {
		try
		{
			return initprop().getProperty("RWCexcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readRINexcel() throws Exception {
		try
		{
			return initprop().getProperty("RINexcel");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readLoginInternalUser() throws Exception {
		try
		{
			return initprop().getProperty("LoginInternalUser");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

	public  String readLoginServiceProvider1() throws Exception {
		try
		{
			return initprop().getProperty("LoginServiceProvider1");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginServiceProvider2() throws Exception {
		try
		{
			return initprop().getProperty("LoginServiceProvider2");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginExternaluser1() throws Exception {
		try
		{
			return initprop().getProperty("LoginExternaluser1");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readLoginExternaluser2() throws Exception {
		try
		{
			return initprop().getProperty("LoginExternaluser2");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readPassedScreenshotFile() throws Exception {
		try
		{
			return initprop().getProperty("ScreenshotPass");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}
	public  String readDownloadFolder() throws Exception {
		try
		{
			return initprop().getProperty("DownloadFolder");
		}
		catch (Exception e){
			log.error("Error in init_prop"+e);
			throw e;
		}
	}

}
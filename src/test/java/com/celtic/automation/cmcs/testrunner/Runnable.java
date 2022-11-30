package com.celtic.automation.cmcs.testrunner;

import java.util.stream.Stream;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class Runnable extends AbstractTestNGCucumberTests {

	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws Throwable{
		try {
		cucumber.api.cli.Main.main(
		new String[]{
		"--glue", "com/celtic/automation/cmcs",
		"src/test/resources/parallel/RWC001.feature",
		"-p","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"-p","timeline:test-output-thread/",
		"-p","rerun:target/failedrerun.txt",
		"-p","json:target/cucumber-reportreport.json"			
		}
		);
		}
		catch(Exception e) {
		System.out.println("Main method exception");
		}
		}
	}
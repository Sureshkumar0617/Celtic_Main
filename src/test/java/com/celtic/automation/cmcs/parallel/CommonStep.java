package com.celtic.automation.cmcs.parallel;


import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CommonStep {
	   
    //helps to generate the logs in test report.
	public static Scenario scenario;
	public static String scenarioName;
	
	@Before
	public void setUp(Scenario scenario){
		CommonStep.scenario=scenario;
		 scenarioName=scenario.getName();
	}
    
	 public static String getScenarioName() {
			return scenarioName;
		}

}

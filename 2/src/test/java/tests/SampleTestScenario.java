package tests;

import java.util.HashMap;

import org.testng.annotations.Test;

import master.MasterWrapper;
import utilities.DataProviderUtils;

public class SampleTestScenario extends MasterWrapper {
//Creating new employee and project with given mandatory filed 
	@Test(dataProvider = "TestScenario1", dataProviderClass = DataProviderUtils.class,priority = 1)
	public void Login(HashMap<String, String> testData) throws Throwable {
		weblogin.ValidUserLogin(testData);
	}

	@Test(dataProvider = "TestScenario1", dataProviderClass = DataProviderUtils.class,priority = 2)
	public void LibraryFunctionality(HashMap<String, String> testData) throws Throwable {
		weblogin.ValidUserLogin(testData)
		.navigateToLibraryPage()
		.DisplayAddVideofunctions()
		.CheckAddVideoFunction()
		.CategoryfieldChange();
//		.deleteOption()
//		.AddvideoByNotFilling()
//		.AddvideobyfillingDetails()
//		.AddvideobyfillingDetailsWithoutVideo()
//		.CancelAddVideo()
//		.AddvideobyfillingDetailsWithVideo();
		
	}

}

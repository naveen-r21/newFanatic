package utilities;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

public final class DataProviderUtils {

	@DataProvider(name = "TestScenario1")
	public static Object[][] getScenarioDetails1(Method method) {
		Map<String, HashMap<String, String>> data;
		data = ExcelUtils.readExcelData("TestScenario1");
		
		return new Object[][] { { data.get(method.getName()) } };
	}
	
	@DataProvider(name = "TestScenario2")
	public static Object[][] getScenarioDetails2(Method method) {
		Map<String, HashMap<String, String>> data;
		data = ExcelUtils.readExcelData("TestScenario2");
		
		return new Object[][] { { data.get(method.getName()) } };
	}
	
	@DataProvider(name = "TestScenario3")
	public static Object[][] getScenarioDetails3(Method method) {		
		Map<String, String> jsonMap;
		jsonMap=JSONUtils.readJsonData("TestScenario3",method.getName());
				
		return new Object[][] { { jsonMap } };
	}

}

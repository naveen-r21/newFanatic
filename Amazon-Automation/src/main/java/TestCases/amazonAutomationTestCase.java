package TestCases;

import org.testng.annotations.Test;

import TestBase.baseTest;
import TestScripts.amazonAutomationScript;

public class amazonAutomationTestCase extends baseTest {

	amazonAutomationScript TestScript = new amazonAutomationScript();


	@Test(priority=1)
	public void TestCase1_AddMenHat() throws Exception {

		TestScript.addMenHat();

	}
	@Test(priority=2)
	public void TestCase2_AddWomenHat() throws Exception {

		TestScript.addWomenHat();

	}
	@Test(priority=3)
	public void TestCase3_SumOfHats() throws Exception {

		TestScript.sumOfMenAndWomenHat();

	}

}

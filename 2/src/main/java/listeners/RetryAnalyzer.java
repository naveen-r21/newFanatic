package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import constants.FrameworkConstants;
import frameworkEnums.ConfigProperties;
import utilities.PropertyUtils;

public final class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;

	@Override
	public boolean retry(ITestResult result) {
		boolean value = false;

		if (PropertyUtils.get(ConfigProperties.RETRYFAILEDTESTCASES).toLowerCase().equalsIgnoreCase("yes")) {
			value = retryCount < FrameworkConstants.getFailedTestCaseRetryCount();
			retryCount++;
		}

		return value;
	}

}


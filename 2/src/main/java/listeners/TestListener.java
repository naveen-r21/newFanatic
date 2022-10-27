package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reports.ExtentLogger;
import reports.ExtentReport;

public class TestListener implements ITestListener, ISuiteListener{

	public void onStart(ISuite suite) {
		System.out.println();
		ExtentReport.initReports();
	}

	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
		System.out.println();
	}

	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result.getMethod().getMethodName() +" is Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getMethod().getMethodName() +" is Failed");
		ExtentLogger.log(result.getThrowable().toString());
		
	}

	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getMethod().getMethodName() +" is Skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		System.out.println();
	}

	public void onFinish(ITestContext context) {
		System.out.println();
	}

}

package reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.FrameworkConstants;
import frameworkEnums.ConfigProperties;
import utilities.PropertyUtils;

public final class ExtentReport {

	private static ExtentReports extent;
	public static ExtentTest test;

	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());

			extent.attachReporter(sparkReporter);
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("Fanatic");
			sparkReporter.config().setReportName(PropertyUtils.get(ConfigProperties.REPORTNAME));
		}
	}

	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentReportManager.unloadTest();
	}

	public static void createTest(String testCaseName) {
		ExtentReportManager.setExtentTest(extent.createTest(testCaseName));
	}

	

}
